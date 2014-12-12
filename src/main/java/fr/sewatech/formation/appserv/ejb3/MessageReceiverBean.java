package fr.sewatech.formation.appserv.ejb3;

import org.apache.log4j.Logger;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Message-Driven Bean implementation class for: MessageReceiverBean
 *
 */
//@MessageDriven(
//		activationConfig = { @ActivationConfigProperty(
//				propertyName = "destinationType", propertyValue = "javax.jms.Queue"
//		) }, 
//		mappedName = "queue/sewa")
public class MessageReceiverBean implements MessageListener {
	private static Logger logger = Logger.getLogger(MessageReceiverBean.class);
	
    /**
     * Default constructor. 
     */
    public MessageReceiverBean() {
    }
	
	/**
     * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
     */
    public void onMessage(Message message) {
        try {
        	logger.info("Message reçu");
        	MessageHolder.add((fr.sewatech.formation.appserv.service.Message) message.getObjectProperty("message"));
		} catch (JMSException e) {
			logger.warn(e);
		}
    }
}
