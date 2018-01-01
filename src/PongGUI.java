import java.awt.*;

import javax.swing.*;

public class PongGUI extends JPanel{
	public Game game;
	public ScaledGame sGame;
	public int width, height;
	
	public PongGUI(Game game, int width, int height) {
		super();
		this.game = game;
		this.width = width;
		this.height = height;
	}
	
	public void paint(Graphics g) {
		//recalculate
		this.sGame = new ScaledGame(game, width, height);
		
		//draw background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		//draw walls
		g.setColor(Color.GREEN);
		g.fillRect(sGame.leftWallX, sGame.leftWallY, 10, sGame.leftWallY + sGame.leftWallLength);
		g.fillRect(sGame.rightWallX, sGame.rightWallY, 10, sGame.rightWallY + sGame.rightWallLength);
		g.fillRect(sGame.topWallX, sGame.topWallY, sGame.topWallX + sGame.topWallLength, 10);
		g.fillRect(sGame.bottomWallX, sGame.bottomWallY - 10, sGame.bottomWallX + sGame.bottomWallLength, 10);
		
		//draw paddle
		g.setColor(Color.ORANGE);
		g.fillRect(sGame.paddleX - 5, sGame.paddleY, 5, sGame.paddleHeight);
		
		//draw ball
		g.setColor(Color.BLUE);
		g.fillOval(sGame.ballX, sGame.ballY, sGame.ballWidth, sGame.ballHeight);
		
	}
	
}