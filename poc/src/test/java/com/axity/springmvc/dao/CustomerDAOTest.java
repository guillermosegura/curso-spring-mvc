package com.axity.springmvc.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.axity.springmvc.dao.projection.CustomerSmall;
import com.axity.springmvc.entity.CustomerDO;
import com.axity.springmvc.entity.EmployeeDO;

/**
 * Pruebas unitarias de la interfaz {@link mx.com.axity.poc.dao.CustomerDAO}
 * 
 * @author guillermo.segura@axity.com
 */
@RunWith(SpringRunner.class)
@ContextConfiguration({ "classpath:/beans.xml" })
@Transactional
public class CustomerDAOTest
{
  private static final Logger LOG = LoggerFactory.getLogger( CustomerDAOTest.class );

  @Autowired
  private CustomerDAO customerDAO;

  @Autowired
  private EmployeeDAO employeeDAO;

  @Test
  public void testFindAll()
  {
    List<CustomerDO> customers = this.customerDAO.findAll();
    Assert.assertNotNull( customers );
    Assert.assertFalse( customers.isEmpty() );

    customers.stream().forEach( c -> LOG.info( "{}", c ) );
  }

  @Test
  public void testFindByCustomerName()
  {
    List<CustomerDO> customers = this.customerDAO.findByCustomerName( "Volvo" );
    Assert.assertNotNull( customers );
    Assert.assertFalse( customers.isEmpty() );

    customers = this.customerDAO.findByCustomerName( "Mini" );
    Assert.assertNotNull( customers );
    Assert.assertFalse( customers.isEmpty() );

    customers = this.customerDAO.findByCustomerName( "Nelson" );
    Assert.assertNotNull( customers );
    Assert.assertTrue( customers.isEmpty() );
  }

  @Test
  public void testCountAll()
  {
    int n = this.customerDAO.countAll();
    Assert.assertTrue( n > 0 );
  }

  @Test
  public void testFindAllCustomers()
  {
    List<CustomerDO> customers = this.customerDAO.findAllCustomers();
    customers.stream().forEach( c -> {
      LOG.info( "{}-{}, contact: {},{}", c.getCustomerNumber(), c.getCustomerName(), c.getContactLastName(),
        c.getContactFirstName() );
    } );

  }

  @Test
  public void testFindBySalesRepEmployee()
  {
    List<CustomerDO> customers = this.customerDAO.findBySalesRepEmployee( 1370L );
    Assert.assertNotNull( customers );
    Assert.assertFalse( customers.isEmpty() );

    customers = this.customerDAO.findBySalesRepEmployee( 1002L );
    Assert.assertNotNull( customers );
    Assert.assertTrue( customers.isEmpty() );
  }

  @Test
  public void testGet()
  {
    CustomerDO customer = this.customerDAO.get( 103L );
    Assert.assertNotNull( customer );

  }

  @Test
  public void testGet_notFound()
  {
    CustomerDO customer = this.customerDAO.get( 99999L );
    Assert.assertNull( customer );

  }

  @Test()
  public void testCreate()
  {
    CustomerDO customerDO = new CustomerDO();
    customerDO.setCustomerName( "Test" );
    customerDO.setContactLastName( "Lopez" );
    customerDO.setContactFirstName( "Martha" );
    customerDO.setAddressLine1( "Calle 5 de Mayo 150" );
    customerDO.setCity( "CDMX" );
    customerDO.setCountry( "México" );
    customerDO.setPhone( "+52 5658 1111" );
    customerDO.setPostalCode( "11500" );

    this.customerDAO.create( customerDO );

    Assert.assertNotNull( this.customerDAO.get( customerDO.getCustomerNumber() ) );

  }

  @Test
  public void testEdit()
  {
    CustomerDO entity = this.customerDAO.get( 125L );
    CustomerDO customer = new CustomerDO();
    BeanUtils.copyProperties( entity, customer );

    System.out.println( customer.getCity() );
    customer.setCity( "ABC" );

    EmployeeDO salesRepEmployee = this.employeeDAO.get( 1165L );

    customer.setSalesRepEmployee( salesRepEmployee );

    this.customerDAO.edit( customer );

    CustomerDO customerOther = this.customerDAO.get( 125L );
    System.out.println( "---->" + customerOther.getCity() );
    Assert.assertNotNull( customerOther );
    Assert.assertNotNull( customerOther.getSalesRepEmployee() );
    Assert.assertEquals( 1165L, customerOther.getSalesRepEmployee().getEmployeeNumber().longValue() );
    Assert.assertEquals( "ABC", customerOther.getCity() );
  }

  @Test
  public void testDelete()
  {
    CustomerDO customerDO = new CustomerDO();
    customerDO.setCustomerName( "Test" );
    customerDO.setContactLastName( "Lopez" );
    customerDO.setContactFirstName( "Martha" );
    customerDO.setAddressLine1( "Calle 5 de Mayo 150" );
    customerDO.setCity( "CDMX" );
    customerDO.setCountry( "México" );
    customerDO.setPhone( "+52 5658 1111" );
    customerDO.setPostalCode( "11500" );

    this.customerDAO.create( customerDO );

    Long customerNumber = customerDO.getCustomerNumber();
    Assert.assertNotNull( customerNumber );

    this.customerDAO.delete( customerNumber );

    Assert.assertNull( this.customerDAO.get( customerNumber ) );
  }

}
