package com.axity.springmvc.to;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class EmployeeReport extends Employee implements Serializable
{

  private static final long serialVersionUID = -6842389286748870692L;

  private Employee reports;

  private Office office;

  public Employee getReports()
  {
    return reports;
  }

  public void setReports( Employee reports )
  {
    this.reports = reports;
  }

  public Office getOffice()
  {
    return office;
  }

  public void setOffice( Office office )
  {
    this.office = office;
  }

}
