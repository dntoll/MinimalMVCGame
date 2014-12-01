package model;

public class Ball {
	public float centerX = 4;
	public float centerY = 4;
	public float diameter = 0.5f;
	
	public float speedX = -2.0f;
	public float speedY = -2.0f;
	
	public Vector2 getNewPosition(float timeElapsedSeconds) {

		float x = centerX + speedX * timeElapsedSeconds;
		float y = centerY + speedY * timeElapsedSeconds;
		
		
		return new Vector2(x, y);
	}
	
	
	
}
