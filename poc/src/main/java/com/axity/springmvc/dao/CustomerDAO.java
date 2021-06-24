package com.axity.springmvc.dao;

import java.util.List;

import com.axity.springmvc.dao.projection.CustomerSmall;
import com.axity.springmvc.entity.CustomerDO;

/**
 * Interface DAO para
 * 
 * @author guillermo.segura@axity.com
 */
public interface CustomerDAO
{

  /**
   * Obtiene todos los clientes
   * 
   * @return
   */
  List<CustomerDO> findAll();

  /**
   * Obtiene los clientes por paginacion
   * 
   * @param page
   * @param pageSize
   * @return
   */
  List<CustomerDO> findAll( int page, int pageSize );

  /**
   * Regresa la cantidad de registros
   * 
   * @return
   */
  int countAll();
  
  List<CustomerDO> findAllCustomers();

  /**
   * Busca los clientes por su nombre
   * 
   * @param customerName
   * @return
   */
  List<CustomerDO> findByCustomerName( String customerName );

  /**
   * Busca los clientes por el id del empleado asignado como representante de ventas
   * 
   * @param employeeNumber
   * @return
   */
  List<CustomerDO> findBySalesRepEmployee( Long employeeNumber );

  /**
   * Busca un cliente porsu id
   * 
   * @param customerNumber
   * @return
   */
  CustomerDO get( Long customerNumber );

  /**
   * Crea un cliente
   * 
   * @param customer
   */
  void create( CustomerDO customer );

  /**
   * Edita un cliente
   * 
   * @param customer
   */
  void edit( CustomerDO customer );

  /**
   * Elimina un cliente
   * 
   * @param customerNumber
   */
  void delete( Long customerNumber );

}
