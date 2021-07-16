package crimeMap.dal;
import crimeMap.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UsersDao extends PersonsDao{
	// Single pattern: instantiation is limited to one object.
	private static UsersDao instance = null;
	protected UsersDao() {
		super();
	}
	public static UsersDao getInstance() {
		if(instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}

	public Users create(Users user) throws SQLException {
		// Insert into the superclass table first.
		create(new Persons(user.getUserName(), user.getPassword(), user.getFirstName(),
			user.getLastName()));

		String insertUser = "INSERT INTO Users(UserName,CredibilityRating) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser);
			insertStmt.setString(1, user.getUserName());
			insertStmt.setDouble(2, user.getCredibilityRating());
			insertStmt.executeUpdate();
			return user;
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
		}
	}

	/**
	 * Update the LastName of the BlogUsers instance.
	 * This runs a UPDATE statement.
	 */
	public Users updateName(Users user, String newFirstName, String newLastName) throws SQLException {
		// The field to update only exists in the superclass table, so we can
		// just call the superclass method.
		super.updateName(user, newFirstName, newLastName);
		return user;
	}

	/**
	 * Delete the BlogUsers instance.
	 * This runs a DELETE statement.
	 */
	public Users delete(Users user) throws SQLException {
		String deleteUser = "DELETE FROM Users WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setString(1, user.getUserName());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for UserName=" + user.getUserName());
			}

			// Then also delete from the superclass.
			// Notes:
			// 1. Due to the fk constraint (ON DELETE CASCADE), we could simply call
			//    super.delete() without even needing to delete from Administrators first.
			// 2. BlogPosts has a fk constraint on BlogUsers with the reference option
			//    ON DELETE SET NULL. If the BlogPosts fk reference option was instead
			//    ON DELETE RESTRICT, then the caller would need to delete the referencing
			//    BlogPosts before this BlogUser can be deleted.
			//    Example to delete the referencing BlogPosts:
			//    List<BlogPosts> posts = BlogPostsDao.getBlogPostsForUser(blogUser.getUserName());
			//    for(BlogPosts p : posts) BlogPostsDao.delete(p);
			super.delete(user);

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
	
	public boolean checkUser(String userName, String passWord) throws SQLException {
		String selectUser =
				"SELECT *" +
				"FROM Persons " +
				"WHERE UserName=? AND Password=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setString(1, userName);
			selectStmt.setString(2, passWord);
			results = selectStmt.executeQuery();
			if(results.next()) {
				return true;
			}else {
				return false;
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
	}
	
	public boolean checkUserName(String userName) throws SQLException {
		String selectUser =
				"SELECT *" +
				"FROM Persons " +
				"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				System.out.println("Here is checkUserName   " + results.getString("UserName"));
				return true;
			}else {
				return false;
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
	}

	public Users getUserFromUserName(String userName) throws SQLException {
		// To build an BlogUser object, we need the Persons record, too.
		String selectUser =
			"SELECT Users.UserName AS UserName, Password, FirstName, LastName, CredibilityRating " +
			"FROM Users INNER JOIN Persons " +
			"  ON Users.UserName = Persons.UserName " +
			"WHERE Users.UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultUserName = results.getString("UserName");
				String password = results.getString("Password");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				double rating = results.getDouble("CredibilityRating");
				Users user = new Users(resultUserName, password, firstName, lastName, rating);
				return user;
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
