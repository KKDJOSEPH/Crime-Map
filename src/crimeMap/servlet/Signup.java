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
@WebServlet(urlPatterns = "/signup")
public class Signup extends HttpServlet{
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
		System.out.println("I'm here in sign up doPost");
        resp.setCharacterEncoding("utf-8");
        //resp.setContentType("textml;charset=UTF-8");
        String msg = "3";
        
        String username = req.getParameter("userName");
        String firstname = req.getParameter("firstName");
        String lastname = req.getParameter("lastName");
        String password1 = req.getParameter("passWord1");
        String password2 = req.getParameter("passWord2");
        System.out.println(username);
        System.out.println(firstname);
        System.out.println(lastname);
        System.out.println(password1);
        System.out.println(password2);
        
        boolean flag = false;
		try {
			// checkUserName: return true if the username already exists
			flag = userdao.checkUserName(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
        if(flag) msg = "2";
        else{
        	msg = "1";
        	try {
        		Persons person = new Persons(username, password1, firstname, lastname);
				person = userdao.create(person);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	    PrintWriter out = resp.getWriter();
	    out.print(msg);
	    out.close();
    }
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("I'm here in sign up doGet");
        resp.setCharacterEncoding("utf-8");
        //resp.setContentType("textml;charset=UTF-8");
        String msg = "3";
        
        String username = req.getParameter("userName");
        String firstname = req.getParameter("firstName");
        String lastname = req.getParameter("lastName");
        String password1 = req.getParameter("passWord1");
        String password2 = req.getParameter("passWord2");
        System.out.println(username);
        System.out.println(firstname);
        System.out.println(lastname);
        System.out.println(password1);
        System.out.println(password2);
        
        boolean flag = false;
		try {
			// checkUserName: return true if the username already exists
			flag = userdao.checkUserName(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
        if(flag) msg = "2";
        else{
        	msg = "1";
        	try {
        		Persons person = new Persons(username, password1, firstname, lastname);
				person = userdao.create(person);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	    PrintWriter out = resp.getWriter();
	    out.print(msg);
	    out.close();
    }
}
