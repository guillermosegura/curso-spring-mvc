package com.axity.springmvc.to;

import java.io.Serializable;

public class GenericResponse implements Serializable
{

  private static final long serialVersionUID = 2129066540908564159L;
  private Header header;
  private Object body;
  public Header getHeader()
  {
    return header;
  }

  public void setHeader( Header header )
  {
    this.header = header;
  }

  public Object getBody()
  {
    return body;
  }

  public void setBody( Object body )
  {
    this.body = body;
  }

}
