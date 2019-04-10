package com.asc.game.components.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import com.asc.game.dao.RecordsDAO;
import com.asc.game.model.Records;
import com.asc.game.model.RecordsId;

public class RecordWindow {

	private JFrame frmWinner;
	private JTextField txtName;
	private Integer completionTime;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecordWindow window = new RecordWindow(200000);
					window.frmWinner.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RecordWindow(Integer completionTime) {
		this.completionTime = completionTime;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWinner = new JFrame();
		frmWinner.setTitle("Winner");
		frmWinner.setBounds(100, 100, 450, 300);
		frmWinner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		txtName = new JTextField();
		txtName.setColumns(10);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if("".equals(txtName.getText())){
					//Send a message and return;
					return;
				}
				System.out.println("Inserting the following record in the table: name " + txtName.getName() + "; time: " + completionTime);
				new RecordsDAO().insert(new Records(new RecordsId(txtName.getText(), completionTime)));
				System.out.println("/Inserting the following record in the table: name " + txtName.getName() + "; time: " + completionTime);
			}
		});
		
		JLabel label = new JLabel("<html><h1>Congratulations</h1> <h4>It is time to write down your name in the history of Alien Invasion</h4>");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout groupLayout = new GroupLayout(frmWinner.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 411, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(30, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(158, Short.MAX_VALUE)
					.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtName, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addGap(116))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(173)
					.addComponent(btnEnviar)
					.addContainerGap(198, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName))
					.addGap(18)
					.addComponent(btnEnviar)
					.addContainerGap(65, Short.MAX_VALUE))
		);
		frmWinner.getContentPane().setLayout(groupLayout);
	}
}
