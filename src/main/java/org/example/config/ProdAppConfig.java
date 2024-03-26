package org.example.config;

import org.example.dataload.DataLoad;
import org.example.dataload.DataLoadProd;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-prod.properties")
@Profile("prod")
public class ProdAppConfig {

    @Bean
    public DataLoad loadData(){
        return new DataLoadProd();
    }

}
