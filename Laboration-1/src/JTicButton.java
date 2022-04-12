import javax.swing.*;
import java.awt.*;

public class JTicButton extends JButton {
	private Controller c;
	private String s;
	
	public JTicButton (String s) {
		super(); 
		this.s = s;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		g2D.setColor(Color.blue);
		g2D.setFont(new Font("Verdana", Font.BOLD, 10));
		g2D.drawString(s, 70, 35);
	}
} 

