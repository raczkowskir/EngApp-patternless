package pl.com.pattern.less.EngApp;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class E2_b extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					E2_b frame = new E2_b();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	public E2_b() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 80, 450, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddNewTable = new JButton("Add new table");
		btnAddNewTable.setBounds(285, 75, 106, 51);
		contentPane.add(btnAddNewTable);
		
		textField = new JTextField();
		textField.setBounds(79, 106, 181, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(79, 75, 181, 20);
		contentPane.add(comboBox);
	
	// btn toE1
	JButton toE1 = new JButton("toE1");
	toE1.setBounds(34, 188, 76, 47);
	contentPane.add(toE1);
	toE1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {

			E1.frame3.setVisible(false);
			E1.frame1.setVisible(true);
			System.out.println("Powrot do pierwszego ekranu");
		}
	});
}
}