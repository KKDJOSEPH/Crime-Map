package crimeMap.servlet;
import crimeMap.dal.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/signin")
public class Signin extends HttpServlet{
	/**
	 * Connect backend with frontend, receive params from signin.js
	 */
	private static final long serialVersionUID = 1L;
	protected UsersDao userdao;
	@Override
	public void init() throws ServletException {
		userdao = UsersDao.getInstance();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("I'm here in sign in doPost");
        resp.setCharacterEncoding("utf-8");
        String msg = "3";
        
        String username = req.getParameter("userName");
        String password = req.getParameter("passWord");
        System.out.println(username);
        System.out.println(password);
        
        boolean flag1 = false;
		try {
			flag1 = userdao.checkUserName(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        boolean flag2 = false;
		try {
			flag2 = userdao.checkUser(username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        if(flag1 && flag2) msg = "1";
        else if(!flag1) msg = "2";
        else msg = "3";
	    PrintWriter out = resp.getWriter();
	    out.print(msg);
	    out.close();
    }
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("I'm here in sign in doGet");
        resp.setCharacterEncoding("utf-8");
        //resp.setContentType("textml;charset=UTF-8");
        String msg = "3";
        
        String username = req.getParameter("userName");
        String password = req.getParameter("passWord");
        System.out.println(username);
        System.out.println(password);
        
        boolean flag1 = false;
		try {
			flag1 = userdao.checkUserName(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        boolean flag2 = false;
		try {
			flag2 = userdao.checkUser(username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        if(flag1 && flag2) msg = "1";
        else if(flag1) msg = "2";
        else msg = "3";
	    PrintWriter out = resp.getWriter();
	    out.print(msg);
	    out.close();
    }
}
