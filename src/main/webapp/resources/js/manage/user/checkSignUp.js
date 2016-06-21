/**
 *
 */

var jq = jQuery.noConflict();


 function existLoginId(loginId) {
	var result = true;
	jq(function() {
		var inputValue = loginId.value;
		if ((inputValue.match(/\S/g)) && inputValue.length > 5) { // テキストボックス内に値があるかどうかを確認(405エラー対策)
			if (!existCheck(inputValue)) {
			}
		} else {
			$("loginIdValidCheck").innerHTML = "半角英数字(A～Z、a～z、0～9)で6～20文字";
			jq("#loginIdValidCheck").css("color", "black");
			jq("#loginIdValidCheck").css('background', 'white');
			jq("#loginId").css('background', 'white');
		}
	});
 }

jq(function($) {
	$("form#userForm").submit(function(event) {
		console.log(this);
		var form = $(this);
		console.log(form);
		var name = form.find("input#name");
		var loginId = form.find("input#loginId");
		var password = form.find("input#password");
		var passwordVerify = form.find("input#password_verify");
		var branch = form.find("select#branchName");
		var department = form.find("select#departmentName");

		var valid = true;


		valid &= checkLoginId(loginId);
		valid &= checkName(name);
		valid &= checkPassword(password, passwordVerify);

		var passhide = "";
		for(var i = 0; i < password.val().length; i++){
			passhide += "*";
		}


		if(valid){
			var ret = confirm("下記の内容で登録します。よろしいですか？\n" + "名前："
					+ name.val() + "\nログインID：" + loginId.val() + "\nパスワード："
					+ passhide +
					"\n所属：" + branch.val() + " " + department.val());
			if(!ret){
				event.preventDefault();
			}
		}else{
			alert("ng");
			event.preventDefault();
		}

	});

	function checkPassword(password, passwordVerify) {
		var result = true;
		$("input#password").css("background", "white");
		$("input#password_verify").css("background", "white");
		$("td#passwordValidCheck").html("記号含む半角文字で6～255文字");
		if (password.val() != passwordVerify.val()) {
			$("td#passwordValidCheck").html("パスワードが一致しません");
			$("td#passwordValidCheck").css("color", "red");
			result = false;
			$("input#password").css("background", "pink");
			$("input#password_verify").css("background", "pink");

		} else if(password.val().length < 6 || passwordVerify.val().length <  6) {
			$("td#passwordValidCheck").html("パスワードの文字数が不正です");
			$("td#passwordValidCheck").css("color", "red");
			$("input#password").css("background", "pink");
			$("input#password_verify").css("background", "pink");
			result = false;
		}else{
			$("td#passwordValidCheck").html("OK");
			$("td#passwordValidCheck").css("color", "green");
			$("input#password").css("background", "white");
			$("input#password_verify").css("background", "white");
		}
		return result;
	}

	function checkName(name) {
		var result = true;
		$(name).css("background", "white");
		if(name.val().length > 10 || !name.val().match(/\S/g)){
			$(name).css("background", "pink");
			$("td#nameValidCheck").html("名前を正しく入力してください(1～10文字)");
			$("td#nameValidCheck").css("color", "red");
			result = false;
		}else{
			$("td#nameValidCheck").html("OK");
			$("td#nameValidCheck").css("color", "green");
			$(name).css("background", "white");
		}
		return result;

	}
	function checkLoginId(loginId){
		var result = true;
		$("#loginId").css('background', 'white');
		if (!(loginId.val().match(/^[0-9a-zA-Z]*$/))) {
			$("td#loginIdValidCheck").html("ログインIDに使えない文字(全角文字、記号)があります");
			$("td#loginIdValidCheck").css("color", "red");
			$("#loginId").css('background', 'pink');
			result = false;
		}else{
			$("td#loginIdValidCheck").html("OK");
			$("td#loginIdValidCheck").css("color", "green");
			$("#loginId").css('background', 'white');
		}
		return result;
	}
});
