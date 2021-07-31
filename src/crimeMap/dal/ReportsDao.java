package crimeMap.dal;
import java.sql.*;
import java.util.*;

public class ReportsDao {

    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
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

    public List<Double[]> getLongLat() throws SQLException {
        String location = "select longitude, latitude from Reports";
        Connection connection = null;
        PreparedStatement locationStmt = null;
        List<Double[]> resultSets = new ArrayList<Double[]>();
        try {
            connection = connectionManager.getConnection();
            locationStmt = connection.prepareStatement(location);
            ResultSet results = locationStmt.executeQuery();

            if(results.next()) {
                Double[] longlat = new Double[2];
                longlat[0] = results.getDouble("longitude");
                longlat[1] = results.getDouble("latitude");
                resultSets.add(longlat);
            }
            return resultSets;
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
