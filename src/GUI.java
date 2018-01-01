import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class GUI implements ActionListener {
	public Game game;

	public JFrame mainFrame;
	public JSlider speedSlider;
	public PongGUI gameGUI;
	private final int DISPLAY_WIDTH;					//overall window width
	private final int DISPLAY_HEIGHT;				//overall window height
	private final int HEADER_OFFSET;
	private final int SLIDER_OFFSET;
	private int gameCount = 0;
	private int sum = 0;
	private int speed = 30;
	private Timer timer;

	public GUI(int width, int height) {
		DISPLAY_WIDTH = width;
		DISPLAY_HEIGHT = height;
		HEADER_OFFSET = 18;
		SLIDER_OFFSET = 45;
		game = new Game();

		prepareGUI();

		timer = new Timer(speed, this);			// change the number to change fps, lower is faster
		timer.start();
	}

	/**
	 * Loaded once, sets up the JFrame and JPanel
	 */
	public void prepareGUI() {
		mainFrame = new JFrame("Pong");
		mainFrame.setSize(DISPLAY_WIDTH, DISPLAY_HEIGHT + HEADER_OFFSET + SLIDER_OFFSET);
		
		gameGUI = new PongGUI(game, DISPLAY_WIDTH, DISPLAY_HEIGHT);
		gameGUI.setPreferredSize(new Dimension(DISPLAY_WIDTH, DISPLAY_HEIGHT));

		speedSlider = new JSlider(JSlider.HORIZONTAL, 1, 60, speed);
		speedSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				speed = speedSlider.getValue();
				timer.setDelay(speed);
				timer.restart();	
			}
		});
		
		mainFrame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 0.9;
		c.gridx = 0;
		c.gridy = 0;
		mainFrame.add(gameGUI, c);
		
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 1;
		mainFrame.add(speedSlider, c);
		
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * Ran every time timer goes off
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {			//new frame
		game.move();
		if(game.gameOver) {
			game.respawn();
			game.gameOver = false;
			//			System.out.println("Game #" + gameCount + ": " + game.bounceCount + " bounces");
			sum += game.bounceCount;
			if(gameCount % 100 == 0) {
				System.out.println("Average bounces/game in last 100: " + sum/100.0);
				sum = 0;
			}

			game.bounceCount = 0;
			gameCount++;
		}
		game.paddle.move(game.ball);

		gameGUI.game = game;
		gameGUI.repaint();

	}
}