package com.axity.springmvc.services;

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

import com.axity.springmvc.to.EmployeeReport;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration({ "classpath:/beans.xml" })
@Transactional
public class EmployeeServiceTest
{
  private static final Logger LOG = LoggerFactory.getLogger( EmployeeServiceTest.class );

  @Autowired
  private EmployeeService employeeService;

  @Test
  public void testFindAllEmployees() throws JsonProcessingException
  {
    List<EmployeeReport> employees = employeeService.findAllEmployees();

    Assert.assertNotNull( employees );

    ObjectMapper objectMapper = new ObjectMapper();

    String json = objectMapper.writeValueAsString( employees );
    LOG.info( json );
  }

}
