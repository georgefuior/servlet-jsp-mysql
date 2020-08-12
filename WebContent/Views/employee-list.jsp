<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link href = "https://unpkg.com/bootstrap@4.1.0/dist/css/bootstrap.min.css" rel = "stylesheet"/>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.10.21/datatables.min.css"/>
<body>

<%
	String email = (String)session.getAttribute("email");
	if(email==null){
		response.sendRedirect("index.jsp");
	}
%>
	<div class = "container">
	<p>${message} </p>
	<div class="float-right">
			<a href="${pageContext.request.contextPath}/logout.jsp">Logout</a>
		</div>
	<button class = "btn btn-primary" onclick="window.location.href='Views/employee-add.jsp'">Add employee</button>
	<table border = 1 class = "table table-striped table-bordered" id = "datatable">
	<thead>
		<tr class = "thead-dark">
			<td>Name</td>
			<td>Department</td>
			<td>Date of birth</td>
			<td>Actions </td>
		</tr>
		</thead>
		
		<tbody>
		<c:forEach items="${list}" var="employee">
			<tr>
				<td>${employee.name}</td>
				<td>${employee.department}</td>
				<td>${employee.dob}</td>
				<td>
				<a href = "${pageContext.request.contextPath}/EmployeeController?action=EDIT&id=${employee.id}">Edit</a> |
				
				<a href = "${pageContext.request.contextPath}/EmployeeController?action=DELETE&id=${employee.id}">Delete</a></td>
				
			</tr>
		</c:forEach>
		</tbody>

	</table></div>
	
<script src = https://unpkg.com/jquery@3.3.1/dist/jquery.min.js></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/bs4/dt-1.10.21/datatables.min.js"></script>
<script>
$(document).ready(function(){
	$('#datatable').DataTable();
})</script>
</body>
</html>