package com.axity.springmvc.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

/**
 * Entidad de la tabla offices
 * 
 * @author guillermo.segura@axity.com
 */
@Entity
@Table(name = "offices")
public class OfficeDO implements Serializable
{
  private static final long serialVersionUID = 9083622353986465393L;

  @Id
  @Column(name = "officeCode", length = 10)
  @Expose
  private String officeCode;

  @Column(name = "city", nullable = false, length = 50)
  @Expose
  private String city;

  @Column(name = "phone", nullable = false, length = 50)
  @Expose
  private String phone;

  @Column(name = "addressLine1", nullable = false, length = 50)
  @Expose
  private String addressLine1;

  @Column(name = "addressLine2", nullable = true, length = 50)
  @Expose
  private String addressLine2;

  @Column(name = "state", nullable = true, length = 50)
  @Expose
  private String state;

  @Column(name = "country", nullable = false, length = 50)
  @Expose
  private String country;

  @Column(name = "postalCode", nullable = false, length = 15)
  @Expose
  private String postalCode;

  @Column(name = "territory", nullable = false, length = 10)
  @Expose
  private String territory;

  @OneToMany(mappedBy = "office", fetch = FetchType.LAZY)
  private List<EmployeeDO> employees;

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
   * @return the city
   */
  public String getCity()
  {
    return city;
  }

  /**
   * @param city the city to set
   */
  public void setCity( String city )
  {
    this.city = city;
  }

  /**
   * @return the phone
   */
  public String getPhone()
  {
    return phone;
  }

  /**
   * @param phone the phone to set
   */
  public void setPhone( String phone )
  {
    this.phone = phone;
  }

  /**
   * @return the addressLine1
   */
  public String getAddressLine1()
  {
    return addressLine1;
  }

  /**
   * @param addressLine1 the addressLine1 to set
   */
  public void setAddressLine1( String addressLine1 )
  {
    this.addressLine1 = addressLine1;
  }

  /**
   * @return the addressLine2
   */
  public String getAddressLine2()
  {
    return addressLine2;
  }

  /**
   * @param addressLine2 the addressLine2 to set
   */
  public void setAddressLine2( String addressLine2 )
  {
    this.addressLine2 = addressLine2;
  }

  /**
   * @return the state
   */
  public String getState()
  {
    return state;
  }

  /**
   * @param state the state to set
   */
  public void setState( String state )
  {
    this.state = state;
  }

  /**
   * @return the country
   */
  public String getCountry()
  {
    return country;
  }

  /**
   * @param country the country to set
   */
  public void setCountry( String country )
  {
    this.country = country;
  }

  /**
   * @return the postalCode
   */
  public String getPostalCode()
  {
    return postalCode;
  }

  /**
   * @param postalCode the postalCode to set
   */
  public void setPostalCode( String postalCode )
  {
    this.postalCode = postalCode;
  }

  /**
   * @return the territory
   */
  public String getTerritory()
  {
    return territory;
  }

  /**
   * @param territory the territory to set
   */
  public void setTerritory( String territory )
  {
    this.territory = territory;
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

  @Override
  public boolean equals( Object object )
  {
    boolean isEquals = false;
    if( this == object )
    {
      isEquals = true;
    }
    else if( object != null && object.getClass().equals( this.getClass() ) )
    {
      OfficeDO that = (OfficeDO) object;

      isEquals = Objects.equals( this.officeCode, that.officeCode );
    }
    return isEquals;
  }

  @Override
  public int hashCode()
  {
    return Objects.hash( this.officeCode );
  }
  
  @Override
  public String toString()
  {
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    return gson.toJson( this );
  }
}
