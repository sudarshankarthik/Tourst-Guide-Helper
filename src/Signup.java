import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.Action;

public class Signup extends JFrame {

	private JPanel contentPane;
	private JTextField idField;
	private JTextField nameField;
	private JTextField phnField;
	private JTextField ctyField;
	private JPasswordField psdField;
	private JPasswordField cfmField;
	private final Action signUp = new SwingAction();

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Signup() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 566, 769);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel headPnl = new JPanel();
		headPnl.setBackground(new Color(204, 255, 255));
		headPnl.setBounds(10, 33, 532, 87);
		contentPane.add(headPnl);
		headPnl.setLayout(null);
		
		JLabel headLbl = new JLabel("Sign Up ");
		headLbl.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 32));
		headLbl.setForeground(new Color(0, 0, 153));
		headLbl.setHorizontalAlignment(SwingConstants.CENTER);
		headLbl.setBounds(160, 24, 165, 53);
		headPnl.add(headLbl);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 255, 255));
		panel_1.setBounds(10, 146, 532, 504);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel idLbl = new JLabel("User id");
		idLbl.setFont(new Font("Arial", Font.BOLD, 22));
		idLbl.setBounds(32, 65, 183, 43);
		panel_1.add(idLbl);
		
		JLabel nmeLbl = new JLabel("Name");
		nmeLbl.setFont(new Font("Arial", Font.BOLD, 22));
		nmeLbl.setBounds(32, 148, 183, 43);
		panel_1.add(nmeLbl);
		
		JLabel phnLbl = new JLabel("Phone Number");
		phnLbl.setFont(new Font("Arial", Font.BOLD, 22));
		phnLbl.setBounds(32, 227, 183, 43);
		panel_1.add(phnLbl);
		
		JLabel ctyLbl = new JLabel("City");
		ctyLbl.setFont(new Font("Arial", Font.BOLD, 22));
		ctyLbl.setBounds(32, 301, 183, 43);
		panel_1.add(ctyLbl);
		
		idField = new JTextField();
		idField.setBounds(299, 65, 183, 43);
		panel_1.add(idField);
		idField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(299, 151, 183, 43);
		panel_1.add(nameField);
		
		phnField = new JTextField();
		phnField.setColumns(10);
		phnField.setBounds(299, 230, 183, 43);
		panel_1.add(phnField);
		
		ctyField = new JTextField();
		ctyField.setColumns(10);
		ctyField.setBounds(299, 304, 183, 43);
		panel_1.add(ctyField);
		
		JLabel psdLbl = new JLabel("Password ");
		psdLbl.setFont(new Font("Arial", Font.BOLD, 22));
		psdLbl.setBounds(32, 374, 183, 43);
		panel_1.add(psdLbl);
		
		JLabel psd2Lbl = new JLabel("Conform Password");
		psd2Lbl.setFont(new Font("Arial", Font.BOLD, 20));
		psd2Lbl.setBounds(32, 443, 183, 43);
		panel_1.add(psd2Lbl);
		
		psdField = new JPasswordField();
		psdField.setBounds(299, 374, 183, 43);
		panel_1.add(psdField);
		
		cfmField = new JPasswordField();
		cfmField.setBounds(299, 443, 183, 43);
		panel_1.add(cfmField);
		
		JButton submitBtn = new JButton("Create New User");
		submitBtn.setAction(signUp);
		submitBtn.setFont(new Font("Arial", Font.BOLD, 20));
		submitBtn.setBackground(new Color(224, 255, 255));
		submitBtn.setBounds(10, 674, 532, 48);
		contentPane.add(submitBtn);
		
		setVisible(true);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Create new User");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb","root", "sqlpsd123*");
				PreparedStatement ps=con.prepareStatement("insert into user_dtl values(?,?,?,?,?)");
				String passText = new String(psdField.getPassword());
				String cnfmText = new String(cfmField.getPassword());
				if(passText.equals(cnfmText)) {
				ps.setString(1, idField.getText());
				ps.setString(2, nameField.getText());
				ps.setString(3, passText);
				ps.setLong(4, Long.parseLong(phnField.getText()));
				ps.setString(5, ctyField.getText());
				ps.execute();
				dispose();
				}
			} catch (ClassNotFoundException e1) {
				System.out.println("crazy");
			} catch (SQLException e1) {
				System.out.println(e1);
			}
			
			
		}
	}
}
