package com.platzi.FundamentoSpring.bean;

public class MyBeanImplement implements MyBean{
    @Override
    public void print() {
        System.out.println("Hola desde mi implementacion del bean");
    }
}
