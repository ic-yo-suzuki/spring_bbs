/**
 *
 */

var jq = jQuery.noConflict();

jq(function($) {
	$.ajax({
		type : "GET",
		url : "/Spring_BBS/getUserList/",
		dataType : "json",
		contentType : "application/json; charset=UTF-8",
		success : function(data) {
//			success(data);
//			generateHtml(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			error(XMLHttpRequest, textStatus, errorThrown);
		}
	});
});

function success(data) {
	alert("success:" + data);

}
function error(XMLHttpRequest, textStatus, errorThrown) {
	alert("error:" + XMLHttpRequest);
	alert("status:" + textStatus);
	alert("errorThrown:" + errorThrown);
}

function generateHtml(Data) {
	jq("table.userlist tbody").html(
			"<th>ユーザ名</th>" + "<th>ログインID</th>" + "<th colspan = \"2\">所属</th>"
					+ "<th> 最終ログイン </th>" + "<th colspan = \"3\">ユーザの操作</th>");

	jq(Data).each(function() {
		console.log(this.name + ", " + this.loginId + ", "
				+ this.branchName + ", " + this.departmentName
				+ ", " + this.id);

		jq('<tr>').appendTo('table.userlist tbody');
		jq('<td>' + this.name + '</td>').appendTo('.userlist tbody');

		jq('<td>' + this.loginId + '</td>').appendTo('.userlist tbody');

		jq('<td>' + this.branchName+ '</td>').appendTo('.userlist tbody');

		jq('<td>' + this.departmentName + '</td>').appendTo('.userlist tbody');

		jq('<td>' + this.elapsedTimeText + '</td>').appendTo('.userlist tbody');
		jq('<form:form modelAttribute="userForm" method="post" style="display: inline">'
						+ '<td>'
						+ '<button type=\'submit\' name=\'editUser\' value="'
						+ this.id + '">編集</button>' + '</td>'
						+ '</form:form>').appendTo(
				'.userlist tbody');

		jq('<td><form:form modelAttribute="userForm" method="post" style="display: inline">').appendTo('.userlist tbody');
		console.log('<td><button type="submit" name="logicalDeleteUser" ' + 'value="' + this.id + '" '
				+ 'onClick="return confirm("このユーザを復活します。よろしいですか？")">復活</button></td>');
			if(this.status){
				if($("loginUserId").value != this.id){
					jq('<td><button type=\'submit\' name=\'logicalDeleteUser\' '
							+ 'value="' + this.id + '" '
							+ 'onClick="return confirm(\'このユーザを停止します。よろしいですか？\')">停止</button></td>').appendTo('.userlist tbody');
				}
			}else{
				if($("loginUserId").value != this.id){
					jq('<td><button type=\'submit\' name=\'logicalDeleteUser\' '
							+ 'value="' + this.id + '" '
							+ 'onClick="return confirm(\'このユーザを復活します。よろしいですか？\')">復活</button></td>').appendTo('.userlist tbody');
				}
			}
		jq('</form:form>').appendTo('.userlist tbody');
//						+ '</form:form></td>'
//						+ '<td><form:form modelAttribute="userForm" method="post"'
//						+ 'style="display: inline">'
//						+ '<c:if test="${loginUser.id != user.id }">'
//						+ '<td><button type=\'submit\' name=\'physicalDeleteUser\''
//						+ 'value="${user.id }"'
//						+ 'onClick="return confirm(\'このユーザを削除します。よろしいですか？\')">削除</button></td>'
//						+ '</c:if>' + '</form:form></td>')
//				.appendTo('.userlist tbody');
		jq('</tr>').appendTo('.userlist tbody');
	});

	// for(var index in data.release){
	// jq('<td class="label"><span class="' +
	// data.release[index].label + '">' +
	// data.release[index].category +
	// '</span></td>'+
	// '<td><a href="' + data.release[index].url +
	// '" target="' + data.release[index].target +
	// '">' + data.release[index].content + '</a></td>'+
	// '</tr>').appendTo('table.tbl tbody');
	// }
}