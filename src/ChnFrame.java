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
import javax.swing.SwingConstants;

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

public class ChnFrame extends JFrame {

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
	public ChnFrame(User user) {
		crntUser = new User(user.getid(),user.getname(),user.getnum(),user.getcity());
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb","root", "sqlpsd123*");
			Statement s=con.createStatement();
			PreparedStatement ps=con.prepareStatement("select * from place_dtl where place = \"Chennai\"");
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
		
		JLabel lblNewLabel = new JLabel("Chennai");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 32));
		lblNewLabel.setForeground(new Color(0, 0, 153));
		lblNewLabel.setBounds(343, 35, 149, 48);
		panel.add(lblNewLabel);
		
		JLabel userLbl = new JLabel();
		userLbl.setBounds(662, 55, 180, 19);
		userLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		userLbl.setForeground(new Color(0, 102, 51));
		userLbl.setText(crntUser.getname());
		panel.add(userLbl);
		
		JButton btnNewButton = new JButton();
		btnNewButton.setBackground(new Color(153, 255, 255));
		btnNewButton.setIcon(null);
		btnNewButton.setAction(action);
		btnNewButton.setBounds(49, 35, 46, 43);
		panel.add(btnNewButton);
		
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
		
		JButton placeBtn_1 = new JButton("Kapaleeshwarar Temple");
		placeBtn_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		placeBtn_1.setAction(action_1);
		placeBtn_1.setBackground(new Color(0, 191, 255));
		placeBtn_1.setBounds(10, 21, 183, 34);
		panel_1.add(placeBtn_1);
		
		JButton placeBtn_2 = new JButton("Marina Beach");
		placeBtn_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		placeBtn_2.setAction(action_2);
		placeBtn_2.setBackground(new Color(0, 191, 255));
		placeBtn_2.setBounds(10, 65, 183, 32);
		panel_1.add(placeBtn_2);
		
		JButton placeBtn_3 = new JButton("Fort St. George");
		placeBtn_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		placeBtn_3.setAction(action_3);
		placeBtn_3.setBackground(new Color(0, 191, 255));
		placeBtn_3.setBounds(10, 107, 183, 32);
		panel_1.add(placeBtn_3);
		
		JButton placeBtn_4 = new JButton("Arignar Anna Zoological Park");
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
			putValue(NAME, "");
			putValue(SHORT_DESCRIPTION, "Back");
		}
		public void actionPerformed(ActionEvent e) {
			dispose();
			new Navg(crntUser);
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(SHORT_DESCRIPTION, "Back");
		}
		public void actionPerformed(ActionEvent e) {
			dispose();
			new Navg(crntUser);
		}
	}
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "Kapaleeshwarar Temple");
			putValue(SHORT_DESCRIPTION, "View Place Infomation");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				new plsFrame(crntUser,"Kapaleeshwarar Temple");
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(NAME, "Marina Beach");
			putValue(SHORT_DESCRIPTION, "View Place Infomation");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				new plsFrame(crntUser,"Marina Beach");
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	private class SwingAction_4 extends AbstractAction {
		public SwingAction_4() {
			putValue(NAME, "Fort St. George");
			putValue(SHORT_DESCRIPTION, "View Place Infomation");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				new plsFrame(crntUser,"Fort St. George");
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	private class SwingAction_5 extends AbstractAction {
		public SwingAction_5() {
			putValue(NAME, "Arignar Anna Zoological Park");
			putValue(SHORT_DESCRIPTION, "View Place Infomation");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				new plsFrame(crntUser,"Arignar Anna Zoological Park");
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
