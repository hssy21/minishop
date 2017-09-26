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

<div class="container">
<p style="padding-left:10px;"><a href="main.do">홈</a> > <a href="QnaList.do">문의게시판</a></p>

		<div class="row">
		<div class="col-md-offset-1 col-md-10 col-sm-12">
		<h3 style="text-align:center;"> 문의 게시판 </h3>
		
		<form method="post" action="QnaWriteAction.do">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="2" style="background-color: #eeeeee; text-align: center;">문의 게시판</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="text" class="form-control" placeholder="글 제목" name="qnaTitle" maxlength="50"></td>
					</tr>
					<tr>
						<td><textarea class="form-control" placeholder="글 내용" name="qnaContent" maxlength="2048" style="height: 350px;"></textarea></td>
					</tr>
				</tbody>
			</table>
			
				<input type="submit" class="btn btn-primary pull-right" value="글쓰기">

		</form>
		</div> <!-- end div col-md-10 col-sm-12 -->
		
		<div class="col-md-1"></div>
		
		</div> <!-- end div row -->
</div> <!-- end div container -->


</body>
</html>