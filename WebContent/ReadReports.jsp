<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search for Reports</title>
</head>
<body>
	<form action="readreports" method="post">
		<h1>Choose A Search Option Below:</h1>
	</form>
	<br/>
	<div id="searchById"><a href="ReadReportsId.jsp">Search Reports By ReportId</a></div>
	<br/>
	<br/>
	<div id="searchByRegion"><a href="ReadReportsRegion.jsp">Search Reports By Region</a></div>
	<br/>
	<br/>
	<div id="searchByDate"><a href="ReadReportsDate.jsp">Search Reports By Date</a></div>
	<br/>
	<br/>
	<br/>
	<div id="return"><a href="UserPage.html">Return to UserPage.</a></div>
</body>
</html>
