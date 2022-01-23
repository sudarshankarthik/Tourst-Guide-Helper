import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.SwingConstants;

public class plsFrame extends JFrame {

	private JPanel contentPane;
	User crntuser;
	ResultSet rs1;
	ResultSet rs2;
	ResultSet rs3;
	ImageIcon icon;
	String placeName;
	private final Action action = new SwingAction();
	JTextArea cmt_Area;
	JTextArea viewCmt_Area;
	
	public plsFrame(User user,String plcName) throws ClassNotFoundException, SQLException {
		
		crntuser = new User(user.getid(),user.getname(),user.getnum(),user.getcity());
		placeName = plcName;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb","root", "sqlpsd123*");
			
			PreparedStatement ps1 = con.prepareStatement("select * from place_dtl where place = ?");
			PreparedStatement ps2 = con.prepareStatement("select * from reviews where place = ?");
			PreparedStatement ps3 = con.prepareStatement("select * from images where place = ?");
			
			ps1.setString(1, plcName);
			ps2.setString(1, plcName);
			ps3.setString(1, plcName);
			
			rs1= ps1.executeQuery();
			rs2 = ps2.executeQuery();
			rs3 = ps3.executeQuery();
			
			rs1.next();
			rs2.next();
			rs3.next();
			
			Blob b = rs3.getBlob(3);
			InputStream is = b.getBinaryStream(1,b.length());
			BufferedImage bimg = ImageIO.read(is);
			Image img = bimg;
			icon = new ImageIcon(img);
		
		} catch (ClassNotFoundException e1) {
			System.out.println("crazy");
		} catch (SQLException e1) {
			System.out.println(e1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1057, 727);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 255, 255));
		panel.setBounds(5, 5, 1033, 67);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(plcName);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 0, 153));
		lblNewLabel.setBounds(10, 20, 1013, 37);
		lblNewLabel.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 32));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(153, 255, 255));
		panel_1.setBounds(5, 82, 1033, 356);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(240, 255, 255));
		panel_3.setBounds(625, 11, 398, 335);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel imgLbl = new JLabel("");
		imgLbl.setHorizontalAlignment(SwingConstants.CENTER);
		imgLbl.setBounds(10, 10, 378, 315);
		imgLbl.setIcon(icon);
		panel_3.add(imgLbl);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 22, 605, 324);
		panel_1.add(scrollPane_2);
		
		JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setRows(20);
		textArea.setEditable(false);
		scrollPane_2.setViewportView(textArea);
		textArea.setBackground(Color.WHITE);
		textArea.setFont(new Font("Arial", Font.PLAIN, 14));
		textArea.setText(rs1.getString(2));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(153, 255, 255));
		panel_2.setBounds(5, 448, 1033, 271);
		contentPane.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);
		
		cmt_Area = new JTextArea(3,83);
		cmt_Area.setFont(new Font("Arial", Font.PLAIN, 14));
		scrollPane.setViewportView(cmt_Area);
		
		JButton Review = new JButton("New button");
		Review.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Review.setAction(action);
		Review.setBackground(new Color(0, 191, 255));
		panel_2.add(Review);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1);
		
		viewCmt_Area = new JTextArea();
		viewCmt_Area.setFont(new Font("Arial", Font.PLAIN, 14));
		viewCmt_Area.setEditable(false);
		viewCmt_Area.setLineWrap(true);
		viewCmt_Area.setColumns(90);
		scrollPane_1.setViewportView(viewCmt_Area);
		viewCmt_Area.setRows(8);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb","root", "sqlpsd123*");				
			
			PreparedStatement ps = con.prepareStatement("select * from reviews where place = ?");
			ps.setString(1, placeName);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				viewCmt_Area.append(rs.getString("user_id"));
				viewCmt_Area.append(":\n");
				viewCmt_Area.append(rs.getString("content"));
				viewCmt_Area.append("\n\n");
			}
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		
		
		setVisible(true);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Review");
			putValue(SHORT_DESCRIPTION, "Add your Review");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb","root", "sqlpsd123*");				
				
				PreparedStatement ps = con.prepareStatement("insert into reviews values (?,?,?)");
				ps.setString(1, crntuser.getid());
				ps.setString(2, cmt_Area.getText());
				ps.setString(3, placeName);
				ps.execute();
				
				
				
				
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			viewCmt_Area.setText("");
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb","root", "sqlpsd123*");				
				
				PreparedStatement ps = con.prepareStatement("select * from reviews where place = ?");
				ps.setString(1, placeName);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					viewCmt_Area.append(rs.getString("user_id"));
					viewCmt_Area.append(":\n");
					viewCmt_Area.append(rs.getString("content"));
					viewCmt_Area.append("\n\n");
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
