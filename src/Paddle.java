import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Paddle {
	public double x;
	public double y;				//middle
	public double radius;		//using half-height to make it easier to set locations etc
	public double direction;		//-1 for down, 0 for stay, 1 for up
	public double paddleSpeed;

	//ai variables
	public ArrayList<State> queue = new ArrayList<State>();
	public Map<Integer, Double> rewards = new HashMap<Integer, Double>();
	public Map<Integer, Double> Q = new HashMap<Integer, Double>();
	public Map<Integer, Integer> N = new HashMap<Integer, Integer>();
	public double gamma = 0.8;
	public double decay = 2.0;


	public Paddle(double x, double y, double radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.direction = 0;
		this.paddleSpeed = 0.03;

		queue.add(new State(0.5, 0.5, 0.03, 0.01, 0.5));

	}

	public boolean hitBall(Ball ball) {
		if(ball.x + ball.radius >= this.x) {
			if(ball.y > y - radius && ball.y < y + radius) {
				return true;
			}
		}

		return false;
	}

	public void move(Ball ball) {
		update(ball);

		if(direction == -1) {
			y -= paddleSpeed;
		}
		else if(direction == 1) {
			y += paddleSpeed;
		}

		if(y - radius < 0.0) {
			y = radius;
		}
		else if(y + radius > 1.0) {
			y = 1.0 - radius;
		}

	}

	public void update(Ball ball) {
		//get current state and neighboring ones
		State currentState = new State(ball.x, ball.y, ball.xVel, ball.yVel, this.y);
		ArrayList<State> nextStates = currentState.findNeighboringStates();

		if(nextStates != null) {
			//randomly select a neighbor
			State nextState = nextStates.get((int) (Math.random() * nextStates.size()));

			//get all neighbors of that state, find max Q value of all those actions
			ArrayList<State> nextNextStates = nextState.findNeighboringStates();
			double maxQ = Double.NEGATIVE_INFINITY;
			for(State state : nextNextStates) {
				double qValue = Q(nextState, state);
				if(qValue > maxQ) {
					maxQ = qValue;
				}
			}

			//set Q
			double qValue = Q(currentState, nextState) + decay/(decay + N(currentState, nextState)) * (reward(currentState, nextState) + gamma * maxQ - Q(currentState, nextState));
			setQ(currentState, nextState, qValue);

			//get max from arraylist
			Double maxValue = Double.NEGATIVE_INFINITY;
			int maxIndex = -1;
			for(int i = 0; i < nextStates.size(); i++) {
				double q = Q(currentState, nextStates.get(i));
				if(q > maxValue) {
					maxValue = q;
					maxIndex = i;
				}
				else if(q == maxValue) {				//randomize which one is selected
					if(Math.random() < 0.5) {
						maxIndex = i;
					}
				}
			}

			//get State of best move, add to queue
			nextState = nextStates.get(maxIndex);
			queue.add(nextState);
			setN(currentState, nextState, N(currentState, nextState) + 1);			//increment seen occurrences by 1

			//make move
			if(nextState.paddleY > currentState.paddleY) {
				direction = 1;
			}
			else if(nextState.paddleY < currentState.paddleY) {
				direction = -1;
			}
			else {
				direction = 0;
			}
		}

	}

	public void clearqueue(boolean hit) {
		//init var
		int add = 1;			//amount to add into rewards
		if(!hit) {
			add = -1;		//subtract if missed
		}
		
		State current = queue.remove(0);
		State next = queue.remove(0);
		updateReward(current, next, add);
		
		while(!queue.isEmpty()) {
			current = next;
			next = queue.remove(0);
			updateReward(current, next, add);
		}
		
		queue.add(new State(0.5, 0.5, 0.03, 0.01, 0.5));
	}


	public double reward(State currentState, State nextState) {
		int key = currentState.getInt() * 100000000 + nextState.getInt();
		Double value = rewards.get(key);
		if(value != null) {
			return value;
		}
		return 0;
	}

	public double Q(State currentState, State nextState) {
		int key = currentState.getInt() * 100000000 + nextState.getInt();
		Double value = Q.get(key);
		if(value != null) {
			return value;
		}
		return 0;

	}

	public void setQ(State currentState, State nextState, double newQ) {
		int key = currentState.getInt() * 100000000 + nextState.getInt();
		Q.put(key, newQ);

	}
	
	public int N(State currentState, State nextState) {
		int key = currentState.getInt() * 100000000 + nextState.getInt();
		Integer value = N.get(key);
		if(value != null) {
			return value;
		}
		return 0;

	}

	public void setN(State currentState, State nextState, int newQ) {
		int key = currentState.getInt() * 100000000 + nextState.getInt();
		N.put(key, newQ);

	}

	public void setReward(State currentState, State nextState, double newReward) {
		int key = currentState.getInt() * 100000000 + nextState.getInt();
		rewards.put(key, newReward);
	}

	public void updateReward(State currentState, State nextState, double change) {
		double reward = reward(currentState, nextState) + change;
		setReward(currentState, nextState, reward);

	}

}