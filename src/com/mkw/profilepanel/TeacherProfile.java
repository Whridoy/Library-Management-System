package com.mkw.profilepanel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableColumn;

import com.mkw.loginpanel.Login;

import net.proteanit.sql.DbUtils;

import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class TeacherProfile extends JFrame {
	private String[] img = { "/demo1.jpg", "/demo2.jpg", "/demo3.jpg", "/demo4.jpg", "/demo5.jpg", "/demo6.jpg", "/demo7.jpg" };
	
	private JPanel contentPane;
	private JLayeredPane layeredPane;
	private JPanel Home;
	private JPanel Profile;
	private JPanel menuPanel;
	private JButton btnProfile;
	private JButton Exit3;
	private JLabel lblNewLabel_1;
	private JTable bookTable;
	private JScrollPane scrollPane;
	private JButton btnHistory;
	private JButton Exit;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_3;
	private JTextField workField;
	private JTextField setName;
	private JButton Exit2;
	private JLabel lblNewLabel_2;
	private JTextField Idfield;
	private JLabel lblID;
	private JLabel lblName;
	private JTextField namefield;
	private JLabel lblPhone;
	private JTextField phonefield;
	private JLabel lblDepartment;
	private JTextField dptfield;
	private JLabel lblEmail;
	private JTextField mailfield;
	private JLabel lblUsername;
	private JTextField usernamefield;
	private JLabel lblPassword;
	private JPasswordField passfield;
	private JLabel showPass;
	private JLabel hidePass;
	private JButton btnEdit;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnHome;
	private JLabel slideshow;
	private JLabel lblNewLabel_4;
	private JButton btnBookRequest;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JButton btnLogout;
	private JButton btnHome_1;
	private JLabel bookImage;
	private JLabel menuImage;
	private JPanel BookHistory;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherProfile frame = new TeacherProfile();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TeacherProfile(){
		setLookAndFeel();
		initialize();
		connect();
		chk();
		profileslide();
		bookLoad();
		changeTeableHeaderFont();
	}
	
	Connection con;
	PreparedStatement pst,pst2;
	ResultSet rs,rs2;
	private JLabel profileImage;
	
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
	
	@SuppressWarnings("unused")
	private void chk() {
		com.mkw.loginpanel.Login lg = new com.mkw.loginpanel.Login();
		Idfield.setText(Login.ID1);
		namefield.setText(Login.NAME1);
		setName.setText(Login.NAME1);
		usernamefield.setText(Login.UNAME1);
		phonefield.setText(Login.PHONE1);
		mailfield.setText(Login.EMAIL1);
		dptfield.setText(Login.DEPT1);
		passfield.setText(Login.PASS1);
		workField.setText(Login.WORK1);
	}
	
	public void setLookAndFeel() {
		try {
				UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
			}
			catch(Exception ee) {
				ee.printStackTrace();
			}
	}
	
	public void changeTeableHeaderFont() {
		UIManager.put("TableHeader.font", new Font("NikoshGrameem", Font.BOLD, 18));
	}
	
	public void bookLoad() {
		try{	
			String id = Login.ID1;
			pst = con.prepareStatement("select * from issuebook where MemberID = ?");
			pst.setString(1, id);
			rs = pst.executeQuery();
			bookTable.setModel(DbUtils.resultSetToTableModel(rs));
		
			bookTable.setShowHorizontalLines(true);
			bookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			TableColumn column = null;
			for (int i = 0; i < 8; i++) {
			    column = bookTable.getColumnModel().getColumn(i);
			    if(i==0) {
			    	column.setPreferredWidth(90);
			    }
			    if (i == 1) {
			        column.setPreferredWidth(130);
			    }
			    else if(i==2) {
			    	column.setPreferredWidth(320);
			    }
			    else if(i==3) {
			    	column.setPreferredWidth(360);
			    }
			    else if(i==4) {
			    	column.setPreferredWidth(230);
			    }
			    else if(i==5) {
			        column.setPreferredWidth(130);
			    }
			    else if(i==6) {
			        column.setPreferredWidth(130);
			    }
			    else if(i==7) {
			        column.setPreferredWidth(130);
			    }
			}
			bookTable.setFont(new Font("SolaimanLipi", Font.PLAIN, 20));
			bookTable.setRowHeight(30);
			
        } catch(SQLException ex){
        	ex.printStackTrace();
        }
	}
	
	public void profileslide() {
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
	
	public void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	private void initialize() {
		setDefaultLookAndFeelDecorated(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 900, 600);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		/*_______________________Panel Home________________________*/
		Home = new JPanel();
		Home.setBackground(new Color(0, 0, 51));
		layeredPane.add(Home, "name_8151571214497");
		Home.setLayout(null);
		
		menuPanel = new JPanel();
		menuPanel.setBorder(new MatteBorder(4, 4, 0, 0, (Color) new Color(0, 0, 51)));
		menuPanel.setBackground(new Color(0, 0, 51));
		menuPanel.setBounds(0, 128, 306, 472);
		Home.add(menuPanel);
		menuPanel.setLayout(null);
		
		btnProfile = new JButton("");
		btnProfile.setBackground(Color.DARK_GRAY);
		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(Profile);
			}
		});
		btnProfile.setBounds(31, 103, 100, 100);
		ImageIcon prof = new ImageIcon(this.getClass().getResource("/profile.png"));
		Image image1 = prof.getImage().getScaledInstance(btnProfile.getWidth(), btnProfile.getHeight(), Image.SCALE_SMOOTH);
		prof = new ImageIcon(image1);
		btnProfile.setIcon(prof);
		menuPanel.add(btnProfile);
		
		btnHistory = new JButton("");
		btnHistory.setBackground(Color.DARK_GRAY);
		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(BookHistory);
			}
		});
		btnHistory.setBounds(31, 247, 100, 100);
		ImageIcon his = new ImageIcon(this.getClass().getResource("/history.png"));
		Image image2 = his.getImage().getScaledInstance(btnHistory.getWidth(), btnHistory.getHeight(), Image.SCALE_SMOOTH);
		his = new ImageIcon(image2);
		btnHistory.setIcon(his);
		menuPanel.add(btnHistory);
		
		btnBookRequest = new JButton("");
		btnBookRequest.setBackground(Color.DARK_GRAY);
		btnBookRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				com.mkw.bookrequest.BookRequest br = new com.mkw.bookrequest.BookRequest();
				br.setVisible(true);
			}
		});
		btnBookRequest.setBounds(179, 103, 100, 100);
		ImageIcon book = new ImageIcon(this.getClass().getResource("/add_book.png"));
		Image image3 = book.getImage().getScaledInstance(btnBookRequest.getWidth(), btnBookRequest.getHeight(), Image.SCALE_SMOOTH);
		book = new ImageIcon(image3);
		btnBookRequest.setIcon(book);
		menuPanel.add(btnBookRequest);
		
		lblNewLabel_5 = new JLabel("View Profile");
		lblNewLabel_5.setFont(new Font("NikoshGrameem", Font.PLAIN, 14));
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(31, 208, 100, 15);
		menuPanel.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Book Request");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setFont(new Font("NikoshGrameem", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(179, 208, 100, 15);
		menuPanel.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("Book History");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setFont(new Font("NikoshGrameem", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(31, 352, 100, 15);
		menuPanel.add(lblNewLabel_7);
		
		btnLogout = new JButton("");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				com.mkw.loginpanel.Login lg = new com.mkw.loginpanel.Login();
				lg.setVisible(true);
			}
		});
		btnLogout.setBackground(Color.DARK_GRAY);
		btnLogout.setBounds(179, 247, 100, 100);
		ImageIcon lg = new ImageIcon(this.getClass().getResource("/logout1.png"));
		Image Img = lg.getImage().getScaledInstance(btnLogout.getWidth(), btnLogout.getHeight(), Image.SCALE_SMOOTH);
		lg = new ImageIcon(Img);
		btnLogout.setIcon(lg);
		menuPanel.add(btnLogout);
		
		JLabel lblNewLabel_7_1 = new JLabel("LogOut");
		lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1.setForeground(Color.WHITE);
		lblNewLabel_7_1.setFont(new Font("NikoshGrameem", Font.PLAIN, 14));
		lblNewLabel_7_1.setBounds(179, 352, 100, 15);
		menuPanel.add(lblNewLabel_7_1);
		
		menuImage = new JLabel("");
		menuImage.setBorder(new MatteBorder(4, 4, 4, 0, (Color) new Color(0, 0, 51)));
		menuImage.setBounds(0, 0, 306, 472);
		ImageIcon menu = new ImageIcon(this.getClass().getResource("/best47.jpg"));
		Image mimg = menu.getImage().getScaledInstance(menuImage.getWidth(), menuImage.getHeight(), Image.SCALE_SMOOTH);
		menu = new ImageIcon(mimg);
		menuImage.setIcon(menu);
		menuPanel.add(menuImage);
		
		Exit = new JButton("");
		Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				com.mkw.joptionpanes.Confirmation w = new com.mkw.joptionpanes.Confirmation();
				w.setVisible(true);
			}
		});
		Exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Exit.setOpaque(false);
		Exit.setBackground(Color.RED);
		Exit.setBounds(866, 11, 24, 22);
		ImageIcon exit = new ImageIcon(this.getClass().getResource("/exit1.png"));
		Image e = exit.getImage().getScaledInstance(Exit.getWidth(), Exit.getHeight(), Image.SCALE_SMOOTH);
		exit = new ImageIcon(e);
		Exit.setIcon(exit);
		Home.add(Exit);
		
		lblNewLabel = new JLabel("Welcome To");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("NikoshGrameem", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(214, 12, 214, 42);
		Home.add(lblNewLabel);
		
		lblNewLabel_3 = new JLabel("Panel");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("NikoshGrameem", Font.BOLD, 30));
		lblNewLabel_3.setBounds(582, 11, 96, 42);
		Home.add(lblNewLabel_3);
		
		workField = new JTextField();
		workField.setHorizontalAlignment(SwingConstants.CENTER);
		workField.setFont(new Font("NikoshGrameem", Font.BOLD, 30));
		workField.setForeground(Color.WHITE);
		workField.setEditable(false);
		workField.setOpaque(false);
		workField.setBorder(null);
		workField.setBounds(432, 12, 146, 42);
		Home.add(workField);
		workField.setColumns(10);
		
		setName = new JTextField();
		setName.setHorizontalAlignment(SwingConstants.CENTER);
		setName.setBorder(null);
		setName.setOpaque(false);
		setName.setForeground(Color.WHITE);
		setName.setFont(new Font("NikoshGrameem", Font.BOLD, 30));
		setName.setEditable(false);
		setName.setBounds(10, 65, 880, 52);
		Home.add(setName);
		setName.setColumns(10);
		
		slideshow = new JLabel("");
		slideshow.setBorder(new LineBorder(new Color(0, 0, 51), 4));
		slideshow.setBounds(305, 128, 595, 472);
		ImageIcon mainImage = new ImageIcon(this.getClass().getResource("/Book28.jpg"));
		Image img2 = mainImage.getImage().getScaledInstance(slideshow.getWidth(), slideshow.getHeight(), Image.SCALE_SMOOTH);
		mainImage = new ImageIcon(img2);
		slideshow.setIcon(mainImage);
		Home.add(slideshow);
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBorder(new LineBorder(new Color(0, 0, 51), 4));
		lblNewLabel_4.setBounds(0, 0, 900, 600);
		ImageIcon titleIMG = new ImageIcon(this.getClass().getResource("/best57.jpg"));
		Image timg = titleIMG.getImage().getScaledInstance(lblNewLabel_4.getWidth(), lblNewLabel_4.getHeight(), Image.SCALE_SMOOTH);
		titleIMG = new ImageIcon(timg);
		lblNewLabel_4.setIcon(titleIMG);
		Home.add(lblNewLabel_4);
		
		/*_______________________Panel Profile________________________*/
		Profile = new JPanel();
		Profile.setBackground(new Color(0, 0, 51));
		layeredPane.add(Profile, "name_8167234978398");
		Profile.setLayout(null);
		
		Exit2 = new JButton("");
		Exit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				com.mkw.joptionpanes.Confirmation w = new com.mkw.joptionpanes.Confirmation();
				w.setVisible(true);
			}
		});
		Exit2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Exit2.setOpaque(false);
		Exit2.setBackground(Color.RED);
		Exit2.setBounds(866, 11, 24, 22);
		ImageIcon exit2 = new ImageIcon(this.getClass().getResource("/exit1.png"));
		Image e2 = exit2.getImage().getScaledInstance(Exit2.getWidth(), Exit2.getHeight(), Image.SCALE_SMOOTH);
		exit2 = new ImageIcon(e2);
		Exit2.setIcon(exit2);
		Profile.add(Exit2);
		
		lblNewLabel_2 = new JLabel("M Y    P R O F I L E");
		lblNewLabel_2.setFont(new Font("Nikosh", Font.BOLD, 30));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 11, 880, 65);
		Profile.add(lblNewLabel_2);
		
		Idfield = new JTextField();
		Idfield.setCaretColor(Color.WHITE);
		Idfield.setForeground(Color.WHITE);
		Idfield.setEditable(false);
		Idfield.setOpaque(false);
		Idfield.setFont(new Font("NikoshGrameem", Font.BOLD, 15));
		Idfield.setBounds(33, 155, 354, 33);
		Profile.add(Idfield);
		Idfield.setColumns(10);
		
		lblID = new JLabel("ID");
		lblID.setForeground(Color.WHITE);
		lblID.setFont(new Font("Nikosh", Font.BOLD, 11));
		lblID.setBounds(33, 130, 159, 22);
		Profile.add(lblID);
		
		lblName = new JLabel("NAME");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Nikosh", Font.BOLD, 11));
		lblName.setBounds(33, 220, 159, 22);
		Profile.add(lblName);
		
		namefield = new JTextField();
		namefield.setCaretColor(Color.WHITE);
		namefield.setOpaque(false);
		namefield.setForeground(Color.WHITE);
		namefield.setFont(new Font("NikoshGrameem", Font.BOLD, 15));
		namefield.setEditable(false);
		namefield.setColumns(10);
		namefield.setBounds(33, 245, 354, 33);
		Profile.add(namefield);
		
		lblPhone = new JLabel("PHONE");
		lblPhone.setForeground(Color.WHITE);
		lblPhone.setFont(new Font("Nikosh", Font.BOLD, 11));
		lblPhone.setBounds(33, 310, 159, 22);
		Profile.add(lblPhone);
		
		phonefield = new JTextField();
		phonefield.setCaretColor(Color.WHITE);
		phonefield.setOpaque(false);
		phonefield.setForeground(Color.WHITE);
		phonefield.setFont(new Font("NikoshGrameem", Font.BOLD, 15));
		phonefield.setEditable(false);
		phonefield.setColumns(10);
		phonefield.setBounds(33, 335, 354, 33);
		Profile.add(phonefield);
		
		lblDepartment = new JLabel("DEPARTMENT");
		lblDepartment.setForeground(Color.WHITE);
		lblDepartment.setFont(new Font("Nikosh", Font.BOLD, 11));
		lblDepartment.setBounds(33, 400, 159, 22);
		Profile.add(lblDepartment);
		
		dptfield = new JTextField();
		dptfield.setCaretColor(Color.WHITE);
		dptfield.setOpaque(false);
		dptfield.setForeground(Color.WHITE);
		dptfield.setFont(new Font("NikoshGrameem", Font.BOLD, 15));
		dptfield.setEditable(false);
		dptfield.setColumns(10);
		dptfield.setBounds(33, 425, 354, 33);
		Profile.add(dptfield);
		
		lblEmail = new JLabel("E-MAIL");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Nikosh", Font.BOLD, 11));
		lblEmail.setBounds(520, 130, 159, 22);
		Profile.add(lblEmail);
		
		mailfield = new JTextField();
		mailfield.setCaretColor(Color.WHITE);
		mailfield.setOpaque(false);
		mailfield.setForeground(Color.WHITE);
		mailfield.setFont(new Font("NikoshGrameem", Font.BOLD, 15));
		mailfield.setEditable(false);
		mailfield.setColumns(10);
		mailfield.setBounds(520, 155, 354, 33);
		Profile.add(mailfield);
		
		lblUsername = new JLabel("USERNAME");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Nikosh", Font.BOLD, 11));
		lblUsername.setBounds(520, 220, 159, 22);
		Profile.add(lblUsername);
		
		usernamefield = new JTextField();
		usernamefield.setCaretColor(Color.WHITE);
		usernamefield.setOpaque(false);
		usernamefield.setForeground(Color.WHITE);
		usernamefield.setFont(new Font("NikoshGrameem", Font.BOLD, 15));
		usernamefield.setEditable(false);
		usernamefield.setColumns(10);
		usernamefield.setBounds(520, 245, 354, 33);
		Profile.add(usernamefield);
		
		lblPassword = new JLabel("PASSWORD");
		lblPassword.setVisible(false);
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Nikosh", Font.BOLD, 11));
		lblPassword.setBounds(520, 310, 159, 22);
		Profile.add(lblPassword);
		
		passfield = new JPasswordField();
		passfield.setVisible(false);
		passfield.setEchoChar('•');
		passfield.setForeground(Color.WHITE);
		passfield.setCaretColor(Color.WHITE);
		passfield.setFont(new Font("Times New Roman", Font.BOLD, 20));
		passfield.setOpaque(false);
		passfield.setBounds(520, 335, 354, 33);
		Profile.add(passfield);
		
		showPass = new JLabel("");
		showPass.setVisible(false);
		showPass.setBounds(520, 370, 26, 23);
		showPass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		showPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				passfield.setEchoChar((char) 0);
				showPass.setVisible(false);
				hidePass.setVisible(true);
			}
		});
		ImageIcon show = new ImageIcon(this.getClass().getResource("/eye1_120px.png"));
		Image img = show.getImage().getScaledInstance(showPass.getWidth(), showPass.getHeight(), Image.SCALE_SMOOTH);
		show = new ImageIcon(img);
		showPass.setIcon(show);
		Profile.add(showPass);
		
		hidePass = new JLabel("");
		hidePass.setBounds(520, 370, 26, 23);
		hidePass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hidePass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				passfield.setEchoChar('•');
				showPass.setVisible(true);
				hidePass.setVisible(false);
			}
		});
		hidePass.setVisible(false);
		ImageIcon hide = new ImageIcon(this.getClass().getResource("/invisible1_120px.png"));
		Image img1 = hide.getImage().getScaledInstance(hidePass.getWidth(), hidePass.getHeight(), Image.SCALE_SMOOTH);
		hide = new ImageIcon(img1);
		hidePass.setIcon(hide);
		Profile.add(hidePass);
		
		btnEdit = new JButton("EDIT");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Idfield.setEditable(true);
				namefield.setEditable(true);				
				phonefield.setEditable(true);
				dptfield.setEditable(true);
				mailfield.setEditable(true);
				usernamefield.setEditable(true);
				lblPassword.setVisible(true);
				passfield.setVisible(true);
				showPass.setVisible(true);
				btnUpdate.setEnabled(true);
			}
		});
		btnEdit.setBackground(new Color(51, 51, 204));
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setFont(new Font("Dialog", Font.BOLD, 20));
		btnEdit.setBounds(136, 519, 135, 40);
		Profile.add(btnEdit);
		
		btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id,name,phn,dpt,work,mail,user,pass,luser,lpass;
				
				luser = com.mkw.loginpanel.Login.user;
				lpass = com.mkw.loginpanel.Login.pass;
				id = Idfield.getText();
				name = namefield.getText();
				phn = phonefield.getText();
				dpt = dptfield.getText();
				mail = mailfield.getText();
				work = workField.getText();
				user = usernamefield.getText();
				pass = String.valueOf(passfield.getPassword());

				try {
					pst = con.prepareStatement("update teacher set ID=?,Name=?,Phone=?,Email=?,Department=?,Work=?,Username=?,Password=? where Username = ? and Password = ?");
					
					pst.setString(1, id);
					pst.setString(2, name);
					pst.setString(3, phn);
					pst.setString(4, mail);
					pst.setString(5, dpt);
					pst.setString(6, work);
					pst.setString(7, user);
					pst.setString(8, pass);
					pst.setString(9, luser);
					pst.setString(10, lpass);
					
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Account Updated Successfully");
							
					Idfield.setText("");
					namefield.setText("");
					phonefield.setText("");
					dptfield.setText("");
					mailfield.setText("");
					usernamefield.setText("");
					passfield.setText("");
					
					Idfield.requestFocus();
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setEnabled(false);
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("Dialog", Font.BOLD, 20));
		btnUpdate.setBackground(new Color(51, 51, 204));
		btnUpdate.setBounds(304, 519, 135, 40);
		Profile.add(btnUpdate);
		
		btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(null, "Do you want to delete your account?","Warning!",JOptionPane.YES_NO_OPTION)==0) {
					String luser,lpass;
					
					luser = com.mkw.loginpanel.Login.user;
					lpass = com.mkw.loginpanel.Login.pass;
					
					try {
						pst = con.prepareStatement("delete from teacher where Username = ? and Password = ?");
						
						pst.setString(1, luser);
						pst.setString(2, lpass);
						
						pst.executeUpdate();
						
						JOptionPane.showMessageDialog(null, "Account Deleted Successfully");
						dispose();
						com.mkw.loginpanel.Login lg = new com.mkw.loginpanel.Login();
						lg.setVisible(true);
					}
					catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Dialog", Font.BOLD, 20));
		btnDelete.setBackground(new Color(51, 51, 204));
		btnDelete.setBounds(472, 519, 135, 40);
		Profile.add(btnDelete);
		
		btnHome = new JButton("HOME");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(Home);
			}
		});
		btnHome.setForeground(Color.WHITE);
		btnHome.setFont(new Font("Dialog", Font.BOLD, 20));
		btnHome.setBackground(new Color(51, 51, 204));
		btnHome.setBounds(640, 519, 135, 40);
		Profile.add(btnHome);
		
		profileImage = new JLabel("");
		profileImage.setBorder(new LineBorder(new Color(0, 0, 51), 4));
		profileImage.setBounds(0, 0, 900, 600);
		ImageIcon p_img = new ImageIcon(this.getClass().getResource("/best63.jpg"));
		Image p = p_img.getImage().getScaledInstance(profileImage.getWidth(), profileImage.getHeight(), Image.SCALE_SMOOTH);
		p_img = new ImageIcon(p);
		profileImage.setIcon(p_img);
		Profile.add(profileImage);
		
		/*_____________Panel BookHistory___________*/
		BookHistory = new JPanel();
		BookHistory.setBackground(new Color(0, 0, 51));
		layeredPane.add(BookHistory, "name_3386977837825");
		BookHistory.setLayout(null);
		
		Exit3 = new JButton("");
		Exit3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				com.mkw.joptionpanes.Confirmation w = new com.mkw.joptionpanes.Confirmation();
				w.setVisible(true);
			}
		});
		Exit3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Exit3.setOpaque(false);
		Exit3.setBackground(Color.RED);
		Exit3.setBounds(866, 11, 24, 22);
		ImageIcon exit3 = new ImageIcon(this.getClass().getResource("/exit1.png"));
		Image e3 = exit3.getImage().getScaledInstance(Exit3.getWidth(), Exit3.getHeight(), Image.SCALE_SMOOTH);
		exit3 = new ImageIcon(e3);
		Exit3.setIcon(exit3);
		BookHistory.add(Exit3);
		
		lblNewLabel_1 = new JLabel("M Y    B O O K    H I S T O R Y");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 30));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 25, 846, 59);
		BookHistory.add(lblNewLabel_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.DARK_GRAY);
		scrollPane.setBounds(10, 128, 880, 361);
		BookHistory.add(scrollPane);
		
		bookTable = new JTable();
		bookTable.setEnabled(false);
		bookTable.setBackground(Color.DARK_GRAY);
		scrollPane.setViewportView(bookTable);
		
		btnHome_1 = new JButton("HOME");
		btnHome_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(Home);
			}
		});
		btnHome_1.setBackground(new Color(51, 51, 204));
		btnHome_1.setForeground(Color.WHITE);
		btnHome_1.setFont(new Font("Dialog", Font.BOLD, 20));
		btnHome_1.setBounds(390, 533, 120, 35);
		BookHistory.add(btnHome_1);
		
		bookImage = new JLabel("");
		bookImage.setBounds(0, 0, 900, 600);
		ImageIcon tbl = new ImageIcon(this.getClass().getResource("/best63.jpg"));
		Image im = tbl.getImage().getScaledInstance(bookImage.getWidth(), bookImage.getHeight(), Image.SCALE_SMOOTH);
		tbl = new ImageIcon(im);
		bookImage.setIcon(tbl);
		BookHistory.add(bookImage);
	}
}
