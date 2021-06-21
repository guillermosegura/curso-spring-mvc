package com.axity.springmvc.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axity.springmvc.dao.OfficeDAO;
import com.axity.springmvc.entity.OfficeDO;
import com.axity.springmvc.exception.BusinessExcepcion;
import com.axity.springmvc.exception.BusinessExcepcionCode;
import com.axity.springmvc.services.OfficeService;
import com.axity.springmvc.to.Office;
import com.axity.springmvc.util.Validator;

/**
 * Implementación de la interface {@link mx.com.axity.poc.service.OfficeService}
 * 
 * @author guillermo.segura@axity.com
 */
@Service
@Transactional
public class OfficeServiceImpl implements OfficeService
{

  private static final Logger LOG = LoggerFactory.getLogger( OfficeServiceImpl.class );

  @Autowired
  private OfficeDAO officeDAO;

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Office> findAll()
  {
    List<Office> list = null;

    List<OfficeDO> offices = officeDAO.findAll();
    if( offices != null )
    {
      list = new ArrayList<Office>( offices.size() );
      for( OfficeDO office : offices )
      {
        list.add( transform( office ) );
      }
    }

    return list;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Office> findByTerritory( String territory )
  {
    List<Office> list = null;

    List<OfficeDO> offices = officeDAO.findByTerritory( territory );
    if( offices != null )
    {
      list = new ArrayList<Office>( offices.size() );
      for( OfficeDO office : offices )
      {
        list.add( transform( office ) );
      }
    }
    return list;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Office get( String officeCode )
  {
    return transform( officeDAO.get( officeCode ) );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void create( Office office )
  {

    Validator.validateNotNull( office.getOfficeCode(), "El campo Código de oficina es requerido" );
    Validator.validateNotNull( office.getCity(), "El campo Ciudad es requerido" );
    Validator.validateNotNull( office.getPhone(), "El campo Teléfono es requerido" );
    Validator.validateNotNull( office.getAddressLine1(), "El campo Dirección 1 es requerido" );
    Validator.validateNotNull( office.getState(), "El campo Dirección 2 es requerido" );
    Validator.validateNotNull( office.getCountry(), "El campo País es requerido" );
    Validator.validateNotNull( office.getPostalCode(), "El campo Código Postal es requerido" );
    Validator.validateNotNull( office.getTerritory(), "El campo Territorio es requerido" );

    try
    {
      officeDAO.create( transform( office ) );
      LOG.info( "Se ha creado el registro {}", officeDAO.get( office.getOfficeCode() ) );
    }
    catch( PersistenceException e )
    {
      LOG.error( e.getMessage(), e );

      BusinessExcepcion businessExcepcion = new BusinessExcepcion(
          "Ocurri&oacute; un error de persistencia: " + e.getMessage(), e );
      businessExcepcion.setCode( BusinessExcepcionCode.DB_INTEGRITY );
      throw businessExcepcion;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void edit( Office office )
  {
    try
    {
      officeDAO.edit( transform( office ) );
    }
    catch( PersistenceException e )
    {
      LOG.error( e.getMessage(), e );

      BusinessExcepcion businessExcepcion = new BusinessExcepcion(
          "Ocurri&oacute; un error de persistencia: " + e.getMessage(), e );
      businessExcepcion.setCode( BusinessExcepcionCode.DB_INTEGRITY );
      throw businessExcepcion;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void delete( String officeCode )
  {
    try
    {
      officeDAO.delete( officeCode );
    }
    catch( PersistenceException e )
    {
      LOG.error( e.getMessage(), e );

      BusinessExcepcion businessExcepcion = new BusinessExcepcion(
          "Ocurri&oacute; un error de persistencia: " + e.getMessage(), e );
      businessExcepcion.setCode( BusinessExcepcionCode.DB_INTEGRITY );
      throw businessExcepcion;
    }
  }

  private Office transform( OfficeDO entity )
  {
    Office to = null;
    if( entity != null )
    {
      to = new Office();
      BeanUtils.copyProperties( entity, to );
    }
    return to;
  }

  private OfficeDO transform( Office to )
  {
    OfficeDO entity = null;
    if( to != null )
    {
      entity = new OfficeDO();
      BeanUtils.copyProperties( to, entity );
    }
    return entity;
  }
}
