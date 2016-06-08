<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title }</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/i18n/jquery.ui.datepicker-ja.min.js"></script>
<link rel="stylesheet"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/redmond/jquery-ui.css">
<link rel="stylesheet"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/ui-lightness/jquery-ui.css">

<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/lib/prototype.js"></script>
<script type="text/javascript"
 	src="<c:url value = "/resources/js/lib/jquery-1.12.4.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value = "/resources/js/getUserList.js" />"></script>
</head>

<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
<div class="main-contents">
	<div class="header">
		<div class="menu">

			<a href="${contextPath}/post/message/">新規投稿</a>
			<c:if test="${loginUser.departmentId == 1 }">
				<a href="${contextPath}/manage/user/">ユーザの管理</a>
			</c:if>
			<c:if test="${loginUser.departmentId == 2 }">
				<a href="${contextPath}/manage/ngword/">NGワードの管理</a>
			</c:if>
			<a href="${contextPath }/logout/">ログアウト</a>
			<p>
		</div>

		<div class="name">
			ようこそ<b><c:out value="${loginUser.name }" /></b>さん
		</div>


		<div>
			<c:out value = "${message }" />
			<form:errors path="*" />
			<c:out value="${errorMessages }" />
			<c:remove var="errorMessages" scope="session" />
			<c:remove var="message" scope = "session" />
		</div>
		<p>
	</div>
	<p>
	<p>
	<div class="narrowing">
		<form:form modelAttribute="narrowingForm">
			<script>
				$(function() {
					$("#dateStart").datepicker({
						maxDate : 0
					});
					$("#dateEnd").datepicker({
						maxDate : 0
					});
				});
			</script>
			<b>投稿の絞込み検索</b>
			<p>${narrowingMessage }</p>
			<ul>

				<li>カテゴリー</li>
				<form:select path="category" items="${categories }">
				</form:select>


				<li>日付<br /></li>
			</ul>


					開始日時<form:input path="start" id="dateStart" value="${dates[0] }" />
				    終了日時<form:input path="end" id="dateEnd" value="${dates[1] }" />(クリックするとカレンダーが表示されます)
					<p></p>

			<button type="submit" name="narrow">指定した条件で検索</button>
			<button type="submit" name="reset">絞込みを解除</button>

		</form:form>


	</div>
	<!-- 		<table class="postCount"> -->
	<!-- 			<th></th> -->
	<!-- 			<th>記事投稿数</th> -->
	<!-- 			<th>コメント投稿数</th> -->

	<!-- 			<tr> -->
	<%-- 				<td><c:out value="${loginUser.name }"></c:out>さんの投稿件数</td> --%>
	<%-- 				<td><c:out value="${userPostCount[0] }" /></td> --%>
	<%-- 				<td><c:out value="${userPostCount[1] }" /></td> --%>
	<!-- 			</tr> -->
	<!-- 			<tr> -->
	<%-- 				<td><c:out value="${loginUser.branchName }"></c:out>の投稿件数</td> --%>
	<%-- 				<td><c:out value="${branchPostCount[0] }" /></td> --%>
	<%-- 				<td><c:out value="${branchPostCount[1] }" /></td> --%>
	<!-- 			</tr> -->
	<!-- 		</table> -->


	<div class="messages">
		<b>投稿一覧</b>
		<c:if test="${empty messages }">
			<ul type="circle">
				<li><b>投稿がありません</b></li>
			</ul>
		</c:if>
		<c:if test="${not empty messages }">
			(<c:out value="${postCount }" />件)
		</c:if>
		<c:forEach items="${messages }" var="message">
			<table class="message">

				<c:set var="id" scope="request" value="${message.postId }" />
				<tr>
					<td>投稿者</td>
					<td><c:out value="${message.name }"></c:out></td>
				</tr>
				<c:set var="name" scope="request" value="${message.name }" />
				<tr>
					<td>タイトル</td>
					<td><c:out value="${message.title }"></c:out></td>
				</tr>
				<tr>
					<td>カテゴリ</td>
					<td><c:out value="${message.category }"></c:out></td>
				</tr>

				<tr>
					<td>本文</td>
					<td>
						<%
							String lineSeparator = System.getProperty("line.separator");
						%> <c:forTokens var="splitedMessage" items="${message.text }"
							delims="<%= lineSeparator %>">
							<c:out value="${splitedMessage }"></c:out>
							<br>
						</c:forTokens>
					</td>
				</tr>
				<tr>
					<td>投稿日時</td>
					<td><abbr
						title="<fmt:formatDate value="${message.insertDate }" pattern ="yyyy/MM/dd HH:mm:ss" />"><c:out
								value="${message.elapsedTimeText }" /></abbr></td>
				</tr>


				<c:if
					test="${(message.userId == loginUser.id) || (loginUser.departmentId == 2) || (message.branchId == loginUser.branchId && loginUser.departmentId == 3) }">
					<form:form modelAttribute="deleteMessageForm">
						<tr>
							<td colspan="2"><button type="submit" name="deleteMessage"
									value="${message.postId }"
									onClick="return confirm('この投稿を削除します。よろしいですか？')">投稿を削除する</button>
					</form:form>
				</c:if>

			</table>

			<div class=comments>
				<br />コメント一覧 <br>
				<table class=comments>


					<c:if test="${not empty comments }">
						<%
							int count = 0;
						%>
						<c:forEach items="${comments }" var="comment">
							<c:if test="${message.postId == comment.postId }">
								<table class="comment">

									<tr>
										<td>投稿者</td>
										<td><c:out value="${comment.name }"></c:out></td>
									</tr>
									<tr>
										<td>本文</td>
										<td><c:forTokens var="splitedMessage"
												items="${comment.text }" delims="<%= lineSeparator %>">
												<c:out value="${splitedMessage }"></c:out>
												<br>
											</c:forTokens></td>
									</tr>
									<tr>
										<td>投稿日時</td>
										<td><abbr
											title="<fmt:formatDate value= "${comment.insertDate }" pattern ="yyyy/MM/dd HH:mm:ss" />"><c:out
													value="${comment.elapsedTimeText }" /></abbr></td>
									</tr>
									<c:if
										test="${(comment.userId == loginUser.id) || (loginUser.departmentId == 2) || (comment.branchId == loginUser.branchId && loginUser.departmentId == 3) }">
										<form:form modelAttribute="deleteCommentForm">
											<tr>
												<td colspan="2"><button type="submit"
														name="deleteComment" value="${comment.commentId }"
														onClick="return confirm('このコメントを削除します。よろしいですか？')">コメントを削除する</button>
												</td>
											</tr>
										</form:form>
									</c:if>
									<hr>
									投稿番号：<%=++count%><br>
								</table>
							</c:if>
						</c:forEach>
					</c:if>
				</table>
				<div class="postComeent">
					<form:form modelAttribute="postCommentForm">
						<br />コメントの投稿<br />
						<div>
							<form:errors path="*" />
						</div>
						<form:textarea path="text" cols="80" rows="5" value="${text }" />
						<br />
						<input type="submit" name="postComment" value="投稿する">(500文字まで)

							<form:hidden path="postId" value="${message.postId }" />
						<form:hidden path="userId" value="${loginUser.id }" />

					</form:form>
				</div>
			</div>
			<hr>
		</c:forEach>

	</div>
</div>




</body>
</html>