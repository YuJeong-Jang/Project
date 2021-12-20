package jframe.window.birthday;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BirthdayGuiApp {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BirthdayGuiApp window = new BirthdayGuiApp();
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
	public BirthdayGuiApp() {
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
		
		JLabel lblNewLabel = new JLabel("생일 리마인더");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(115, 10, 203, 53);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("등록");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
	            JFrame newWin = new 등록().initialize();
	            newWin.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnNewButton.setBounds(12, 90, 125, 46);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("수정");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
            JFrame newWin = new 수정().initialize();
            newWin.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnNewButton_1.setBounds(145, 90, 125, 46);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("삭제");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
	            JFrame newWin = new 삭제().initialize();
	            newWin.setVisible(true);
			}
		});
		btnNewButton_2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnNewButton_2.setBounds(282, 90, 125, 46);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("조회");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
	            JFrame newWin = new 조회().initialize();
	            newWin.setVisible(true);
			}
		});
		btnNewButton_3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnNewButton_3.setBounds(12, 160, 125, 46);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("축하");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
	            JFrame newWin = new 문자발송().initialize();
	            newWin.setVisible(true);
			}
		});
		btnNewButton_4.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnNewButton_4.setBounds(145, 159, 125, 47);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("종료");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_5.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnNewButton_5.setBounds(282, 160, 125, 46);
		frame.getContentPane().add(btnNewButton_5);
		return frame;
	}

}
