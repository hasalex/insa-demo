package fr.sewatech.formation.appserv.service;

import org.apache.log4j.Logger;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.Serializable;

public final class MessageServiceSecuredJms {
    private static final Logger logger = Logger.getLogger(MessageServiceSecuredJms.class);

    private ConnectionFactory connectionFactory;
    private Queue queue;
    private Connection connection;
    private javax.jms.Message message;

    public Message getMessage() {
        try {
            logger.debug("Try to receive a message...");
            ObjectMessage message = (ObjectMessage) getJmsMessage();
            logger.debug("Message trouvé : " + message);
            return (message == null ? new Message("Aucun message", null) : (Message) message.getObject());
        } catch (Exception e) {
            logger.error("Problème de réception du message", e);
            throw new SewaException("Problème de réception du message", e);
        }
    }

    private javax.jms.Message getJmsMessage() throws Exception {
        MessageConsumer consumer = null;
        try {
            consumer = initialize().createConsumer(queue);
            message = consumer.receive(10000);
            return message;
        } finally {
            close(consumer);
            closeConnection();
        }
    }

    private void close(MessageConsumer consumer) {
        if (consumer != null) {
            try {
                consumer.close();
            } catch (JMSException e) {
                logger.warn("Problem when closing the consumer : " + e);
            }
        }
    }

    public void send(Serializable message) {
        MessageProducer producer = null;
        try {
            Session session = initialize();
            producer = session.createProducer(queue);
            producer.setTimeToLive(10000L);
            javax.jms.Message jmsMessage;
            if (message instanceof String) {
                logger.debug("Sending text message via JMS");
                jmsMessage = session.createTextMessage((String) message);
            } else {
                logger.debug("Sending object message via JMS");
                jmsMessage = session.createObjectMessage(message);
            }
            producer.send(jmsMessage);
            logger.debug("Message envoyé : " + message);
        } catch (Exception e) {
            logger.error("Problème de d'envoi du message", e);
            throw new SewaException("Problème d'envoi du message", e);
        } finally {
            close(producer);
            closeConnection();
        }
    }

    private void close(MessageProducer producer) {
        if (producer != null) {
            try {
                producer.close();
            } catch (JMSException e) {
                logger.warn("Problem when closing the producer : " + e);
            }
        }
    }

    private Session initialize() throws Exception {
        Session session;
        try {
            Context jndiContext;
            if (connectionFactory == null) {
                jndiContext = new InitialContext();
                connectionFactory = (ConnectionFactory) jndiContext.lookup("ConnectionFactory");
                queue = (Queue) jndiContext.lookup("java:comp/env/jms/SWq-sec");
            }

            if (connection == null) {
                logger.debug("Initializing connections to JMS server");
                connection = connectionFactory.createConnection();
            }
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            connection.start();
            return session;
        } catch (Exception e) {
            closeConnection();
            throw e;
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                logger.debug("Closing queue connection");
                connection.close();
                connection = null;
            }
        } catch (JMSException e) {
            logger.warn("Problème à la fermeture de la connexion JMS : " + e);
        }
    }
}
