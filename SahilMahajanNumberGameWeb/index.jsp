<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>Welcome to a Sahil Mahajan's game</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<h1>Sahil Mahajan's Number Guessing Game</h1>
<p>Pick a number between 0 and 1000, inclusive. Remember it but don't tell me. 
I guarantee to guess it in 10 guesses or less, 
provided you don't change your number and answer my questions truthfully. </p>
<p>I will make a series of guesses. 
After each guess please tell me whether my guess is too high, too low, or correct. 
The game ends when the guess equals your number.</p>
<fieldset>
<legend><font color="red">${requestScope.message}</font></legend>
<form action="${pageContext.request.contextPath}/SignIn" method="post">
<p>Enter your name <input type="text" name="user"/> and click
<input type="submit" name="submit" value="Begin"/> to Start.</p>
</form></fieldset>
</body>
</html>