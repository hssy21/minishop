<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>

<c:set var="project" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mini Shop</title>
</head>
<body>

<jsp:include page="/layout/header.jsp" />

<div class="container">
<p style="padding-left:10px;"><a href="main.do">관리자</a> > <a href="OrderCheckView.do">주문관리</a> > 주문상세</p>

  <h3 style="text-align: center;">주문 조회 상세</h3>
  <br>

	<div class="row">
	
       <div class="col-md-offset-1 col-md-10">
       <div class="table-responsive">
             
           <table id="mytable" class="table table-custom">
                  
			<thead>
				<th style= "width: 10%;">주문 번호</th>
				<th style= "width: 10%;">상품 사진</th>
				<th style= "width: 45%;">주문 정보</th>
				<th style= "width: 20%;">주문 상태</th>
				<th style= "width: 15%;">주문 수량</th>
			</thead>
  			
  			<tbody>
			    <tr>
			    <td>${order.orderCode}</td>
			    <td><img class="product-image" src="${project}/resources/images/products/${order.productImage1}"></td>
			    <td style="text-align: left; padding-left: 30px;">
			    	상품 이름 : ${order.productName}<br>
			    	상품 코드 : ${order.productCodePk}<br>
			    	상품 가격 : <span id="priceSale">${order.priceSale}</span><br>
			    	주문 일자 : ${order.orderDate}<br>
			    </td>
			    <td>
			   	<c:choose>
					<c:when test="${order.orderState == 0}"><!-- 주문완료 후 입금 대기 -->
						입금 확인중<br>
						<img src="${project}/resources/images/orderState1.jpg" />
					</c:when>
					<c:when test="${order.orderState == 1}"><!-- 결제완료 후 배송 준비 -->
						배송 준비중<br>
						<img src="${project}/resources/images/orderState2.jpg" />
					</c:when>
					<c:when test="${order.orderState == 2}"><!-- 상품발송 후 도착 대기 -->
						현재 배송중<br>
						<img src="${project}/resources/images/orderState3.jpg" />
					</c:when>
					<c:when test="${order.orderState == 3}"><!-- 수취주소 로 상품 도착 -->
						배송 완료됨<br>
						<img src="${project}/resources/images/orderState4.jpg" />
					</c:when>
					</c:choose>
				</td>
				<td>
				
				<div class="form-group">
					<!-- <label class="control-label">구매 수량:</label> -->
					<div class="input-group">
						<span class="input-group-btn"><button type="button" class="btn btn-default" onclick="QntyDown2();">-</button></span>
						<input id="orderQnty" name="orderQnty" class="form-control" type="text" value="${order.orderCount}" min="1" max="10" style="text-align: center;">
						<span class="input-group-btn"><button type="button" class="btn btn-default" onclick="QntyUp2();">+</button></span>
					</div>
					<br>
					결제 금액 : <span id="AmountView">${order.orderAmount}</span>
				</div>
				
				</td>
			    </tr>
   			</tbody>
		</table>
	</div>
	
	
	<form action="OrderModifyAction.do" method="post">
	<input type="hidden" value="${order.orderState}" name= "orderState">
	<input type="hidden" value="${order.orderCode}" name= "orderCode">
	<input type="hidden" value="${order.orderCount}" name= "orderCount" id="orderCount">
	<input type="hidden" value="${order.orderAmount}" name= "orderAmount" id="orderAmount">
	
	<div class="row">
	<div class="col-md-6">
		<h4>주문자 정보</h4><hr>
		
				<div class="form-group">
				  <label for="buyerName">이름:</label>
				  <input type="text" class="form-control" placeholder="주문자 이름" id="buyerName" name="buyerName" value="${order.buyerName}">
				</div>
				
				<div class="form-group">
				  <label for="userAddress1">주소:</label>
				  <input type="text" class="form-control" placeholder="주문자 주소1" id="buyerAddress1" name="buyerAddress1" value="${order.buyerAddress1}">
				  <label for="userAddress2"></label>
				  <input type="text" class="form-control" placeholder="주문자 주소2" id="buyerAddress2" name="buyerAddress2" value="${order.buyerAddress2}">
				</div>
				
				<div class="form-group">
				  <label for="buyerPhone">전화번호:</label>
				  <input type="text" class="form-control" placeholder="휴대전화번호 ('-' 없이 입력해주세요.)" id="buyerPhone" name="buyerPhone" value="${order.buyerPhone}">
				</div>
				
				<div class="form-group">
				  <label for="buyerEmail">이메일:</label>
				  <input type="email" class="form-control" placeholder="이메일 주소" id="buyerEmail" name="buyerEmail" value="${order.buyerEmail}">
				</div>		
	</div>
	
	
	<div class="col-md-6">
		<h4>배송지 정보</h4><hr>
		
				<div class="form-group">
				  <label for="rcptName">이름:</label>
				  <input type="text" class="form-control" placeholder="수신자 이름" id="rcptName" name="rcptName" value="${order.rcptName}">
				</div>
				
				<div class="form-group">
				  <label for="rcptAddress1">주소:</label>
				  <input type="text" class="form-control" placeholder="수신자 주소1" id="rcptAddress1" name="rcptAddress1" value="${order.rcptAddress1}">
				  <label for="rcptAddress2"></label>
				  <input type="text" class="form-control" placeholder="수신자 주소2" id="rcptAddress2" name="rcptAddress2" value="${order.rcptAddress2}">
				</div>
				
				<div class="form-group">
				  <label for="rcptPhone">전화번호:</label>
				  <input type="text" class="form-control" placeholder="휴대전화번호 ('-' 없이 입력해주세요.)" id="rcptPhone" name="rcptPhone" value="${order.rcptPhone}">
				</div>
				
				<div class="form-group">
				  <label for="shipMessage">배송 메시지:</label>
				  <input type="text" class="form-control" placeholder="배송 시 전화주세요." name="shipMessage" value="${order.shipMessage}">
				</div>
	</div>
	</div> <!-- div row end -->
	<hr>

		<div class="row">
					<p style="text-align:center;">
						<input type="submit" class="btn btn-primary" value="수정하기">
						<a href="#" class="btn btn-default" role="button" onclick="history.back();">돌아가기</a>
					</p>
		</div> <!-- div row end -->
	
	</form>
	
	</div>	
	</div> <!-- div row end -->

		
</div> <!-- div container end -->


<script src="${pageContext.request.contextPath}/resources/js/jquery-tools.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/product/view.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.format-1.1.min.js"></script>
</body>
</html>