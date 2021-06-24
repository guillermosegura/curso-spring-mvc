package com.axity.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InitController
{
  private static final Logger LOG = LoggerFactory.getLogger( InitController.class );

  @GetMapping(path = "/")
  public ModelAndView index()
  {
    LOG.info( "index" );
    ModelAndView mv = new ModelAndView();
    mv.setViewName( "index" );
    mv.getModel().put( "hello", "Hello world!!!" );
    return mv;
  }
}
