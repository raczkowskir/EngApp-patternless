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

public class E2 extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextArea txtENG;
	private JTextArea txtPL;
	private JButton btnNext;
	private JButton btnCheck;
	private ArrayList<String> listPL;
	private ArrayList<String> listENG;
	private int iterator = 0;
	private boolean deleted = false;

	// iterarator for checking
	private int iterator2 = 0;
	private JButton btnDelete;

	public void createList() {
		// list of english words
		listENG = new ArrayList<String>();
		listENG.add("dog");
		listENG.add("cat");
		listENG.add("bird");
		// list of polish words
		listPL = new ArrayList<String>();
		listPL.add("pies");
		listPL.add("kot");
		listPL.add("ptak");

	}

	// the method for debugging
	public void log(String a) {
		System.out.println(a);
	}

	public void log(int a) {
		System.out.println(a);
	}

	public E2() {
		createList();
		log(listENG.size());
		// txtENG.setText(listENG.get(iterator));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// btn toE1
		JButton toE1 = new JButton("toE1");
		toE1.addActionListener(this);
		toE1.setBounds(10, 203, 76, 47);
		contentPane.add(toE1);
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
				
				iterator2 = iterator;
				if (listENG.size() == 0) {
					txtENG.setText("empty list");
				} else {
					txtENG.setText(listENG.get(iterator));
					txtPL.setText("");

					if (iterator < listENG.size() - 1) {
						txtENG.setText(listENG.get(iterator));
						iterator++;
						deleted = false;
					}

					else {
						iterator = 0;
						deleted = false;
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
				if (listENG.size() == 0) {
					txtPL.setText("empty list");
				} else {
					txtPL.setText(listPL.get(iterator2));
				}
			}
		});
		btnCheck.setBounds(294, 155, 89, 45);
		contentPane.add(btnCheck);
		// btnDelete
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int size = listENG.size();
				if (listENG.size() == 0) {
					txtENG.setText("empty list");
				} else {
					if (deleted == false) {
						if (size != 0) {
							listENG.remove(iterator2);
							listPL.remove(iterator2);
							deleted = true;
							if (iterator ==listENG.size()){
								iterator=0;
								iterator2=0;
							}
							/*
							 * for (String a : listENG) { log(a); }
							 * log(listENG.size()); log(listENG.get(iterator2));
							 */
							txtENG.setText("");
							txtPL.setText("");
						} else {
							txtENG.setText("empty list");
							txtPL.setText("empty list");
						}
						
					}else
					{
						txtENG.setText("Deleted before");
					}
				}

			}
		});
		btnDelete.setBounds(106, 155, 89, 45);
		contentPane.add(btnDelete);

	}

	public void actionPerformed(ActionEvent arg0) {

		E1.frame2.setVisible(false);
		E1.frame1.setVisible(true);
		log("powrot do pierwszego ekranu");
	}
}
