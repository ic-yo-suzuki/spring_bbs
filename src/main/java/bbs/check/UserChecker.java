package bbs.check;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class UserChecker {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Boolean isValidUser(int id) throws Exception {

		System.out.println("bbs.check.UserChecker#isValidUser running.");

		Boolean status;
		Long exist;
		exist = null;
		status = null;
		boolean result = false;
		Class.forName("org.gjt.mm.mysql.Driver");
		String url = "jdbc:mysql:///bbs?useUnicode=true&characterEncoding=UTF-8";
		Connection con = DriverManager.getConnection(url, "java", "ic-com.Test");
		try {
			QueryRunner qr = new QueryRunner();
			ResultSetHandler rs = new ScalarHandler();
			exist = (long) qr.query(con, "select count(*) as count from users where id = ?;", rs, id);
			status = (boolean) qr.query(con, "select status from users where id = ?;", rs, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

		if (exist != null && status != null) {
			if (status == true && exist == 1) {
				result = true;
			}
		}
		System.out.println("bbs.check.UserChecker#result is " + result);
		return result;
	}
}
