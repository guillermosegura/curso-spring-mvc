package com.axity.springmvc.dao;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

import com.axity.springmvc.entity.PaymentDO;
import com.axity.springmvc.entity.PaymentId;

@RunWith(SpringRunner.class)
@ContextConfiguration({ "classpath:/beans.xml" })
@Transactional
public class PaymentDAOTest
{
  private static final Logger LOG = LoggerFactory.getLogger( PaymentDAOTest.class );

  @Autowired
  private PaymentDAO paymentDAO;

  @Autowired
  private CustomerDAO customerDAO;

  @Test
  public void testFindAllByCustomerNumber()
  {
    List<PaymentDO> payments = paymentDAO.findAllByCustomerNumber( 124L );
    Assert.assertNotNull( payments );
    Assert.assertFalse( payments.isEmpty() );

    for( PaymentDO payment : payments )
    {
      log( payment );
    }
  }

  @Test
  public void testGet()
  {
    PaymentDO payment = paymentDAO.get( 124L, "CQ287967" );
    Assert.assertNotNull( payment );
    log( payment );
  }

  @Test
  public void testGet_notFound()
  {
    PaymentDO payment = paymentDAO.get( 124L, "qwerty" );
    Assert.assertNull( payment );
  }

  @Test
  public void testCreate()
  {
    PaymentDO payment = new PaymentDO();
    payment.setId( new PaymentId( 124L, "QUACK101" ) );
    payment.setPaymentDate( createDate( 10, Calendar.MARCH, 2020 ) );
    payment.setAmount( new BigDecimal( 5000.0 ) );
    paymentDAO.create( payment );

    Assert.assertNotNull( paymentDAO.get( 124L, "QUACK101" ) );
  }

  @Test
  public void testEdit()
  {
    PaymentDO payment = paymentDAO.get( 124L, "CQ287967" );
    payment.setAmount( new BigDecimal( 11050.0 ) );
    paymentDAO.edit( payment );

    payment = paymentDAO.get( 124L, "CQ287967" );
    Assert.assertNotNull( payment );
    Assert.assertEquals( 11050.0, payment.getAmount().doubleValue(), 0.01 );
    log( payment );
  }

  @Test
  public void testDelete()
  {
    PaymentDO payment = paymentDAO.get( 103L, "HQ336336" );
    Assert.assertNotNull( payment );

    paymentDAO.delete( 103L, "HQ336336" );

    payment = paymentDAO.get( 103L, "HQ336336" );
    Assert.assertNull( payment );
  }

  private Date createDate( int date, int month, int year )
  {
    Calendar cal = Calendar.getInstance();
    cal.set( year, month, date );
    return cal.getTime();
  }

  private void log( PaymentDO payment )
  {
    DateFormat df = new SimpleDateFormat( "dd/MM/yyyy" );
    NumberFormat nf = new DecimalFormat( "###,##0.00" );
    LOG.info( "{} [{}], {}, Check:{}, ${}", payment.getCustomer().getCustomerName(),
      payment.getCustomer().getCustomerNumber(), df.format( payment.getPaymentDate() ),
      payment.getId().getCheckNumber(), nf.format( payment.getAmount() ) );

  }

}
