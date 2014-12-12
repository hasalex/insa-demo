<%@page import="fr.sewatech.formation.appserv.service.MessageService"%>
<%@page import="fr.sewatech.formation.appserv.service.MessageServiceImpl"%>
<%@page import="fr.sewatech.formation.appserv.service.Message"%>
<%@ page import="org.apache.log4j.Logger" %>
<%@ page import="org.slf4j.helpers.MessageFormatter" %>
<%@ page import="org.slf4j.helpers.FormattingTuple" %>

<%! private MessageService service;
    private Logger log = Logger.getLogger("jsp.modules");
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
    Message messageOriginal = service.getMessage(Integer.parseInt(id));
    FormattingTuple messageText = MessageFormatter.format(messageOriginal.getText() + ", {}", "via SLF4J");
    Message message = new Message(messageText.getMessage(), messageOriginal.getOrigin());
%>

<%@ include file="include/message.jsp" %>
