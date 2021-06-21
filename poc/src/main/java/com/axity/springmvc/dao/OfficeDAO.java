package com.axity.springmvc.dao;

import java.util.List;

import com.axity.springmvc.entity.EmployeeDO;
import com.axity.springmvc.entity.OfficeDO;

/**
 * Interface DAO para las oficina
 * 
 * @author guillermo.segura@axity.com
 */
public interface OfficeDAO
{

  /**
   * Busca los registros de la tabla offices
   * 
   * @return
   */
  List<OfficeDO> findAll();

  /**
   * Busca los registros de la tabla offices por paginacion
   * 
   * @param page
   * @param pageSize
   * @return
   */
  List<OfficeDO> findAll( int page, int pageSize );

  /**
   * Cuenta los registros
   * 
   * @return
   */
  int countAll();

  /**
   * Busca los registros de la tabla offices por territorio
   * 
   * @param territory
   * @return
   */
  List<OfficeDO> findByTerritory( String territory );

  /**
   * Busca una oficina por su código en la tabla offices
   * 
   * @param officeCode
   * @return
   */
  OfficeDO get( String officeCode );

  /**
   * Crea un registro en la tabla offices
   * 
   * @param office
   */
  void create( OfficeDO office );

  /**
   * Edita un registro en la tabla offices
   * 
   * @param office
   */
  void edit( OfficeDO office );

  /**
   * Elimina un registro a partir de su código de oficina
   * 
   * @param officeCode
   */
  void delete( String officeCode );

  /**
   * Agrega un emplado a la oficina
   * 
   * @param officeCode
   * @param employee
   */
  void addEmployee( String officeCode, EmployeeDO employee );

  /**
   * Quita un empleado de la oficina
   * 
   * @param officeCode
   * @param employee
   */
  void removeEmployee( String officeCode, EmployeeDO employee );

}
