package com.axity.springmvc.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.Serializable;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.axity.springmvc.to.Customer;
import com.axity.springmvc.to.PaginatedResponse;

@RunWith(SpringRunner.class)
@ContextConfiguration({ "classpath:/beans.xml" })
@Transactional
public class CustomerControllerTest
{

  @Autowired
  private CustomerController controller;

  @Before
  public void setUp() throws Exception
  {
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testGetCustomersIntInt()
  {
    int page = 0;
    int pageSize = 20;
    ResponseEntity<Serializable> responseEntity = controller.getCustomers( page, pageSize );
    assertNotNull( responseEntity );
    assertNotNull( responseEntity.getBody() );

    assertEquals( HttpStatus.OK, responseEntity.getStatusCode() );

    assertTrue( responseEntity.getBody() instanceof PaginatedResponse );
    PaginatedResponse<Customer> paginated = (PaginatedResponse<Customer>) responseEntity.getBody();

    assertNotNull( paginated.getResponse() );
    assertFalse( paginated.getResponse().isEmpty() );

  }

  @Test
  @Ignore
  public void testGetCustomers()
  {
    fail( "Not yet implemented" );
  }

  @Test
  @Ignore
  public void testFindCustomerByCustomerNumber()
  {
    fail( "Not yet implemented" );
  }

  @Test
  @Ignore
  public void testCreate()
  {
    fail( "Not yet implemented" );
  }

  @Test
  @Ignore
  public void testEdit()
  {
    fail( "Not yet implemented" );
  }

  @Test
  @Ignore
  public void testDelete()
  {
    fail( "Not yet implemented" );
  }

}
