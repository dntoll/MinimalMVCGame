package model;

public class Ball {
	public float centerX = 4;
	public float centerY = 4;
	public float diameter = 0.5f;
	
	private float speedX = 1.0f;
	private float speedY = 10.0f;
	
	public boolean update(float timeElapsedSeconds, Level level, Pad pad) {
		centerX = centerX + speedX * timeElapsedSeconds;
		
		if (centerX + diameter/2 > level.width) {
			speedX = speedX * -1.0f;
		}
		if (centerX - diameter/2 < 0) {
			speedX = speedX * -1.0f;
		}
		
		centerY = centerY + speedY * timeElapsedSeconds;
		
		
		if (centerY - diameter/2 < 0) {
			speedY = speedY * -1.0f;
		}
		
		if (centerY + diameter/2 > level.height) {
			return true;
		}
		
		if (centerY + diameter/2 > level.height) {
			return true;
		}
		
		if (centerX > pad.centerX - pad.width/2 && 
			centerX < pad.centerX + pad.width / 2 &&  
			centerY + diameter / 2 > pad.topY ) {
			speedY = speedY * -1.0f;
		}
		
		return false;
	}
}
