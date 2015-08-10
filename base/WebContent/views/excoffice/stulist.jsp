<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" type="text/css" href="resources/lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="resources/stylesheets/theme.css">
<link rel="stylesheet" href="resources/lib/font-awesome/css/font-awesome.css">
<link rel="stylesheet" href="resources/edujs/jquery.confirm.css">

<script src="resources/lib/jquery-1.8.1.min.js" type="text/javascript"></script>
<script src="resources/edujs/jquery.confirm.js" type="text/javascript"></script>
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

.black_overlay{  display: none;  position: absolute;  top: 0%;  left: 0%;  width: 100%;  height: 100%;  background-color: black;  z-index:1001;  -moz-opacity: 0.8;  opacity:.80;  filter: alpha(opacity=80);  }  
.white_content {  display: none;  position: absolute;  top: 15%;  left: 25%;  width: 50%;  height: 40%;  padding: 16px; background-color: white;  z-index:1002;  overflow: auto;  }  
</style>
<link rel="shortcut icon" href="../resources/assets/ico/favicon.ico">

<title>StudentsList</title>
</head>
<body>
	<div class="span12">
		<h1 class="page-title">Students Module View</h1>
		<div class="well">
			<table class="table">
				<thead>
					<tr>
						<th>id</th>
						<th>student_id</th>
						<th>course_id</th>
						<th>course_type</th>
						<th>score</th>
						<th>admin_status</th>
						<th>tutor_status</th>
						<th>lecturer_status</th>
						<th>department</th>
						<th>Your_Status</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${stuCoursesPage.list}" var="stuCoure" varStatus="status">
						<tr>
							<td>${stuCoure.id}</td>
							<td><a href="UserManage.htm?action=view&userId=${stuCoure.student_id}">${stuCoure.username}</a></td>
							<td><a href="CourseManage.htm?courseId=${stuCoure.course_id}&action=view">${stuCoure.coursename}</a></td>
							<c:if test="${stuCoure.department_id == stuCoure.stu_depart_id}">
								<td>Local</td>
							</c:if>
							<c:if test="${stuCoure.department_id != stuCoure.stu_depart_id}">
								<td>OutSide</td>
							</c:if>
							<td>${stuCoure.score}</td>
							
							
							<c:if test="${stuCoure.admin_status == 0}">
								<td>Undetermined </td>
							</c:if>
							<c:if test="${stuCoure.admin_status == 1}">
								<td>Agree</td>
							</c:if>
							<c:if test="${stuCoure.admin_status == 2}">
								<td>Refuse</td>
							</c:if>
							<c:if test="${stuCoure.tutor_status == 0}">
								<td>Undetermined </td>
							</c:if>
							<c:if test="${stuCoure.tutor_status == 1}">
								<td>Agree</td>
							</c:if>
							<c:if test="${stuCoure.tutor_status == 2}">
								<td>Refuse</td>
							</c:if>
							<c:if test="${stuCoure.lecturer_status == 0}">
								<td>Undetermined </td>
							</c:if>
							<c:if test="${stuCoure.lecturer_status == 1}">
								<td>Agree</td>
							</c:if>
							<c:if test="${stuCoure.lecturer_status == 2}">
								<td>Refuse</td>
							</c:if>
							
							
							<c:if test="${stuCoure.admin_id == null || stuCoure.admin_id == 0}">
								<td><button class="btn" onclick="showSelectBox('${stuCoure.id}')">Distribution</button></td>
							</c:if>
							<c:if test="${stuCoure.admin_id != null}">
								<c:forEach items="${selectList}" var="depart" varStatus="status">
									<c:if test="${depart.departmentadmin_id  == stuCoure.admin_id}">
										<td>${depart.departmentname}</td>
									</c:if>
								</c:forEach>
							</c:if>
							
							<c:if test="${stuCoure.status == 0}">
								<td><button class="btn" onclick="submitConfirm('${stuCoure.id}','${stuCoure.is_exchange}','${stuCoure.department_id}','${stuCoure.stu_depart_id}')">Confirm</button></td>
							</c:if>
							<c:if test="${stuCoure.status == 1}">
								<td>Agree</td>
							</c:if>
							<c:if test="${stuCoure.status == 2}">
								<td>Refuse</td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="pagination">
			<ul>
				<li><span>Current Page ${stuCoursesPage.pageIndex},Every Page ${stuCoursesPage.pageSize},Total ${stuCoursesPage.totleSize}</span></li>
				<c:if test="${stuCoursesPage.pageIndex > 1}">
					<li><a href="StudentList.htm?is_exchange=${param.is_exchange}&pageIndex=${stuCoursesPage.pageIndex - 1}">Prev</a></li>
				</c:if>
				<c:forEach var="i" begin="1" end="${stuCoursesPage.totalPage}">
					<li><a href="StudentList.htm?is_exchange=${param.is_exchange}&pageIndex=${i}">${i}</a></li>
				</c:forEach>
				<c:if test="${stuCoursesPage.pageIndex != stuCoursesPage.totalPage}">
					<li><a href="StudentList.htm?is_exchange=${param.is_exchange}&pageIndex=${stuCoursesPage.pageIndex + 1}">Next</a></li>
				</c:if>
			</ul>
		</div>
	</div>
	
	<div id="selectBox" class="dialog span4 white_content">
		<div class="block">
			<div class="block-heading">Distribute Department<a class="pull-right" href="javascript:void(0)" onclick="closeSelectBox()">X</a></div>
		    
		    <div class="block-body">
		    	<input type="hidden" id="stuCouId" name="stuCouId">
			    <c:forEach items="${selectList}" var="depart" varStatus="status">
					<label><input type="radio" name="departAdmin" value="${depart.departmentadmin_id}">&nbsp;&nbsp;&nbsp;&nbsp;${depart.departmentname}</label>
				</c:forEach>
			</div>
			<button class="btn pull-right" onclick="submitDepartment()">Confirm</button>
		</div>
	</div>
	<div id="selectBg" class="black_overlay"></div>
	<script src="resources/lib/bootstrap/js/bootstrap.js"></script>
	<script>
		function showSelectBox(stuCouId){
			document.getElementById('selectBox').style.display='block';
			document.getElementById('selectBg').style.display='block';
			$("#stuCouId").val(stuCouId);
		}
		
		function closeSelectBox(){
			document.getElementById('selectBox').style.display='none';
			document.getElementById('selectBg').style.display='none';
		}
		
		function submitDepartment(){
			var stuCouId = $("#stuCouId").val();
			var departAdminId = $("input[name='departAdmin']:checked").val();
			//alert(stuCouId+"   "+departAdminId);
			$.post("UpdateDepartment.htm", { "stuCouId":stuCouId,"departAdminId":departAdminId },
				function(data){
				  if(data.result == 1){
					  alert("Operation Success");
				  } else {
					  alert(data.info);
				  }
				}, "json");
			closeSelectBox();
			window.location.href="StudentList.htm?is_exchange=${param.is_exchange}&pageIndex=${param.pageIndex}";
		}
		
		function submitConfirm(stuCouId, isExchange, couDepartId, stuDepartId){
			$.confirm({
				'title'		: 'Do You Agree With The Subscribed',
				'message'	: 'If You Agree, We Will Transfer To Department Admin .If You Refuse, We Will Notice The Student! <br />Make a Choice?',
				'buttons'	: {
					'Agree'	: {
						'class'	: 'blue',
						'action': function(){PostSubmitConfirm(stuCouId, isExchange, couDepartId, stuDepartId,1)}
					},
					'Refuse'	: {
						'class'	: 'gray',
						'action': function(){PostSubmitConfirm(stuCouId, isExchange, couDepartId, stuDepartId,2)}
					}
				}
			});
		}
		function PostSubmitConfirm(stuCouId, isExchange, couDepartId, stuDepartId,status){
			$.post("excoffice/ConfirmSubmit.htm", { "stuCouId":stuCouId,"isExchange":isExchange,"couDepartId":couDepartId,"stuDepartId":stuDepartId,"status":status},
					function(data){
					  if(data.result == 1){
						  alert("Operation Success");
					  } else {
						  alert(data.info);
					  }
					}, "json");
			window.location.href="StudentList.htm?is_exchange=${param.is_exchange}&pageIndex=${param.pageIndex}";
		}
	</script>
</body>
</html>