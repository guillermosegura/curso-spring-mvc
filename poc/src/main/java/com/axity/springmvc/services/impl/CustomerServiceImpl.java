package com.axity.springmvc.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axity.springmvc.dao.CustomerDAO;
import com.axity.springmvc.dao.EmployeeDAO;
import com.axity.springmvc.dao.PaymentDAO;
import com.axity.springmvc.entity.CustomerDO;
import com.axity.springmvc.entity.EmployeeDO;
import com.axity.springmvc.entity.OfficeDO;
import com.axity.springmvc.entity.PaymentDO;
import com.axity.springmvc.entity.PaymentId;
import com.axity.springmvc.exception.BusinessExcepcion;
import com.axity.springmvc.exception.BusinessExcepcionCode;
import com.axity.springmvc.services.CustomerService;
import com.axity.springmvc.services.EmployeeService;
import com.axity.springmvc.services.OfficeService;
import com.axity.springmvc.to.Commission;
import com.axity.springmvc.to.Customer;
import com.axity.springmvc.to.Employee;
import com.axity.springmvc.to.PaginatedResponse;
import com.axity.springmvc.to.Payment;

/**
 * Implementación de la interface {@link com.axity.springmvc.services.CustomerService}
 * 
 * @author guillermo.segura@axity.com
 */
@Service
public class CustomerServiceImpl implements CustomerService
{

  private static Function<CustomerDO, Customer> transformerToCustomer = e -> {
    Customer customer = new Customer();
    BeanUtils.copyProperties( e, customer );
    Employee salesRepresentative = null;

    if( e.getSalesRepEmployee() != null )
    {
      salesRepresentative = new Employee();
      salesRepresentative.setEmployeeNumber( e.getSalesRepEmployee().getEmployeeNumber() );
      salesRepresentative.setFirstName( e.getSalesRepEmployee().getFirstName() );
      salesRepresentative.setLastName( e.getSalesRepEmployee().getLastName() );
    }
    customer.setSalesRepresentative( salesRepresentative );

    return customer;
  };

  private static Function<CustomerDO, Customer> transformerToCustomerCompact = e -> {
    Customer customer = new Customer();
    BeanUtils.copyProperties( e, customer );
    return customer;
  };

  @Autowired
  private CustomerDAO customerDAO;

  @Autowired
  private EmployeeDAO employeeDAO;

  @Autowired
  private PaymentDAO paymentDAO;

  @Autowired
  private OfficeService officeService;

  @Autowired
  private EmployeeService employeeService;

  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponse<Customer> findAllPaginated( int page, int pageSize )
  {
    List<CustomerDO> entities = this.customerDAO.findAll( page, pageSize );

    PaginatedResponse<Customer> paginated = new PaginatedResponse<>();

    List<Customer> customers = entities.stream().map( transformerToCustomer ).collect( Collectors.toList() );
    paginated.setResponse( customers );

    paginated.setPage( page );
    paginated.setPageSize( pageSize );

    int pages = this.customerDAO.countAll();
    paginated.setPages( pages );

    return paginated;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Customer> findAllCustomers()
  {
    List<CustomerDO> entities = this.customerDAO.findAllCustomers();
    return entities.stream().map( transformerToCustomerCompact ).collect( Collectors.toList() );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Customer get( Long customerNumber )
  {
    CustomerDO entity = this.customerDAO.get( customerNumber );
    Customer customer = null;
    if( entity != null )
    {
      customer = transformerToCustomer.apply( entity );
    }
    return customer;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void create( Customer customer )
  {
    CustomerDO entity = new CustomerDO();
    BeanUtils.copyProperties( customer, entity );
    entity.setCustomerNumber( null );

    if( customer.getSalesRepresentative() != null && customer.getSalesRepresentative().getEmployeeNumber() != null )
    {
      Employee salesRepresentative = customer.getSalesRepresentative();
      setSalesRepresentative( entity, salesRepresentative );
    }

    this.customerDAO.create( entity );
    customer.setCustomerNumber( entity.getCustomerNumber() );
  }

  private void setSalesRepresentative( CustomerDO entity, Employee salesRepresentative )
  {
    EmployeeDO salesRepEmployee = this.employeeDAO.get( salesRepresentative.getEmployeeNumber() );

    if( salesRepEmployee == null )
    {
      BusinessExcepcion be = new BusinessExcepcion( "No existe el representante de ventas" );
      be.setCode( BusinessExcepcionCode.INVALID_DATA );
      throw be;
    }
    // Existe el registro en la BD del empleado
    entity.setSalesRepEmployee( salesRepEmployee );

    if( salesRepEmployee.getCustomers() == null )
    {
      salesRepEmployee.setCustomers( new ArrayList<>() );
    }
    salesRepEmployee.getCustomers().add( entity );
    this.employeeDAO.edit( salesRepEmployee );

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void edit( Customer customer )
  {
    CustomerDO entity = this.customerDAO.get( customer.getCustomerNumber() );
    if( entity != null )
    {
      BeanUtils.copyProperties( customer, entity );
      EmployeeDO originalSalesRepresentative = entity.getSalesRepEmployee();

      if( customer.getSalesRepresentative() != null && customer.getSalesRepresentative().getEmployeeNumber() != null )
      {
        Employee salesRepresentative = customer.getSalesRepresentative();

        if( originalSalesRepresentative == null )
        {
          // Se agrega un nuevo representante
          setSalesRepresentative( entity, salesRepresentative );
        }
        else if( !salesRepresentative.getEmployeeNumber().equals( originalSalesRepresentative.getEmployeeNumber() ) )
        {
          // Cambio de representante de ventas
          originalSalesRepresentative.getCustomers().remove( entity );
          this.employeeDAO.edit( originalSalesRepresentative );
          // Se agrega el nuevo
          setSalesRepresentative( entity, salesRepresentative );
        }

      }
      else if( originalSalesRepresentative != null )
      {
        // Se quita el representante de ventas
        originalSalesRepresentative.getCustomers().remove( entity );
        this.employeeDAO.edit( originalSalesRepresentative );
        entity.setSalesRepEmployee( null );
      }
      this.customerDAO.edit( entity );
    }
    else
    {
      BusinessExcepcion be = new BusinessExcepcion( "No existe el cliente" );
      be.setCode( BusinessExcepcionCode.INVALID_DATA );
      throw be;
    }

  }

  @Override
  public void delete( Long customerNumber )
  {
    CustomerDO entity = this.customerDAO.get( customerNumber );
    if( entity != null )
    {
      this.customerDAO.delete( customerNumber );
    }
    else
    {
      BusinessExcepcion be = new BusinessExcepcion( "No existe el cliente" );
      be.setCode( BusinessExcepcionCode.INVALID_DATA );
      throw be;
    }
  }

  @Override
  public Commission assignPayment( Payment payment )
  {
    CustomerDO customer = this.customerDAO.get( payment.getCustomerNumber() );
    validateCustomer( customer );
    validateSalesRepresentantAssigned( customer );

    PaymentDO entity = new PaymentDO();
    entity.setId( new PaymentId( payment.getCustomerNumber(), payment.getCheckNumber() ) );
    entity.setAmount( payment.getAmount() );

    Calendar cal = new GregorianCalendar( payment.getDate().getYear(), payment.getDate().getMonthValue() - 1,
        payment.getDate().getDayOfMonth() );

    entity.setPaymentDate( cal.getTime() );
    paymentDAO.create( entity );

    OfficeDO office = customer.getSalesRepEmployee().getOffice();
    BigDecimal commissionPercent = officeService.getCommission( office.getOfficeCode() );
    validateCommission( commissionPercent );

    Commission commission = new Commission();
    BeanUtils.copyProperties( payment, commission );
    commission.setEmployeeNumber( customer.getSalesRepEmployee().getEmployeeNumber() );
    commission.setPercent( commissionPercent );
    commission.setSalesCommission( commissionPercent.multiply( payment.getAmount() ) );

    Long commissionId = employeeService.saveCommision( commission );
    commission.setCommissionId( commissionId );

    return commission;
  }

  private void validateCommission( BigDecimal commission )
  {
    if( commission == null )
    {
      BusinessExcepcion be = new BusinessExcepcion( "La comisión no está asignada" );
      be.setCode( BusinessExcepcionCode.DB_INTEGRITY );
      throw be;
    }
  }

  private void validateSalesRepresentantAssigned( CustomerDO customer )
  {
    if( customer.getSalesRepEmployee() == null )
    {
      BusinessExcepcion be = new BusinessExcepcion( "No tiene representante de ventas" );
      be.setCode( BusinessExcepcionCode.DB_INTEGRITY );
      throw be;
    }
  }

  private void validateCustomer( CustomerDO customer )
  {
    if( customer == null )
    {
      BusinessExcepcion be = new BusinessExcepcion( "No existe el customer" );
      be.setCode( BusinessExcepcionCode.DB_INTEGRITY );
      throw be;
    }
  }

}
