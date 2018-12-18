package com.ir.example.miniticket.config;

import com.ir.example.miniticket.dao.UserDao;
import com.ir.example.miniticket.dao.UserDaoImpl;
import com.ir.example.miniticket.service.UserService;
import com.ir.example.miniticket.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


/**
 * Class responsible to configure (instantiate) the dependencies injections.
 * @author thiago-ferreira
 */
@Component
public class ApplicationConfiguration {

    @Bean
    @Autowired
    public UserDao userDao(JdbcTemplate jdbcTemplate) {
        return new UserDaoImpl(jdbcTemplate);
    }

    @Bean
    @Autowired
    public UserService userService(UserDao userDao){
        return new UserServiceImpl(userDao);
    }

}
