import javax.swing.*;
import java.awt.*;

public class JTicButton extends JButton {
	private String s;
	
	public JTicButton (String s) {
		super(); 
		this.s = s;
	}
	
	/* public void setPressed(String s) {
		this.setEnabled(false);
		this.setText(s);
		if(s.equals("X")) {
			this.setForeground(Color.blue);
		} else {
			this.setForeground(Color.red);
		}
	} */
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		/* g2D.setColor(Color.red);
		g2D.setFont(new Font("Verdana", Font.BOLD, 100));
		g2D.drawString(s, 70, 35); */
	}
} 

