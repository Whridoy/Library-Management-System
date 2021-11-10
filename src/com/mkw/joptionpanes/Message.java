package com.mkw.joptionpanes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class Message extends JFrame {

	private JPanel contentPane;
	private JButton btnOk;
	private JPanel panel;
	private JPanel title;
	private JLabel lblNewLabel;
	private JLabel lblimg;
	private JLabel lblNewLabel_1;
	private JLabel lblpic;
	private Point mouseClickPoint; // Will reference to the last pressing (not clicking) position

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Message frame = new Message();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Message(){
		setLookAndFeel();
		initialize();
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
		setDefaultLookAndFeelDecorated(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(270, 140);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		title = new JPanel();
		title.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
		title.setBackground(Color.DARK_GRAY);
		title.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				Point newPoint = arg0.getLocationOnScreen();
	            newPoint.translate(-mouseClickPoint.x, -mouseClickPoint.y); // Moves the point by given values from its location
	            setLocation(newPoint); // set the new location
			}
		});
		title.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				mouseClickPoint = arg0.getPoint();
			}
		});
		title.setBounds(0, 0, 270, 24);
		contentPane.add(title);
		title.setLayout(null);
		
		lblNewLabel = new JLabel("MESSAGE");
		lblNewLabel.setFont(new Font("Cambria Math", Font.BOLD, 11));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(6, 0, 98, 24);
		title.add(lblNewLabel);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
		panel.setBounds(0, 24, 270, 116);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnOk.setBackground(new Color(0, 153, 255));
		btnOk.setForeground(Color.WHITE);
		btnOk.setFont(new Font("Dialog", Font.BOLD, 12));
		btnOk.setBounds(97, 81, 76, 23);
		panel.add(btnOk);
		
		lblimg = new JLabel("");
		lblimg.setBounds(4, 12, 40, 42);
		ImageIcon warning = new ImageIcon(this.getClass().getResource("/information1.png"));
		Image img = warning.getImage().getScaledInstance(lblimg.getWidth(), lblimg.getHeight(), Image.SCALE_SMOOTH);
		warning = new ImageIcon(img);
		lblimg.setIcon(warning);
		panel.add(lblimg);
		
		lblNewLabel_1 = new JLabel("Account Created Successfully");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Cambria Math", Font.BOLD, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(46, 27, 214, 23);
		panel.add(lblNewLabel_1);
		
		lblpic = new JLabel("");
		lblpic.setBounds(0, 0, 270, 116);
		ImageIcon pic = new ImageIcon(this.getClass().getResource("/light10.jpg"));
		Image img1 = pic.getImage().getScaledInstance(lblpic.getWidth(), lblpic.getHeight(), Image.SCALE_SMOOTH);
		pic = new ImageIcon(img1);
		lblpic.setIcon(pic);
		panel.add(lblpic);
	}

}
