<%@page import="fr.sewatech.formation.appserv.service.MessageService"%>
<%@page import="fr.sewatech.formation.appserv.service.MessageServiceSlf4jImpl"%>
<%@page import="fr.sewatech.formation.appserv.service.Message"%>
<%@ page import="org.apache.log4j.Logger" %>

<%! private MessageService service;
    private Logger log = Logger.getLogger("jsp.modules");
%>
<%
    if (service == null) {
        // Instanciation de la couche métier
        service = new MessageServiceSlf4jImpl();
    }

    String id = request.getParameter("id");
    if (id == null) {
        id = "0";
    }
    Message message = service.getMessage(Integer.parseInt(id));
%>

<%@ include file="include/message.jsp" %>
