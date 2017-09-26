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
<p style="padding-left:10px;"><a href="main.do">관리자</a> > <a href="OrderManageView.do">주문관리</a></p>

	<h3 style="text-align: center;">상품 주문관리</h3>
	<br>
	
	<div class="row">
       <div class="col-md-offset-1 col-md-10">
       <div class="table-responsive">
           <p class="lead">쇼핑몰 결제 완료 건</p>  
           <table id="mytable" class="table table-custom">
                  
			<thead>
				<th style= "width: 30%;background-color: #cccccc;">상품 명</th>
				<th style= "width: 20%;background-color: #cccccc;">주문 회원</th>
				<th style= "width: 20%;background-color: #cccccc;">판매가</th>
				<th style= "width: 10%;background-color: #cccccc;">수량</th>
				<th style= "width: 20%;background-color: #cccccc;text-align: left;">주문금액(배송비포함)</th>
			</thead>
  			
  			<c:forEach var="vo2" items="${requestScope.voList2}">	
  			<tbody>
			    <tr>
			    <td><a href="OrderDetailView.do?orderCode=${vo2.orderCode}">${vo2.productName}</a></td>
			    <td>${vo2.buyerName}</td>
				<td>${vo2.priceSale}</td>
			    <td>${vo2.orderCount}</td>
			    <td style="text-align: left;">${vo2.orderAmount}원</td>
			    </tr>
   			</tbody>
   			</c:forEach>
   			
		</table>
	</div>
	</div>
	</div> <!-- end div row -->
	
		
	<br><br>
	

	<div class="row">
       <div class="col-md-offset-1 col-md-10">
       <div class="table-responsive">
           <p class="lead">쇼핑몰 결제 미완 건</p>  
           <table id="mytable" class="table table-custom">
                  
			<thead>
				<th style= "width: 30%;background-color: #cccccc;">상품 명</th>
				<th style= "width: 20%;background-color: #cccccc;">주문 회원</th>
				<th style= "width: 20%;background-color: #cccccc;">판매가</th>
				<th style= "width: 10%;background-color: #cccccc;">수량</th>
				<th style= "width: 20%;background-color: #cccccc;text-align: left;">주문금액(배송비포함)</th>
			</thead>
  			
  			<c:forEach var="vo" items="${requestScope.voList}">	
  			<tbody>
			    <tr>
			    <td><a href="OrderDetailView.do?orderCode=${vo.orderCode}">${vo.productName}</a></td>
			    <td>${vo.buyerName}</td>
				<td>${vo.priceSale}</td>
			    <td>${vo.orderCount}</td>
			    <td style="text-align: left;">${vo.orderAmount}원</td>
			    </tr>
   			</tbody>
   			</c:forEach>
   			
		</table>
	</div>
	</div>
	</div> <!-- end div row -->
</div>


</body>
</html>