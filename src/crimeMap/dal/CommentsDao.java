package crimeMap.dal;

import crimeMap.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class CommentsDao {
  protected ConnectionManager connectionManager;

  private static CommentsDao instance = null;
  protected CommentsDao() {
    connectionManager = new ConnectionManager();
  }
  public static CommentsDao getInstance() {
    if(instance == null) {
      instance = new CommentsDao();
    }
    return instance;
  }

  public Comments create(Comments comments) throws SQLException {
    //INSERT without UserName and ReportId
    String insertComment =
      "INSERT INTO Comments(Created,Content) " +
      "VALUES(?,?);";
    
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertComment,
        Statement.RETURN_GENERATED_KEYS);
      
      insertStmt.setTimestamp(1, new Timestamp(comments.getCreated().getTime()));
      insertStmt.setString(2, comments.getContent());
//      insertStmt.setInt(3, comments.getReportId());
      
      insertStmt.executeUpdate();
      
      resultKey = insertStmt.getGeneratedKeys();
      int commentId = -1;
      
      if(resultKey.next()) {
        commentId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      
      comments.setCommentId(commentId);
      return comments;
      
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
