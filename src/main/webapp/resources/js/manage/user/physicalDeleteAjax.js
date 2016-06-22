/**
 *
 */

var jq = jQuery.noConflict();

jq(function($) {
	$("form#physicalDelete").submit(
			function(event) {

				var form = $(this);
				var id = parseInt(form.attr('value'));
				if (!confirm('このユーザを削除します。よろしいですか？(復元できません)')) {
					event.preventDefault();
					return;
				}

				console.log("Destination is "
						+ "/Spring_BBS/manage/user/delete/physical/id/" + id
						+ "/");
				event.preventDefault();
				$.ajax({
					type : "GET",
					url : "/Spring_BBS/manage/user/delete/physical/id/" + id
							+ "/",
					dataType : "json",
					contentType : "application/json; charset=UTF-8",
					success : function(data) {
						console.log("success");
						console.log(data);
						if (data.result == "success") {
							alert("ユーザの物理削除に成功しました");
						} else {
							alert("ユーザの物理削除に失敗しました");
						}
						location.reload();
					},
					error : function(XMLHttpResult, textStatus, errorThrown) {
						console.log("XHR : " + XMLHttpRequest);
						console.log("textStatus : " + textStatus);
						console.log("errorThrown : " + errorThrown);
						alert("エラーが発生しました");
						$("p#resultMessage").html("ユーザの物理削除に失敗しました");
					}

				})

			});
});
