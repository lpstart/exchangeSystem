<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="resources/lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="resources/stylesheets/theme.css">
<link rel="stylesheet" href="resources/lib/font-awesome/css/font-awesome.css">

<script src="resources/lib/jquery-1.8.1.min.js" type="text/javascript"></script>
<title>manage course</title>
</head>
<body>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span4">
			<div class="block">
				<div class="block-heading">Course ${action}</div>
				<div class="block-body">
					<form action="CourseManage.htm" method="post">
						<input type="hidden" name="action" value="${action}">
						<input type="hidden" name="courseid" value="${course.id}">
						<label>CourseName</label>
						<input type="text" class="span12" name="coursename" value="${course.coursename}">
						<label>CourseInfo</label>
						<input type="text" class="span12" name="courseinfo" value="${course.courseinfo}">
						<c:if test="${action == 'view'}">
							<label>lecturerName</label>
							<input type="text" class="span12" name="user" value="${course.username}">
						</c:if>
						<label>Deadline</label>
						<input type="text" class="span12" name="deadline" value="${course.deadline}">
						
						
						<c:if test="${action == 'add'}">
							<button type="submit" class="btn btn-primary pull-right">Add</button>
						</c:if>
						<c:if test="${action == 'edit'}">
							<button type="submit" class="btn btn-primary pull-right">Submit</button>
						</c:if>
						<div class="clearfix"></div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="resources/lib/bootstrap/js/bootstrap.js"></script>
</body>
<script type="text/javascript">
	var action = '${action}';
	if(action == 'view'){
		$('input').attr("readonly",true);
	}
</script>
</html>