import java.util.*;

public class Model {
	private boolean playerTurn;
	private String[] gameBoard;
	private String result;
	private int playerXScore;
	private int playerOScore;
	private int nbrOfDraws;

	public Model() {
		playerTurn = false;														// Boolesk variabel för vilken spelares tur det är
		gameBoard = new String[9];												// Strängvektor som speglar spelbrädet för rättning
		playerXScore = 0;														// Variabel som håller spelare X poäng av typen int
		playerOScore = 0;														// Variabel som håller spelare O poäng av typen int
		result = null;															// Variabel som håller resultatet av spelomgången av typen String
		nbrOfDraws = 0;															// Variabel som håller antal drag i omgången av typen int
	}
	
	/**
	 * Ökar antal drag som gjorts under omgången. 
	 */
	public void setNbrOfDraws() {
		nbrOfDraws++;
	}
	
	/**
	 * Ökar antal poäng med ett för spelare X.
	 */
	public void setPlayerXScore() {
		playerXScore++;
	}
	
	/**
	 * Metod för att nå spelare X:s poäng.
	 * @return
	 * Spelare X:s poäng. 
	 */
	public int getPlayerXScore() {
		return playerXScore;
	}
	
	/**
	 * Ökar antal poäng med ett för spelare O.
	 */
	public void setPlayerOScore() {
		playerOScore++;
	}
	
	/**
	 * Metod för att nå spelare O:s poäng.
	 * @return
	 * Spelare O:s poäng.
	 */
	public int getPlayerOScore() {
		return playerOScore;
	}
	
	/**
	 * Metod som sätter speltur till true eller false. 
	 * False = O och true = X. 
	 */
	public void setCurrentPlayer() {
		if(!playerTurn) {
			playerTurn = true;
		} else {
			playerTurn = false;
		}
	}

	/**
	 * Metod för att nå aktuell spelare.
	 * @return
	 * Aktuell spelare.
	 */
	public boolean getCurrentPlayer() {
		return playerTurn;
	}
	
	public String getResult() {
		checkForWinner(); 
		return result;
	}

	/**
	 * Rättningsfunktion hämtad från tidigare grupprojekt i kursen ISGB15 som gjordes av Henrik Karlsson, Mateusz Weber och Felicia Strandberg.
	 */
	public void checkForWinner() {
		String a, b, c;
		int i;
		
		// Rättning horisontellt
		for(i = 0;i < 3; i++) {
			a = gameBoard[i*3];
			b = gameBoard[(i*3) + 1];
			c = gameBoard[(i*3) + 2];
			if(a == "" || b == "" || c == "") {
				result = null;
			}
			if(a == b && b == c) {
				if(a == "X") {
					result = "X";
					setPlayerXScore(); 
				}
				if(a == "O") {
					result = "O";
					setPlayerOScore(); 
				}	
			}
		}
		
		// Rättning vertikalt
		for(i = 0;i < 3; i++) {
			a = gameBoard[i];
			b = gameBoard[i+3];
			c = gameBoard[i+6];
			if(a == "" || b == "" || c == "") {
				result = null;
			}
			if(a == b && b == c) {
				if(a == "X") {
					result = "X";
					setPlayerXScore(); 
				}
				if(a == "O") {
					result = "O";
					setPlayerOScore(); 
				}	
			}
		}
		
		// Rättning diagonalt
		a = gameBoard[0];
		b = gameBoard[4];
		c = gameBoard[8];
		
		if(a == b && b == c && a!="") {
			if(a == "X") {
				result = "X";
				setPlayerXScore(); 
			}
			if(a == "O") {
				result = "O";
				setPlayerOScore(); 
			}
		}
		a = gameBoard[2];
		b = gameBoard[4];
		c = gameBoard[6];
		
		if(a == b && b == c && a!="") {
			if(a == "X") {
				result = "X";
				setPlayerXScore(); 
			}
			if(a == "O") {
				result = "O";
				setPlayerOScore(); 
			}
		}
		
		for(i = 0; i < gameBoard.length; i++) {
			if(gameBoard[i] != null && nbrOfDraws == 9) {
				result = "draw";
			}
		}
	}

	/**
	 * Metod som anropas när ett klick gjorts på spelplanen.
	 * Sätter aktuell spelare som som en post i vektorn GameBoard på den position som valts av spelaren i vektorn. Speglar spelplanen.  
	 * @param position
	 * Position är aktuell position på spelplanen som valts.
	 * @param currentPlayer
	 * CurrentPlayer är antingen X eller O.
	 */
	public void executeMove(int position, String currentPlayer) {
		gameBoard[position] = currentPlayer;
	}

	public void resetResult() {
		result = null;
		gameBoard = new String[9];
		nbrOfDraws = 0;
	}
}
