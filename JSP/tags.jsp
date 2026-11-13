<html>
	<head>
		<title>JSP Tutorial : Tags</title>
	</head>
	<body>
		<h1>JSP Tags</h1>

		<!-- Expression Tag -->
		<%= "<h2>Expression Tag</h2>" %>
		The time now is: <b><i><%= new java.util.Date() %></i></b>
		<br>Java version: <%= System.getProperty("java.version") %>
		<br>Java home: <%= System.getProperty("java.home") %>
		<br>OS name: <%= System.getProperty("os.name") %>
		<br>User name: <%= System.getProperty("user.name") %>
		<br>User home: <%= System.getProperty("user.home") %>
		<br>User dir: <%= System.getProperty("user.dir") %>

		<!-- Scriptlet Tag -->
		<%
			out.println("<h2>Scriptlet Tag</h2>");
			String os_name = System.getProperty("os.name");
			String os_version = System.getProperty("os.version");
		%>
		<%
			// Variables are live
			out.println("<font color=red>Server OS: " + os_name + ", " + os_version + "</font>");
			out.println("<br><b>Client IP addr: " + request.getRemoteAddr() + 
				"</b>");
		%>
		<br>
		<form method="post" action="tags.jsp">
			<input type="text" name="uname">
			<br>
			<input type="submit" value="Done">
		</form>
		<%
			/* Yet Another Scriptlet Tag */
			String name = request.getParameter("uname");
			out.println("Welcome from Sheldon to: " + name);
		%>

		<!-- Declaration Tag -->
		<h2>Declaration Tag</h2>
		<%!
			String fname = "Cube";
			int a = 3;
			int cube(int x) {
				return x * x * x;
			}
		%>
		<%=
			fname + " of " + a + " is: " + cube(a)
		%>
	</body>
</html>