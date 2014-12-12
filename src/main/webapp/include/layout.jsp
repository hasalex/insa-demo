<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="/include/error.jsp" %>
<!DOCTYPE html>
<html dir='ltr' lang='fr'>
<head>
    <title>Formation JBoss / Tomcat</title>
    <meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1'>
    <%
        pageContext.setAttribute("context_root", request.getContextPath());
    %>
    <link rel='stylesheet' href='${context_root}/css/default.css' type='text/css'>
    <link rel="icon" type="image/ico" href="favicon.ico"/>
</head>
<body>
<table style='border: 1px solid rgb(204, 204, 204);' align='center' cellpadding='0' cellspacing='0' width='1000'>
    <tbody>
    <tr>
        <td colspan="2">
            <div id="head">                <h1>D&eacute;mo INSA 12/2014</h1>
            </div>
        </td>
    </tr>

    <tr height='300' valign='top'>
        <td width='200pt'>
            <div id='menu'>
                <h2>Messages</h2>
                <ul>
                    <li><a href="${context_root}/message">Via servlet</a></li>
                    <li><a href="${context_root}/message.jsp">Via JSP</a></li>
                    <li><a href="${context_root}/message-db.jsp">Via database</a></li>
                    <li><a href="${context_root}/message-ejb3.jsp">Via EJB</a></li>
                    <li><a href="${context_root}/message-jms.jsp">Via JMS</a></li>
                </ul>
                <h2>S&eacute;curit&eacute;</h2>
                <ul>
                    <li><a href="${context_root}/secured">Secured servlet</a></li>
                    <li><a href="${context_root}/message-secured-ejb3.jsp">Secured EJB</a></li>
                    <li><a href="${context_root}/message-secured-jms.jsp">Secured JMS</a></li>
                    <li><a href="${context_root}/security/shutdown.jsp">Shutdown</a></li>
                    <li><a href="${context_root}/security/filesystem.jsp">File system</a></li>
                </ul>
                <h2>Divers</h2>
                <ul>
                    <li><a href="${context_root}/count.jsp">Compteur (session)</a></li>
                    <li><a href="${context_root}/memory.jsp">Mémoire</a></li>
                    <li><a href="${context_root}/info.jsp">Informations Web</a></li>
                    <li><a href="${context_root}/module-web.jsp">SLF4J (web)</a></li>
                    <li><a href="${context_root}/module-lib.jsp">SLF4J (lib)</a></li>
                    <!--<li><a href="${context_root}/redirect.jsp">redirection</a></li>-->
                </ul>
            </div>
        </td>
        <td>
            <div id='content'>
                <br/>
                <jsp:include page="${include_path}"/>
            </div>
        </td>
    </tr>

    <tr height='20'>
        <td valign='top' colspan="2">
            <div id='foot' align='center'>
                &copy; <a href='http://www.alexis-hassler.com'>Alexis Hassler</a>
            </div>
        </td>
    </tr>
    </tbody>
</table>

</body>
</html>
