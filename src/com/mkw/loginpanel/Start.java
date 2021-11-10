package com.mkw.loginpanel;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import com.mkw.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Start extends JFrame {

	private JPanel contentPane;
	private JButton btnTeacher;
	private JButton btnStudent;
	private JButton btnAdmin;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start frame = new Start();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Start() {
		setLookAndFeel();
		initialize();
	}
	
	public void setLookAndFeel() {
		try {
				UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			//UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
			//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
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
		contentPane.setLayout(null);
		
		btnTeacher = new JButton("Teacher");
		btnTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				com.mkw.loginpanel.TeacherLogin tl = new com.mkw.loginpanel.TeacherLogin();
				tl.setVisible(true);
			}
		});
		btnTeacher.setBounds(10, 193, 252, 195);
		contentPane.add(btnTeacher);
		
		btnStudent = new JButton("Student");
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				com.mkw.loginpanel.StudentLogin sl = new com.mkw.loginpanel.StudentLogin();
				sl.setVisible(true);
			}
		});
		btnStudent.setBounds(321, 193, 252, 195);
		contentPane.add(btnStudent);
		
		btnAdmin = new JButton("Admin");
		btnAdmin.setBounds(638, 193, 252, 195);
		contentPane.add(btnAdmin);
	}
}
