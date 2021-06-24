package com.axity.springmvc.aop;

import java.io.Serializable;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.axity.springmvc.exception.BusinessExcepcion;
import com.axity.springmvc.to.GenericResponse;
import com.axity.springmvc.to.Header;

@Aspect
@Component
public class JsonResponseInterceptor implements HandlerInterceptor
{
  private static final Logger LOG = LoggerFactory.getLogger( JsonResponseInterceptor.class );

  @Around("execution (* com.axity.springmvc.controller.*.*(..))"
      + " and @annotation(com.axity.springmvc.aop.JsonResponseIntercept)")
  public Object interceptMethodAdvice( ProceedingJoinPoint pjp ) throws Throwable
  {
    Object result = null;
    try
    {

      result = pjp.proceed();
      if( result instanceof ResponseEntity )
      {
        ResponseEntity<?> responseEntity = (ResponseEntity<?>) result;
        responseEntity.getStatusCode();

        if( responseEntity.getStatusCode().equals( HttpStatus.OK )
            || responseEntity.getStatusCode().equals( HttpStatus.CREATED ) )
        {
          GenericResponse genericResponse = new GenericResponse();
          genericResponse.setBody( (Serializable) responseEntity.getBody() );
          Header header = new Header();
          header.setMessage( "OK" );
          header.setStatus( "000" );
          genericResponse.setHeader( header );
          result = new ResponseEntity<>( genericResponse, responseEntity.getStatusCode() );
        }
        else
        {
          GenericResponse genericResponse = new GenericResponse();
          genericResponse.setBody( (Serializable) responseEntity.getBody() );
          result = new ResponseEntity<>( genericResponse, responseEntity.getStatusCode() );
        }
      }
    }
    catch( BusinessExcepcion e )
    {
      LOG.error( e.getMessage(), e );

      GenericResponse genericResponse = new GenericResponse();
      Header header = new Header();
      header.setMessage( e.getMessage() );
      header.setStatus( e.getCode().getCode() );
      genericResponse.setHeader( header );
      result = new ResponseEntity<>( genericResponse, HttpStatus.INTERNAL_SERVER_ERROR );
    }
    catch( Exception e )
    {
      LOG.error( e.getMessage(), e );

      GenericResponse genericResponse = new GenericResponse();
      Header header = new Header();
      header.setMessage( "Error:" + e.getMessage() );
      header.setStatus( "001" );
      genericResponse.setHeader( header );
      result = new ResponseEntity<>( genericResponse, HttpStatus.INTERNAL_SERVER_ERROR );
    }
    return result;
  }

}
