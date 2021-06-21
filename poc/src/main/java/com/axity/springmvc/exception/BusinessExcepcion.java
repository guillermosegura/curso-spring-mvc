package com.axity.springmvc.exception;

/**
 * Excepción de negocio
 * 
 * @author guillermo.segura@axity.com
 */
public class BusinessExcepcion extends RuntimeException
{

  private static final long serialVersionUID = -7281539346624574897L;
  private BusinessExcepcionCode code;
  private String detailMessage;

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

  public String getDetailMessage()
  {
    return detailMessage;
  }

  public void setDetailMessage( String detailMessage )
  {
    this.detailMessage = detailMessage;
  }

}
