package com.axity.springmvc.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

/**
 * Llave compuesta de la tabla payments
 * 
 * @author guillermo.segura@axity.com
 */
@Embeddable
public class PaymentId implements Serializable
{

  private static final long serialVersionUID = 8207197912982355628L;

  @Column(name = "customerNumber", nullable = false)
  @Expose
  private Long customerNumber;

  @Column(name = "checkNumber", nullable = false)
  @Expose
  private String checkNumber;

  /**
   * Constructor default
   */
  public PaymentId()
  {
  }

  /**
   * Constructor por argumentos
   * 
   * @param customerNumber
   * @param checkNumber
   */
  public PaymentId( Long customerNumber, String checkNumber )
  {
    this.customerNumber = customerNumber;
    this.checkNumber = checkNumber;
  }

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
   * @return the checkNumber
   */
  public String getCheckNumber()
  {
    return checkNumber;
  }

  /**
   * @param checkNumber the checkNumber to set
   */
  public void setCheckNumber( String checkNumber )
  {
    this.checkNumber = checkNumber;
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
      PaymentId that = (PaymentId) object;

      isEquals = Objects.equals( this.customerNumber, that.customerNumber );
      isEquals = isEquals && Objects.equals( this.checkNumber, that.checkNumber );
    }
    return isEquals;
  }

  @Override
  public int hashCode()
  {
    return Objects.hash( this.customerNumber, this.checkNumber );
  }

  @Override
  public String toString()
  {
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    return gson.toJson( this );
  }

}
