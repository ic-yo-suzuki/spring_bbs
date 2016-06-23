/**
 *
 */

var jq = jQuery.noConflict();

jq(function($) {
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
				console.log(ngWord);

				if (valid) {
					var ret = confirm("下記の内容で投稿します。よろしいですか？\n" + "タイトル："
							+ title.val() + "\n" + "カテゴリー：" + category.val()
							+ "\n" + "本文：" + text.val());
					if (!ret) {
						event.preventDefault();
					}
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
				+ "文字オーバーしています(" + length + "文字)";
		jq("textarea#text").css("background", "pink");
		jq("#textValidCheck").css("color", "red");
	} else if (length == 0 || !text.val().match(/\S/g)) {
		document.getElementById("textValidCheck").innerHTML = "本文を入力してください";
		jq("textarea#text").css("background", "pink");
		jq("#textValidCheck").css("color", "red");
	} else if (isExistNgWord(text.val())) {
		document.getElementById("textValidCheck").innerHTML = "本文中に使えないキーワードが含まれています";
		jq("textarea#text").css("background", "pink");
		jq("#textValidCheck").css("color", "red");
	} else {
		textStatus = true;
		document.getElementById("textValidCheck").innerHTML = "";
		jq("textarea#text").css("background", "white");
		jq("#textValidCheck").css("color", "black");
	}

	return textStatus;
}

function checkTitle(title) {
	var titleStatus = false;

	var length = title.val().length;

	if (length > 50) {
		document.getElementById("titleValidCheck").innerHTML = (length - 50)
				+ "文字オーバーしています(" + length + "文字)";
		jq("#titleValidCheck").css("color", "red");
		jq("input#title").css("background", "pink");

	} else if (length == 0 || !title.val().match(/\S/g)) {
		document.getElementById("titleValidCheck").innerHTML = "タイトルを入力してください";
		jq("#titleValidCheck").css("color", "red");
		jq("input#title").css("background", "pink");
	} else if (isExistNgWord(title.val())) {
		document.getElementById("titleValidCheck").innerHTML = "タイトル中に使えないキーワードが含まれています";
		jq("#titleValidCheck").css("color", "red");
		jq("input#title").css("background", "pink");
	} else {
		titleStatus = true;
		document.getElementById("titleValidCheck").innerHTML = "";
		jq("#titleValidCheck").css("color", "black");
		jq("input#title").css("background", "white");
	}
	return titleStatus;
}

function checkCategory(category) {
	var categoryStatus = false;
	var categoryValue = category.val();

	if (categoryValue.length == 0 || !categoryValue.match(/\S/g)) {
		document.getElementById("categoryValidCheck").innerHTML = "カテゴリを選択/入力してください";
		jq("select#cmbCategory").css("background", "pink");
		jq("input#txtCategory").css("background", "pink");
		jq("#categoryValidCheck").css("color", "red");

	} else if (isExistNgWord(category.val())) {
		document.getElementById("categoryValidCheck").innerHTML = "カテゴリに使えないキーワードが含まれています";
		jq("input#txtCategory").css("background", "pink");
		jq("#categoryValidCheck").css("color", "red");
	} else {
		categoryStatus = true;
		document.getElementById("categoryValidCheck").innerHTML = "";
		jq("select#cmbCategory").css("background", "white");
		jq("input#txtCategory").css("background", "white");
		jq("#categoryValidCheck").css("color", "black");
	}
	return categoryStatus;
}

function isExistNgWord(text) {
	var trimText;
	trimText = text.replace(/\s+/g, "");
	for (var i = 0; i < ngWord.length; i++) {
		if (trimText.indexOf(ngWord[i].word) != -1) {
			console.log("Hit!! Keyword is : " + ngWord[i].word);
			return true;
		}
	}
	return false;
}
