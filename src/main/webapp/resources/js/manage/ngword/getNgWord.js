/**
 *
 */

var ngWord;
console.log("NGワード読み込み開始");
var jq = jQuery.noConflict();
getNgWord();

function getNgWord(){
	jq(function($){
		console.log("Ajax通信開始");
		$.ajax({
			type : "GET",
			url : "/Spring_BBS/manage/ngword/get/",
			dataType : "json",
			contentType : "application/json; charset=UTF-8",
			success : function(data) {
				ngWord = data;
				console.log("Ajax通信成功");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log("XHR : " + XMLHttpRequest);
				console.log("textStatus" + textStatus);
				console.log("errorThrown" + errorThrown);
				console.log("Ajax通信失敗");
			}
		});
	});
}

window.onload = getNgWord;
console.log("NGワード読み込み終了");
