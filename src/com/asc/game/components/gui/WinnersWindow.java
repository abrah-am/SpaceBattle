package com.asc.game.components.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import com.asc.game.dao.RecordsDAO;
import com.asc.game.model.Records;

import java.awt.Font;
import java.util.List;

public class WinnersWindow {

	private JFrame frmBigWinners;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinnersWindow window = new WinnersWindow();
					window.frmBigWinners.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WinnersWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBigWinners = new JFrame();
		frmBigWinners.setTitle("Winners");
		frmBigWinners.setBounds(100, 100, 450, 300);
		frmBigWinners.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblTopTenOf = new JLabel("Top 10 of the best times in finish the game");
		lblTopTenOf.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout groupLayout = new GroupLayout(frmBigWinners.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(29)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(58)
							.addComponent(lblTopTenOf)))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(43, Short.MAX_VALUE)
					.addComponent(lblTopTenOf)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
					.addGap(32))
		);
		
		List<Records> records = new RecordsDAO().findAll();
		System.out.println("Loading records: " + records.size());
		Object[][] values = new Object[records.size()][2];
		for(int i=0; i < records.size(); i++){
			values[i][0] = records.get(i).getId().getName();
			values[i][1] = records.get(i).getId().getWintime();
		}
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			values,
			new String[] {
				"Name", "Completion Time"
			}
		));
		scrollPane.setViewportView(table);
		frmBigWinners.getContentPane().setLayout(groupLayout);
	}
}
