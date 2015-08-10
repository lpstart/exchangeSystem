<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">

<link rel="stylesheet" type="text/css" href="resources/lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="resources/stylesheets/theme.css">
<link rel="stylesheet" href="resources/lib/font-awesome/css/font-awesome.css">

<script src="resources/lib/jquery-1.8.1.min.js" type="text/javascript"></script>

<title>left</title>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span3">
			<div class="sidebar-nav">
				<c:forEach items="${menus}" var="menu" varStatus="status">
					<div class="nav-header" data-toggle="collapse" data-target="#main-menu${status.index }">
						<i class="icon-dashboard"></i>${menu.menuname}<span class="label label-info">${menu.childrenSize}</span>
					</div>
					<ul id="main-menu${status.index }" class="nav nav-list collapse in">
						<c:forEach items="${menu.children}" var="cMenu" varStatus="status">
							<li>
								<a href="${cMenu.location}" target="content">
									<span>${cMenu.menuname}</span>
								</a>
							</li>
						</c:forEach>
					</ul>
				</c:forEach>
			</div>
		</div>
	</div>
</div>
<script>
	$(function(){
		$('.nav li a').click(function(){
	        $('.nav li').removeClass('active');
	        $(this).parent().addClass('active');
	   	})
	})
</script>
<script src="resources/lib/bootstrap/js/bootstrap.js"></script>
