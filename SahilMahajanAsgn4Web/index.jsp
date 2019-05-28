<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Index Page</title>
</head>
<body>
	<h1>Sahil Mahajan's Body Mass Index calculator</h1>
	<form method="post"><p>Pounds and Inches <input type="submit" name="unitU" value="USA" formaction="BmiForm" formmethod="post"/></p></form>
	<form method="post">Kilograms and Meters <input type="submit" name="unitM" value="Metric" formaction="BmiForm" formmethod="post"/></form>

</body>
</html>