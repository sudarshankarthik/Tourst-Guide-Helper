import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
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

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	JCheckBox showPsdbox;
	private final Action loginAcn = new SwingAction();
	private final Action showPsd = new SwingAction_2();
	private final Action signup = new SwingAction_1();
	private final Action rsdntsigin = new SwingAction_3();
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 979, 516);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setForeground(new Color(153, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel imagePanel = new JPanel();
		imagePanel.setBounds(21, 159, 566, 289);
		contentPane.add(imagePanel);
		imagePanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBackground(new Color(224, 255, 255));
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/Images/Title.png")));
		lblNewLabel.setBounds(0, 0, 578, 293);
		imagePanel.add(lblNewLabel);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(null);
		contentPanel.setBackground(new Color(204, 255, 255));
		contentPanel.setBounds(606, 40, 349, 408);
		contentPane.add(contentPanel);
		
		JLabel headingLabel = new JLabel("Sign In");
		headingLabel.setBackground(new Color(0, 102, 0));
		headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headingLabel.setForeground(new Color(0, 0, 153));
		headingLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		headingLabel.setBounds(98, 51, 180, 41);
		contentPanel.add(headingLabel);
		
		JLabel userImageLabel = new JLabel("New label");
		userImageLabel.setIcon(new ImageIcon(Login.class.getResource("/Icons/user-32.png")));
		userImageLabel.setBounds(30, 148, 32, 32);
		contentPanel.add(userImageLabel);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(98, 145, 180, 40);
		contentPanel.add(textField);
		
		JLabel psdLabel = new JLabel("New label");
		psdLabel.setIcon(new ImageIcon(Login.class.getResource("/Icons/key-32.png")));
		psdLabel.setBounds(30, 230, 32, 32);
		contentPanel.add(psdLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(98, 227, 180, 40);
		contentPanel.add(passwordField);
		
		showPsdbox = new JCheckBox("");
		showPsdbox.setSelected(true);
		showPsdbox.setAction(showPsd);
		showPsdbox.setBounds(302, 230, 41, 32);
		showPsdbox.setIcon(new ImageIcon(Login.class.getResource("/Icons/eye-2-32.png")));
		contentPanel.add(showPsdbox);
		
		JButton siginBtn = new JButton("Sign In");
		siginBtn.setBackground(new Color(0, 191, 255));
		siginBtn.setAction(loginAcn);
		siginBtn.setBounds(98, 291, 85, 21);
		contentPanel.add(siginBtn);
		
		JButton sigupBtn = new JButton("Sigin Up");
		sigupBtn.setBackground(new Color(0, 191, 255));
		sigupBtn.setAction(signup);
		sigupBtn.setBounds(193, 291, 84, 21);
		contentPanel.add(sigupBtn);
		
		JButton sgnButton = new JButton("Sigin as Resident");
		sgnButton.setAction(rsdntsigin);
		sgnButton.setBackground(new Color(0, 191, 255));
		sgnButton.setBounds(98, 334, 180, 32);
		contentPanel.add(sgnButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(224, 255, 255));
		panel.setBounds(21, 40, 566, 109);
		contentPane.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("Tourist Guide Helper");
		lblNewLabel_1.setForeground(new Color(25, 25, 112));
		lblNewLabel_1.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 53));
		panel.add(lblNewLabel_1);
		
		setVisible(true);
	}
	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Login");
			putValue(SHORT_DESCRIPTION, "Login to the application");
		}
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
			try {
				
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb","root", "sqlpsd123*");
				
				Statement s=con.createStatement();
				PreparedStatement ps=con.prepareStatement("select * from user_dtl where id = ?");
				ps.setString(1,textField.getText());
				ResultSet rs= ps.executeQuery();
				rs.next();
				String passText = new String(passwordField.getPassword());
				
				User user = new User(rs.getString("id"),rs.getString("name"),rs.getLong("phn"),rs.getString("cty"));
				if(passText.equals(rs.getString("psd"))) {
					dispose();
					new Navg(user);
					System.out.println("Success");
				}
				else {
					System.out.println(rs.getString("psd"));
					System.out.println(passText);
				}
			}
			catch (SQLException e1) {
				// TODO Auto-generated catch block
			
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
			
			}
		}
	}

	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "show Password");
			putValue(SHORT_DESCRIPTION, "Show Password");
		}
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
			if(showPsdbox.isSelected() == true)
				passwordField.setEchoChar('*');
			else
				passwordField.setEchoChar((char)0);
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Sigin Up");
			putValue(SHORT_DESCRIPTION, "Create new User");
		}
		public void actionPerformed(ActionEvent e) {
			new Signup();
		}
	}
	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(NAME, "Sign in as Resident");
			putValue(SHORT_DESCRIPTION, "Sigin in as Resident");
		}
		public void actionPerformed(ActionEvent e) {
			dispose();
			new rsdntSignin();
		}
	}
}
