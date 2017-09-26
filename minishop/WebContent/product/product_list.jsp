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

<!-- <h1> 카테고리 product_list.jsp </h1> -->

<div class="container">
<p style="padding-left:10px;"><a href="main.do">홈</a> > <a href="ProductList.do?category_code=all">상품보기</a></p>

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
</div> <!-- div container end -->
</body>
</html>