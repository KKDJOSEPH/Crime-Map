package crimeMap.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;
import crimeMap.dal.ReportsDao;


@WebServlet(urlPatterns = "/heatmap")
public class Heatmap extends HttpServlet {
    protected ReportsDao reportsDao;

    @Override
    public void init() throws ServletException {
        reportsDao = ReportsDao.getInstance();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        crimeMap.model.Heatmap heatmap;
        try {
            heatmap = ReportsDao.getInstance().getLongLat();
            String jsonObject = new Gson().toJson(heatmap);
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("text/json");
            resp.getWriter().write(jsonObject);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
