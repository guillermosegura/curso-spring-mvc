package com.axity.springmvc.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axity.springmvc.aop.JsonResponseIntercept;
import com.axity.springmvc.exception.Module;
import com.axity.springmvc.services.CustomerService;
import com.axity.springmvc.to.Customer;
import com.axity.springmvc.to.PaginatedResponse;
import com.axity.springmvc.util.Validator;

@RestController
public class CustomerController
{

  @Autowired
  private CustomerService customerService;

  /**
   * Atiende la petición Rest de consulta de clientes
   * 
   * @return
   */
  @GetMapping(path = "/api/customer", produces = MediaType.APPLICATION_JSON_VALUE)
  @JsonResponseIntercept(Module.CUSTOMER)
  public ResponseEntity<Serializable> getCustomers( @RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "20") int pageSize )
  {
    PaginatedResponse<Customer> customers = customerService.findAllPaginated( page, pageSize );
    return new ResponseEntity<>( customers, HttpStatus.OK );
  }

  /**
   * Busca los clientes
   * 
   * @return Un listado de clientes con la información recortada
   * 
   *         <pre>
   *         { "customerNumber": 0, 
   *           "customerName": "", 
   *           "contactLastName": "", 
   *           "contactFirstName": ""  
   *         }
   *         </pre>
   */
  @GetMapping(path = "/api/customer/all", produces = MediaType.APPLICATION_JSON_VALUE)
  @JsonResponseIntercept(Module.CUSTOMER)
  public ResponseEntity<Serializable> getCustomers()
  {
    List<Customer> customers = customerService.findAllCustomers();
    return new ResponseEntity<>( (Serializable) customers, HttpStatus.OK );
  }

  /**
   * Busca el cliente por el número de cliente
   * 
   * @param customerNumber
   * @return
   */
  @GetMapping(path = "/api/customer/{customerNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
  @JsonResponseIntercept(Module.OFFICE)
  public ResponseEntity<Serializable> findCustomerByCustomerNumber(
      @PathVariable("customerNumber") Long customerNumber )
  {
    Customer customer = this.customerService.get( customerNumber );
    ResponseEntity<Serializable> response;
    if( customer != null )
    {
      response = new ResponseEntity<>( customer, HttpStatus.OK );
    }
    else
    {
      response = new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }
    return response;
  }

  /**
   * Crea un registro de clientes
   * 
   * @param office
   * @return
   */
  @PostMapping(path = "/api/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @JsonResponseIntercept(Module.OFFICE)
  public ResponseEntity<Serializable> create( @RequestBody Customer customer )
  {
    extractCustomer( customer );

    this.customerService.create( customer );

    return new ResponseEntity<>( customer, HttpStatus.CREATED );
  }

  private void extractCustomer( Customer customer )
  {
    customer.setCustomerName( Validator.adjustLength( customer.getCustomerName(), 50 ) );
    customer.setContactLastName( Validator.adjustLength( customer.getContactLastName(), 50 ) );
    customer.setContactFirstName( Validator.adjustLength( customer.getContactFirstName(), 50 ) );
    customer.setPhone( Validator.adjustLength( customer.getPhone(), 50 ) );
    customer.setAddressLine1( Validator.adjustLength( customer.getAddressLine1(), 50 ) );
    customer.setAddressLine2( Validator.adjustLength( customer.getAddressLine2(), 50 ) );
    customer.setCity( Validator.adjustLength( customer.getCity(), 50 ) );
    customer.setState( Validator.adjustLength( customer.getState(), 50 ) );
    customer.setPostalCode( Validator.adjustLength( customer.getPostalCode(), 15 ) );
    customer.setCountry( Validator.adjustLength( customer.getCountry(), 50 ) );
    customer.setCreditLimit( customer.getCreditLimit() );
    customer.setSalesRepresentative( customer.getSalesRepresentative() );
  }

  /**
   * Edita un registro de clientes
   * 
   * @param office
   * @param officeCode
   * @return
   */
  @PutMapping(path = "/api/customer/{customerNumber}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @JsonResponseIntercept(Module.OFFICE)
  public ResponseEntity<Serializable> edit( @RequestBody Customer customer,
      @PathVariable("customerNumber") Long customerNumber )
  {
    customer.setCustomerNumber( customerNumber );
    extractCustomer( customer );

    this.customerService.edit( customer );

    return new ResponseEntity<>( customer, HttpStatus.OK );
  }

  /**
   * Elimina un registro de oficinas
   * 
   * @param officeCode
   * @return
   */
  @DeleteMapping(path = "/api/customer/{customerNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
  @JsonResponseIntercept(Module.OFFICE)
  public ResponseEntity<Serializable> delete( @PathVariable("customerNumber") Long customerNumber )
  {
    this.customerService.delete( customerNumber );
    return new ResponseEntity<>( HttpStatus.OK );
  }
}
