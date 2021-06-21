package com.axity.springmvc.to;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Header implements Serializable
{

  private static final long serialVersionUID = 4483038131814542859L;
  private String status;
  private String message;
  public String getStatus()
  {
    return status;
  }

  public void setStatus( String status )
  {
    this.status = status;
  }

  public String getMessage()
  {
    return message;
  }

  public void setMessage( String message )
  {
    this.message = message;
  }

}
