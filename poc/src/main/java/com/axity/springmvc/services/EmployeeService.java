package com.axity.springmvc.services;

import java.util.List;

import com.axity.springmvc.to.EmployeeReport;

public interface EmployeeService
{
  List<EmployeeReport> findAllEmployees();
  List<EmployeeReport> findAllEmployees(int page, int pageSize);
}
