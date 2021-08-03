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
	<form action="readreportsDate" method="post">
		<h1>Search For Reports By Date.</h1>
		<p>
			<label for="content">Enter a Date(yyyy-MM-dd):</label>
			<input id="content" name="content" value="${fn:escapeXml(param.content)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="readReports"><a href="ReadReports.jsp">Return to Reports Search page.</a></div>
	<br/>
	<h1>Matching Reports</h1>
        <table border="1">
            <tr>
                <th>ReportId</th>
                <th>Latitude</th>
                <th>Longitude</th>
                <th>ReportTime</th>
                <th>PublishedAsReport</th>

            </tr>
            <c:forEach items="${reports}" var="reports" >
                <tr>
                	<td><c:out value="${reports.getReportId()}" /></td>
                	<td><c:out value="${reports.getLatitude()}" /></td>
                    <td><c:out value="${reports.getLongitude()}" /></td>
                	<td><fmt:formatDate value="${reports.getReportTime()}" pattern="yyyy-MM-dd"/></td>
                    <td><c:out value="${reports.isPublishedAsReport()}" /></td>
                </tr>
            </c:forEach>
       </table>
       <script src="./js/bootstrap.min.js"></script>
</body>
</html>
