package fr.sewatech.formation.appserv.service;

import fr.sewatech.formation.appserv.util.ClassUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MessageServiceImpl implements MessageService {

	private static final File origin = ClassUtil.getLibrary(MessageServiceImpl.class);
	private List<Message> messages;
	
	public MessageServiceImpl() {
		messages = new ArrayList<Message>();
		
		messages.add(new Message("Hello tout le monde", origin));
		messages.add(new Message("Salut everybody", origin));
		messages.add(new Message("JAAS is the way", origin));
	}
	
	public Message getMessage(int id) {
		return messages.get(id);
	}

	public int countMessages() {
		return messages.size();
	}

	public int addMessage(String text) {
		messages.add(new Message(text, origin));
		return messages.size()-1;
	}	
}
