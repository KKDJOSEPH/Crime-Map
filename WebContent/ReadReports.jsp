<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  	<link rel="stylesheet" type="text/css" href="./stylesheets/header.css" />
    <link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css" />
<title>Search for Reports</title>
</head>
<body>
	 <header>
		<nav>
			<div class="logo">
				<a href="index.html"> <img src="./images/map_logo.jpg" /> </a>
			</div>
			<a class="nav-link active" href="index.html">Home</a>
			<a class="nav-link active" href="FindCrimeTips.jsp">CrimeTips</a>
			<a class="nav-link active" href="ReadReports.jsp">Reports</a>
		</nav>
	</header>
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
	
	<h1>Create Comment</h1>
	<form action="commentscreate" method="post">
		<p>
			<label for="created">Created (yyyy-mm-dd)</label>
			<input id="created" name="created" value="">
		</p>
		<p>
			<label for="content">Content</label>
			<input id="content" name="content" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
	<script src="./js/bootstrap.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
</body>
</html>
