package pl.com.pattern.less.EngApp;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class E1 extends JFrame implements ActionListener {

	// This is first view of the program which lets user to chose
	// between view E2 - browsing words
	// or view E2_b - adding entire lists to tables

	// the method for debuging
	public void log(String a) {
		System.out.println(a);
	}

	private JPanel contentPane;
	static final E1 frame1 = new E1();
	static E2 frame2 = new E2();
	static final E2_b frame3 = new E2_b();
	static E3 frame4 = new E3();

	// label
	private JLabel lblAdvise;

	// buttons
	private JButton toE2_b;
	private JButton toE2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public E1() {
		setTitle("EngApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 80, 450, 319);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// button toE2
		toE2 = new JButton("");
		toE2.setIcon(new ImageIcon("C:\\Users\\Rafał\\EngAppDesktop\\EngApp\\buttons\\button (16).png"));
		toE2.setBounds(50, 112, 141, 82);
		contentPane.add(toE2);
		toE2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame2 = new E2();
				frame2.setVisible(true);
				frame1.setVisible(false);
				log("Przejscie do drugiego ekranu");
			}
		});

		// lblAdvise
		lblAdvise = new JLabel(
				"<html>This is an application for learning languages.<br> It lets user create his own lists of words, browse them, translate by him self  and check how many of his tranlations was correct.<html>");
		lblAdvise.setBounds(55, 26, 341, 63);
		contentPane.add(lblAdvise);

		// button toE2_b
		toE2_b = new JButton("");
		toE2_b.setIcon(new ImageIcon("C:\\Users\\Rafał\\EngAppDesktop\\EngApp\\buttons\\button (15).png"));
		toE2_b.setBounds(247, 112, 141, 82);
		contentPane.add(toE2_b);
		toE2_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame3.setVisible(true);
				frame1.setVisible(false);
				log("Przejscie do ekranu do dodawania list");
			}
		});

	}

	public void actionPerformed(ActionEvent arg0) {
	}

}
