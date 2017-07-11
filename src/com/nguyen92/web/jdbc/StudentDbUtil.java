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
}
