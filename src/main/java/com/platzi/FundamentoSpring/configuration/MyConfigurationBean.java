package com.platzi.FundamentoSpring.configuration;

import com.platzi.FundamentoSpring.bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigurationBean {
    @Bean
    public MyBean beanOperation(){
        return new MyBean2Implement();
    }

    @Bean
    public MyOperation beanOperationOperation(){
        return new MyOperationImplement();
    }

    @Bean
    public MyBeanWithDependency beanOperationWithDependency(MyOperation myOperation){
        return new MyBeanWithDependencyImplement(myOperation);
    }

    @Bean
    public MyBeanOwn beanOwn(){
        return new MyBeanOwnImplement();
    }

    @Bean
    public MyOwnBeanWithName myOwnBeanWithName(){
        return new MyOwnBeanWithNameImplement();
    }

}
