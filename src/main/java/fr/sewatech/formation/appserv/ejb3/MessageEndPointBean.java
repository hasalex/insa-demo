package fr.sewatech.formation.appserv.ejb3;

import fr.sewatech.formation.appserv.service.*;
import org.apache.log4j.*;

//@Stateless @Local(MessageServiceLocal.class)
//@WebService(name = "wsejb") @SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
//@RolesAllowed("sw-wsuser")
//@org.jboss.ejb3.annotation.SecurityDomain("sw-domain")
public class MessageEndPointBean {
    private static final Logger logger = Logger.getLogger(MessageEndPointBean.class);

	public MessageEndPointBean() {
	}

	public String getMessage(int id) {
        logger.info("Getting message with id " + id);
		return MessageHolder.get(id).toString();
	}

	public int countMessages() {
		return MessageHolder.count();
	}
	
	public int addMessage(String text) {
		Message message = new Message();
		message.setText(text);
		return MessageHolder.add(message);
	}
}
