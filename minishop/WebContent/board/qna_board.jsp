<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix= "fn" uri= "http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mini Shop</title>
</head>
<body>

<jsp:include page="/layout/header.jsp" />

<div class="container">
<p style="padding-left:10px;"><a href="main.do">홈</a> > <a href="QnaList.do">문의게시판</a></p>

		<div class="row">
		<div class="col-md-offset-1 col-md-10 col-sm-12">
		<h3 style="text-align:center;"> 문의 게시판 </h3>
			<table class="table" style="text-align: center; border: 1px solid #dddddd;">
				<thead>
					<tr>
						<th style="width: 5%; background-color: #eeeeee; text-align: center;">번호</th>
						<th style="width: 20%; background-color: #eeeeee; text-align: center;">글쓴이</th>
						<th style="width: 50%; background-color: #eeeeee; text-align: center;">제목</th>
						<th style="width: 20%; background-color: #eeeeee; text-align: center;">등록일</th>
						<th style="width: 5%; background-color: #eeeeee; text-align: center;">조회</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="vo" items="${requestScope.voList}">
						<tr>
			 				<td>${vo.qnaIdx}</td>
							<td>${vo.qnaWriter}</td>
							
							<c:if test="${vo.qnaAvailable== 0}">
								<td style="text-align: left; color: #C0C0C0;">
									<c:forEach begin="1" end="${vo.qnaIndent}">&nbsp;&nbsp;&nbsp;</c:forEach>
									<c:if test="${vo.qnaIndent!= 0}"><img src="${pageContext.request.contextPath}/resources/images/reply_icon.gif"></c:if>
									[삭제됨] ${fn:replace(vo.qnaTitle,' ','&nbsp;')} <!-- 리스트 출력시 띄어쓰기 처리 -->
								</td>
							</c:if>
							
							<c:if test="${vo.qnaAvailable!= 0}">
								<td style="text-align: left;">
									<c:forEach begin="1" end="${vo.qnaIndent}">&nbsp;&nbsp;&nbsp;</c:forEach>
									<c:if test="${vo.qnaIndent!= 0}"><img src="${pageContext.request.contextPath}/resources/images/reply_icon.gif"></c:if>
									<a href="QnaContentView.do?qnaIdx=${vo.qnaIdx}">${fn:replace(vo.qnaTitle,' ','&nbsp;')}</a> <!-- 리스트 출력시 띄어쓰기 처리 -->
								</td> 
							</c:if>
							
							<td>${fn:substring(vo.qnaDate,0,11)} (${fn:substring(vo.qnaDate,11,13)}시 ${fn:substring(vo.qnaDate,14,16)}분)</td>
							<td>${vo.qnaHit}</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
			
			<!-- 이전, 다음 버튼 생성 -->
			
			<c:if test="${requestScope.pageNumber!= 1}">		
				<a href="QnaList.do?pageNumber=${requestScope.pageNumber -1}" class="btn btn-success btn-arrow-left">이전</a>
			</c:if>
		
			<c:if test="${requestScope.isNextPage}">
				<a href="QnaList.do?pageNumber=${requestScope.pageNumber +1}" class="btn btn-success btn-arrow-right">다음</a>
			</c:if>

			<c:if test="${sessionScope.userId!= null}">
				<a href="QnaWriteView.do" class="btn btn-primary pull-right">글쓰기</a>
			</c:if>
		</div> <!-- end div col-md-10 col-sm-12 -->
		
		<div class="col-md-1"></div>
		
		</div> <!-- end div row -->
	</div> <!-- end div container -->

</body>
</html>