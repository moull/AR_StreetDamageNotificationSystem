<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Choose Upload Method</title>
</head>
<style>
ul
{
float:middle;
width:100%;
padding:0;
margin:0;
list-style-type:none;
}
a
{
float:middle;
width:7em;
text-decoration:none;
color:white;
background-color:#000033;
padding:0.2em 0.6em;
border-right:3px solid white;
}
a:hover {background-color:#000033;}
li {display:inline;}
</style>
</head>
<body>
<p style="font-family:arial;font-size:150%;color:black">Choose Upload Method</p>

Here you can choose your upload method. You can either 
activate your GPS and let the GPS get the location for you 
or you can insert the GPS coordinates (if you happen to know them), 
but the easiest way is to insert the address (city, street, number).
<br>Choose now! <br>
<br>
<a href="getLocation.jsp">I want to use the GPS!</a><br><br>
<a href="insertaddress.jsp">I want to insert the address!</a><br><br>
<a href="insertcoordinates.jsp">I know the GPS coordinates!</a><br><br>

<a href="startpage.html">Back to mainpage</a>
</body>
</html>