package fr.sewatech.formation.appserv.web;

import fr.sewatech.formation.appserv.util.ClassUtil;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class LogListener implements ServletContextListener {
    private static Logger logger = Logger.getLogger(LogListener.class);

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Initializing " + ClassUtil.getFile(Logger.class));
        String configuration = servletContextEvent.getServletContext().getInitParameter("log4j.configuration");
        DOMConfigurator.configureAndWatch(configuration, 5000);
        logger.debug("Configuring Log4J with file " + configuration);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Undeploying context");
    }
}
