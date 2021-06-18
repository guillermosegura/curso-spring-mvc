package com.axity.springmvc.exception;

/**
 * Excepción de negocio
 * 
 * @author guillermo.segura@axity.com
 */
public class BusinessExcepcion extends RuntimeException
{

  private static final long serialVersionUID = 8389119791244900216L;
  private BusinessExcepcionCode code;

  public BusinessExcepcion()
  {
    super();
    this.code = BusinessExcepcionCode.UNKOWN_EXCEPTION;
  }

  public BusinessExcepcion( String message )
  {
    super( message );
    this.code = BusinessExcepcionCode.UNKOWN_EXCEPTION;
  }

  public BusinessExcepcion( String message, Throwable cause )
  {
    super( message, cause );
    this.code = BusinessExcepcionCode.UNKOWN_EXCEPTION;
  }

  public BusinessExcepcion( Throwable cause )
  {
    super( cause );
    this.code = BusinessExcepcionCode.UNKOWN_EXCEPTION;
  }

  public BusinessExcepcionCode getCode()
  {
    return code;
  }

  public void setCode( BusinessExcepcionCode code )
  {
    this.code = code;
  }

}
