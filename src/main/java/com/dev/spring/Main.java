package com.dev.spring;

import com.dev.spring.config.AppConfig;
import com.dev.spring.model.User;
import com.dev.spring.service.UserService;
import com.dev.spring.util.HashUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private static final Logger LOGGER =
            LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        User user = new User("user@user.com", "1234");
        user.setSalt(HashUtil.getSalt());
        user.setPassword(HashUtil.hashPassword(user.getPassword(), user.getSalt()));
        userService.add(user);
        userService.listUsers().forEach(user1 -> LOGGER.info(user));
    }
}
