import javax.swing.*;
import java.awt.event.*;

public class Controller implements ActionListener {
	private Vy v;
	private Model m;
	private String currentPlayer;
	
	public Controller(Vy vin) {
		v=vin;
		m = new Model(); 
		currentPlayer = "";
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Fått konceptuell hjälp av Cristoffer Gustavsson
		for(int i=0;i<9;i++) {
			if(Integer.parseInt(e.getActionCommand()) == i) {
				v.updateGameBoard(i, currentPlayer); 
				m.setCurrentPlayer();
				if(m.getCurrentPlayer()) {
					currentPlayer = "X";
					v.updateTurnLabelX(currentPlayer); 
				} else {
					currentPlayer = "O";
					v.updateTurnLabelO(currentPlayer); 
				}
			}
		}
	}
}
