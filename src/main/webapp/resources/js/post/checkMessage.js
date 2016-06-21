/**
 *
 */

$(function() {
	$("form#postMessageForm").submit(
			function(event) {
				setCategory(); // Formにあるhidden属性のinputにカテゴリをセット
				var form = $(this);
				var category = form.find("input#category");
				var text = form.find("textarea#text");
				var title = form.find("input#title");

				var valid = true;

				valid &= checkText(text);
				valid &= checkTitle(title);
				valid &= checkCategory(category);

				console.log("Validation Result : " + valid);

				if (valid) {
					var ret = confirm("下記の内容で投稿します。よろしいですか？\n" + "タイトル："
							+ title.val() + "\n" + "カテゴリー：" + category.val()
							+ "\n" + "本文：" + text.val());
				} else {
					alert("NG：フォーム上のエラーメッセージを確認の上修正してください");
					event.preventDefault();
				}

			});
});

function checkText(text) {

	var textStatus = false;
	var length = text.val().length;
	if (length > 1000) {
		document.getElementById("textValidCheck").innerHTML = (length - 1000)
				+ "文字オーバーしています(" + $length + "文字)";
		$("textarea#text").css("background", "pink");
		$("#textValidCheck").css("color", "red");
	} else if (length == 0 || !text.val().match(/\S/g)) {
		document.getElementById("textValidCheck").innerHTML = "本文を入力してください";
		$("textarea#text").css("background", "pink");
		$("#textValidCheck").css("color", "red");
	} else {
		textStatus = true;
		document.getElementById("textValidCheck").innerHTML = "";
		$("textarea#text").css("background", "white");
		$("#textValidCheck").css("color", "black");
	}

	return textStatus;
}

function checkTitle(title) {
	var titleStatus = false;

	var length = title.val().length;

	if (length > 50) {
		document.getElementById("titleValidCheck").innerHTML = (length - 50)
				+ "文字オーバーしています(" + length + "文字)";
		$("#titleValidCheck").css("color", "red");
		$("input#title").css("background", "pink");

	} else if (length == 0 || !title.val().match(/\S/g)) {
		document.getElementById("titleValidCheck").innerHTML = "タイトルを入力してください";
		$("#titleValidCheck").css("color", "red");
		$("input#title").css("background", "pink");
	} else {
		titleStatus = true;
		document.getElementById("titleValidCheck").innerHTML = "";
		$("#titleValidCheck").css("color", "black");
		$("input#title").css("background", "white");
	}
	return titleStatus;
}

function checkCategory(category) {
	var categoryStatus = false;
	var categoryValue = category.val();

	if (categoryValue.length == 0 || !categoryValue.match(/\S/g)) {
		document.getElementById("categoryValidCheck").innerHTML = "カテゴリを選択/入力してください";
		$("select#cmbCategory").css("background", "pink");
		$("input#txtCategory").css("background", "pink");
		$("#categoryValidCheck").css("color", "red");

	} else {
		categoryStatus = true;
		document.getElementById("categoryValidCheck").innerHTML = "";
		$("select#cmbCategory").css("background", "white");
		$("input#txtCategory").css("background", "white");
		$("#categoryValidCheck").css("color", "black");
	}
	return categoryStatus;
}
