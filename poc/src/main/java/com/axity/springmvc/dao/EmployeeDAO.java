package com.axity.springmvc.dao;

import java.util.List;

import com.axity.springmvc.entity.EmployeeDO;

/**
 * Interface dao para la manipulación de los empleados
 * 
 * @author guillermo.segura@axity.com
 */
public interface EmployeeDAO
{

  /**
   * Busca todos los empleados
   * 
   * @return
   */
  List<EmployeeDO> findAll();

  /**
   * Busca los empleados por paginacion
   * 
   * @param page
   * @param pageSize
   * @return
   */
  List<EmployeeDO> findAll( int page, int pageSize );

  /**
   * Busca los empleados de una oficina en particular
   * 
   * @param officeCode
   * @return
   */
  List<EmployeeDO> findByOffice( String officeCode );

  /**
   * Busca los empleados que le reportan a un jefe en particular
   * 
   * @param reportsTo
   * @return
   */
  List<EmployeeDO> findBySuperior( Long reportsTo );

  /**
   * Busca los empleados por apellido
   * 
   * @param lastName
   * @return
   */
  List<EmployeeDO> findByLastName( String lastName );

  /**
   * Busca los empleados por su número de empleado
   * 
   * @param employeeNumber
   * @return
   */
  EmployeeDO get( Long employeeNumber );

  /**
   * Crea un nuevo empleado
   * 
   * @param employeeDO
   */
  void create( EmployeeDO employeeDO );

  /**
   * Edita un empleado
   * 
   * @param employeeDO
   */
  void edit( EmployeeDO employeeDO );

}
