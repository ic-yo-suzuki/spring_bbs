<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${editUser.name}-ユーザ情報の編集</title>
<%-- <link rel="stylesheet" type="text/css" href="<c:url value = "/resources/stylesheet/style.css"/>"> --%>
<script type="text/javascript"
	src="<c:url value = "/resources/js/lib/prototype.js"/>"></script>
<script type="text/javascript"
	src="<c:url value = "/resources/js/lib/jquery-1.12.4.min.js"/>"></script>
	<script type="text/javascript"
	src="<c:url value = "/resources/js/manage/user/existLoginId.js"/>"></script>
	<script type="text/javascript"
	src="<c:url value = "/resources/js/general/ajaxProcedure.js" />"></script>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
	${message }
	<br>
	<a href="${contextPath}/manage/user/">戻る</a>
	<br>
	<form:form modelAttribute="editUserForm">
		<form:errors path="*" />
		<br />
		<table class="inputvalue">
			<th>項目</th>
			<th>入力値</th>
			<th>備考</th>


			<tr>
				<td><label for="name">名前</label></td>
				<td><input name="name" id="name" value="${editUser.name }"
					maxlength="10" /></td>
				<td>10文字以下</td>
			</tr>
			<tr>
				<td><label for="loginId">ログインID</label></td>
				<td>
				<input type = "hidden" id = "orginalId" value = "${editUser.loginId }" />
				<input name="loginId" id="loginId"
					value="${editUser.loginId }" maxlength="20" onKeyUp = "existLoginIdEdit(this)" /></td>
				<td id = "notice">半角英数字(A～Z、a～z、0～9)で6～20文字</td>
			</tr>
			<tr>
				<td><label for="password">パスワード</label></td>
				<td><input name="password" type="password" maxlength="255" /></td>
				<td>記号含む半角文字で6～255文字</td>
			</tr>
			<tr>
				<td><label for="password_verify">パスワード(確認用)</label></td>
				<td><input name="password_verify" type="password"
					maxlength="255" /></td>
				<td></td>
			</tr>
			<tr>
				<td><label for="branch">所属支店</label></td>

				<td><form:select path="branchName">
						<form:option value="${editUser.branchName }" selected="true" />
						<form:options items="${branches }" />
					</form:select></td>
				<td></td>
			</tr>
			<tr>

				<td><label for="department">所属部署・役職</label></td>
				<td><form:select path="departmentName">

						<%-- 					</form:select> --%>
						<form:option value="${editUser.departmentName }" selected="true" />
						<form:options items="${departments }" />
					</form:select></td>
				<td></td>
			</tr>
		</table>

		<input type="submit" value="登録" />
		<form:hidden path="id" value="${editUser.id }" />
		<br />
	</form:form>
</body>
</html>