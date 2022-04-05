import javax.swing.*;
import java.awt.*; 

public class Vy extends JFrame{
	private Controller c;
	private JButton myButton;
	private JTextField text;
	
	public Vy () {
		
		this.setLayout(new FlowLayout());
		c = new Controller(this);
		myButton = new JButton("Test");
		text = new JTextField();
		
		myButton.addActionListener(c);
		text.setPreferredSize(new Dimension(100, 20));
		this.add(myButton);
		this.add(text);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(200, 200);
	}
}
