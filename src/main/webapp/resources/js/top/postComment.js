/**
 *
 */

jq(function($) {
	$('form#postComment').submit(function(event) {
		event.preventDefault();

		var valid = false;
		var form = $(this);
		var button = form.find('button'); 	// データ送信中の二十送信防止のために使用
		var textarea = form.find('textarea');	// ボタン押下されたフォームにあるtextareaを取得
		var messageArea = form.find("div");


		if(isExistNgWord(textarea.val())){
			messageArea.html("コメントに使用できない文字が含まれています");
			$(messageArea).css('color', 'red');
			$(textarea).css('background', 'pink');
			return;
		}
		if(textarea.val().match(/\S/g)){
			var userId = $('#postComment [id = userId]').val();	// 投稿者のIDを取得
			var postId = form.attr('value');	// 対象となる投稿のIDを取得(formのvalue属性に指定したもの)
			var text = textarea.val();	// コメント本文を取得(対象となるform中にあるtextareaのもの)
			valid = true;
		}
		var data = JSON.stringify({		// JSONデータの生成
			userId : parseInt(userId),
			postId : parseInt(postId),
			text : text
		});
		console.log("Data Destination : " + form.attr("action"));
		console.log("Method : " + form.attr('method'));
		console.log("Data : " + data);
		if(valid){
			if(sendData(form, button, data)){
				alert("コメントが投稿されました");
			}else{
				messageArea.html("コメントの投稿に失敗しました");
			}
		}else{
			$(textarea).css("background", "pink");
			$(messageArea).html("コメントを入力してください");
		}
	});
});

function isExistNgWord(text) {
	var trimText;
	console.log("before : " + text)
	trimText = text.replace(/\s+/g, "");

	console.log("after  : " + trimText);

	for (var i = 0; i < ngWord.length; i++) {
		console.log(ngWord[i].word);
		if (trimText.indexOf(ngWord[i].word) != -1) {
			console.log("Hit!! Keyword is : " + ngWord[i].word);
			return true;
		}
	}
	return false;
}
