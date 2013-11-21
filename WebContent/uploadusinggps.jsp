<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="damage.model.bean.DamageBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Report damage using GPS</title>
</head>
<body>
<% if(request.getAttribute("error") != null) {%>
<h2>Something went wrong, please try again later.</h2><br><br>
<% } else { %>
<p style="font-family:arial;font-size:150%;color:black">Please insert the data of the damage you want to report</p>
<% DamageBean damage = (DamageBean) request.getAttribute("damage"); %>
<% double lat = damage.getLatitude(); %>
<% double lon = damage.getLongitude(); %>
<%
lat = lat * 10000000;
lat = Math.round(lat);
lat = lat / 10000000;

lon = lon * 10000000;
lon = Math.round(lon);
lon = lon / 10000000;
%>

<form action="/DamageFinal/coords" method="post">
<input id="longitude" type="hidden" name="longitude" value = <%=lon%> >
<input id="latitude" type="hidden" name="latitude" value = <%=lat%> >
<br>
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
		  <option value= 0>0 - not dangerous at all</option>
		  <option value = 1>1 - a little bit dangerous</option>
		  <option value = 2>2 - dangerous</option>
		  <option value = 3>3 - very dangerous</option>
		  </select>
		  <br>
Description: <input type="text" name="description" placeholder="Short description" /><br />
<input type="submit" value="Continue" /><br><br>
<% } %>
<a href="getLocation.jsp">Back</a> <br>
<a href="insertaddress.jsp">Report by inserting address</a><br>
<a href="insertcoordinates.jsp">Report by inserting Coordinates</a><br>
<a href="startpage.html">Back to mainpage</a>
</form>
</body>
</html>