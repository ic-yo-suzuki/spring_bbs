/**
 *
 */

var jq = jQuery.noConflict();
function existLoginId($this) {
	jq(function() {
		var inputValue = $this.value;
		 console.log(inputValue);
		if (inputValue.length > 5) {	// テキストボックス内に値があるかどうかを確認(405エラー対策)
			console.log(inputValue.length);
			if(!(inputValue.match(/^[0-9a-zA-Z]*$/))){
				console.log(!(inputValue.match(/^[0-9a-zA-Z]*$/)));

				$("notice").innerHTML = "ログインIDに使えない文字(全角文字、記号)があります";
				jq("#notice").css("color", "red");
				jq("#loginId").css('background', 'pink');
			}else{
				console.log(!(inputValue.match(/^[0-9a-zA-Z]*$/)));
				existCheck(inputValue);
			}
		}else{
			$("notice").innerHTML = "半角英数字(A～Z、a～z、0～9)で6～20文字";
			jq("#notice").css("color", "black");
			jq("#loginId").css('background', 'white');
		}

	});

}

function existLoginIdEdit($this) {
	jq(function() {
		var inputValue = $this.value;
		var org = $("orginalId").value;

		if (inputValue.length > 5 && inputValue != org) {	// テキストボックス内に値があるかどうかを確認(405エラー対策)
			if(!inputValue.match(/^[0-9a-zA-Z]*$/)){

				$("notice").innerHTML = "ログインIDに使えない文字(全角文字、記号)があります";
				jq("#notice").css("color", "red");
				jq("#loginId").css('background', 'pink');
			}else{
				existCheck(inputValue);
			}
		}else{
			$("notice").innerHTML = "半角英数字(A～Z、a～z、0～9)で6～20文字";
			jq("#notice").css("color", "black");
			jq("#loginId").css('background', 'white');
		}

	});

}

