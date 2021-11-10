package com.mkw.joptionpanes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionEvent;

public class Confirmation extends JFrame {

	private JPanel contentPane;
	private JButton btnCancel;
	private JButton btnOk;
	private JPanel panel;
	private JPanel title;
	private JLabel lblNewLabel;
	private JLabel lblimg;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblpic;
	private Point mouseClickPoint; // Will reference to the last pressing (not clicking) position

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Confirmation frame = new Confirmation();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Confirmation() {
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
		
		lblNewLabel = new JLabel("CONFERMATION");
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
				System.exit(0);
			}
		});
		btnOk.setBackground(new Color(0, 153, 255));
		btnOk.setForeground(Color.WHITE);
		btnOk.setFont(new Font("Dialog", Font.BOLD, 12));
		btnOk.setBounds(46, 81, 76, 23);
		panel.add(btnOk);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("Dialog", Font.BOLD, 12));
		btnCancel.setBackground(new Color(0, 153, 255));
		btnCancel.setBounds(147, 81, 76, 23);
		panel.add(btnCancel);
		
		lblimg = new JLabel("");
		lblimg.setBounds(4, 8, 40, 42);
		ImageIcon warning = new ImageIcon(this.getClass().getResource("/information2.png"));
		Image img = warning.getImage().getScaledInstance(lblimg.getWidth(), lblimg.getHeight(), Image.SCALE_SMOOTH);
		warning = new ImageIcon(img);
		lblimg.setIcon(warning);
		panel.add(lblimg);
		
		lblNewLabel_1 = new JLabel("Do You Want To");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Cambria Math", Font.BOLD, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(61, 12, 147, 23);
		panel.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Close The Application?");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Cambria Math", Font.BOLD, 15));
		lblNewLabel_2.setBounds(48, 35, 173, 23);
		panel.add(lblNewLabel_2);
		
		lblpic = new JLabel("");
		lblpic.setBounds(0, 0, 270, 116);
		ImageIcon pic = new ImageIcon(this.getClass().getResource("/light10.jpg"));
		Image img1 = pic.getImage().getScaledInstance(lblpic.getWidth(), lblpic.getHeight(), Image.SCALE_SMOOTH);
		pic = new ImageIcon(img1);
		lblpic.setIcon(pic);
		panel.add(lblpic);
	}
}
