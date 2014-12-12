    <p>Cookies</p>
    <ul>
<%
    Cookie[] cookies = request.getCookies();

    for(Cookie cookie : cookies){
%>
        <li><%= cookie.getName() %> : <%= cookie.getValue() %></li>

<%
    }
%>
    </ul>
