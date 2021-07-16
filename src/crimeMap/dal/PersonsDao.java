package crimeMap.dal;
import crimeMap.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PersonsDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static PersonsDao instance = null;
	protected PersonsDao() {
		connectionManager = new ConnectionManager();
	}
	public static PersonsDao getInstance() {
		if(instance == null) {
			instance = new PersonsDao();
		}
		return instance;
	}

	/**
	 * Save the Persons instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Persons create(Persons person) throws SQLException {
		String insertPerson = "INSERT INTO Persons(UserName,Password,FirstName,LastName) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPerson);
			// PreparedStatement allows us to substitute specific types into the query template.
			// For an overview, see:
			// http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html.
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// For nullable fields, you can check the property first and then call setNull()
			// as applicable.
			insertStmt.setString(1, person.getUserName());
			insertStmt.setString(2, person.getPassword());
			insertStmt.setString(3, person.getFirstName());
			insertStmt.setString(4, person.getLastName());
			// Note that we call executeUpdate(). This is used for a INSERT/UPDATE/DELETE
			// statements, and it returns an int for the row counts affected (or 0 if the
			// statement returns nothing). For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// I'll leave it as an exercise for you to write UPDATE/DELETE methods.
			insertStmt.executeUpdate();
			
			// Note 1: if this was an UPDATE statement, then the person fields should be
			// updated before returning to the caller.
			// Note 2: there are no auto-generated keys, so no update to perform on the
			// input param person.
			return person;
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
	 * Update the LastName of the Persons instance.
	 * This runs a UPDATE statement.
	 */
	public Persons updateName(Persons person, String newFirstName, String newLastName) throws SQLException {
		String updatePerson = "UPDATE Persons SET FirstName=?,LastName=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePerson);
			updateStmt.setString(1, newFirstName);
			updateStmt.setString(2, newLastName);
			updateStmt.setString(3, person.getUserName());
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			person.setLastName(newLastName);
			person.setFirstName(newFirstName);
			return person;
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

	/**
	 * Delete the Persons instance.
	 * This runs a DELETE statement.
	 */
	public Persons delete(Persons person) throws SQLException {
		String deletePerson = "DELETE FROM Persons WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePerson);
			deleteStmt.setString(1, person.getUserName());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Persons instance.
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

	/**
	 * Get the Persons record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Persons instance.
	 */
	public Persons getPersonFromUserName(String userName) throws SQLException {
		String selectPerson = "SELECT UserName,Password,FirstName,LastName FROM Persons WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPerson);
			selectStmt.setString(1, userName);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				String resultUserName = results.getString("UserName");
				String password = results.getString("Password");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				Persons person = new Persons(resultUserName, password, firstName, lastName);
				return person;
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
