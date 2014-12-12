package fr.sewatech.formation.appserv.ejb3;

import fr.sewatech.formation.appserv.service.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageHolder {
	private static List<Message> messages;

	static {
		fr.sewatech.formation.appserv.service.MessageService delegate = new fr.sewatech.formation.appserv.service.MessageServiceImpl();

		messages = new ArrayList<>();

		for (int i = 0; i < delegate.countMessages(); i++) {
			Message message = delegate.getMessage(i);
			message.setText(message.getText() + " (via EJB3)");
			messages.add(message);
		}
	}

	public static int add(Message message) {
		messages.add(message);
		return messages.size()-1;
	}

	public static Message get(int id) {
		return messages.get(id);
	}

	public static int count() {
		return messages.size();
	}
}
