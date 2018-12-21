package com.ir.example.miniticket.config;

import com.ir.example.miniticket.dao.EventDao;
import com.ir.example.miniticket.dao.EventDaoImpl;
import com.ir.example.miniticket.dao.EventDateDao;
import com.ir.example.miniticket.dao.EventDateDaoImpl;
import com.ir.example.miniticket.dao.TicketDao;
import com.ir.example.miniticket.dao.TicketDaoImpl;
import com.ir.example.miniticket.dao.UserDao;
import com.ir.example.miniticket.dao.UserDaoImpl;
import com.ir.example.miniticket.service.EventService;
import com.ir.example.miniticket.service.EventServiceImpl;
import com.ir.example.miniticket.service.TicketService;
import com.ir.example.miniticket.service.TicketServiceImpl;
import com.ir.example.miniticket.service.UserService;
import com.ir.example.miniticket.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


/**
 * Class responsible to configure (instantiate) the dependencies injections.
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

    @Bean
    @Autowired
    public EventService eventService(EventDao eventDao, EventDateDao eventDateDao){
        return new EventServiceImpl(eventDao,eventDateDao);
    }

    @Bean
    @Autowired
    public TicketService ticketService(TicketDao ticketDao){
        return new TicketServiceImpl(ticketDao);
    }

    @Bean
    @Autowired
    public EventDao eventDao(JdbcTemplate jdbcTemplate) {
        return new EventDaoImpl(jdbcTemplate);
    }

    @Bean
    @Autowired
    public EventDateDao eventDateDao(JdbcTemplate jdbcTemplate) {
        return new EventDateDaoImpl(jdbcTemplate);
    }

    @Bean
    @Autowired
    public TicketDao ticketDaoDao(JdbcTemplate jdbcTemplate) {
        return new TicketDaoImpl(jdbcTemplate);
    }


}
