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
			<p id="status">Status Device 1: ${message}</p>
			<form action="SimpleServlet" method=get>
				<input type="submit" name="switch_1" value="Toggle"/>
				<!-- <input type="submit" name="light_1" value="off"/> -->
				<input type="submit" name="switch_1" value="Update"/>
			</form>		
			</td>
		</tr>
		<tr>
			<td>	
			<p id="status_2">Status Device 2: ${message_2}</p>
			<form action="SimpleServlet" method=get>
				<input type="submit" name="switch_2" value="Toggle"/>
				<!-- <input type="submit" name="light_1" value="off"/> -->
				<input type="submit" name="switch_2" value="Update"/>
			</form>		
			</td>
		</tr>
	</table>
</body>
</html>