<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" type="text/css"
	href="resources/lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="resources/stylesheets/theme.css">
<link rel="stylesheet"
	href="resources/lib/font-awesome/css/font-awesome.css">
<script src="resources/lib/jquery-1.8.1.min.js" type="text/javascript"></script>

<title>Select your dept</title>
<style type="text/css">
#line-chart {
	height: 300px;
	width: 800px;
	margin: 0px auto;
	margin-top: 1em;
}

.brand {
	font-family: georgia, serif;
}

.brand .first {
	color: #ccc;
	font-style: italic;
}

.brand .second {
	color: #fff;
	font-weight: bold;
}
</style>
<div class="span9">
	<h1 class="page-title">Select Your Departments Firstly</h1>
	<div class="well">
		<table class="table">
			<thead>
				<tr>
					<th>id</th>
					<th>Department</th>
					<th>University</th>
					<th style="width: 26px;"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${DepartmentPage.list}" var="deps" varStatus="status">
					<tr>
						<td><a href="GetInfo.htm?objectName='department'&objectID=${deps.id}">${deps.id}</a></td>
						<td>${deps.departmentname}</td>
						<td>${deps.universityname}</td>
						<td></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
		<div class="pagination">
		<ul>
			<li><span>current page:${DepartmentPage.pageIndex} &nbsp;&nbsp;&nbsp;total record:${DepartmentPage.totleSize}</span></li>
			<c:if test="${DepartmentPage.pageIndex > 1}">
				<li><a href="SelectDepartment.htm?pageIndex=${DepartmentPage.pageIndex - 1}">Prev</a></li>
			</c:if>
			<c:forEach var="i" begin="1" end="${DepartmentPage.totalPage}">
				<li><a href="SelectDepartment.htm?pageIndex=${i}">${i}</a></li>
			</c:forEach>
			<c:if test="${DepartmentPage.pageIndex < DepartmentPage.totalPage}">
				<li><a href="SelectDepartment.htm?pageIndex=${DepartmentPage.pageIndex + 1}">Next</a></li>
			</c:if>
		</ul>
	</div>
	
	<div class="well">
		<form method="post" action="SelectedDepartment.htm">
			<table class="table">
				<tr>
					<td>Your department is :</td>
					<td><select name="departmentID">
							<c:forEach items="${DepartmentPage.list}" var="deps" varStatus="status">
								<option value="${deps.id}">${deps.id}</option>
							</c:forEach>
					</select></td>
					<td><input type="submit" value="submit" /></td>
				</tr>
			</table>
		</form>
	</div>
</div>
<script src="lib/bootstrap/js/bootstrap.js"></script>