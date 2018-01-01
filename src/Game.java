public class Game {
	public Wall leftWall, topWall, bottomWall, rightWall;
	public Ball ball;
	public Paddle paddle;
	public boolean gameOver;
	public int bounceCount = 0;

	public Game() {
		//set up walls
		leftWall = new Wall(0, 0.0, 0.0, 1.0);
		topWall = new Wall(1, 0.0, 0.0, 1.0);
		bottomWall = new Wall(2, 0.0, 1.0, 1.0);
		rightWall = new Wall(3, 1.1, 0.0, 1.0);		//slightly past the actual right to avoid conflict with paddle

		//set up paddle
		paddle = new Paddle(1.0, 0.7, 0.1);

		//set up ball
		ball = new Ball(0.5, 0.5, 0.03, 0.03, 0.01);

		//set up rest of vars
		gameOver = false;
	}

	public void move() {
		//move ball
		double origX = ball.x;
		double origY = ball.y;
		
		ball.x += ball.xVel;
		ball.y += ball.yVel;

		//check if collision
		if(leftWall.hitWall(ball)) {
			//return ball back to original position
			ball.x = origX;
			ball.y = origY;

			//change direction
			ball.xVel = -ball.xVel;
		}
		else if(topWall.hitWall(ball) || bottomWall.hitWall(ball)) {
			ball.x = origX;
			ball.y = origY;
			
			ball.yVel = -ball.yVel;
		}
		else if(rightWall.hitWall(ball)) {
			gameOver = true;
			paddle.clearqueue(false);
		}
		else if(paddle.hitBall(ball)) {
			ball.x = origX;
			ball.y = origY;
			
			paddle.clearqueue(true);
			
			ball.xVel = -ball.xVel + (Math.random() * 0.03) - 0.015;			//random variance as stated in assignment page
			ball.yVel = -ball.yVel + (Math.random() * 0.06) - 0.03;
			
			//make sure it never gets too slow
			if(Math.abs(ball.xVel) < 0.03) {
				if(ball.xVel > 0) {
					ball.xVel = 0.03;
				}
				else {
					ball.xVel = -0.03;
				}
			}
			
			bounceCount++;
		}
		
		//  |velocity_x| < 1 and |velocity_y| < 1.
		if(ball.xVel >= 1.0) {
			ball.xVel = 1.0;
		}
		else if(ball.xVel <= -1.0) {
			ball.xVel = -1.0;
		}
		if(ball.yVel >= 1.0) {
			ball.yVel = 1.0;
		}
		else if(ball.yVel <= -1.0) {
			ball.yVel = -1.0;
		}
		
	}

	public void respawn() {
		
		//set up paddle
		paddle.x = 1.0;
		paddle.y = 0.5;

		//set up ball
		ball = new Ball(0.5, 0.5, 0.03, 0.03, 0.01);
	}
}