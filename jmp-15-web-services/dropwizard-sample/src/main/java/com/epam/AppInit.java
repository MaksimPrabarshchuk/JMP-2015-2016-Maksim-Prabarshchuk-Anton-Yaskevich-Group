package com.epam;

import com.epam.dao.UserDAO;
import com.epam.model.User;
import com.epam.resource.InitialResource;
import com.epam.resource.UserResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

public class AppInit extends Application<AppConfiguration> {
    public static void main(String[] args) throws Exception {
        new AppInit().run(args);
    }

    private final HibernateBundle<AppConfiguration> hibernate =
            new HibernateBundle<AppConfiguration>(User.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new AssetsBundle("/assets/"));
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) throws Exception {
        final UserDAO dao = new UserDAO(hibernate.getSessionFactory());
        environment.jersey().register(new InitialResource());
        environment.jersey().register(new UserResource(dao));
    }
}
