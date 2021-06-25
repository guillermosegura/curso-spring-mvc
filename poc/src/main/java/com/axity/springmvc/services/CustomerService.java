package com.axity.springmvc.services;

import java.util.List;

import com.axity.springmvc.to.Commission;
import com.axity.springmvc.to.Customer;
import com.axity.springmvc.to.PaginatedResponse;
import com.axity.springmvc.to.Payment;

/**
 * Interface para la manipulación de clientes
 * 
 * @author guillermo.segura@axity.com
 */
public interface CustomerService
{

  /**
   * Búsqueda paginada
   * 
   * @param page
   * @param pageSize
   * @return
   */
  PaginatedResponse<Customer> findAllPaginated( int page, int pageSize );

  /**
   * Busca todos los clientes (recortados)
   * 
   * @return
   */
  List<Customer> findAllCustomers();

  /**
   * Obtiene un cliente por su customerNumber
   * 
   * @param customerNumber
   * @return
   */
  Customer get( Long customerNumber );

  /**
   * Crea un registro de cliente
   * 
   * @param customer
   */
  void create( Customer customer );

  /**
   * Edita un registro de cliente
   * 
   * @param customer
   */
  void edit( Customer customer );

  /**
   * Elimina un registro de cliente
   * 
   * @param customerNumber
   */
  void delete( Long customerNumber );

  /**
   * Asigna el pago a un cliente
   * 
   * @param payment
   */
  Commission assignPayment( Payment payment );
}
