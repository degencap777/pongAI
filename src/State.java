import java.util.ArrayList;

public class State {
	public final double PADDLE_HEIGHT = 0.2;
	public final int GRID_WIDTH = 12;
	public final int GRID_HEIGHT = 12;
	public int ballX, ballY, velocityX, velocityY, paddleY;
	
	//to avoid errors, relocate
	int[][][][][][][][][][] rewards = new int[12][12][2][3][12][12][12][2][3][12];


	/**
	 * Constructor from other states
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
	 * @param newBallX
	 * @param newBallY
	 * @param newVelocityX
	 * @param newVelocityY
	 * @param newPaddleY
	 */
	public State(double newBallX, double newBallY, double newVelocityX, double newVelocityY, double newPaddleY) {
		ballX = (int) Math.floor(newBallX * GRID_WIDTH);
		ballY = (int) Math.floor(newBallY * GRID_WIDTH);

		velocityX = (int) (newVelocityX/Math.abs(newVelocityX));	
		if(Math.abs(newVelocityY) < 0.015) {
			velocityY = 0;
		}
		else {
			velocityY = (int) (newVelocityY/Math.abs(newVelocityY));	
		}

		if(newPaddleY == 1 - PADDLE_HEIGHT) {
			paddleY = 11;
		}
		else {
			paddleY = (int) Math.floor(12.0 * newPaddleY / (1 - PADDLE_HEIGHT));
		}

	}

	public ArrayList<State> findNeighboringStates() {
		ArrayList<State> neighbors = new ArrayList<State>();
		for(int xDiff = -1; xDiff < 2; xDiff++){
			for(int yDiff = -1; yDiff < 2; yDiff++) {
				for(int paddleDiff = -1; paddleDiff < 2; paddleDiff++) {
					int newBallX = ballX + xDiff;
					int newBallY = ballY + yDiff;
					int newPaddleY = paddleY + paddleDiff;
					//check this line, not sure if the bounds are correct
					if(newBallX > 0 && newBallX < GRID_WIDTH && newBallY > 0 && newBallY < GRID_HEIGHT && newPaddleY > 0 && newPaddleY < GRID_HEIGHT) {
						for(int newVelocityY = -1; newVelocityY < 2; newVelocityY++) {
							neighbors.add(new State(newBallX, newBallY, 1, newVelocityY, newPaddleY));
							neighbors.add(new State(newBallX, newBallY, -1, newVelocityY, newPaddleY));
						}
					}
				}
			}
		}

		return neighbors;

	}

	public int reward(State nextState) {
		return rewards[ballX][ballY][velocityX][velocityY][paddleY][nextState.ballX][nextState.ballY][nextState.velocityX][nextState.velocityY][nextState.paddleY];
	}


}
