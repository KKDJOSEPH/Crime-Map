<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Report</title>
</head>
<body>
	<form action="findreports" method="post">
		<h1>Search for a Report by Date</h1>
		<p>
			<label for="date">Date (YYYY-MM-DD)</label>
			<input id="date" name="date" value="${fn:escapeXml(param.date)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<br/>
	<h1>Matching Reports</h1>
        <table border="1">
            <tr>
                <th>ReportId</th>
                <th>Latitude</th>
                <th>Longitude</th>
                <th>ReportTime</th>
                <th>CrimeTipId</th>
            </tr>
            <c:forEach items="${reports}" var="reports" >
                <tr>
                	<td><c:out value="${reports.getReportId()}"/></td>
                    <td><c:out value="${reports.getLatitude()}" /></td>
                    <td><c:out value="${reports.getLongitude()}" /></td>
					<td><fmt:formatDate value="${reports.getReportTime()}" pattern="yyyy-MM-dd"/></td>
                    <td><c:out value="${reports.getCrimeTipId()}" /></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
