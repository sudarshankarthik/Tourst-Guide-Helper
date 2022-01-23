import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
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

public class rsdntSignin extends JFrame {

	private JPanel contentPane;
	private JTextField usrField;
	private JPasswordField psdField;
	private final Action action = new SwingAction();

	public rsdntSignin() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 501);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(214, 245, 255));
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Sign in as a Resident");
		lblNewLabel.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 32));
		lblNewLabel.setForeground(new Color(0, 0, 153));;
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(224, 255, 255));
		contentPane.add(panel_1, BorderLayout.CENTER);
		SpringLayout sl_panel_1 = new SpringLayout();
		panel_1.setLayout(sl_panel_1);
		
		JLabel userLbl = new JLabel("");
		userLbl.setIcon(new ImageIcon(rsdntSignin.class.getResource("/Icons/user-32.png")));
		sl_panel_1.putConstraint(SpringLayout.NORTH, userLbl, 116, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, userLbl, 189, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, userLbl, -257, SpringLayout.SOUTH, panel_1);
		panel_1.add(userLbl);
		
		usrField = new JTextField();
		sl_panel_1.putConstraint(SpringLayout.EAST, userLbl, -23, SpringLayout.WEST, usrField);
		sl_panel_1.putConstraint(SpringLayout.NORTH, usrField, 0, SpringLayout.NORTH, userLbl);
		sl_panel_1.putConstraint(SpringLayout.WEST, usrField, 257, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, usrField, -163, SpringLayout.EAST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, usrField, -257, SpringLayout.SOUTH, panel_1);
		panel_1.add(usrField);
		usrField.setColumns(10);
		
		JLabel pssdLbl = new JLabel("");
		pssdLbl.setIcon(new ImageIcon(rsdntSignin.class.getResource("/Icons/key-32.png")));
		sl_panel_1.putConstraint(SpringLayout.NORTH, pssdLbl, 162, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, pssdLbl, 189, SpringLayout.WEST, panel_1);
		panel_1.add(pssdLbl);
		
		psdField = new JPasswordField();
		sl_panel_1.putConstraint(SpringLayout.NORTH, psdField, 12, SpringLayout.SOUTH, usrField);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, pssdLbl, 0, SpringLayout.SOUTH, psdField);
		sl_panel_1.putConstraint(SpringLayout.EAST, pssdLbl, -23, SpringLayout.WEST, psdField);
		sl_panel_1.putConstraint(SpringLayout.WEST, psdField, 257, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, psdField, -163, SpringLayout.EAST, panel_1);
		panel_1.add(psdField);
		
		JButton lgnButton = new JButton("New button");
		sl_panel_1.putConstraint(SpringLayout.SOUTH, psdField, -80, SpringLayout.NORTH, lgnButton);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lgnButton, -131, SpringLayout.SOUTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lgnButton, -330, SpringLayout.EAST, panel_1);
		lgnButton.setAction(action);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, lgnButton, -97, SpringLayout.SOUTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, lgnButton, -241, SpringLayout.EAST, panel_1);
		lgnButton.setBackground(new Color(0, 191, 255));
		panel_1.add(lgnButton);
		
		setVisible(true);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Sigin in");
			putValue(SHORT_DESCRIPTION, "Login into your Account");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb","root", "sqlpsd123*");
				Statement s=con.createStatement();
				PreparedStatement ps=con.prepareStatement("select * from resident_dtl where id = ?");
				ps.setString(1,usrField.getText());
				ResultSet rs= ps.executeQuery();
				rs.next();
				String passText = new String(psdField.getPassword());
				User user = new User(rs.getString("id"),rs.getString("name"),rs.getLong("phn"),rs.getString("cty"));
				
				if(passText.equals(rs.getString("psd"))) {
					dispose();
					new rsdntFrame(user);
					System.out.println("Success");
				}
				else {
					System.out.println(rs.getString("psd"));
					System.out.println(passText);
				}
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
