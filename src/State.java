import java.util.ArrayList;

public class State {
	public final double PADDLE_HEIGHT = 0.2;
	public final int GRID_WIDTH = 12;
	public final int GRID_HEIGHT = 12;
	public int ballX, ballY, velocityX, velocityY, paddleY;
	public boolean badState = false;

	/**
	 * Constructor from other states
	 * 
	 * @param newBallX
	 * @param newBallY
	 * @param newVelocityX
	 * @param newVelocityY
	 * @param newPaddleY
	 */
	public State(int newBallX, int newBallY, int newVelocityX, int newVelocityY, int newPaddleY) {
		ballX = newBallX;
		ballY = newBallY;
		velocityX = newVelocityX;
		velocityY = newVelocityY;
		paddleY = newPaddleY;
	}

	/**
	 * Constructor for data from GUI
	 * 
	 * @param newBallX
	 * @param newBallY
	 * @param newVelocityX
	 * @param newVelocityY
	 * @param newPaddleY
	 */
	public State(double newBallX, double newBallY, double newVelocityX, double newVelocityY, double newPaddleY) {
		ballX = (int) Math.floor(newBallX * GRID_WIDTH);
		ballY = (int) Math.floor(newBallY * GRID_WIDTH);
		paddleY = (int) Math.floor(newPaddleY * GRID_WIDTH);

		velocityX = (int) (newVelocityX / Math.abs(newVelocityX));
		if (Math.abs(newVelocityY) < 0.015) {
			velocityY = 0;
		} else {
			velocityY = (int) (newVelocityY / Math.abs(newVelocityY));
		}

		if(newBallX > 1.0) {
			badState = true;
		}


	}

	public ArrayList<State> findNeighboringStates() {
		ArrayList<State> neighbors = new ArrayList<State>();

		int newBallX = ballX;
		int newBallY = ballY;
		int newVelocityY = velocityY;
		for (int paddleDiff = -1; paddleDiff < 2; paddleDiff++) {
			int newPaddleY = paddleY + paddleDiff;
			if(newPaddleY >= 0 && newPaddleY < 12) {
				neighbors.add(new State(newBallX, newBallY, velocityX, newVelocityY, newPaddleY));

			}
		}
		return neighbors;

	}

	public String toString() {
		return "State (" + ballX + ", " + ballY + ", " + velocityX + ", " + velocityY + ", " + paddleY + ")";
	}

	public boolean isGoalState() {
		if(ballX == 11 && ballY == paddleY) {
			return true;
		}
		return false;
	}

	public int getInt() {
		if(badState) {
			return -1;
		}

		int returnValue = 0;
		returnValue += paddleY;
		returnValue += (velocityY + 1)*100;
		returnValue += (velocityX + 1)*1000;
		returnValue += ballY * 10000;
		returnValue += ballX * 1000000;
		return returnValue;
	}

}