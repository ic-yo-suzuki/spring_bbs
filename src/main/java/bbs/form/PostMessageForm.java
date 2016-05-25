package bbs.form;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Lazy;

import lombok.Getter;
import lombok.Setter;

@Lazy
@Getter
public class PostMessageForm {
	@Lazy
	@Setter
	@Size(min = 1, max = 10, message = "カテゴリを10文字以内で入力するか、選択してください")
//	@NotNull(message = "カテゴリを選択するか、入力してください")
	private String category;
	@Setter
	private String name;
	@Setter
	@NotNull(message = "本文を入力してください")
	@Size(min = 1, max = 1000, message = "本文は1000文字以下で入力してください")
	private String text;
	@Setter
	@NotNull(message = "タイトルを入力してください")
	@Size(min = 1, max = 50, message = "タイトルは50文字以内で入力してください")
	private String title;
	private String elapsedTimeText;
	@Setter
	private long elapsedTime;
	@Setter
	private int id, branchId, departmentId, userId;
	@Setter
	private Date insertDate;

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
