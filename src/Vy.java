import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File; 

public class Vy extends JFrame{
	private Controller controller;
	private JPanel panelGameBoard, panelScoreBoard, panelLogoBoard;
	private JLabel playerOLabel, turnLabel, playerXLabel, logoLabel;
	private JTicButton[] buttons;
	private ImageIcon icon;
	private final int padding = 10;
	
	public Vy () {
		JTicButton[] buttons = new JTicButton[9];
		controller = new Controller(this);
		panelScoreBoard = new JPanel();
		panelGameBoard = new JPanel();
		panelLogoBoard = new JPanel(); 
		
		// Kod inspirerad från: https://www.delftstack.com/howto/java/java-resize-image/
		try {
			BufferedImage bufferedImage = ImageIO.read(new File("logo.png"));
			Image image = bufferedImage.getScaledInstance(60, 40, Image.SCALE_SMOOTH);
			icon = new ImageIcon(image);
		} catch(Exception e) {
			// GÖR NÅGOT
		}
		
		logoLabel = new JLabel();
		logoLabel.setIcon(icon);
		
		panelScoreBoard.setLayout(new BorderLayout());
		panelScoreBoard.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
		panelGameBoard.setLayout(new GridLayout(3, 3));
		panelLogoBoard.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
		
		playerOLabel = new JLabel("Player O Score:");
		turnLabel = new JLabel("It's O's turn!", SwingConstants.CENTER);
		playerXLabel = new JLabel("Player X Score:");
		for(int i = 0;i < 9;i++) {
			buttons[i] = new JTicButton("Button " + (i + 1));
			panelGameBoard.add(buttons[i]);
		}
		
		panelScoreBoard.add(playerOLabel, BorderLayout.WEST);
		panelScoreBoard.add(turnLabel, BorderLayout.CENTER);
		panelScoreBoard.add(playerXLabel, BorderLayout.EAST);
		panelLogoBoard.add(logoLabel);
		
		this.setLayout(new BorderLayout()); // Sätter BorderLayout på framen
		this.add(panelScoreBoard, BorderLayout.NORTH);
		this.add(panelGameBoard, BorderLayout.CENTER);
		this.add(panelLogoBoard, BorderLayout.SOUTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(600, 600);
	}
}
