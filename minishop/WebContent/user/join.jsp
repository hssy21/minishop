<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mini Shop</title>

<script type="text/javascript">
/*회원 ID 중복 조회 */
function isIdFunction() {
	var userId = $('#userId').val();	
$.ajax({
		type: 'GET',
		url: 'UserCheckAction.do',
		data: {'userId': userId },
		dataType: 'html',
		success: function(data) {
			if($.trim(data)=='cant'){
				//$('#userIdForm').addClass('has-error');
				$('#userIdForm').removeClass('has-success').addClass('has-error');
				$('#userIdMark').removeClass('glyphicon glyphicon-ok form-control-feedback').addClass('glyphicon glyphicon-remove form-control-feedback');
				$('#checkMessage').html('<b>사용 불가</b>');	
			}else{
				$('#userIdForm').removeClass('has-error').addClass('has-success');
				$('#userIdMark').removeClass('glyphicon glyphicon-remove form-control-feedback').addClass('glyphicon glyphicon-ok form-control-feedback');
				$('#checkMessage').html('<b>사용 가능</b>');	
			}
			
		}
	});	
}

/*Captcha 스크립트 */
function FormSubmit() {
	if (grecaptcha.getResponse() == ""){
	   alert("자동가입 방지 체크를 해야 합니다.");
	   return false; 
	} else {
	      return true; 
	} 
}
</script>


</head>
<body>

<jsp:include page="/layout/header.jsp" />

<div class="container">
<div class="row">
	<div class="col-md-3"></div>
	<div class="col-md-6 col-sm-12">
			
			<div class="page-header">
			  <h3 style="text-align: center;">회원가입</h3>
			</div>
			
			<div class="jumbotron" style="padding-top: 30px; padding-bottom: 15px;">
			<form action="JoinAction.do" method="post" onsubmit="return FormSubmit();">
			<!-- <form action="JoinAction.do" method="post"> -->
				
				<div class="form-group has-feedback" id="userIdForm">
				  <label for="userId">ID:</label> <span id="checkMessage"></span>
				  <div>
				  	<input type="text" class="form-control" placeholder="아이디" name="userId" id="userId" onblur="isIdFunction();">
				  	<span style="padding-top: 25px;" id="userIdMark"></span>
				  </div>
				</div>
				<div class="form-group">
				  <label for="userPassword">Password:</label>
				  <input type="password" class="form-control" placeholder="비밀번호" name="userPassword1">
				</div>
				
				<div class="form-group">
				  <label for="userPassword2">Password 확인:</label>
				  <input type="password" class="form-control" placeholder="비밀번호 확인" name="userPassword2">
				</div>
				
				<div class="form-group">
				  <label for="userName">Name:</label>
				  <input type="text" class="form-control" placeholder="이름" name="userName">
				</div>
				
				<div class="form-group">
				  <label for="userAddress">Address:</label>
				  <input type="text" class="form-control" placeholder="주소1" name="userAddress1">
				  <label for="userAddress2"></label>
				  <input type="text" class="form-control" placeholder="주소2" name="userAddress2">
				</div>
				
				<div class="form-group">
				  <label for="userPhone">Phone:</label>
				  <input type="text" class="form-control" placeholder="휴대전화번호 ('-' 없이 입력해주세요.)" name="userPhone">
				</div>
				
				<div class="form-group">
				  <label for="userEmail">Email:</label>
				  <input type="email" class="form-control" placeholder="이메일 주소" name="userEmail">
				</div>
				
				<!-- <div class="g-recaptcha g-recaptcha-outer g-recaptcha-inner g-recaptcha" data-sitekey="6Lfv2S4UAAAAAPGjrRUnOWL9tx6bCjntmErei30t"></div> -->
				<div class="g-recaptcha" data-theme="light" data-sitekey="6Lfv2S4UAAAAAPGjrRUnOWL9tx6bCjntmErei30t" style="transform:scale(0.77);-webkit-transform:scale(0.77);transform-origin:0 0;-webkit-transform-origin:0 0;"></div> 
				
				<p style="text-align:center;">
					<input type="submit" class="btn btn-default" value="회원가입">
					<a href="#" class="btn btn-default" role="button" onclick="history.back();">돌아가기</a>
				</p>
			</form>
			</div>
			
		</div>
		<div class="col-md-3"></div>
	</div>
	
</div> <!-- close div container  -->

<script src='https://www.google.com/recaptcha/api.js'></script>
</body>
</html>