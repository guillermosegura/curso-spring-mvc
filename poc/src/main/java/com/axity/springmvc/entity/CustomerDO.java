package com.axity.springmvc.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

/**
 * Entidad de la tabla customers
 * 
 * @author guillermo.segura@axity.com
 */
@Entity
@Table(name = "customers")
public class CustomerDO implements Serializable
{

  private static final long serialVersionUID = -3565538651566659414L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "customerNumber")
  @Expose
  private Long customerNumber;

  @Column(name = "customerName", length = 50, nullable = false)
  @Expose
  private String customerName;

  @Column(name = "contactLastName", length = 50, nullable = false)
  @Expose
  private String contactLastName;

  @Column(name = "contactFirstName", length = 50, nullable = false)
  @Expose
  private String contactFirstName;

  @Column(name = "phone", length = 50, nullable = false)
  @Expose
  private String phone;

  @Column(name = "addressLine1", length = 50, nullable = false)
  @Expose
  private String addressLine1;

  @Column(name = "addressLine2", length = 50, nullable = true)
  @Expose
  private String addressLine2;

  @Column(name = "city", length = 50, nullable = false)
  @Expose
  private String city;

  @Column(name = "state", length = 50, nullable = true)
  @Expose
  private String state;

  @Column(name = "postalCode", length = 15, nullable = true)
  @Expose
  private String postalCode;

  @Column(name = "country", length = 50, nullable = false)
  @Expose
  private String country;

  @ManyToOne(fetch = FetchType.EAGER, optional = true)
  @JoinColumn(name = "salesRepEmployeeNumber", referencedColumnName = "employeeNumber")
  @Expose
  private EmployeeDO salesRepEmployee;

  @Column(name = "creditLimit", precision = 10, scale = 2)
  @Expose
  private BigDecimal creditLimit;

  @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
  private List<PaymentDO> payments;

  /**
   * @return the customerNumber
   */
  public Long getCustomerNumber()
  {
    return customerNumber;
  }

  /**
   * @param customerNumber the customerNumber to set
   */
  public void setCustomerNumber( Long customerNumber )
  {
    this.customerNumber = customerNumber;
  }

  /**
   * @return the customerName
   */
  public String getCustomerName()
  {
    return customerName;
  }

  /**
   * @param customerName the customerName to set
   */
  public void setCustomerName( String customerName )
  {
    this.customerName = customerName;
  }

  /**
   * @return the contactLastName
   */
  public String getContactLastName()
  {
    return contactLastName;
  }

  /**
   * @param contactLastName the contactLastName to set
   */
  public void setContactLastName( String contactLastName )
  {
    this.contactLastName = contactLastName;
  }

  /**
   * @return the contactFirstName
   */
  public String getContactFirstName()
  {
    return contactFirstName;
  }

  /**
   * @param contactFirstName the contactFirstName to set
   */
  public void setContactFirstName( String contactFirstName )
  {
    this.contactFirstName = contactFirstName;
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
   * @return the salesRepEmployee
   */
  public EmployeeDO getSalesRepEmployee()
  {
    return salesRepEmployee;
  }

  /**
   * @param salesRepEmployee the salesRepEmployee to set
   */
  public void setSalesRepEmployee( EmployeeDO salesRepEmployee )
  {
    this.salesRepEmployee = salesRepEmployee;
  }

  /**
   * @return the creditLimit
   */
  public BigDecimal getCreditLimit()
  {
    return creditLimit;
  }

  /**
   * @param creditLimit the creditLimit to set
   */
  public void setCreditLimit( BigDecimal creditLimit )
  {
    this.creditLimit = creditLimit;
  }

  /**
   * @return the payments
   */
  public List<PaymentDO> getPayments()
  {
    return payments;
  }

  /**
   * @param payments the payments to set
   */
  public void setPayments( List<PaymentDO> payments )
  {
    this.payments = payments;
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
      CustomerDO that = (CustomerDO) object;

      isEquals = Objects.equals( this.customerNumber, that.customerNumber );
    }
    return isEquals;
  }

  @Override
  public int hashCode()
  {
    return Objects.hash( this.customerNumber );
  }

  @Override
  public String toString()
  {
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    return gson.toJson( this );
  }

}
