<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>sign up</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet" type="text/css" href="resources/lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="resources/lib/bootstrap/css/bootstrap-responsive.css">
<link rel="stylesheet" type="text/css" href="resources/stylesheets/theme.css">
<link rel="stylesheet" href="resources/lib/font-awesome/css/font-awesome.css">
<script src="resources/lib/jquery-1.8.1.min.js" type="text/javascript"></script>
<script src="resources/lib/laydate/laydate.js" type="text/javascript"></script>
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
</head>
<body>
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<ul class="nav pull-right">
				</ul>
				<a class="brand"> <span class="first">Exchange System</span>
				</a>
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span4 offset4 dialog">
				<div class="block">
					<div class="block-heading">Student Sign Up</div>
					<div class="block-body">
						<form id="regForm">
							<label>UserName</label> <input placeholder="UserName" type="text" class="span12" name="username"> <label>Password</label>
							<input placeholder="password" type="password" class="span12" name="password"> <label>First Name</label> <input
								placeholder="First Name" type="text" class="span12" name="frist_name"> <label>Last Name</label> <input
								placeholder="Last Name" type="text" class="span12" name="last_name"> <label>Birthday</label> <input
								placeholder="Birthday" type="text" class="span12" onclick="laydate()" name="birthday"> <label>Gender</label> <label
								style="border: 1px"> <input type="radio" name="gender" value="0" checked> <span>man</span>
								&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="gender" value="1"> <span>woman</span>
							</label> <label>Nationality</label> <input placeholder="Nationality" type="text" class="span12" name="nationality"> <label>Home
								University</label> <input placeholder="University" type="text" class="span12" name="home_university"> <label>Email
								Address</label> <input placeholder="email" type="text" class="span12" name="email">
							<button type="button" class="btn btn-primary pull-right" onclick="register()">Sign Up!</button>
							<!-- <label class="remember-me"><input type="checkbox"> I agree with the <a href="terms-and-conditions.html">Terms and Conditions</a></label> -->
							<a href="login.htm" class="btn btn-primary pull-left">have Account,go to Login</a>
							<div class="clearfix"></div>
						</form>
					</div>
				</div>
				<!-- <p><a href="privacy-policy.html">Privacy Policy</a></p> -->
			</div>
		</div>
	</div>

	<!-- Le javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="resources/lib/bootstrap/js/bootstrap.js"></script>
	<script>
		function checkemail() {
			var email = $("input[name='email']").val().trim();
			if (email.length != 0) {
				var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
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
			var birthday = $("input[name='birthday']").val().trim();
			if (password.length == 0) {
				alert("password is null");
				return false;
			}
			if (email.length == 0) {
				alert("email is null");
				return false;
			}
			//验证邮箱格式
			var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
			if (!reg.test(email)) {
				alert("Sorry! Your email is invalid!");    
				return false;
			}
			var datereg = /^(\d{4})-(\d{2})-(\d{2})$/;
			if (birthday.length > 0
					&& (!(datereg.test(birthday) && RegExp.$2 <= 12 && RegExp.$3 <= 31))) {
				alert("Invalid birthday!");
				return false;
			}
			return true;
		}

		//用ajax技术来完成用户的注册
		function register() {
			if (check()) {
				//如果用户的输入信息都合格。
				$
						.ajax({
							type : 'POST',
							url : '/base/SignUp.htm',
							data : $('#regForm').serialize(),
							dataType : "JSON",
							timeout : 5000,
							async : true,
							success : function(responseJson) {
								if (responseJson.length > 0) {
									var msg = '';
									for (var i = 0, len = responseJson.length; i < len; i++) {
										for ( var key in responseJson[i]) {
											msg = msg + key + ":"
													+ responseJson[i][key]
													+ '\r\n'
										}
									}
									alert(msg);
								}
							}
						})
			}
		}

		//判断输入的EMAIL格式是否正确    
		$("input[name='email']").blur(checkemail);
	</script>

</body>
</html>


