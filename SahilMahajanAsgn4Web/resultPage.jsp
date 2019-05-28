<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Result Page</title>
</head>
<body>
	<h1>
		Sahil Mahajan Calculates a BMI of:
		<fmt:formatNumber type="number" maxFractionDigits="1" value="${bmi}" />
	</h1>
	<c:choose>

		<c:when test="${flag=='1'}">
			<p>
				For a person with <b>height ${ht} inches</b> and <b>weight ${wt}
					pounds.</b>
			</p>
			<h2>How to interpret this BMI value</h2>
			<p>${description }</p>
			<br />
			<a href="usUnit.jsp"> Try again</a> (same units) OR 
			<a href="index.jsp">Start Over</a> to change units
		</c:when>


		<c:when test="${flag=='0'}">
			<p>
				For a person with <b>height ${ht} meters</b> and <b>weight ${wt}
					kilograms.</b>
			</p>
			<h2>How to interpret this BMI value</h2>
			<p>${description }</p>
			<br />
			<a href="metricUnit.jsp"> Try again</a> (same units) OR 
			<a href="index.jsp">Start Over</a> to change units
		</c:when>
	</c:choose>

</body>
</html>