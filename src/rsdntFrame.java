import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Action;
import javax.swing.JEditorPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class rsdntFrame extends JFrame {
	User crntUser;
	private final Action action_1 = new SwingAction_1();
	JEditorPane editorPane;
	private final Action action = new SwingAction_2();
	private final Action action_2 = new SwingAction_3();
	String[] placeLst;
	JComboBox comboBox;

	public rsdntFrame(User user) {
		getContentPane().setBackground(new Color(224, 255, 255));
		crntUser = new User(user.getid(),user.getname(),user.getnum(),user.getcity());
		
		String city = crntUser.getcity();
		switch (city) {
		case "tirupati":
			placeLst = new String[]{"tirupati","Tirumala","Chandragiri","Kanipakam Vinayaka Temple","Kapila Theertham"};
			break;
		case "Mumbai":
			placeLst = new String[]{"Mumbai","Gateway of India","Elephanta Caves","Marine Drive","Chhatrapati Shivaji Terminus"};
			break;
		case "Chennai":
			placeLst = new String[]{"Chennai","Kapaleeshwarar Temple","Marina Beach","Fort St. George","Arignar Anna Zoological Park"};
			break;
		case "new delhi":
			placeLst = new String[]{"new delhi","Qutub Minar","Indian gate","The Lotus Temple","National museum "};
			break;
		}
		
		setBounds(100, 100, 816, 565);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 255));
		panel.setBounds(10, 10, 782, 94);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel CtyName = new JLabel();
		CtyName.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 32));
		CtyName.setForeground(new Color(0, 0, 153));
		CtyName.setText(crntUser.getcity());
		CtyName.setBounds(283, 10, 239, 74);
		panel.add(CtyName);
		
		JLabel usrName = new JLabel();
		usrName.setHorizontalAlignment(SwingConstants.TRAILING);
		usrName.setForeground(new Color(0, 102, 51));
		usrName.setFont(new Font("Tahoma", Font.BOLD, 15));
		usrName.setText(crntUser.getname());
		usrName.setBounds(552, 37, 220, 25);
		panel.add(usrName);
		
		JButton bckButton = new JButton("New button");
		bckButton.setAction(action_2);
		bckButton.setBackground(new Color(135, 206, 235));
		bckButton.setBounds(24, 23, 48, 47);
		panel.add(bckButton);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 255, 255));
		panel_1.setBounds(10, 114, 782, 404);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		editorPane = new JEditorPane();
		editorPane.setBounds(10, 24, 762, 329);
		panel_1.add(editorPane);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBackground(new Color(135, 206, 235));
		btnNewButton_1.setAction(action_1);
		btnNewButton_1.setBounds(10, 363, 85, 31);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBackground(new Color(135, 206, 235));
		btnNewButton_2.setAction(action);
		btnNewButton_2.setBounds(622, 363, 150, 31);
		panel_1.add(btnNewButton_2);
		
		comboBox = new JComboBox(placeLst);
		comboBox.setBounds(105, 368, 190, 21);
		panel_1.add(comboBox);
		
		
		setVisible(true);

	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Update");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb","root", "sqlpsd123*");
				Statement s=con.createStatement();
				PreparedStatement ps=con.prepareStatement("update place_dtl set dtls = ? where place = ?;");
				ps.setString(1, editorPane.getText());
				ps.setString(2, (String) comboBox.getSelectedItem());
				
				ps.execute();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "Add Picture");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			String city = crntUser.getcity();
			switch (city) {
			case "tirupati":
				new ImageUpdate("Tirumala","Chandragiri","Kanipakam Vinayaka Temple","Kapila Theertham");
				break;
			case "Mumbai":
				new ImageUpdate("Gateway of India","Elephanta Caves","Marine Drive","Chhatrapati Shivaji Terminus");
				break;
			case "Chennai":
				new ImageUpdate("Kapaleeshwarar Temple","Marina Beach","Fort St. George","Arignar Anna Zoological Park");
				break;
			case "new delhi":
				new ImageUpdate("Qutub Minar","Indian gate","The Lotus Temple","National museum ");
				break;
			}
		}
	}
	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(SMALL_ICON, new ImageIcon(TptFrame.class.getResource("/Icons/back.png")));
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			dispose();
			new Login();
		}
	}
}
