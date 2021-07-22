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

@WebServlet("/findcrimetips")
public class FindCrimeTips extends HttpServlet {
  protected CrimeTipsDao crimeTipsDao;
  
  @Override
  public void init() throws ServletException {
    crimeTipsDao = CrimeTipsDao.getInstance();
  }
  
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<CrimeTips> crimeTips = new ArrayList<CrimeTips>();
        
        // Retrieve and validate name.
        // content is retrieved from the URL query string.
        String content = req.getParameter("content");
        if (content == null || content.trim().isEmpty()) {
            messages.put("success", "Please enter a valid content");
        } else {
       // Retrieve CrimeTips, and store as a message.
          try {
            crimeTips = crimeTipsDao.getCrimeTipsFromContent(content);
            } catch (SQLException e) {
          e.printStackTrace();
          throw new IOException(e);
            }
          messages.put("success", "Displaying results for " + content);
          // Save the previous search term, so it can be used as the default
          // in the input box when rendering FindCrimeTips.jsp.
          messages.put("previousContent", content);
        }
        req.setAttribute("crimeTips", crimeTips);
        
        req.getRequestDispatcher("/FindCrimeTips.jsp").forward(req, resp);
  }
  
  @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<CrimeTips> crimeTips = new ArrayList<CrimeTips>();
        
        // Retrieve and validate name.
        // content is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindCrimeTips.jsp).
        String content = req.getParameter("content");
        if (content == null || content.trim().isEmpty()) {
            messages.put("success", "Please enter a valid content");
        } else {
       // Retrieve CrimeTips, and store as a message.
          try {
            crimeTips = crimeTipsDao.getCrimeTipsFromContent(content);
            } catch (SQLException e) {
          e.printStackTrace();
          throw new IOException(e);
            }
          messages.put("success", "Displaying results for " + content);
        }
        req.setAttribute("crimeTips", crimeTips);
        
        req.getRequestDispatcher("/FindCrimeTips.jsp").forward(req, resp);
    }
}
