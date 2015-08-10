<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
<title>Edit profile</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span4">
				<div class="block">
					<div class="block-heading">Edit profile</div>
					<div class="block-body">
						<form action="SaveOrUpdateStudent.htm" method="post">
							<label>UserName</label>
							<input placeholder="UserName" type="text" class="span12" name="username" value="${userInfo.username}"> 
							<label>Password</label> 
							<input placeholder="password" type="text" class="span12" name="password" value="${userInfo.password}"> 
							<label>First Name</label>
							<input placeholder="First Name" type="text" class="span12" name="frist_name" value="${userInfo.frist_name}"> 
							<label>Last Name</label> 
							<input placeholder="Last Name" type="text" class="span12" name="last_name" value="${userInfo.last_name}"> 
							<label>Gender</label>
							<c:choose>
								<c:when test="${userInfo.sex == 'false'}">
									<input type="radio" name="gender" value="0" checked>
									<span>Male</span>
											&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" name="gender" value="1">
									<span>Female</span>
								</c:when> 
								<c:otherwise>
									<input type="radio" name="gender" value="0">
									<span>Male</span>
										&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" name="gender" value="1" checked>
									<span>Female</span>
								</c:otherwise>
							</c:choose>
							<label>Nationality</label> 
							<input placeholder="Nationality" type="text" class="span12" name="nationality" value="${userInfo.nationality }"> 
							<label>Home University</label> 
							<input placeholder="University" type="text" class="span12" name="home_university" value="${userInfo.homeuniversity}"> 
							<label>Email Address</label> 
							<input readonly="readonly" type="text" class="span12" name="email" value="${userInfo.email}">
							<label style="color:red">${SubmitResult }</label>							
							<button type="submit">Submit</button>
							<div class="clearfix"></div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		//检查用户输入的表单信息，保证：输入的格式都是正确的；必须输入合法的email;保证输入的birthday是合法的。
		function check() {
			var password = $("input[name='password']").val().trim();
			if (password.length == 0) {
				alert("password is null");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>