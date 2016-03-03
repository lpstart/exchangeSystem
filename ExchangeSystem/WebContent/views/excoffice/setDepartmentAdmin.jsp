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
<title>set department admin</title>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span4">
			<div class="block">
				<div class="block-heading">set department admin</div>
				<div class="block-body">
					<form action="SaveOrUpdateDepadmin.htm" method="post" id="myForm">
						<input type="hidden" name="action" value="${action}">
						<input type="hidden" name="departmentId" value="${departmentId}">
						<input type="hidden" name="userId" value="${muser.id}">
						<label>UserName</label>
						<input placeholder="UserName" type="text" class="span12" name="username" value="${muser.username}">
						<c:if test="${action == 'add' || action == 'edit'}">
							<label>Password</label>
							<input placeholder="password" type="password" class="span12" name="password" value="${muser.password}">
						</c:if>
						<label>First Name</label>
						<input placeholder="First Name" type="text" class="span12" name="first_name" value="${muser.first_name}">
						<label>Last Name</label>
						<input placeholder="Last Name" type="text" class="span12" name="last_name" value="${muser.last_name}">
						<label>Gender</label>
						<label style="border: 1px">
							<c:if test="${action == 'view'}">
								<c:if test="${muser.sex == 'false'}"><input placeholder="gender" type="text" class="span12" name="gender" value="Male"></c:if>
								<c:if test="${muser.sex == 'true'}"><input placeholder="gender" type="text" class="span12" name="gender" value="Female"></c:if>
							</c:if>
							<c:if test="${action == 'add'}">
								<input type="radio" name="gender" value="0" checked>
								<span>Male</span>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="gender" value="1">
								<span>Female</span>
							</c:if>
							<c:if test="${action == 'edit'}">
								<c:choose>
									 <c:when test="${muser.sex == 'false'}">
										<input type="radio" name="gender" value="0" checked>
										<span>Male</span>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" name="gender" value="1">
										<span>Female</span>
							      	 </c:when>
							      	 <c:otherwise>
										<input type="radio" name="gender" value="0" >
										<span>Male</span>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" name="gender" value="1" checked>
										<span>Female</span>
      								 </c:otherwise>
								</c:choose>
							</c:if>
						</label>
						<label>Email Address</label>
						<input placeholder="email" type="text" class="span12" name="email" value="${muser.email}">
						
						<c:if test="${action == 'add'}">
							<button type="button" onclick="setDepAdmin()" class="btn btn-primary pull-right">Add</button>
						</c:if>
						<c:if test="${action == 'edit'}">
							<button type="button" onclick="setDepAdmin()" class="btn btn-primary pull-right">Submit</button>
						</c:if>
						
						<div class="clearfix"></div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	function checkemail() {
		var email = $("input[name='email']").val().trim();
		if (email.length == 0) {
			alert("email is null");
			return false;
		}
		if (email.length != 0) {
			reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
			if (!reg.test(email)) {
				alert("Sorry! Your email is invalid!");//请将“字符串类型”要换成你要验证的那个属性名称！    
				return false;
			} else {
				$.ajax({
					type : 'POST',
					url : '/base/CheckEmailUnique.htm',
					data : 'email=' + email,
					dataType : 'JSON',
					timeout : 5000,
					async : true,
					success : function(responseJson) {
						if (responseJson.success == "success") {
								return true;
						} else {
							alert("This email is existed!");
							return false;
						}
					},
				});
			}
		}
	}
	//检查用户输入的表单信息，保证：输入的格式都是正确的；必须输入合法的email;保证输入的birthday是合法的。
		function check() {
			var password = $("input[name='password']").val().trim();
			var email = $("input[name='email']").val().trim();
			if (password.length == 0) {
				alert("password is null");
				return false;
			}
			if (email.length == 0) {
				alert("email is null");
				return false;
			}
			return true;
		}

		//用ajax技术来完成用户的注册
		function setDepAdmin() {
			if (check()) {
				//如果用户的输入信息都合格。
				$.ajax({
						type : 'POST',
						url : '/base/SaveOrUpdateDepadmin.htm',
						data : $('#myForm').serialize(),
						dataType : "JSON",
						timeout : 5000,
						async : true,
						success : function(responseJson) {
							if (responseJson.success == "success") {
								window.location.href="DepartmentList.htm";
							} else {
								alert("This email is existed!");
							}
								
						}
					});
			}
		}
	//判断输入的EMAIL格式是否正确    
	if("${action}"=="add"){
		$("input[name='email']").blur(checkemail);
	}else{
		$("input[name='email']").attr("readonly",true);
	}
</script>
</body>
</html>