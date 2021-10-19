package com.platzi.FundamentoSpring.bean;

import org.springframework.beans.factory.annotation.Autowired;

public class MyOwnBeanWithNameImplement implements MyOwnBeanWithName{

    @Autowired
    private MyBeanOwn myBeanOwn;

    /*
        inyectando por constructor y sin autoWired
        public MyOwnBeanWithNameImplement(MyBeanOwn myBeanOwn){
            this.myBeanOwn=myBeanOwn;

          }

     */

    @Override
    public void nombre() {
        System.out.println("Mi nombre es luis y estoy ");
        myBeanOwn.saludar();
    }
}
