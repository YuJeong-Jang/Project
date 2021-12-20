package jframe.window.birthday;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class 삭제 {

	private JFrame frame;
	private JTextField txtname;

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
					삭제 window = new 삭제();
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
	public 삭제() {
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
		panel.setBorder(new TitledBorder(null, "\uC0AD\uC81C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 10, 410, 241);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("이름");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 64, 109, 33);
		panel.add(lblNewLabel);

		txtname = new JTextField();
		txtname.setBounds(133, 70, 215, 27);
		panel.add(txtname);
		txtname.setColumns(10);

		JButton btnNewButton = new JButton("삭제");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect();
				String bname;
				bname = txtname.getText();

				try {
					psmt = conn.prepareStatement("delete from birthday where name = ?");
					psmt.setString(1, bname);
					int r = psmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "삭제되었습니다.");
					System.out.println(r + "건 삭제완료.");
				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					close();
				}

			}
		});
		btnNewButton.setBounds(67, 153, 109, 28);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("나가기");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
	            JFrame newWin = new BirthdayGuiApp().initialize();
	            newWin.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(239, 156, 109, 28);
		panel.add(btnNewButton_1);
		return frame;
	}
}
