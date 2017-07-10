<!-- Import all files from com.nguyen92.web.jdbc package -->
<%@ page import="java.util.*, com.nguyen92.web.jdbc.*" %>


<!DOCTYPE html>
<html>
<head><title>Student Roster</title></head>

<%
	// get the students from the request object (sent by servlet)
	// attribute name found in StudentControllServlet in listStudents()
	List<Student> studentList = (List<Student>) request.getAttribute("studentList");


%>



<body>




</body>


</html>