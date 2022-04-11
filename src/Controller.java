import javax.swing.*;
import java.awt.event.*;

public class Controller implements ActionListener {
	private Vy v;
	private Model m;
	
	public Controller(Vy vin) {
		v=vin;
		m = new Model(); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Test") {
			
		}
		
		if(e.getActionCommand() == "1") {
			
		}
		
	}
	
}
