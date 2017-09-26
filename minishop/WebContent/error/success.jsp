<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
</head>
<body>

	<%-- admin 에러 체크 페이지 --%>

	<c:choose>
		<c:when test="${requestScope.successType== 'join' }">
			<script>
			alert('회원가입이 완료 되었습니다.');
			location.href='main.do';
			</script>
		</c:when>
		<c:when test="${requestScope.successType== 'logout' }">
			<script>
			alert('로그아웃 되었습니다.');
			location.href='main.do';
			</script>
		</c:when>
		<c:when test="${requestScope.successType== 'modify' }">
			<script>
			alert('회원정보 변경이 완료되었습니다.');
			location.href= 'main.do';
			</script>
		</c:when>
		
		<c:when test="${requestScope.errorType== 'xxxx' }">
			<script>
			alert('xxxxxx');
			location.href= 'xxxxxx.do';
			</script>
		</c:when>
		
		<c:otherwise>
			<script>
			alert('xxxxxx');
			history.back();
			</script>
		</c:otherwise>
	</c:choose>
	
</body>
</html>