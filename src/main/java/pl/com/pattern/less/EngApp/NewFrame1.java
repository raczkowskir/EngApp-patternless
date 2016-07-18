package pl.com.pattern.less.EngApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class NewFrame1 extends JFrame implements ActionListener
{
        /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		//initialises the frame and opens it
        public NewFrame1()
        {
                JButton open = new JButton("New Window");
                open.addActionListener(this);
                add(open);
                setVisible(true);
        }

        public void actionPerformed(ActionEvent event)
        {
                //code for the new frame
        }
}