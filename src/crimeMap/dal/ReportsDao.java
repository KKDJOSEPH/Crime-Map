package crimeMap.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import crimeMap.model.CrimeType;
import crimeMap.model.OffenseParentGroup;
import crimeMap.model.OffenseType;
import crimeMap.model.RegionCategory;
import crimeMap.model.Reports;

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

	  public Reports create(Reports reports) throws SQLException {
	    String insertReport =
	      "INSERT INTO Reports(Latitude,Longitude,ReportTime,PublishedAsReport,CrimeTipId) " +
	      "VALUES(?,?,?,?,?);";
	    
	    Connection connection = null;
	    PreparedStatement insertStmt = null;
	    ResultSet resultKey = null;
	    
	    try {
	      connection = connectionManager.getConnection();
	      insertStmt = connection.prepareStatement(insertReport,
	        Statement.RETURN_GENERATED_KEYS);
	      
	      insertStmt.setDouble(1, reports.getLatitude());
	      insertStmt.setDouble(2, reports.getLongitude());
	      insertStmt.setTimestamp(3, new Timestamp(reports.getReportTime().getTime()));
	      insertStmt.setBoolean(4, reports.isPublishedAsReport());
	      insertStmt.setInt(5, reports.getCrimeTipId());
	      
	      insertStmt.executeUpdate();
	      
	      resultKey = insertStmt.getGeneratedKeys();
	      int reportId = -1;
	      
	      if(resultKey.next()) {
	        reportId = resultKey.getInt(1);
	      } else {
	        throw new SQLException("Unable to retrieve auto-generated key.");
	      }
	      
	      reports.setReportId(reportId);
	      return reports;
	      
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
	  
	  public List<Reports> getReportsByDate(Date date) throws SQLException {
	    String selectReports =
	      "SELECT ReportId,Latitude,Longitude,ReportTime,PublishedAsReport,CrimeTipId " +
	      "FROM Reports " +
	      "WHERE DATE(ReportTime) = ?;";
	    
//	      "SELECT Reports.ReportId,Latitude,Longitude,ReportTime,Region,OffenseType,OffenseParentGroup " +
//	      "FROM Reports INNER JOIN RegionCategory ON Reports.ReportId = RegionCategory.ReportId " +
//	      "INNER JOIN OffenseParentGroup ON Reports.ReportId =  OffenseParentGroup.ReportId " +
//	      "INNER JOIN OffenseType ON Reports.ReportId =  OffenseType.ReportId " +
//	      "WHERE DATE(ReportTime) = ? " +
//	      "GROUP BY Reports.ReportId;";
	    
	    Connection connection = null;
	    PreparedStatement selectStmt = null;
	    ResultSet results = null;
	    List<Reports> reports = new ArrayList<Reports>();
	    
	    try {
	      ReportsDao reportsDao = ReportsDao.getInstance();
	      connection = connectionManager.getConnection();
	      selectStmt = connection.prepareStatement(selectReports);
	      selectStmt.setDate(1, date);
	      
	      results = selectStmt.executeQuery();
	      
	      while (results.next()) {
	    	  
		    int reportId = results.getInt("ReportId");
		    double latitude = results.getDouble("Latitude");
		    double longitude = results.getDouble("Longitude");
		    Date reportTime = new Date(results.getTimestamp("ReportTime").getTime());
		    RegionCategory.Region region = RegionCategory.Region.valueOf(results.getString("Region").replaceAll(" ", "_").replaceAll("/",  "_").replaceAll("-",  "_").toUpperCase());
		    OffenseType.Type offenseType = OffenseType.Type.valueOf(results.getString("OffenseType").replaceAll(" ", "_").replaceAll("/",  "_").replaceAll("-",  "_").toUpperCase());
		    OffenseParentGroup.ParentGroup offenseParentGroup = OffenseParentGroup.ParentGroup.valueOf(results.getString("OffenseParentGroup").replaceAll("/",  "_").replaceAll("-",  "_").replaceAll(" ", "_").toUpperCase());

	    	  
	    	  
	        Reports report = new Reports(reportId, latitude, longitude, reportTime, RegionCategory.Region.ALASKA_JUNCTION, OffenseType.Type.AGGRAVATED_ASSAULT, OffenseParentGroup.ParentGroup.ANIMAL_CRUELTY);
	        reports.add(report);
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
	    return reports;
	  }
	  
	  
	  public List<Reports> getReportsByRegion(String region) throws SQLException {
		    String selectReports =
		      "SELECT Reports.ReportId,Reports.Latitude,Reports.Longitude,ReportTime,PublishedAsReport,CrimeTipId " +
			  "FROM Reports INNER JOIN RegionCategory ON Reports.ReportId = RegionCategory.ReportId " +
		      "WHERE Region = ?;";

		    Connection connection = null;
		    PreparedStatement selectStmt = null;
		    ResultSet results = null;
		    List<Reports> reports = new ArrayList<Reports>();
		    
		    try {
		      ReportsDao reportsDao = ReportsDao.getInstance();
		      connection = connectionManager.getConnection();
		      selectStmt = connection.prepareStatement(selectReports);
		      selectStmt.setString(1, region);
		      
		      results = selectStmt.executeQuery();
		      
		      while (results.next()) {
		    	  
			    int reportId = results.getInt("ReportId");
			    double latitude = results.getDouble("Latitude");
			    double longitude = results.getDouble("Longitude");
			    Date reportTime = new Date(results.getTimestamp("ReportTime").getTime());
			    boolean reportPublished = results.getBoolean("PublishedAsReport");
			    int reportCrimeTipId = results.getInt("CrimeTipid");
			    
			    // RegionCategory.Region region = RegionCategory.Region.valueOf(results.getString("Region").replaceAll(" ", "_").replaceAll("/",  "_").replaceAll("-",  "_").toUpperCase());
			    // OffenseType.Type offenseType = OffenseType.Type.valueOf(results.getString("OffenseType").replaceAll(" ", "_").replaceAll("/",  "_").replaceAll("-",  "_").toUpperCase());
			    // OffenseParentGroup.ParentGroup offenseParentGroup = OffenseParentGroup.ParentGroup.valueOf(results.getString("OffenseParentGroup").replaceAll("/",  "_").replaceAll("-",  "_").replaceAll(" ", "_").toUpperCase());

		    	  
		    	  
		        Reports report = new Reports(reportId, latitude, longitude, reportTime, reportPublished, reportCrimeTipId);
		        reports.add(report);
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
		    return reports;
		  }
	  
	  public List<Reports> getReportsByReportId(int reportId) throws SQLException {
		    String selectReports =
		      "SELECT ReportId,Latitude,Longitude,ReportTime,PublishedAsReport,CrimeTipId " +
		      "FROM Reports " +
		      "WHERE ReportId = ?;";

		    Connection connection = null;
		    PreparedStatement selectStmt = null;
		    ResultSet results = null;
		    List<Reports> reports = new ArrayList<Reports>();
		    
		    try {
		      ReportsDao reportsDao = ReportsDao.getInstance();
		      connection = connectionManager.getConnection();
		      selectStmt = connection.prepareStatement(selectReports);
		      selectStmt.setInt(1, reportId);
		      
		      results = selectStmt.executeQuery();
		      
		      while (results.next()) {
		    	  
			    int resultsReportId = results.getInt("ReportId");
			    double latitude = results.getDouble("Latitude");
			    double longitude = results.getDouble("Longitude");
			    Date reportTime = new Date(results.getTimestamp("ReportTime").getTime());
			    boolean reportPublished = results.getBoolean("PublishedAsReport");
			    int reportCrimeTipId = results.getInt("CrimeTipid");
			    
			    // RegionCategory.Region region = RegionCategory.Region.valueOf(results.getString("Region").replaceAll(" ", "_").replaceAll("/",  "_").replaceAll("-",  "_").toUpperCase());
			    // OffenseType.Type offenseType = OffenseType.Type.valueOf(results.getString("OffenseType").replaceAll(" ", "_").replaceAll("/",  "_").replaceAll("-",  "_").toUpperCase());
			    // OffenseParentGroup.ParentGroup offenseParentGroup = OffenseParentGroup.ParentGroup.valueOf(results.getString("OffenseParentGroup").replaceAll("/",  "_").replaceAll("-",  "_").replaceAll(" ", "_").toUpperCase());

		    	  
		    	  
		        Reports report = new Reports(resultsReportId, latitude, longitude, reportTime, reportPublished, reportCrimeTipId);
		        reports.add(report);
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
		    return reports;
		  }
	 
}