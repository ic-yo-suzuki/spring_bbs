<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ page isELIgnored="false"%>
<%@ page import="java.lang.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv = "content-language" content="ja">
<title>ユーザ管理</title>
<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
<%-- <link rel="stylesheet" type="text/css" href="<c:url value = "/resources/stylesheet/style.css"/>"> --%>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/lib/prototype.js"></script>
<script type="text/javascript"
	src="<c:url value = "/resources/js/lib/jquery-1.12.4.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value = "/resources/js/manage/user/logicalDeleteAjax.js" />"></script>
<script type="text/javascript"
	src="<c:url value = "/resources/js/manage/user/physicalDeleteAjax.js" />"></script>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
	<input type = "hidden" id = "loginUserId" value = "${loginUser.id }">
	<div class="main-contents">
		<div class="header">
			<div class="menu">
				<a href="${contextPath}/top/">ホーム</a>
				<c:if test="${loginUser.departmentId == 1 }">
					<a href="${contextPath}/manage/user/signup/">ユーザ新規登録</a>
				</c:if>

			</div>
		</div>
					${message } <br>
		<p id = "resultMessage">

			<c:if test="${not empty successMessage }">
				${successMessage }
		</c:if>
			<c:if test="${not empty errorMessage }">
			${errorMessage }
		</c:if>

			<br>
		<hr>
		ユーザ一覧
		<div class="userlist">
			<table class="userlist">
				<tbody>
					<th>ユーザ名</th>
					<th>ログインID</th>
					<th colspan="2">所属</th>
					<th>最終ログイン</th>
					<th colspan="3">ユーザの操作</th>

					<c:forEach items="${users }" var="user">
						<tr>
							<td id = "name"><c:out value="${user.name }" /></td>
							<td id = "loginId"><c:out value="${user.loginId }" /></td>
							<td id = "branchName"><c:out value="${user.branchName }" /></td>
							<td id = "departmentName"><c:out value="${user.departmentName }" /></td>
							<td id = "loginDate">
								<abbr title="<fmt:formatDate value= "${user.lastLoginDate }" pattern ="yyyy/MM/dd HH:mm:ss" />">
									<c:out value="${user.elapsedTimeText }" />
								</abbr>
							</td>

							<form:form modelAttribute="userForm" method="post"
								style="display: inline">
								<td>
									<button type='submit' name='editUser' value="${user.id }">編集</button>
								</td>
							</form:form>

							<form:form modelAttribute="userForm" id = "logicalDelete" method="post"
								style="display: inline" value = "${user.id}">
								<c:if test="${loginUser.id != user.id }">
									<c:if test="${user.status == true }">
										<td>
											<button type='submit' id = "logicalDelete" name='logicalDeleteUser' value="${user.id }" >停止</button>
										</td>
									</c:if>
									<c:if test="${user.status == false }">
										<td>
											<button type='submit' id = "logicalDelete" name='logicalDeleteUser' value="${user.id }" >復活</button>
										</td>
									</c:if>
								</c:if>
								<c:if test="${loginUser.id == user.id }">
									<td></td>
								</c:if>
							</form:form>
							<form:form modelAttribute="userForm" method="post"  id = "physicalDelete" value="${user.id }" style="display: inline">
								<c:if test="${loginUser.id != user.id }">
									<td>
										<button type='submit' name='physicalDeleteUser' value="${user.id }" >削除</button>
									</td>
								</c:if>
								<c:if test="${loginUser.id == user.id }">
									<td></td>
								</c:if>
							</form:form>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>