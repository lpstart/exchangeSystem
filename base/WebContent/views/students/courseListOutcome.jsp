<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" type="text/css" href="resources/lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="resources/stylesheets/theme.css">
<link rel="stylesheet" href="resources/lib/font-awesome/css/font-awesome.css">
<script src="resources/lib/jquery-1.8.1.min.js" type="text/javascript"></script>


<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="javascripts/html5.js"></script>
    <![endif]-->


<title>Course List</title>
<style type="text/css">
#line-chart {
	height: 300px;
	width: 800px;
	margin: 0px auto;
	margin-top: 1em;
}

.choose {
	width: 13px;
	height: 13px;
	line-height: 13px;
	margin-right: 10px;
	vertical-align: -2px;
	*vertical-align: middle;
	_vertical-align: 3px;
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
<h1 class="page-title">&nbsp;Course List</h1>
<div class="well">
	<table class="table">
		<thead>
			<tr>
				<th>#</th>
				<th>Course Name</th>
				<th>Department</th>
				<th>University</th>
				<th>Lecture</th>
				<th>Grade</th>
			</tr>
		</thead>
		<c:forEach items="${stuCoursesPage.list}" var="course" varStatus="index">
			<tr>
				<td>${course.seq}</td>
				<td><a href="GetInfo.htm?objectName='course'&objectID=${course.mc_id}">${course.mc_name}</a></td>
				<td><a href="GetInfo.htm?objectName='department'&objectID=${course.md_id}">${course.md_name }</a></td>
				<td><a href="GetInfo.htm?objectName='university'&objectID=${course.mu_id}">${course.mu_name }</a></td>
				<td><a href="GetInfo.htm?objectName='user'&objectID=${course.su_id}">${course.su_name }</a></td>
				<td>${course.grade }</td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">
		<ul>
			<li><span>current page:${stuCoursesPage.pageIndex} &nbsp;&nbsp;&nbsp;total record:${stuCoursesPage.totleSize}</span></li>
			<c:if test="${stuCoursesPage.pageIndex > 1}">
				<li><a href="CourseListOutcome.htm?pageIndex=${stuCoursesPage.pageIndex - 1}">Prev</a></li>
			</c:if>
			<c:forEach var="i" begin="1" end="${stuCoursesPage.totalPage}">
				<li><a href="CourseListOutcome.htm?pageIndex=${i}">${i}</a></li>
			</c:forEach>
			<c:if test="${stuCoursesPage.pageIndex < stuCoursesPage.totalPage}">
				<li><a href="CourseListOutcome.htm?pageIndex=${stuCoursesPage.pageIndex + 1}">Next</a></li>
			</c:if>
		</ul>
	</div>
</div>
