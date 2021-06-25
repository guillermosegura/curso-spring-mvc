package com.axity.springmvc.services;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.axity.springmvc.dao.EmployeeDAO;
import com.axity.springmvc.dao.OfficeDAO;
import com.axity.springmvc.dao.PaymentDAO;
import com.axity.springmvc.entity.EmployeeDO;
import com.axity.springmvc.entity.PaymentDO;
import com.axity.springmvc.exception.BusinessExcepcion;
import com.axity.springmvc.to.Customer;
import com.axity.springmvc.to.Employee;
import com.axity.springmvc.to.PaginatedResponse;
import com.axity.springmvc.to.Payment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RunWith(SpringRunner.class)
@ContextConfiguration({ "classpath:/beans.xml" })
@Transactional
public class CustomerServiceTest
{
  private static final Logger LOG = LoggerFactory.getLogger( CustomerServiceTest.class );
  @Autowired
  private CustomerService customerService;

  @Autowired
  private OfficeDAO officeDAO;

  @Autowired
  private EmployeeDAO employeeDAO;


  @Before
  public void setUp() throws Exception
  {
  }

  @Test
  public void testFindAllPaginated()
  {
    PaginatedResponse<Customer> paginated = customerService.findAllPaginated( 0, 20 );
    Assert.assertNotNull( paginated );
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    LOG.info( gson.toJson( paginated ) );
  }

  @Test
  public void testFindAllCustomers() throws JsonProcessingException
  {
    List<Customer> customers = customerService.findAllCustomers();
    Assert.assertNotNull( customers );

    ObjectMapper mapper = new ObjectMapper();
    PrettyPrinter pp = new DefaultPrettyPrinter();
    mapper.setDefaultPrettyPrinter( pp );
    LOG.info( mapper.writeValueAsString( customers ) );
  }

  @Test
  public void testGet()
  {
    Customer customer = customerService.get( 240L );
    Assert.assertNotNull( customer );
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    LOG.info( gson.toJson( customer ) );
  }

  @Test
  public void testCreate()
  {
    Customer customer = new Customer();
    customer.setCustomerName( "Nuevo" );
    customer.setContactFirstName( "John" );
    customer.setContactLastName( "Wick" );
    customer.setCountry( "México" );
    customer.setState( "CDMX" );
    customer.setCity( "CDMX" );
    customer.setAddressLine1( "addressLine1" );
    customer.setAddressLine2( "addressLine2" );
    customer.setCreditLimit( new BigDecimal( "120000.00" ) );
    customer.setPhone( "555555" );
    customer.setPostalCode( "01234" );
    Employee salesRepresentative = new Employee();
    salesRepresentative.setEmployeeNumber( 1501L );
    customer.setSalesRepresentative( salesRepresentative );
    customerService.create( customer );

    Long customerNumber = customer.getCustomerNumber();
    customer = customerService.get( customerNumber );
    Assert.assertNotNull( customer );

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    LOG.info( gson.toJson( customer ) );
  }

  @Test
  public void testCreate_employeeWithoutClients()
  {
    EmployeeDO employee = new EmployeeDO();
    employee.setCustomers( null );
    employee.setEmail( "email@email.com" );
    employee.setExtension( "123" );
    employee.setFirstName( "firstName" );
    employee.setJobTitle( "jobTitle" );
    employee.setLastName( "lastName" );
    employee.setOffice( this.officeDAO.get( "1" ) );
    this.employeeDAO.create( employee );

    Customer customer = new Customer();
    customer.setCustomerName( "Nuevo" );
    customer.setContactFirstName( "John" );
    customer.setContactLastName( "Wick" );
    customer.setCountry( "México" );
    customer.setState( "CDMX" );
    customer.setCity( "CDMX" );
    customer.setAddressLine1( "addressLine1" );
    customer.setAddressLine2( "addressLine2" );
    customer.setCreditLimit( new BigDecimal( "120000.00" ) );
    customer.setPhone( "555555" );
    customer.setPostalCode( "01234" );
    Employee salesRepresentative = new Employee();
    salesRepresentative.setEmployeeNumber( employee.getEmployeeNumber() );
    customer.setSalesRepresentative( salesRepresentative );
    customerService.create( customer );

    Long customerNumber = customer.getCustomerNumber();
    customer = customerService.get( customerNumber );
    Assert.assertNotNull( customer );

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    LOG.info( gson.toJson( customer ) );
  }

  @Test
  public void testCreate_withoutSalesRepresentative()
  {
    Customer customer = new Customer();
    customer.setCustomerName( "Nuevo" );
    customer.setContactFirstName( "John" );
    customer.setContactLastName( "Wick" );
    customer.setCountry( "México" );
    customer.setState( "CDMX" );
    customer.setCity( "CDMX" );
    customer.setAddressLine1( "addressLine1" );
    customer.setAddressLine2( "addressLine2" );
    customer.setCreditLimit( new BigDecimal( "120000.00" ) );
    customer.setPhone( "555555" );
    customer.setPostalCode( "01234" );
    customerService.create( customer );

    Long customerNumber = customer.getCustomerNumber();
    customer = customerService.get( customerNumber );
    Assert.assertNotNull( customer );

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    LOG.info( gson.toJson( customer ) );
  }

  @Test
  public void testCreate_withoutSalesRepresentative2()
  {
    Customer customer = new Customer();
    customer.setCustomerName( "Nuevo" );
    customer.setContactFirstName( "John" );
    customer.setContactLastName( "Wick" );
    customer.setCountry( "México" );
    customer.setState( "CDMX" );
    customer.setCity( "CDMX" );
    customer.setAddressLine1( "addressLine1" );
    customer.setAddressLine2( "addressLine2" );
    customer.setCreditLimit( new BigDecimal( "120000.00" ) );
    customer.setPhone( "555555" );
    customer.setPostalCode( "01234" );
    customer.setSalesRepresentative( new Employee() );
    customerService.create( customer );

    Long customerNumber = customer.getCustomerNumber();
    customer = customerService.get( customerNumber );
    Assert.assertNotNull( customer );

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    LOG.info( gson.toJson( customer ) );
  }

  @Test(expected = BusinessExcepcion.class)
  public void testCreate_nonExistentSalesRepresentative()
  {
    Customer customer = new Customer();
    customer.setCustomerName( "Nuevo" );
    customer.setContactFirstName( "John" );
    customer.setContactLastName( "Wick" );
    customer.setCountry( "México" );
    customer.setState( "CDMX" );
    customer.setCity( "CDMX" );
    customer.setAddressLine1( "addressLine1" );
    customer.setAddressLine2( "addressLine2" );
    customer.setCreditLimit( new BigDecimal( "120000.00" ) );
    customer.setPhone( "555555" );
    customer.setPostalCode( "01234" );
    Employee salesRepresentative = new Employee();
    salesRepresentative.setEmployeeNumber( 1502L );
    customer.setSalesRepresentative( salesRepresentative );
    customerService.create( customer );
  }

  @Test
  public void testEdit()
  {
    Customer customer = new Customer();
    customer.setCustomerName( "Nuevo" );
    customer.setContactFirstName( "John" );
    customer.setContactLastName( "Wick" );
    customer.setCountry( "México" );
    customer.setState( "CDMX" );
    customer.setCity( "CDMX" );
    customer.setAddressLine1( "addressLine1" );
    customer.setAddressLine2( "addressLine2" );
    customer.setCreditLimit( new BigDecimal( "120000.00" ) );
    customer.setPhone( "555555" );
    customer.setPostalCode( "01234" );

    customerService.create( customer );

    Long customerNumber = customer.getCustomerNumber();
    customer.setCustomerName( "edit1" );
    customer.setContactFirstName( "edit2" );
    customer.setContactLastName( "edit3" );
    customer.setCountry( "edit4" );
    customer.setState( "edit5" );
    customer.setCity( "edit6" );
    customer.setAddressLine1( "edit7" );
    customer.setAddressLine2( "edit8" );
    customer.setCreditLimit( new BigDecimal( "10.0" ) );
    customer.setPhone( "edit9" );
    customer.setPostalCode( "edit10" );

    Employee salesRepresentative = new Employee();
    salesRepresentative.setEmployeeNumber( 1504L );
    customer.setSalesRepresentative( salesRepresentative );
    customerService.edit( customer );

    customer = customerService.get( customerNumber );
    Assert.assertNotNull( customer );

    Assert.assertEquals( 1504L, customer.getSalesRepresentative().getEmployeeNumber().longValue() );
    Assert.assertEquals( "edit1", customer.getCustomerName() );
    Assert.assertEquals( "edit2", customer.getContactFirstName() );
    Assert.assertEquals( "edit3", customer.getContactLastName() );
    Assert.assertEquals( "edit4", customer.getCountry() );
    Assert.assertEquals( "edit5", customer.getState() );
    Assert.assertEquals( "edit6", customer.getCity() );
    Assert.assertEquals( "edit7", customer.getAddressLine1() );
    Assert.assertEquals( "edit8", customer.getAddressLine2() );
    Assert.assertEquals( "edit9", customer.getPhone() );
    Assert.assertEquals( "edit10", customer.getPostalCode() );
    Assert.assertEquals( new BigDecimal( "10.0" ), customer.getCreditLimit() );

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    LOG.info( gson.toJson( customer ) );
  }

  @Test
  public void testEdit_withNoSalesRepresentative()
  {
    Customer customer = new Customer();
    customer.setCustomerName( "Nuevo" );
    customer.setContactFirstName( "John" );
    customer.setContactLastName( "Wick" );
    customer.setCountry( "México" );
    customer.setState( "CDMX" );
    customer.setCity( "CDMX" );
    customer.setAddressLine1( "addressLine1" );
    customer.setAddressLine2( "addressLine2" );
    customer.setCreditLimit( new BigDecimal( "120000.00" ) );
    customer.setPhone( "555555" );
    customer.setPostalCode( "01234" );

    customerService.create( customer );

    Long customerNumber = customer.getCustomerNumber();
    customer.setCustomerName( "edit1" );
    customer.setContactFirstName( "edit2" );
    customer.setContactLastName( "edit3" );
    customer.setCountry( "edit4" );
    customer.setState( "edit5" );
    customer.setCity( "edit6" );
    customer.setAddressLine1( "edit7" );
    customer.setAddressLine2( "edit8" );
    customer.setCreditLimit( new BigDecimal( "10.0" ) );
    customer.setPhone( "edit9" );
    customer.setPostalCode( "edit10" );

    customerService.edit( customer );

    customer = customerService.get( customerNumber );
    Assert.assertNotNull( customer );

    Assert.assertNull( customer.getSalesRepresentative() );
    Assert.assertEquals( "edit1", customer.getCustomerName() );
    Assert.assertEquals( "edit2", customer.getContactFirstName() );
    Assert.assertEquals( "edit3", customer.getContactLastName() );
    Assert.assertEquals( "edit4", customer.getCountry() );
    Assert.assertEquals( "edit5", customer.getState() );
    Assert.assertEquals( "edit6", customer.getCity() );
    Assert.assertEquals( "edit7", customer.getAddressLine1() );
    Assert.assertEquals( "edit8", customer.getAddressLine2() );
    Assert.assertEquals( "edit9", customer.getPhone() );
    Assert.assertEquals( "edit10", customer.getPostalCode() );
    Assert.assertEquals( new BigDecimal( "10.0" ), customer.getCreditLimit() );

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    LOG.info( gson.toJson( customer ) );
  }

  @Test
  public void testEdit_removeSalesRepresentative()
  {
    Customer customer = new Customer();
    customer.setCustomerName( "Nuevo" );
    customer.setContactFirstName( "John" );
    customer.setContactLastName( "Wick" );
    customer.setCountry( "México" );
    customer.setState( "CDMX" );
    customer.setCity( "CDMX" );
    customer.setAddressLine1( "addressLine1" );
    customer.setAddressLine2( "addressLine2" );
    customer.setCreditLimit( new BigDecimal( "120000.00" ) );
    customer.setPhone( "555555" );
    customer.setPostalCode( "01234" );
    Employee salesRepresentative = new Employee();
    salesRepresentative.setEmployeeNumber( 1504L );
    customer.setSalesRepresentative( salesRepresentative );

    customerService.create( customer );

    Long customerNumber = customer.getCustomerNumber();
    customer.setCustomerName( "edit1" );
    customer.setContactFirstName( "edit2" );
    customer.setContactLastName( "edit3" );
    customer.setCountry( "edit4" );
    customer.setState( "edit5" );
    customer.setCity( "edit6" );
    customer.setAddressLine1( "edit7" );
    customer.setAddressLine2( "edit8" );
    customer.setCreditLimit( new BigDecimal( "10.0" ) );
    customer.setPhone( "edit9" );
    customer.setPostalCode( "edit10" );
    customer.setSalesRepresentative( null );
    customerService.edit( customer );

    customer = customerService.get( customerNumber );
    Assert.assertNotNull( customer );

    Assert.assertNull( customer.getSalesRepresentative() );
    Assert.assertEquals( "edit1", customer.getCustomerName() );
    Assert.assertEquals( "edit2", customer.getContactFirstName() );
    Assert.assertEquals( "edit3", customer.getContactLastName() );
    Assert.assertEquals( "edit4", customer.getCountry() );
    Assert.assertEquals( "edit5", customer.getState() );
    Assert.assertEquals( "edit6", customer.getCity() );
    Assert.assertEquals( "edit7", customer.getAddressLine1() );
    Assert.assertEquals( "edit8", customer.getAddressLine2() );
    Assert.assertEquals( "edit9", customer.getPhone() );
    Assert.assertEquals( "edit10", customer.getPostalCode() );
    Assert.assertEquals( new BigDecimal( "10.0" ), customer.getCreditLimit() );

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    LOG.info( gson.toJson( customer ) );
  }

  @Test
  public void testEdit_keepSalesRepresentative()
  {
    Customer customer = new Customer();
    customer.setCustomerName( "Nuevo" );
    customer.setContactFirstName( "John" );
    customer.setContactLastName( "Wick" );
    customer.setCountry( "México" );
    customer.setState( "CDMX" );
    customer.setCity( "CDMX" );
    customer.setAddressLine1( "addressLine1" );
    customer.setAddressLine2( "addressLine2" );
    customer.setCreditLimit( new BigDecimal( "120000.00" ) );
    customer.setPhone( "555555" );
    customer.setPostalCode( "01234" );
    Employee salesRepresentative = new Employee();
    salesRepresentative.setEmployeeNumber( 1504L );
    customer.setSalesRepresentative( salesRepresentative );

    customerService.create( customer );

    Long customerNumber = customer.getCustomerNumber();
    customer.setCustomerName( "edit1" );
    customer.setContactFirstName( "edit2" );
    customer.setContactLastName( "edit3" );
    customer.setCountry( "edit4" );
    customer.setState( "edit5" );
    customer.setCity( "edit6" );
    customer.setAddressLine1( "edit7" );
    customer.setAddressLine2( "edit8" );
    customer.setCreditLimit( new BigDecimal( "10.0" ) );
    customer.setPhone( "edit9" );
    customer.setPostalCode( "edit10" );

    salesRepresentative = new Employee();
    salesRepresentative.setEmployeeNumber( 1504L );
    customer.setSalesRepresentative( salesRepresentative );
    customerService.edit( customer );

    customer = customerService.get( customerNumber );
    Assert.assertNotNull( customer );

    Assert.assertEquals( 1504L, customer.getSalesRepresentative().getEmployeeNumber().longValue() );
    Assert.assertEquals( "edit1", customer.getCustomerName() );
    Assert.assertEquals( "edit2", customer.getContactFirstName() );
    Assert.assertEquals( "edit3", customer.getContactLastName() );
    Assert.assertEquals( "edit4", customer.getCountry() );
    Assert.assertEquals( "edit5", customer.getState() );
    Assert.assertEquals( "edit6", customer.getCity() );
    Assert.assertEquals( "edit7", customer.getAddressLine1() );
    Assert.assertEquals( "edit8", customer.getAddressLine2() );
    Assert.assertEquals( "edit9", customer.getPhone() );
    Assert.assertEquals( "edit10", customer.getPostalCode() );
    Assert.assertEquals( new BigDecimal( "10.0" ), customer.getCreditLimit() );

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    LOG.info( gson.toJson( customer ) );
  }

  @Test
  public void testEdit_changeSalesRepresentative()
  {
    Customer customer = new Customer();
    customer.setCustomerName( "Nuevo" );
    customer.setContactFirstName( "John" );
    customer.setContactLastName( "Wick" );
    customer.setCountry( "México" );
    customer.setState( "CDMX" );
    customer.setCity( "CDMX" );
    customer.setAddressLine1( "addressLine1" );
    customer.setAddressLine2( "addressLine2" );
    customer.setCreditLimit( new BigDecimal( "120000.00" ) );
    customer.setPhone( "555555" );
    customer.setPostalCode( "01234" );
    Employee salesRepresentative = new Employee();
    salesRepresentative.setEmployeeNumber( 1501L );
    customer.setSalesRepresentative( salesRepresentative );

    customerService.create( customer );

    Long customerNumber = customer.getCustomerNumber();
    customer.setCustomerName( "edit1" );
    customer.setContactFirstName( "edit2" );
    customer.setContactLastName( "edit3" );
    customer.setCountry( "edit4" );
    customer.setState( "edit5" );
    customer.setCity( "edit6" );
    customer.setAddressLine1( "edit7" );
    customer.setAddressLine2( "edit8" );
    customer.setCreditLimit( new BigDecimal( "10.0" ) );
    customer.setPhone( "edit9" );
    customer.setPostalCode( "edit10" );

    salesRepresentative = new Employee();
    salesRepresentative.setEmployeeNumber( 1504L );
    customer.setSalesRepresentative( salesRepresentative );
    customerService.edit( customer );

    customer = customerService.get( customerNumber );
    Assert.assertNotNull( customer );

    Assert.assertEquals( 1504L, customer.getSalesRepresentative().getEmployeeNumber().longValue() );
    Assert.assertEquals( "edit1", customer.getCustomerName() );
    Assert.assertEquals( "edit2", customer.getContactFirstName() );
    Assert.assertEquals( "edit3", customer.getContactLastName() );
    Assert.assertEquals( "edit4", customer.getCountry() );
    Assert.assertEquals( "edit5", customer.getState() );
    Assert.assertEquals( "edit6", customer.getCity() );
    Assert.assertEquals( "edit7", customer.getAddressLine1() );
    Assert.assertEquals( "edit8", customer.getAddressLine2() );
    Assert.assertEquals( "edit9", customer.getPhone() );
    Assert.assertEquals( "edit10", customer.getPostalCode() );
    Assert.assertEquals( new BigDecimal( "10.0" ), customer.getCreditLimit() );

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    LOG.info( gson.toJson( customer ) );
  }

  @Test(expected = BusinessExcepcion.class)
  public void testEdit_nonExistentcustomer()
  {
    Customer customer = new Customer();
    customer.setCustomerNumber( 9999999L );
    customer.setCustomerName( "Nuevo" );
    customer.setContactFirstName( "John" );
    customer.setContactLastName( "Wick" );
    customer.setCountry( "México" );
    customer.setState( "CDMX" );
    customer.setCity( "CDMX" );
    customer.setAddressLine1( "addressLine1" );
    customer.setAddressLine2( "addressLine2" );
    customer.setCreditLimit( new BigDecimal( "120000.00" ) );
    customer.setPhone( "555555" );
    customer.setPostalCode( "01234" );
    customerService.edit( customer );
  }

  @Test
  public void testDelete()
  {
    Customer customer = new Customer();
    customer.setCustomerName( "Nuevo" );
    customer.setContactFirstName( "John" );
    customer.setContactLastName( "Wick" );
    customer.setCountry( "México" );
    customer.setState( "CDMX" );
    customer.setCity( "CDMX" );
    customer.setAddressLine1( "addressLine1" );
    customer.setAddressLine2( "addressLine2" );
    customer.setCreditLimit( new BigDecimal( "120000.00" ) );
    customer.setPhone( "555555" );
    customer.setPostalCode( "01234" );
    Employee salesRepresentative = new Employee();
    salesRepresentative.setEmployeeNumber( 1501L );
    customer.setSalesRepresentative( salesRepresentative );
    customerService.create( customer );

    Long customerNumber = customer.getCustomerNumber();
    this.customerService.delete( customerNumber );

    customer = customerService.get( customerNumber );
    Assert.assertNull( customer );

  }

  @Test(expected = BusinessExcepcion.class)
  public void testDelete_notFound()
  {
    this.customerService.delete( 999999999999L );
  }


}
