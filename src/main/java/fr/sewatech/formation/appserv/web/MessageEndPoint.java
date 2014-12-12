package fr.sewatech.formation.appserv.web;

import fr.sewatech.formation.appserv.service.*;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class MessageEndPoint {

    private MessageService service;

    public MessageEndPoint() {
        service = new MessageServiceImpl();
    }

    @WebMethod
    public String getMessage(int id) {
        return service.getMessage(id).toString();
    }
    public int addMessage(String text) {
        return service.addMessage(text);
    }
    public int countMessages() {
        return service.countMessages();
    }
}
