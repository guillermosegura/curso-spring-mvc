package com.axity.springmvc.aop;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.axity.springmvc.exception.Module;

/**
 * Anotación para interceptar métodos de controladores que regresan un objeto JSON
 * 
 * @author guillermo.segura@axity.com
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface JsonResponseIntercept
{

  Module value();
}
