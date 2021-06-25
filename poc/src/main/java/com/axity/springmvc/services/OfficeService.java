package com.axity.springmvc.services;

import java.math.BigDecimal;
import java.util.List;

import com.axity.springmvc.to.Office;
import com.axity.springmvc.to.PaginatedResponse;

/**
 * Servicio de oficinas
 * 
 * @author guillermo.segura@axity.com
 */
public interface OfficeService
{
  /**
   * Busca todas las oficinas
   * 
   * @return
   */
  List<Office> findAll();

  /**
   * Busca todas las oficinas
   * 
   * @return
   */
  PaginatedResponse<Office> findAllPaginated( int page, int pageSize );

  /**
   * Busca las oficinas por territorio
   * 
   * @param territory
   * @return
   */
  List<Office> findByTerritory( String territory );

  /**
   * Busca una oficina por su código
   * 
   * @param officeCode
   * @return
   */
  Office get( String officeCode );

  /**
   * Crea una oficina
   * 
   * @param office
   */
  void create( Office office );

  /**
   * Actualiza una oficina
   * 
   * @param office
   */
  void edit( Office office );

  /**
   * Elimina un registro de oficina
   * 
   * @param officeCode
   */
  void delete( String officeCode );

  /**
   * Obtiene el porcentaje de comision
   * 
   * @param officeCode
   * @return
   */
  BigDecimal getCommission( String officeCode );
}
