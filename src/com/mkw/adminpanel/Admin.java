package com.mkw.adminpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.event.ListSelectionEvent;

import com.mkw.loginpanel.Login;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Admin extends JFrame {

	private String[] img = { "/demo1.jpg", "/demo2.jpg", "/demo3.jpg", "/demo4.jpg", "/demo5.jpg", "/demo6.jpg", "/demo7.jpg" };
	
	private JPanel contentPane;
	private JLayeredPane layeredPane;
	private JPanel PanelAdmin;
	private JPanel Catagory;
	private JPanel AddingBook;
	private JPanel IssueBook;
	private JPanel Menu;
	private JButton btncatagory;
	private JButton btnAuthor;
	private JButton btnPublisher;
	private JButton btnBook;
	private JButton btnMember;
	private JButton btnIssueBook;
	private JButton btnReturnBook;
	private JButton btnlogout;
	private JPanel admininfo;
	private JPanel panelpic;
	private JLabel slideshow;
	private JButton Exit;
	private JLabel lblimg;
	private JPanel panelsearch;
	private JTextField serialtext;
	private JTextField txtSearchByCatagory;
	private JList list;
	private JScrollPane listscrol;
	private JLabel catagorySearchImage;
	private JLabel lbltitle;
	private JButton Exit1;
	private JTextField catname;
	private JTextField catPlaceHolder;
	private JComboBox statusbox;
	private JTable catagorytable;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnHome;
	private JScrollPane scrollPane;
	private JLabel addingTitle;
	private JButton Exit2;
	private JPanel booksearch;
	private JTextField textSearch;
	private JTextField txtSearchBookBy;
	private JLabel SearchLabel;
	private JList bookList;
	private JScrollPane bookScroll;
	private JTextField txtbname;
	private JTextField txtauthor;
	private JLabel lblAuthorName;
	private JTextField txtpublisher;
	private JLabel lblPublisherName;
	private JLabel lblTotalCopies;
	private JLabel lblEntryDate;
	private JTable tableissue;
	private JLabel lblNewLabel_1;
	private JLabel lblBookCount;
	private JButton bookEntry;
	private JButton bookReset;
	private JButton bookUpdate;
	private JButton bookDelete;
	private JButton bookHome;
	private JDateChooser entryDate;
	private JTable bookRecord;
	private JScrollPane scrollPane_2;
	private JButton btnHome_1;
	private JButton btnIssue;
	private JSpinner txtcopies;
	private JComboBox itembox;
	private JPanel ReturnBook;
	private JTable returnTable;
	private JScrollPane scrollPane_3;
	private JTextField idInput;
	private JButton btnReturn;
	private JButton btnHome_2;
	private JDateChooser returnDate;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Admin(){
		setLookAndFeel();
		initialize();
		connect();
		categoryTableload();
		changeTeableHeaderFont();
		tableBookEntry();
		adminSlide();
		getsum();
		category();
		issueLoad();
		returnTableLoad();
	}
	
	Connection con;
	PreparedStatement pst,pst2;
	ResultSet rs,rs2;
	String memberName;
	String id,member,book,rqst,i_date,r_date;
	private JLabel catImg;
	private JLabel bookAddImage;
	private JButton Exit3;
	private JLabel lblNewLabel_2;
	private JLabel issueImage;
	private JButton Exit4;
	private JLabel lblNewLabel_3;
	private JLabel returnImage;
	private JLabel titleImage;
	
	/*Change Font of JTable Header.It changes all the JTable font added in the JFrame*/
	public void changeTeableHeaderFont() {
		UIManager.put("TableHeader.font", new Font("NikoshGrameem", Font.BOLD, 16));
	}
	
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
	
	/*Category Table Load into Category Panel*/
	public void categoryTableload() {
		try {
			pst = con.prepareStatement("select * from catagory");
			rs = pst.executeQuery();
			catagorytable.setModel(DbUtils.resultSetToTableModel(rs));
			
			catagorytable.setShowHorizontalLines(true);
			catagorytable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			TableColumn column = null;
			for (int i = 0; i < 3; i++) {
			    column = catagorytable.getColumnModel().getColumn(i);
			    if(i==0) {
			    	column.setPreferredWidth(70);
			    }
			    if (i == 1) {
			        column.setPreferredWidth(200);
			    }
			    else if(i==2) {
			    	column.setPreferredWidth(120);
			    }
			}
			catagorytable.setFont(new Font("SolaimanLipi", Font.BOLD, 18));
			catagorytable.setRowHeight(30);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*Count the summation of total books in library*/
	public void getsum() {
		int i;
		int sum = 0;
		for(i=0; i < tableissue.getRowCount(); i++) {
			int Total = Integer.parseInt(String.valueOf(tableissue.getValueAt(i, 5)));
			sum = sum + Total;
		}
		lblBookCount.setText(String.valueOf(sum));
	}
	
	/*Load the table from entry into AddingBook Panel*/
	public void tableBookEntry() {
		try {
			pst = con.prepareStatement("select * from entry");
			rs = pst.executeQuery();
			tableissue.setModel(DbUtils.resultSetToTableModel(rs));
		
			tableissue.setShowHorizontalLines(true);
			tableissue.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			TableColumn column = null;
			for (int i = 0; i < 7; i++) {
			    column = tableissue.getColumnModel().getColumn(i);
			    if(i==0) {
			    	column.setPreferredWidth(70);
			    }
			    if (i == 1) {
			        column.setPreferredWidth(240);
			    }
			    else if(i==2) {
			    	column.setPreferredWidth(110);
			    }
			    else if(i==3 || i==4) {
			    	column.setPreferredWidth(220);
			    }
			    else if(i==5) {
			        column.setPreferredWidth(100);
			    }
			    else if(i==6) {
			        column.setPreferredWidth(120);
			    }
			}
			tableissue.setFont(new Font("SolaimanLipi", Font.PLAIN, 20));
			tableissue.setRowHeight(30);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*Load category from database to combobox into AddBooks panel*/
	public void category() {
		try {
			pst = con.prepareStatement("select * from catagory");
			rs = pst.executeQuery();
			while (rs.next()) {
				// String bookCategory = rs.getString(2);
				// itembox.setSelectedItem(bookCategory);
				String bookCategory = rs.getString("Category");
				itembox.addItem(bookCategory);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}
	
	/*Load Issue Book List from database to Table into IssueBook Panel*/
	public void issueLoad() {
		try {
			pst = con.prepareStatement("select Issue_ID,MemberID,MemberName,BookName,RequestTime,IssueDate,LastDate from issuebook");
			rs = pst.executeQuery();
			bookRecord.setModel(DbUtils.resultSetToTableModel(rs));
		
			bookRecord.setShowHorizontalLines(true);
			bookRecord.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			TableColumn column = null;
			for (int i = 0; i < 7; i++) {
			    column = bookRecord.getColumnModel().getColumn(i);
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
			}
			bookRecord.setFont(new Font("SolaimanLipi", Font.PLAIN, 20));
			bookRecord.setRowHeight(30);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*Load Issue Book List as a return table from database to Table into ReturnBook Panel*/
	public void returnTableLoad() {
		try{	
			pst = con.prepareStatement("select * from issuebook");
			rs = pst.executeQuery();
			returnTable.setModel(DbUtils.resultSetToTableModel(rs));
		
			returnTable.setShowHorizontalLines(true);
			returnTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			TableColumn column = null;
			for (int i = 0; i < 8; i++) {
			    column = returnTable.getColumnModel().getColumn(i);
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
			returnTable.setFont(new Font("SolaimanLipi", Font.PLAIN, 20));
			returnTable.setRowHeight(30);
			
        } catch(SQLException ex){
        	ex.printStackTrace();
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
	
	public void adminSlide() {
		Timer timer = new Timer(2000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int n = (int) Math.floor(Math.random() * 7);
				String image = img[n];
				ImageIcon icon = new ImageIcon(this.getClass().getResource(image));
				Image img1 = icon.getImage().getScaledInstance(slideshow.getWidth(), slideshow.getHeight(), Image.SCALE_SMOOTH);
				icon = new ImageIcon(img1);
				slideshow.setIcon(icon);
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
		contentPane.setBackground(new Color(0, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		addWindowListener( new WindowAdapter() {
			   public void windowOpened( WindowEvent e ){
				   //txtUserName.requestFocus();
			     }
			   } ); 
		contentPane.setLayout(null);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 900, 600);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		/*__________________________Home_________________________*/
		PanelAdmin = new JPanel();
		PanelAdmin.setBackground(new Color(0, 0, 51));
		layeredPane.add(PanelAdmin, "name_28845313961900");
		PanelAdmin.setLayout(null);
		
		Menu = new JPanel();
		Menu.setBorder(new MatteBorder(4, 4, 4, 0, (Color) new Color(0, 0, 51)));
		Menu.setBackground(new Color(51, 51, 204));
		Menu.setBounds(0, 0, 356, 598);
		PanelAdmin.add(Menu);
		Menu.setLayout(null);
		
		btncatagory = new JButton("CATAGORY");
		btncatagory.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btncatagory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(Catagory);
			}
		});
		btncatagory.setBackground(new Color(51, 153, 255));
		btncatagory.setForeground(Color.WHITE);
		btncatagory.setFont(new Font("Dialog", Font.BOLD, 18));
		btncatagory.setBounds(10, 280, 168, 39);
		Menu.add(btncatagory);
		
		btnAuthor = new JButton("AUTHOR");
		btnAuthor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAuthor.setForeground(Color.WHITE);
		btnAuthor.setFont(new Font("Dialog", Font.BOLD, 18));
		btnAuthor.setBackground(new Color(51, 153, 255));
		btnAuthor.setBounds(181, 280, 168, 39);
		Menu.add(btnAuthor);
		
		btnPublisher = new JButton("PUBLISHER");
		btnPublisher.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPublisher.setForeground(Color.WHITE);
		btnPublisher.setFont(new Font("Dialog", Font.BOLD, 18));
		btnPublisher.setBackground(new Color(51, 153, 255));
		btnPublisher.setBounds(10, 330, 168, 39);
		Menu.add(btnPublisher);
		
		btnBook = new JButton("BOOK ENTRY");
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(AddingBook);
			}
		});
		btnBook.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBook.setForeground(Color.WHITE);
		btnBook.setFont(new Font("Dialog", Font.BOLD, 18));
		btnBook.setBackground(new Color(51, 153, 255));
		btnBook.setBounds(181, 330, 168, 39);
		Menu.add(btnBook);
		
		btnMember = new JButton("MEMBER");
		btnMember.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMember.setForeground(Color.WHITE);
		btnMember.setFont(new Font("Dialog", Font.BOLD, 18));
		btnMember.setBackground(new Color(51, 153, 255));
		btnMember.setBounds(10, 380, 168, 39);
		Menu.add(btnMember);
		
		btnIssueBook = new JButton("ISSUE BOOK");
		btnIssueBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(IssueBook);
				btnIssue.setEnabled(true);
			}
		});
		btnIssueBook.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnIssueBook.setForeground(Color.WHITE);
		btnIssueBook.setFont(new Font("Dialog", Font.BOLD, 18));
		btnIssueBook.setBackground(new Color(51, 153, 255));
		btnIssueBook.setBounds(181, 380, 168, 39);
		Menu.add(btnIssueBook);
		
		btnReturnBook = new JButton("RETURN BOOK");
		btnReturnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				issueLoad();
				switchPanels(ReturnBook);
			}
		});
		btnReturnBook.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReturnBook.setForeground(Color.WHITE);
		btnReturnBook.setFont(new Font("Dialog", Font.BOLD, 18));
		btnReturnBook.setBackground(new Color(51, 153, 255));
		btnReturnBook.setBounds(10, 430, 168, 39);
		Menu.add(btnReturnBook);
		
		btnlogout = new JButton("LOGOUT");
		btnlogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				com.mkw.loginpanel.Login lg = new com.mkw.loginpanel.Login();
				lg.setVisible(true);
			}
		});
		btnlogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnlogout.setForeground(Color.WHITE);
		btnlogout.setFont(new Font("Dialog", Font.BOLD, 18));
		btnlogout.setBackground(new Color(51, 153, 255));
		btnlogout.setBounds(181, 430, 168, 39);
		Menu.add(btnlogout);
		
		lblimg = new JLabel("");
		lblimg.setBounds(0, 0, 356, 598);
		ImageIcon bcg = new ImageIcon(this.getClass().getResource("/book9.jpg"));
		Image img1 = bcg.getImage().getScaledInstance(lblimg.getWidth(), lblimg.getHeight(), Image.SCALE_SMOOTH);
		bcg = new ImageIcon(img1);
		lblimg.setIcon(bcg);
		Menu.add(lblimg);
		
		admininfo = new JPanel();
		admininfo.setBorder(new MatteBorder(0, 4, 0, 0, (Color) new Color(0, 0, 153)));
		admininfo.setBackground(new Color(0, 0, 51));
		admininfo.setBounds(355, 0, 545, 598);
		PanelAdmin.add(admininfo);
		admininfo.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("WELCOME TO ADMIN PANEL");
		lblNewLabel_5.setFont(new Font("Dialog", Font.BOLD, 30));
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(31, 18, 485, 66);
		admininfo.add(lblNewLabel_5);
		
		panelpic = new JPanel();
		panelpic.setBounds(0, 88, 545, 510);
		panelpic.setBorder(new MatteBorder(0, 4, 0, 0, (Color) new Color(0, 0, 153)));
		panelpic.setBackground(new Color(0, 0, 51));
		admininfo.add(panelpic);
		panelpic.setLayout(null);
		
		slideshow = new JLabel("");
		slideshow.setBorder(null);
		slideshow.setBounds(6, 0, 539, 510);
		ImageIcon mainImage = new ImageIcon(this.getClass().getResource("/Book28.jpg"));
		Image img10 = mainImage.getImage().getScaledInstance(slideshow.getWidth(), slideshow.getHeight(), Image.SCALE_SMOOTH);
		mainImage = new ImageIcon(img10);
		slideshow.setIcon(mainImage);
		panelpic.add(slideshow);
		
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
		Exit.setBounds(512, 8, 24, 22);
		ImageIcon exit = new ImageIcon(this.getClass().getResource("/exit1.png"));
		Image img = exit.getImage().getScaledInstance(Exit.getWidth(), Exit.getHeight(), Image.SCALE_SMOOTH);
		exit = new ImageIcon(img);
		Exit.setIcon(exit);
		admininfo.add(Exit);
		
		titleImage = new JLabel("");
		titleImage.setBounds(6, 0, 539, 83);
		ImageIcon timg = new ImageIcon(this.getClass().getResource("/best59.jpg"));
		Image tim = timg.getImage().getScaledInstance(titleImage.getWidth(), titleImage.getHeight(), Image.SCALE_SMOOTH);
		timg = new ImageIcon(tim);
		titleImage.setIcon(timg);
		admininfo.add(titleImage);
		
		/*__________________________Catagory_________________________*/
		Catagory = new JPanel();
		Catagory.setBackground(new Color(0, 0, 51));
		layeredPane.add(Catagory, "name_28849566581017");
		Catagory.setLayout(null);
		
		panelsearch = new JPanel();
		panelsearch.setBorder(new LineBorder(new Color(51, 153, 255), 2));
		panelsearch.setBackground(Color.WHITE);
		panelsearch.setBounds(310, 120, 280, 30);
		Catagory.add(panelsearch);
		panelsearch.setLayout(null);
		
		serialtext = new JTextField();
		serialtext.setOpaque(false);
		serialtext.setBounds(7, 0, 228, 30);
		serialtext.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(51, 153, 255)));
		serialtext.setForeground(Color.BLACK);
		serialtext.setFont(new Font("NikoshGrameem", Font.PLAIN, 18));
		panelsearch.add(serialtext);
		serialtext.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					String ser = serialtext.getText();
					list.setVisible(true);
					listscrol.setVisible(true);
					pst = con.prepareStatement("select * from catagory where Category like ?");
					pst.setString(1, ser + '%');
					ResultSet rs = pst.executeQuery();

					@SuppressWarnings("rawtypes")
					DefaultListModel dlm = new DefaultListModel();

					while (rs.next()) {
						dlm.addElement(rs.getString("Category"));
					}
					list.setModel(dlm);

					if (ser.equals("")) {
						list.setVisible(false);
						listscrol.setVisible(false);
					}
			}
			catch(SQLException ex) {
				
				}
			}
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(arg0.getKeyChar()!=KeyEvent.VK_BACK_SPACE && arg0.getKeyChar()!=KeyEvent.VK_DELETE && arg0.getKeyChar()!=KeyEvent.VK_ENTER) {
					txtSearchByCatagory.setVisible(false);
					txtSearchByCatagory.setEnabled(false);
			    }
			    else if(serialtext.getText().equals("")) {
			    	txtSearchByCatagory.setVisible(true);
			    	txtSearchByCatagory.setEnabled(false);
			    }
			}
		});
		serialtext.setColumns(10);
		
		txtSearchByCatagory = new JTextField();
		txtSearchByCatagory.setBounds(9, 0, 200, 30);
		panelsearch.add(txtSearchByCatagory);
		txtSearchByCatagory.setBackground(Color.WHITE);
		txtSearchByCatagory.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(51, 153, 255)));
		txtSearchByCatagory.setEditable(false);
		txtSearchByCatagory.setEnabled(false);
		txtSearchByCatagory.setText("Search By Category");
		txtSearchByCatagory.setForeground(new Color(51, 51, 51));
		txtSearchByCatagory.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtSearchByCatagory.setColumns(10);
		
		catagorySearchImage = new JLabel("");
		catagorySearchImage.setBounds(244, 4, 28, 23);
		ImageIcon catsrch = new ImageIcon(this.getClass().getResource("/search8.jpg"));
		Image image = catsrch.getImage().getScaledInstance(catagorySearchImage.getWidth(), catagorySearchImage.getHeight(), Image.SCALE_SMOOTH);
		catsrch = new ImageIcon(image);
		catagorySearchImage.setIcon(catsrch);
		panelsearch.add(catagorySearchImage);
		
		listscrol = new JScrollPane();
		listscrol.setBackground(Color.WHITE);
		listscrol.setVisible(false);
		listscrol.setBounds(310, 151, 280, 145);
		Catagory.add(listscrol);
		
		list = new JList();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				try 
		        {
				 	Object ans = list.getSelectedValue();
				 	list.setVisible(false);
				 	listscrol.setVisible(false);
					
		            pst=con.prepareStatement("select * from catagory where Category = ?");
		            pst.setString(1, (String.valueOf(ans)));
		            ResultSet rs = pst.executeQuery();
		            
		            if(rs.next()) {
		            	String Category = rs.getString(2);
		            	String Status = rs.getString(3);
		            	
		            	catPlaceHolder.setVisible(false);
		            	catname.setText(Category);
		            	statusbox.setSelectedItem(Status);
		            }
		        } 
		        catch (SQLException ex) 
		        {
		        	ex.printStackTrace();
		        }
			}
		});
		list.setBackground(Color.WHITE);
		list.setVisible(false);
		list.setForeground(Color.BLACK);
		list.setFont(new Font("SolaimanLipi", Font.PLAIN, 19));
		listscrol.setViewportView(list);
		
		Exit1 = new JButton("");
		Exit1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				com.mkw.joptionpanes.Confirmation w = new com.mkw.joptionpanes.Confirmation();
				w.setVisible(true);
			}
		});
		Exit1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Exit1.setOpaque(false);
		Exit1.setBackground(Color.RED);
		Exit1.setBounds(866, 11, 24, 22);
		ImageIcon exit1 = new ImageIcon(this.getClass().getResource("/exit1.png"));
		Image e1 = exit1.getImage().getScaledInstance(Exit1.getWidth(), Exit1.getHeight(), Image.SCALE_SMOOTH);
		exit1 = new ImageIcon(e1);
		Exit1.setIcon(exit1);
		Catagory.add(Exit1);
		
		lbltitle = new JLabel("C A T E G O R Y   A D D");
		lbltitle.setFont(new Font("Dialog", Font.BOLD, 36));
		lbltitle.setForeground(Color.CYAN);
		lbltitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbltitle.setBounds(10, 11, 880, 88);
		Catagory.add(lbltitle);
		
		catname = new JTextField();
		catname.setCaretColor(Color.WHITE);
		catname.setFont(new Font("NikoshGrameem", Font.PLAIN, 18));
		catname.setForeground(Color.WHITE);
		catname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(arg0.getKeyChar()!=KeyEvent.VK_BACK_SPACE && arg0.getKeyChar()!=KeyEvent.VK_DELETE && arg0.getKeyChar()!=KeyEvent.VK_ENTER) {
					catPlaceHolder.setVisible(false);
					catPlaceHolder.setEnabled(false);
			    }
			    else if(catname.getText().equals("")) {
			    	catPlaceHolder.setVisible(true);
			    	catPlaceHolder.setEnabled(false);
			    }
			}
		});
		catname.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(220, 20, 60)));
		catname.setOpaque(false);
		catname.setBackground(Color.WHITE);
		catname.setBounds(122, 254, 240, 30);
		Catagory.add(catname);
		catname.setColumns(10);
		
		catPlaceHolder = new JTextField();
		catPlaceHolder.setDisabledTextColor(Color.DARK_GRAY);
		catPlaceHolder.setBorder(null);
		catPlaceHolder.setOpaque(false);
		catPlaceHolder.setFont(new Font("Tahoma", Font.PLAIN, 15));
		catPlaceHolder.setText("Enter Category Name");
		catPlaceHolder.setEnabled(false);
		catPlaceHolder.setEditable(false);
		catPlaceHolder.setBounds(124, 254, 230, 30);
		Catagory.add(catPlaceHolder);
		catPlaceHolder.setColumns(10);
		
		statusbox = new JComboBox();
		statusbox.setBackground(Color.WHITE);
		statusbox.setModel(new DefaultComboBoxModel(new String[] {"Choose Status", "Available", "Unavailable"}));
		statusbox.setForeground(Color.BLACK);
		statusbox.setFont(new Font("NikoshGrameem", Font.PLAIN, 18));
		statusbox.setBounds(122, 349, 240, 30);
		Catagory.add(statusbox);
		
		btnAdd = new JButton("Add");
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name,status;
				
				name = catname.getText();
				status = statusbox.getSelectedItem().toString();
				
				if(name.equals("") || status.equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill up all the field");
				}
				
				else {
					try {
						pst = con.prepareStatement("insert into catagory(Category,Status)values(?,?)");
						pst.setString(1, name);
						pst.setString(2, status);

						pst.executeUpdate();

						JOptionPane.showMessageDialog(null, "Category Added Successfully!");
						categoryTableload();

						catPlaceHolder.setVisible(true);
						catname.setText("");
						statusbox.setSelectedItem("");
						catname.requestFocus();
					}
					catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnAdd.setForeground(Color.BLACK);
		btnAdd.setBackground(new Color(51, 51, 204));
		btnAdd.setFont(new Font("Dialog", Font.BOLD, 18));
		btnAdd.setBounds(122, 430, 115, 37);
		Catagory.add(btnAdd);
		
		btnUpdate = new JButton("Edit");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name,status,sr;
				Object ans = list.getSelectedValue();
				name = catname.getText();
				status = statusbox.getSelectedItem().toString();
				try {
					pst = con.prepareStatement("update catagory set Category = ?, Status = ? where Category = ?");
					pst.setString(1, name);
					pst.setString(2, status);
					pst.setString(3, (String.valueOf(ans)));
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Updated Successfully!");
					categoryTableload();
					
					catPlaceHolder.setVisible(true);
					catname.setText("");
					statusbox.setSelectedItem("");
					catname.requestFocus();
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setForeground(Color.BLACK);
		btnUpdate.setFont(new Font("Dialog", Font.BOLD, 18));
		btnUpdate.setBackground(new Color(51, 51, 204));
		btnUpdate.setBounds(247, 430, 115, 37);
		Catagory.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Object ans = list.getSelectedValue();
					pst = con.prepareStatement("delete from catagory where Category = ? ");
					pst.setString(1, (String.valueOf(ans)));
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Deleted Successfully!");
					categoryTableload();
					
					catPlaceHolder.setVisible(true);
					catname.setText("");
					statusbox.setSelectedItem("");
					
					catname.requestFocus();
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setForeground(Color.BLACK);
		btnDelete.setFont(new Font("Dialog", Font.BOLD, 18));
		btnDelete.setBackground(new Color(51, 51, 204));
		btnDelete.setBounds(122, 478, 115, 37);
		Catagory.add(btnDelete);
		
		btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(PanelAdmin);
			}
		});
		btnHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHome.setForeground(Color.BLACK);
		btnHome.setFont(new Font("Dialog", Font.BOLD, 18));
		btnHome.setBackground(new Color(51, 51, 204));
		btnHome.setBounds(247, 478, 115, 37);
		Catagory.add(btnHome);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(453, 254, 340, 261);
		Catagory.add(scrollPane);
		
		catagorytable = new JTable();
		catagorytable.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(catagorytable);
		
		catImg = new JLabel("");
		catImg.setBounds(0, 0, 900, 600);
		ImageIcon catimage = new ImageIcon(this.getClass().getResource("/best47.jpg"));
		Image c1 = catimage.getImage().getScaledInstance(catImg.getWidth(), catImg.getHeight(), Image.SCALE_SMOOTH);
		catimage = new ImageIcon(c1);
		catImg.setIcon(catimage);
		Catagory.add(catImg);
		
		/*__________________________AddingBook_________________________*/
		AddingBook = new JPanel();
		AddingBook.setBackground(new Color(0, 0, 51));
		layeredPane.add(AddingBook, "name_28852393619999");
		AddingBook.setLayout(null);
		
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
		AddingBook.add(Exit2);
		
		addingTitle = new JLabel("B O O K     E N T R Y");
		addingTitle.setFont(new Font("Dialog", Font.BOLD, 36));
		addingTitle.setHorizontalAlignment(SwingConstants.CENTER);
		addingTitle.setForeground(Color.WHITE);
		addingTitle.setBounds(10, 11, 880, 53);
		AddingBook.add(addingTitle);
		
		booksearch = new JPanel();
		booksearch.setBackground(Color.WHITE);
		booksearch.setBorder(new LineBorder(new Color(51, 255, 255), 2));
		booksearch.setBounds(232, 75, 426, 32);
		AddingBook.add(booksearch);
		booksearch.setLayout(null);
		
		textSearch = new JTextField();
		textSearch.setBounds(8, 0, 367, 32);
		textSearch.setBackground(Color.WHITE);
		textSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					String search = textSearch.getText();
					bookList.setVisible(true);
					bookScroll.setVisible(true);
					pst = con.prepareStatement("select * from entry where Book like ?");
					pst.setString(1, search + '%');
					ResultSet rs = pst.executeQuery();
					@SuppressWarnings("rawtypes")
					DefaultListModel showData = new DefaultListModel();
					
					while(rs.next()) {
						showData.addElement(rs.getString("Book"));
					}
					/*This is set out of the loop.Because, if set inside the loop
					 * it only check the first character from database of items.
					 * So, if set outside it always check every character from items
					 * from database*/
					bookList.setModel(showData);
					
					if(search.equals("")) {
						bookList.setVisible(false);
						bookScroll.setVisible(false);
					}
				}
				catch(SQLException ex) {
					
					}
			}
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(arg0.getKeyChar()!=KeyEvent.VK_BACK_SPACE && arg0.getKeyChar()!=KeyEvent.VK_DELETE && arg0.getKeyChar()!=KeyEvent.VK_ENTER) {
					txtSearchBookBy.setVisible(false);
					txtSearchBookBy.setEnabled(false);
			    }
			    else if(textSearch.getText().equals("")) {
			    	txtSearchBookBy.setVisible(true);
			    	txtSearchBookBy.setEnabled(false);
			    }
			}
		});
		textSearch.setOpaque(false);
		textSearch.setForeground(Color.BLACK);
		textSearch.setFont(new Font("NikoshGrameem", Font.PLAIN, 19));
		textSearch.setBorder(new MatteBorder(2, 0, 2, 0, (Color) Color.CYAN));
		booksearch.add(textSearch);
		textSearch.setColumns(10);
		
		txtSearchBookBy = new JTextField();
		txtSearchBookBy.setBackground(Color.WHITE);
		txtSearchBookBy.setEnabled(false);
		txtSearchBookBy.setEditable(false);
		txtSearchBookBy.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtSearchBookBy.setForeground(Color.DARK_GRAY);
		txtSearchBookBy.setText("Search Book By Name");
		txtSearchBookBy.setBorder(null);
		txtSearchBookBy.setBounds(11, 0, 349, 32);
		booksearch.add(txtSearchBookBy);
		txtSearchBookBy.setColumns(10);
		
		SearchLabel = new JLabel("");
		SearchLabel.setBounds(389, 3, 30, 26);
		ImageIcon bsrch = new ImageIcon(this.getClass().getResource("/search8.jpg"));
		Image image1 = bsrch.getImage().getScaledInstance(SearchLabel.getWidth(), SearchLabel.getHeight(), Image.SCALE_SMOOTH);
		bsrch = new ImageIcon(image1);
		SearchLabel.setIcon(bsrch);
		booksearch.add(SearchLabel);
		
		bookScroll = new JScrollPane();
		bookScroll.setBackground(Color.DARK_GRAY);
		bookScroll.setBorder(new MatteBorder(0, 2, 2, 2, (Color) new Color(51, 255, 255)));
		bookScroll.setVisible(false);
		bookScroll.setBounds(232, 107, 426, 203);
		AddingBook.add(bookScroll);
		
		bookList = new JList();
		bookList.setFont(new Font("SolaimanLipi", Font.PLAIN, 20));
		bookList.setForeground(Color.BLACK);
		bookList.setVisible(false);
		bookList.setBackground(new Color(173, 216, 230));
		bookScroll.setViewportView(bookList);
		
		txtbname = new JTextField();
		txtbname.setCaretColor(Color.WHITE);
		txtbname.setOpaque(false);
		txtbname.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(51, 153, 255)));
		txtbname.setForeground(Color.WHITE);
		txtbname.setFont(new Font("NikoshGrameem", Font.PLAIN, 18));
		txtbname.setBounds(10, 145, 255, 31);
		AddingBook.add(txtbname);
		txtbname.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Book Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 177, 105, 20);
		AddingBook.add(lblNewLabel);
		
		txtauthor = new JTextField();
		txtauthor.setCaretColor(Color.WHITE);
		txtauthor.setOpaque(false);
		txtauthor.setForeground(Color.WHITE);
		txtauthor.setFont(new Font("NikoshGrameem", Font.PLAIN, 18));
		txtauthor.setColumns(10);
		txtauthor.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(51, 153, 255)));
		txtauthor.setBounds(10, 222, 255, 31);
		AddingBook.add(txtauthor);
		
		lblAuthorName = new JLabel("Author Name");
		lblAuthorName.setForeground(Color.WHITE);
		lblAuthorName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAuthorName.setBounds(10, 254, 105, 20);
		AddingBook.add(lblAuthorName);
		
		txtpublisher = new JTextField();
		txtpublisher.setCaretColor(Color.WHITE);
		txtpublisher.setOpaque(false);
		txtpublisher.setForeground(Color.WHITE);
		txtpublisher.setFont(new Font("NikoshGrameem", Font.PLAIN, 18));
		txtpublisher.setColumns(10);
		txtpublisher.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(51, 153, 255)));
		txtpublisher.setBounds(10, 299, 255, 31);
		AddingBook.add(txtpublisher);
		
		lblPublisherName = new JLabel("Publisher Name");
		lblPublisherName.setForeground(Color.WHITE);
		lblPublisherName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPublisherName.setBounds(10, 331, 105, 20);
		AddingBook.add(lblPublisherName);
		
		lblTotalCopies = new JLabel("Total Copies");
		lblTotalCopies.setForeground(Color.WHITE);
		lblTotalCopies.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalCopies.setBounds(10, 408, 87, 20);
		AddingBook.add(lblTotalCopies);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setForeground(Color.WHITE);
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCategory.setBounds(10, 480, 80, 20);
		AddingBook.add(lblCategory);
		
		entryDate = new JDateChooser();
		entryDate.getCalendarButton().setFont(new Font("NikoshGrameem", Font.PLAIN, 16));
		entryDate.setFont(new Font("NikoshGrameem", Font.PLAIN, 15));
		entryDate.setForeground(Color.BLACK);
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		entryDate.setBounds(114, 381, 151, 26);
		AddingBook.add(entryDate);
		
		lblEntryDate = new JLabel("Entry Date");
		lblEntryDate.setForeground(Color.WHITE);
		lblEntryDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEntryDate.setBounds(114, 408, 120, 20);
		AddingBook.add(lblEntryDate);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBackground(Color.WHITE);
		scrollPane_1.setBounds(309, 145, 581, 309);
		AddingBook.add(scrollPane_1);
		
		tableissue = new JTable();
		tableissue.setEnabled(false);
		tableissue.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(tableissue);
		
		bookEntry = new JButton("ENTRY");
		bookEntry.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bookEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String bname,bcat,baut,bpub,btotal,entry;
				
				bname = txtbname.getText();
				bcat = itembox.getSelectedItem().toString();
				baut = txtauthor.getText();
				bpub = txtpublisher.getText();
				btotal = txtcopies.getValue().toString();
	            entry = dateformat.format(entryDate.getDate()); 
				
	            if(bname.equals("")||bcat.equals("")||baut.equals("")||bpub.equals("")
	            		||btotal.equals("")) {
	            	JOptionPane.showMessageDialog(null, "Please Fill Up all the field");
	            }
	            
				else {
					try {
						pst = con.prepareStatement("insert into entry(Book,Category,Author,Publisher,Total,Date)values(?,?,?,?,?,?)");
						pst.setString(1, bname);
						pst.setString(2, bcat);
						pst.setString(3, baut);
						pst.setString(4, bpub);
						pst.setInt(5, Integer.parseInt(btotal));
						pst.setString(6, entry);
						pst.executeUpdate();

						JOptionPane.showMessageDialog(null, "Book Entry Successfully!");
						tableBookEntry();
						getsum();

						txtbname.setText("");
						itembox.setSelectedItem("");
						txtauthor.setText("");
						txtpublisher.setText("");
						txtcopies.setValue(0);
						itembox.setSelectedItem("");

						txtbname.requestFocus();
					}
					catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		bookEntry.setBackground(new Color(51, 51, 204));
		bookEntry.setForeground(Color.WHITE);
		bookEntry.setFont(new Font("Dialog", Font.BOLD, 21));
		bookEntry.setBounds(102, 535, 135, 42);
		AddingBook.add(bookEntry);
		
		bookReset = new JButton("RESET");
		bookReset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bookReset.setForeground(Color.WHITE);
		bookReset.setFont(new Font("Dialog", Font.BOLD, 21));
		bookReset.setBackground(new Color(51, 51, 204));
		bookReset.setBounds(247, 535, 135, 42);
		AddingBook.add(bookReset);
		
		bookUpdate = new JButton("UPDATE");
		bookUpdate.setToolTipText("Edit Entered Data");
		bookUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bookUpdate.setForeground(Color.WHITE);
		bookUpdate.setFont(new Font("Dialog", Font.BOLD, 21));
		bookUpdate.setBackground(new Color(51, 51, 204));
		bookUpdate.setBounds(392, 535, 135, 42);
		AddingBook.add(bookUpdate);
		
		bookDelete = new JButton("DELETE");
		bookDelete.setToolTipText("Delete A Book From Data");
		bookDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bookDelete.setForeground(Color.WHITE);
		bookDelete.setFont(new Font("Dialog", Font.BOLD, 21));
		bookDelete.setBackground(new Color(51, 51, 204));
		bookDelete.setBounds(537, 535, 135, 42);
		AddingBook.add(bookDelete);
		
		bookHome = new JButton("HOME");
		bookHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(PanelAdmin);
			}
		});
		bookHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bookHome.setForeground(Color.WHITE);
		bookHome.setFont(new Font("Dialog", Font.BOLD, 21));
		bookHome.setBackground(new Color(51, 51, 204));
		bookHome.setBounds(682, 535, 135, 42);
		AddingBook.add(bookHome);
		
		lblNewLabel_1 = new JLabel("Total Books");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 21));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(377, 456, 135, 46);
		AddingBook.add(lblNewLabel_1);
		
		lblBookCount = new JLabel("");
		lblBookCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookCount.setForeground(Color.WHITE);
		lblBookCount.setFont(new Font("Kalpurush", Font.BOLD, 40));
		lblBookCount.setBounds(527, 456, 202, 46);
		AddingBook.add(lblBookCount);
		
		JLabel lblNewLabel_1_1 = new JLabel("Pices");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 21));
		lblNewLabel_1_1.setBounds(745, 456, 72, 46);
		AddingBook.add(lblNewLabel_1_1);
		
		txtcopies = new JSpinner();
		txtcopies.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(5)));
		txtcopies.setBackground(Color.WHITE);
		txtcopies.setForeground(Color.BLACK);
		txtcopies.setFont(new Font("NikoshGrameem", Font.PLAIN, 18));
		txtcopies.setBounds(10, 381, 87, 26);
		AddingBook.add(txtcopies);
		
		itembox = new JComboBox();
		itembox.setForeground(Color.BLACK);
		itembox.setFont(new Font("NikoshGrameem", Font.PLAIN, 18));
		itembox.setBackground(Color.WHITE);
		itembox.setBounds(10, 453, 255, 26);
		AddingBook.add(itembox);
		
		bookAddImage = new JLabel("");
		bookAddImage.setBounds(0, 0, 900, 600);
		ImageIcon bimg = new ImageIcon(this.getClass().getResource("/best47.jpg"));
		Image bi = bimg.getImage().getScaledInstance(bookAddImage.getWidth(), bookAddImage.getHeight(), Image.SCALE_SMOOTH);
		bimg = new ImageIcon(bi);
		bookAddImage.setIcon(bimg);
		AddingBook.add(bookAddImage);
		
		/*__________________________IssueBook_________________________*/
		IssueBook = new JPanel();
		IssueBook.setBackground(new Color(0, 0, 51));
		layeredPane.add(IssueBook, "name_28914773860364");
		IssueBook.setLayout(null);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 110, 880, 383);
		IssueBook.add(scrollPane_2);
		
		bookRecord = new JTable();
		bookRecord.setEnabled(false);
		scrollPane_2.setViewportView(bookRecord);
		
		btnIssue = new JButton("ISSUE");
		btnIssue.setEnabled(false);
		btnIssue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DateTimeFormatter dfd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDateTime now = LocalDateTime.now();
				LocalDateTime returnvalue = now.plusDays(30);

				try {
					pst = con.prepareStatement("update issuebook set IssueDate = ?, LastDate = ? where IssueDate is null and LastDate is null");
					pst.setString(1, dfd.format(now));
					pst.setString(2, dfd.format(returnvalue));
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Book Issued Successfully!");
					issueLoad();
					btnIssue.setEnabled(false);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnIssue.setBackground(new Color(51, 51, 204));
		btnIssue.setForeground(Color.BLACK);
		btnIssue.setFont(new Font("Dialog", Font.BOLD, 18));
		btnIssue.setBounds(287, 524, 144, 34);
		IssueBook.add(btnIssue);
		
		btnHome_1 = new JButton("HOME");
		btnHome_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(PanelAdmin);
			}
		});
		btnHome_1.setForeground(Color.BLACK);
		btnHome_1.setFont(new Font("Dialog", Font.BOLD, 18));
		btnHome_1.setBackground(new Color(51, 51, 204));
		btnHome_1.setBounds(482, 524, 144, 34);
		IssueBook.add(btnHome_1);
		
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
		IssueBook.add(Exit3);
		
		lblNewLabel_2 = new JLabel("B O O K    I S S U E");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 40));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 11, 880, 89);
		IssueBook.add(lblNewLabel_2);
		
		issueImage = new JLabel("");
		issueImage.setBounds(0, 0, 900, 600);
		ImageIcon issueimage = new ImageIcon(this.getClass().getResource("/best47.jpg"));
		Image isimg = issueimage.getImage().getScaledInstance(issueImage.getWidth(), issueImage.getHeight(), Image.SCALE_SMOOTH);
		issueimage = new ImageIcon(isimg);
		issueImage.setIcon(issueimage);
		IssueBook.add(issueImage);
		
		ReturnBook = new JPanel();
		ReturnBook.setBackground(new Color(0, 0, 51));
		layeredPane.add(ReturnBook, "name_5795608937728");
		ReturnBook.setLayout(null);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 184, 880, 303);
		ReturnBook.add(scrollPane_3);
		
		returnTable = new JTable();
		returnTable.setEnabled(false);
		scrollPane_3.setViewportView(returnTable);
		
		idInput = new JTextField();
		idInput.setHorizontalAlignment(SwingConstants.CENTER);
		idInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					String Id = idInput.getText();
					pst = con.prepareStatement("select * from issuebook where Issue_ID = ?");
					pst.setString(1, Id);
					ResultSet rs = pst.executeQuery();
					
					returnTable.setModel(DbUtils.resultSetToTableModel(rs));
					returnTable.setShowHorizontalLines(true);
					returnTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					
					TableColumn column = null;
					for (int i = 0; i < 8; i++) {
					    column = returnTable.getColumnModel().getColumn(i);
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
					returnTable.setFont(new Font("SolaimanLipi", Font.PLAIN, 20));
					returnTable.setRowHeight(30);
					
					if(Id.equals("")) {
						returnTableLoad();
					}
				}
				catch(SQLException ex) {
					
				}
				
			}
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if(!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
					getToolkit().beep();
					arg0.consume();
				}
			}
		});
		idInput.setForeground(Color.BLACK);
		idInput.setFont(new Font("NikoshGrameem", Font.BOLD, 18));
		idInput.setBounds(221, 146, 208, 27);
		ReturnBook.add(idInput);
		idInput.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Enter Issue ID");
		lblNewLabel_4.setFont(new Font("NikoshGrameem", Font.BOLD, 14));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(221, 118, 208, 27);
		ReturnBook.add(lblNewLabel_4);
		
		returnDate = new JDateChooser();
		SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM-dd");
		returnDate.setFont(new Font("NikoshGrameem", Font.BOLD, 18));
		returnDate.setForeground(Color.BLACK);
		returnDate.setBounds(470, 146, 208, 27);
		ReturnBook.add(returnDate);
		
		JLabel lblNewLabel_4_1 = new JLabel("Enter Return Date");
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setForeground(Color.WHITE);
		lblNewLabel_4_1.setFont(new Font("NikoshGrameem", Font.BOLD, 14));
		lblNewLabel_4_1.setBounds(470, 118, 208, 27);
		ReturnBook.add(lblNewLabel_4_1);
		
		btnReturn = new JButton("RETURN");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idInput.getText();
				String r_Date = dateformat1.format(returnDate.getDate());

				try {
					pst = con.prepareStatement("update issuebook set ReturnDate = ? where Issue_ID = ? and ReturnDate is null");
					pst.setString(1, r_Date);
					pst.setString(2, id);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Book Returned Successfully!");
					returnTableLoad();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnReturn.setBackground(new Color(51, 51, 204));
		btnReturn.setForeground(Color.BLACK);
		btnReturn.setFont(new Font("Dialog", Font.BOLD, 18));
		btnReturn.setBounds(265, 521, 144, 34);
		ReturnBook.add(btnReturn);
		
		btnHome_2 = new JButton("HOME");
		btnHome_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(PanelAdmin);
			}
		});
		btnHome_2.setForeground(Color.BLACK);
		btnHome_2.setFont(new Font("Dialog", Font.BOLD, 18));
		btnHome_2.setBackground(new Color(51, 51, 204));
		btnHome_2.setBounds(479, 521, 144, 34);
		ReturnBook.add(btnHome_2);
		
		Exit4 = new JButton("");
		Exit4.setBounds(302, 11, 89, 23);
		Exit4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				com.mkw.joptionpanes.Confirmation w = new com.mkw.joptionpanes.Confirmation();
				w.setVisible(true);
			}
		});
		Exit4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Exit4.setOpaque(false);
		Exit4.setBackground(Color.RED);
		Exit4.setBounds(866, 11, 24, 22);
		ImageIcon exit4 = new ImageIcon(this.getClass().getResource("/exit1.png"));
		Image e4 = exit4.getImage().getScaledInstance(Exit4.getWidth(), Exit4.getHeight(), Image.SCALE_SMOOTH);
		exit4 = new ImageIcon(e4);
		Exit4.setIcon(exit4);
		ReturnBook.add(Exit4);
		
		lblNewLabel_3 = new JLabel("R E T U R N    B O O K");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 40));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 11, 880, 88);
		ReturnBook.add(lblNewLabel_3);
		
		returnImage = new JLabel("");
		returnImage.setBounds(0, 0, 900, 600);
		ImageIcon r_img = new ImageIcon(this.getClass().getResource("/best47.jpg"));
		Image r = r_img.getImage().getScaledInstance(returnImage.getWidth(), returnImage.getHeight(), Image.SCALE_SMOOTH);
		r_img = new ImageIcon(r);
		returnImage.setIcon(r_img);
		ReturnBook.add(returnImage);
	}
}
