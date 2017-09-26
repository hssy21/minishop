<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>

<c:set var="project" value="${pageContext.request.contextPath}"/>
<c:set var="vo" value="${requestScope.vo}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mini Shop</title>
</head>
<body>

<jsp:include page="/layout/header.jsp" />

<div class="container">
<p style="padding-left:10px;"><a href="main.do">홈</a> > <a href="ProductList.do?category_code=all">상품보기</a> > 결제하기</p>

	<h3 style="text-align: center;">상품 주문하기</h3>
	<br>
	
	<!-- 상품 정보 -->
	<c:set var="vo" value="${requestScope.vo}" />

	<div class="row">
       <div class="col-md-12">
       <div class="table-responsive">
             
           <table id="mytable" class="table table-custom">
                  
			<thead>
				<th style= "width: 10%;">상품 사진</th>
				<th style= "width: 50%;">주문 정보 (상품명/설명/옵션)</th>
				<th style= "width: 10%;">판매가</th>
				<th style= "width: 10%;">수량</th>
				<th style= "width: 20%; text-align: left;">주문금액(배송비포함)</th>
			</thead>
  				
  			<tbody>
			    <tr>
			    <td><img class="product-image" src="${project}/resources/images/products/${vo.productImage1}"></td>
			    <td style="text-align: left;">상품 이름 : ${vo.productName}<br>상품 설명 : ${vo.productMessage}<br><br></td>
				<td>${vo.priceSale}</td>
			    <td>${requestScope.orderQnty}</td>
			    <td style="text-align: left;">판매가 : ${vo.priceSale}원<br>배송비 : 5000원<br>총 결제금액 : ${vo.priceSale*orderQnty+5000}</td>
			    </tr>
   			</tbody>
		</table>
	</div>
	</div>
	</div> <!-- end div row -->
	
	<br>
	
	<!-- 사용자 정보 -->
	<c:set var="vo2" value="${requestScope.vo2}" />
	
	<form action="ProductOrderAction.do" method="post">
	<input type="hidden" value="${vo.priceSale}" name= "priceSale">
	<input type="hidden" value="${requestScope.orderQnty}" name= "orderCount">
	<input type="hidden" value="${param.productCode}" name= "productCode">
	
	<div class="row">
	<div class="col-md-6">
		<h4>주문자 정보</h4><hr>
		
				<div class="form-group">
				  <label for="buyerName">이름:</label>
				  <input type="text" class="form-control" placeholder="주문자 이름" id="buyerName" name="buyerName" value="${vo2.userName}">
				</div>
				
				<div class="form-group">
				  <label for="userAddress1">주소:</label>
				  <input type="text" class="form-control" placeholder="주문자 주소1" id="buyerAddress1" name="buyerAddress1" value="${vo2.userAddress1}">
				  <label for="userAddress2"></label>
				  <input type="text" class="form-control" placeholder="주문자 주소2" id="buyerAddress2" name="buyerAddress2" value="${vo2.userAddress2}">
				</div>
				
				<div class="form-group">
				  <label for="buyerPhone">전화번호:</label>
				  <input type="text" class="form-control" placeholder="휴대전화번호 ('-' 없이 입력해주세요.)" id="buyerPhone" name="buyerPhone" value="${vo2.userPhone}">
				</div>
				
				<div class="form-group">
				  <label for="buyerEmail">이메일:</label>
				  <input type="email" class="form-control" placeholder="이메일 주소" id="buyerEmail" name="buyerEmail" value="${vo2.userEmail}">
				</div>		
	</div>
	
	
	<div class="col-md-6">
		<h4>배송지 정보<small> ( 주문자 정보와 동일 <input type="checkbox" onclick="sameAddress();"/> )</small></h4><hr>
		
				<div class="form-group">
				  <label for="rcptName">이름:</label>
				  <input type="text" class="form-control" placeholder="수신자 이름" id="rcptName" name="rcptName">
				</div>
				
				<div class="form-group">
				  <label for="rcptAddress1">주소:</label>
				  <input type="text" class="form-control" placeholder="수신자 주소1" id="rcptAddress1" name="rcptAddress1">
				  <label for="rcptAddress2"></label>
				  <input type="text" class="form-control" placeholder="수신자 주소2" id="rcptAddress2" name="rcptAddress2">
				</div>
				
				<div class="form-group">
				  <label for="rcptPhone">전화번호:</label>
				  <input type="text" class="form-control" placeholder="휴대전화번호 ('-' 없이 입력해주세요.)" id="rcptPhone" name="rcptPhone">
				</div>
				
				<div class="form-group">
				  <label for="shipMessage">배송 메시지:</label>
				  <input type="text" class="form-control" placeholder="배송 시 전화주세요." name="shipMessage">
				</div>
	</div>
	</div> <!-- div row end -->
	<hr>
	<div class="row">
				<p style="text-align:center;">
					<input type="submit" class="btn btn-primary" value="주문하기">
					<a href="#" class="btn btn-default" role="button" onclick="history.back();">돌아가기</a>
				</p>
	</div> <!-- div row end -->
	
	</form>
</div> <!-- div container end -->


<script src="${pageContext.request.contextPath}/resources/js/product/view.js"></script>
</body>
</html>