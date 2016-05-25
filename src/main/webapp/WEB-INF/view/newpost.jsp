<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規投稿</title>
</head>
<body>
	<table class="newpost">
		<form:form modelAttribute="postMessageForm">
			<div>
				<form:errors path="*" />
			</div>

			<tr>
				<td>カテゴリー(必須)<br>カテゴリーの新規作成
				</td>
				<td><form:select path="category" items="${categories }"></form:select>

					<br>  <input name="newCategory" id="newCategory" />新たに追加するカテゴリ名を10文字以内で入力してください)
				</td>
			</tr>
			<tr>
				<td>投稿者</td>
				<td><c:out value="${loginUser.name }"></c:out>さん(自動で追加されます)</td>
			</tr>
			<tr>
				<td>タイトル(必須)(50文字まで)</td>
				<td><form:input path="title" size="40" /></td>
			</tr>
			<tr>
				<td>本文(1000文字まで)</td>
				<td><form:textarea path="text" cols="60" rows="8" /> <c:out
						value="${inputValues.text }"></c:out></td>
			</tr>

			<tr>
				<td></td>
				<td><input type="submit" value="投稿する"></td>
				<form:hidden path="userId" value="${loginUser.id }" />
				<form:hidden path="branchId" value="${loginUser.branchId }" />
				<form:hidden path="departmentId" value="${loginUser.departmentId }" />

			</tr>

		</form:form>
	</table>
</body>
</html>