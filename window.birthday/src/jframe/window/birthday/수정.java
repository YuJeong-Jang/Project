package jframe.window.birthday;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class 수정 {

	private JFrame frame;
	private JTextField txtname;
	private JTextField txtsex;
	private JTextField txtbirth;
	private JTextField txttel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					수정 window = new 수정();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	static PreparedStatement psmt;
	static ResultSet rs;
	static Connection conn;
	private JTextField txtupdate;
	
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
	 * Create the application.
	 */
	public 수정() {
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
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\uC218\uC815", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 10, 410, 241);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("수정");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect();
				String bname, bsex, bbirth, btel, bupdate;
				
				bname = txtname.getText();
				bsex = txtsex.getText();
				bbirth = txtbirth.getText();
				btel = txttel.getText();
				bupdate = txtupdate.getText();
			
				try {
					psmt = conn.prepareStatement("update birthday set name = ?, sex = ?, birth = ?, tel = ? where name = ?");
					psmt.setString(1, bname);
					psmt.setString(2, bsex);
					psmt.setString(3, bbirth);
					psmt.setString(4, btel);
					psmt.setString(5, bupdate);
					int r = psmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "수정되었습니다.");
					System.out.println(r + "건 변경되었습니다.");
				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					close();
				}
				
			}
		});
		btnNewButton.setBounds(71, 200, 109, 28);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("나가기");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.dispose();
	            JFrame newWin = new BirthdayGuiApp().initialize();
	            newWin.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(211, 200, 109, 28);
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\uBCC0\uACBD\uB300\uC0C1", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(12, 13, 386, 50);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("이름");
		lblNewLabel_4.setBounds(0, 20, 109, 20);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		panel_1.add(lblNewLabel_4);
		
		txtupdate = new JTextField();
		txtupdate.setColumns(10);
		txtupdate.setBounds(121, 20, 215, 20);
		panel_1.add(txtupdate);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "\uBCC0\uACBD\uB0B4\uC6A9", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 72, 388, 118);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("전화번호");
		lblNewLabel_3.setBounds(0, 88, 109, 20);
		panel_2.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		JLabel lblNewLabel_2 = new JLabel("생년월일");
		lblNewLabel_2.setBounds(0, 65, 109, 20);
		panel_2.add(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		JLabel lblNewLabel_1 = new JLabel("성별");
		lblNewLabel_1.setBounds(0, 40, 109, 20);
		panel_2.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		JLabel lblNewLabel = new JLabel("이름");
		lblNewLabel.setBounds(0, 15, 109, 20);
		panel_2.add(lblNewLabel);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtsex = new JTextField();
		txtsex.setBounds(121, 40, 215, 20);
		panel_2.add(txtsex);
		txtsex.setColumns(10);
		
		txtname = new JTextField();
		txtname.setBounds(121, 15, 215, 20);
		panel_2.add(txtname);
		txtname.setColumns(10);
		
		txtbirth = new JTextField();
		txtbirth.setBounds(121, 65, 215, 20);
		panel_2.add(txtbirth);
		txtbirth.setColumns(10);
		
		txttel = new JTextField();
		txttel.setBounds(121, 88, 215, 20);
		panel_2.add(txttel);
		txttel.setColumns(10);
		return frame;
	}

}
