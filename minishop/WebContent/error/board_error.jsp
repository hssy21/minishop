<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
</head>
<body>

	<%-- board 에러 체크 페이지 --%>

	<c:choose>
		<c:when test="${requestScope.errorType== 'isNotLogin' }">
			<script>
			alert('로그인이 되어 있지 않습니다.');
			history.back();
			</script>
		</c:when>
		
		<c:when test="${requestScope.errorType== 'isNotAdmin' }">
			<script>
			alert('관리자만 가능합니다.');
			history.back();
			</script>
		</c:when>
		
		<c:when test="${requestScope.errorType== 'isNull' }">
			<script>
			alert('작성되지 않은 필드가 있습니다.');
			history.back();
			</script>
		</c:when>
		
		<c:when test="${requestScope.errorType== 'notAuth' }">
			<script>
			alert('권한이 없습니다.');
			history.back();
			</script>
		</c:when>
		
		<c:when test="${requestScope.errorType== 'xxxx' }">
			<script>
			alert('xxxxxx');
			location.href= 'xxxxxx.do';
			</script>
		</c:when>
		
		<c:otherwise>
			<h3>${requestScope.errorType}</h3>
			<h3>${sessionScope.userId}</h3>
		</c:otherwise>
	</c:choose>
	
</body>
</html>