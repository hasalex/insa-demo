package fr.sewatech.formation.appserv.service;

public interface MessageService {
	
	Message getMessage(int id);
	int addMessage(String text);
	int countMessages();

}
