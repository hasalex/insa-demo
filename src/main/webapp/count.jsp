<%@page import="fr.sewatech.formation.appserv.service.Counter"%><HTML>

<% 
  Counter cnt = (Counter)session.getAttribute("count");
  if (cnt == null) {
  	cnt = new Counter();
  	session.setAttribute("count", cnt);
  }
%>

Appel n&bull; <%= cnt.getNumber() %>
