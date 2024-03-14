
package com.example.config;

import com.example.primary.CustomerAServiceImpl;
import com.example.primary.CustomerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
//importamos un archivo beans
@ImportResource(value = "classpath:beans.xml")
public class BeansConfig {
    /*
    esta configuracion se usa para configurar clase que provienen de frameworks externos
     */

//    @Bean
//    public CustomerService customerService(){
//        return new CustomerAServiceImpl();
//    }
}
