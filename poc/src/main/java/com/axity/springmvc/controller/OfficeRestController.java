package com.axity.springmvc.controller;

import java.io.Serializable;

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
import com.axity.springmvc.services.OfficeService;
import com.axity.springmvc.to.Office;
import com.axity.springmvc.to.PaginatedResponse;
import com.axity.springmvc.util.Validator;

/**
 * Controlador base de oficinas
 * 
 * @author guillermo.segura@axity.com
 */
@RestController
public class OfficeRestController
{

  @Autowired
  private OfficeService officeService;

  /**
   * Atiende la petición Rest de consulta de oficinas
   * 
   * @return
   */
  @GetMapping(path = "/api/office", produces = MediaType.APPLICATION_JSON_VALUE)
  @JsonResponseIntercept(Module.OFFICE)
  public ResponseEntity<Serializable> getOffices( @RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "20") int pageSize )
  {
    PaginatedResponse<Office> offices = officeService.findAllPaginated( page, pageSize );
    return new ResponseEntity<>( offices, HttpStatus.OK );
  }

  @GetMapping(path = "/api/office/{officeCode}", produces = MediaType.APPLICATION_JSON_VALUE)
  @JsonResponseIntercept(Module.OFFICE)
  public ResponseEntity<Serializable> findOfficeById( @PathVariable("officeCode") String officeCode )
  {
    Office office = this.officeService.get( officeCode );
    ResponseEntity<Serializable> response;
    if( office != null )
    {
      response = new ResponseEntity<>( office, HttpStatus.OK );
    }
    else
    {
      response = new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }
    return response;
  }

  /**
   * Crea un registro de oficinas
   * 
   * @param office
   * @return
   */
  @PostMapping(path = "/api/office", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @JsonResponseIntercept(Module.OFFICE)
  public ResponseEntity<Serializable> create( @RequestBody Office office )
  {
    office.setOfficeCode( Validator.adjustLength( office.getOfficeCode(), 10 ) );
    office.setCity( Validator.adjustLength( office.getCity(), 50 ) );
    office.setPhone( Validator.adjustLength( office.getPhone(), 50 ) );
    office.setAddressLine1( Validator.adjustLength( office.getAddressLine1(), 50 ) );
    office.setAddressLine2( Validator.adjustLength( office.getAddressLine2(), 50 ) );
    office.setState( Validator.adjustLength( office.getState(), 50 ) );
    office.setCountry( Validator.adjustLength( office.getCountry(), 50 ) );
    office.setPostalCode( Validator.adjustLength( office.getPostalCode(), 15 ) );
    office.setTerritory( Validator.adjustLength( office.getTerritory(), 10 ) );
    this.officeService.create( office );

    return new ResponseEntity<>( office, HttpStatus.CREATED );
  }

  /**
   * Edita un registro de oficinas
   * 
   * @param office
   * @param officeCode
   * @return
   */
  @PutMapping(value = "/api/office/{officeCode}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @JsonResponseIntercept(Module.OFFICE)
  public ResponseEntity<Serializable> edit( @RequestBody Office office, @PathVariable("officeCode") String officeCode )
  {
    office.setOfficeCode( Validator.adjustLength( officeCode, 10 ) );
    office.setCity( Validator.adjustLength( office.getCity(), 50 ) );
    office.setPhone( Validator.adjustLength( office.getPhone(), 50 ) );
    office.setAddressLine1( Validator.adjustLength( office.getAddressLine1(), 50 ) );
    office.setAddressLine2( Validator.adjustLength( office.getAddressLine2(), 50 ) );
    office.setState( Validator.adjustLength( office.getState(), 50 ) );
    office.setCountry( Validator.adjustLength( office.getCountry(), 50 ) );
    office.setPostalCode( Validator.adjustLength( office.getPostalCode(), 15 ) );
    office.setTerritory( Validator.adjustLength( office.getTerritory(), 10 ) );
    this.officeService.edit( office );

    return new ResponseEntity<>( office, HttpStatus.OK );
  }

  /**
   * Elimina un registro de oficinas
   * 
   * @param officeCode
   * @return
   */
  @DeleteMapping(value = "/api/office/{officeCode}", produces = MediaType.APPLICATION_JSON_VALUE)
  @JsonResponseIntercept(Module.OFFICE)
  public ResponseEntity<Serializable> delete( @PathVariable("officeCode") String officeCode )
  {
    this.officeService.delete( officeCode );
    return new ResponseEntity<>( HttpStatus.OK );
  }
}
