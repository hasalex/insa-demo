<%@ page import="org.apache.log4j.Logger" %><%! private Logger log = Logger.getLogger("jsp.message_db");
%>
<%
     String action = request.getParameter("action");
     if ("stop".equals(action)) {
       System.exit(1);
     }
%>
	<form>
		<input type="hidden" name="action" value="stop">
	    <input type="submit" value="Arr&ecirc;ter le serveur"/>
	</form>
