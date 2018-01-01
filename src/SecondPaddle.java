
public class SecondPaddle {
	public double x;
	public double y;				//middle
	public double radius;		//using half-height to make it easier to set locations etc
	public double direction;		//-1 for down, 0 for stay, 1 for up
	public double paddleSpeed;
	
	public SecondPaddle(double x, double y, double radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.direction = 0;
		this.paddleSpeed = 0.02;

	}
	
	public boolean hitBall(Ball ball) {
		if(ball.x - ball.radius <= this.x) {
			if(ball.y > y - radius && ball.y < y + radius) {
				return true;
			}
		}

		return false;
	}
	
	public void move(Ball ball) {
		if(ball.y > y) {
			y += paddleSpeed;
		}
		else if(ball.y < y) {
			y -= paddleSpeed;
		}
		
		if(y - radius < 0.0) {
			y = radius;
		}
		else if(y + radius > 1.0) {
			y = 1.0 - radius;
		}

	}

}
