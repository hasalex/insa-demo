package fr.sewatech.formation.appserv.web;

import fr.sewatech.formation.appserv.service.MessageServiceDbImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author Alexis Hassler
 */
@WebListener
public class DbInitListener implements ServletContextListener {

    public static final String DATASOURCE_NAME = "java:comp/env/jdbc/sewa-ds";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        new MessageServiceDbImpl(DATASOURCE_NAME).init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
