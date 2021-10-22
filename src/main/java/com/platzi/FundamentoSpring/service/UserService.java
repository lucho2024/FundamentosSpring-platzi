package com.platzi.FundamentoSpring.service;

import com.platzi.FundamentoSpring.entity.User;
import com.platzi.FundamentoSpring.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    private final Log LOGGER = LogFactory.getLog(UserService.class);
    @Autowired
    private UserRepository userRepository;

    @Transactional//para hacer rollback
    public void saveTransactional(List<User> users){
        users.stream().peek(user -> LOGGER.info("Usuario insertado"+user))
                .forEach(user -> userRepository.save(user));
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }



}
