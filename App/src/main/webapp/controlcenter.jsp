<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>
	<title>Home Automation Control Center</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="style.css" />
</head>
<body>
	<h1>Home Automation Control Center</h1>
	<table>
		<tr>
			<td>	
			<p>Light 1 Status: ${message}</p>
			<form action="SimpleServlet" method=get>
				<input type="submit" name="light_1" value="on"/>
				<input type="submit" name="light_1" value="off"/>
				<input type="submit" name="light_1" value="update"/>
			</form>		
			</td>
		</tr>
	</table>
</body>
</html>