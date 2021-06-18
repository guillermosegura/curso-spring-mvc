package com.axity.springmvc.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.axity.springmvc.aop.JsonResponseIntercept;
import com.axity.springmvc.services.OfficeService;
import com.axity.springmvc.to.Office;

/**
 * Controlador base de oficinas
 * 
 * @author guillermo.segura@axity.com
 */
@Controller
public class OfficeController
{
  private static final Logger LOG = LoggerFactory.getLogger( OfficeController.class );

  @Autowired
  private OfficeService officeService;

  @PostConstruct
  public void init()
  {
    LOG.info( "Creado OfficeController" );
  }

  /**
   * Atiende la petición MVC de consulta de oficinas
   * 
   * @return
   */
  @RequestMapping("/office")
  @GetMapping
  public ModelAndView offices()
  {
    ModelAndView mv = new ModelAndView();
    mv.setViewName( "office" );
    mv.getModel().put( "data", "Welcome!!!" );

    List<Office> offices = officeService.findAll();
    offices.stream().forEach( o -> LOG.info( "{}", o ) );
    mv.getModel().put( "offices", offices );

    return mv;
  }

}
