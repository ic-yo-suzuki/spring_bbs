<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規投稿</title>
<%-- <link rel="stylesheet" type="text/css" href="<c:url value = "/resources/stylesheet/style.css"/>"> --%>
<script type="text/javascript"
	src="<c:url value = "/resources/js/lib/jquery-1.12.4.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value = "/resources/js/post/categorySelect.js" />"></script>
<script type="text/javascript"
	src="<c:url value = "/resources/js/post/checkMessage.js" />"></script>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
	<a href="${contextPath}/top/">ホーム</a>
	<h2>${message }</h2>

	<form:form modelAttribute="postMessageForm">
		<table class="newpost">
			<div>
				<form:errors path="*" />
			</div>
			<tr id="selectCategoryDivision">
				<td>
					カテゴリー(新規作成/選択)
					<div id = "categoryValidCheck"></div>
				</td>
				<td id="selectCategoryRadioButton"><label> <input
						type="radio" name="categorySelect" value="select"
						onclick="categorySelectChanged();" checked="checked" />既存のカテゴリーから選択
				</label> <label> <input type="radio" name="categorySelect"
						value="create" onclick="categorySelectChanged();" />新たにカテゴリーを作成
				</label></td>
			</tr>
			<tr id="selectCategory">
				<td>カテゴリの選択
				</td>
				<td><select name="selectCategory" id="cmbCategory">
						<c:forEach items="${categories }" var="category">
							<c:if test="${category == selectedCategory }">
								<option value="${category }" selected="selected">
									<c:out value="${category }" />
								</option>
							</c:if>
							<c:if test="${category != selectedCategory }">
								<option value="${category }">
									<c:out value="${category }" />
								</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>
			<tr id="createCategory">
				<td>カテゴリの新規作成(10文字まで)
				</td>
				<td><input name="createCategory" id="txtCategory"
					value="${selectedCategory  }" maxlength = "10" /></td>
			</tr>
			<form:hidden path="category" value="" />
			<tr>
				<td>投稿者</td>
				<td><c:out value="${loginUser.name }" />さん(自動で追加されます)</td>
			</tr>
			<tr>
				<td>タイトル(50文字まで)
					<div id="titleValidCheck"></div>
				</td>
				<td><form:input path="title" size="40" maxlength = "50" required = "required" /></td>
			</tr>
			<tr>
				<td>本文(1000文字まで)
					<div id="textValidCheck"></div>
				</td>
				<td><form:textarea path="text" cols="60" rows="8" maxlength = "1000" required = "required"/></td>
			</tr>

			<tr>
				<td></td>
				<td><input type="submit" value="投稿する" id="submit-button">
				</td>
				<form:hidden path="userId" value="${loginUser.id }" />
				<form:hidden path="branchId" value="${loginUser.branchId }" />
				<form:hidden path="departmentId" value="${loginUser.departmentId }" />
			</tr>
		</table>
	</form:form>

</body>

</html>