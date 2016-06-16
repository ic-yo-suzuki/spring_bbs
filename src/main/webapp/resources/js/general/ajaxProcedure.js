/**
 *
 */

function sendData(form, button, data){
	var jq = jQuery.noConflict();
	jq.ajax({
		url : form.attr('action'),
		contentType: 'application/json; charset=utf-8',
		type : form.attr('method'),
		dataType: 'json',
		data : data,
		timeout : 10000,

		beforeSend : function(xhr, settings) {
			button.attr('disabled', true);
		},

		complete : function(xhr, settings) {
			button.attr('disabled', false);
		},

		success : function(result, textStatus, xhr) {
			form[0].reset();
			alert('OK');
			location.reload();
		},

		error : function(xhr, textStatus, error) {
			alert("NG...");
			console.log("XHR:" + xhr);
			console.log("textStatus:" + textStatus);
			console.log("Error:" + error);
		}

	});
}

function existCheck(inputValue){
	var jq = jQuery.noConflict();
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
			error(XMLHttpRequest, textStatus, errorThrown);
		}
	})
	function printResult(data) {
		var result = JSON.parse(data);	// JSON文字列→Boolean型変換
		if(!result){
			$("notice").innerHTML = "入力されたユーザIDは使用可能です　　　　　";
			jq("#notice").css("color", "black");
			jq("#loginId").css('background', 'white');
		}else{
			$("notice").innerHTML = "入力されたユーザIDは既に使用されています";
			jq("#notice").css("color", "red");
			jq("#loginId").css('background', 'pink');
		}
	}

}
