package pl.com.pattern.less.EngApp;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class E2 extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextArea txtrEnglish;
	private JTextField textField;
	private JButton btnNext;
	private JButton btnCheck;
	private ArrayList<String> listPL;
	private ArrayList<String> listENG;
	private int iterator= 0;
	
	public void createList(){
//lista angielskich słowek
		listENG = new ArrayList<String>();
	    listENG.add("dog");
	    listENG.add("cat");
	    listENG.add("bird");
//lista polskich slowek	
	listPL = new ArrayList<String>();
     listPL.add("pies");
     listPL.add("kot");
     listPL.add("ptak");
	
	
	
	}
	//the method for debuging
	public void log(String a){
		System.out.println(a);
	}
	
	public E2() {
		createList();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
//btn toE1		
		JButton toE1 = new JButton("toE1");
		toE1.addActionListener(this);
		toE1.setBounds(10, 203, 76, 47);
		contentPane.add(toE1);
//JTextField(		
		textField = new JTextField();
		textField.setBounds(69, 57, 207, 20);
		contentPane.add(textField);
		textField.setColumns(10);
//JTextArea		
		txtrEnglish = new JTextArea();
		txtrEnglish.setText("english");
		txtrEnglish.setBounds(69, 24, 207, 20);
		contentPane.add(txtrEnglish);
//btnNext		
		btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtrEnglish.setText(listENG.get(iterator));
				if (iterator<2){
					iterator++;
				}
				else 
					iterator=0;
			}
		});
		btnNext.setBounds(299, 97, 89, 47);
		contentPane.add(btnNext);
//btnCheck		
		btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtrEnglish.setText("dog");
			}
		});
		btnCheck.setBounds(299, 155, 89, 45);
		contentPane.add(btnCheck);
	}

	public void actionPerformed(ActionEvent arg0) {
		
		E1.frame2.setVisible(false);
		E1.frame1.setVisible(true);
		log("powrot do pierwszego ekranu");
	}
}
