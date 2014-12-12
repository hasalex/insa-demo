<%@page import="fr.sewatech.formation.appserv.service.MessageService"%>
<%@page import="fr.sewatech.formation.appserv.service.MessageServiceImpl"%>
<%@page import="fr.sewatech.formation.appserv.service.Message"%>
<%@ page import="org.apache.log4j.Logger" %>

<%! private MessageService service;
    private Logger log = Logger.getLogger("jsp.message");
%>
<%
    if (service == null) {
    	// Instanciation de la couche métier
    	service = new MessageServiceImpl();
    }
	    
	String id = request.getParameter("id");
	if (id == null) {
		id = "0";
	}
	Message message = service.getMessage(Integer.parseInt(id));
%>

<%@ include file="include/message.jsp" %>
