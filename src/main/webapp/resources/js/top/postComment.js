/**
 *
 */

$(function() {
	$('form#postComment').submit(function(event) {
		event.preventDefault();

		var form = $(this);
		var button = form.find('button'); 	// データ送信中の二十送信防止のために使用
		var textarea = form.find('textarea');	// ボタン押下されたフォームにあるtextareaを取得

		var userId = $('#postComment [id = userId]').val();	// 投稿者のIDを取得
		var postId = form.attr('value');	// 対象となる投稿のIDを取得(formのvalue属性に指定したもの)
		var text = textarea.val();	// コメント本文を取得(対象となるform中にあるtextareaのもの)

		var data = JSON.stringify({		// JSONデータの生成
			userId : parseInt(userId),
			postId : parseInt(postId),
			text : text
		});
//		alert("text:" + text);
//		alert(data);
		console.log("Data Destination : " + form.attr("action"));
		console.log("Method : " + form.attr('method'));

		sendData(form, button, data);


	});
});
