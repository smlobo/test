<html>
	<head>
		<title>JSP Tutorial : Directives</title>
	</head>
	<body>
		<h1>JSP Directives</h1>

		<!-- Page Directive -->
		<h3>Page Directive</h3>
		<%@ page import="java.util.Date" %>
		<%@ page info="composed by Sheldon Lobo" %>
		Today is: <%= new Date() %>
		<br>Servlet Info: <%= getServletInfo() %>
		
		<!-- Include Directive -->
		<h3>Include Directive</h3>
		<%@ include file="dropdown.html" %>
	</body>
</html>