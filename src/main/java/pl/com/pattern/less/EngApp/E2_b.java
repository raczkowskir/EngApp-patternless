package pl.com.pattern.less.EngApp;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class E2_b extends JFrame implements ItemListener {

	// This view lets user add entire list of words into table
	// before that he has to fill connected with program excel file
	// each table has its own excel file

	private JPanel contentPane;
	// buttons
	private JButton btnAddNewTable;
	private JButton toE1;
	// labels
	private JLabel lblChoseTable;
	private JLabel lblInformation;
	private JLabel lblAdvise;

	// choice list
	private Choice choice;
	// String with name of file which will be used for taking the words for
	// adding to table
	String fileName = "list1.csv";
	// table name in database
	String sqlTableName = "list1";

	// table for input
	String StrInput[] = new String[11];
	// table for substrings input
	String subString1[] = new String[5];

	public E2_b() {
		setTitle("EngApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 80, 450, 319);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// btn AddNewTable ///////////////////////////////
		btnAddNewTable = new JButton("");
		btnAddNewTable.setIcon(new ImageIcon("C:\\Users\\Rafał\\EngAppDesktop\\EngApp\\buttons\\button (11).png"));
		btnAddNewTable.setBounds(285, 75, 128, 87);
		contentPane.add(btnAddNewTable);
		btnAddNewTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				System.out.println("przycisk dziala");
				/*
				 * String resultSelectENG =
				 * E1.frame1.frame2.sqlForApp.selectWord("list2", "engWord", 2);
				 * System.out.println(resultSelectENG);
				 */
				csvReader(fileName);
				split();
				lblInformation.setText("Words added!");

				// E1.frame1.frame2.sqlForApp.insertWord("list2", "duck",
				// "kaczka");

			}
		});
		// btn toE1 ////////////////////////////////
		JButton toE1 = new JButton("");
		toE1.setIcon(new ImageIcon("C:\\Users\\Rafał\\EngAppDesktop\\EngApp\\buttons\\button (1).png"));
		toE1.setBounds(34, 188, 76, 47);
		contentPane.add(toE1);
		// choice list with tables names
		choice = new Choice();
		choice.setFont(new Font("Dialog", Font.BOLD, 12));
		choice.setForeground(Color.BLUE);
		choice.setBounds(10, 96, 254, 22);
		contentPane.add(choice);
		choice.add("Table: 1 (Add words from excel file: list1)");
		choice.add("Table: 2 (list2)");
		choice.add("Table: 3 (list3)");
		choice.add("Table: 4 (list4)");
		choice.add("Table: 5 (list5)");
		choice.addItemListener(this);

		// JLabel lblChoseTable
		lblChoseTable = new JLabel("Chose table:");
		lblChoseTable.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblChoseTable.setBounds(93, 63, 120, 26);
		contentPane.add(lblChoseTable);

		// JLabel lblInformation
		lblInformation = new JLabel("");
		lblInformation.setBounds(93, 22, 171, 14);
		contentPane.add(lblInformation);

		// lblAdvise
		lblAdvise = new JLabel("<html>Please, first fill in an excel file attached to the program.<html>");
		lblAdvise.setBounds(139, 188, 274, 47);
		contentPane.add(lblAdvise);

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
		if (choice.getSelectedItem().equals("Table: 1 (Add words from excel file: list1)")) {
			fileName = "list1.csv";
			sqlTableName = "list1";
			System.out.println("Wybrano tabele list1");
			lblInformation.setText("");
		}
		if (choice.getSelectedItem().equals("Table: 2 (list2)")) {
			fileName = "list2.csv";
			sqlTableName = "list2";
			System.out.println("Wybrano tabele list2");
			lblInformation.setText("");
		}
		if (choice.getSelectedItem().equals("Table: 3 (list3)")) {
			fileName = "list3.csv";
			sqlTableName = "list3";
			System.out.println("Wybrano tabele list3");
			lblInformation.setText("");
		}
		if (choice.getSelectedItem().equals("Table: 4 (list4)")) {
			fileName = "list4.csv";
			sqlTableName = "list4";
			System.out.println("Wybrano tabele list4");
			lblInformation.setText("");
		}
		if (choice.getSelectedItem().equals("Table: 5 (list5)")) {
			fileName = "list5.csv";
			sqlTableName = "list5";
			System.out.println("Wybrano tabele list5");
			lblInformation.setText("");
		}

	}

	// the method for reading csv
	public String csvReader(String fileName) {
		// file name - "list3.csv"
		try {
			File file = new File(fileName);

			Scanner in = new Scanner(file);
			StrInput[0] = in.nextLine();
			// INCREASE MAXIMAL "i" FOR ADDING BIGGER LISTS (MORE WORDS AT
			// ONES)(*1)
			for (int i = 0; i < 10; i++) {
				StrInput[i] = in.nextLine();

				System.out.println(StrInput[i]);

			}
		} catch (FileNotFoundException e) {
			System.out.println("error");
		}

		return "i";
	}

	// the method for splitting rows on separate words and inserting it into
	// proper table
	public void split() {
		// showing where each part of string is ending

		// INCREASE MAXIMAL "i" FOR ADDING BIGGER LISTS (MORE WORDS AT
		// ONES)(*2*)
		for (int i = 0; i < 10; i++) {
			int position1 = StrInput[i].indexOf(";");
			// System.out.println(position1);

			// split String on substrings
			subString1[0] = StrInput[i].substring(0, position1);
			// System.out.println(subString1[0]);

			subString1[1] = StrInput[i].substring(position1 + 1, StrInput[i].length());
			// System.out.println(subString1[1]);

			E1.frame1.frame2.sqlForApp.insertWord(sqlTableName, subString1[0], subString1[1]);
		}
		
	}
}