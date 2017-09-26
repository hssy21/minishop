<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
</head>
<body>

	<%-- user 에러 체크 페이지 --%>

	<c:choose>
		<c:when test="${requestScope.errorType== 'loginError' }">
			<script>
			alert('아이디 또는 비밀번호가 일치하지 않습니다.');
			history.back();
			</script>
		</c:when>
		
		<c:when test="${requestScope.errorType== 'passwordNotSame' }">
			<script>
			alert('비밀번호가 서로 일치하지 않습니다.');
			history.back();
			</script>
		</c:when>
		
		<c:when test="${requestScope.errorType== 'isNull' }">
			<script>
			alert('입력되지 않은 값이 있습니다.');
			history.back();
			</script>
		</c:when>
		
		<c:when test="${requestScope.errorType== 'isDuplicated' }">
			<script>
			alert('이미 존재하는 아이디 입니다.');
			history.back();
			</script>
		</c:when>
		
		<c:when test="${requestScope.errorType== 'authError' }">
			<script>
			alert('비밀번호가 일치하지 않습니다.');
			history.back();
			</script>
		</c:when>
		
		<c:when test="${requestScope.errorType== 'isNotLogin' }">
			<script>
			alert('로그인이 되어있지 않습니다.');
			history.back();
			</script>
		</c:when>
		
		<c:when test="${requestScope.errorType== 'successMessage' }">
			<script>
			alert('회원정보 변경이 완료되었습니다.');
			location.href= 'main.do';
			</script>
		</c:when>
		
		<c:when test="${requestScope.errorType== 'phone' }">
			<script>
			alert(' - 없이 입력해 주세요.');
			history.back();
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