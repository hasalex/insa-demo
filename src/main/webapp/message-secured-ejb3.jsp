<%@page import="javax.naming.InitialContext"%>
<%@page import="fr.sewatech.formation.appserv.service.MessageService"%>
<%@page import="fr.sewatech.formation.appserv.service.Message"%>

<%!private MessageService service;%>
<%
	if (service == null) {
		service = (MessageService)new InitialContext().lookup("java:app/swmsg-web/MessageSecuredBean!fr.sewatech.formation.appserv.ejb3.MessageServiceLocal");
	}
	
    String id = request.getParameter("id");
    if (id == null) {
    	id = "0";
    }
    Message message = service.getMessage(Integer.parseInt(id));
%>

<%@ include file="include/message.jsp" %>
