<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>Sahil Mahajan Wins Game</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<h1>Sorry ${sessionScope.user}, I won.</h1>
<p>Guessed your  number <b> ${requestScope.lastGuess}</b>
in <b>${requestScope.guessCount}</b> tries.</p>
<p>Click to <a href="${pageContext.request.contextPath}/index.jsp" ><b>try again</b></a></p>
</body>
</html>