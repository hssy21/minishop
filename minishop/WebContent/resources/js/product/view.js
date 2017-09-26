//주문 수량 증가 버튼
function QntyUp() {
	var qnt= parseInt($("#orderQnty").val());
	qnt+= 1;
	$("#orderQnty").val(qnt);
	UpdateTtlProductAmount();
}

//주문 수량 감소 버튼
function QntyDown() {
	var qnt= parseInt($("#orderQnty").val());
	if (qnt<=1) return;
	qnt-= 1;
	$("#orderQnty").val(qnt);
	UpdateTtlProductAmount();
	
}

//총금액 계산, 총 결제금액 계산 추가
function UpdateTtlProductAmount() {
    var salesPrice = $.toInt($("#salesPrice").text()); // 유료회원가/일반회원가 추가
    var $prodTtlPayAmt = $("#prodTtlPayAmt");       // 총 결제금액 추가
    var qnt = $("#orderQnty").toInt();	//제품 수량
    var prodTtlPayAmt = qnt * salesPrice;
    
    $prodTtlPayAmt.text($.format.number(prodTtlPayAmt, "#,###") + "원");
}

/*function NumberWithComma(numElement){
	alert(numElement.html);
}*/


//관리자 상품 관리 주문 수량 증가 버튼
function QntyUp2() {
	var qnt= $("#orderQnty").toInt();
	var priceSale= $.toInt($("#priceSale").text());
	var curOrderAmount= $("#orderAmount").toInt();
	qnt+= 1;
	$("#orderQnty").val(qnt);	//보이는 주문수량
	$("#orderCount").val(qnt);	//form의 hidden 주문수량
	$("#orderAmount").val(curOrderAmount + priceSale);	//form의 hidden 총액
	$("#AmountView").text($("#orderAmount").val())		//보이는 총액
}

//관리자 상품 관리 주문 수량 감소 버튼
function QntyDown2() {
	var qnt= parseInt($("#orderQnty").val());
	var priceSale= $.toInt($("#priceSale").text());
	var curOrderAmount= $("#orderAmount").toInt();
	if (qnt<=1) return;
	qnt-= 1;
	$("#orderQnty").val(qnt);
	$("#orderCount").val(qnt);
	$("#orderAmount").val(curOrderAmount - priceSale);	//form의 hidden 총액
	$("#AmountView").text($("#orderAmount").val())		//보이는 총액	
}



//[주문 페이지] 주문지 정보와 동일한 배송지 정보 체크 액션
function sameAddress(){
	/*alert($("#buyerName").val());*/
	$("#rcptName").val($("#buyerName").val());
	$("#rcptAddress1").val($("#buyerAddress1").val());
	$("#rcptAddress2").val($("#buyerAddress2").val());
	$("#rcptPhone").val($("#buyerPhone").val());
}
