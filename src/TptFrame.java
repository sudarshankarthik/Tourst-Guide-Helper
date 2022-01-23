import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;
import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import java.awt.Component;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Action;
import javax.swing.ImageIcon;

import java.awt.Font;
import javax.swing.SwingConstants;

public class TptFrame extends JFrame {

	private JPanel contentPane;
	private final Action action = new SwingAction();
	String dtls;
	User crntUser;
	private final Action tirumalaAcn = new SwingAction_2();
	private final Action ktAction = new SwingAction_3();
	private final Action kanpAtion = new SwingAction_4();
	private final Action action_2 = new SwingAction_5();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public TptFrame(User user) {
		crntUser = new User(user.getid(),user.getname(),user.getnum(),user.getcity());
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb","root", "sqlpsd123*");
			Statement s=con.createStatement();
			PreparedStatement ps=con.prepareStatement("select * from place_dtl where place = \"tirupati\"");
			ResultSet rs = ps.executeQuery();
			rs.next();
			dtls = rs.getString(2);
			
		} catch (Exception e) {
			
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 941, 473);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 255, 255));
		panel.setBounds(23, 29, 894, 111);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tirupati");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 32));
		lblNewLabel.setForeground(new Color(0, 0, 153));
		lblNewLabel.setBounds(88, 35, 587, 48);
		panel.add(lblNewLabel);
		
		JButton bckButton = new JButton();
		bckButton.setBackground(new Color(153, 255, 255));
		bckButton.setFocusable(false);
		bckButton.setIcon(new ImageIcon(TptFrame.class.getResource("/Icons/back.png")));
		bckButton.setAction(action);
		bckButton.setBounds(32, 35, 46, 35);
		panel.add(bckButton);
		
		JLabel userLbl = new JLabel();
		userLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		userLbl.setForeground(new Color(0, 102, 51));
		userLbl.setBounds(644, 42, 201, 35);
		userLbl.setText(crntUser.getname());
		panel.add(userLbl);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 180, 681, 233);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Arial", Font.PLAIN, 15));
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setText(dtls);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(153, 255, 255));
		panel_1.setBounds(714, 180, 203, 233);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton placeBtn_1 = new JButton("Tirumala");
		placeBtn_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		placeBtn_1.setAction(tirumalaAcn);
		placeBtn_1.setBackground(new Color(0, 191, 255));
		placeBtn_1.setBounds(10, 21, 183, 34);
		panel_1.add(placeBtn_1);
		
		JButton placeBtn_2 = new JButton("Kapila Theertham");
		placeBtn_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		placeBtn_2.setAction(ktAction);
		placeBtn_2.setBackground(new Color(0, 191, 255));
		placeBtn_2.setBounds(10, 65, 183, 32);
		panel_1.add(placeBtn_2);
		
		JButton knpAction = new JButton("Kanipakam Vinayaka Temple");
		knpAction.setFont(new Font("Tahoma", Font.PLAIN, 16));
		knpAction.setAction(kanpAtion);
		knpAction.setBackground(new Color(0, 191, 255));
		knpAction.setBounds(10, 107, 183, 32);
		panel_1.add(knpAction);
		
		JButton placeBtn_4 = new JButton("Chandragiri");
		placeBtn_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		placeBtn_4.setAction(action_2);
		placeBtn_4.setBackground(new Color(0, 191, 255));
		placeBtn_4.setBounds(10, 149, 183, 34);
		panel_1.add(placeBtn_4);
		
		setVisible(true);
	}
	@SuppressWarnings("serial")
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(SMALL_ICON, new ImageIcon(TptFrame.class.getResource("/Icons/back.png")));
			putValue(SHORT_DESCRIPTION, "Back");
		}
		public void actionPerformed(ActionEvent e) {
			dispose();
			new Navg(crntUser);
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "SwingAction_1");
			putValue(SHORT_DESCRIPTION, "Back");
		}
		public void actionPerformed(ActionEvent e) {
			dispose();
			new Navg(crntUser);
		}
	}
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "Kapila Theertham");
			putValue(SHORT_DESCRIPTION, "View Place Infomation");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				new plsFrame(crntUser,"Kapila Theertham");
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(NAME, "Kanipakam Vinayaka Temple");
			putValue(SHORT_DESCRIPTION, "View Place Infomation");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				new plsFrame(crntUser,"Kanipakam Vinayaka Temple");
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	private class SwingAction_4 extends AbstractAction {
		public SwingAction_4() {
			putValue(NAME, "Chandragiri");
			putValue(SHORT_DESCRIPTION, "View Place Infomation");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				new plsFrame(crntUser,"Chandragiri");
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	private class SwingAction_5 extends AbstractAction {
		public SwingAction_5() {
			putValue(NAME, "Tirumala");
			putValue(SHORT_DESCRIPTION, "View Place Infomation");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				new plsFrame(crntUser,"Tirumala");
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
