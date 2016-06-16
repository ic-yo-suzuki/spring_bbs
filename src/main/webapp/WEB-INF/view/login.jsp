<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン</title>
<link rel="stylesheet" type="text/css" href="<c:url value = "/resources/stylesheet/style.css"/>">
</head>
<body>

	<div class="login-screen">

		<h3>掲示板 ログイン</h3>

		<form:form modelAttribute="loginForm">
			<div>
				<form:errors path="*" />
				<c:if test = "${not empty message }">
					${message }
				</c:if>
				<c:if test = "${not empty errorMessages }">
					${errorMessages }
				</c:if>
			</div>
			<table>
				<tbody>
					<tr>
						<td>ユーザID</td>
						<td><form:input path="loginId" /></td>
					</tr>
					<tr>
						<td>パスワード</td>
						<td><form:password path="password" /></td>
					</tr>
				</tbody>
			</table>
			<br>
			<input type="submit" value="ログイン">
		</form:form>



	</div>

</body>
</html>