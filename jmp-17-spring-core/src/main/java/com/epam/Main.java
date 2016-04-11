package com.epam;

import com.epam.config.HibernateConfiguration;
import com.epam.model.User;
import com.epam.repository.UserDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfiguration.class);
        UserDAO userDAO = context.getBean(UserDAO.class);

        User user = new User();
        user.setFirstName("Bob");
        user.setLastName("Marley");

        user = userDAO.create(user);
    }
}
