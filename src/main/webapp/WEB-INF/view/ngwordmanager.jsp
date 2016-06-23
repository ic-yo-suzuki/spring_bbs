<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page isELIgnored="false"%>
<%@ page import="java.lang.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <link rel = "stylesheet" type = "text/css" href = "stylesheet/style.css"> -->
<title>NGワード管理</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value = "/resources/stylesheet/style.css" />">
<script type="text/javascript"
	src="<c:url value = "/resources/js/lib/jquery-1.12.4.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value = "/resources/js/manage/ngword/postNgWord.js" />"></script>
	<script type="text/javascript"
	src="<c:url value = "/resources/js/manage/ngword/deleteNgWord.js" />"></script>
<script type="text/javascript"
	src="<c:url value = "/resources/js/general/ajaxProcedure.js" />"></script>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
	<div class="main-contents">
		<div class="header">
			<div class="menu">
				<a href="${contextPath}/top/">ホーム</a>


			</div>
		</div>
		<p>

			<c:if test="${not empty errorMessages }">
				<div class="errorMessages">
					<ul>
						<c:forEach items="${errorMessages }" var="message">
							<li><c:out value="${message }" /> <br />
						</c:forEach>
					</ul>
				</div>
				<c:remove var="errorMessages" scope="session" />
			</c:if>
		<hr>


		<div class="ngwordlist">
			NGワード管理
			<form action="${contextPath }/manage/ngword/add/" id="postNgWord"
				method="post">
				<table class="ngwordadd">
					<th>新たに追加するNGワード</th>
					<th>操作</th>
					<tbody>
						<tr>
							<td><input name="word" id="word" />(100文字まで)
								<div id="messageArea"></div></td>
							<td colspan="2">
								<button type='submit' name='post' id="post">登録</button>
							</td>
						</tr>
					</tbody>
				</table>
			</form>

			<table class="ngwordlist">
				<tbody>
					<th>NGワード</th>
					<th>操作</th>
					<c:forEach items="${ngWordList }" var="ngWord">

						<tr>
							<form action="${contextPath}/manage/ngword/delete/id/"
								id="deleteNgWord" method="post" style="display: inline" value = "${ngWord.id }">
							<td><c:out value="${ngWord.word }" /></td>
							<td><button type='submit' name='id' value="${ngWord.id }" >削除</button></td>
							</form>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>
</body>
</html>