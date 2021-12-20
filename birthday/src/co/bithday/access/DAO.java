package co.bithday.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	public Connection conn;

	protected DAO() {
		String url = "jdbc:sqlite:D:/IT/sqlite/db/sample.db";
		try {
			conn = DriverManager.getConnection(url);
			System.out.println("연결되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
