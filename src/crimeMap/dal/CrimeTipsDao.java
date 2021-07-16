package crimeMap.dal;

import crimeMap.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;

public class CrimeTipsDao {
  protected ConnectionManager connectionManager;

  private static CrimeTipsDao instance = null;
  protected CrimeTipsDao() {
    connectionManager = new ConnectionManager();
  }
  public static CrimeTipsDao getInstance() {
    if(instance == null) {
      instance = new CrimeTipsDao();
    }
    return instance;
  }

  public CrimeTips create(CrimeTips crimeTips) throws SQLException {
    //INSERT without UserName and publishedAsReport
    String insertCrimeTip =
      "INSERT INTO CrimeTips(Created,OccurredTime,Address,City,State,Zipcode,Content) " +
      "VALUES(?,?,?,?,?,?,?);";
    
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertCrimeTip,
        Statement.RETURN_GENERATED_KEYS);
      
      insertStmt.setTimestamp(1, new Timestamp(crimeTips.getCreated().getTime()));
      insertStmt.setTimestamp(2, new Timestamp(crimeTips.getOccurredTime().getTime()));
      insertStmt.setString(3, crimeTips.getAddress());
      insertStmt.setString(4, crimeTips.getCity());
      insertStmt.setString(5, crimeTips.getState());
      insertStmt.setString(6, crimeTips.getZipcode());
      insertStmt.setString(7, crimeTips.getContent());
      
      insertStmt.executeUpdate();
      
      resultKey = insertStmt.getGeneratedKeys();
      int crimeTipId = -1;
      
      if(resultKey.next()) {
        crimeTipId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      
      crimeTips.setCrimeTipId(crimeTipId);
      return crimeTips;
      
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(insertStmt != null) {
        insertStmt.close();
      }
      if(resultKey != null) {
        resultKey.close();
      }
    }
  }
}
