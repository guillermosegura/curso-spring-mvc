package com.axity.springmvc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.axity.springmvc.dao.CustomerDAO;
import com.axity.springmvc.entity.CustomerDO;

/**
 * Clase que implementa la interface {@link mx.com.axity.poc.dao.impl.CustomerDAOImpl}
 * 
 * @author guillermo.segura@axity.com
 */
@Repository
public class CustomerDAOImpl implements CustomerDAO
{

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
    String query = "SELECT o FROM CustomerDO o ORDER BY o.customerName";
    TypedQuery<CustomerDO> typedQuery = em.createQuery( query, CustomerDO.class );
    typedQuery.setMaxResults( pageSize );
    typedQuery.setFirstResult( page * pageSize );
    return typedQuery.getResultList();
  }

  @Override
  public List<CustomerDO> findByCustomerName( String customerName )
  {
    String query = "SELECT o FROM CustomerDO o"
        + " WHERE LOWER(o.customerName) LIKE LOWER(CONCAT('%', :customerName,'%'))";
    TypedQuery<CustomerDO> typedQuery = em.createQuery( query, CustomerDO.class );
    typedQuery.setParameter( "customerName", customerName );
    return typedQuery.getResultList();
  }

  @Override
  public List<CustomerDO> findBySalesRepEmployee( Long employeeNumber )
  {
    String query = "SELECT o FROM CustomerDO o" + " WHERE o.salesRepEmployee.employeeNumber = :employeeNumber";
    TypedQuery<CustomerDO> typedQuery = em.createQuery( query, CustomerDO.class );
    typedQuery.setParameter( "employeeNumber", employeeNumber );
    return typedQuery.getResultList();
  }

  @Override
  public CustomerDO get( Long customerNumber )
  {
    String query = "SELECT o FROM CustomerDO o" + " WHERE o.customerNumber = :customerNumber";

    TypedQuery<CustomerDO> typedQuery = em.createQuery( query, CustomerDO.class );
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
    // TODO Auto-generated method stub

  }

  @Override
  public void edit( CustomerDO customer )
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void delete( Long customerNumber )
  {
    // TODO Auto-generated method stub

  }

}
