package fr.sewatech.formation.appserv.service;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message implements Serializable {
    private static final long serialVersionUID = 7102785003051250764L;
    private String text;
    private File origin;
    private final Date created;
    private final SimpleDateFormat dateFormatter;

    public Message() {
        created = new Date();
        dateFormatter = new SimpleDateFormat("HH:mm:ss,SSS");
    }

    public Message(String text, File origin) {
        this();
        this.origin = origin;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public File getOrigin() {
        return origin;
    }

    public void setOrigin(File origin) {
        this.origin = origin;
    }

    public Date getCreated() {
        return created;
    }

    public String getCreatedAsText() {
        return dateFormatter.format(created);
    }

    @Override
    public String toString() {
        return this.text;
    }
}
