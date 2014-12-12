package fr.sewatech.formation.appserv.ejb3;

import fr.sewatech.formation.appserv.service.*;
import org.apache.log4j.*;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless @Remote(MessageService.class) @Local(MessageServiceLocal.class)
@RolesAllowed("sw-ejbuser") // Can be overridden in ejb-jar.xml
public class MessageSecuredBean implements MessageService {
    private static Logger logger = Logger.getLogger(MessageSecuredBean.class);

	public MessageSecuredBean() {
	}

	public Message getMessage(int id) {
        logger.info("Getting message with id " + id);
		return MessageSecuredHolder.get(id);
	}

	public int countMessages() {
		return MessageSecuredHolder.count();
	}

	public int addMessage(String text) {
		Message message = new Message();
		message.setText(text);
        logger.debug("Added message with text " + text);
		return MessageSecuredHolder.add(message);
	}
}
