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

@WebServlet(urlPatterns = "/deleteuser")
public class deleteUser extends HttpServlet{
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
		System.out.println("I'm here in deleteuser doPost");
        resp.setCharacterEncoding("utf-8");
        //resp.setContentType("textml;charset=UTF-8");
        String msg = "3";
        String username = req.getParameter("userName");
        System.out.println("deleteuser, username =   " + username);
		try {
			Persons user = userdao.getPersonFromUserName(username);
			user = userdao.delete(user);
			msg = "1";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg = "2";
		}
	    PrintWriter out = resp.getWriter();
	    out.print(msg);
	    out.close();
    }
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("I'm here in deleteuser doGet");
        resp.setCharacterEncoding("utf-8");
        //resp.setContentType("textml;charset=UTF-8");
        String msg = "3";
        String username = req.getParameter("userName");
        System.out.println("deleteuser, username =   " + username);
		try {
			Persons user = userdao.getPersonFromUserName(username);
			user = userdao.delete(user);
			msg = "1";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg = "2";
		}
	    PrintWriter out = resp.getWriter();
	    out.print(msg);
	    out.close();
    }
}
