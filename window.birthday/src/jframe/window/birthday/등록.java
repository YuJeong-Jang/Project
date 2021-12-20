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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class 등록{

	private JFrame frame;
	private JTextField txtname;
	private JTextField txtsex;
	private JTextField txtbirth;
	private JTextField txttel;

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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					등록 window = new 등록();
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
	public 등록() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @return 
	 */
	public JFrame initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtname = new JTextField();
		txtname.setBounds(133, 45, 215, 27);
		frame.getContentPane().add(txtname);
		txtname.setColumns(10);
		
		txtsex = new JTextField();
		txtsex.setColumns(10);
		txtsex.setBounds(133, 85, 215, 27);
		frame.getContentPane().add(txtsex);
		
		txtbirth = new JTextField();
		txtbirth.setColumns(10);
		txtbirth.setBounds(133, 125, 215, 27);
		frame.getContentPane().add(txtbirth);
		
		txttel = new JTextField();
		txttel.setColumns(10);
		txttel.setBounds(133, 165, 215, 27);
		frame.getContentPane().add(txttel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\uB4F1\uB85D", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 10, 410, 241);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("등록");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect();
				String bname, bsex, bbirth, btel;
				
				bname = txtname.getText();
				bsex = txtsex.getText();
				bbirth = txtbirth.getText();
				btel = txttel.getText();
			
				try {
					psmt = conn.prepareStatement("insert into birthday(name,sex,birth,tel) values(?,?,?,?)");
					psmt.setString(1, bname);
					psmt.setString(2, bsex);
					psmt.setString(3, bbirth);
					psmt.setString(4, btel);
					int r = psmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "등록되었습니다.");
					System.out.println(r + "건이 입력되었습니다.");
				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					close();
				}
			}
		});
		btnNewButton.setBounds(71, 196, 109, 28);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("나가기");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
	            JFrame newWin = new BirthdayGuiApp().initialize();
	            newWin.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(211, 196, 109, 28);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("이름");
		lblNewLabel.setBounds(0, 30, 109, 33);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		JLabel lblNewLabel_1 = new JLabel("성별");
		lblNewLabel_1.setBounds(0, 70, 109, 33);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		JLabel lblNewLabel_2 = new JLabel("생년월일");
		lblNewLabel_2.setBounds(0, 110, 109, 33);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		JLabel lblNewLabel_3 = new JLabel("전화번호");
		lblNewLabel_3.setBounds(0, 150, 109, 33);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		return frame;
	}

}
