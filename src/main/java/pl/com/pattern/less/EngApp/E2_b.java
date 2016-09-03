package pl.com.pattern.less.EngApp;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class E2_b extends JFrame implements ItemListener{

	private JPanel contentPane;
	private JTextField tableName;
	// buttons
	private JButton btnAddNewTable;
	private JButton toE1;
	// choice  list
	private Choice choice;
	
	public static String name1="1";
	public static String name2 = "2";


	public E2_b() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 80, 450, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// btn AddNewTable ///////////////////////////////
		btnAddNewTable = new JButton("Add new table");
		btnAddNewTable.setBounds(285, 75, 106, 51);
		contentPane.add(btnAddNewTable);
		btnAddNewTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//dzialaja metody dla pola klasy E2 - utworzyc w klasie sqlForApp metode dodawania recordow z excela  
				//i zaimplementowac ja dla tego przycisku nastepnie 
				//grab and drop
				System.out.println("przycisk dziala");
				String resultSelectENG = E1.frame1.frame2.sqlForApp.selectWord("list2", "engWord", 2);
				System.out.println(resultSelectENG);
				
			}
		});
	
		
		// txt field tableName 
		tableName = new JTextField();
		tableName.setBounds(79, 106, 181, 20);
		contentPane.add(tableName);
		tableName.setColumns(10);
		

		
		//na podstawie choice z menu bar E2 dodac choice tutaj do listy !! /////////////////
	
	// btn toE1 ////////////////////////////////
	JButton toE1 = new JButton("toE1");
	toE1.setBounds(34, 188, 76, 47);
	contentPane.add(toE1);
	// choice list with tables names
	choice = new Choice();
	choice.setBounds(79, 80, 181, 20);
	contentPane.add(choice);
	choice.add("1");
	choice.add("2");
	choice.addItemListener(this);
	
	
	
	toE1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {

			E1.frame3.setVisible(false);
			E1.frame1.setVisible(true);
			System.out.println("Powrot do pierwszego ekranu");
		}
	});
}
	
	public void itemStateChanged(ItemEvent arg0) {
		if (choice.getSelectedItem().equals(name1)){
			
			
			
			
			
			System.out.println("Wyswietl 1");
	        }
		if (choice.getSelectedItem().equals(name2))
		{
			

	       
			System.out.println("Wyswietl 2");
	        }
			
	}
}