package com.epam.repository.factory;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateFactory {

    private static SessionFactory factory = null;

    private static void init() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        factory = configuration.buildSessionFactory(builder.build());
    }

    public static SessionFactory getSessionFactory() {
        if (factory == null) {
            init();
        }
        return factory;
    }


}
