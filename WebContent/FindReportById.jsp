<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Report By Id</title>
</head>
<body>
	<form action="findreportbyid" method="post">
		<h1>Search for a Report by ID</h1>
		<p>
			<label for="reportid">Report ID</label>
			<input id="reportid" name="reportid" value="${fn:escapeXml(param.reportid)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<br/>
	<h1>Matching Report</h1>
        <table border="1">
            <tr>
                <th>ReportId</th>
                <th>Latitude</th>
                <th>Longitude</th>
                <th>ReportTime</th>
                <th>CrimeTipId</th>
            </tr>
                <tr>
                	<td><c:out value="${report.getReportId()}"/></td>
                    <td><c:out value="${report.getLatitude()}" /></td>
                    <td><c:out value="${report.getLongitude()}" /></td>
					<td><fmt:formatDate value="${report.getReportTime()}" pattern="yyyy-MM-dd"/></td>
                    <td><c:out value="${report.getCrimeTipId()}" /></td>
                </tr>
       </table>
</body>
</html>
