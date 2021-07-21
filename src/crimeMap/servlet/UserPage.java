package crimeMap.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crimeMap.model.*;
import crimeMap.dal.*;
import com.google.gson.*;

@WebServlet(urlPatterns = "/profile")
public class UserPage extends HttpServlet{
	public class tempUser{
		String UserName;
		String FirstName;
		String LastName;
		//double credibilityRating;
		public tempUser(String UserName, String FirstName, String LastName) {
			this.UserName = UserName;
			this.FirstName = FirstName;
			this.LastName = LastName;
			//this.credibilityRating = credibilityRating;
		}
	}
	/**
	 * Connect backend with frontend, receive params from signup.js
	 */
	private static final long serialVersionUID = 1L;
	protected UsersDao userdao;
	@Override
	public void init() throws ServletException {
		userdao = UsersDao.getInstance();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("I'm here in userpage doPost");
        resp.setCharacterEncoding("utf-8");
        //resp.setContentType("textml;charset=UTF-8");
        
        String username = req.getParameter("userName");
        System.out.println("userpage, username =   " + username);
		try {
			Persons user = userdao.getUserFromUserName(username);
			System.out.println("can I get the user?   " + user.getUserName());
	        String temp = new Gson().toJson(user);
	        System.out.println("the json string is:   " + temp);
		    PrintWriter out = resp.getWriter();
		    out.print(temp);
		    out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("I'm here in userpage doGet");
        resp.setCharacterEncoding("utf-8");
        //resp.setContentType("textml;charset=UTF-8");
        
        String username = req.getParameter("userName");
        System.out.println("userpage, username =   " + username);
        Persons user = new Persons("nulluser", "nulluser", "nulluser", "nulluser");
		try {
			user = userdao.getUserFromUserName(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tempUser tempuser = new tempUser(user.getUserName(), user.getFirstName(), user.getLastName());
        String temp = new Gson().toJson(tempuser);
	    PrintWriter out = resp.getWriter();
	    out.print(temp);
	    out.close();
    }
}
