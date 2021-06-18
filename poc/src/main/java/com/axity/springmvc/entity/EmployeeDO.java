package com.axity.springmvc.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entidad de la tabla employees
 * 
 * @author guillermo.segura@axity.com
 */
@Entity
@Table(name = "employees")
public class EmployeeDO implements Serializable
{

  private static final long serialVersionUID = 8294455790159202317L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "employeeNumber")
  private Long employeeNumber;

  @Column(name = "lastName")
  private String lastName;

  @Column(name = "firstName")
  private String firstName;

  @Column(name = "extension")
  private String extension;

  @Column(name = "email")
  private String email;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "officeCode", referencedColumnName = "officeCode")
  private OfficeDO office;

  @ManyToOne(fetch = FetchType.EAGER, optional = true)
  @JoinColumn(name = "reportsTo", referencedColumnName = "employeeNumber")
  private EmployeeDO reportsTo;

  @OneToMany(mappedBy = "reportsTo", fetch = FetchType.LAZY)
  private List<EmployeeDO> employees;

  @Column(name = "jobTitle")
  private String jobTitle;

  @OneToMany(mappedBy = "salesRepEmployee", fetch = FetchType.LAZY)
  private List<CustomerDO> customers;

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
   * @return the office
   */
  public OfficeDO getOffice()
  {
    return office;
  }

  /**
   * @param office the office to set
   */
  public void setOffice( OfficeDO office )
  {
    this.office = office;
  }

  /**
   * @return the reportsTo
   */
  public EmployeeDO getReportsTo()
  {
    return reportsTo;
  }

  /**
   * @param reportsTo the reportsTo to set
   */
  public void setReportsTo( EmployeeDO reportsTo )
  {
    this.reportsTo = reportsTo;
  }

  /**
   * @return the employees
   */
  public List<EmployeeDO> getEmployees()
  {
    return employees;
  }

  /**
   * @param employees the employees to set
   */
  public void setEmployees( List<EmployeeDO> employees )
  {
    this.employees = employees;
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

  /**
   * @return the customers
   */
  public List<CustomerDO> getCustomers()
  {
    return customers;
  }

  /**
   * @param customers the customers to set
   */
  public void setCustomers( List<CustomerDO> customers )
  {
    this.customers = customers;
  }

}
