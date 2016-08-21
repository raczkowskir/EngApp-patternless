package pl.com.pattern.less.EngApp;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class E2 extends JFrame {

	private JPanel contentPane;
	private JTextArea txtENG;
	private JTextArea txtPL;
	///////////////////////////
	private JButton btnNext;
	private JButton btnDelete;
	private JButton btnAdd;
	private JButton btnCheck;
	private JButton btnClearList;
	// label which is showing index of current position in list (+1)and total
	// volume
	private JLabel lblNumber;
	// iterator which is pointing next position on a list
	private int iterator = 0;
	// variable which is showing total volume of current table
	private int volume = 0;
	// object of SQLite data base - it will be store all data for this
	// application
	SQLforApp sqlForApp = new SQLforApp();

	// the constructor of class E2
	public E2() {
		// settings of frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 80, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// creating tables
		sqlForApp.createTables();
		System.out.println("utworzono tabele");

		// the method which is counting total number of rows for current table
		volume = sqlForApp.countWords("list1");
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
		// JtxtPL(
		txtPL = new JTextArea();
		txtPL.setBounds(69, 57, 207, 20);
		contentPane.add(txtPL);
		txtPL.setColumns(10);
		// NEXT///////////////////////////////////
		btnNext = new JButton("Next");
		btnNext.setBounds(294, 97, 89, 47);
		contentPane.add(btnNext);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// moving to the next row in the table

				iterator++;
				System.out.println("Przejscie do nastepnej pozycji w tabeli: " + iterator);
				/*
				 * if (sqlForApp.selectWord("list1", "engWord",
				 * iterator).equals("") && iterator<10){
				 * System.out.println("''"); actionPerformed(arg0); }
				 */
				// moving to the next row in the table

				/*
				 * if (sqlForApp.selectWord("list1", "engWord",
				 * iterator).equals(null)){ System.out.println("null"); } if
				 * (sqlForApp.selectWord("list1", "engWord", iterator)==(null)){
				 * System.out.println("==null"); } if
				 * (sqlForApp.selectWord("list1", "engWord", iterator)==("")){
				 * System.out.println("==''"); } if (true){
				 * System.out.println("jasna cholera!!!!!!!!"); }
				 */

				String resultSelectENG = sqlForApp.selectWord("list1", "engWord", iterator);
				txtENG.setText(resultSelectENG);
				txtPL.setText("");

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
				iterator--;
				System.out.println("Przejscie do poprzedniej pozycji w tabeli: " + iterator);
				String resultSelectENG = sqlForApp.selectWord("list1", "engWord", iterator);
				System.out.println("to sie nie wyswietla " + resultSelectENG + " to sie wyswietla");
				txtENG.setText(resultSelectENG);
				txtPL.setText("");

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

				String resultSelect = sqlForApp.selectWord("list1", "plWord", iterator);
				System.out.println("Wyswietlenie tlumaczenia: " + resultSelect);
				txtPL.setText(resultSelect);
			}
		});
		// DELETE///////////////////////////////////
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sqlForApp.deleteWord("list1", iterator);
				System.out.println("Usunieto słowo z tabeli z pozycji: " + iterator);
				txtENG.setText(sqlForApp.selectWord("list1", "engWord", iterator));
				txtPL.setText("");
				// setting label which is showing current position in table
				volume--;
				String label = iterator + "/" + volume;
				lblNumber.setText(label);

				/////// I'm am not sure the below is necessary
				/////// //////////////////////
				sqlForApp.createTables();
			}
		});
		btnDelete.setBounds(69, 155, 89, 45);
		contentPane.add(btnDelete);

		// btn Add
		btnAdd = new JButton("Add");
		btnAdd.setBounds(69, 97, 89, 47);
		contentPane.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String StrTxtENG = txtENG.getText();
				String StrTxtPL = txtPL.getText();
				sqlForApp.insertWord("list1", StrTxtENG, StrTxtPL);
				System.out.println("dodano slowo do tabeli");
				txtENG.setText("");
				txtPL.setText("");

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
				sqlForApp.clearTable("list1");
				// setting label which is showing current position in table
				volume = 0;
				String label = iterator + "/" + volume;
				lblNumber.setText(label);
				txtENG.setText("");
				txtPL.setText("");
			}
		});

		/// counting volume
		volume = sqlForApp.countWords("list1");
		// labelNUMBER
		String label = iterator + "/" + volume;
		lblNumber = new JLabel(label);
		lblNumber.setBounds(400, 236, 24, 14);
		contentPane.add(lblNumber);
		////////////////////// znalezc klase kursor i metody rawQuery i
		////////////////////// moveToPosition//////////////

		JButton btnDrop = new JButton("Drop");
		btnDrop.setBounds(187, 215, 89, 23);
		contentPane.add(btnDrop);
		btnDrop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Cursor cursor = sqlForApp.rawQuery("SELECT * FROM Powtorki",
				// null);

			}
		});

	}
}
