<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mini Shop</title>
</head>
<body onload="$('#userId').focus();">

<jsp:include page="/layout/header.jsp" />

<div class="container">
<div class="row">
	<div class="col-md-3"></div>
	<div class="col-md-6 col-sm-12">
			
			<div class="page-header">
			  <h3 style="text-align: center;">회원 로그인</h3>
			</div>
			
			<div class="jumbotron" style="padding-top: 30px; padding-bottom: 15px;">
			<form action="LoginAction.do" method="post">
				<div class="form-group">
				  <label for="userId">ID:</label>
				  <input type="text" class="form-control" placeholder="아이디" name="userId" id="userId">
				</div>
				<div class="form-group">
				  <label for="userPassword">Password:</label>
				  <input type="password" class="form-control" placeholder="비밀번호" name="userPassword">
				</div>
				<p style="text-align:center;">
					<!-- <a href="LoginAction.do" class="btn btn-default" role="button">로그인</a> -->
					<input type="submit" class="btn btn-primary" value="로그인">
					<a href="#" class="btn btn-default" role="button" onclick="history.back();">돌아가기</a>
				</p>
			</form>
			</div>
			
		</div>
		<div class="col-md-3"></div>
	</div>
</div> <!-- close div container  -->

</body>
</html>