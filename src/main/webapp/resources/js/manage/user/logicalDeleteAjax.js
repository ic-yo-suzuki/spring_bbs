/**
 *
 */
var jq = jQuery.noConflict();

jq(function($) {
	$("form#logicalDelete").submit(
			function(event) {

				var form = $(this);
				var id = parseInt(form.attr('value'));
				if (!confirm('このユーザを' + form.find('button').context[0].innerHTML + 'します。よろしいですか？')) {
					event.preventDefault();
					return;
				}

				console.log("Destination is "
						+ "/Spring_BBS/manage/user/delete/logical/id/" + id
						+ "/");
				event.preventDefault();
				$.ajax({
					type : "GET",
					url : "/Spring_BBS/manage/user/delete/logical/id/" + id
							+ "/",
					dataType : "json",
					contentType : "application/json; charset=UTF-8",
					success : function(data) {
						printResult(data, form);
					},
					error : function(XMLHttpResult, textStatus, errorThrown) {
						console.log("XHR : " + XMLHttpRequest);
						console.log("textStatus : " + textStatus);
						console.log("errorThrown : " + errorThrown);
						alert("エラーが発生しました");
						$("p#resultMessage").html("ユーザの論理削除に失敗しました");
					}
				})

			});
});

function printResult(result, form) {
	var id = parseInt(form.attr('value'));
	var button = form.find('button').context[0];

	if (result.result == "success") {
		if (!result.userStatus) {
			jq("p#resultMessage").html("ユーザの論理削除に成功しました");
			button.innerHTML = "復活";

		} else {
			jq("p#resultMessage").html("ユーザの復元に成功しました");
			button.innerHTML = "停止";
		}
	} else {
		jq("p#resultMessage").html("ユーザの論理削除に失敗しました");
	}
}