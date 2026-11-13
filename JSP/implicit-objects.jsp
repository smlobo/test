<html>
	<head>
		<title>JSP Tutorial : Implicit Objects</title>
	</head>
	<body>
		<h1>JSP Implicit Objects</h1>

		<!-- out : JspWriter -->
		<%
			out.print("<h2>out : JspWriter</h2>");
			out.print("Today is: " + java.util.Calendar.getInstance().
				getTime());
		%>

		<!-- request : HttpServletRequest -->
		<h2>request : HttpServletRequest</h2>
		<form method="post" action="implicit-objects.jsp">
			Favorite color: 
			<select name="fColor">
        		<option value="green">Green</option>
        		<option value="yellow">Yellow</option>
        		<option value="blue">Blue</option>
        		<option value="purple">Purple</option>
		        <option value="orange">Orange</option>
		        <option value="red">Red</option>
    		</select>
    		<input type="submit" value="Use">
		</form>
		<%
			String sColor = request.getParameter("fColor");
			if (sColor == null)
				sColor = "black";
			out.println("<p><font color=\"" + sColor + "\">You should " + 
				"like this :-)</font>");
		%>


		<!-- response : HttpServletResponse -->
		<h2>response : HttpServletResponse</h2>
		<form method="post" action="implicit-objects.jsp">
			<input type="radio" name="gender" value="f">Female
    		<input type="radio" name="gender" value="m">Male
    		<input type="submit">
    	</form>
    	<%
			String gender = request.getParameter("gender");
    		if (gender == null) {
    			out.print("<p>No selection yet!");
 		   	}
 		   	else if (gender.equals("f")) {
    			response.sendRedirect("http://www.oracle.com");
 		   	}
 		   	else if (gender.equals("m")) {
    			response.sendRedirect("http://www.google.com");
    		}
    	%>

		<!-- config : ServletConfig -->
		<h2>config : ServletConfig</h2>
		<%
			out.print("<br>ServletName: " + config.getServletName());
			java.util.Enumeration<String> pNames = 
				config.getInitParameterNames();
      		while (pNames.hasMoreElements() ) {
      			String pName = pNames.nextElement();
          		out.println("<br>[" + pName + "] = " + 
          			config.getInitParameter(pName));
      		}
		%>
		
		<!-- application : ServletContext -->
		<h2>application : ServletContext</h2>
		<%
			out.print("<br>ServerInfo: " + application.
				getServerInfo());
			out.print("<br>VirtualServerName: " + application.
				getVirtualServerName());
			out.print("<br>ContextPath: " + application.
				getContextPath());
		%>
		
		<!-- session : HttpSession -->
		<h2>session : HttpSession</h2>
		<%
			session.setAttribute("user", "Timbuctoo");
			out.print("<br>Session attribute {user} = " + 
				session.getAttribute("user"));
		%>
		
		<!-- pageContext : PageContext -->
		
	</body>
</html>