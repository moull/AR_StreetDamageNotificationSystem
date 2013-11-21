<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Report Damage</title>
</head>
<body>
<p style="font-family:arial;font-size:180%;color:grey">
<p style="font-family:arial;font-size:150%;color:black">Please insert the data of the damage you want to report</p>
<form action="/DamageFinal/coords" method="post">
Latitude: <input type="text" name="latitude" placeholder="Coordinate: Latitude" /><br />
Longitude: <input type="text" name="longitude" placeholder="Coordinate: Longitude" /><br />
Type:
		<select name = "type">
		  <option value= "hole">Hole</option>
		  <option value = "object">Object</option>
		  <option value = "broken traffic light">Broken traffic lights</option>
		  <option value = "deer">Deer</option>
		  <option value = "other">Other</option>
		  </select>
		  <br>
Degree: 
		<select name = "degree">
		  <option value= "0">0 - not dangerous at all</option>
		  <option value = "1">1 - a little bit dangerous</option>
		  <option value = "2">2 - dangerous</option>
		  <option value = "3">3 - very dangerous</option>
		  </select>
		  <br>
Description: <input type="text" name="description" placeholder="Short description" /><br />
<input type="submit" value="Continue" />
</form>
<br>
<a href="upload.jsp">Back</a> <br>
<a href="insertaddress.jsp">Report by inserting address</a><br>
<a href="startpage.html">Back to mainpage</a>
</body>
</html>