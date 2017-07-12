<!-- Import all files from com.nguyen92.web.jdbc package -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>Student Roster</title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>


<body>


	<div id="wrapper">
		<div id="header">
			<h1>DZL Center</h1>
			<h5>For Kids Who Can't Read Good</h5>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
		
			<!-- Add new button: Add Student -->
			<input type="button" value="Add Student"
				onclick="window.location.href='addStudentForm.jsp'; return false;"
				class="addStudentButton"
			/> 
			<br/><br/>
			
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th></th>
				</tr>
					
			<c:forEach var="currentStudent" items="${studentList}">
			
				<!-- Set up a unique Edit link for each student -->
				<c:url var="editLink" value="StudentControllerServlet">
					<c:param name="action" value="LOAD" />
					<c:param name="studentId" value="${currentStudent.id}" />
				</c:url>
				
				<!-- Set up a unique Delete link for each student -->
				<c:url var="deleteLink" value="StudentControllerServlet">
					<c:param name="action" value="DELETE" />		
					<c:param name="studentId" value="${currentStudent.id}" />
				</c:url>
				
				<tr>
					<td>${currentStudent.firstName}</td>
					<td>${currentStudent.lastName}</td>
					<td>${currentStudent.email}</td>
					<td>
						<a href="${editLink}">Edit</a>
						 | 
						<a href="${deleteLink}"
							onclick="if (!(confirm('There will be no way to restore this student once deleted.  Is that okay?'))) return false"
						>Delete</a>
					 </td>
				</tr>
			</c:forEach>
									
			</table>
			
		</div>
	</div>




</body>


</html>