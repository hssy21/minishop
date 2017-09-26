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
	<div class="col-md-2"></div>
	<div class="col-md-8 col-sm-12">
			
			<div class="page-header">
			  <h3 style="text-align: center;">상품 등록 페이지</h3>
			</div>
			
			<div class="jumbotron" style="padding-top: 30px; padding-bottom: 15px;">
			<form class="form-horizontal" action="ProductAddAction.do" method="post" enctype="multipart/form-data">
				
				<div class="form-group">
				  <label class="control-label col-md-2" for="userId">카테고리:</label>
				  <div class="col-md-10">
				  	<input type="text" class="form-control" name="category1CodeFk" value="${param.ctgryName}">
				  </div>
				</div>
				<div class="form-group">
				  <label class="control-label col-md-2" for="userPassword">상품 이름:</label>
				  <div class="col-md-10">
				  	<input type="text" class="form-control" placeholder="상품 이름" name="productName">
				  </div>
				</div>
				
				<div class="form-group">
				  <label class="control-label col-md-2" for="userPassword2">상품 코드:</label>
				  <div class="col-md-10">
				  	<input type="text" class="form-control" placeholder="상품 코드" name="productCode">
				  </div>
				</div>
				
				<div class="form-group">
				  <label class="control-label col-md-2" for="userName">제조 회사:</label>
				  <div class="col-md-10">
				  	<input type="text" class="form-control" placeholder="제조 회사" name="productCompany">
				  </div>
				</div>
				
				<div class="form-group">
				  <label class="control-label col-md-2" for="userAddress">소비자 가:</label>
				  <div class="col-md-10">
				  	<input type="text" class="form-control" placeholder="숫자만 입력" name="priceRetail">
				  </div>
				</div>
				
				<div class="form-group">
				  <label class="control-label col-md-2" for="userPhone">판매 가:</label>
				  <div class="col-md-10">
				  	<input type="text" class="form-control" placeholder="숫자만 입력" name="priceSale">
				  </div>
				</div>
				
				<div class="form-group">
				  <label class="control-label col-md-2" for="userEmail">상품 수량:</label>
				  <div class="col-md-10">
				  	<input type="text" class="form-control" placeholder="숫자만 입력" name="productQnty">
				  </div>
				</div>
				
				<div class="form-group">
				  <label class="control-label col-md-3" for="userEmail">상품 표시 이미지:</label>
				  <div class="col-md-9">
				  	<input type="file" class="form-control" placeholder="파일명.jpg" name="productImages1">
				  </div>
				</div>
				
				<div class="form-group">
				  <label class="control-label col-md-3" for="userEmail">상품 상세 이미지:</label>
				  <div class="col-md-9">
				  	<input type="file" class="form-control" placeholder="파일명.jpg" name="productImages2">
				  </div>
				</div>
				
				<div class="form-group">
				  <label class="control-label col-md-2" for="userEmail">추가 설명:</label>
				  <div class="col-md-10">
				  	<input type="text" class="form-control" placeholder="상품 썸네일 하단에 표시될 요약 설명" name="productMessage">
				  </div>
				</div>
				
				<p style="text-align:center;">
					<input type="submit" class="btn btn-default" value="상품 등록">
					<!-- <a href="JoinAction.do" class="btn btn-default" role="button">회원가입</a> -->
					<a href="#" class="btn btn-default" role="button" onclick="history.back();">돌아가기</a>
				</p>
			</form>
			</div>
			
		</div>
		<div class="col-md-2"></div>
	</div>
</div> <!-- close div container  -->


</body>
</html>