package bbs.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserForm {
	@Size(min = 6, max = 20, message = "ログインIDは6文字以上20文字以下で入力してください")
	@Pattern(regexp = "^[0-9a-zA-Z]", message = "ログインIDに使えない文字があります")
	@NotNull
	String loginId;
	
}
