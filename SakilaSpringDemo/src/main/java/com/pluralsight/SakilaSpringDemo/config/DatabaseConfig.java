package com.pluralsight.SakilaSpringDemo.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// This is a Configuration class — Spring Boot will detect it and run it at startup.
// It defines a Bean for our DataSource — the object used to connect to the database.

@Configuration
public class DatabaseConfig {

    // We will build the BasicDataSource and store it here.
    private BasicDataSource basicDataSource;

    // This method defines the DataSource bean.
    // Spring will call this and register the DataSource in the ApplicationContext.
    @Bean
    public DataSource dataSource() {
        return basicDataSource;
    }

    // Constructor — Spring will call this and inject the datasource.url property here.
    // We will also manually read username/password from System properties — as you wanted — just like Workbook 8 style.
    public DatabaseConfig(@Value("${datasource.url}") String url) {

        // Read username and password from system properties — these were passed as command-line args.
        String username = System.getProperty("dbUsername");
        String password = System.getProperty("dbPassword");

        // Build the BasicDataSource.
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

    }
}
