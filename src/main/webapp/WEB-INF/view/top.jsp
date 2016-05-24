<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title }</title>
</head>
<body>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/i18n/jquery.ui.datepicker-ja.min.js"></script>
<link rel="stylesheet"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/ui-lightness/jquery-ui.css">
<!-- <link rel="stylesheet" type="text/css" href="stylesheet/style.css"> -->


</head>
<body>
	<div class="main-contents">
		<div class="header">
			<div class="menu">

				<a href="newpost">新規投稿</a>
				<c:if test="${loginUser.departmentId == 1 }">
					<a href="usermanager">ユーザの管理</a>
				</c:if>
				<c:if test="${loginUser.departmentId == 2 }">
					<a href="ngwordmanager">NGワードの管理</a>
				</c:if>
				<a href="logout">ログアウト</a>
				<p>
			</div>
			<div class="name">
				ようこそ<b><c:out value="${loginUser.name }" /></b>さん
			</div>
			<p>
		</div>
		<p>
			<c:if test="${not empty errorMessages }">
				<div class="errorMessages">
					<ul>
						<c:forEach items="${errorMessages }" var="messages">
							<li><c:out value="${messages }" /></li>
							<br />
						</c:forEach>
					</ul>
				</div>
				<c:remove var="errorMessages" scope="session" />
			</c:if>
		<p>
		<div class="narrowing">
			<form action="top" method="post" style="display: inline">
				<b>投稿の絞込み検索</b>
				<p>
				<ul>
					<li>カテゴリー</li>
					<select name="category">
						<c:forEach items="${categories }" var="category">
							<c:if test="${category == selectedCategory }">
								<option value="${category }" selected><c:out
										value="${category }"></c:out></option>
							</c:if>
							<c:if test="${category != selectedCategory }">
								<option value="${category }"><c:out
										value="${category }"></c:out></option>
							</c:if>
						</c:forEach>
					</select>

					</p>
					<p>
					<li>日付<br /></li>
				</ul>

				開始日時<input type="text" name="dateStart" id="dateStart"
					value="${dates[0] }"> 終了日時<input type="text" name="dateEnd"
					id="dateEnd" value="${dates[1] }">(クリックするとカレンダーが表示されます)
				<p></p>

				<button type="submit" name="mode" value="narrow">指定した条件で検索</button>
				<button type="submit" name="mode" value="reset">絞込みを解除</button>

			</form>


		</div>
		<table class="postCount">
			<th></th>
			<th>記事投稿数</th>
			<th>コメント投稿数</th>

			<tr>
				<td><c:out value="${loginUser.name }"></c:out>さんの投稿件数</td>
				<td><c:out value="${userPostCount[0] }" /></td>
				<td><c:out value="${userPostCount[1] }" /></td>
			</tr>
			<tr>
				<td><c:out value="${loginUser.branchName }"></c:out>の投稿件数</td>
				<td><c:out value="${branchPostCount[0] }" /></td>
				<td><c:out value="${branchPostCount[1] }" /></td>
			</tr>
		</table>


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

					<c:set var="id" scope="request" value="${message.id }" />
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
						<form action="deletePost" method="post">
							<tr>
								<td colspan="2"><button type="submit" name="id"
										value="${message.id }"
										onClick="return confirm('この投稿を削除します。よろしいですか？')">投稿を削除する</button>
						</form>
					</c:if>

				</table>

				<div class=comments>
					<table class=comments>
						<br />コメント一覧
						<br>

						<c:if test="${not empty comments }">
							<%
								int count = 1;
							%>
							<c:forEach items="${comments }" var="comment">
								<c:if test="${message.id == comment.postId }">
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
											<form action="deleteComment" method="post">
												<tr>
													<td colspan="2"><button type="submit" name="id"
															value="${comment.id }"
															onClick="return confirm('このコメントを削除します。よろしいですか？')">コメントを削除する</button>
													</td>
												</tr>
											</form>
										</c:if>
										<hr>
										投稿番号：<%=count%><br>
									</table>
									<%
										count++;
									%>
								</c:if>
							</c:forEach>
						</c:if>
					</table>
					<div class="postComeent">
						<form action="postComment" method="post">
							<br />コメントの投稿<br />
							<textarea name="comment" cols="80" rows="5" class="post-box"><c:out
									value="${inputValues.text }"></c:out></textarea>
							<br /> <input type="submit" value="投稿する">(500文字まで) <input
								type="hidden" name="postId" value="${message.id }" /> <input
								type="hidden" name="userId" value="${loginUser.id }" />

						</form>
					</div>
				</div>
				<hr>
			</c:forEach>

		</div>
	</div>

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

</body>
</html>