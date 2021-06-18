package com.axity.springmvc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axity.springmvc.aop.JsonResponseIntercept;
import com.axity.springmvc.exception.Module;
import com.axity.springmvc.services.OfficeService;
import com.axity.springmvc.to.Office;

/**
 * Controlador base de oficinas
 * 
 * @author guillermo.segura@axity.com
 */
@RestController
public class OfficeRestController
{
  private static final Logger LOG = LoggerFactory.getLogger( OfficeRestController.class );

  @Autowired
  private OfficeService officeService;

  /**
   * Atiende la petición Rest de consulta de oficinas
   * 
   * @return
   */
  @RequestMapping("/api/office")
  @GetMapping(produces = "application/json")
  @JsonResponseIntercept(Module.OFFICE)
  public ResponseEntity<?> getOffices()
  {
    List<Office> offices = officeService.findAll();
    ResponseEntity<?> response = new ResponseEntity<>( offices, HttpStatus.OK );
    return response;
  }

  @RequestMapping("/api/office/{officeCode}")
  @GetMapping(produces = "application/json")
  @JsonResponseIntercept(Module.OFFICE)
  public ResponseEntity<?> findOfficeById( @PathVariable("officeCode") String officeCode )
  {
    Office office = this.officeService.get( officeCode );
    ResponseEntity<?> response;
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
}
