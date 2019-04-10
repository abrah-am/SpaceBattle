package com.asc.game.main;

import javax.swing.JApplet;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MainGameApplet extends JApplet {
	private AlienKillerGame canvas;

	/**
	 * Create the applet.
	 */
	public MainGameApplet() {
		
		canvas = new AlienKillerGame(1000, 800);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
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
		mnMenu.add(chckbxmntmSound);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnMenu.add(mntmExit);
		
		getContentPane().add(canvas, BorderLayout.NORTH);
		

	}
	@Override
	public void init() {
		setSize(1000, 800);
		new Thread(canvas).start();
	}

}
