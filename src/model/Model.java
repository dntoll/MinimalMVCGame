package model;



public class Model {
	
	public Level level = new Level();
	public Ball ball = new Ball();
	public Pad pad = new Pad();

	public void update(float timeElapsed) {
		boolean gameOver = ball.update(timeElapsed, level, pad);
		
		if (gameOver) {
			ball = new Ball();
			level = new Level();
		}
	}

}
