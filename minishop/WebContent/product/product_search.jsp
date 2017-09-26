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
	<p style="padding-left:10px;"><a href="main.do">홈</a> > <a href="ProductSearch.do">상품검색</a></p>
	
	<br>
		<form class="form-horizontal" action="ProductSearch.do">
		
			<div class="form-group">
				<label class="control-label col-md-2" for="category">카테고리: </label>
				<div class="col-md-2">
				<select class="form-control" id="category" name ="ctgryName">
					<option>모두</option>
					<option>동물인형</option>
					<option>식물인형</option>
					<option>곤충인형</option>
					<option>음식인형</option>
					<option>캐릭터인형</option>
				</select>
				</div>
				<div class="col-md-2"><input type="text" class="form-control" name="search" placeholder="Search..."></div>
				<div class="col-md-4">
					<input type="submit" class="btn btn-primary" value="검색하기">
				</div>
			</div>
		
		</form>
		
		<br><br><br>
		
		<div class="page-header">
		  <h3 style="text-align: center;">상품 검색 결과</h3>
		</div>
		
		<div class="row">
		<c:forEach var="vo" items="${voList}" varStatus="status">
		<c:if test="${not status.first and (status.index) % 3 == 0}">
			</div>
			<div class="row">
		</c:if>
		
			<div class="col-xs-6 col-sm-4">
	            <div class="thumbnail">
	                <img class="img-thumbnail" src="${pageContext.request.contextPath}/resources/images/products/${vo.productImage1}" alt="${vo.productImage1}">
	                <div class="add-to-cart">
	                      <a href="#" class="glyphicon glyphicon-shopping-cart pull-right" data-toggle="tooltip" data-placement="top" title="Add to cart"></a>
	                </div>
	
	                <div class="caption">
	                  
	                  <h4>상품 이름 : <a href="ProductView.do?productCode=${vo.productCode}">${vo.productName}</a></h4>
	                  <h4>상품 설명 : ${vo.productMessage}</h4>
	                  <h4>가격: &#8361;<del>${vo.priceRetail}</del> → &#8361;${vo.priceSale}</h4>
	            	  <h4>상품 수량 : ${vo.productQnty}</h4>
	                  <div class="ratings">
	                      <p class="pull-right"><a href="#">25 reviews</a></p>
	                      <p><span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span></p>
	                  </div>
	                </div>
	            </div>
	    	</div>
		</c:forEach>
		</div> 
</div>


</body>
</html>