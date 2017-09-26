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
<p style="padding-left:10px;"><a href="main.do">홈</a> > <a href="NoticeList.do">공지사항</a></p>
		
		<div class="row">
		<div class="col-md-offset-1 col-md-10 col-sm-12">
		<h3 style="text-align:center;"> 공지사항 게시판 </h3>
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd;">
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
					<c:forEach var="voList" items="${requestScope.voList}">
						<tr>
			 				<td>${voList.noticeIdx}</td>
							<td>${voList.noticeWriter}</td>
							<td style="text-align: left;">
								<a href="NoticeContentView.do?noticeIdx=${voList.noticeIdx}">${fn:replace(voList.noticeTitle,' ','&nbsp;')}</a>
							</td> <!-- 리스트 출력시 띄어쓰기 처리 -->
							<td>${fn:substring(voList.noticeDate,0,11)} (${fn:substring(voList.noticeDate,11,13)}시 ${fn:substring(voList.noticeDate,14,16)}분)</td>
							<td>${voList.noticeHit}</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
			
			<!-- 이전, 다음 버튼 생성 -->
			
			<c:if test="${requestScope.pageNumber!= 1}">		
				<a href="NoticeList.do?pageNumber=${requestScope.pageNumber -1}" class="btn btn-success btn-arrow-left">이전</a>
			</c:if>
		
			<c:if test="${requestScope.isNextPage}">
				<a href="NoticeList.do?pageNumber=${requestScope.pageNumber +1}" class="btn btn-success btn-arrow-right">다음</a>
			</c:if>

			<c:if test="${sessionScope.userId== 'admin'}">
				<a href="NoticeWriteView.do" class="btn btn-primary pull-right">글쓰기</a>
			</c:if>
		</div> <!-- end div col-md-10 col-sm-12 -->
		
		<div class="col-md-1"></div>
		
		</div> <!-- end div row -->
	</div> <!-- end div container -->

</body>
</html>