package com.axity.springmvc.to;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Payment
{
  protected Long customerNumber;
  protected String checkNumber;
  protected LocalDate date;
  protected BigDecimal amount;
  
  public Long getCustomerNumber()
  {
    return customerNumber;
  }

  public void setCustomerNumber( Long customerNumber )
  {
    this.customerNumber = customerNumber;
  }

  public String getCheckNumber()
  {
    return checkNumber;
  }

  public void setCheckNumber( String checkNumber )
  {
    this.checkNumber = checkNumber;
  }

  public LocalDate getDate()
  {
    return date;
  }

  public void setDate( LocalDate date )
  {
    this.date = date;
  }

  public BigDecimal getAmount()
  {
    return amount;
  }

  public void setAmount( BigDecimal amount )
  {
    this.amount = amount;
  }

}
