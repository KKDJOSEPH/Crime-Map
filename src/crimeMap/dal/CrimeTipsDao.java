package crimeMap.dal;

import crimeMap.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
import java.util.Date;
import java.util.List;

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
      "INSERT INTO CrimeTips(CreatedTime,OccurredTime,Address,City,State,Zipcode,Content) " +
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
//      insertStmt.setString(8, crimeTips.getUserName().getUserName());
      
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
  
  public CrimeTips getCrimeTipsByContent(String content) throws SQLException {
    String selectCrimeTip =
      "SELECT CreatedTime,OccurredTime,Address,City,State,Zipcode,Content " +
      "FROM CrimeTips " +
      "WHERE Content=?;";
    
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectCrimeTip);
      selectStmt.setString(1, content);
      
      results = selectStmt.executeQuery();
      
      if(results.next()) {
        Date createdTime = new Date(results.getTimestamp("CreatedTime").getTime());
        Date occurredTime = new Date(results.getTimestamp("OccurredTime").getTime());
        String address = results.getString("Address");    
        String city = results.getString("City");
        String state = results.getString("State");    
        String zipcode = results.getString("Zipcode");
        String resultContent = results.getString("Content");
        
        CrimeTips crimeTip = new CrimeTips(createdTime,occurredTime, address, city, state,
            zipcode, resultContent);
        
        return crimeTip;
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(selectStmt != null) {
        selectStmt.close();
      }
      if(results != null) {
        results.close();
      }
    }
    return null;
  }
  
  public CrimeTips updateAddress(CrimeTips crimeTip, String newAddress) throws SQLException {
    String updateCrimeTip = "UPDATE CrimeTips SET Address=? WHERE Content=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateCrimeTip);
      updateStmt.setString(1, crimeTip.getContent());
      updateStmt.setString(2, newAddress);
      updateStmt.executeUpdate();

      crimeTip.setAddress(newAddress);
      
      return crimeTip;
      
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(updateStmt != null) {
        updateStmt.close();
      }
    }
  }
  
  public List<CrimeTips> getCrimeTipsFromContent(String content)
      throws SQLException {
    List<CrimeTips> crimeTips = new ArrayList<CrimeTips>();
    String selectCrimeTips =
      "SELECT CreatedTime,OccurredTime,Address,City,State,Zipcode,Content " +
      "FROM CrimeTips " +
      "WHERE Content=?;";
    
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectCrimeTips);
      selectStmt.setString(1, content);
      results = selectStmt.executeQuery();
      
      while(results.next()) {
        Date createdTime = new Date(results.getTimestamp("CreatedTime").getTime());
        Date occurredTime = new Date(results.getTimestamp("OccurredTime").getTime());
        String address = results.getString("Address");    
        String city = results.getString("City");
        String state = results.getString("State");    
        String zipcode = results.getString("Zipcode");
        String resultContent = results.getString("Content");
        
        CrimeTips crimeTip = new CrimeTips(createdTime,occurredTime, address, city, state,
            zipcode, resultContent);
        
        crimeTips.add(crimeTip);
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(selectStmt != null) {
        selectStmt.close();
      }
      if(results != null) {
        results.close();
      }
    }
    return crimeTips;
  }
  
  public CrimeTips getCrimeTipsById(int crimeTipId) throws SQLException {
	    String selectCrimeTip =
	      "SELECT CrimeTipId,CreatedTime,OccurredTime,Address,City,State,Zipcode,Content " +
	      "FROM CrimeTips " +
	      "WHERE CrimeTipId=?;";
	    
	    Connection connection = null;
	    PreparedStatement selectStmt = null;
	    ResultSet results = null;
	    
	    try {
	      connection = connectionManager.getConnection();
	      selectStmt = connection.prepareStatement(selectCrimeTip);
	      selectStmt.setInt(1, crimeTipId);
	      
	      results = selectStmt.executeQuery();
	      
	      if(results.next()) {
	        Date createdTime = new Date(results.getTimestamp("CreatedTime").getTime());
	        Date occurredTime = new Date(results.getTimestamp("OccurredTime").getTime());
	        String address = results.getString("Address");    
	        String city = results.getString("City");
	        String state = results.getString("State");    
	        String zipcode = results.getString("Zipcode");
	        String content = results.getString("Content");
	        
	        CrimeTips crimeTip = new CrimeTips(createdTime,occurredTime, address, city, state,
	            zipcode, content);
	        crimeTip.setCrimeTipId(crimeTipId);
	        
	        return crimeTip;
	      }
	      
	    } catch (SQLException e) {
	      e.printStackTrace();
	      throw e;
	    } finally {
	      if(connection != null) {
	        connection.close();
	      }
	      if(selectStmt != null) {
	        selectStmt.close();
	      }
	      if(results != null) {
	        results.close();
	      }
	    }
	    return null;
	  }
  
}
