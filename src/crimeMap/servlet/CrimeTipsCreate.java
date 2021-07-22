package crimeMap.servlet;

import crimeMap.dal.*;
import crimeMap.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/crimetipscreate")
public class CrimeTipsCreate extends HttpServlet {  
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
        //Just render the JSP.   
        req.getRequestDispatcher("/CrimeTipsCreate.jsp").forward(req, resp);
  }
  
  @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
//        String crimeTipId = req.getParameter("crimeTipId");
//        if (crimeTipId == null || crimeTipId.trim().isEmpty()) {
//            messages.put("success", "Invalid CrimeTipId");
//        } else {
        String content = req.getParameter("content");
        if (content == null || content.trim().isEmpty()) {
            messages.put("success", "Invalid content");
        } else {
          // Create the CrimeTip.
          DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
          
          String stringCreated = req.getParameter("created");
          Date created = new Date();
          try {
            created = dateFormat1.parse(stringCreated);
          } catch (ParseException e) {
            e.printStackTrace();
        throw new IOException(e);
          }
          
          DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
          String stringOccurredTime = req.getParameter("occurredTime");
          Date occurredTime = new Date();
          try {
            occurredTime = dateFormat2.parse(stringOccurredTime);
          } catch (ParseException e) {
            e.printStackTrace();
        throw new IOException(e);
          }
          
          String address = req.getParameter("address");
          String city = req.getParameter("city");
          String state = req.getParameter("state");
          String zipcode = req.getParameter("zipcode");
//          String userName = req.getParameter("userName");

//          boolean publishedAsReport = req.getParameter("published as report");

          try {
//            CrimeTips crimeTip = new CrimeTips(Integer.parseInt(crimeTipId), created, occurredTime, address, city, state,
//                zipcode, content);
            CrimeTips crimeTip = new CrimeTips(created, occurredTime, address, city, state,
                zipcode, content);
            crimeTip = crimeTipsDao.create(crimeTip);
//            messages.put("success", "Successfully created " + crimeTipId);
            messages.put("success", "Successfully created " + content);
          } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
          }
        }
        
        req.getRequestDispatcher("/CrimeTipsCreate.jsp").forward(req, resp);
    }
}
