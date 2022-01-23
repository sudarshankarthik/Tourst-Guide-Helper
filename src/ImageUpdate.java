import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.Action;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;

public class ImageUpdate extends JFrame {

	private JPanel contentPane;
	private JTextField NameField;
	private final Action action = new SwingAction();
	JComboBox placeField;

	public ImageUpdate(String place1,String place2,String place3,String place4) {
		String[] placeLst = {place1,place2,place3,place4};
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		NameField = new JTextField();
		NameField.setBounds(211, 70, 143, 44);
		contentPane.add(NameField);
		NameField.setColumns(10);
		
		JLabel nameLbl = new JLabel("Image Name");
		nameLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nameLbl.setBounds(26, 70, 143, 44);
		contentPane.add(nameLbl);
		
		JLabel placeLbl = new JLabel("Image Place");
		placeLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		placeLbl.setBounds(26, 137, 143, 44);
		contentPane.add(placeLbl);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBackground(new Color(0, 191, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setAction(action);
		btnNewButton.setBounds(117, 212, 152, 41);
		contentPane.add(btnNewButton);
		
		placeField = new JComboBox(placeLst);
		placeField.setBounds(211, 137, 143, 35);
		contentPane.add(placeField);
		
		setVisible(true);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Upload Image");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				
				JFileChooser fileChooser = new JFileChooser();
				int r = fileChooser.showOpenDialog(null);
				
					File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
					InputStream in = new FileInputStream(file);

				
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb","root", "sqlpsd123*");
				
				PreparedStatement ps = con.prepareStatement("delete from images where place = ?");
				ps.setString(1, (String) placeField.getSelectedItem());
				ps.execute();
				
				ps = con.prepareStatement("insert into images values(?,?,?)");
				ps.setString(1, NameField.getText());
				ps.setString(2, (String) placeField.getSelectedItem());
				ps.setBlob(3, in);
				
				ps.execute();
				dispose();
				
				
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
