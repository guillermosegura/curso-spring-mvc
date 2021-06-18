package com.axity.springmvc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.axity.springmvc.dao.EmployeeDAO;
import com.axity.springmvc.entity.EmployeeDO;

/**
 * Implementación de la interface {@link mx.com.axity.poc.dao.EmployeeDAO}
 * 
 * @author guillermo.segura@axity.com
 */
@Repository
public class EmployeeDAOImpl implements EmployeeDAO
{
  private static final Logger LOG = LoggerFactory.getLogger( EmployeeDAOImpl.class );
  
  private static final int PAGE_SIZE = 20;

  private static final int FIRST_PAGE = 0;

  private static final int MAX_RESULTS_DEFAULT = 20;

  @PersistenceContext
  private EntityManager em;

  /**
   * {@inheritDoc}
   */
  @Override
  public List<EmployeeDO> findAll()
  {
    return findAll(MAX_RESULTS_DEFAULT, FIRST_PAGE, PAGE_SIZE);
  }
  
  
  @Override
  public List<EmployeeDO> findAll(int maxResults, int page, int pageSize )
  {
    TypedQuery<EmployeeDO> query = em.createQuery( "FROM EmployeeDO e ORDER BY e.employeeNumber", EmployeeDO.class );
    query.setMaxResults( maxResults );
    query.setFirstResult( page * pageSize );
    return query.getResultList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<EmployeeDO> findByOffice( String officeCode )
  {
    TypedQuery<EmployeeDO> query = em.createQuery(
      "SELECT e FROM EmployeeDO e JOIN e.office o WHERE o.officeCode = :officeCode  ORDER BY e.employeeNumber",
      EmployeeDO.class );
    query.setParameter( "officeCode", officeCode );
    return query.getResultList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<EmployeeDO> findBySuperior( Long reportsTo )
  {
    TypedQuery<EmployeeDO> query = em.createQuery(
      "SELECT e FROM EmployeeDO e  WHERE e.reportsTo.employeeNumber = :reportsTo  ORDER BY e.employeeNumber",
      EmployeeDO.class );
    query.setParameter( "reportsTo", reportsTo );
    return query.getResultList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<EmployeeDO> findByLastName( String lastName )
  {
    TypedQuery<EmployeeDO> query = em.createQuery(
      "SELECT e FROM EmployeeDO e WHERE LOWER(e.lastName) LIKE LOWER(CONCAT('%', :lastName,'%')) ORDER BY e.employeeNumber",
      EmployeeDO.class );
    query.setParameter( "lastName", lastName );
    return query.getResultList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EmployeeDO get( Long employeeNumber )
  {
    TypedQuery<EmployeeDO> query = em
        .createQuery( "SELECT e FROM EmployeeDO e WHERE  e.employeeNumber = :employeeNumber", EmployeeDO.class );
    query.setParameter( "employeeNumber", employeeNumber );

    EmployeeDO employee;
    try
    {
      employee = query.getSingleResult();
    }
    catch( NoResultException e )
    {
      employee = null;
    }

    return employee;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void create( EmployeeDO employeeDO )
  {
    em.persist( employeeDO );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void edit( EmployeeDO employeeDO )
  {
    em.merge( employeeDO );
  }

}
