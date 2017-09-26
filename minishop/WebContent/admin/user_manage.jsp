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
<br>

<div class="container">
<p style="padding-left:10px;"><a href="main.do">홈</a> > <a href="admin_main.jsp">관리자 메인 페이지</a></p>
		
	<div class="row">
	<div class="col-md-offset-1 col-md-10 col-sm-12">
	<h3 style="text-align:center;"> 쇼핑몰 회원 리스트 </h3>
		<table class="table " style="text-align: center; border: 1px solid #dddddd;">
			<thead>
				<tr>
					<th style="width: 25%; background-color: #eeeeee; text-align: center;">아이디</th>
					<th style="width: 25%; background-color: #eeeeee; text-align: center;">이름</th>
					<th style="width: 25%; background-color: #eeeeee; text-align: center;">전화번호</th>
					<th style="width: 25%; background-color: #eeeeee; text-align: center;">이메일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="vo" items="${requestScope.voList}">
					<tr>
						<td>
							<a href="UserManageModifyView.do?userId=${vo.userId}">${vo.userId}</a>
						</td>
						<td>${vo.userName}</td>
						<td>${vo.userPhone}</td>
						<td>${vo.userEmail}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<!-- 이전, 다음 버튼 생성 -->
		
	</div> <!-- end div col-md-10 col-sm-12 -->
	
	<div class="col-md-1"></div>
	
	</div> <!-- end div row -->
</div> <!-- end div container -->

</body>
</html>