package com.asc.game.components.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.asc.game.dao.RecordsDAO;
import com.asc.game.model.Records;

import java.awt.Font;
import java.awt.Color;
import java.util.List;

public class WinnersFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinnersFrame frame = new WinnersFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WinnersFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblTopTenOf = new JLabel("Top Ten of the better score times in Alien Invasion");
		lblTopTenOf.setForeground(new Color(0, 0, 204));
		lblTopTenOf.setFont(new Font("Arial", Font.BOLD, 14));
		GridBagConstraints gbc_lblTopTenOf = new GridBagConstraints();
		gbc_lblTopTenOf.insets = new Insets(20, 0, 20, 0);
		gbc_lblTopTenOf.gridx = 1;
		gbc_lblTopTenOf.gridy = 1;
		contentPane.add(lblTopTenOf, gbc_lblTopTenOf);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		contentPane.add(scrollPane, gbc_scrollPane);
		
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
	}

}
