<%@page import="fr.sewatech.formation.appserv.service.MessageService"%>
<%@page import="fr.sewatech.formation.appserv.service.MessageServiceDbImpl"%>
<%@page import="fr.sewatech.formation.appserv.service.Message"%>
<%@ page import="org.apache.log4j.Logger" %>

<%! private MessageService service;
    private Logger log = Logger.getLogger("fr.sewatech.jsp.message_db");
%>

<%
    if (service == null) {
		service = new MessageServiceDbImpl("java:comp/env/jdbc/sewa-ds", true);
	}
	
	String id = request.getParameter("id");
	if (id == null) {
	   	id = "0";
	}
	log.debug("Asking for message " + id + " to " + service.getClass().getName());
	Message message = service.getMessage(Integer.parseInt(id));
%>

<%@ include file="include/message.jsp" %>
