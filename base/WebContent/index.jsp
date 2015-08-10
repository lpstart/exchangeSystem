<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path =  request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +  path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base href="<%=basePath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home</title>
<frameset rows="60,*" frameborder="no">
	<frame src="HomeTop.htm"></frame>
	<frameset cols="300,*">
		<frame src="HomeLeft.htm" scrolling="auto"></frame>
		<frame src="HomeWelcome.htm" name="content"></frame>
	</frameset>
</frameset>
</html>