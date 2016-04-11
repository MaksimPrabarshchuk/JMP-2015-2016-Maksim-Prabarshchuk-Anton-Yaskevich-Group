package com.epam;

import com.epam.repository.factory.HibernateFactory;
import org.hibernate.SessionFactory;

public class Entry {
    public static void main(String[] args) {
        SessionFactory factory = HibernateFactory.getSessionFactory();
//        UserDAO userDAO = new UserDAOImpl();
    }
}
