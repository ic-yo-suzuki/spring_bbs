/**
 *
 */

function sendData(form, button, data){
	var jq = jQuery.noConflict();
	var result = false;
	jq.ajax({
		url : form.attr('action'),
		type : form.attr('method'),
		data : data,
		dataType: 'json',
		contentType: 'application/json; charset=UTF-8',
		timeout : 10000,

		beforeSend : function(xhr, settings) {
			button.attr('disabled', true);
		},

		complete : function(xhr, settings) {
			button.attr('disabled', false);
		},

		success : function(result, textStatus, xhr) {
			form[0].reset();
			alert("操作は正常に完了しました");
			location.reload();
		},

		error : function(xhr, textStatus, error) {
			console.log("XHR:" + xhr);
			console.log("textStatus:" + textStatus);
			console.log("Error:" + error);
			jq(messageArea).html("操作に失敗しました");
			alert("error");
		}

	});
	return result;
}

function existCheck(inputValue){
	var jq = jQuery.noConflict();
	console.log("url : /Spring_BBS/check/exist/loginid/"
			+ inputValue + "/");
	jq.ajax({
		type : "GET",
		url : "/Spring_BBS/check/exist/loginid/"
				+ inputValue + "/",	// URLに入力値を組み込む
		dataType : "json",
		contentType : "application/json; charset=UTF-8",
		success : function(data) {
			printResult(data);

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("XHR : " + XMLHttpRequest);
			console.log("textStatus" + textStatus);
			console.log("errorThrown" + errorThrown);
		}
	})
	function printResult(data) {
		console.log(data);
		var result = JSON.parse(data);	// JSON文字列→Boolean型変換
		if(!result){
			$("loginIdValidCheck").innerHTML = "入力されたユーザIDは使用可能です　　　　　";
			jq("#loginIdValidCheck").css("color", "green");
			jq("#loginId").css('background', 'white');

		}else{
			$("loginIdValidCheck").innerHTML = "入力されたユーザIDは既に使用されています";
			jq("#loginIdValidCheck").css("background", "white");
			jq("#loginIdValidCheck").css("color", "red");
			jq("#loginId").css('background', 'pink');
		}
	}

}
