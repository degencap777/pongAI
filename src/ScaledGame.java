public class ScaledGame {
	//wall vars
	public int leftWallX, leftWallY, leftWallLength;
	public int topWallX, topWallY, topWallLength;
	public int bottomWallX, bottomWallY, bottomWallLength;
	public int rightWallX, rightWallY, rightWallLength;
	
	//ball vars
	public int ballX, ballY, ballWidth, ballHeight;
	
	//paddle vars
	public int paddleX, paddleY, paddleHeight;
	
	//scale
	public int width, height;
	
	public ScaledGame(Game game, int width, int height) {
		this.width = width;
		this.height = height;

		leftWallX = (int) (game.leftWall.x * width);
		leftWallY = (int) (game.leftWall.y * height);
		leftWallLength = (int) (game.leftWall.length * height);
		
		rightWallX = (int) (game.rightWall.x * width);
		rightWallY = (int) (game.rightWall.y * height);
		rightWallLength = (int) (game.rightWall.length * height);
		
		topWallX = (int) (game.topWall.x * width);
		topWallY = (int) (game.topWall.y * height);
		topWallLength = (int) (game.topWall.length * width);
		
		bottomWallX = (int) (game.bottomWall.x * width);
		bottomWallY = (int) (game.bottomWall.y * height);
		bottomWallLength = (int) (game.bottomWall.length * width);

		ballX = (int) ((game.ball.x - game.ball.radius) * width);
		ballY = (int) ((game.ball.y - game.ball.radius) * height);
		ballWidth = (int) (game.ball.radius * width * 2);
		ballHeight = (int) (game.ball.radius * height * 2);
		
		paddleX = (int) (game.paddle.x * width);
		paddleY = (int) ((game.paddle.y - game.paddle.radius) * height);
		paddleHeight = (int) (game.paddle.radius * height * 2);
		
		
	}
}