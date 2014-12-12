<form>
    <select name="id">
    <% int messageCount = service.countMessages();
       for (int i = 0; i <= messageCount-1; i++) {
    	  String selected = (id.equals(String.valueOf(i)) ? "selected='selected'" : ""); %>
	      <option value="<%= i %>" <%= selected %>>n&deg;<%= i %></option>
    <% } %>
    </select>
    <input type="submit" value="OK"/>
</form>

<%System.out.println("Printing message : " + message.getText());%>
<p><%= message.getText() %></p>
<p><small><%= message.getOrigin() %></small></p>
