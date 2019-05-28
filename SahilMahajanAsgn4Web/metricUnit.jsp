<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Metric Page</title>
</head>
<body>
	<h1>Sahil Mahajan's BMI Calculator</h1>
	<FORM NAME="form1" METHOD="post" ACTION="CalculatorForm">
		Please Enter your values in metric Units:<br/> 
		<font color="#ff0000">${message}</font><br/>
		<br/> 
		Height in Meters: <INPUT TYPE="TEXT" NAME="height" value="${storeht}"><br/> 
		<br/> 
		Weight in Kilograms: <INPUT	TYPE="TEXT" NAME="weight" value="${storewt}"><br/> 
		<br/>
		<INPUT TYPE="SUBMIT" value="Calculate BMI"><br/>
	</FORM>
</body>
</html>