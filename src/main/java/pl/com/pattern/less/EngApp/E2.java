package pl.com.pattern.less.EngApp;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class E2 extends JFrame {

	private JPanel contentPane;
	private JTextArea txtENG;
	private JTextArea txtPL;
	private JButton btnNext;
	private JButton btnCheck;
	private ArrayList<String> listPL;
	private ArrayList<String> listENG;
	private boolean deleted = false;
	private boolean added = false;
	// variables usefull when we add/delete/check position
	// and we do not want receive false result because of trashes in text field
	private boolean screenTrash = false;
	private boolean screenTrash2 = false;
	private int nextBack = 0;
	
	// iterator which is pointing next position on a list
	private int iterator = 0;
	// iterator for checking - it is pointing on current position in our list
	private int iterator2 = 0;

	private JButton btnDelete;
	private JButton btnAdd;
	// label which is showing index of current position in list (+1)
	private JLabel lblNumber;

	// method usefull on stage of lack implementation of SQLite in project
	public void createList() {
		// list of english words
		listENG = new ArrayList<String>();
		listENG.add("1dog");
		listENG.add("2cat");
		listENG.add("3bird");
		listENG.add("4monkey");
		listENG.add("5elephant");
		listENG.add("6stork");
		// list of polish words
		listPL = new ArrayList<String>();
		listPL.add("1pies");
		listPL.add("2kot");
		listPL.add("3ptak");
		listPL.add("4małpa");
		listPL.add("5słoń");
		listPL.add("6bocian");

	}

	// the methods for debugging
	public void log(String a) {
		System.out.println(a);
	}

	public void log(int a) {
		System.out.println(a);
	}

	public void log(boolean a) {
		System.out.println(a);
	}

	// the constructor of class E2
	public E2() {
		createList();
		log("Wielkość listy: " + listENG.size());
		// settings of frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// all used components of frame
		// btn toE1
		JButton toE1 = new JButton("toE1");
		toE1.setBounds(10, 203, 76, 47);
		contentPane.add(toE1);
		toE1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				E1.frame2.setVisible(false);
				E1.frame1.setVisible(true);
				log("Powrot do pierwszego ekranu");
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
		// btnNext
		btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				log("Przejscie do nastepnej pozycji na liscie");
				
				log("iterator");
				log(iterator);
				
				log("iterator2");
				log(iterator2);
				
				
				iterator2 = iterator;
				if (listENG.size() == 0) {
					txtENG.setText("empty list");
					lblNumber.setText("?");
					log("lista pusta");
				} else {
					txtENG.setText(listENG.get(iterator));
					txtPL.setText("");
					lblNumber.setText((iterator + 1) + " / " + listENG.size());

					if (iterator < listENG.size() - 1) {
						if (iterator > 0) {
							if(nextBack == 0){
								
							txtENG.setText(listENG.get(iterator));
							nextBack =(1);
							}
							if(nextBack == (-1)){
								iterator=iterator+2;
							txtENG.setText(listENG.get(iterator));
							nextBack =(1);
							}
							if(nextBack == (1)){							
							txtENG.setText(listENG.get(iterator));
							nextBack =(-1);
							}}
						iterator++;
						deleted = false;
						added = false;
						
						log("iterator");
						log(iterator);
					}

					else {
						iterator = 0;
						deleted = false;
						added = false;
					}

				}
			}
		});
		btnNext.setBounds(294, 97, 89, 47);
		contentPane.add(btnNext);
		// btnCheck
		btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtENG.getText().equalsIgnoreCase("") || txtENG.getText().equalsIgnoreCase("Added before")
						|| txtENG.getText().equalsIgnoreCase("deleteded before")) {
					screenTrash2 = true;
				} else {
					screenTrash2 = false;
				}

				if (listENG.size() == 0) {
					txtPL.setText("empty list");
					log("Lista jest pusta");
				} else if (screenTrash2 == true) {
					txtPL.setText("");
					
				} else {
					txtPL.setText(listPL.get(iterator2));
					log("wyswietlono tlumaczenie");
				}
			}
		});
		btnCheck.setBounds(294, 155, 89, 45);
		contentPane.add(btnCheck);
		// btnDelete
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtPL.getText().equalsIgnoreCase("") || txtENG.getText().equalsIgnoreCase("") || txtENG.getText().equalsIgnoreCase("added before")
						|| txtENG.getText().equalsIgnoreCase("deleteded before")) {
					screenTrash = true;
				} else {
					screenTrash = false;
				}
				int size = listENG.size();
				if (listENG.size() == 0) {
					txtENG.setText("empty list");
					log("lista jest pusta");
				} else {
					if (deleted == false && screenTrash == false) {
						if (size != 0) {
							log("Usunieto: "+ txtPL.getText());
							listENG.remove(iterator2);
							listPL.remove(iterator2);
							deleted = true;
							if (iterator == listENG.size()) {
								iterator = 0;
								iterator2 = 0;
							}
							txtENG.setText("");
							txtPL.setText("");
							
						} else {
							txtENG.setText("error");
							txtPL.setText("error");
						}

					} else {
						txtENG.setText("Deleted before");
						txtPL.setText("");
					}
				}

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
				if (txtPL.getText().equalsIgnoreCase("") || txtENG.getText().equalsIgnoreCase("") || txtENG.getText().equalsIgnoreCase("added before")
						|| txtENG.getText().equalsIgnoreCase("deleteded before")) {
					screenTrash = true;
				} else {
					screenTrash = false;
				}
				if (added == false && screenTrash == false) {
					log("Dodano do listy: " + txtPL.getText());
					listENG.add(txtENG.getText());
					listPL.add(txtPL.getText());
					added = true;
				} else {
					txtENG.setText("Added before");
					txtPL.setText("");
				}
			}
		});

		// labelNUMBER
		lblNumber = new JLabel("0 / " + listENG.size());
		lblNumber.setBounds(400, 236, 24, 14);
		contentPane.add(lblNumber);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(187, 97, 89, 47);
		contentPane.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				log("Przejscie do poprzedniej pozycji na liscie");
				log("iterator");
				log(iterator);
				
				log("iterator2");
				log(iterator2);
				
				iterator2 = iterator;
				if (listENG.size() == 0) {
					txtENG.setText("empty list");
					lblNumber.setText("?");
					log("lista pusta");
				} else {
					txtENG.setText(listENG.get(iterator));
					txtPL.setText("");
					lblNumber.setText((iterator + 1) + " / " + listENG.size());

					if (iterator > 0) {
						if(nextBack == 0){
							iterator=listENG.size()-1;
						txtENG.setText(listENG.get(iterator));
						nextBack =(-1);
						}
						if(nextBack == 1){
							iterator=iterator-2;
						txtENG.setText(listENG.get(iterator));
						nextBack =(-1);
						}
						if(nextBack == (-1)){							
						txtENG.setText(listENG.get(iterator));
						nextBack =(-1);
						}
						iterator--;
						deleted = false;
						added = false;
						log("iterator");
						log(iterator);
					}

					else {
						iterator = listENG.size() - 1;
						deleted = false;
						added = false;
						log("iterator");
						log(iterator);
					}

				}
			}
		});
	}
}
