package com.axity.springmvc.to;

import java.io.Serializable;
import java.util.List;

/**
 * Objeto de transferencia para las consultas paginadas
 * 
 * @author guillermo.segura@axity.com
 * @param <T>
 */
public class PaginatedResponse<T extends Serializable> implements Serializable
{

  private static final long serialVersionUID = -6578796470167164711L;
  private int page;
  private int pageSize;
  private int pages;
  private List<T> response;
  public int getPage()
  {
    return page;
  }

  public void setPage( int page )
  {
    this.page = page;
  }

  public int getPageSize()
  {
    return pageSize;
  }

  public void setPageSize( int pageSize )
  {
    this.pageSize = pageSize;
  }

  public int getPages()
  {
    return pages;
  }

  public void setPages( int pages )
  {
    this.pages = pages;
  }

  public List<T> getResponse()
  {
    return response;
  }

  public void setResponse( List<T> response )
  {
    this.response = response;
  }

}
