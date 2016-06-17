/**
 *
 */

// それぞれの状態を判定するためのフラグ
var textStatus = false;
var titleStatus = false;
var categoryStatus = false;

/*
 * 本文の文字数をカウント。 1～1000文字の範囲内であればtrue、そうでなければfalseをflgにセットする。
 * その後、flgの値を基にボタンの有効/無効切り替えを実施
 */
function checkText($this) {

	var button = document.getElementById("submit-button");
	textStatus = false;
	colorChange('normal', "#textValidCheck", "#text");
	if ($this.value.length > 1000) {
		document.getElementById("textValidCheck").innerHTML = ($this.value.length - 1000)
				+ "文字オーバーしています(" + $this.value.length + "文字)";
		colorChange('error', "#textValidCheck", "#text");
	} else if ($this.value.length == 0) {
		document.getElementById("textValidCheck").innerHTML = "";
		colorChange('normal', "#textValidCheck", "#text");
	} else if ($this.value.length > 969) {
		document.getElementById("textValidCheck").innerHTML = "あと"
				+ (1000 - $this.value.length) + "文字入力できます("
				+ $this.value.length + "文字)";
		colorChange('notice', "#textValidCheck", "#text");
		textStatus = true;
	} else {
		document.getElementById("textValidCheck").innerHTML = "あと"
				+ (1000 - $this.value.length) + "文字入力できます("
				+ $this.value.length + "文字)";
		textStatus = true;
	}
	buttonEnable(textStatus, titleStatus, categoryStatus, button);
}

/*
 * タイトルの文字数をカウント。 1～50文字の範囲内であればtrue、そうでなければfalseをflgにセットする。
 * その後、flgの値を基にボタンの有効/無効切り替えを実施
 */
function checkTitle($this) {
	var button = document.getElementById("submit-button");
	colorChange('normal', "#titleValidCheck", "#title");
	titleStatus = false;
	if ($this.value.length > 50) {
		document.getElementById("titleValidCheck").innerHTML = ($this.value.length - 50)
				+ "文字オーバーしています(" + $this.value.length + "文字)";
		colorChange('error', "#titleValidCheck", "#title");

	} else if ($this.value.length == 0) {
		document.getElementById("titleValidCheck").innerHTML = "";
		colorChange('normal', "#titleValidCheck", "#title");
	} else if ($this.value.length > 39) {
		document.getElementById("titleValidCheck").innerHTML = "あと"
				+ (50 - $this.value.length) + "文字入力できます(" + $this.value.length
				+ "文字)";
		colorChange('notice', "#titleValidCheck", "#title");
		titleStatus = true;
	} else {
		document.getElementById("titleValidCheck").innerHTML = "あと"
				+ (50 - $this.value.length) + "文字入力できます(" + $this.value.length
				+ "文字)";
		titleStatus = true;
	}
	buttonEnable(textStatus, titleStatus, categoryStatus, button);
}

/*
 * カテゴリの文字数をカウント。セレクトボックスの場合、1以上あればtrue、そうでなければfalseをflgにセットする。
 * テキストボックスの場合、1～10文字であればtrue、そうでなければfalseをflgにセットする
 * その後、flgの値を基にボタンの有効/無効切り替えを実施
 */
function checkCategory($this) {
	var button = document.getElementById("submit-button");
	categoryStatus = false;
	switch ($this.name) {
	case "selectCategory":
		if ($this.value.length == 0) {
			document.getElementById("selectCategoryValidCheck").innerHTML = "カテゴリを選択してください";
			colorChange('error', '#selectCategoryValidCheck', '#cmbCategory');
		} else {
			document.getElementById("selectCategoryValidCheck").innerHTML = "";
			colorChange('normal', '#selectCategoryValidCheck', '#cmbCategory');
			categoryStatus = true;
		}
		break;
	case "createCategory":
		if ($this.value.length === 0) {
			colorChange('error', '#createCategoryValidCheck', '#txtCategory');
			document.getElementById("createCategoryValidCheck").innerHTML = "カテゴリを入力してください";
		} else if ($this.value.length > 10) {
			document.getElementById("createCategoryValidCheck").innerHTML = ($this.value.length - 10)
					+ "文字オーバーしています(" + $this.value.length + "文字)";
			colorChange('error', '#createCategoryValidCheck', '#txtCategory');
		} else if ($this.value.length > 7) {
			colorChange('notice', '#createCategoryValidCheck', '#txtCategory');
			document.getElementById("createCategoryValidCheck").innerHTML = "あと"
					+ (10 - $this.value.length)
					+ "文字入力できます("
					+ $this.value.length + "文字)";
			categoryStatus = true;
		} else {
			document.getElementById("createCategoryValidCheck").innerHTML = "あと"
					+ (10 - $this.value.length)
					+ "文字入力できます("
					+ $this.value.length + "文字)";
			colorChange('normal', '#createCategoryValidCheck', '#txtCategory');
			categoryStatus = true;

		}

		break;
	default:
		console.log("error");
	}
	buttonEnable(textStatus, titleStatus, categoryStatus, button);
}

/*
 * バリデーションメッセージを表示する領域、テキストボックス、セレクトボックスの色を変更
 */

function colorChange(mode, validDivDom, textDom) {
	if (mode === 'normal') {
		$(validDivDom).css('color', 'black');
		$(textDom).css('background', 'white');
	} else if (mode === 'error') {
		$(validDivDom).css('color', 'red');
		$(textDom).css('background', 'pink');
	} else if (mode === 'notice') {
		$(textDom).css('background', '#FFFF99');
	}

}

/*
 * 送信ボタンの有効/無効切り替えを行う
 *
 */

function buttonEnable(textStatus, titleStatus, categoryStatus, button) {
	if (textStatus && titleStatus && categoryStatus) {
		$(button).attr('disabled', false);
	} else {
		$(button).attr('disabled', true);
	}

}
