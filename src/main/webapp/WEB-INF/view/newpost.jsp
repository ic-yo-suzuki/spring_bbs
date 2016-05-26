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
	<h2>${message }</h2>
	<table class="newpost">
		<form:form modelAttribute="postMessageForm">
			<div>
				<form:errors path="*" />
			</div>

			<tr>
				<td>カテゴリー(必須)(どちらか一方を選択してください)</td>
				<td><label><input type="radio" name="categorySelect"
						value="select" onclick="categorySelectChanged();"
						checked="checked" />既存のカテゴリーから選択</label> <label><input
						type="radio" name="categorySelect" value="create"
						onclick="categorySelectChanged();" />新たにカテゴリーを作成</label></td>
			</tr>
			<tr id="selectCategory">
				<td>カテゴリの選択</td>
				<td><select name="selectCategory">
						<c:forEach items="${categories }" var="category">

							<option value="${category }"><c:out
									value="${category }"></c:out></option>
						</c:forEach>
				</select></td>
			</tr>
			<tr id="createCategory">
				<td>カテゴリの新規作成</td>
				<td><input name="createCategory" id = "createCategory" />新たに追加するカテゴリ名を10文字以内で入力してください)
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

<script type="text/javascript">
	function categorySelectChanged() {

		radio = document.getElementsByName('categorySelect');

		if (radio[0].checked) {
			document.getElementById('createCategory').style.display = "none";
			document.getElementById('selectCategory').style.display = "";

		} else if (radio[1].checked) {
			document.getElementById('createCategory').style.display = "";
			document.getElementById('selectCategory').style.display = "none";
		}
	}
	//オンロードさせ、リロード時に選択を保持
	window.onload = categorySelectChanged;
</script>
</html>