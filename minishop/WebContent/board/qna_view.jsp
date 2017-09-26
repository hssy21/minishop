<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
	
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3" style="background-color: #eeeeee; text-align: center;">문의 게시판</th>
					</tr>
				</thead>
				<tbody>

					<c:set var="vo" value="${requestScope.vo}" />
					<tr>
						<td style="width: 20%;">글 제목</td>
						<td colspan="2" style="text-align: left;">${vo.qnaTitle}</td>
					</tr>
					<tr>
						<td>작성자</td>
						<td colspan="2" style="text-align: left;">${vo.qnaWriter}</td>
					</tr>
					<tr>
						<td>작성일자</td>
						<td colspan="2" style="text-align: left;">${fn:substring(vo.qnaDate,0,11)} ${fn:substring(vo.qnaDate,11,13)}시 ${fn:substring(vo.qnaDate,14,16)}분</td>
					</tr>
					<tr>
						<td>내용</td>
						<td colspan="2" style="min-height: 200px; text-align: left; ">${vo.qnaContent}</td>
						<%-- <td colspan="2" style="min-height: 200px; text-align: left; "><%= content.getBbsContent().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>") %></td> --%>
					</tr>
				</tbody>
			</table>
			<a href="QnaList.do" class="btn btn-primary">목록</a>

			<c:if test="${sessionScope.userId!= null && (sessionScope.userId== vo.qnaWriter || sessionScope.userId=='admin') }">
					<a href="QnaUpdateView.do?qnaIdx=${vo.qnaIdx}" class="btn btn-primary">수정</a>
					<a href="QnaDeleteAction.do?qnaIdx=${vo.qnaIdx}&qnaGroup=${vo.qnaGroup}&qnaStep=${vo.qnaStep}" class="btn btn-primary" onclick="return confirm('정말로 삭제 하시겠습니까?')">삭제</a>
			</c:if>
			
			<c:if test="${sessionScope.userId== 'admin'}">
				<a href="QnaReplyView.do?qnaIdx=${vo.qnaIdx}" class="btn btn-primary pull-right">답변</a>
			</c:if>
			
	</div>
	</div>
</div>


</body>
</html>