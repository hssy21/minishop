<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mini Shop</title>
</head>
<body>

<jsp:include page="/layout/header.jsp" />

<div class="container">
<div class="row">
	<div class="col-md-3"></div>
	<div class="col-md-6 col-sm-12">
			
			<div class="page-header">
			  <h3 style="text-align: center;">회원정보 수정</h3>
			</div>
			
			<div class="jumbotron" style="padding-top: 30px; padding-bottom: 15px;">
			<form action="UserModifyAction.do" method="post">
				
				<div class="form-group">
				  <label for="userId">ID:</label>
				  <input type="text" class="form-control" value="${requestScope.vo.userId}" readonly>
				</div>
				
				<div class="form-group">
				  <label for="currentUserPassword">Current Password:</label>
				  <input type="password" class="form-control" placeholder="현재 비밀번호" name="currentUserPassword">
				</div>
				
				<div class="form-group">
				  <label for="newUserPassword1">New Password:</label>
				  <input type="password" class="form-control" placeholder="새 비밀번호" name="newUserPassword1">
				</div>
				
				<div class="form-group">
				  <label for="newUserPassword2">New Password 확인:</label>
				  <input type="password" class="form-control" placeholder="새 비밀번호 확인" name="newUserPassword2">
				</div>
				
				<div class="form-group">
				  <label for="userName">Name:</label>
				  <input type="text" class="form-control" value="${requestScope.vo.userName}" name="userName">
				</div>
				
				<div class="form-group">
				  <label for="userAddress">Address:</label>
				  <input type="text" class="form-control" value="${requestScope.vo.userAddress1}" name="userAddress1">
				  <label for="userAddress2"></label>
				  <input type="text" class="form-control" value="${requestScope.vo.userAddress2}" name="userAddress2">
				</div>
				
				<div class="form-group">
				  <label for="userPhone">Phone:</label>
				  <input type="text" class="form-control" value="${requestScope.vo.userPhone}" name="userPhone">
				</div>
				
				<div class="form-group">
				  <label for="userEmail">Email:</label>
				  <input type="email" class="form-control" value="${requestScope.vo.userEmail}" name="userEmail">
				</div>
				
				<p style="text-align:center;">
					<input type="submit" class="btn btn-default" value="정보수정">
					<!-- <a href="JoinAction.do" class="btn btn-default" role="button">회원가입</a> -->
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