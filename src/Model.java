
public class Model {
	private boolean playerTurn;

	public Model() {
		playerTurn = false;
	}
	
	public void setCurrentPlayer() {
		if(!playerTurn) {
			playerTurn = true;
		} else {
			playerTurn = false;
		}
	}

	public boolean getCurrentPlayer() {
		return playerTurn;
	}

}
