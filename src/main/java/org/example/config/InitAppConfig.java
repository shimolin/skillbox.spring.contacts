package org.example.config;

import org.example.DataLoadInit;
import org.example.DataLoad;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource("classpath:application-init.properties")
@Profile("init")
public class InitAppConfig {
    @Bean
    public DataLoad loadData(){
        return new DataLoadInit();
    }

}
