package fr.sewatech.formation.appserv.web;

import fr.sewatech.formation.appserv.service.Message;
import fr.sewatech.formation.appserv.service.MessageService;
import fr.sewatech.formation.appserv.service.MessageServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;


/**
 * Servlet implementation class MessageServlet
 */
public class MessageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Logger logger = Logger.getLogger(MessageServlet.class);

    private MessageService service;

    /**
     * Default constructor.
     */
    public MessageServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("GET");
        service = new MessageServiceImpl();
        response.setContentType("text/html");
        try {
            Message message = service.getMessage(0);
            Writer out = response.getWriter();
            out.write("<p>" + message.getText() + "</p>");
            out.write("<p><small>Origine du message : " + message.getOrigin() + "</small></p>");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
