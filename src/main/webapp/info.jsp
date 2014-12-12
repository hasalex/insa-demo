    <p>Informations de la requ&ecirc;te :</p>
    <ul>
      <li>Principal : <%= request.getUserPrincipal() %></li>
      <li>Request : <%= request.getClass() %></li>
      <li>ContextPath : <%= request.getContextPath() %></li>
      <li>ServletPath : <%= request.getServletPath() %></li> 
      <li>RequestURL : <%= request.getRequestURL() %></li>
      <li>PathInfo : <%= request.getPathInfo() %></li> 
      <li>PathTranslated : <%= request.getPathTranslated() %></li>  
      <li>LocalAddr : <%= request.getLocalAddr() %></li>
      <li>RemoteAddr : <%= request.getRemoteAddr() %></li>
      <li>RemoteUser : <%= request.getRemoteUser() %></li>
      <li>ServerName:ServerPort : <%= request.getServerName() + ":" + request.getServerPort() %></li>
      <li>SessionId : <%= session.getId() %></li>
      <li>X-Forwarded-For : <%= request.getHeader("X-Forwarded-For") %></li>
      <li>X-Forwarded-Host : <%= request.getHeader("X-Forwarded-Host") %></li>
      <li>X-Forwarded-Server : <%= request.getHeader("X-Forwarded-Server") %></li>
    </ul>

    <p>Informations du serveur :</p>
    <ul>
        <li>Node name : <%= getSystemProperty("jboss.node.name") %></li>
    </ul>

    <%!
        private String getSystemProperty(String name) {
            try {
                return System.getProperty("jboss.node.name");
            } catch (java.security.AccessControlException e) {
                return ("********");
            }
        }
    %>