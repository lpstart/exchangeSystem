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
<script src="resources/laydate/laydate.js" type="text/javascript"></script>
<title>user course</title>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span4">
			<div class="block">
				<div class="block-heading">User ${action}</div>
				<div class="block-body">
					<form action="UserManage.htm" method="post">
						<input type="hidden" name="action" value="${action}">
						<input type="hidden" name="userId" value="${muser.id}">
						<c:if test="${action == 'view'}">
							<label>University</label>
							<input placeholder="University" type="text" class="span12" name="university" value="${muser.universityname}">
							<label>Department</label>
							<input placeholder="Department" type="text" class="span12" name="department" value="${muser.departmentname}">
						</c:if>
						<label>UserName</label>
						<input placeholder="UserName" type="text" class="span12" name="username" value="${muser.username}">
						<c:if test="${action == 'add' || action == 'edit'}">
							<label>Password</label>
							<input placeholder="password" type="password" class="span12" name="password" value="${muser.password}">
						</c:if>
						<label>First Name</label>
						<input placeholder="First Name" type="text" class="span12" name="frist_name" value="${muser.frist_name}">
						<label>Last Name</label>
						<input placeholder="Last Name" type="text" class="span12" name="last_name" value="${muser.last_name}">
						<label>Birthday</label>
						<input placeholder="Birthday" type="text" class="span12" onclick="laydate()" name="birthday" value="${muser.birthday}">
						<label>Gender</label>
						<label style="border: 1px">
							<c:if test="${action == 'view'}">
								<c:if test="${muser.sex == 'false'}"><input placeholder="gender" type="text" class="span12" name="gender" value="Male"></c:if>
								<c:if test="${muser.sex == 'true'}"><input placeholder="gender" type="text" class="span12" name="gender" value="Female"></c:if>
							</c:if>
							<c:if test="${action == 'add' || action == 'edit'}">
								<input type="radio" name="gender" value="0" checked>
								<span>Male</span>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="gender" value="1">
								<span>Female</span>
							</c:if>
						</label>
						<label>Nationality</label>
						<input placeholder="Nationality" type="text" class="span12" name="nationality" value="${muser.nationality}">
						
						<c:if test="${action == 'view' && muser.role_id == 2}">
							<label>Home University</label>
							<input placeholder="University" type="text" class="span12" name="home_university" value="${muser.home_university}">
						</c:if>
						
						<label>Email Address</label>
						<input placeholder="email" type="text" class="span12" name="email" value="${muser.email}">
						
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
<script type="text/javascript">
	var action = '${action}';
	if(action == 'view'){
		$('input').attr("readonly",true);
	}
</script>
</body>
</html>