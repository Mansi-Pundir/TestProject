import java.awt.Frame;
import java.awt.Color;
import java.awt.event.*;
public class Demo1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame f=new Frame();
		  f.setVisible(true);
		  f.setSize(500,300);
		  f.setLocation(300,100);
		  f.setBackground(Color.blue);
		  f.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e){System.exit(0);}});
	}

}
