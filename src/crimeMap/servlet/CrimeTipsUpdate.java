package crimeMap.servlet;

import crimeMap.dal.*;
import crimeMap.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/crimetipsupdate")
public class CrimeTipsUpdate extends HttpServlet {  
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

        // Retrieve user and validate.
        String content = req.getParameter("content");
        if (content == null || content.trim().isEmpty()) {
            messages.put("success", "Invalid content");
        } else {
          try {
            CrimeTips crimeTip = crimeTipsDao.getCrimeTipsByContent(content);
            if(crimeTip == null) {
              messages.put("success", "Content does not exist.");
            }
            req.setAttribute("crimeTip", crimeTip);
          } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
          }
        }
        
        req.getRequestDispatcher("/CrimeTipsUpdate.jsp").forward(req, resp);
  }
  
  @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String content = req.getParameter("content");
        if (content == null || content.trim().isEmpty()) {
            messages.put("success", "Invalid content");
        } else {
          try {
            CrimeTips crimeTip = crimeTipsDao.getCrimeTipsByContent(content);
            if(crimeTip == null) {
              messages.put("success", "Content does not exist. No update to perform.");
            } else {
              String newAddress = req.getParameter("address");
              if (newAddress == null || newAddress.trim().isEmpty()) {
                      messages.put("success", "Please enter a valid address.");
                  } else {
                    crimeTip = crimeTipsDao.updateAddress(crimeTip, newAddress);
                    messages.put("success", "Successfully updated " + content);
                  }
            }
            req.setAttribute("crimeTip", crimeTip);
          } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
          }
        }
        
        req.getRequestDispatcher("/CrimeTipsUpdate.jsp").forward(req, resp);
    }
}
