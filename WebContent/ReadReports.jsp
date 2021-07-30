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
		<h1>Search For Reports.</h1>
		<p>
			<label for="content">Search by ReoportId:</label>
			<input id="content" name="content" value="${fn:escapeXml(param.content)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
<%-- 		<p>
			<label for="content">SearchByDate:</label>
			<input id="content" name="content" value="${fn:escapeXml(param.content)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p> --%>
	</form>
	<br/>
	<div id="userPage"><a href="UserPage.html">View User Page</a></div>
	<br/>
	<h1>Matching Reports</h1>
        <table border="1">
            <tr>
                <th>ReportId</th>
                <th>Latitude</th>
                <th>Longitude</th>
                <th>ReportTime</th>
<!--                 <th>Region</th> -->
                <th>PublishedAsReport</th>
<!--                 <th>OffenseType</th>
                <th>OffenseParentGroup</th> -->

            </tr>
            <c:forEach items="${reports}" var="reports" >
                <tr>
                	<td><c:out value="${reports.getReportId()}" /></td>
                	<td><c:out value="${reports.getLatitude()}" /></td>
                    <td><c:out value="${reports.getLongitude()}" /></td>
                	<td><fmt:formatDate value="${reports.getReportTime()}" pattern="yyyy-MM-dd"/></td>
<%--                  	<td><c:out value="${content.toString()}" /></td> --%>
                    <td><c:out value="${reports.isPublishedAsReport()}" /></td>
<%--                 	<td><c:out value="${reports.getOffenseType()}" /></td>
                    <td><c:out value="${reports.getOffenseParentGroup()}" /></td> --%>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
