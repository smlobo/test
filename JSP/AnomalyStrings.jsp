<html>
	<head>
		<title>JSP Anomaly Strings</title>
	</head>
	<body>
		<h1>Anomaly Strings</h1>

		<%
			int stringCount = 0;
		%>

		<form method="post" action="AnomalyStrings.jsp">
			<input type="text" name="aString" value="moo">
			<br>
			<input type="submit" value="Generate New String">
		</form>

		<%
			String name = request.getParameter("aString");
			stringCount++;
			out.println("[" + stringCount + "] " + name);
		%>

	</body>
</html>