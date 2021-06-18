package com.axity.springmvc.config;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

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
    em.setPackagesToScan( new String[] { "com.axity.springmvc.entity" } );

    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter( vendorAdapter );
    em.setLoadTimeWeaver( new InstrumentationLoadTimeWeaver() );
    return em;
  }

  /**
   * Obtiene el transactionManager
   * @return
   */
  @Bean
  public DataSourceTransactionManager transactionManager()
  {
    DataSourceTransactionManager txManager = new DataSourceTransactionManager();
    txManager.setDataSource( dataSource() );
    return txManager;
  }

}
