<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../css/reset.css" type="text/css" rel="stylesheet" />
<link href="../css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<!--  --------------헤더부분--------------- -->
	<jsp:include page="../../inc/header.jsp" />

	<!--  --------------비쥬얼부분--------------- -->
	<jsp:include page="../inc/visual.jsp" />



	<div id="body" class="clearfix">
		<div class="content-container">
			<!--  --------------어사이드부분--------------- -->
			<jsp:include page="../inc/aside.jsp" />

			<main id="main">
			<h2>공지사항</h2>

			<div>
				<h3>경로</h3>
				<ol>
					<li>home</li>
					<li>고객센터</li>
					<li>공지사항</li>
				</ol>
			</div>

			<div>
				<h3>공지사항 검색 폼</h3>
				<form action="notice" method="get">
					<label>검색어</label> <input type="text" name="title" /> <input
						type="submit" />
				</form>
			</div>
			<table border="1">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
				<c:forEach var="n" items="${list}" begin="0" end="3" step="2">
					<tr>
						<td>${n.id}</td>
						<td><a href="notice-detail?id=${n.id}">${n.title}</a></td>
						<td>newlec</td>
						<td>${n.regDate}</td>
						<td>${n.hit}</td>
					</tr>
				</c:forEach>
			</table>

			<span class="btn btn-default" href="">글쓰기</span> <a
				class="btn-img btn-cancel" href="">취소</a> </main>
		</div>
	</div>
	<!--  --------------푸터부분--------------- -->
	<jsp:include page="../../inc/footer.jsp" />

</body>
</html>