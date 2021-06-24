package com.axity.springmvc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.axity.springmvc.dao.OfficeDAO;
import com.axity.springmvc.entity.EmployeeDO;
import com.axity.springmvc.entity.OfficeDO;
import com.axity.springmvc.exception.BusinessExcepcion;
import com.axity.springmvc.exception.BusinessExcepcionCode;

/**
 * Implementación del dao {@link mx.com.axity.poc.dao.OfficeDAO}
 * 
 * @author guillermo.segura@axity.com
 */
@Repository
@Transactional
public class OfficeDAOImpl implements OfficeDAO
{
  private static final String ERROR_EMPLOYEE_NOT_ASSIGNED = "El empleado no está asociado a la oficina";

  private static final String ERROR_EMPLOYEE_ALREADY_ASSIGNED = "El empleado ya está asociado a la oficina";

  private static final String ERROR_OFFICE_DOESNT_EXISTS = "La oficina no existe";

  private static final String QUERY_FIND_BY_OFFICE_CODE = "from OfficeDO as o WHERE o.officeCode = :officeCode";

  private static final String QUERY_FIND_BY_TERRITORY = "from OfficeDO as o WHERE o.territory = :territory ORDER BY o.officeCode";

  private static final String QUERY_COUNT_ALL = "SELECT COUNT(o) FROM OfficeDO as o";

  private static final String QUERY_FIND_ALL = "SELECT o FROM OfficeDO as o ORDER BY o.officeCode";

  private static final Logger LOG = LoggerFactory.getLogger( OfficeDAOImpl.class );

  private static final int PAGE_SIZE = 20;

  private static final int FIRST_PAGE = 0;

  @PersistenceContext
  private EntityManager em;

  /**
   * {@inheritDoc}
   */
  @Override
  public List<OfficeDO> findAll()
  {
    return findAll( FIRST_PAGE, PAGE_SIZE );
  }

  @Override
  public List<OfficeDO> findAll( int page, int pageSize )
  {

    TypedQuery<OfficeDO> query = em.createQuery( QUERY_FIND_ALL, OfficeDO.class );
    query.setMaxResults( pageSize );
    query.setFirstResult( page * pageSize );
    return query.getResultList();
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

  /**
   * {@inheritDoc}
   */
  @Override
  public List<OfficeDO> findByTerritory( String territory )
  {
    TypedQuery<OfficeDO> query = em
        .createQuery( QUERY_FIND_BY_TERRITORY, OfficeDO.class );
    query.setParameter( "territory", territory );
    return query.getResultList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public OfficeDO get( String officeCode )
  {

    TypedQuery<OfficeDO> query = em.createQuery( QUERY_FIND_BY_OFFICE_CODE,
      OfficeDO.class );
    query.setParameter( "officeCode", officeCode );
    OfficeDO office;
    try
    {
      office = query.getSingleResult();
    }
    catch( NoResultException e )
    {
      office = null;
    }
    return office;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void create( OfficeDO office )
  {
    em.persist( office );
    em.flush();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void edit( OfficeDO office )
  {
    OfficeDO entity = this.get( office.getOfficeCode() );
    if( entity != null )
    {
      entity.setAddressLine1( office.getAddressLine1() );
      entity.setAddressLine2( office.getAddressLine2() );
      entity.setCity( office.getCity() );
      entity.setCountry( office.getCity() );
      entity.setPhone( office.getPhone() );
      entity.setPostalCode( office.getPostalCode() );
      entity.setState( office.getState() );
      entity.setTerritory( office.getTerritory() );

      em.merge( entity );
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void delete( String officeCode )
  {
    OfficeDO entity = this.get( officeCode );
    if( entity != null )
    {
      em.remove( entity );
      em.flush();
    }
  }

  @Override
  public void addEmployee( String officeCode, EmployeeDO employee )
  {
    OfficeDO entity = this.get( officeCode );
    if( entity != null )
    {
      if( entity.getEmployees() == null )
      {
        entity.setEmployees( new ArrayList<EmployeeDO>() );
      }
      if( entity.getEmployees().contains( employee ) )
      {
        // Lanzar excepcion de negocio
        LOG.warn( ERROR_EMPLOYEE_ALREADY_ASSIGNED );
        BusinessExcepcion be = new BusinessExcepcion(ERROR_EMPLOYEE_ALREADY_ASSIGNED);
        be.setCode( BusinessExcepcionCode.INVALID_DATA );
        throw be;
      }
      else
      {
        entity.getEmployees().add( employee );
        this.edit( entity );
      }

    }
    else
    {
      
      LOG.warn( ERROR_OFFICE_DOESNT_EXISTS );
      BusinessExcepcion be = new BusinessExcepcion(ERROR_OFFICE_DOESNT_EXISTS);
      be.setCode( BusinessExcepcionCode.DB_INTEGRITY );
      throw be;
    }

  }

  @Override
  public void removeEmployee( String officeCode, EmployeeDO employee )
  {
    OfficeDO entity = this.get( officeCode );
    if( entity != null )
    {
      if( entity.getEmployees() == null )
      {
        entity.setEmployees( new ArrayList<EmployeeDO>() );
      }
      if( entity.getEmployees().contains( employee ) )
      {
        entity.getEmployees().remove( employee );
      }
      else
      {
        // Lanzar excepcion de negocio
        LOG.warn( ERROR_EMPLOYEE_NOT_ASSIGNED );
        BusinessExcepcion be = new BusinessExcepcion(ERROR_EMPLOYEE_NOT_ASSIGNED);
        be.setCode( BusinessExcepcionCode.INVALID_DATA );
        throw be;
      }

    }
    else
    {
      // Lanzar excepcion de negocio
      LOG.warn( ERROR_OFFICE_DOESNT_EXISTS );
      BusinessExcepcion be = new BusinessExcepcion(ERROR_OFFICE_DOESNT_EXISTS);
      be.setCode( BusinessExcepcionCode.INVALID_DATA );
      throw be;
    }

  }

}
