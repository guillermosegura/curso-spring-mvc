package com.axity.springmvc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.axity.springmvc.dao.OfficeDAO;
import com.axity.springmvc.entity.EmployeeDO;
import com.axity.springmvc.entity.OfficeDO;

/**
 * Implementación del dao {@link mx.com.axity.poc.dao.OfficeDAO}
 * 
 * @author guillermo.segura@axity.com
 */
@Repository
public class OfficeDAOImpl implements OfficeDAO
{
  private static final Logger LOG = LoggerFactory.getLogger( OfficeDAOImpl.class );

  @PersistenceContext
  private EntityManager em;

  /**
   * {@inheritDoc}
   */
  @Override
  public List<OfficeDO> findAll()
  {

    TypedQuery<OfficeDO> query = em.createQuery( "SELECT o FROM OfficeDO as o ORDER BY o.officeCode", OfficeDO.class );
    return query.getResultList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<OfficeDO> findByTerritory( String territory )
  {
    TypedQuery<OfficeDO> query = em
        .createQuery( "from OfficeDO as o WHERE o.territory = :territory ORDER BY o.officeCode", OfficeDO.class );
    query.setParameter( "territory", territory );
    return query.getResultList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public OfficeDO get( String officeCode )
  {

    TypedQuery<OfficeDO> query = em.createQuery( "from OfficeDO as o WHERE o.officeCode = :officeCode",
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
        LOG.warn( "El empleado ya está asociado a la oficina" );
        throw new RuntimeException( "El empleado ya está asociado a la oficina" );
      }
      else
      {
        entity.getEmployees().add( employee );
        this.edit( entity );
      }

    }
    else
    {
      // Lanzar excepcion de negocio
      LOG.warn( "La oficina no existe" );
      throw new RuntimeException( "La oficina no existe" );
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
        LOG.warn( "El empleado no está asociado a la oficina" );
        throw new RuntimeException( "El empleado no está asociado a la oficina" );
      }

    }
    else
    {
      // Lanzar excepcion de negocio
      LOG.warn( "La oficina no existe" );
      throw new RuntimeException( "La oficina no existe" );
    }

  }

}
