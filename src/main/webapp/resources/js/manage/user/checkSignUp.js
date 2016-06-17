/**
 *
 */

var jq = jQuery.noConflict();

function checkName($this){
	if($this.value.length > 10){
		$("nameValidCheck").innerHTML = ($this.value.length - 10) + "文字オーバーです(" + $this.value.length + "文字)";
	}else{
		$("nameValidCheck").innerHTML = "10文字以下";
	}

}

function existLoginId($this) {
	jq(function() {
		var inputValue = $this.value;
		 console.log(inputValue);
		if (inputValue.length > 5) {	// テキストボックス内に値があるかどうかを確認(405エラー対策)
			console.log(inputValue.length);
			if(!(inputValue.match(/^[0-9a-zA-Z]*$/))){
				console.log(!(inputValue.match(/^[0-9a-zA-Z]*$/)));

				$("notice").innerHTML = "ログインIDに使えない文字(全角文字、記号)があります";
				jq("#notice").css("background", "#FEFED7");
				jq("#loginId").css('background', 'pink');
			}else{
				console.log(!(inputValue.match(/^[0-9a-zA-Z]*$/)));
				if(existCheck(inputValue)){
					jq("#notice").css('background', '#d2ffd2');
				}
			}
		}else{
			$("notice").innerHTML = "半角英数字(A～Z、a～z、0～9)で6～20文字";
			jq("#notice").css("color", "black");
			jq("#notice").css('background', 'white');
			jq("#loginId").css('background', 'white');
		}

	});

}

function checkPassword($this){
}

function checkSameValueOfPassword($this){
	checkPassword($this);
	$("passwordValidCheck").innerHTML = "記号含む半角文字で6～255文字";
	if($this.value != $("password").value){
		$("passwordValidCheck").innerHTML = "パスワードが一致しません";
		console.log("不一致");
	}else{
		$("passwordValidCheck").innerHTML = "パスワードが一致しました";
		jq("#passwordValidCheck").css("background", "#d2ffd2");
		console.log("一致");
	}

}

function enableButton(){

}

function changeColors(){

}