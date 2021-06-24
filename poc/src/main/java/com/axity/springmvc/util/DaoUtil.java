package com.axity.springmvc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DaoUtil
{
  private static final Logger LOG = LoggerFactory.getLogger( DaoUtil.class );

  public static Long safeLong( Object object )
  {
    Long value = null;
    if( object != null )
    {
      if( object instanceof Number )
      {
        value = ((Number) object).longValue();
      }
      else if( object instanceof String )
      {
        try
        {
          value = Long.parseLong( (String) object );
        }
        catch( NumberFormatException e )
        {
          LOG.error( e.getMessage() );
          LOG.error( "Dato: {} ", (String) object );
        }

      }
    }
    return value;
  }

  public static String safeString( Object object )
  {
    String value = null;
    if( object != null )
    {
      if( object instanceof String )
      {
        value = (String) object;
      }
      else
      {
        value = object.toString();
      }
    }
    return value;
  }
}
