package pl.com.pattern.less.EngApp;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class E2 extends JFrame implements ItemListener {

	/*
	 * this view lets user: chose table he want to browse add and delete
	 * separate words clear whole table browsing words typing translation by
	 * your self and checking is it right
	 */
	private JPanel contentPane;
	private JTextArea txtENG;
	private JTextArea txtPL;
	///////////////////////////
	private JButton toE1;
	private JButton btnStatistics;
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

	// static fields for generating statistic for the view E3
	// all of them are use in the method "check" and later in class E3
	static String wrongAnswers = "";
	static int noOfAnswers = 0;
	static int noOfGoodAns = 0;
	static int noOfBadAns = 0;

	// the constructor of class E2
	public E2() {
		setTitle("EngApp");
		// settings of frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 80, 450, 319);
		/////////////////////////// MENU ////////////////////////////
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		// list of tables available for user
		choice = new Choice();
		choice.setForeground(Color.BLUE);
		menuBar.add(choice);

		// btnStatistics
		btnStatistics = new JButton("STATISTICS");
		menuBar.add(btnStatistics);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnStatistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				E1.frame4 = new E3();
				E1.frame2.setVisible(false);
				E1.frame4.setVisible(true);
				System.out.println("Przejście do trzeciego ekranu - statystyk");
			}
		});
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
		toE1 = new JButton("");
		toE1.setIcon(new ImageIcon("C:\\Users\\Rafał\\EngAppDesktop\\EngApp\\buttons\\button (1).png"));
		toE1.setBounds(10, 199, 76, 47);
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
		txtENG.setFont(new Font("Arial Black", Font.BOLD, 12));
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
		txtPL.setFont(new Font("Arial Black", Font.BOLD, 12));
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
		btnNext = new JButton("");
		btnNext.setIcon(new ImageIcon("C:\\Users\\Rafał\\EngAppDesktop\\EngApp\\buttons\\button (5).png"));
		btnNext.setBounds(294, 101, 81, 38);
		contentPane.add(btnNext);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// moving to the next row in the table

				if (iterator < volume && volume != 0) {
					iterator++;
				} else if (iterator == volume && volume != 0) {
					iterator = 1;
					lblNumber.setForeground(Color.BLUE);
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
		JButton btnBack = new JButton("");
		btnBack.setIcon(new ImageIcon("C:\\Users\\Rafał\\EngAppDesktop\\EngApp\\buttons\\button (4).png"));
		btnBack.setBounds(182, 101, 81, 38);
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
		btnCheck = new JButton("");
		btnCheck.setIcon(new ImageIcon("C:\\Users\\Rafał\\EngAppDesktop\\EngApp\\buttons\\button (6).png"));
		btnCheck.setBounds(294, 155, 81, 38);
		contentPane.add(btnCheck);
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (volume != 0) {
					noOfAnswers = noOfAnswers + 1;
					System.out.println(noOfAnswers + " -liczba wszystkich odpowiedzi");
					String resultSelect = sqlForApp.selectWord(list, "plWord", iterator);
					if (resultSelect.equals(txtPL.getText())) {
						prompt.setText("WELL DONE !");
						txtPL.setForeground(Color.GREEN);
						noOfGoodAns = noOfGoodAns + 1;

					} else {
						txtPL.setText(resultSelect + "  - TRY HARDER!");
						txtPL.setForeground(Color.RED);
						// below code lets move words to next line if we have
						// more than 15 words
						if (noOfBadAns == 15) {
							wrongAnswers = wrongAnswers + sqlForApp.selectWord(list, "engWord", iterator) + ",<br>";
						} else {
							wrongAnswers = wrongAnswers + sqlForApp.selectWord(list, "engWord", iterator) + ", ";
						}
						System.out.println(wrongAnswers + " -to są złe odpowiedzi");
					}
					System.out.println(noOfGoodAns + " -liczba dobrych odpowiedzi");
				}
			}
		});
		// DELETE///////////////////////////////////
		btnDelete = new JButton("");
		btnDelete.setIcon(new ImageIcon("C:\\Users\\Rafał\\EngAppDesktop\\EngApp\\buttons\\button (3).png"));
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
		btnDelete.setBounds(69, 155, 81, 38);
		contentPane.add(btnDelete);

		// ADD /////////////////////////////////////////////////
		btnAdd = new JButton("");
		btnAdd.setIcon(new ImageIcon("C:\\Users\\Rafał\\EngAppDesktop\\EngApp\\buttons\\button (2).png"));
		btnAdd.setBounds(69, 101, 81, 38);
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
		btnClearList = new JButton("");
		btnClearList.setIcon(new ImageIcon("C:\\Users\\Rafał\\EngAppDesktop\\EngApp\\buttons\\button (8).png"));
		btnClearList.setBounds(304, 16, 120, 30);
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
		lblNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNumber.setBounds(374, 221, 50, 25);
		contentPane.add(lblNumber);

		prompt = new JLabel("");
		prompt.setBounds(94, 0, 150, 14);
		contentPane.add(prompt);

	}

	// the method for choosing table from scroll list in menu bar
	public void itemStateChanged(ItemEvent e) {
		if (choice.getSelectedItem().equals("1")) {
			System.out.println("Ustawiono: list1");
			list = "list1";
			// setting label, volume and iterator for new list
			iterator = 0;
			volume = sqlForApp.countWords(list);
			String label = iterator + "/" + volume;
			lblNumber.setText(label);
			prompt.setText("");
		}
		if (choice.getSelectedItem().equals("2")) {
			System.out.println("Ustawiono: list2");
			list = "list2";
			// setting label, volume and iterator for new list
			iterator = 0;
			volume = sqlForApp.countWords(list);
			String label = iterator + "/" + volume;
			lblNumber.setText(label);
			prompt.setText("");
		}
		if (choice.getSelectedItem().equals("3")) {
			System.out.println("Ustawiono: list3");
			list = "list3";
			// setting label, volume and iterator for new list
			iterator = 0;
			volume = sqlForApp.countWords(list);
			String label = iterator + "/" + volume;
			lblNumber.setText(label);
			prompt.setText("");
		}
		if (choice.getSelectedItem().equals("4")) {
			System.out.println("Ustawiono: list4");
			list = "list4";
			// setting label, volume and iterator for new list
			iterator = 0;
			volume = sqlForApp.countWords(list);
			String label = iterator + "/" + volume;
			lblNumber.setText(label);
			prompt.setText("");
		}
		if (choice.getSelectedItem().equals("5")) {
			System.out.println("Ustawiono: list5");
			list = "list5";
			// setting label, volume and iterator for new list
			iterator = 0;
			volume = sqlForApp.countWords(list);
			String label = iterator + "/" + volume;
			lblNumber.setText(label);
			prompt.setText("");
		}

	}
}
