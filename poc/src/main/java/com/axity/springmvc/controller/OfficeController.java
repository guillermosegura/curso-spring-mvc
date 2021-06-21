package com.axity.springmvc.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;

import com.axity.springmvc.exception.BusinessExcepcion;
import com.axity.springmvc.exception.BusinessExcepcionCode;
import com.axity.springmvc.services.OfficeService;
import com.axity.springmvc.to.Office;
import com.axity.springmvc.util.Validator;

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
  public ModelAndView offices( @RequestParam(required = false, defaultValue = "html") String view )
  {
    ModelAndView mv = new ModelAndView();

    mv = new ModelAndView();
    if( view != null && view.equalsIgnoreCase( "xml" ) )
    {
      mv = new ModelAndView( new MappingJackson2XmlView() );
    }
    else if( view != null && view.equalsIgnoreCase( "json" ) )
    {
      mv = new ModelAndView( new MappingJackson2JsonView() );
    }
    else if( view != null && view.equalsIgnoreCase( "html" ) )
    {
      mv = new ModelAndView();
      mv.setViewName( "office" );
      mv.getModel().put( "data", "Welcome!!!" );
    }
    else
    {
      mv = new ModelAndView();
      mv.setViewName( "office" );
      mv.getModel().put( "data", "Welcome!!!" );
    }

    List<Office> offices = officeService.findAll();
    offices.stream().forEach( o -> LOG.info( "{}", o ) );
    mv.getModel().put( "offices", offices );

    return mv;
  }

  @RequestMapping(value = "/office_add", method = RequestMethod.GET)
  public ModelAndView officesAddView()
  {
    ModelAndView mv = new ModelAndView();
    mv.setViewName( "office_add" );
    return mv;
  }

  @RequestMapping(value = "/office_add", method = RequestMethod.POST)
  public ModelAndView officesAddPost( @RequestParam MultiValueMap<String, String> body )
  {
    ModelAndView mv = new ModelAndView();

    Office office = extractOffice( body );

    try
    {
      this.officeService.create( office );
      mv.setViewName( "redirect:/office" );
    }
    catch( BusinessExcepcion e )
    {
      mv.setViewName( "office_add" );
      mv.getModelMap().put( "error", true );
      mv.getModelMap().put( "errorMessage", e.getMessage() );
      mv.getModelMap().put( "errorCode", e.getCode() );
    }

    return mv;
  }

  @RequestMapping(value = "/office_edit", method = RequestMethod.GET)
  public ModelAndView officesEditView( @RequestParam String officeCode )
  {
    ModelAndView mv = getOfficeModelView( officeCode );

    mv.setViewName( "office_edit" );
    return mv;
  }

  @RequestMapping(value = "/office_view", method = RequestMethod.GET)
  public ModelAndView officesView( @RequestParam String officeCode, @RequestParam String view )
  {
    ModelAndView mv = null;
    if( view != null && view.equalsIgnoreCase( "xml" ) )
    {
      mv = new ModelAndView( new MappingJackson2XmlView() );
      Office office = this.officeService.get( officeCode );
      mv.getModelMap().put( "office", office );
    }
    else if( view != null && view.equalsIgnoreCase( "json" ) )
    {
      mv = new ModelAndView( new MappingJackson2JsonView() );
      Office office = this.officeService.get( officeCode );
      mv.getModelMap().put( "office", office );
    }
    else
    {
      mv = new ModelAndView();
      mv.setViewName( "redirect:/office" );
    }

    return mv;
  }

  @RequestMapping(value = "/office_edit", method = RequestMethod.POST)
  public ModelAndView officesEditPost( @RequestParam MultiValueMap<String, String> body )
  {
    ModelAndView mv = new ModelAndView();

    Office office = extractOffice( body );

    try
    {
      this.officeService.edit( office );
      mv.setViewName( "redirect:/office" );
    }
    catch( BusinessExcepcion e )
    {
      mv.setViewName( "office_edit" );

      mv.getModelMap().put( "error", true );
      mv.getModelMap().put( "errorMessage", e.getMessage() );
      mv.getModelMap().put( "errorCode", e.getCode() );
    }

    return mv;
  }

  @RequestMapping(value = "/office_delete", method = RequestMethod.GET)
  public ModelAndView officesDeleteView( @RequestParam String officeCode )
  {
    ModelAndView mv = getOfficeModelView( officeCode );

    mv.setViewName( "office_delete" );
    return mv;
  }

  @RequestMapping(value = "/office_delete", method = RequestMethod.POST)
  public ModelAndView officesDeletePost( @RequestParam MultiValueMap<String, String> body )
  {
    ModelAndView mv = new ModelAndView();

    Office office = extractOffice( body );

    try
    {
      this.officeService.delete( office.getOfficeCode() );
      mv.setViewName( "redirect:/office" );
    }
    catch( BusinessExcepcion e )
    {
      mv.setViewName( "office_delete" );

      mv.getModelMap().put( "error", true );
      mv.getModelMap().put( "errorMessage", e.getMessage() );
      mv.getModelMap().put( "errorCode", e.getCode() );
      mv.getModelMap().put( "office", office );
    }

    return mv;
  }

  private ModelAndView getOfficeModelView( String officeCode )
  {
    ModelAndView mv = new ModelAndView();

    Office office = this.officeService.get( officeCode );
    if( office != null )
    {
      mv.getModelMap().put( "office", office );
    }
    else
    {
      mv.getModelMap().put( "error", true );
      mv.getModelMap().put( "errorMessage", "No existe la oficina" );
      mv.getModelMap().put( "errorCode", BusinessExcepcionCode.INVALID_DATA );
    }
    return mv;
  }

  private Office extractOffice( MultiValueMap<String, String> body )
  {
    Office office = new Office();
    office.setOfficeCode( Validator.adjustLength( body.getFirst( "officeCode" ), 10 ) );
    office.setCity( Validator.adjustLength( body.getFirst( "city" ), 50 ) );
    office.setPhone( Validator.adjustLength( body.getFirst( "phone" ), 50 ) );
    office.setAddressLine1( Validator.adjustLength( body.getFirst( "addressLine1" ), 50 ) );
    office.setAddressLine2( Validator.adjustLength( body.getFirst( "addressLine2" ), 50 ) );
    office.setState( Validator.adjustLength( body.getFirst( "state" ), 50 ) );
    office.setCountry( Validator.adjustLength( body.getFirst( "country" ), 50 ) );
    office.setPostalCode( Validator.adjustLength( body.getFirst( "postalCode" ), 15 ) );
    office.setTerritory( Validator.adjustLength( body.getFirst( "territory" ), 10 ) );
    return office;
  }

}
