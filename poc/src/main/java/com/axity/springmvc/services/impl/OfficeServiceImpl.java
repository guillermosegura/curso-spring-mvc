package com.axity.springmvc.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axity.springmvc.dao.OfficeDAO;
import com.axity.springmvc.entity.OfficeDO;
import com.axity.springmvc.services.OfficeService;
import com.axity.springmvc.to.Office;

/**
 * Implementación de la interface {@link mx.com.axity.poc.service.OfficeService}
 * 
 * @author guillermo.segura@axity.com
 */
@Service
@Transactional
public class OfficeServiceImpl implements OfficeService
{
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
    officeDAO.create( transform( office ) );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void edit( Office office )
  {
    officeDAO.edit( transform( office ) );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void delete( String officeCode )
  {
    officeDAO.delete( officeCode );
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
