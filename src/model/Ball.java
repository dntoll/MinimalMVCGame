package model;

public class Ball {
	public float centerX = 1.5f;
	public float centerY = 1.5f;
	public float diameter = 0.5f;
	
	public float speedX = 2.9f;
	public float speedY = 1.1f;
	
	public Vector2 getNewPosition(float timeElapsedSeconds) {

		float x = centerX + speedX * timeElapsedSeconds;
		float y = centerY + speedY * timeElapsedSeconds;
		
		
		return new Vector2(x, y);
	}

	public Vector2 getSpeed() {
		return new Vector2(speedX, speedY);
	}
	
	
	
}
