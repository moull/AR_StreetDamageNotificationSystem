<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>End of Download</title>
</head>
<body>
<% if(request.getAttribute("error") != null) {%>
Something went wrong :( Please try again later!
<a href="startpage.html">Back to mainpage</a>
<% } else { %>
Download successful!<br>
The new data is now stored in your database!<br>
<a href="startpage.html">Back to mainpage</a>
<% } %>
</body>
</html>