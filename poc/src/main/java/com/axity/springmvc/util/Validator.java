package com.axity.springmvc.util;

import com.axity.springmvc.exception.BusinessExcepcion;
import com.axity.springmvc.exception.BusinessExcepcionCode;

public final class Validator
{
  public static void validateNotNull( Object object, String message )
  {
    if( object == null )
    {
      BusinessExcepcion businessExcepcion = new BusinessExcepcion( message );
      businessExcepcion.setCode( BusinessExcepcionCode.INVALID_DATA );
      throw businessExcepcion;
    }
  }

  public static String adjustLength( String data, int maxlength )
  {
    String adjusted = data;
    if( data != null && data.length() > maxlength )
    {
      adjusted = data.substring( 0, maxlength );
    }
    return adjusted;
  }
}
