package pl.com.pattern.less.EngApp;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class E2 extends JFrame implements ItemListener{
	// commit for:EngApp Pattern less - part 2 ManyTables
	/*
	 * Right now user can: - use SQLite DB with GUI - create and drop only one
	 * table - insert new rows - delete rows - brows content of the
	 * table(forward and backward) and: (part2) - move between components by
	 * using TAB button
	 */

	private JPanel contentPane;
	private JTextArea txtENG;
	private JTextArea txtPL;
	///////////////////////////
	private JButton btnNext;
	private JButton btnDelete;
	private JButton btnAdd;
	private JButton btnCheck;
	private JButton btnClearList;
	// label which is showing index of current position in list and total volume
	private JLabel lblNumber;
	// label for showing important information like "Table is empty!"
	private JLabel prompt;
	// iterator which is pointing next position on a list
	private int iterator = 0;
	// variable which is showing total volume of current table
	private int volume = 0;
	// object of SQLite data base - it will be store all data for this
	// application
	SQLforApp sqlForApp = new SQLforApp();
	// Menu bar
	JMenuBar menuBar;
	// list for selecting table we want to use
	Choice choice; 
	
	// field which is using for setting table
	private String list = "list1";
	
	////////TEST METHOD
	
	
	// the constructor of class E2
	public E2() {
		// settings of frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 80, 450, 319);
		/////////////////////////// MENU ////////////////////////////
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		//list of tables available for user
		choice = new Choice();
		menuBar.add(choice);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// adding new available tables
		choice.add("1");
		choice.add("2");
		choice.add("3");
		choice.add("4");
		choice.add("5");
		choice.addItemListener(this);
		
		// creating tables
		sqlForApp.createTables();
		System.out.println("utworzono tabele");

		// the method which is counting total number of rows for current table
		volume = sqlForApp.countWords(list);
		System.out.println("oto label:" + iterator + "/" + volume);

		// btn toE1
		JButton toE1 = new JButton("toE1");
		toE1.setBounds(10, 203, 76, 47);
		contentPane.add(toE1);
		toE1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				E1.frame2.setVisible(false);
				E1.frame1.setVisible(true);
				System.out.println("Powrot do pierwszego ekranu");
			}
		});
		// JTextArea
		txtENG = new JTextArea();
		txtENG.setBounds(69, 24, 207, 20);
		contentPane.add(txtENG);

		// below code lets user move between JTextArea by using TAB btn
		txtENG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					if (e.getModifiers() > 0) {
						txtENG.transferFocusBackward();
					} else {
						txtENG.transferFocus();
					}
					e.consume();
				}
			}
		});
		// JtxtPL(
		txtPL = new JTextArea();
		txtPL.setBounds(69, 57, 207, 20);
		contentPane.add(txtPL);
		txtPL.setColumns(10);
		// below code lets user move between JTextArea by using TAB btn
		txtPL.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					if (e.getModifiers() > 0) {
						txtPL.transferFocusBackward();
					} else {
						txtPL.transferFocus();
					}
					e.consume();
				}
			}
		});
		// NEXT///////////////////////////////////
		btnNext = new JButton("Next");
		btnNext.setBounds(294, 97, 89, 47);
		contentPane.add(btnNext);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// moving to the next row in the table

				if (iterator < volume && volume != 0) {
					iterator++;
				} else if (iterator == volume && volume != 0) {
					iterator = 1;
				} else {
					iterator = 0;
				}
				if (iterator == 0) {
					txtENG.setText("");
					txtPL.setText("");
					prompt.setText("Table is empty!");
				} else {
					String resultSelectENG = sqlForApp.selectWord(list, "engWord", iterator);
					txtENG.setText(resultSelectENG);
					txtPL.setText("");
				}
				// setting label which is showing current position in table
				String label = iterator + "/" + volume;
				lblNumber.setText(label);
			}
		});
		// BACK///////////////////////////////////
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(187, 97, 89, 47);
		contentPane.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// moving to the previous row in the table
				if (iterator > 1) {
					iterator--;

				} else {
					iterator = volume;
				}

				if (iterator == 0) {
					txtENG.setText("");
					txtPL.setText("");
					prompt.setText("Table is empty!");
				} else {
					String resultSelectENG = sqlForApp.selectWord(list, "engWord", iterator);
					txtENG.setText(resultSelectENG);
					txtPL.setText("");
				}

				// setting label which is showing current position in table
				String label = iterator + "/" + volume;
				lblNumber.setText(label);
			}
		});

		/// CHECK///////////////////////////////////
		btnCheck = new JButton("Check");
		btnCheck.setBounds(294, 155, 89, 45);
		contentPane.add(btnCheck);
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (volume != 0) {
					String resultSelect = sqlForApp.selectWord(list, "plWord", iterator);
					txtPL.setText(resultSelect);
				}
			}
		});
		// DELETE///////////////////////////////////
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sqlForApp.deleteWord(list, txtENG.getText());
				if (iterator == volume) {
					iterator = 1;
				}
				System.out.println("Usunieto słowo z tabeli z pozycji: " + iterator);
				if (volume > 0) {
					volume--;
				}
				if (volume != 0) {
					txtENG.setText(sqlForApp.selectWord(list, "engWord", iterator));

				} else {
					txtENG.setText("");
				}
				txtPL.setText("");
				// setting label which is showing current position in table

				String label = iterator + "/" + volume;
				lblNumber.setText(label);

				/////// I'm am not sure the below is necessary here
				/////// //////////////////////
				sqlForApp.createTables();
			}
		});
		btnDelete.setBounds(69, 155, 89, 45);
		contentPane.add(btnDelete);

		// ADD /////////////////////////////////////////////////
		btnAdd = new JButton("Add");
		btnAdd.setBounds(69, 97, 89, 47);
		contentPane.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String StrTxtENG = txtENG.getText();
				String StrTxtPL = txtPL.getText();
				sqlForApp.insertWord(list, StrTxtENG, StrTxtPL);
				System.out.println("dodano slowo do tabeli");
				txtENG.setText("");
				txtPL.setText("");
				prompt.setText("");

				// setting label which is showing current position in table
				volume++;
				String label = iterator + "/" + volume;
				lblNumber.setText(label);
			}
		});
		// btn ClearList
		btnClearList = new JButton("btnClearList");
		btnClearList.setBounds(335, 11, 89, 23);
		contentPane.add(btnClearList);
		btnClearList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// the method which clear current table
				sqlForApp.clearTable(list);
				// setting label which is showing current position in table
				volume = 0;
				String label = iterator + "/" + volume;
				lblNumber.setText(label);
				txtENG.setText("");
				txtPL.setText("");
			}
		});

		/// counting volume
		volume = sqlForApp.countWords(list);
		// labelNUMBER
		String label = iterator + "/" + volume;
		lblNumber = new JLabel(label);
		lblNumber.setBounds(400, 236, 24, 14);
		contentPane.add(lblNumber);

		prompt = new JLabel("");
		prompt.setBounds(94, 0, 150, 14);
		contentPane.add(prompt);

	}
// the method for choosing table from scroll list in menu bar
	public void itemStateChanged(ItemEvent e) {
	if (choice.getSelectedItem().equals("1")){
        System.out.println("Ustawiono: list1");
        list = "list1";
        //setting label, volume and iterator for new list
        iterator =0;
        volume = sqlForApp.countWords(list);
        String label = iterator + "/" + volume;
		lblNumber.setText(label);
		prompt.setText("");
        }
	if (choice.getSelectedItem().equals("2"))
	{
        System.out.println("Ustawiono: list2");
        list = "list2";
      //setting label, volume and iterator for new list
        iterator =0;
        volume = sqlForApp.countWords(list);
        String label = iterator + "/" + volume;
		lblNumber.setText(label);
		prompt.setText("");
        }
	if (choice.getSelectedItem().equals("3")){
        System.out.println("Ustawiono: list3");
        list = "list3";
        //setting label, volume and iterator for new list
        iterator =0;
        volume = sqlForApp.countWords(list);
        String label = iterator + "/" + volume;
		lblNumber.setText(label);
		prompt.setText("");
        }
	if (choice.getSelectedItem().equals("4"))
	{
        System.out.println("Ustawiono: list4");
        list = "list4";
      //setting label, volume and iterator for new list
        iterator =0;
        volume = sqlForApp.countWords(list);
        String label = iterator + "/" + volume;
		lblNumber.setText(label);
		prompt.setText("");
	}
	if (choice.getSelectedItem().equals("5"))
	{
        System.out.println("Ustawiono: list5");
        list = "list5";
      //setting label, volume and iterator for new list
        iterator =0;
        volume = sqlForApp.countWords(list);
        String label = iterator + "/" + volume;
		lblNumber.setText(label);
		prompt.setText("");
	}
		
	}
}
