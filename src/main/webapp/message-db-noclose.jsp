<%@page import="fr.sewatech.formation.appserv.service.MessageService"%>
<%@page import="fr.sewatech.formation.appserv.service.MessageServiceDbImpl"%>
<%@page import="fr.sewatech.formation.appserv.service.Message"%>

<%! private MessageService service;%>

<%
	if (service == null) {
		service = new MessageServiceDbImpl("java:comp/env/jdbc/sewa-ds", false);
	}
	
    String id = request.getParameter("id");
    if (id == null) {
    	id = "0";
    }
    Message message = service.getMessage(Integer.parseInt(id));
%>

<%@ include file="include/message.jsp" %>
