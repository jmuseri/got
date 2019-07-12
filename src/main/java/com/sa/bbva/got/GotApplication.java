package com.sa.bbva.got;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.boot.jdbc.DataSourceBuilder;
import javax.sql.DataSource;

@SpringBootApplication
public class GotApplication {
    private String getSysConfPathResource(String fileName) {
        File f = new File(".");
        if (fileName.startsWith("/"))
        fileName = fileName.substring(1);
        
        String pathfinal = f.getAbsolutePath().concat("/syscfg/".concat(fileName));
        
        return pathfinal;
    }
        
    @Bean
    @Primary
    public DataSource dataSource() {
        Properties p = new Properties(); 
        try {
            String path = getSysConfPathResource("/bbdd-local.properties");
            FileReader reader=new FileReader(path); 
                p.load(reader);  
                System.out.println(p.getProperty("username"));  
                System.out.println(p.getProperty("password"));
                System.out.println(p.getProperty("url"));  
                System.out.println(p.getProperty("driver")); 
            return DataSourceBuilder
                .create()
                .username(p.getProperty("username"))
                .password(p.getProperty("password"))
                .url(p.getProperty("url"))
                .driverClassName(p.getProperty("driver"))
                .build();
        } catch (Exception e) { 
            System.out.println("Error al cargar Properties");
            return null;
        }
    }
    public static void main(String[] args) {
        SpringApplication.run(GotApplication.class, args);
    }
}
