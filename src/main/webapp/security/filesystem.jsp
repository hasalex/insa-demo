<%@ page import="java.io.File"%>
<%@ page import="org.apache.log4j.Logger" %>

<%! private Logger log = Logger.getLogger("jsp.message_db");
%>
<% 
	   %>
		<form>
			<p>Cr&eacute;er le fichier<input type="radio" name="action" value="newfile"><br/>
			   Supprimer le fichier<input type="radio" name="action" value="deletefile"></p>
		    <p><input type="submit" value="Go"/></p>
		</form>
	<% 
     String action = request.getParameter("action");
     if ("newfile".equals(action)) {
       File newfile = new File("nosecurity.txt");
       boolean ok = newfile.createNewFile();
       if (ok) {
%>
         Fichier &eacute;crit
         <%= newfile.getAbsolutePath() %>
<%
       }
     } else if ("deletefile".equals(action)) {
         File dir = new File(".");
         for (File file : dir.listFiles()) {
        	 if ("nosecurity.txt".equals(file.getName())) {
        		 boolean ok = file.delete();
%>
                 Fichier supprim&eacute;
                 <%= file.getAbsolutePath() %>
<%
        	 }
         }
    	 
      }
%>
