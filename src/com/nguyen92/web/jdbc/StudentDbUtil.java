package com.nguyen92.web.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
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
		// Step 4a:  get a connection
			myConn = dataSource.getConnection();
			
		// Step 4b:  create the SQL statement
			String sql = "SELECT * FROM student ORDER BY last_name";
			myStmt = myConn.createStatement();
			
		// Step 4c:  process the ResultSet
			myRs = myStmt.executeQuery(sql);
			
			while(myRs.next()) {
			
				// retrieve data from ResultSet row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("firstName");
				String lastName = myRs.getString("lastName");
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
	
	
	
}
