package com.axity.springmvc.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

/**
 * Entidad de la tabla payments
 * 
 * @author guillermo.segura@axity.com
 */
@Entity
@Table(name = "payments")
public class PaymentDO implements Serializable
{

  private static final long serialVersionUID = 2263012586989817489L;

  @EmbeddedId
  @Expose
  private PaymentId id;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "customerNumber", referencedColumnName = "customerNumber", 
    insertable = false, updatable = false)
  private CustomerDO customer;

  @Column(name = "paymentDate", nullable = false)
  @Temporal(TemporalType.DATE)
  @Expose
  private Date paymentDate;

  @Column(name = "amount", precision = 10, scale = 2)
  @Expose
  private BigDecimal amount;

  /**
   * @return the id
   */
  public PaymentId getId()
  {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId( PaymentId id )
  {
    this.id = id;
  }

  /**
   * @return the paymentDate
   */
  public Date getPaymentDate()
  {
    return paymentDate;
  }

  /**
   * @param paymentDate the paymentDate to set
   */
  public void setPaymentDate( Date paymentDate )
  {
    this.paymentDate = paymentDate;
  }

  /**
   * @return the customer
   */
  public CustomerDO getCustomer()
  {
    return customer;
  }

  /**
   * @param customer the customer to set
   */
  public void setCustomer( CustomerDO customer )
  {
    this.customer = customer;
  }

  /**
   * @return the amount
   */
  public BigDecimal getAmount()
  {
    return amount;
  }

  /**
   * @param amount the amount to set
   */
  public void setAmount( BigDecimal amount )
  {
    this.amount = amount;
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
      PaymentDO that = (PaymentDO) object;

      isEquals = Objects.equals( this.id, that.id );
    }
    return isEquals;
  }

  @Override
  public int hashCode()
  {
    return Objects.hash( this.id );
  }
  
  @Override
  public String toString()
  {
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    return gson.toJson( this );
  }

}
