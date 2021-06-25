package com.axity.springmvc.to;

import java.math.BigDecimal;

public class Commission extends Payment
{
  private Long commissionId;
  private Long employeeNumber;
  private BigDecimal percent;
  private BigDecimal salesCommission;

  public Long getCommissionId()
  {
    return commissionId;
  }

  public void setCommissionId( Long commissionId )
  {
    this.commissionId = commissionId;
  }

  public Long getEmployeeNumber()
  {
    return employeeNumber;
  }

  public void setEmployeeNumber( Long employeeNumber )
  {
    this.employeeNumber = employeeNumber;
  }

  public BigDecimal getPercent()
  {
    return percent;
  }

  public void setPercent( BigDecimal percent )
  {
    this.percent = percent;
  }

  public BigDecimal getSalesCommission()
  {
    return salesCommission;
  }

  public void setSalesCommission( BigDecimal salesCommission )
  {
    this.salesCommission = salesCommission;
  }

}
