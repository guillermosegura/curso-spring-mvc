package com.axity.springmvc.dao.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.axity.springmvc.dao.CustomerDAO;
import com.axity.springmvc.entity.CustomerDO;
import com.axity.springmvc.util.DaoUtil;

/**
 * Clase que implementa la interface {@link mx.com.axity.poc.dao.impl.CustomerDAOImpl}
 * 
 * @author guillermo.segura@axity.com
 */
@Repository
public class CustomerDAOImpl implements CustomerDAO
{

  private static final String QUERY_FIND_ALL = "SELECT o FROM CustomerDO o ORDER BY o.customerName";

  private static final String QUERY_COUNT_ALL = "SELECT COUNT(o) FROM CustomerDO as o";

  private static final String QUERY_FIND_BY_CUSTOMER_NUMBER = "SELECT o FROM CustomerDO o"
      + " WHERE o.customerNumber = :customerNumber";

  private static final String QUERY_FIND_BY_SALES_REPRESENTATIVE = "SELECT o FROM CustomerDO o"
      + " WHERE o.salesRepEmployee.employeeNumber = :employeeNumber";

  private static final String QUERY_FIND_ALL_CUSTOMERS = "SELECT o.customerNumber, o.customerName, o.contactLastName, o.contactFirstName"
      + " FROM CustomerDO o ORDER BY o.customerName, o.customerNumber";

  private static final String QUERY_FIND_BY_CUSTOMER_NAME = "SELECT o FROM CustomerDO o"
      + " WHERE LOWER(o.customerName) LIKE LOWER(CONCAT('%', :customerName,'%'))";

  private static final int PAGE_SIZE = 20;

  private static final int FIRST_PAGE = 0;

  private static final Logger LOG = LoggerFactory.getLogger( CustomerDAOImpl.class );

  @PersistenceContext
  private EntityManager em;

  @Override
  public List<CustomerDO> findAll()
  {
    return findAll( FIRST_PAGE, PAGE_SIZE );
  }

  @Override
  public List<CustomerDO> findAll( int page, int pageSize )
  {
    TypedQuery<CustomerDO> typedQuery = em.createQuery( QUERY_FIND_ALL, CustomerDO.class );
    typedQuery.setMaxResults( pageSize );
    typedQuery.setFirstResult( page * pageSize );
    return typedQuery.getResultList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int countAll()
  {
    TypedQuery<Number> query = em.createQuery( QUERY_COUNT_ALL, Number.class );
    return query.getSingleResult().intValue();
  }

  @Override
  public List<CustomerDO> findByCustomerName( String customerName )
  {
    TypedQuery<CustomerDO> typedQuery = em.createQuery( QUERY_FIND_BY_CUSTOMER_NAME, CustomerDO.class );
    typedQuery.setParameter( "customerName", customerName );
    return typedQuery.getResultList();
  }

  @Override
  public List<CustomerDO> findAllCustomers()
  {
    TypedQuery<Object[]> typedQuery = em.createQuery( QUERY_FIND_ALL_CUSTOMERS, Object[].class );

    Function<Object[], CustomerDO> mapper = o -> {
      CustomerDO customerDO = new CustomerDO();
      customerDO.setCustomerNumber( DaoUtil.safeLong( o[0] ) );
      customerDO.setCustomerName( DaoUtil.safeString( o[1] ) );
      customerDO.setContactLastName( DaoUtil.safeString( o[2] ) );
      customerDO.setContactFirstName( DaoUtil.safeString( o[3] ) );
      return customerDO;
    };
    return typedQuery.getResultList().stream().map( mapper ).collect( Collectors.toList() );
  }

  @Override
  public List<CustomerDO> findBySalesRepEmployee( Long employeeNumber )
  {
    TypedQuery<CustomerDO> typedQuery = em.createQuery( QUERY_FIND_BY_SALES_REPRESENTATIVE, CustomerDO.class );
    typedQuery.setParameter( "employeeNumber", employeeNumber );
    return typedQuery.getResultList();
  }

  @Override
  public CustomerDO get( Long customerNumber )
  {

    TypedQuery<CustomerDO> typedQuery = em.createQuery( QUERY_FIND_BY_CUSTOMER_NUMBER, CustomerDO.class );
    typedQuery.setParameter( "customerNumber", customerNumber );

    CustomerDO customer;
    try
    {
      customer = typedQuery.getSingleResult();
    }
    catch( PersistenceException e )
    {
      LOG.error( e.getMessage() );
      customer = null;
    }

    return customer;
  }

  @Override
  public void create( CustomerDO customer )
  {
    em.persist( customer );

  }

  @Override
  public void edit( CustomerDO customer )
  {
    CustomerDO entity = this.get( customer.getCustomerNumber() );
    if( entity != null )
    {
      entity.setCustomerName( customer.getCustomerName() );
      entity.setContactLastName( customer.getContactLastName() );
      entity.setContactFirstName( customer.getContactFirstName() );
      entity.setPhone( customer.getPhone() );
      entity.setAddressLine1( customer.getAddressLine1() );
      entity.setAddressLine2( customer.getAddressLine2() );
      entity.setCity( customer.getCity() );
      entity.setState( customer.getState() );
      entity.setPostalCode( customer.getPostalCode() );
      entity.setCountry( customer.getCountry() );
      entity.setCreditLimit( customer.getCreditLimit() );

      entity.setSalesRepEmployee( customer.getSalesRepEmployee() );

      em.merge( entity );
    }

  }

  @Override
  public void delete( Long customerNumber )
  {
    CustomerDO entity = this.get( customerNumber );
    if( entity != null )
    {
      em.remove( entity );
      em.flush();
    }

  }

}
