<%@page import="fr.sewatech.formation.appserv.service.MessageService"%>
<%@page import="fr.sewatech.formation.appserv.service.MessageServiceImpl"%>
<%@page import="fr.sewatech.formation.appserv.service.Message"%>
<%@ page import="org.apache.log4j.Logger" %>

<%! private MessageService service;
    private Logger log = Logger.getLogger("fr.sewatech.jsp.message");
%>

<%
    if (service == null) {
    	service = new MessageServiceImpl();
    }
	    
	String id = request.getParameter("id");
	if (id == null) {
		id = "0";
	}
	log.debug("Asking for message " + id + " to " + service.getClass().getName());
	Message message = service.getMessage(Integer.parseInt(id));
%>

<%@ include file="include/message.jsp" %>
