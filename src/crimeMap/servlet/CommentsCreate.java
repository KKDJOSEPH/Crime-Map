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


@WebServlet("/commentscreate")
public class CommentsCreate extends HttpServlet {  
  protected CommentsDao commentsDao;
  
  @Override
  public void init() throws ServletException {
    commentsDao = CommentsDao.getInstance();
  }
  
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/CommentsCreate.jsp").forward(req, resp);
  }
  
  @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String content = req.getParameter("content");
        if (content == null || content.trim().isEmpty()) {
            messages.put("success", "Invalid content");
        } else {
//        Integer reportId = Integer.parseInt(req.getParameter("reportId"));
//        if (reportId == 0 || reportId == null) {
//            messages.put("success", "Invalid report Id");
//        } else {
          // Create the Comment.
          DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
          
          String stringCreated = req.getParameter("created");
          Date created = new Date();
          try {
            created = dateFormat1.parse(stringCreated);
          } catch (ParseException e) {
            e.printStackTrace();
        throw new IOException(e);
          }
          
//          String content = req.getParameter("content");
          
          try {
            Comments comment = new Comments(created, content);
            comment = commentsDao.create(comment);
            messages.put("success", "Successfully created a comment");
          } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
          }
        }
        
        req.getRequestDispatcher("/ReadReports.jsp").forward(req, resp);
    }
}
