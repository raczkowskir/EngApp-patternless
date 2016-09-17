package pl.com.pattern.less.EngApp;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class E2_b extends JFrame implements ItemListener {

	private JPanel contentPane;
	// buttons
	private JButton btnAddNewTable;
	private JButton toE1;
	// labels
	private JLabel lblChoseTable;
	private JLabel lblInformation;
	
	// choice list
	private Choice choice;
	// String with name of file which will be used for taking the words for adding to table
	String fileName = "list1.csv";
	// table name in database
	String sqlTableName = "list1";
	
	// table for input
	String StrInput[] = new String[11];
	// table for substrings input
	String subString1[] = new String[5];

	public E2_b() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 80, 450, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// btn AddNewTable ///////////////////////////////
		btnAddNewTable = new JButton("Add words");
		btnAddNewTable.setBounds(285, 75, 128, 66);
		contentPane.add(btnAddNewTable);
		btnAddNewTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				System.out.println("przycisk dziala");
				/*String resultSelectENG = E1.frame1.frame2.sqlForApp.selectWord("list2", "engWord", 2);
				System.out.println(resultSelectENG);*/
				csvReader(fileName);
				split();
				lblInformation.setText("Words added!");	
					
			//	E1.frame1.frame2.sqlForApp.insertWord("list2", "duck", "kaczka");
				

			}
		});
		// btn toE1 ////////////////////////////////
		JButton toE1 = new JButton("toE1");
		toE1.setBounds(34, 188, 76, 47);
		contentPane.add(toE1);
		// choice list with tables names
		choice = new Choice();
		choice.setBounds(83, 96, 181, 20);
		contentPane.add(choice);
		choice.add("1");
		choice.add("2");
		choice.add("3");
		choice.add("4");
		choice.add("5");
		choice.addItemListener(this);
		
		//JLabel lblChoseTable
		lblChoseTable = new JLabel("Chose table:");
		lblChoseTable.setBounds(93, 63, 120, 26);
		contentPane.add(lblChoseTable);
		
		// JLabel lblInformation
		lblInformation = new JLabel("");
		lblInformation.setBounds(93, 22, 171, 14);
		contentPane.add(lblInformation);
		
		// btn toE1
		toE1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				E1.frame3.setVisible(false);
				E1.frame1.setVisible(true);
				System.out.println("Powrot do pierwszego ekranu");
			}
		});
	}

	public void itemStateChanged(ItemEvent arg0) {
		if (choice.getSelectedItem().equals("1")) {
			fileName= "list1.csv";
			sqlTableName = "list1";
			System.out.println("Wybrano tabele list1");
			lblInformation.setText("");	
		}
		if (choice.getSelectedItem().equals("2")) {
			fileName= "list2.csv";
			sqlTableName = "list2";
			System.out.println("Wybrano tabele list2");
			lblInformation.setText("");	
		}
		if (choice.getSelectedItem().equals("3")) {
			fileName= "list3.csv";
			sqlTableName = "list3";
			System.out.println("Wybrano tabele list3");
			lblInformation.setText("");	
		}
		if (choice.getSelectedItem().equals("4")) {
			fileName= "list4.csv";
			sqlTableName = "list4";
			System.out.println("Wybrano tabele list4");
			lblInformation.setText("");	
		}
		if (choice.getSelectedItem().equals("5")) {
			fileName= "list5.csv";
			sqlTableName = "list5";
			System.out.println("Wybrano tabele list5");
			lblInformation.setText("");	
		}

	}

	// the method for reading csv
	public String csvReader(String fileName){
		// file name - "list3.csv"
		try{
			File file = new File(fileName);

			Scanner in = new Scanner(file);
			StrInput[0] = in.nextLine();
			//INCREASE MAXIMAL "i" FOR ADDING BIGGER LISTS (MORE WORDS AT ONES)(*1)
			for (int i = 0; i < 10; i++) {
				StrInput[i] = in.nextLine();

				System.out.println(StrInput[i]);

			}
			}
			catch (FileNotFoundException e) {
				System.out.println("error");
			}

			return "i";
	}
// the method for splitting rows on separate words and inserting it intu proper table
	public void split() {
		// showing where each part of string is ending
		
		//INCREASE MAXIMAL "i" FOR ADDING BIGGER LISTS (MORE WORDS AT ONES)(*2*)
		for (int i = 0; i < 10; i++) {
			int position1 = StrInput[i].indexOf(";");
			// System.out.println(position1);

			// split String on substrings
			subString1[0] = StrInput[i].substring(0, position1);
			//System.out.println(subString1[0]);

			subString1[1] = StrInput[i].substring(position1 + 1, StrInput[i].length());
			//System.out.println(subString1[1]);

			E1.frame1.frame2.sqlForApp.insertWord(sqlTableName, subString1[0], subString1[1]);
		}
	}
}