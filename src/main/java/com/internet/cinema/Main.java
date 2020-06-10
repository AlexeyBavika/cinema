package com.internet.cinema;

import com.internet.cinema.config.ApplicationConfig;
import com.internet.cinema.model.User;
import com.internet.cinema.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ApplicationConfig.class);
        UserService userService = context.getBean(UserService.class);
        User alexey = new User();
        alexey.setEmail("alexey@gmail.com");
        userService.add(alexey);
        User egor = new User();
        egor.setEmail("egor@gmail.com");
        userService.add(egor);

    }
}
