package co.bithday.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.bithday.model.Birthday;
import message.MessageSend;

public class BirthdayDAO extends DAO implements BirthdayAccess {
	static PreparedStatement psmt;
	static ResultSet rs;
	static Connection conn;

	public static void connect() {
		String url = "jdbc:sqlite:D:/IT/sqlite/db/sample.db";
		try {
			conn = DriverManager.getConnection(url);
			System.out.println("연결되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close() {
		try {
			if (!rs.isClosed()) {
				rs.close();
			}

			if (!psmt.isClosed()) {
				psmt.close();
			}

			if (!conn.isClosed()) {
				conn.close();
			}
		} catch(Exception e) {
			
		}
		
	}

	@Override
	public void insert(Birthday birthday) {
		System.out.println(birthday);
		connect();
		try {
			psmt = conn.prepareStatement("insert into birthday(name,sex,birth,tel) values(?,?,?,?)");
			psmt.setString(1, birthday.getName());
			psmt.setString(2, birthday.getSex());
			psmt.setString(3, birthday.getBirth());
			psmt.setString(4, birthday.getTel());
			int r = psmt.executeUpdate();
			System.out.println(r + "건이 입력되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	@Override
	public void update(Birthday birthday, String searchedName) {
		System.out.println(birthday);
		connect();
		try {
			psmt = conn.prepareStatement("update birthday set name = ?, sex = ?, birth = ?, tel = ? where name = ?");
			psmt.setString(1, birthday.getName());
			psmt.setString(2, birthday.getSex());
			psmt.setString(3, birthday.getBirth());
			psmt.setString(4, birthday.getTel());
			psmt.setString(5, searchedName);
			int r = psmt.executeUpdate();
			System.out.println(r + "건 변경되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

	}

	@Override
	public void delete(String name) {
		connect();
		try {
			psmt = conn.prepareStatement("delete from birthday where name = ?");
			psmt.setString(1, name);
			int r = psmt.executeUpdate();
			System.out.println(r + "건 삭제완료.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	@Override
	public ArrayList<Birthday> selectAll() {
		ArrayList<Birthday> bList = new ArrayList<>();
		connect();
		try {
			psmt = conn.prepareStatement("select * from birthday");
			rs = psmt.executeQuery();
			while (rs.next()) {
				Birthday birthday = new Birthday();
				birthday.setName(rs.getString("name"));
				birthday.setSex(rs.getString("sex"));
				birthday.setBirth(rs.getString("birth"));
				birthday.setTel(rs.getString("tel"));
				bList.add(birthday);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return bList;
	}

	@Override
	public Birthday selectName(String name) {
		connect();
		ArrayList<Birthday> birthdayList = new ArrayList<>();
		Birthday b = null;
		try {
			psmt = conn.prepareStatement("select * from birthday where name = ?");
			psmt.setString(1, name);
			rs = psmt.executeQuery();
			while (rs.next()) {
				b = new Birthday();
				b.setName(rs.getString("name"));
				b.setSex(rs.getString("sex"));
				b.setBirth(rs.getString("birth"));
				b.setTel(rs.getString("tel"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return b;
	}

	@Override
	public ArrayList<Birthday> selectBirth(String birth) {
		connect();
		ArrayList<Birthday> birthdayList = new ArrayList<>();
		Birthday b = null;
		try {
			psmt = conn.prepareStatement("select * from birthday where birth = ?");
			psmt.setString(1, birth);
			rs = psmt.executeQuery();
			while (rs.next()) {
				b = new Birthday();
				b.setName(rs.getString("name"));
				b.setSex(rs.getString("sex"));
				b.setBirth(rs.getString("birth"));
				b.setTel(rs.getString("tel"));
				birthdayList.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return birthdayList;
	}

	@Override
	public ArrayList<Birthday> sendMessageAll() {
		connect();
		ArrayList<Birthday> bList = new ArrayList<>();

		Date nowDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String today = simpleDateFormat.format(nowDate).substring(4);
	
		try {
			psmt = conn.prepareStatement("select * from birthday where birth like ?");
			psmt.setString(1, "%" + today);
			rs = psmt.executeQuery();
			while (rs.next()) {
				Birthday birthday = new Birthday();
				birthday.setName(rs.getString("name"));
				birthday.setSex(rs.getString("sex"));
				birthday.setBirth(rs.getString("birth"));
				birthday.setTel(rs.getString("tel").replace("-", ""));
				bList.add(birthday);
				System.out.println(birthday);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return bList;
	}
}
