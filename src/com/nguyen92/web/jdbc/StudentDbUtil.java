package com.nguyen92.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;



public class StudentDbUtil {

	// Step 1:  Set up reference to a DataSource
	private DataSource dataSource;
	
	// Step 2:  Set up constructor, used by Servlet to pass db as parameter	
	public StudentDbUtil(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	
	// Step 3:  Write method to list the Student Objects
	public List<Student> getStudents() throws Exception {
		List<Student> students = new ArrayList<>();
		
	// Step 4:  Set up JDBC objects
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
		// Step 4a:  get a DB connection
			myConn = dataSource.getConnection();
			
		// Step 4b:  create the SQL statement
			String sql = "SELECT * FROM student ORDER BY last_name";
			myStmt = myConn.createStatement();
			
		// Step 4c:  process the ResultSet
			myRs = myStmt.executeQuery(sql);
			
			while(myRs.next()) {
			
				// retrieve data from ResultSet row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_Name");
				String lastName = myRs.getString("last_Name");
				String email = myRs.getString("email");
				
				// create new Student object
				Student currentStudent = new Student(id, firstName, lastName, email);
				
				// add it to the Student List
				students.add(currentStudent);
			}
			
			return students;
			
		// Step 4d:  close the JDBC objects (write method below)
		// prevents memory leaks and frees up connections when done
		} finally {
			close(myConn, myStmt, myRs);
			
		}
		
	}

// Step 4d:  Write method to close JDBC connection objects
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		
		try{
			if (myRs != null) {
				myRs.close();
			}
			if (myStmt != null) {
				myStmt.close();
			}
			if (myConn != null) {
				myConn.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

// Step 5:  Write method to add Student objects to Database
	public void addStudent(Student newStudent) throws SQLException {
		
		// Set up JDBC objects
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get DB connection
			myConn = dataSource.getConnection();
	
			// create SQL Prepared Statement for insert
			// Prepared Statements use place holders "?" to set param values
			String sql = "INSERT INTO student"
					   + " (first_name, last_name, email)"
					   + " VALUES (?, ?, ?)";
			myStmt = myConn.prepareStatement(sql);
	
			// set the param values for the student
			// index starts from 1 for prepared statements
			myStmt.setString(1, newStudent.getFirstName());
			myStmt.setString(2, newStudent.getLastName());
			myStmt.setString(3, newStudent.getEmail());
	
			// execute SQL insert
			myStmt.execute();

		}
		// clean up JDBC objects
		// replace myRs with null since we don't have one
		finally {
			close(myConn, myStmt, null);
		}		
	}

// Step 6: Create method for getStudent
	// retrieves a student based on their student ID from the DB
	public Student getStudent(String theStudentId) throws Exception {
		Student theStudent = null;
		
		// Set up JDBC objects
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int studentId;
		
		try {
			// Convert student id String to int
			studentId = Integer.parseInt(theStudentId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql statement to get selected student			
			// create prepared statement
			String sql = "SELECT * FROM student WHERE id=?";
			myStmt = myConn.prepareStatement(sql);
			
			// set param values
			myStmt.setInt(1, studentId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from ResultSet row
			if (myRs.next()) {
				String firstName = myRs.getString("first_Name");
				String lastName = myRs.getString("last_Name");
				String email = myRs.getString("email");
				
				// create a new Student object using the new studentID
				theStudent = new Student(studentId, firstName, lastName, email);
			} else {
				throw new Exception("Could not find studentID: " + studentId);
			}
			
			return theStudent;
		}
		// clean up JDBC objects
		finally {
			close(myConn, myStmt, myRs);
		}
	}


	// Step 7:  Create method for editStudent
	// edits student information on the db
	public void editStudent(Student theStudent) throws Exception{
		
		// Set up JDBC objects
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			// prepare statement
			String sql = "UPDATE student"
						+ " SET first_name=?, last_name=?, email=?"
						+ " WHERE id=?";
			myStmt = myConn.prepareStatement(sql);
				
			// set param values
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());
			myStmt.setInt(4, theStudent.getId());
						
			// execute SQL statement
			myStmt.execute();
			
		}
		finally {
			close(myConn, myStmt, null);
		}
	}
	
	
	// Step 9:  Create implementation for deleteStudent method
	// deletes a student from the db
	public void deleteStudent(String theStudentId) throws Exception{
		
		// set up JDBC objects
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert String theStudentId arg to int
			int studentId = Integer.parseInt(theStudentId);
			
			// get db connection
			myConn = dataSource.getConnection();
			
			// Create SQL delete statement
			// prepare statement
			String sql = "DELETE FROM student WHERE id=?";
			myStmt = myConn.prepareStatement(sql);
			
			// set prepared statement param values
			myStmt.setInt(1, studentId);
			
			// execute SQL statement
			myStmt.execute();
			
		}
		// close JDBC objects
		finally {
			close(myConn, myStmt, null);
		}
	}
	
	
	
}
