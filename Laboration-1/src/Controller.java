import javax.swing.*;
import java.awt.event.*;

public class Controller implements ActionListener {
	private Vy v;
	private Model m;
	private String currentPlayer;
	private String winner;
	
	/**
	 * Konstruktorn. 
	 * Anropas när ett objekt av klassen Controller instansieras. 
	 * @param vin
	 * Vin är vyn som "skickar med sig själv" när objektet skapas. 
	 */
	public Controller(Vy vin) {
		v=vin;
		m = new Model(); 													// Referens till modellen som skapas i controllerns konstruktor
		currentPlayer = "O";												// CurrentPlayer är aktuell spelares tur
		winner = "";														// Winner är vinnaren
	}

	/**
	 * Metod som överlagras och lyssnar efter händelser i vyn. 
	 * Tar emot ett objekt av typen ActionEvent som innehåller data om händelsen. 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<9;i++) {												// Loop som körs 9 gånger där en kontroll görs för vilken knapp i spelbrädet som en användare har klickat på.
			if(Integer.parseInt(e.getActionCommand()) == i) { 				// Fått konceptuell hjälp av Cristoffer Gustavsson. 
				m.setCurrentPlayer();										// Använder referensen till modellen och sätter aktuell spelare
				m.executeMove(i, currentPlayer);							// Använder referensen till modellen och skickar med aktuell spelare och index i spelbrädet
				m.setNbrOfDraws(); 											// Använder referensen till modellen och räknar upp antal drag
				if(currentPlayer == "X") {
					v.updateGameBoardX(i, currentPlayer);					// Använder referensen till vyn och uppdaterar gränssnittet
				} else {
					v.updateGameBoardO(i, currentPlayer);
				}
				if(m.getCurrentPlayer()) {									// Om metoden getCurrentPlayer i modellen returnerar true sätts currentPlayer till X annars O
					currentPlayer = "X";
					v.updateTurnLabelX(currentPlayer); 
				} else {
					currentPlayer = "O";
					v.updateTurnLabelO(currentPlayer); 
				}
			}
		}
		winner = m.getResult();												// Strängvariabeln winner tar emot returen från getResult i modellen och kontrolleras med if-satser
		if(winner == "X") {
			v.updatePlayerXLabel(m.getPlayerXScore());
			v.winnerDialog(winner);
			m.resetResult(); 
		} else if(winner == "O") {
			v.updatePlayerOLabel(m.getPlayerOScore());
			v.winnerDialog(winner);
			m.resetResult(); 
		} else if(winner == "draw") {
			v.drawDialog();
			m.resetResult(); 
		} 
	}
}
