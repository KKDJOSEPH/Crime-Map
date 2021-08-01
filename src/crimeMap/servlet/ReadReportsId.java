package crimeMap.servlet;

import crimeMap.dal.*;
import crimeMap.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/readreportsId")
public class ReadReportsId extends HttpServlet {
	protected ReportsDao reportsDao;
	  
  @Override
  public void init() throws ServletException {
    reportsDao = ReportsDao.getInstance();
  }
  
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        List<Reports> reports = new ArrayList<Reports>();
        Integer content =  Integer.parseInt(req.getParameter("content"));
        if (content.equals(null)) {
            messages.put("success", "Please enter valid date");
        } else {
          try {
            reports = reportsDao.getReportsByReportId(content);
            } catch (SQLException e) {
          e.printStackTrace();
          throw new IOException(e);
            }
          messages.put("success", "Displaying results for " + content);
          messages.put("previousContent", content.toString());
        req.setAttribute("reports", reports);
        req.getRequestDispatcher("/ReadReportsId.jsp").forward(req, resp);
        }
  }
  
  @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        List<Reports> reports = new ArrayList<Reports>();  
        Integer content =  Integer.parseInt(req.getParameter("content"));
        if (content.equals(null)) {
            messages.put("success", "Please enter a valid date");
        } else {
          try {
        	  reports = reportsDao.getReportsByReportId(content);
            } catch (SQLException e) {
          e.printStackTrace();
          throw new IOException(e);
            }
          messages.put("success", "Displaying results for " + content);
        }
        req.setAttribute("reports", reports);
        req.getRequestDispatcher("/ReadReportsId.jsp").forward(req, resp);
    }
}