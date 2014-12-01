package model;



public class Model {
	
	public Level level = new Level();
	public Ball ball = new Ball();
	public Pad pad = new Pad();

	public void update(float timeElapsed, IBallObserver observer) {
		
		while (timeElapsed > 0) {
			Vector2 newPos = ball.getNewPosition(timeElapsed);
			timeElapsed = collide(timeElapsed, newPos, observer);
		}
		//BOTTOM
		if (ball.centerY + ball.diameter/2 > level.height) {
			ball = new Ball();
			level = new Level();
		}
			
		
		
	}
	
	public float collide(float timeElapsedSeconds, Vector2 ballNewPos, IBallObserver observer) {
		Collision collision = new Collision();
		
		collideWalls(ballNewPos, collision);
		collidePad(ballNewPos, collision);
		collision.handleCollision(ball, ballNewPos, observer);
		
		if (collision.hasCollided()) {
			return timeElapsedSeconds - collision.getTime();
		}
		return timeElapsedSeconds;
	}

	private void collideWalls(Vector2 ballNewPos, Collision collision) {
		//RIGHT WALL
		if (ballNewPos.x + ball.diameter/2 > level.width) {
			float rightWallBallCenterPosition = level.width - ball.diameter/2;
			float collisionTime = (rightWallBallCenterPosition - ball.centerX) / ball.speedX;
			Vector2 collisionNormal = new Vector2(-1.0f, 0.0f);
			collision.setCollision(collisionTime, collisionNormal);
		}
		
		//LEFT WALL
		if (ballNewPos.x - ball.diameter/2 < 0) {
			float leftWallBallCenterPosition = 0 + ball.diameter/2;
			float collisionTime = (leftWallBallCenterPosition - ball.centerX) / ball.speedX;
			Vector2 collisionNormal = new Vector2(1.0f, 0.0f);
			collision.setCollision(collisionTime, collisionNormal);
		}
		
		//TOP WALL
		if (ballNewPos.y - ball.diameter/2 < 0) {
			float topWallBallCenterPosition = 0 + ball.diameter/2;
			float collisionTime = (topWallBallCenterPosition - ball.centerY) / ball.speedY;
			Vector2 collisionNormal = new Vector2(0.0f, 1.0f);
			collision.setCollision(collisionTime, collisionNormal);
		}
	}

	private void collidePad(Vector2 ballNewPos, Collision collision) {
		if (ballNewPos.x > pad.centerX - pad.width/2 && 
			ballNewPos.x < pad.centerX + pad.width / 2 &&  
			ballNewPos.y + ball.diameter / 2 > pad.topY ) {
			
			float bottomPadBallCenterPosition = pad.topY - ball.diameter/2;
			float collisionTime = (bottomPadBallCenterPosition - ball.centerY) / ball.speedY;
			Vector2 collisionNormal = new Vector2(0.0f, -1.0f);
			collision.setCollision(collisionTime, collisionNormal);
			
			
		}
	}

	

}
