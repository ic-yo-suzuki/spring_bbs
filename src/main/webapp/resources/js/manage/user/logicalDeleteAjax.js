/**
 *
 */
var jq = jQuery.noConflict();

jq(function($) {
	$("form#logicalDelete").submit(
			function(event) {

				var form = $(this);
				var button = form.find('button#logicalDelete');
				var id = parseInt(form.attr('value'));

				console.log("Destination is "
						+ "/Spring_BBS/manage/user/delete/logical/id/" + id
						+ "/");
				event.preventDefault();
				 $.ajax({
					type : "GET",
					url : "/Spring_BBS/manage/user/delete/logical/id/" + id
							+ "/",
					dataType : "json",
					contentType : "application/json; charset=utf-8",
					success : function() {
						printResult(data);
					},
					error : function(XMLHttpResult, textStatus, errorThrown) {
						console.log("XHR : " + XMLHttpRequest);
						console.log("textStatus : " + textStatus);
						console.log("errorThrown : " + errorThrown);
						alert("エラーが発生しました");
						$("p#resultMessage").html("ユーザの論理削除に失敗しました");
					}
				})

				function printResult(data) {
					var result = JSON.parse(data);
					if (result === "success") {

						$("p#resultMessage").html("ユーザの論理削除に成功しました");
					} else {
						("p#resultMessage").html("ユーザの論理削除に失敗しました");
					}
				}
			});
});