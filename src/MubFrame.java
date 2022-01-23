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

public class MubFrame extends JFrame {

	private JPanel contentPane;
	private final Action action = new SwingAction();
	String dtls;
	User crntUser;
	private final Action action_1 = new SwingAction_2();
	private final Action action_2 = new SwingAction_3();
	private final Action action_3 = new SwingAction_4();
	private final Action action_4 = new SwingAction_5();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public MubFrame(User user) {
		crntUser = new User(user.getid(),user.getname(),user.getnum(),user.getcity());
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb","root", "sqlpsd123*");
			Statement s=con.createStatement();
			PreparedStatement ps=con.prepareStatement("select * from place_dtl where place = \"Mumbai\"");
			ResultSet rs = ps.executeQuery();
			rs.next();
			dtls = rs.getString(2);
			
		} catch (Exception e) {
			
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 946, 472);
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
		
		JLabel lblNewLabel = new JLabel("Mumbai");
		lblNewLabel.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 32));
		lblNewLabel.setForeground(new Color(0, 0, 153));
		lblNewLabel.setBounds(343, 35, 149, 48);
		panel.add(lblNewLabel);
		
		JButton bckButton = new JButton();
		bckButton.setBackground(new Color(153, 255, 255));
		bckButton.setAction(action);
		bckButton.setBounds(32, 35, 46, 35);
		panel.add(bckButton);
		
		JLabel userLbl = new JLabel();
		userLbl.setBounds(660, 55, 182, 19);
		userLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		userLbl.setForeground(new Color(0, 102, 51));
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
		
		JButton placeBtn_1 = new JButton("Gateway of India");
		placeBtn_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		placeBtn_1.setAction(action_1);
		placeBtn_1.setBackground(new Color(0, 191, 255));
		placeBtn_1.setBounds(10, 21, 183, 34);
		panel_1.add(placeBtn_1);
		
		JButton placeBtn_2 = new JButton("Elephanta Caves");
		placeBtn_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		placeBtn_2.setAction(action_2);
		placeBtn_2.setBackground(new Color(0, 191, 255));
		placeBtn_2.setBounds(10, 65, 183, 32);
		panel_1.add(placeBtn_2);
		
		JButton placeBtn_3 = new JButton("Marine Drive");
		placeBtn_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		placeBtn_3.setAction(action_3);
		placeBtn_3.setBackground(new Color(0, 191, 255));
		placeBtn_3.setBounds(10, 107, 183, 32);
		panel_1.add(placeBtn_3);
		
		JButton placeBtn_4 = new JButton("Chhatrapati Shivaji Terminus");
		placeBtn_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		placeBtn_4.setAction(action_4);
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
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "Gateway of India");
			putValue(SHORT_DESCRIPTION, "View Place Infomation");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				new plsFrame(crntUser,"Gateway of India");
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(NAME, "Elephanta Caves");
			putValue(SHORT_DESCRIPTION, "View Place Infomation");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				new plsFrame(crntUser,"Elephanta Caves");
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	private class SwingAction_4 extends AbstractAction {
		public SwingAction_4() {
			putValue(NAME, "Marine Drive");
			putValue(SHORT_DESCRIPTION, "View Place Infomation");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				new plsFrame(crntUser,"Marine Drive");
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	private class SwingAction_5 extends AbstractAction {
		public SwingAction_5() {
			putValue(NAME, "Chhatrapati Shivaji Terminus");
			putValue(SHORT_DESCRIPTION, "View Place Infomation");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				new plsFrame(crntUser,"Chhatrapati Shivaji Terminus");
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
