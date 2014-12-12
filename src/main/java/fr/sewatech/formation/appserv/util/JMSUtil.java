package fr.sewatech.formation.appserv.util;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;


public class JMSUtil {
	public static void sendObjectMessage(String queueName, Object content) {
		try {
			// Etablissement de la connexion
			Context jndiContext = new InitialContext();
			QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) jndiContext
					.lookup("ConnectionFactory");
			QueueConnection queueConnection = queueConnectionFactory
					.createQueueConnection();
			QueueSession queueSession = queueConnection.createQueueSession(
					false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = (Queue) jndiContext.lookup(queueName);
			// Envoi du message
			QueueSender queueSender = queueSession.createSender(queue);
			TextMessage message = queueSession.createTextMessage();
			message.setObjectProperty("message", content);
			queueSender.send(message);
			queueConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
