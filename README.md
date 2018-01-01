# Pong AI 

Functional game of Pong w/ GUI, then implemented Q-learning to train agent to play the game. Written for Assignment 4 of CS 440 (Artificial Intelligence). 

## Getting Started

Import the folder into Eclipse as existing project, run Main.java. Move slider to the left for faster fps (frames per second), to the right for slower fps. Over time, the paddle will learn and improve performance.

## Output

Every hundred trials, program will print the average # of bounces in the last hundred games. 

```
Average bounces/game in last 100: 0.01
Average bounces/game in last 100: 1.3
Average bounces/game in last 100: 1.53
...
Average bounces/game in last 100: 4.79
Average bounces/game in last 100: 5.78
Average bounces/game in last 100: 6.74
```


## Customizables

GUI size can be changed in Main.java

```
GUI gui = new GUI([WIDTH], [HEIGHT]);
```

Q-learning variables can be changed in Paddle.java

```
public double gamma = 0.8;
public double decay = 2.0;
```

Paddle speed can also be changed in Paddle.java constructor

```
this.paddleSpeed = 0.03;
```



## Implementations

* Q-learning algorithm
* Markov decision processes
* Java Swing Library

## Purpose
UIUC CS 440 Assignment 4
http://slazebni.cs.illinois.edu/fall17/assignment4.html

## Author
Elnathan Au (eau3)

