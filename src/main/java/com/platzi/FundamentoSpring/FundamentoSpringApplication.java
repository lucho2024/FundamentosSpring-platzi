package com.platzi.FundamentoSpring;

import com.platzi.FundamentoSpring.bean.MyBean;
import com.platzi.FundamentoSpring.bean.MyBeanWithDependency;
import com.platzi.FundamentoSpring.bean.MyBeanWithProperties;
import com.platzi.FundamentoSpring.bean.MyOwnBeanWithName;
import com.platzi.FundamentoSpring.component.ComponentDependency;
import com.platzi.FundamentoSpring.entity.User;
import com.platzi.FundamentoSpring.pojo.UserPojo;
import com.platzi.FundamentoSpring.repository.UserRepository;
import com.platzi.FundamentoSpring.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Autowired//inyectar dependencia
    public FundamentoSpringApplication(@Qualifier("componentTwoImplement") ComponentDependency componenteDependecy, MyBean myBean
            , MyBeanWithDependency myBeanWithDependency, MyOwnBeanWithName myOwnBeanWithName, UserRepository userRepository) {
        this.componenteDependecy = componenteDependecy;
        this.myBean = myBean;
        this.myBeanWithDependecy = myBeanWithDependency;
        this.myOwnBeanWithName = myOwnBeanWithName;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(FundamentoSpringApplication.class, args);
    }

    @Override
    public void run(String... args) {
        //ejemplosAnteriores();
        saveUsersInDataBase();
        getInformationJpqlFromUser();
        saveWithErrorTransactional();
    }

    private void saveUsersInDataBase() {
        User user1 = new User("luis2", "luis@gmail.com", LocalDate.of(2021, 03, 10));
        User user2 = new User("luis2", "luis2@gmail.com", LocalDate.of(2021, 04, 12));
        User user3 = new User("luis3", "luis3@gmail.com", LocalDate.of(2021, 05, 14));
        User user4 = new User("luis4", "luis4@gmail.com", LocalDate.of(2021, 02, 16));
        User user5 = new User("luis5", "luis5@gmail.com", LocalDate.of(2021, 05, 12));
        User user6 = new User("luis6", "luis6@gmail.com", LocalDate.of(2021, 07, 15));
        User user7 = new User("luis7", "luis7@gmail.com", LocalDate.of(2021, 02, 10));

        List<User> userList = Arrays.asList(user1, user2, user3, user4, user5, user6, user7);

        //userList.stream().forEach(userRepository::save);
        //userList.forEach(userRepository::save);
        userRepository.saveAll(userList);


    }

    private void getInformationJpqlFromUser() {


       /* LOGGER.info("usuario con metodo findByUserEmail"
                + userRepository.findByUserEmail("luis6@gmail.com")
                .orElseThrow(() -> new RuntimeException("No se encontro el usario")));

        userRepository.findAndSort("luis",
                Sort.by("id").descending())
                .forEach(user -> LOGGER.info(user));

        userRepository.findByName("luis2").forEach(user -> LOGGER.info("Usuario con query method " + user));

        LOGGER.info("Usuario con nombre y correo" + userRepository.findByNameAndEmail("luis3", "luis3@gmail.com")
                .orElseThrow(() -> new RuntimeException("no encontrado")));

        userRepository
                .findByNameLike("%2%")
                .forEach(user -> LOGGER.info("Query metohd con like" + user));

        userRepository
                .findByNameOrEmail(null,"luis7@gmail.com")
                .forEach(user -> LOGGER.info("Query metohd con OR" + user));*/

        userRepository
                .findByBirthDateBetween(LocalDate.of(2021,03,01),
                        LocalDate.of(2021,05,14))
                .forEach(user -> LOGGER.info("usuario con fecha"+user));

        userRepository.findByNameLikeOrderByIdDesc("%2%")
                      .forEach(user -> LOGGER.info("usuario ordenados"+user));

        userRepository.findByNameContainingOrderByIdDesc("2")
                .forEach(user -> LOGGER.info("usuario ordenados containig"+user));

        LOGGER.info("El usuario apartir del named parameter es "+userRepository.getAllByDateAndEmail(LocalDate.of(2021, 05, 14),"luis3@gmail.com")
                .orElseThrow(()->new RuntimeException("No se encontro el usuario apartir del named parameter")));
    }

    public void ejemplosAnteriores() {
        componenteDependecy.saludar();
        myBean.print();
        myBeanWithDependecy.printWithDependency();
        myOwnBeanWithName.nombre();
        System.out.println(myBeanWithProperties.function());
        System.out.println(userPojo.getAge());
        System.out.println(userPojo.getEmail());
        System.out.println(userPojo.getPassword());
        try {
            int value = 10 / 0;
            LOGGER.debug("Mi valor : " + value);
        } catch (Exception e) {
            LOGGER.error("Esto es un error " + e.getMessage());
        }


    }

    private void saveWithErrorTransactional() {
        User test1 = new User("testTransactiona1","holi@gmail.com",LocalDate.now());
        User test2 = new User("testTransactiona2","holi2@gmail.com",LocalDate.now());
        User test3 = new User("testTransactiona3","holi@gmail.com",LocalDate.now());
        User test4 = new User("testTransactiona4","holi4@gmail.com",LocalDate.now());

        List<User> users= Arrays.asList(test1,test2,test3,test4);
        try {
            userService.saveTransactional(users);

            userService.getAllUsers()
                    .forEach(user ->
                            LOGGER.info("Este es el usuario dentro del metodo transactional"+user));
        }catch (Exception e){
            LOGGER.error("Excettion con transactional"+e);
        }

    }
}
