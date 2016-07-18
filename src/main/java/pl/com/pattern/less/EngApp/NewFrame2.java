package pl.com.pattern.less.EngApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

//import statements here
public class NewFrame2 extends JFrame implements ActionListener
{
      /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//initialises the frame and opens it
	private static NewFrame2 INSTANCE;
     private NewFrame2()
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
	public static NewFrame2 instanceNewFrame2() {
		if (INSTANCE == null ){
  		  INSTANCE = new NewFrame2();
  	  }
  	  return INSTANCE;
	}
}