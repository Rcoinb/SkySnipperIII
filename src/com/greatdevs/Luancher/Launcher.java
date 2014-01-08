package com.greatdevs.Luancher;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import com.greatdevs.Game;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Launcher {

	private static int ReleaselauncherID = -2;
	private static int TestinglauncherID = -2;
	
	private int launcherID = -2;
	
	private JPanel contentPane;
	private JFrame frame = new JFrame("Sky Snipper III Launcher");

	public Launcher() {		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 280);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSkySnipperIii = new JLabel("Sky Snipper III");
		lblSkySnipperIii.setFont(new Font("Arial", Font.BOLD, 72));
		lblSkySnipperIii.setBounds(45, 11, 494, 107);
		contentPane.add(lblSkySnipperIii);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				play();
			}
		});
		btnPlay.setBounds(10, 228, 89, 23);
		contentPane.add(btnPlay);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		btnExit.setBounds(109, 228, 89, 23);
		contentPane.add(btnExit);
		
		JLabel lblGreatDevs = new JLabel("Copyright \u00A9 2014 Great Devs. All rights reserved.\r\n");
		lblGreatDevs.setFont(new Font("Arial", Font.BOLD, 10));
		lblGreatDevs.setBounds(341, 238, 243, 13);
		contentPane.add(lblGreatDevs);
		
		JLabel lblOfficialLauncher = new JLabel("Official Launcher");
		lblOfficialLauncher.setFont(new Font("Arial", Font.BOLD, 20));
		lblOfficialLauncher.setBounds(331, 107, 170, 23);
		contentPane.add(lblOfficialLauncher);
	}
	
	public void play(){
		frame.dispose();
		if (launcherID == Game.DefaultlauncherID) Game.LaunchedFromLauncher = true;
		else Game.LaunchedFromLauncher = false;
		ReleaselauncherID = TestinglauncherID = launcherID = -1;
		Game.startGame();
	}
	
	public void exit(){
		frame.dispose();
		System.exit(0);
	}
	
	public void setupLauncher() {
		System.out.println("Starting launcher...");
	    ReleaselauncherID = Game.DefaultlauncherID;
		TestinglauncherID = 0x515E11;
		launcherID = ReleaselauncherID;
		if (launcherID == TestinglauncherID){
			play();
		}
		if (launcherID != TestinglauncherID) frame.setVisible(true);
	}
}
