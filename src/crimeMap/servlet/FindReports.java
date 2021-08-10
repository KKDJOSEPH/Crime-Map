package crimeMap.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crimeMap.dal.ReportsDao;
import crimeMap.model.Reports;

@WebServlet("/findreports")

public class FindReports extends HttpServlet {
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

          List<Reports> reports = new ArrayList<>();
          
          // Retrieve and validate name.
          // content is retrieved from the URL query string.
          String dateStr = req.getParameter("date");
          if (dateStr == null || dateStr.trim().isEmpty()) {
              messages.put("success", "Please enter a valid date");
          } else {
         // Retrieve reports, and store as a message.
            try {
              Date date = Date.valueOf(dateStr);
              reports = reportsDao.getReportsByDate(date);
              } catch (SQLException e) {
	            e.printStackTrace();
	            throw new IOException(e);
              }
            messages.put("success", "Displaying results for " + dateStr);
            // Save the previous search term, so it can be used as the default
            // in the input box when rendering FindReports.jsp.
            messages.put("previousDate", dateStr);
          }
          req.setAttribute("reports", reports);
          
          req.getRequestDispatcher("/FindReports.jsp").forward(req, resp);
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Reports> reports = new ArrayList<>();
        
        // Retrieve and validate name.
        // date is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindReports.jsp).
        String dateStr = req.getParameter("date");
        if (dateStr == null || dateStr.trim().isEmpty()) {
            messages.put("success", "Please enter a valid date");
        } else {
       // Retrieve Reports, and store as a message.
          try {
            Date date = Date.valueOf(dateStr);
            reports = reportsDao.getReportsByDate(date);
            } catch (SQLException e) {
          e.printStackTrace();
          throw new IOException(e);
            }
          messages.put("success", "Displaying results for " + dateStr);
        }
        req.setAttribute("reports", reports);
        
        req.getRequestDispatcher("/FindReports.jsp").forward(req, resp);
    }
	
}