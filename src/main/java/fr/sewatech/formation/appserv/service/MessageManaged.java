package fr.sewatech.formation.appserv.service;

public class MessageManaged implements  MessageManagedMBean {
    
    private String message = "???";

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }
}
