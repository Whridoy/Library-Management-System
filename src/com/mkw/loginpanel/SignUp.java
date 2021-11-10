package com.mkw.loginpanel;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import com.mkw.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField phone;
	private JTextField mail;
	private JTextField username;
	private JTextField ID;
	private JLabel lblTeacher;
	private JLabel lblStudent;
	private JButton btnsignup;
	private JButton Exit;
	private JComboBox comboBox;
	private JPanel designPanel;
	private JButton btnreset;
	private JButton btncancel;
	private JLabel designpic;
	private JPanel signUpPanel;
	private JComboBox Department;
	private JPanel passPanel;
	private JPasswordField pass;
	private JLabel showPass;
	private JLabel hidePass;
	private JLabel signUpImage;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.put("OptionPane.btnBackground", Color.RED);
					UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Dialog",Font.BOLD,15)));
					UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("Dialog",Font.BOLD,15)));
					UIManager.put("OptionPane.background", Color.WHITE);
					UIManager.put("OptionPane.messagebackground", Color.WHITE);
					UIManager.put("OptionPane.messageForeground", Color.RED);
					UIManager.put("Panel.background", Color.CYAN);
					SignUp frame = new SignUp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SignUp() {
		setLookAndFeel();
		initialize();
		connect();
	}
	
	String id,name1,phn,email,dpt,wrk,uname,pas;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JLabel titleImage;
	
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/librarymanagement","root","");
			
		}
		catch(ClassNotFoundException ex) {
			
		}
		catch(SQLException ex) {
			
		}
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
		contentPane.setBorder(new LineBorder(new Color(0, 0, 51), 4));
		setContentPane(contentPane);
		/*For force focus on the first textfield in the window*/
		addWindowListener( new WindowAdapter() {
			   public void windowOpened( WindowEvent e ){
				   name.requestFocus();
			     }
			   } ); 
		contentPane.setLayout(null);
		
		Exit = new JButton("");
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
		Exit.setBounds(869, 10, 22, 20);
		ImageIcon exit = new ImageIcon(this.getClass().getResource("/exit1.png"));
		Image img = exit.getImage().getScaledInstance(Exit.getWidth(), Exit.getHeight(), Image.SCALE_SMOOTH);
		exit = new ImageIcon(img);
		Exit.setIcon(exit);
		contentPane.add(Exit);
		
		JLabel lblNewLabel_2 = new JLabel("M E M B E R   R E G I S T R A T I O N");
		lblNewLabel_2.setBorder(null);
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 40));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(6, 7, 888, 66);
		contentPane.add(lblNewLabel_2);
		
		designPanel = new JPanel();
		designPanel.setBackground(new Color(0, 0, 51));
		designPanel.setBorder(new MatteBorder(0, 0, 0, 4, (Color) new Color(0, 0, 51)));
		designPanel.setBounds(6, 81, 504, 512);
		contentPane.add(designPanel);
		designPanel.setLayout(null);
		
		designpic = new JLabel("");
		designpic.setBounds(0, 0, 499, 512);
		ImageIcon pic = new ImageIcon(this.getClass().getResource("/Book30.jpg"));
		Image img1 = pic.getImage().getScaledInstance(designpic.getWidth(), designpic.getHeight(), Image.SCALE_SMOOTH);
		pic = new ImageIcon(img1);
		designpic.setIcon(pic);
		designPanel.add(designpic);
		
		signUpPanel = new JPanel();
		signUpPanel.setBackground(new Color(0, 0, 51));
		signUpPanel.setBorder(new MatteBorder(0, 4, 0, 0, (Color) new Color(51, 153, 255)));
		signUpPanel.setBounds(508, 81, 386, 512);
		contentPane.add(signUpPanel);
		signUpPanel.setLayout(null);
		
		name = new JTextField();
		name.setCaretColor(Color.WHITE);
		name.setBounds(43, 22, 313, 30);
		signUpPanel.add(name);
		name.setFont(new Font("Tahoma", Font.PLAIN, 17));
		name.setForeground(Color.WHITE);
		name.setOpaque(false);
		name.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.RED));
		name.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(43, 52, 313, 25);
		signUpPanel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.RED);
		
		phone = new JTextField();
		phone.setCaretColor(Color.WHITE);
		phone.setBounds(43, 150, 141, 30);
		signUpPanel.add(phone);
		phone.setFont(new Font("Tahoma", Font.PLAIN, 17));
		phone.setForeground(Color.WHITE);
		phone.setOpaque(false);
		phone.setColumns(10);
		phone.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.RED));
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(43, 180, 141, 25);
		signUpPanel.add(lblPhone);
		lblPhone.setForeground(Color.RED);
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		mail = new JTextField();
		mail.setCaretColor(Color.WHITE);
		mail.setBounds(43, 86, 313, 30);
		signUpPanel.add(mail);
		mail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		mail.setForeground(Color.WHITE);
		mail.setOpaque(false);
		mail.setColumns(10);
		mail.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.RED));
		
		JLabel lblDepartment = new JLabel("E-mail");
		lblDepartment.setBounds(43, 116, 313, 25);
		signUpPanel.add(lblDepartment);
		lblDepartment.setForeground(Color.RED);
		lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		username = new JTextField();
		username.setCaretColor(Color.WHITE);
		username.setBounds(43, 214, 313, 30);
		signUpPanel.add(username);
		username.setFont(new Font("Tahoma", Font.PLAIN, 17));
		username.setForeground(Color.WHITE);
		username.setOpaque(false);
		username.setColumns(10);
		username.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.RED));
		
		JLabel lblNewLabel_1 = new JLabel("User Name");
		lblNewLabel_1.setBounds(43, 244, 313, 25);
		signUpPanel.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setBounds(43, 308, 313, 25);
		signUpPanel.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setForeground(Color.RED);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		comboBox = new JComboBox();
		comboBox.setBounds(43, 342, 131, 30);
		signUpPanel.add(comboBox);
		comboBox.setBackground(Color.WHITE);
		comboBox.setForeground(Color.BLACK);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		comboBox.setOpaque(false);
		comboBox.setBorder(null);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Teacher", "Student"}));
		
		JLabel lblNewLabel_1_1_1 = new JLabel("SignUP as");
		lblNewLabel_1_1_1.setBounds(43, 372, 131, 25);
		signUpPanel.add(lblNewLabel_1_1_1);
		lblNewLabel_1_1_1.setForeground(Color.RED);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		ID = new JTextField();
		ID.setCaretColor(Color.WHITE);
		ID.setBounds(208, 342, 148, 30);
		signUpPanel.add(ID);
		ID.setVisible(false);
		ID.setOpaque(false);
		ID.setForeground(Color.WHITE);
		ID.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ID.setColumns(10);
		ID.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.RED));
		
		lblTeacher = new JLabel("Teacher ID");
		lblTeacher.setBounds(208, 372, 148, 25);
		signUpPanel.add(lblTeacher);
		lblTeacher.setVisible(false);
		lblTeacher.setForeground(Color.RED);
		lblTeacher.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblStudent = new JLabel("Student ID");
		lblStudent.setBounds(208, 372, 148, 25);
		signUpPanel.add(lblStudent);
		lblStudent.setVisible(false);
		lblStudent.setForeground(Color.RED);
		lblStudent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		btnsignup = new JButton("Register");
		btnsignup.setBounds(17, 438, 114, 47);
		signUpPanel.add(btnsignup);
		btnsignup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnsignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {						
				try {
						id = ID.getText();
						name1 = name.getText();
						phn = phone.getText();
						email = mail.getText();
						dpt = Department.getSelectedItem().toString();
						wrk = comboBox.getSelectedItem().toString();
						uname = username.getText();
						pas = pass.getText();
						
						if(id.equals("")||name1.equals("")||phn.equals("")||email.equals("")||dpt.equals("")
								||wrk.equals("")||uname.equals("")||pas.equals("")) {
							JOptionPane.showMessageDialog(null, "Please Fill up all the field");
						}
						
					else {
						if (wrk.equals("Teacher")) {
							pst = con.prepareStatement("select ID,Name,Phone,Email,Department,Work,Username,Password from teacher where Username = ?");
							pst.setString(1, uname);
							ResultSet rs = pst.executeQuery();

							if (rs.next()) {
								JOptionPane.showMessageDialog(null, "Username already exists");
								username.setText("");
								pass.setText("");
								comboBox.setSelectedItem("");
								ID.setText("");
							}

							else {
								pst = con.prepareStatement("insert into teacher(ID,Name,Phone,Email,Department,Work,Username,Password)values(?,?,?,?,?,?,?,?)");

								pst.setString(1, id);
								pst.setString(2, name1);
								pst.setString(3, phn);
								pst.setString(4, email);
								pst.setString(5, dpt);
								pst.setString(6, wrk);
								pst.setString(7, uname);
								pst.setString(8, pas);
								pst.executeUpdate();

								ID.setText("");
								name.setText("");
								phone.setText("");
								mail.setText("");
								Department.setSelectedItem("");
								comboBox.setSelectedItem("");
								username.setText("");
								pass.setText("");
								ID.setVisible(false);
								lblTeacher.setVisible(false);
								lblStudent.setVisible(false);
								username.requestFocus();
								
								JOptionPane.showMessageDialog(null, "Account Created Successfully");
								dispose();
								com.mkw.loginpanel.Login lg = new com.mkw.loginpanel.Login();
								lg.setVisible(true);
							}
							
						}
						
						else if (wrk.equals("Student")) {
							pst = con.prepareStatement("select ID,Name,Phone,Email,Department,Work,Username,Password from student where Username = ?");
							pst.setString(1, uname);
							ResultSet rs = pst.executeQuery();

							if (rs.next()) {
								JOptionPane.showMessageDialog(null, "Username already exists");
								username.setText("");
								pass.setText("");
								comboBox.setSelectedItem("");
								ID.setText("");
							}

							else {
								
								pst = con.prepareStatement("insert into student(ID,Name,Phone,Email,Department,Work,Username,Password)values(?,?,?,?,?,?,?,?)");

								pst.setString(1, id);
								pst.setString(2, name1);
								pst.setString(3, phn);
								pst.setString(4, email);
								pst.setString(5, dpt);
								pst.setString(6, wrk);
								pst.setString(7, uname);
								pst.setString(8, pas);
								pst.executeUpdate();

								ID.setText("");
								name.setText("");
								phone.setText("");
								mail.setText("");
								Department.setSelectedItem("");
								comboBox.setSelectedItem("");
								username.setText("");
								pass.setText("");
								ID.setVisible(false);
								lblTeacher.setVisible(false);
								lblStudent.setVisible(false);
								username.requestFocus();

								JOptionPane.showMessageDialog(null, "Account Created Successfully");
								dispose();
								com.mkw.loginpanel.Login lg = new com.mkw.loginpanel.Login();
								lg.setVisible(true);
							}
							
						}
					}
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnsignup.setFont(new Font("Dialog", Font.BOLD, 20));
		btnsignup.setForeground(Color.WHITE);
		btnsignup.setBackground(new Color(51, 153, 255));
		
		btnreset = new JButton("Reset");
		btnreset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				name.setText("");
				mail.setText("");
				phone.setText("");
				Department.setSelectedItem("");
				username.setText("");
				pass.setText("");
				comboBox.setSelectedItem("");
				ID.setText("");
				ID.setVisible(false);
				lblTeacher.setVisible(false);
				lblStudent.setVisible(false);
				name.requestFocus();
			}
		});
		btnreset.setBounds(142, 438, 114, 47);
		signUpPanel.add(btnreset);
		btnreset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnreset.setForeground(Color.WHITE);
		btnreset.setFont(new Font("Dialog", Font.BOLD, 20));
		btnreset.setBackground(new Color(51, 153, 255));
		
		btncancel = new JButton("Cancel");
		btncancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				com.mkw.loginpanel.Login lg = new com.mkw.loginpanel.Login();
				lg.setVisible(true);
			}
		});
		btncancel.setBounds(265, 438, 114, 47);
		signUpPanel.add(btncancel);
		btncancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btncancel.setForeground(Color.WHITE);
		btncancel.setFont(new Font("Dialog", Font.BOLD, 20));
		btncancel.setBackground(new Color(51, 153, 255));
		
		Department = new JComboBox();
		Department.setModel(new DefaultComboBoxModel(new String[] {"", "CSE", "EEE", "CCE", "ETE", "CIVIL", "PHARMACY", "QSIS", "DIS", "SHIS", "BUSINESS STUDIES", "ELL", "ALL", "LAW", "ECONOMICS"}));
		Department.setOpaque(false);
		Department.setForeground(Color.BLACK);
		Department.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Department.setBorder(null);
		Department.setBackground(Color.WHITE);
		Department.setBounds(208, 150, 148, 30);
		signUpPanel.add(Department);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Department");
		lblNewLabel_1_1_1_1.setForeground(Color.RED);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1.setBounds(208, 180, 148, 25);
		signUpPanel.add(lblNewLabel_1_1_1_1);
		
		passPanel = new JPanel();
		passPanel.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(255, 0, 0)));
		passPanel.setOpaque(false);
		passPanel.setBounds(43, 267, 313, 41);
		signUpPanel.add(passPanel);
		passPanel.setLayout(null);
		
		pass = new JPasswordField();
		pass.setEchoChar('•');
		pass.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pass.setForeground(Color.WHITE);
		pass.setCaretColor(Color.WHITE);
		pass.setOpaque(false);
		pass.setBorder(null);
		pass.setBounds(0, 10, 274, 30);
		passPanel.add(pass);
		
		showPass = new JLabel("");
		showPass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		showPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				pass.setEchoChar((char) 0);
				showPass.setVisible(false);
				hidePass.setVisible(true);
			}
		});
		showPass.setBounds(287, 15, 24, 21);
		ImageIcon show = new ImageIcon(this.getClass().getResource("/eye1_120px.png"));
		Image img3 = show.getImage().getScaledInstance(showPass.getWidth(), showPass.getHeight(), Image.SCALE_SMOOTH);
		show = new ImageIcon(img3);
		showPass.setIcon(show);
		passPanel.add(showPass);
		
		hidePass = new JLabel("");
		hidePass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hidePass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pass.setEchoChar('•');
				showPass.setVisible(true);
				hidePass.setVisible(false);
			}
		});
		hidePass.setVisible(false);
		hidePass.setBounds(287, 15, 24, 21);
		ImageIcon hide = new ImageIcon(this.getClass().getResource("/invisible1_120px.png"));
		Image img4 = hide.getImage().getScaledInstance(hidePass.getWidth(), hidePass.getHeight(), Image.SCALE_SMOOTH);
		hide = new ImageIcon(img4);
		hidePass.setIcon(hide);
		passPanel.add(hidePass);
		
		signUpImage = new JLabel("");
		signUpImage.setBounds(10, 0, 376, 512);
		ImageIcon design = new ImageIcon(this.getClass().getResource("/a14.jpg"));
		Image img2 = design.getImage().getScaledInstance(signUpImage.getWidth(), signUpImage.getHeight(), Image.SCALE_SMOOTH);
		design = new ImageIcon(img2);
		signUpImage.setIcon(design);
		signUpPanel.add(signUpImage);
		
		titleImage = new JLabel("");
		titleImage.setBounds(6, 7, 888, 66);
		ImageIcon title = new ImageIcon(this.getClass().getResource("/title2.jpg"));
		Image img5 = title.getImage().getScaledInstance(titleImage.getWidth(), titleImage.getHeight(), Image.SCALE_SMOOTH);
		title = new ImageIcon(img5);
		titleImage.setIcon(title);
		contentPane.add(titleImage);
		
		comboBox.addItemListener(new ItemListener() {
		    public void itemStateChanged(ItemEvent arg0) {
		    	if(comboBox.getSelectedItem().equals("Teacher")) {
					ID.setVisible(true);
					lblTeacher.setVisible(true);
					lblStudent.setVisible(false);
					ID.setText("");
					ID.requestFocus();
				}
				else if(comboBox.getSelectedItem().equals("Student")) {
					ID.setVisible(true);
					lblTeacher.setVisible(false);
					lblStudent.setVisible(true);
					ID.setText("");
					ID.requestFocus();
				}
		    }
		});
	}
}