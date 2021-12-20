package jframe.window.birthday;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class 문자발송 {

	private JFrame frame;

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
		} catch (Exception e) {

		}

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					문자발송 window = new 문자발송();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public 문자발송() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @return
	 */
	public JFrame initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\uC0DD\uC77C \uCD95\uD558 \uBB38\uC790 \uBC1C\uC1A1(feat.\uC0AC\uD68C\uC0DD\uD65C)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(12, 10, 410, 241);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
//		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		JButton btnNewButton = new JButton("<html>오늘 생일인 친구에게<br>축하문자를 보내세요!<html>");
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				ArrayList<Birthday> birthday = sendMessageAll();
				for (Birthday b : birthday) {
					messageSend(b);
				}
			}
		
		//db에서 정보 불러오기
		public ArrayList<Birthday> sendMessageAll() {
			ArrayList<Birthday> bList = new ArrayList<>();
			connect();

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
		
		//문자보내기
		public void messageSend(Birthday birthday) {
			String api_key = "NCSF9OS7AO9BR6VH";
			String api_secret = "ER0FCXGE5MHK8SOQV3SXRRMA4NIO819O";
			Message coolsms = new Message(api_key, api_secret);
			
			HashMap<String, String> params = new HashMap<String, String>();
			
			params.put("to", birthday.getTel());
			params.put("from", "01057030647");
			params.put("type", "SMS");
			params.put("text", "사랑하는 " + birthday.getName() + "(이)의 생일을 축하합니다!!!!!");
			params.put("app_version", "test app 1.2"); 
			try {
				coolsms.send(params);
			} catch (CoolsmsException e) {
				e.printStackTrace();
			}
		};
		});

		btnNewButton.setBounds(63, 32, 288, 94);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("나가기");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
	            JFrame newWin = new BirthdayGuiApp().initialize();
	            newWin.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(117, 160, 179, 50);
		panel.add(btnNewButton_1);

		return frame;
	}

}
