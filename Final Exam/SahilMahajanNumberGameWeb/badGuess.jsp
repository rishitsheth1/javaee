<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>Sahil Mahajan loses Game</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<h1>${sessionScope.user} has won!</h1>
<h2>All ${requestScope.maxGuesses} guesses are used</h2>
<p>Guess Trail: 
<c:forEach items = "${sessionScope.guessTrail}" var = "guess" > 
.. ${guess}
</c:forEach>
</p>
<h2>You win, but that should never happen.</h2>
<p> Did you change your number or respond incorrectly after a guess?</p>
<p><b>Reason:</b>  ${requestScope.message} </p>
<p>Click to <a href="${pageContext.request.contextPath}/index.jsp" ><b>try again</b></a></p>
</body>
</html>