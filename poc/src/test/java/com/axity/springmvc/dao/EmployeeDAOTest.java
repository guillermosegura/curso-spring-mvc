package com.axity.springmvc.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.axity.springmvc.entity.EmployeeDO;

@RunWith(SpringRunner.class)
@ContextConfiguration({ "classpath:/beans.xml" })
@Transactional
public class EmployeeDAOTest
{
  private static final Logger LOG = LoggerFactory.getLogger( EmployeeDAOTest.class );

  @Autowired
  private EmployeeDAO employeeDAO;

  @Autowired
  private OfficeDAO officeDAO;

  @Test
  public void testFindAll()
  {
    List<EmployeeDO> employees = employeeDAO.findAll();
    Assert.assertNotNull( employees );
    Assert.assertFalse( employees.isEmpty() );
    for( EmployeeDO employee : employees )
    {
      log( employee );
    }
  }

  @Test
  public void testFindByOffice()
  {
    List<EmployeeDO> employees = employeeDAO.findByOffice( "1" );
    Assert.assertNotNull( employees );
    Assert.assertFalse( employees.isEmpty() );
    for( EmployeeDO employee : employees )
    {
      log( employee );
    }
  }

  @Test
  public void testFindBySuperior()
  {
    List<EmployeeDO> employees = employeeDAO.findBySuperior( 1088L );
    Assert.assertNotNull( employees );
    Assert.assertFalse( employees.isEmpty() );
    for( EmployeeDO employee : employees )
    {
      log( employee );
    }
  }

  @Test
  public void testFindByLastName()
  {
    List<EmployeeDO> employees = employeeDAO.findByLastName( "Pat" );
    Assert.assertNotNull( employees );
    Assert.assertFalse( employees.isEmpty() );
    for( EmployeeDO employee : employees )
    {
      log( employee );
    }
  }

  @Test
  public void testGet()
  {
    EmployeeDO employee = employeeDAO.get( 1619L );
    Assert.assertNotNull( employee );
    log( employee );
  }

  @Test
  public void testCreate()
  {
    EmployeeDO employee = new EmployeeDO();
    employee.setLastName( "Sanchez" );
    employee.setFirstName( "Juan" );
    employee.setOffice( officeDAO.get( "1" ) );
    employee.setEmail( "juan.sanchez@classicmodelcars.com" );
    employee.setExtension( "x9001" );
    employee.setJobTitle( "Sales Rep" );
    employee.setReportsTo( this.employeeDAO.get( 1143L ) );

    this.employeeDAO.create( employee );
    log( employee );

    Long employeeNumber = employee.getEmployeeNumber();
    Assert.assertNotNull( employeeNumber );
    this.employeeDAO.get( employeeNumber );
    Assert.assertNotNull( employee );

  }

  @Test
  public void testEdit()
  {
    EmployeeDO employee = this.employeeDAO.get( 1619L );
    employee.setOffice( this.officeDAO.get( "2" ) );
    this.employeeDAO.edit( employee );
    employee = this.employeeDAO.get( 1619L );

    Assert.assertNotNull( employee );
    Assert.assertNotNull( employee.getOffice() );
    Assert.assertEquals( "2", employee.getOffice().getOfficeCode() );
  }

  private void log( EmployeeDO employee )
  {
    LOG.info( "{}. {}, {}. {} - {}", employee.getEmployeeNumber(), employee.getLastName(), employee.getFirstName(),
      employee.getJobTitle(), employee.getOffice().getCity() );

  }

}
