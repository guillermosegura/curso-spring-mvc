package com.axity.springmvc.services;

import java.math.BigDecimal;
import java.util.List;

import com.axity.springmvc.to.Commission;
import com.axity.springmvc.to.EmployeeReport;

public interface EmployeeService
{
  List<EmployeeReport> findAllEmployees();

  List<EmployeeReport> findAllEmployees( int page, int pageSize );

  /**
   * Guarda la comision al representante de acuerdo al pago
   * 
   * @param commission
   */
  Long saveCommision( Commission commission );

}
