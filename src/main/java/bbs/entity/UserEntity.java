package bbs.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEntity {

	@Getter
	@Setter
	private String loginId;

	@Getter
	@Setter
	private String name;

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