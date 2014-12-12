<%@page import="fr.sewatech.formation.appserv.service.Message" %>
<%@ page import="fr.sewatech.formation.appserv.util.ClassUtil" %>
<%@ page import="fr.sewatech.formation.appserv.service.MessageServiceSecuredJms" %>

<%!private MessageServiceSecuredJms service;%>
<%
    if (service == null) {
        service = new MessageServiceSecuredJms();
    }

    String operation = request.getParameter("operation");
    Message message = null;

    if (!"receive".equals(operation)) {
        String textMessage = request.getParameter("message");
        if (textMessage != null) {
            message = new Message();
            message.setText(textMessage);
            message.setOrigin(ClassUtil.getLibrary(MessageServiceSecuredJms.class));
            service.send(message);
        }
    }
%>


<table width="100%">
    <tr>
        <td width="50%" valign="top">
            <form>
                <input type="hidden" name="operation" value="send"/>

                <p><input type="text" name="message"/><input type="submit" value="Send"/></p>
            </form>
        </td>
        <td width="50%" valign="top">
            <form>
                <input type="hidden" name="operation" value="receive"/>

                <p><input type="submit" value="Receive"/></p>
                <% if ("receive".equals(operation)) {
                    message = service.getMessage();
                %>
                <p>Message re&ccedil;u : <%= (message == null ? "" : message.getText()) %>
                </p>
                <% } %>
            </form>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <p>
                <small><%= (message == null ? "" : "Origine : " + message.getOrigin()) %>
                </small>
            </p>
        </td>
    </tr>
</table>
