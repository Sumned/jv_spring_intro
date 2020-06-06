package com.dev.spring.controller;

import com.dev.spring.config.AppConfig;
import com.dev.spring.dto.UserResponseDto;
import com.dev.spring.model.User;
import com.dev.spring.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController {
    private static final Logger LOGGER =
            LogManager.getLogger(UserController.class);

    private final AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);
    private final UserService userService = context.getBean(UserService.class);

    @GetMapping(value = "/user")
    public List<UserResponseDto> getAll() {
        List<UserResponseDto> dtoList = new ArrayList<>();
        userService.listUsers().forEach(user -> dtoList.add(new UserResponseDto(user)));
        return dtoList;
    }

    @GetMapping(value = "/")
    public ModelAndView index() {
        LOGGER.info("Hello");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping(value = "/user/inject")
    public String userInject() {
        User user = new User("user@user.com", "1234");
        userService.add(user);
        User user1 = new User("user1@user.com", "1234");
        userService.add(user1);
        User user2 = new User("user2@user.com", "1234");
        userService.add(user2);
        User user3 = new User("user3@user.com", "1234");
        userService.add(user3);
        return "Injection done successfully";
    }

    @GetMapping(value = "/user/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        UserResponseDto dto = null;
        for (User user : userService.listUsers()) {
            if (user.getId().equals(id)) {
                dto = new UserResponseDto(user);
                break;
            }
        }
        return dto;
    }
}
