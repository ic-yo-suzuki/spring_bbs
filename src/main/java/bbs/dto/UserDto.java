package bbs.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
public class UserDto {

	@Setter
	private String loginId;

	@Setter
	private String name;

	@Setter
	private String password;

	@Setter
	private int id, branchId, departmentId;
	@Setter
	private String branchName, departmentName;

	private String elapsedTimeText;

	@Setter
	private boolean status;
	@Setter
	private long elapsedTime;
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