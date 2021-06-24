package com.axity.springmvc.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Configuración de la base de datos de H2
 * 
 * @author guillermo.segura@axity.com
 */
@Configuration
public class DatabaseConfig
{

  /**
   * Obtiene el datasource
   * 
   * @return
   */
  @Bean
  public DataSource dataSource()
  {
    return new EmbeddedDatabaseBuilder().setType( EmbeddedDatabaseType.H2 ).addScript( "schema.sql" )
        .addScript( "data.sql" ).build();
  }

  /**
   * Obtiene el EntityManagerFactory
   * 
   * @return
   */
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory()
  {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource( dataSource() );
    em.setPackagesToScan( "com.axity.springmvc.entity" );

    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter( vendorAdapter );
    em.setLoadTimeWeaver( new InstrumentationLoadTimeWeaver() );
    return em;
  }

  /**
   * Obtiene el transactionManager
   * 
   * @return
   */
  @Bean
  public PlatformTransactionManager transactionManager()
  {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory( entityManagerFactory().getObject() );
    return transactionManager;
  }

}
