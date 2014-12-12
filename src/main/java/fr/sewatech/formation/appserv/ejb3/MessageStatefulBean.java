package fr.sewatech.formation.appserv.ejb3;

import fr.sewatech.formation.appserv.service.Message;
import fr.sewatech.formation.appserv.service.MessageService;
import org.apache.log4j.Logger;

import javax.annotation.security.PermitAll;
import javax.ejb.Remote;
import javax.ejb.Stateful;

@Stateful
@Remote(MessageService.class)
@PermitAll
public class MessageStatefulBean implements MessageService {
    private static Logger logger = Logger.getLogger(MessageStatefulBean.class);
    private int counter = 0;

	public MessageStatefulBean() {
	}

	public Message getMessage(int id) {
        logger.info("Getting message with id " + id + " - call number" + ++counter);
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
