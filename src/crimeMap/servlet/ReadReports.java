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

@WebServlet("/readreports")
public class ReadReports extends HttpServlet {
  protected ReportsDao reportsDao;
  
  @Override
  public void init() throws ServletException {
    reportsDao = ReportsDao.getInstance();
  }
  
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Reports> reports = new ArrayList<Reports>();
        
        // Retrieve and validate name.
        // content is retrieved from the URL query string.
        Integer content =  Integer.parseInt(req.getParameter("content"));
        //Date content = Date.valueOf(contentUnformatted);
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
          // Save the previous search term, so it can be used as the default
          // in the input box when rendering FindCrimeTips.jsp.
          messages.put("previousContent", content.toString());
        }
        req.setAttribute("reports", reports);
        
        req.getRequestDispatcher("/ReadReports.jsp").forward(req, resp);
  }
  
  @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Reports> reports = new ArrayList<Reports>();
        
        String content =  req.getParameter("content");
        int contentInt = Integer.parseInt(content);
        //Date content = Date.valueOf(contentUnformatted);
        if (content == null) {
            messages.put("success", "Please enter a valid date");
        } else {
          try {
        	  reports = reportsDao.getReportsByReportId(contentInt);
            } catch (SQLException e) {
          e.printStackTrace();
          throw new IOException(e);
            }
          messages.put("success", "Displaying results for " + content);
        }
        req.setAttribute("reports", reports);
        
        req.getRequestDispatcher("/ReadReports.jsp").forward(req, resp);
    }
}
