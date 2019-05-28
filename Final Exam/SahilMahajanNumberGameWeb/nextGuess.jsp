<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>Sahil Mahajan's Guessing Page </title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<h1>Sahil Mahajan's Guessing Page for ${sessionScope.user} </h1>
<h2>Guess number: ${requestScope.guessCount}</h2>
<c:if test = "${requestScope.guessCount > 1}">
<p>Guess Trail: 
<c:forEach items = "${sessionScope.guessTrail}" var = "guess" >
<c:if test = "${guess != requestScope.lastGuess }"> 
.. ${guess}
</c:if>
</c:forEach>
</p>
</c:if>
<p>Is your number <b>${requestScope.lastGuess}?</b></p>
<form method="post" action="${pageContext.request.contextPath}/GuessingGame" >
Please indicate whether this guess is &nbsp;
<input type="Submit" name="submit" value="correct" /> &nbsp;
<input type="Submit" name="submit" value="too high" />&nbsp; or &nbsp;
<input type="Submit" name="submit" value="too low" />
</form>
</body>
</html>