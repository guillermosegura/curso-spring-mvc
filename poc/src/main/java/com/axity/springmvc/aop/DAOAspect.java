package com.axity.springmvc.aop;

import javax.persistence.PersistenceException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.axity.springmvc.exception.BusinessExcepcion;
import com.axity.springmvc.exception.BusinessExcepcionCode;

/**
 * Aspecto de la capa DAO
 * 
 * @author guillermo.segura@axity.com
 */
@Aspect
@Component
public class DAOAspect
{
  private static final Logger LOG = LoggerFactory.getLogger( DAOAspect.class );

  @Around("execution (* com.axity.springmvc.dao.*.*(..))")
  public Object interceptMethodAdvice( ProceedingJoinPoint pjp ) throws Throwable
  {
    Object result = null;
    try
    {
      result = pjp.proceed();
    }
    catch( PersistenceException e )
    {
      String detailMessage = getCause( e );
      LOG.error( e.getMessage(), e );
      LOG.error( detailMessage );
      BusinessExcepcion businessExcepcion = new BusinessExcepcion( "Ocurri\u00F3 un error de persistencia ", e );
      businessExcepcion.setDetailMessage( detailMessage );
      businessExcepcion.setCode( BusinessExcepcionCode.DB_INTEGRITY );
      throw businessExcepcion;
    }
    return result;
  }

  private String getCause( Throwable t )
  {
    String message;
    if( t.getCause() != null )
    {
      message = getCause( t.getCause() );
    }
    else
    {
      message = t.getMessage();
    }

    return message;
  }

}
