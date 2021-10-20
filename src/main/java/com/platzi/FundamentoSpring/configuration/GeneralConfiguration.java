package com.platzi.FundamentoSpring.configuration;

import com.platzi.FundamentoSpring.bean.MyBeanWithProperties;
import com.platzi.FundamentoSpring.bean.MyBeanWithPropertiesImplement;
import com.platzi.FundamentoSpring.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties(UserPojo.class)
public class GeneralConfiguration {

    @Value("${value.name}")
    private String name;


    @Value("${value.apellido}")
    private String apellido;


    @Value("${value.random}")
    private String random;


    @Bean
    public MyBeanWithProperties function(){
        return new MyBeanWithPropertiesImplement(name,apellido);
    }
}
