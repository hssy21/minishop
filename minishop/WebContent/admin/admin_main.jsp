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
<p style="padding-left:10px;"><a href="AdminMainView.do">관리자</a> > 대시보드</p>

<div class="row">
	<div class="col-md-3"></div>
	<div class="col-md-6 col-sm-12">
			
			<div class="page-header">
			  <h3 style="text-align: center;">대시보드</h3>
			</div>
			
			<div class="jumbotron" style="padding-top: 30px; padding-bottom: 15px;">
			<form action="AdminMainView.do" method="post">
				<!-- 회원 count -->
				<div class="form-group">
				  <label for="UserCount">회원 수:</label>
				  <input type="text" class="form-control"  name="UserCount" readonly value="${requestScope.UserCount}">
				</div>
				<!-- 상품 count -->
				<div class="form-group">
				  <label for="ProductCount">등록 상품 수:</label>
				  <input type="text" class="form-control"  name="ProductCount" readonly value="${requestScope.ProductCount}">
				</div>
				<!-- 공지 count -->
				<div class="form-group">
				  <label for="NoticeCount">공지사항 글 수:</label>
				  <input type="text" class="form-control"  name="NoticeCount" readonly value="${requestScope.NoticeCount}">
				</div>
				<!-- Q&A count -->
				<div class="form-group">
				  <label for="QnaCount">Q&A 글 수:</label>
				  <input type="text" class="form-control"  name="QnaCount" readonly value="${requestScope.QnaCount}">
				</div>
				<!-- Q&A count -->
				<div class="form-group">
				  <label for="delCount">Q&A 삭제된 글 수:</label>
				  <input type="text" class="form-control"  name="delCount" readonly value="${requestScope.delCount}">
				</div>
				
				<p style="text-align:center;">
					<a href="#" class="btn btn-default" role="button" onclick="window.location.reload()">새로고침</a>
				</p>
			</form>
			</div>
			
		</div>
		<div class="col-md-3"></div>
	</div>
</div> <!-- close div container  -->


</body>
</html>