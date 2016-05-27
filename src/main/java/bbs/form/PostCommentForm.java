package bbs.form;

import java.util.Date;

import javax.validation.constraints.Size;

import bbs.validator.annotation.RefuseBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
public class PostCommentForm {
	@Setter
	private int id, postId, userId, branchId, departmentId;
	@Setter
	private long elapsedTime;
	@Setter
	@Size(max = 500, message = "コメントは500文字以内で入力してください")
	@RefuseBlank(message = "コメントを入力してください")
	private String text;
	@Setter
	private String name;
	private String elapsedTimeText;
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
