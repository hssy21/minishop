<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script>
function init(){
	/* NumberWithComma(salesPrice); */
};
</script>

<title>Mini Shop</title>
</head>

<script type="text/javascript">
  function mySubmit(index) {
    if (index == 1) { document.myform.action='user/cart.jsp'; }
    if (index == 2) { document.myform.action='ProductOrderView.do'; }
    document.myform.submit();
  }
</script>


<body onload="init();">

<jsp:include page="/layout/header.jsp" />

<c:set var="vo" value="${requestScope.vo}"></c:set>

<div class="container">
<p style="padding-left:10px;"><a href="main.do">홈</a> > <a href="ProductList.do?category_code=all">상품보기</a> > ${vo.productName}</p>

      <section>
        <div class="row">
          <div class="col-md-offset-2 col-md-10">
            
            <div class="row product-info">
            
              <div class="col-md-4 thumbnail">
                <img src="${pageContext.request.contextPath}/resources/images/products/${vo.productImage1}">
              </div>
              
              <div class="col-md-6 product-info-right">
          
                <h2>상품 명 : ${vo.productName}</h2>
                
               <div class="panel panel-default product-info-detail">
			    <div class="panel-heading" role="tab" id="headingOne">
			      <h4 class="panel-title">
			        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#product-info-panel" aria-expanded="false" aria-controls="product-info-panel">
			          	<b>상품 정보</b> (펼쳐보기:클릭)
			        </a>
			      </h4>
			    </div>
			    <div id="product-info-panel" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
			      <div class="panel-body">
					<dl>
					  <dt>상품 코드: </dt>
					  <dd>${vo.productCode}</dd>
					  <dt>제조 회사: </dt>
					  <dd>${vo.productCompany}</dd>
					  <dt>원산지: </dt>
					  <dd>대한 민국</dd>
					  <dt>소비자 가: </dt>
					  <dd>${vo.priceRetail} 원</dd>
					  <dt style="color:#aa0b0b;">판매 가: </dt>
					  <dd style="color:#aa0b0b;"id="salesPrice">${vo.priceSale} 원</dd>
					  <dt>제품 수량: </dt>
					  <dd>${vo.productQnty}</dd>
					  <dt>적립금: </dt>
					  <dd>10원</dd>
					</dl>
			      </div>
			    </div>
			   </div> <!-- end panel -->

                <!-- <p>구매 수량: -->
                <form name="myform" method="post">
					<div class="form-group">
						<label class="control-label">구매 수량:</label>
						<div class="input-group">
							<span class="input-group-btn"><button type="button" class="btn btn-default" onclick="QntyDown();">-</button></span>
							<input id="orderQnty" name="orderQnty" class="form-control" type="text" value="1" min="1" max="10" style="text-align: center;">
							<span class="input-group-btn"><button type="button" class="btn btn-default" onclick="QntyUp();">+</button></span>
						</div>
					</div>
					
					<input type="hidden" name="productCode" value="${vo.productCode}">
					
					<div class="form-group" style="text-align:right;">
						<a href="#" class="btn btn-success" onclick="mySubmit(1)">장바구니</a>
               			<a href="#" class="btn btn-success" onclick="mySubmit(2)">결제하기</a>
               		</div>
                </form>
                
                <div class="well product-amount">   	
                	<dl>
					  <dt>총 결제금액: </dt>
					  <dd id="prodTtlPayAmt">${vo.priceSale} 원</dd>
					</dl>
                </div>
                
              </div>
         	 </div>

          </div>
          
        </div> <!-- div row end -->
      </section>
      
		<br><hr><br>
		
		<section>
			<div class="row product-detail">
				<div class="col-md-offset-2 col-md-10">
					
					<img src="${pageContext.request.contextPath}/resources/images/products/${vo.productImage2}" />
				
				
				</div>
			</div> <!-- div row end -->
		</section>

</div> <!-- /container -->

<script src="${pageContext.request.contextPath}/resources/js/jquery-tools.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/product/view.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.format-1.1.min.js"></script>
</body>
</html>