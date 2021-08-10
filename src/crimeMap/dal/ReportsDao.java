package crimeMap.dal;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import crimeMap.model.Heatmap;

import java.sql.*;
import java.util.*;

public class ReportsDao {
	protected ConnectionManager connectionManager;

	  private static ReportsDao instance = null;
	  protected ReportsDao() {
	    connectionManager = new ConnectionManager();
	  }
	  public static ReportsDao getInstance() {
	    if(instance == null) {
	      instance = new ReportsDao();
	    }
	    return instance;
	  }

    public Heatmap getLongLat() throws SQLException {
        String location = "select Longitude, Latitude from Reports";
        Connection connection = null;
        PreparedStatement locationStmt = null;
        List<Double> lng = new ArrayList<Double>();
        List<Double> lat = new ArrayList<Double>();
        try {
            connection = connectionManager.getConnection();
            locationStmt = connection.prepareStatement(location);
            ResultSet results = locationStmt.executeQuery();

            if(results.next()) {
                lng.add(results.getDouble("Longitude"));
                lat.add(results.getDouble("Latitude"));
            }
            return new Heatmap(lng, lat);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(locationStmt != null) {
                locationStmt.close();
            }
        }
    }
}
