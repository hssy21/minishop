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

<%-- 절대경로 생성을 위한 현재 프로젝트 Context 명 저장 --%>
<c:set var="project" value="${pageContext.request.contextPath}"/>


<div class="container">
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
	<!-- 			<li data-target="#myCarousel" data-slide-to="2"></li> -->
		</ol>
		<div class="carousel-inner">
			<div class="item active">
				<img src="${project}/resources/images/carousel/bigsale1.jpg">
			</div>
			<div class="item">
				<img src="${project}/resources/images/carousel/openevent1.jpg">
			</div>
	<!-- 			<div class="item">
					<img src="resources/images/1.jpg">
				</div> -->
		</div>
		<a class="left carousel-control" href="#myCarousel" data-slide="prev">
			<span class="glyphicon glyphicon-chevron-left"></span>
		</a>
		<a class="right carousel-control" href="#myCarousel" data-slide="next">
			<span class="glyphicon glyphicon-chevron-right"></span>
		</a>
	</div> <!-- div container end -->	



<p class="newitem-font">★★금주 NEW ITEM★★</p>

<div class="row">
	<c:forEach var="vo" items="${requestScope.newItem}" varStatus="status">
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


<br><hr>


<p class="hotitem-font">★★ 금주 HOT ITEM★★</p>

<div class="row">
	<c:forEach var="vo" items="${requestScope.hotItem}" varStatus="status">
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