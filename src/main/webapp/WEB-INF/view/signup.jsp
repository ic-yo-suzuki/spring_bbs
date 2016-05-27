<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザ登録</title>
</head>

<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
	<div class="header">
		<a href="${contextPath}/manage/user/">戻る</a>
	</div>
	<div class="main-contents">

		${message }<br>

		<form:form modelAttribute="userForm">
			<div>
				<form:errors path="*" />
			</div>
			<table class="inputvalue">
				<th colspan="2">項目</th>

				<th>備考</th>
				<tr>
					<td>名前</td>
					<td><form:input path="name" /></td>
					<td>10文字以下</td>
				</tr>
				<tr>
					<td>ログインID</td>
					<td><form:input path="loginId" /></td>
					<td>半角英数字(A～Z、a～z、0～9)で6～20文字</td>
				</tr>
				<tr>
					<td>パスワード</td>
					<td><form:password path="password" /></td>
					<td>記号含む半角文字で6～255文字</td>
				</tr>
				<tr>
					<td>パスワード(確認)</td>
					<td><input type="password" name="password_verify"></td>
					<td></td>
				<tr>
					<td><label for="branch">所属支店</label></td>

					<td><form:select path="branchName" items="${branches }">
						</form:select></td>
					<td></td>
				</tr>
				<tr>

					<td><label for="department">所属部署・役職</label></td>
					<td><form:select path="departmentName" items="${departments }">

						</form:select></td>
					<td></td>
				</tr>
			</table>

			<input type="submit" value="登録" />
			<br />

		</form:form>
	</div>


</body>
</html>