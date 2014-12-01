package model;


public class Collision {
	private float collisionTime = Float.MAX_VALUE;
	private Vector2 collisionNormal = null;
	private boolean hasCollided = false;
	private Brick collidedBrick;
	
	void setCollision(float collisionTime, Vector2 collisionNormal, Vector2 ballSpeed, Brick brick) {
		
		float projection = Vector2.dot(ballSpeed, collisionNormal );
		if (projection > 0.0) {
			return;
		}
		
		hasCollided = true;
		if (this.collisionTime == collisionTime) {
			this.collisionNormal = this.collisionNormal.add(collisionNormal);
			this.collisionNormal.normalize();
			collidedBrick = brick;
			
		}
		if (this.collisionTime > collisionTime) {
			this.collisionTime = collisionTime;
			this.collisionNormal = collisionNormal;
			collidedBrick = brick;
		}
		
	}
	
	public boolean hasCollided(float elapsedTime) {
		return hasCollided && collisionTime <= elapsedTime;
	}
	
	public float getTime() {
		return collisionTime;
	}
	
	void handleCollision(float elapsedTime, Ball ball, Vector2 ballNewPos, IBallObserver observer) {
		if (hasCollided(elapsedTime)) {
			//Hantera collisionen
			Vector2 newPos = ball.getNewPosition(collisionTime);
			Vector2 ballVelocity = new Vector2(ball.speedX, ball.speedY);
			
			//Vector2 R = 2.0f * (-Vector2.dot(ballVelocity, normal)) * normal + ballVelocity;		
			
			Vector2 newDirection = ballVelocity.add(collisionNormal.mul(2.0f * (-Vector2.dot(ballVelocity, collisionNormal))));
			
			
			ball.centerX = newPos.x;
			ball.centerY = newPos.y;
			
			ball.speedX = newDirection.x;
			ball.speedY = newDirection.y;
					
			observer.doCollide(newPos.x, newPos.y);
			
			
				
		} else {
			ball.centerX = ballNewPos.x;
			ball.centerY = ballNewPos.y;
		}
	}

	public Brick getBrick() {
		return collidedBrick;
	}

	

	
}
