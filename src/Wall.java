public class Wall {
	public double x, y, length;
	public int mode;			//0 for left wall, 1 for top wall, 2 for bottom wall, 3 for right "wall"
	
	public Wall(int mode, double x, double y, double length) {
		this.mode = mode;
		this.x = x;
		this.y = y;
		this.length = length;
	}
	
	/**
	 * Returns true if the ball hit the wall
	 * @param ball
	 * @return
	 */
	public boolean hitWall(Ball ball) {
		if(mode == 0) {
			if(ball.x - ball.radius <= this.x) {
				return true;
			}
		}
		else if(mode == 1) {
			if(ball.y - ball.radius <= this.y) {
				return true;
			}
		}
		else if(mode == 2) {
			if(ball.y + ball.radius >= this.y) {
				return true;
			}
		}
		else if(mode == 3) {
			if(ball.x + ball.radius >= this.x) {
				return true;
			}
		}
		
		return false;
	}
	
	
}