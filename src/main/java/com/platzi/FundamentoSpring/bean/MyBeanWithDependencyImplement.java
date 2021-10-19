package com.platzi.FundamentoSpring.bean;

import org.springframework.beans.factory.annotation.Autowired;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency {

    private MyOperation myOperation;
    @Autowired
    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        int numero=1;
       System.out.println(myOperation.suma(numero));
        System.out.println("hola desde la implementacio de ben con dependecia");
    }
}
