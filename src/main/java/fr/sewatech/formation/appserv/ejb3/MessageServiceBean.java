package fr.sewatech.formation.appserv.ejb3;

import fr.sewatech.formation.appserv.service.Message;
import fr.sewatech.formation.appserv.service.MessageService;
import org.apache.log4j.Logger;

import javax.annotation.security.PermitAll;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(MessageService.class)
@Local(MessageServiceLocal.class)
@PermitAll
public class MessageServiceBean implements MessageService {
    private static Logger logger = Logger.getLogger(MessageServiceBean.class);

	public MessageServiceBean() {
	}

	public Message getMessage(int id) {
        logger.info("Getting message with id " + id);
		return MessageHolder.get(id);
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
