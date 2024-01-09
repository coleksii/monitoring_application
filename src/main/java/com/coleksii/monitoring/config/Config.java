package com.coleksii.monitoring.config;

import org.hsqldb.server.Server;
import org.hsqldb.server.ServerAcl.AclFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RestController;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.io.IOException;
import javax.sql.DataSource;

@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class Config {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
        .paths(PathSelectors.any())
        .build();
  }

  @Bean(initMethod = "start", destroyMethod = "stop")
  @Profile("local")
  public Server hsqlServer(DataSourceProperties dsProps) {
    /* CVE-2022-41853 vulnerability
       due to the requirement use Java 1.8 it is impossible to use hsqldb 2.7.1 version, as it uses Java 11.
       Earlier versions have vulnerability, but it can be prevented
       by setting the system property "hsqldb.method_class_names" to classes which are allowed to be called.
       abc value means that no classes are allowed to be called.
    */
    System.setProperty("hsqldb.method_class_names", "abc");
    Server server = new org.hsqldb.server.Server();
    server.setDatabaseName(0, dsProps.getName());
    server.setDatabasePath(0, "mem:" + dsProps.getName());
    server.setPort(9001);
    return server;
  }

  @Bean
  @DependsOn("hsqlServer")
  @Profile("local")
  public DataSource getDataSource(
      @Autowired DataSourceProperties dsProps) {
    return DataSourceBuilder
        .create()
        .driverClassName(dsProps.getDriverClassName())
        .url(dsProps.getUrl())
        .username(dsProps.getUsername())
        .password(dsProps.getPassword()).build();
  }
}
