import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*; 

public class Vy extends JFrame {
	private Controller controller;
	private JPanel panelGameBoard, panelScoreBoard, panelLogoBoard;
	private JLabel playerOLabel, turnLabel, playerXLabel, logoLabel;
	private ArrayList<JTicButton> buttons;
	private ImageIcon icon;
	private final int padding = 10;
	
	// KOLLA KNAPPFÄRG MED CAJ OCH LÖS
	// Fråga om rättning på diagonalen 
	
	/**
	 * Konstruktorn för Vyn.
	 * Anropas när ett objekt av Vy instansieras.
	 */
	public Vy () {
		buttons = new ArrayList<JTicButton>();																// ArrayList som håller alla knappar på spelbrädet av typen JTicButton
		controller = new Controller(this);																	// Referens till controllern, skickar med this som är vyn (framen)
		panelScoreBoard = new JPanel();																		// Panel för att presentera poäng för spelarna och vilken spelares tur det är
		panelGameBoard = new JPanel();																		// Panel som håller spelbrädet och knapparna
		panelLogoBoard = new JPanel(); 																		// Panel som innehåller logotypen
		
		// Kod inspirerad från: https://www.delftstack.com/howto/java/java-resize-image/
		try {
			BufferedImage bufferedImage = ImageIO.read(new File("logo.png"));
			Image image = bufferedImage.getScaledInstance(60, 40, Image.SCALE_SMOOTH);
			icon = new ImageIcon(image);
		} catch(Exception e) {
			// GöR NåGOT
		}
		
		logoLabel = new JLabel();
		logoLabel.setIcon(icon);
		
		/****************************************************
		 * Layouts på panelerna
		 ****************************************************/
		panelScoreBoard.setLayout(new BorderLayout());
		panelScoreBoard.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
		panelGameBoard.setLayout(new GridLayout(3, 3));
		panelLogoBoard.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
		
		/****************************************************
		 * Instansiering av labels och JTicButtons
		 ****************************************************/
		playerOLabel = new JLabel("<html>Player <font color=red>O</font> Score: 0</html>");
		turnLabel = new JLabel("<html>It's <font color=red>O's</font> turn!</html>", SwingConstants.CENTER); 
		playerXLabel = new JLabel("<html>Player <font color=blue>X</font> Score: 0</html>");
		for(int i = 0;i < 9;i++) {
			buttons.add(new JTicButton("") {{ 
				setFocusPainted(false);
				setBackground(Color.WHITE);
			}});
			panelGameBoard.add(buttons.get(i));
			buttons.get(i).addActionListener(controller);
			buttons.get(i).setActionCommand(Integer.toString(i));
		}
		
		/****************************************************
		 * Lägger till alla komponenter i panelerna
		 ****************************************************/
		panelScoreBoard.add(playerOLabel, BorderLayout.WEST);
		panelScoreBoard.add(turnLabel, BorderLayout.CENTER);
		panelScoreBoard.add(playerXLabel, BorderLayout.EAST);
		panelLogoBoard.add(logoLabel);
		
		/****************************************************
		 * Layout på framen (this)
		 ****************************************************/
		this.setResizable(false);
		this.setLayout(new BorderLayout()); 
		this.add(panelScoreBoard, BorderLayout.NORTH);
		this.add(panelGameBoard, BorderLayout.CENTER);
		this.add(panelLogoBoard, BorderLayout.SOUTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(600, 600);
	}
	
	/**
	 * Metod som rensar spelplanen och tömmer all text i knapparna. 
	 */
	public void clearGameBoard() {
		for(int i = 0;i < 9;i++) {
			buttons.get(i).setText("");
			buttons.get(i).setEnabled(true);
		}
	}
	
	/**
	 * Metod som anropas när en vinnare har utsetts. 
	 * En dialogruta visas centrerat i framen med aktuell vinnare och metoden clearGameBoard anropas. 
	 * @param winner
	 * Winner är aktuell vinnare av omgången. 
	 */
	public void winnerDialog(String winner) {
		JOptionPane.showMessageDialog(this, winner + " vann!");
		clearGameBoard(); 
	}
	
	/**
	 * Metod som anropas när spelet är oavgjort. 
	 * En dialogruta visas centrerat i framen med text om att spelet är oavgjort. 
	 */
	public void drawDialog() {
		JOptionPane.showMessageDialog(this, "Spelet blev oavgjort!");
		clearGameBoard(); 
	}

	/**
	 * Metod som anropas när det är X:s tur att spela sin omgång.
	 * Labeln som visar vems tur det är uppdateras med spelare X.  
	 * @param currentPlayer
	 * CurrentPlayer är den aktuella spelaren, spelare X. 
	 */
	public void updateTurnLabelX(String currentPlayer) {
		turnLabel.setText("<html>It's<font color=blue> " + currentPlayer + "'s</font> turn!</html>");
	}

	/**
	 * Metod som anropas när det är O:s tur att spela sin omgång. 
	 * Labeln som visar vems tur det är uppdateras med spelare O.
	 * @param currentPlayer
	 * CurrentPlayer är den aktuella spelaren, spelare O. 
	 */
	public void updateTurnLabelO(String currentPlayer) {
		turnLabel.setText("<html>It's<font color=red> " + currentPlayer + "'s</font> turn!</html>");
	}

	/**
	 * Metod som anropas när spelare X vunnit och poängen räknas upp.
	 * Labeln som visar antal poäng för spelare X uppdateras.
	 * @param score
	 * Score är antal poäng för spelare X. 
	 */
	public void updatePlayerXLabel(int score) {
		playerXLabel.setText("<html>Player <font color=blue>X</font> Score: " + score +"</html>");
	}

	/**
	 * Metod som anropas när spelare O vunnit och poängen räknas upp.
	 * Labeln som visar antal poäng för spelare O uppdateras.
	 * @param score
	 * Score är antal poäng för spelare O.
	 */
	public void updatePlayerOLabel(int score) {
		playerOLabel.setText("<html>Player <font color=red>O</font> Score: " + score + "</html>");
	}

	/**
	 * 
	 * @param pressedButton
	 * @param currentPlayer
	 */
	public void updateGameBoardX(int pressedButton, String currentPlayer) {
		buttons.get(pressedButton).setEnabled(false);
		buttons.get(pressedButton).setText("<html><font size=50 color=blue>" + currentPlayer + "</font></html>");
	}

	/**
	 * 
	 * @param pressedButton
	 * @param currentPlayer
	 */
	public void updateGameBoardO(int pressedButton, String currentPlayer) {
		buttons.get(pressedButton).setEnabled(false);
		buttons.get(pressedButton).setText("<html><font size=50 color=red>" + currentPlayer + "</font></html>");
	}
}
