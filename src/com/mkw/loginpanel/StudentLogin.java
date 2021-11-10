package com.mkw.loginpanel;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import com.mkw.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;

import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Cursor;
import javax.swing.JButton;

public class StudentLogin extends JFrame {
	/*though i use different packages, i need to put a '/' before every image name.
	 * if i use default package then i don't need to put '/' before every image name.*/
	
	private String[] img = { "/demo1.jpg", "/demo2.jpg", "/demo3.jpg", "/demo4.jpg", "/demo5.jpg", "/demo6.jpg", "/demo7.jpg" };
	
	private JPanel contentPane;
	private JPanel LoginPanel;
	private JPanel paneluser;
	private JTextField txtUserName;
	private JTextField userPlaceHolder;
	private JLabel userPic;
	private JTextField passPlaceHolder;
	private JPasswordField passField;
	private JPanel panelpass;
	private JLabel passPic;
	private JPanel paneluser_1;
	private JTextField txtLoginAs;
	private JLabel workingPic;
	private JTextField working;
	private JPanel panelpic;
	private JLabel slideshow;
	private JLabel showPass;
	private JLabel hidePass;
	private JLabel btnforgot;
	private JButton btnLogin;
	private JLabel lblNewLabel;
	private JLabel lblsignup;
	private JLabel lblNewLabel_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentLogin frame = new StudentLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StudentLogin() {
		setLookAndFeel();
		initialize();
		connect();
		StudentSlideShow();
	}
	
	public static String ID1, NAME1, UNAME1, PHONE1, EMAIL1, WORK1, DEPT1,PASS1;
	public static String user,pass,work;
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JButton Exit;
	private JLabel heading;
	private JLabel loginImage;

	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/librarymanagement", "root", "");
		} catch (ClassNotFoundException ex) {

		} catch (SQLException ex) {

		}
	}
	
	public void StudentSlideShow() {
		Timer timer = new Timer(2000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int n = (int) Math.floor(Math.random() * 7);
				String image = img[n];
				ImageIcon imageicon = new ImageIcon(this.getClass().getResource(image));
				Image imgslide = imageicon.getImage().getScaledInstance(slideshow.getWidth(), slideshow.getHeight(), Image.SCALE_SMOOTH);
				imageicon = new ImageIcon(imgslide);
				slideshow.setIcon(imageicon);
			}
		});
		timer.start();
	}

	public void setLookAndFeel() {
		try {
				UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			}
			catch(Exception ee) {
				ee.printStackTrace();
			}
	}
	
	private void initialize() {
		/*
		 * If setDefaultLookAndFeelDecorated is true, the current LookAndFeel supports
		 * providing window decorations, and the current window manager supports
		 * undecorated windows, then newly created JFrames will have their Window
		 * decorations provided by the current LookAndFeel.
		 * 
		 * That's why setDefaultLookAndFeelDecorated(false) to make the JFrame Undecorated continuously.
		 */
		setDefaultLookAndFeelDecorated(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		addWindowListener( new WindowAdapter() {
			   public void windowOpened( WindowEvent e ){
				   txtUserName.requestFocus();
			     }
			   } ); 
		contentPane.setLayout(null);
		
		LoginPanel = new JPanel();
		LoginPanel.setBackground(new Color(0, 0, 51));
		LoginPanel.setBounds(593, 106, 307, 494);
		contentPane.add(LoginPanel);
		LoginPanel.setLayout(null);
		
		paneluser = new JPanel();
		paneluser.setOpaque(false);
		paneluser.setBounds(0, 73, 307, 41);
		LoginPanel.add(paneluser);
		paneluser.setLayout(null);
		
		txtUserName = new JTextField();
		txtUserName.setCaretColor(Color.WHITE);
		txtUserName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(arg0.getKeyChar()!=KeyEvent.VK_BACK_SPACE && arg0.getKeyChar()!=KeyEvent.VK_DELETE && arg0.getKeyChar()!=KeyEvent.VK_ENTER) {
					userPlaceHolder.setVisible(false);
					userPlaceHolder.setEnabled(false);
			    }
			    else if(txtUserName.getText().equals("")) {
			    	userPlaceHolder.setVisible(true);
			    	userPlaceHolder.setEnabled(false);
			    }
			}
		});
		txtUserName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUserName.setForeground(Color.WHITE);
		txtUserName.setBorder(new MatteBorder(0, 0, 4, 0, (Color) Color.RED));
		txtUserName.setOpaque(false);
		txtUserName.setBounds(10, 10, 232, 31);
		paneluser.add(txtUserName);
		txtUserName.setColumns(10);
		
		userPlaceHolder = new JTextField();
		userPlaceHolder.setDisabledTextColor(Color.DARK_GRAY);
		userPlaceHolder.setOpaque(false);
		userPlaceHolder.setBorder(null);
		userPlaceHolder.setEnabled(false);
		userPlaceHolder.setEditable(false);
		userPlaceHolder.setFont(new Font("Tahoma", Font.PLAIN, 15));
		userPlaceHolder.setForeground(Color.DARK_GRAY);
		userPlaceHolder.setText("Enter Username");
		userPlaceHolder.setBounds(11, 10, 231, 31);
		paneluser.add(userPlaceHolder);
		userPlaceHolder.setColumns(10);
		
		userPic = new JLabel("");
		userPic.setBounds(252, 2, 44, 39);
		ImageIcon ii = new ImageIcon(this.getClass().getResource("/user.png"));
		Image img2 = ii.getImage().getScaledInstance(userPic.getWidth(), userPic.getHeight(), Image.SCALE_SMOOTH);
		ii = new ImageIcon(img2);
		userPic.setIcon(ii);
		paneluser.add(userPic);
		
		panelpass = new JPanel();
		panelpass.setLayout(null);
		panelpass.setOpaque(false);
		panelpass.setBounds(0, 140, 307, 41);
		LoginPanel.add(panelpass);
		
		passField = new JPasswordField();
		//passField.setEchoChar('\u25CF');
		passField.setEchoChar('•');
		passField.setCaretColor(Color.WHITE);
		passField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(arg0.getKeyChar()!=KeyEvent.VK_BACK_SPACE && arg0.getKeyChar()!=KeyEvent.VK_DELETE && arg0.getKeyChar()!=KeyEvent.VK_ENTER) {
					passPlaceHolder.setVisible(false);
					passPlaceHolder.setEnabled(false);
			    }
			    else if(passField.getText().equals("")) {
			    	passPlaceHolder.setVisible(true);
			    	passPlaceHolder.setEnabled(false);
			    }
			}
		});
		passField.setForeground(Color.WHITE);
		passField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passField.setOpaque(false);
		passField.setColumns(10);
		passField.setBorder(new MatteBorder(0, 0, 4, 0, (Color) Color.RED));
		passField.setBounds(10, 10, 232, 31);
		panelpass.add(passField);
		
		passPlaceHolder = new JTextField();
		passPlaceHolder.setDisabledTextColor(Color.DARK_GRAY);
		passPlaceHolder.setText("Enter Password");
		passPlaceHolder.setOpaque(false);
		passPlaceHolder.setForeground(Color.DARK_GRAY);
		passPlaceHolder.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passPlaceHolder.setEnabled(false);
		passPlaceHolder.setEditable(false);
		passPlaceHolder.setColumns(10);
		passPlaceHolder.setBorder(null);
		passPlaceHolder.setBounds(11, 10, 231, 31);
		panelpass.add(passPlaceHolder);
		
		passPic = new JLabel("");
		passPic.setBounds(252, 2, 44, 39);
		ImageIcon ii1 = new ImageIcon(this.getClass().getResource("/preview.png"));
		Image img1 = ii1.getImage().getScaledInstance(passPic.getWidth(), passPic.getHeight(), Image.SCALE_SMOOTH);
		ii1 = new ImageIcon(img1);
		passPic.setIcon(ii1);
		panelpass.add(passPic);
		
		paneluser_1 = new JPanel();
		paneluser_1.setLayout(null);
		paneluser_1.setOpaque(false);
		paneluser_1.setBounds(0, 207, 307, 41);
		LoginPanel.add(paneluser_1);
		
		txtLoginAs = new JTextField();
		txtLoginAs.setEditable(false);
		txtLoginAs.setHorizontalAlignment(SwingConstants.RIGHT);
		txtLoginAs.setText("Login as");
		txtLoginAs.setOpaque(false);
		txtLoginAs.setForeground(Color.WHITE);
		txtLoginAs.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtLoginAs.setColumns(10);
		txtLoginAs.setCaretColor(Color.WHITE);
		txtLoginAs.setBorder(null);
		txtLoginAs.setBounds(10, 10, 114, 31);
		paneluser_1.add(txtLoginAs);
		
		workingPic = new JLabel("");
		workingPic.setBounds(252, 2, 44, 39);
		ImageIcon wrk = new ImageIcon(this.getClass().getResource("/workingAs.png"));
		Image img5 = wrk.getImage().getScaledInstance(workingPic.getWidth(), workingPic.getHeight(), Image.SCALE_SMOOTH);
		wrk = new ImageIcon(img5);
		workingPic.setIcon(wrk);
		paneluser_1.add(workingPic);
		
		working = new JTextField();
		working.setEditable(false);
		working.setText("Student");
		working.setOpaque(false);
		working.setForeground(Color.WHITE);
		working.setFont(new Font("Tahoma", Font.PLAIN, 18));
		working.setColumns(10);
		working.setCaretColor(Color.WHITE);
		working.setBorder(null);
		working.setBounds(134, 10, 108, 31);
		paneluser_1.add(working);
		
		showPass = new JLabel("");
		showPass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		showPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				passField.setEchoChar((char) 0);
				passField.setFont(new Font("Tahoma", Font.PLAIN, 15));
				showPass.setVisible(false);
				hidePass.setVisible(true);
			}
		});
		showPass.setBounds(10, 185, 25, 20);
		ImageIcon show = new ImageIcon(this.getClass().getResource("/eye8.png"));
		Image img3 = show.getImage().getScaledInstance(showPass.getWidth(), showPass.getHeight(), Image.SCALE_SMOOTH);
		show = new ImageIcon(img3);
		showPass.setIcon(show);
		LoginPanel.add(showPass);
		
		hidePass = new JLabel("");
		hidePass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hidePass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//passField.setEchoChar('\u25CF');
				passField.setEchoChar('•');
				showPass.setVisible(true);
				hidePass.setVisible(false);
			}
		});
		hidePass.setVisible(false);
		hidePass.setBounds(10, 185, 25, 20);
		ImageIcon hide = new ImageIcon(this.getClass().getResource("/hide6.png"));
		Image img4 = hide.getImage().getScaledInstance(hidePass.getWidth(), hidePass.getHeight(), Image.SCALE_SMOOTH);
		hide = new ImageIcon(img4);
		hidePass.setIcon(hide);
		LoginPanel.add(hidePass);
		
		btnforgot = new JLabel("Forgot Password?");
		btnforgot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnforgot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnforgot.setForeground(Color.CYAN);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btnforgot.setForeground(new Color(0, 153, 255));
			}
		});
		btnforgot.setHorizontalAlignment(SwingConstants.CENTER);
		btnforgot.setForeground(new Color(0, 153, 255));
		btnforgot.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnforgot.setBounds(100, 258, 101, 19);
		LoginPanel.add(btnforgot);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					user = txtUserName.getText();
					pass = passField.getText();
					work = working.getText();

					if (user.equals("") || pass.equals("") || work.equals("")) {
						JOptionPane.showMessageDialog(null, "Wrong Username or Password");
						txtUserName.requestFocus();
					}
					else {
						pst = con.prepareStatement("select ID,Name,Phone,Email,Department,Work,Username,Password from student where Username = ? and Password = ? and Work = ?");
						pst.setString(1, user);
						pst.setString(2, pass);
						pst.setString(3, work);
						ResultSet rs = pst.executeQuery();

						if (rs.next() == true) {
							ID1 = rs.getString(1);
							NAME1 = rs.getString(2);
							UNAME1 = rs.getString(7);
							PHONE1 = rs.getString(3);
							EMAIL1 = rs.getString(4);
							WORK1 = rs.getString(6);
							DEPT1 = rs.getString(5);
							PASS1 = rs.getString(8);

							JOptionPane.showMessageDialog(null, "Logging in Successfully");
							dispose();
							com.mkw.profilepanel.StudentProfile sp = new com.mkw.profilepanel.StudentProfile();
							sp.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null, "Wrong Username or Password");
						}
					}
				}
				catch (SQLException ex) {

				}
			}
		});
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setBackground(new Color(30, 144, 255));
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setFont(new Font("Dialog", Font.BOLD, 28));
		btnLogin.setBounds(10, 300, 287, 41);
		/*ImageIcon btn = new ImageIcon(this.getClass().getResource("/tickmark.jpg"));
		Image img7 = btn.getImage().getScaledInstance(40, btnLogin.getHeight(), Image.SCALE_SMOOTH);
		btn = new ImageIcon(img7);
		btnLogin.setIcon(btn);*/
		LoginPanel.add(btnLogin);
		
		lblNewLabel = new JLabel("New User?");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel.setForeground(new Color(0, 153, 255));
		lblNewLabel.setBounds(79, 357, 68, 21);
		LoginPanel.add(lblNewLabel);
		
		lblsignup = new JLabel("SignUp");
		lblsignup.setToolTipText("Click here to crate new account");
		lblsignup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblsignup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				com.mkw.loginpanel.SignUp sp = new com.mkw.loginpanel.SignUp();
				sp.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblsignup.setForeground(Color.CYAN);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				lblsignup.setForeground(new Color(0, 153, 255));
			}
		});
		lblsignup.setHorizontalAlignment(SwingConstants.CENTER);
		lblsignup.setForeground(new Color(0, 153, 255));
		lblsignup.setFont(new Font("Dialog", Font.BOLD, 16));
		lblsignup.setBounds(154, 357, 57, 21);
		LoginPanel.add(lblsignup);
		
		lblNewLabel_1 = new JLabel("Now");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(0, 153, 255));
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(217, 357, 31, 21);
		LoginPanel.add(lblNewLabel_1);
		
		loginImage = new JLabel("");
		loginImage.setBorder(new MatteBorder(4, 0, 4, 4, (Color) new Color(0, 0, 51)));
		loginImage.setBounds(0, 0, 307, 494);
		ImageIcon design = new ImageIcon(this.getClass().getResource("/best5.jpg"));
		Image image = design.getImage().getScaledInstance(loginImage.getWidth(), loginImage.getHeight(), Image.SCALE_SMOOTH);
		design = new ImageIcon(image);
		loginImage.setIcon(design);
		LoginPanel.add(loginImage);
		
		panelpic = new JPanel();
		panelpic.setBorder(new LineBorder(new Color(0, 0, 51), 4));
		panelpic.setBackground(new Color(51, 51, 255));
		panelpic.setBounds(0, 106, 592, 494);
		contentPane.add(panelpic);
		panelpic.setLayout(null);
		
		slideshow = new JLabel("");
		slideshow.setBorder(new LineBorder(new Color(0, 0, 51), 4));
		slideshow.setBounds(8, 9, 576, 476);
		ImageIcon mainImage = new ImageIcon(this.getClass().getResource("/Book28.jpg"));
		Image img10 = mainImage.getImage().getScaledInstance(slideshow.getWidth(), slideshow.getHeight(), Image.SCALE_SMOOTH);
		mainImage = new ImageIcon(img10);
		slideshow.setIcon(mainImage);
		panelpic.add(slideshow);
		
		Exit = new JButton("");
		Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		Exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Exit.setBackground(Color.RED);
				dispose();
			}
		});
		
		Exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Exit.setOpaque(false);
		Exit.setBackground(Color.RED);
		Exit.setBounds(870, 10, 24, 22);
		ImageIcon exit = new ImageIcon(this.getClass().getResource("/exit1.png"));
		Image img = exit.getImage().getScaledInstance(Exit.getWidth(), Exit.getHeight(), Image.SCALE_SMOOTH);
		exit = new ImageIcon(img);
		Exit.setIcon(exit);
		contentPane.add(Exit);
		
		heading = new JLabel("WELCOME TO LIBRARY MANAGEMENT");
		heading.setFont(new Font("Dialog", Font.BOLD, 40));
		heading.setForeground(Color.WHITE);
		heading.setHorizontalAlignment(SwingConstants.CENTER);
		heading.setBounds(0, 11, 900, 89);
		contentPane.add(heading);
	}
}
