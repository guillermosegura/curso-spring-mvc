package com.axity.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InitController
{
  private static final Logger LOG = LoggerFactory.getLogger( InitController.class );

  @RequestMapping("/")
  @GetMapping
  public ModelAndView index()
  {
    ModelAndView mv = new ModelAndView();
    mv.setViewName( "index" );
    return mv;
  }
}
