package com.axity.springmvc.to;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Objeto de transferencia de Empleado
 * 
 * @author guillermo.segura@axity.com
 */
@JsonInclude(Include.NON_NULL)
public class Employee implements Serializable
{

  private static final long serialVersionUID = -226371449711827352L;
  protected Long employeeNumber;
  protected String lastName;
  protected String firstName;
  protected String extension;
  protected String email;
  protected String officeCode;
  protected Long reportsTo;
  protected String jobTitle;

  /**
   * @return the employeeNumber
   */
  public Long getEmployeeNumber()
  {
    return employeeNumber;
  }

  /**
   * @param employeeNumber the employeeNumber to set
   */
  public void setEmployeeNumber( Long employeeNumber )
  {
    this.employeeNumber = employeeNumber;
  }

  /**
   * @return the lastName
   */
  public String getLastName()
  {
    return lastName;
  }

  /**
   * @param lastName the lastName to set
   */
  public void setLastName( String lastName )
  {
    this.lastName = lastName;
  }

  /**
   * @return the firstName
   */
  public String getFirstName()
  {
    return firstName;
  }

  /**
   * @param firstName the firstName to set
   */
  public void setFirstName( String firstName )
  {
    this.firstName = firstName;
  }

  /**
   * @return the extension
   */
  public String getExtension()
  {
    return extension;
  }

  /**
   * @param extension the extension to set
   */
  public void setExtension( String extension )
  {
    this.extension = extension;
  }

  /**
   * @return the email
   */
  public String getEmail()
  {
    return email;
  }

  /**
   * @param email the email to set
   */
  public void setEmail( String email )
  {
    this.email = email;
  }

  /**
   * @return the officeCode
   */
  public String getOfficeCode()
  {
    return officeCode;
  }

  /**
   * @param officeCode the officeCode to set
   */
  public void setOfficeCode( String officeCode )
  {
    this.officeCode = officeCode;
  }

  /**
   * @return the reportsTo
   */
  public Long getReportsTo()
  {
    return reportsTo;
  }

  /**
   * @param reportsTo the reportsTo to set
   */
  public void setReportsTo( Long reportsTo )
  {
    this.reportsTo = reportsTo;
  }

  /**
   * @return the jobTitle
   */
  public String getJobTitle()
  {
    return jobTitle;
  }

  /**
   * @param jobTitle the jobTitle to set
   */
  public void setJobTitle( String jobTitle )
  {
    this.jobTitle = jobTitle;
  }

}
