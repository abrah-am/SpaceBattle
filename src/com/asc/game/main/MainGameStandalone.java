package com.asc.game.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;

import com.asc.game.components.gui.WinnersWindow;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGameStandalone {

	private JFrame frmSpaceConquere;
	private AlienKillerGame canvas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGameStandalone window = new MainGameStandalone();
					window.frmSpaceConquere.setVisible(true);
					new Thread(window.canvas).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGameStandalone() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSpaceConquere = new JFrame();
		frmSpaceConquere.setTitle("Space conqueror");
		frmSpaceConquere.setBounds(100, 100, 950, 700);
		frmSpaceConquere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas = new AlienKillerGame(900, 600);
		
		JMenuBar menuBar = new JMenuBar();
		frmSpaceConquere.setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		JMenuItem mntmNewGame = new JMenuItem("New Game");
		mntmNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.newGame();
			}
		});
		mnMenu.add(mntmNewGame);
		
		JCheckBoxMenuItem chckbxmntmSound = new JCheckBoxMenuItem("Sound");
		chckbxmntmSound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.setSoundOn(!canvas.isSoundOn());
			}
		});
		chckbxmntmSound.setSelected(canvas.isSoundOn());
		mnMenu.add(chckbxmntmSound);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JMenuItem mntmScores = new JMenuItem("Scores");
		mntmScores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinnersWindow.main(null);
			}
		});
		mnMenu.add(mntmScores);
		mnMenu.add(mntmExit);
		frmSpaceConquere.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		frmSpaceConquere.getContentPane().add(canvas);
	}
}
