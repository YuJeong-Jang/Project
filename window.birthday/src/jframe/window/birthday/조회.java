package jframe.window.birthday;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

public class 조회 {

	JFrame frame;
	private JTextField txtname;
	private JTextField txtbirth;

	static PreparedStatement psmt;
	static ResultSet rs;
	static Connection conn;
	private JTable table;

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
					조회 window = new 조회();
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
	public 조회() {
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
		panel.setBorder(new TitledBorder(null, "\uC870\uD68C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 10, 410, 241);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("이름");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 13, 109, 26);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("생일");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_1.setBounds(12, 49, 109, 21);
		panel.add(lblNewLabel_1);

		txtname = new JTextField();
		txtname.setBounds(133, 13, 215, 27);
		panel.add(txtname);
		txtname.setColumns(10);

		txtbirth = new JTextField();
		txtbirth.setColumns(10);
		txtbirth.setBounds(133, 46, 215, 27);
		panel.add(txtbirth);

		JButton btnNewButton = new JButton("전체조회");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Birthday> bList = new ArrayList<>();
				connect();

				try {
					psmt = conn.prepareStatement("select * from birthday");
					rs = psmt.executeQuery();
					tableAll();
					while (rs.next()) {
						Birthday birthday = new Birthday();
						birthday.setName(rs.getString("name"));
						birthday.setSex(rs.getString("sex"));
						birthday.setBirth(rs.getString("birth"));
						birthday.setTel(rs.getString("tel"));
						bList.add(birthday);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					close();
				}
				return;
			}
		});

		btnNewButton.setBounds(12, 74, 95, 33);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("이름조회");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect();
				ArrayList<Birthday> birthdayList = new ArrayList<>();
				Birthday b = null;

				String bname;
				bname = txtname.getText();

				try {
					psmt = conn.prepareStatement("select * from birthday where name = ?");
					psmt.setString(1, bname);
					rs = psmt.executeQuery();
					tableName();
					while (rs.next()) {
						b = new Birthday();
						b.setName(rs.getString("name"));
						b.setSex(rs.getString("sex"));
						b.setBirth(rs.getString("birth"));
						b.setTel(rs.getString("tel"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					close();
				}
				return;
			}
		});
		btnNewButton_1.setBounds(112, 74, 95, 33);
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("생일조회");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect();

				String bbirth;
				bbirth = txtbirth.getText();

				ArrayList<Birthday> birthdayList = new ArrayList<>();
				Birthday b = null;
				try {
					psmt = conn.prepareStatement("select * from birthday where birth = ?");
					psmt.setString(1, bbirth);
					rs = psmt.executeQuery();
					tableBirth();
					while (rs.next()) {
						b = new Birthday();
						b.setName(rs.getString("name"));
						b.setSex(rs.getString("sex"));
						b.setBirth(rs.getString("birth"));
						b.setTel(rs.getString("tel"));
						birthdayList.add(b);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					close();
				}
				return;
			}
		});
		btnNewButton_2.setBounds(209, 74, 95, 33);
		panel.add(btnNewButton_2);

		table = new JTable();
		table.setBounds(12, 117, 386, 114);
		panel.add(table);

		JButton btnNewButton_2_1 = new JButton("나가기");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				JFrame newWin = new BirthdayGuiApp().initialize();
				newWin.setVisible(true);
			}
		});
		btnNewButton_2_1.setBounds(306, 74, 95, 33);
		panel.add(btnNewButton_2_1);

		return frame;
	}

	public void tableAll() {
		try {
			psmt = conn.prepareStatement("select * from birthday");
			rs = psmt.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void tableName() {
		String bname;
		bname = txtname.getText();
		try {
			psmt = conn.prepareStatement("select * from birthday where name = ?");
			psmt.setString(1, bname);
			rs = psmt.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void tableBirth() {
		String bbirth;
		bbirth = txtbirth.getText();
		try {
			psmt = conn.prepareStatement("select * from birthday where birth = ?");
			psmt.setString(1, bbirth);
			rs = psmt.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
