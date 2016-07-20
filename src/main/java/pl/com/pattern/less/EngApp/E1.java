package pl.com.pattern.less.EngApp;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class E1 extends JFrame implements ActionListener{
	
	//the method for debuging
		public void log(String a){
			System.out.println(a);
		}

	private JPanel contentPane;
	static final  E1 frame1 = new E1();
	static final E2 frame2= new E2();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				//	frame1 = new E1();
					frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public E1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton toE2 = new JButton("toE2");
		toE2.setBounds(52, 64, 155, 79);
		contentPane.add(toE2);
		toE2.addActionListener(this);
	}

	public void actionPerformed(ActionEvent arg0) {
		frame2.setVisible(true);
		frame1.setVisible(false);
		log("Przejscie do drugiego ekranu");
		}
		
	
}
