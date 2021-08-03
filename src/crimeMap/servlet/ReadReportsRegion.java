package crimeMap.servlet;

import crimeMap.dal.*;
import crimeMap.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/readreportsRegion")
public class ReadReportsRegion extends HttpServlet {
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
        String content = req.getParameter("content");
        if (content.equals(null)) {
            messages.put("success", "Please enter valid date");
        } else {
          try {
            reports = reportsDao.getReportsByRegion(content);
            } catch (SQLException e) {
          e.printStackTrace();
          throw new IOException(e);
            }
          messages.put("success", "Displaying results for " + content);
          messages.put("previousContent", content);
        }
        req.setAttribute("reports", reports);
        req.getRequestDispatcher("/ReadReportsRegion.jsp").forward(req, resp);
  }
  
  @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        List<Reports> reports = new ArrayList<Reports>();
        String content =  req.getParameter("content");
        if (content.equals(null)) {
            messages.put("success", "Please enter a valid Region");
        } else {
          try {
        	  reports = reportsDao.getReportsByRegion(content);
            } catch (SQLException e) {
          e.printStackTrace();
          throw new IOException(e);
            }
          messages.put("success", "Displaying results for " + content);
        }
        req.setAttribute("reports", reports);
        
        req.getRequestDispatcher("/ReadReportsRegion.jsp").forward(req, resp);
    }
}