package com.axity.springmvc.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.axity.springmvc.dao.PaymentDAO;
import com.axity.springmvc.entity.PaymentDO;
import com.axity.springmvc.to.Commission;
import com.axity.springmvc.to.Payment;

@RunWith(SpringRunner.class)
@ContextConfiguration({ "classpath:/beans_commission.xml" })
@Transactional
public class CustomerServiceCommissionTest
{

  @Autowired
  private CustomerService customerService;

  @Autowired
  private PaymentDAO paymentDAO;

  @Autowired
  private EmployeeService employeeService;

  @Autowired
  private OfficeService officeService;

  @Before
  public void setUp() throws Exception
  {
  }

  @Test
  public void testAssignPayment()
  {
    Long customerNumber = 103L;
    String checkNumber = "A0000001";
    long salesRepresentant = 1370L;

    Payment payment = new Payment();
    payment.setCustomerNumber( 103L );
    payment.setCheckNumber( "A0000001" );
    payment.setDate( LocalDate.now() );
    payment.setAmount( new BigDecimal( 1000.0 ) );

    BigDecimal commissionPercent = new BigDecimal( "0.05" );
    BigDecimal salesCommission = payment.getAmount().multiply( commissionPercent );

    when( officeService.getCommission( ArgumentMatchers.anyString() ) ).thenReturn( commissionPercent );
    when( employeeService.saveCommision( ArgumentMatchers.any( Commission.class ) ) ).thenReturn( 1900L );
    Commission commission = this.customerService.assignPayment( payment );

    assertNotNull( commission );
    assertNotNull( commission.getCommissionId() );
    assertEquals( payment.getCustomerNumber(), commission.getCustomerNumber() );
    assertEquals( payment.getCheckNumber(), commission.getCheckNumber() );
    assertEquals( payment.getDate(), commission.getDate() );
    assertEquals( payment.getAmount(), commission.getAmount() );
    assertEquals( salesRepresentant, commission.getEmployeeNumber().longValue() );
    assertEquals( commissionPercent, commission.getPercent() );
    assertEquals( salesCommission, commission.getSalesCommission() );

    assertEquals( 1900L, commission.getCommissionId().longValue() );

    PaymentDO paymentDO = paymentDAO.get( customerNumber, checkNumber );
    assertNotNull( paymentDO );
  }

  @Test
  public void testAssignPayment_customerNotFound()
  {

  }

  @Test
  public void testAssignPayment_employeeNotAssigned()
  {

  }

}
