package com.mkw.bookrequest;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.mkw.loginpanel.Login;
import com.mkw.loginpanel.Start;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Event;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookRequest extends JFrame {

	private JPanel contentPane;
	private JButton Exit;
	private JPanel booksearch;
	private JTextField textSearch;
	private JTextField txtSearchBookBy;
	private JLabel SearchLabel;
	private JList categoryList;
	private JScrollPane categoryScroll;
	private JTable bookTable;
	private JScrollPane scrollPane;
	private JButton btnRequest;
	private JButton btnBack;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookRequest frame = new BookRequest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public BookRequest() {
		setLookAndFeel();
		initialize();
		connect();
		bookLoad();
		changeTeableHeaderFont();
	}
	
	Connection con;
	PreparedStatement pst,pst2;
	ResultSet rs,rs2;
	String bname,bcat,bauth,bpub;
	private JLabel bookImage;
	
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
	
	public void changeTeableHeaderFont() {
		UIManager.put("TableHeader.font", new Font("NikoshGrameem", Font.BOLD, 18));
	}
	
	public void bookLoad() {
		try{	
			pst = con.prepareStatement("select Book,Author,Publisher from entry");
			rs = pst.executeQuery();
			bookTable.setModel(DbUtils.resultSetToTableModel(rs));
			
			bookTable.setShowHorizontalLines(true);
			bookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			TableColumn column1 = null;
			for (int i = 0; i < 3; i++) {
			    column1 = bookTable.getColumnModel().getColumn(i);
			    if(i==0) {
			    	column1.setPreferredWidth(340);
			    }
			    if (i == 1) {
			        column1.setPreferredWidth(240);
			    }
			    else if(i==2) {
			    	column1.setPreferredWidth(340);
			    }
			}
			bookTable.setFont(new Font("SolaimanLipi", Font.PLAIN, 20));
			bookTable.setRowHeight(30);
			
        } catch(SQLException ex){
        	ex.printStackTrace();
        }
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
		contentPane.setLayout(null);
		
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
		Image e1 = exit.getImage().getScaledInstance(Exit.getWidth(), Exit.getHeight(), Image.SCALE_SMOOTH);
		exit = new ImageIcon(e1);
		Exit.setIcon(exit);
		contentPane.add(Exit);
		
		JLabel lblNewLabel = new JLabel("REQUEST   FOR   BOOK");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 880, 55);
		contentPane.add(lblNewLabel);
		
		booksearch = new JPanel();
		booksearch.setBackground(Color.WHITE);
		booksearch.setBorder(new LineBorder(new Color(51, 255, 255), 2));
		booksearch.setBounds(237, 86, 426, 32);
		contentPane.add(booksearch);
		booksearch.setLayout(null);
		
		textSearch = new JTextField();
		textSearch.setBounds(8, 0, 367, 32);
		textSearch.setBackground(Color.WHITE);
		textSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					String search = textSearch.getText();
					categoryList.setVisible(true);
					categoryScroll.setVisible(true);
					pst = con.prepareStatement("select Category from catagory where Category like ?");
					pst.setString(1, search + '%');
					ResultSet rs = pst.executeQuery();
					@SuppressWarnings("rawtypes")
					DefaultListModel showData = new DefaultListModel();
					
					while(rs.next()) {
						showData.addElement(rs.getString("Category"));
					}
					categoryList.setModel(showData);
					
					if(search.equals("")) {
						categoryList.setVisible(false);
						categoryScroll.setVisible(false);
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
		textSearch.setColumns(10);
		textSearch.setBorder(new MatteBorder(2, 0, 2, 0, (Color) Color.CYAN));
		booksearch.add(textSearch);
		textSearch.setColumns(10);
		
		txtSearchBookBy = new JTextField();
		txtSearchBookBy.setBackground(Color.WHITE);
		txtSearchBookBy.setEnabled(false);
		txtSearchBookBy.setEditable(false);
		txtSearchBookBy.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtSearchBookBy.setForeground(Color.DARK_GRAY);
		txtSearchBookBy.setText("Search Book By Category");
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
		
		categoryScroll = new JScrollPane();
		categoryScroll.setBackground(Color.DARK_GRAY);
		categoryScroll.setBorder(new MatteBorder(0, 2, 2, 2, (Color) new Color(51, 255, 255)));
		categoryScroll.setVisible(false);
		categoryScroll.setBounds(237, 118, 426, 203);
		contentPane.add(categoryScroll);
		
		categoryList = new JList();
		categoryList.addListSelectionListener(new ListSelectionListener() {
			@SuppressWarnings("unchecked")
			public void valueChanged(ListSelectionEvent arg0) {
				 try 
			        {
					 	Object ans = categoryList.getSelectedValue();
					 	categoryList.setVisible(false);
						categoryScroll.setVisible(false);
						
						/*Object[] column = {"Book","Category","Author","Publisher"};
						DefaultTableModel model = new DefaultTableModel();
						model.setColumnIdentifiers(column);
						bookTable.setModel(model);*/
						
						pst = con.prepareStatement("select Book,Category,Author,Publisher from entry where Category = ?");
						pst.setString(1, (String.valueOf(ans)));
						rs = pst.executeQuery();
						bookTable.setModel(DbUtils.resultSetToTableModel(rs));
						
						bookTable.setShowHorizontalLines(true);
						bookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
						
						TableColumn column1 = null;
						for (int i = 0; i < 4; i++) {
						    column1 = bookTable.getColumnModel().getColumn(i);
						    if(i==0) {
						    	column1.setPreferredWidth(340);
						    }
						    if (i == 1) {
						        column1.setPreferredWidth(160);
						    }
						    else if(i==2) {
						    	column1.setPreferredWidth(240);
						    }
						    else if(i==3) {
						    	column1.setPreferredWidth(300);
						    }
						}
						bookTable.setFont(new Font("SolaimanLipi", Font.PLAIN, 20));
						bookTable.setRowHeight(30);
			           	
			            if(textSearch.getText().equals("")) {
			            	bookLoad();
			            }
			        }
			        catch (SQLException ex) 
			        {
			        	ex.printStackTrace();
			        }
			}
		});
		categoryList.setFont(new Font("SolaimanLipi", Font.PLAIN, 20));
		categoryList.setForeground(Color.BLACK);
		categoryList.setVisible(false);
		categoryList.setBackground(new Color(173, 216, 230));
		categoryScroll.setViewportView(categoryList);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 180, 694, 301);
		contentPane.add(scrollPane);
		
		bookTable = new JTable();
		bookTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnRequest.setEnabled(true);
		        DefaultTableModel d= (DefaultTableModel) bookTable.getModel();
		        int selectedindex = bookTable.getSelectedRow();        
		        bname = d.getValueAt(selectedindex, 0).toString();
			}
		});
		scrollPane.setViewportView(bookTable);
		
		btnRequest = new JButton("Request");
		btnRequest.setEnabled(false);
		btnRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name,id;
				id = Login.ID1;
				name = Login.NAME1;
				java.util.Date date = new java.util.Date();
				java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
				
				try {
					pst = con.prepareStatement("insert into issuebook(MemberID,MemberName,BookName,RequestTime)values(?,?,?,?)");
					pst.setString(1, id);
					pst.setString(2, name);
					pst.setString(3, bname);
					pst.setTimestamp(4, timestamp);
					pst.executeUpdate();

					JOptionPane.showMessageDialog(null, "Your Request is sent.");
					
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRequest.setBackground(new Color(51, 51, 204));
		btnRequest.setForeground(Color.WHITE);
		btnRequest.setFont(new Font("Dialog", Font.BOLD, 24));
		btnRequest.setBounds(160, 532, 177, 40);
		contentPane.add(btnRequest);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(com.mkw.loginpanel.Login.work.equals("Student")) {
					dispose();
					com.mkw.profilepanel.StudentProfile sp = new com.mkw.profilepanel.StudentProfile();
					sp.setVisible(true);
				}
				else if(com.mkw.loginpanel.Login.work.equals("Teacher")) {
					dispose();
					com.mkw.profilepanel.TeacherProfile tp = new com.mkw.profilepanel.TeacherProfile();
					tp.setVisible(true);
				}
			}
		});
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Dialog", Font.BOLD, 24));
		btnBack.setBackground(new Color(51, 51, 204));
		btnBack.setBounds(557, 532, 177, 40);
		contentPane.add(btnBack);
		
		bookImage = new JLabel("");
		bookImage.setBorder(new LineBorder(new Color(0, 0, 51), 4));
		bookImage.setBounds(0, 0, 900, 600);
		ImageIcon titleIMG = new ImageIcon(this.getClass().getResource("/best52.jpg"));
		Image timg = titleIMG.getImage().getScaledInstance(bookImage.getWidth(), bookImage.getHeight(), Image.SCALE_SMOOTH);
		titleIMG = new ImageIcon(timg);
		bookImage.setIcon(titleIMG);
		contentPane.add(bookImage);
	}
}
