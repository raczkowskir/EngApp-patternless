package pl.com.pattern.less.EngApp;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class E3 extends JFrame {

	// This view have only one button - for coming back to first view
	// whole data displayed in this view came from Class E2
	// This view is used for showing statistics how many user answers was good

	private JButton toE1;
	private JPanel contentPane;
	private JLabel lblTotalNo;
	private JLabel lblGoodAnswers;
	private JLabel lblWrongWords;
	private JLabel lblWrongWordsList;
	private JLabel lblTotalNoValue;
	private JLabel lblGoodAnsValue;

	public E3() {
		setTitle("EngApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 80, 450, 319);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// btn toE1
		toE1 = new JButton("");
		toE1.setIcon(new ImageIcon("C:\\Users\\Rafał\\EngAppDesktop\\EngApp\\buttons\\button (1).png"));
		toE1.setBounds(34, 188, 76, 47);
		contentPane.add(toE1);
		toE1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				E1.frame4.setVisible(false);
				E1.frame1.setVisible(true);
				System.out.println("Powrot do pierwszego ekranu");
			}
		});

		// lblTotalNo
		lblTotalNo = new JLabel("Total number of answers:");
		lblTotalNo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalNo.setBounds(34, 25, 156, 14);
		contentPane.add(lblTotalNo);

		// lblGoodAnswers
		lblGoodAnswers = new JLabel("Good answers:");
		lblGoodAnswers.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGoodAnswers.setBounds(34, 63, 137, 14);
		contentPane.add(lblGoodAnswers);

		// lblWrongWords
		lblWrongWords = new JLabel("Words you typed wrongly:");
		lblWrongWords.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWrongWords.setBounds(34, 103, 156, 14);
		contentPane.add(lblWrongWords);

		// lblWrongWordsList
		lblWrongWordsList = new JLabel("No mistakes");
		lblWrongWordsList.setBounds(34, 128, 390, 49);
		contentPane.add(lblWrongWordsList);
		String wrongAnswers = "<html>" + E2.wrongAnswers + "</html>";
		lblWrongWordsList.setText(wrongAnswers);
		lblWrongWordsList.setForeground(Color.RED);

		// lblTotalNoValue
		lblTotalNoValue = new JLabel("0");
		lblTotalNoValue.setForeground(new Color(0, 0, 139));
		lblTotalNoValue.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTotalNoValue.setBounds(200, 15, 60, 30);
		contentPane.add(lblTotalNoValue);
		String noOfAnswers = E2.noOfAnswers + "";
		lblTotalNoValue.setText(noOfAnswers);

		// lblGoodAnsValue
		lblGoodAnsValue = new JLabel("0 (0%)");
		lblGoodAnsValue.setForeground(new Color(0, 128, 0));
		lblGoodAnsValue.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGoodAnsValue.setBounds(200, 53, 116, 30);
		contentPane.add(lblGoodAnsValue);
		double noOfGoodPercent = 0;
		if (E2.noOfAnswers != 0) {
			noOfGoodPercent = E2.noOfGoodAns * 100 / E2.noOfAnswers;
		}
		String noOfGoodAns = E2.noOfGoodAns + " (" + noOfGoodPercent + "%)";
		lblGoodAnsValue.setText(noOfGoodAns);

	}
}
