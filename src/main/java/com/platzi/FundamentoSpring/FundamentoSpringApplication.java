package com.platzi.FundamentoSpring;

import com.platzi.FundamentoSpring.bean.MyBean;
import com.platzi.FundamentoSpring.bean.MyBeanWithDependency;
import com.platzi.FundamentoSpring.bean.MyBeanWithProperties;
import com.platzi.FundamentoSpring.bean.MyOwnBeanWithName;
import com.platzi.FundamentoSpring.component.ComponentDependency;
import com.platzi.FundamentoSpring.pojo.UserPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentoSpringApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentoSpringApplication.class);

	private ComponentDependency componenteDependecy;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependecy;
	private MyOwnBeanWithName myOwnBeanWithName;

	@Autowired
	private MyBeanWithProperties myBeanWithProperties;

	@Autowired
	private UserPojo userPojo;

	@Autowired//inyectar dependencia
	public FundamentoSpringApplication(@Qualifier("componentTwoImplement") ComponentDependency componenteDependecy, MyBean myBean
	, MyBeanWithDependency myBeanWithDependency,MyOwnBeanWithName myOwnBeanWithName){
		this.componenteDependecy=componenteDependecy;
		this.myBean=myBean;
		this.myBeanWithDependecy=myBeanWithDependency;
		this.myOwnBeanWithName=myOwnBeanWithName;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentoSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		componenteDependecy.saludar();
		myBean.print();
		myBeanWithDependecy.printWithDependency();
		myOwnBeanWithName.nombre();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getAge());
		System.out.println(userPojo.getEmail());
		System.out.println(userPojo.getPassword());
		try {
			int value=10/0;
			LOGGER.debug("Mi valor : "+value);
		}catch (Exception e){
			LOGGER.error("Esto es un error "+e.getMessage());
		}


	}
}
