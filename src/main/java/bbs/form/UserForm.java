package bbs.form;

import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import bbs.validator.annotation.RefuseBlank;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserForm {
	@RefuseBlank(message = "ログインIDを入力してください")
	@Size(min = 6, max = 20, message = "ログインIDは6文字以上20文字以下で入力してください")
	@Pattern(regexp = "^[0-9a-zA-Z]*$", message = "ログインIDに使えない文字があります")
	@Getter
	@Setter
	private String loginId;

	@Size(max = 10, message = "名前は10文字以下で入力してください")
	@RefuseBlank(message = "名前を入力してください")
	@Getter
	@Setter
	private String name;

	@Size(min = 6, max = 255, message = "パスワードは6文字以上255文字以下で入力してください")
	@RefuseBlank(message = "パスワードを入力してください")
	@Getter
	@Setter
	private String password;

	@Getter
	@Setter
	private int id, branchId, departmentId;

	@Getter
	@Setter
	private String branchName, departmentName;

	@Getter
	private String elapsedTimeText;

	@Getter
	@Setter
	private boolean status;
	@Getter
	@Setter
	private long elapsedTime;
	@Getter
	@Setter
	private Date lastLoginDate;

	public void setElapsedTimeText(long elapsedTime) {

		if (elapsedTime / 60 < 1) {
			this.elapsedTimeText = String.valueOf(elapsedTime) + "秒前";

		}
		if (elapsedTime / 60 >= 1) {
			this.elapsedTimeText = String.valueOf(elapsedTime / 60) + "分前";

		}
		if (elapsedTime / 3600 >= 1) {
			this.elapsedTimeText = String.valueOf(elapsedTime / 3600) + "時間前";

		}
		if (elapsedTime / 86400 >= 1) {
			this.elapsedTimeText = String.valueOf(elapsedTime / 86400) + "日前";

		}
		if (elapsedTime / 604800 >= 1) {
			this.elapsedTimeText = String.valueOf(elapsedTime / 604800) + "週間前";

		}
		if (elapsedTime / 2592000 >= 1) {
			this.elapsedTimeText = String.valueOf(elapsedTime / 2592000) + "ヶ月前";

		}
		if (elapsedTime / 31536000 >= 1) {
			this.elapsedTimeText = String.valueOf(elapsedTime / 31536000) + "年前";

		}
	}

}
