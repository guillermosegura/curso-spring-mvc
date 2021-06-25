package com.axity.springmvc.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axity.springmvc.dao.EmployeeDAO;
import com.axity.springmvc.entity.EmployeeDO;
import com.axity.springmvc.services.EmployeeService;
import com.axity.springmvc.services.OfficeService;
import com.axity.springmvc.to.Commission;
import com.axity.springmvc.to.Employee;
import com.axity.springmvc.to.EmployeeReport;
import com.axity.springmvc.to.Office;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService
{

  @Autowired
  private EmployeeDAO employeeDAO;

  @Autowired
  private OfficeService officeService;

  @Override
  public List<EmployeeReport> findAllEmployees()
  {
    return findAllEmployees( 0, 20 );
  }

  @Override
  public List<EmployeeReport> findAllEmployees( int page, int pageSize )
  {
    List<EmployeeDO> employees = this.employeeDAO.findAll( page, pageSize );

    List<EmployeeReport> list = new ArrayList<>();

    employees.stream().forEach( e -> list.add( employeeDOTransformer( e ) ) );

    return list;
  }

  private EmployeeReport employeeDOTransformer( EmployeeDO e )
  {
    EmployeeReport employeeReport = new EmployeeReport();
    BeanUtils.copyProperties( e, employeeReport );

    if( e.getReportsTo() != null )
    {
      Employee reports = new Employee();

      reports.setEmployeeNumber( e.getReportsTo().getEmployeeNumber() );
      reports.setFirstName( e.getReportsTo().getFirstName() );
      reports.setLastName( e.getReportsTo().getLastName() );
      employeeReport.setReports( reports );
    }

    Office office = new Office();
    office.setOfficeCode( e.getOffice().getOfficeCode() );
    office.setCity( e.getOffice().getCity() );
    office.setCountry( e.getOffice().getCountry() );
    employeeReport.setOffice( office );
    return employeeReport;
  }

  @Override
  public Long saveCommision( Commission commission )
  {
    return null;
  }

}
