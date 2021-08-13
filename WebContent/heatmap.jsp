<%--
  Created by IntelliJ IDEA.
  User: HeyangLiu
  Date: 7/24/21
  Time: 9:46 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import ="java.sql.*" %>
<html>
<head>
    <title>HeatMap</title>
</head>
</html>


<%
  try {
    String url = "";
    Class.forName("com.mysql.jdbc.Driver");

  } catch (Exception ex) {
    out.print("FAIL TO LOAD HEATMAP <a href='ACCOUNT.html'>refresh</a>");
  } finally {

  }
%>