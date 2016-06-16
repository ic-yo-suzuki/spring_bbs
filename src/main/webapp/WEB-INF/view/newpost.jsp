<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規投稿</title>
<link rel="stylesheet" type="text/css" href="<c:url value = "/resources/stylesheet/style.css"/>">
<script type="text/javascript" src = "<c:url value = "/resources/js/post/categorySelect.js" />"></script>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
	<a href="${contextPath}/top/">ホーム</a>
	<h2>${message }</h2>
	<table class="newpost">
		<form:form modelAttribute="postMessageForm" onsubmit="setCategory();">
			<div>
				<form:errors path="*" />
			</div>
			<tr>
				<td>カテゴリー(必須)(選択してください)</td>
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
							<c:if test="${category == selectedCategory }">
								<option value="${category }" selected="selected"><c:out
										value="${category }"></c:out></option>
							</c:if>
							<c:if test="${category != selectedCategory }">
								<option value="${category }"><c:out
										value="${category }"></c:out></option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>
			<tr id="createCategory">
				<td>カテゴリの新規作成(10文字まで)</td>
				<td><input name="createCategory" id="createCategory"
					value="${selectedCategory  }" /></td>
			</tr>
			<form:hidden path="category" value="" />
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
				<td><form:textarea path="text" cols="60" rows="8" /></td>
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