/**
 *
 */

var jq = jQuery.noConflict();


function existLoginIdEdit($this) {
	jq(function() {
		var inputValue = $this.value;
		var org = $("orginalId").value;

		if (inputValue.length > 5 && inputValue != org) {	// テキストボックス内に値があるかどうかを確認(405エラー対策)
			if(!inputValue.match(/^[0-9a-zA-Z]*$/)){

				$("loginIdValidCheck").innerHTML = "ログインIDに使えない文字(全角文字、記号)があります";
				jq("#loginIdValidCheck").css("color", "#FEFED7");
				jq("#loginId").css('background', 'pink');
			}else{
				existCheck(inputValue);
				
			}
		}else{
			$("loginIdValidCheck").innerHTML = "半角英数字(A～Z、a～z、0～9)で6～20文字";
			jq("#loginIdValidCheck").css("color", "black");
			jq("#loginIdValidCheck").css('background', 'white');
			jq("#loginId").css('background', 'white');
		}

	});

}

