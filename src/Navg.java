import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Navg extends JFrame {

	private JPanel contentPane;
	JButton tptButton;
	JButton chnButton;
	JButton dlhButton;
	JButton otyButton;
	User crntUser;
	private final Action tptAction = new SwingAction();
	private final Action chnAction = new SwingAction_1();
	private final Action dlhAction = new SwingAction_2();
	private final Action mubAction = new SwingAction_3();
	private final Action bckAction = new SwingAction_4();
	private JButton tptButton_1;


	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Navg(User user) {
		crntUser = new User(user.getid(),user.getname(),user.getnum(),user.getcity());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 939, 530);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 255));
		contentPane.setForeground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel Header = new JPanel();
		Header.setBackground(new Color(204, 255, 255));
		Header.setBounds(24, 10, 888, 85);
		contentPane.add(Header);
		Header.setLayout(null);
		
		JLabel headingLbl = new JLabel("Tourist Guide Helper");
		headingLbl.setHorizontalAlignment(SwingConstants.CENTER);
		headingLbl.setForeground(new Color(0, 0, 153));
		headingLbl.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 32));
		headingLbl.setBounds(119, 19, 580, 44);
		Header.add(headingLbl);
		
		JLabel nameLbl = new JLabel("New label");
		nameLbl.setForeground(new Color(0, 102, 51));
		nameLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		nameLbl.setBounds(657, 28, 198, 28);
		nameLbl.setText(user.getname());
		Header.add(nameLbl);
		
		JButton bckBtn = new JButton("Logout");
		bckBtn.setAction(bckAction);
		bckBtn.setBackground(new Color(0, 191, 255));
		bckBtn.setBounds(24, 32, 85, 21);
		Header.add(bckBtn);
		
		JPanel Body = new JPanel();
		Body.setBackground(new Color(204, 255, 255));
		Body.setBounds(24, 125, 888, 340);
		contentPane.add(Body);
		Body.setLayout(null);
		
		JLabel tptImage = new JLabel("New label");
		tptImage.setIcon(new ImageIcon(Navg.class.getResource("/Images/17tirumala-balaji.jpg")));
		tptImage.setBounds(47, 86, 178, 130);
		Body.add(tptImage);
		
		JLabel chnImage = new JLabel("New label");
		chnImage.setIcon(new ImageIcon(Navg.class.getResource("/Images/25chennai1.jpg")));
		chnImage.setBounds(247, 42, 185, 219);
		Body.add(chnImage);
		
		tptButton_1 = new JButton("Tirupati");
		tptButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tptButton_1.setAction(tptAction);
		tptButton_1.setBackground(new Color(204, 204, 255));
		tptButton_1.setBounds(47, 275, 185, 44);
		Body.add(tptButton_1);
		
		JLabel dlhImage = new JLabel("New label");
		dlhImage.setIcon(new ImageIcon(Navg.class.getResource("/Images/India_Gate_PTI.jpg")));
		dlhImage.setBounds(466, 42, 185, 219);
		Body.add(dlhImage);
		
		JButton chnButton = new JButton("Chennai");
		chnButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chnButton.setAction(chnAction);
		chnButton.setBackground(new Color(204, 204, 255));
		chnButton.setBounds(247, 275, 185, 44);
		Body.add(chnButton);
		
		JButton dlhButton = new JButton("Delhi");
		dlhButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dlhButton.setAction(dlhAction);
		dlhButton.setBackground(new Color(204, 204, 255));
		dlhButton.setBounds(466, 275, 185, 44);
		Body.add(dlhButton);
		
		JLabel otyImage = new JLabel("New label");
		otyImage.setIcon(new ImageIcon(Navg.class.getResource("/Images/image.jpg")));
		otyImage.setBounds(681, 86, 185, 130);
		Body.add(otyImage);
		
		JButton mubButton = new JButton("Ooty");
		mubButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mubButton.setAction(mubAction);
		mubButton.setBackground(new Color(204, 204, 255));
		mubButton.setBounds(681, 275, 185, 44);
		Body.add(mubButton);
		
		setVisible(true);
	}
	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Tirupati");
			putValue(SHORT_DESCRIPTION, "View places in Tirupati");
			
		}
		public void actionPerformed(ActionEvent e) {
			dispose();
			new TptFrame(crntUser);
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Chennai");
			putValue(SHORT_DESCRIPTION, "View places in Chennai");
		}
		public void actionPerformed(ActionEvent e) {
			dispose();
			new ChnFrame(crntUser);
		}
	}
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "Delhi");
			putValue(SHORT_DESCRIPTION, "View places in Delhi");
		}
		public void actionPerformed(ActionEvent e) {
			dispose();
			new DlhFrame(crntUser);
		}
	}
	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(NAME, "Mumbai");
			putValue(SHORT_DESCRIPTION, "View places in Mumbai");
		}
		public void actionPerformed(ActionEvent e) {
			dispose();
			new MubFrame(crntUser);
		}
	}
	private class SwingAction_4 extends AbstractAction {
		public SwingAction_4() {
			putValue(NAME, "Logout");
			putValue(SHORT_DESCRIPTION, "Go back to login page");
		}
		public void actionPerformed(ActionEvent e) {
			dispose();
			new Login();
		}
	}
	public Color getTptButtonBackground() {
		return tptButton_1.getBackground();
	}
	public void setTptButtonBackground(Color background) {
		tptButton_1.setBackground(background);
	}
}
