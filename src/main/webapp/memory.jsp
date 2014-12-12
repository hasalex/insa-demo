<%@page import="java.util.*"%>
<%@page import="fr.sewatech.formation.appserv.service.HeavyData"%><p>

<%! private final List<HeavyData> list = new ArrayList<HeavyData>(); %>
<%! private static final int weight = 500000; %>


	<form>
	    <input type="submit" name="action" value="+"/>
	</form>
	<form>
	    <input type="submit" name="action" value="-"/>
	</form>
</p>
<p>
<%
	int count = list.size();
	if ("+".equals(request.getParameter("action"))) {
		list.add(new HeavyData(weight).interned());
		count++;
	} else if (count>0 && "-".equals(request.getParameter("action"))) {
		list.remove(count-1);
		count--;
	}
	out.print("Heavy count : " + count);
%>
</p>
