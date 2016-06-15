/**
 *
 */

var jq = jQuery.noConflict();

function existLoginId($this) {
	jq(function() {
		var inputValue = $this.value;
		// console.log(inputValue);

		if (inputValue.length > 5) {	// テキストボックス内に値があるかどうかを確認(405エラー対策)
			existCheck(inputValue);

		}else{
			$("notice").innerHTML = "半角英数字(A～Z、a～z、0～9)で6～20文字";
			jq("#notice").css("color", "black");
		}

	});

}

var jq = jQuery.noConflict();

function existLoginIdEdit($this) {
	jq(function() {
		var inputValue = $this.value;
		var org = $("orginalId").value;
		// console.log(inputValue);

		if (inputValue.length > 5 && inputValue != org) {	// テキストボックス内に値があるかどうかを確認(405エラー対策)
			existCheck(inputValue);

		}else{
			$("notice").innerHTML = "半角英数字(A～Z、a～z、0～9)で6～20文字";
			jq("#notice").css("color", "black");
		}

	});

}

