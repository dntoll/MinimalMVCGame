package model;



public class Model {
	
	public Level level = new Level();
	public Ball ball = new Ball();
	public Pad pad = new Pad();

	public void update(float timeElapsed, IBallObserver observer) {
		
		//continue until no more collisions
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
		collideBricks(ballNewPos, collision);
		
		collision.handleCollision(timeElapsedSeconds, ball, ballNewPos, observer);
		
		
		
		if (collision.hasCollided(timeElapsedSeconds)) {
			
			Brick brickToRemove = collision.getBrick();
			
			level.removeBrick(brickToRemove);
			
			return timeElapsedSeconds - collision.getTime();
		}
		return 0.0f;
	}

	private void collideBricks(Vector2 ballNewPos, Collision collision) {
		for (int x = 0; x < level.width; x++) {
			for (int y = 0; y < level.height; y++) {
				Brick b = level.bricks[x][y];
				if (b.collides()) {
					collideBrick(x,y,ballNewPos, collision, b);
				}
			}
		}
	}

	private void collideBrick(int brickX, int brickY, Vector2 ballNewPos, 	Collision collision, Brick brick) {
		
		
		Vector2 topLeft = new Vector2(brickX, brickY);
		Vector2 topRight = new Vector2(brickX+1, brickY);
		Vector2 bottomRight = new Vector2(brickX+1, brickY+1);
		Vector2 bottomLeft = new Vector2(brickX, brickY+1);
		
		collideCorner(ballNewPos, collision, topLeft, brick);
		collideCorner(ballNewPos, collision, topRight, brick);
		collideCorner(ballNewPos, collision, bottomRight, brick);
		collideCorner(ballNewPos, collision, bottomLeft, brick);
		
		collideLine(ballNewPos, collision, topLeft.add(new Vector2(0, - ball.diameter/2)), 
										   topRight.add(new Vector2(0, - ball.diameter/2)),
										   new Vector2(0, -1), brick);
		
		collideLine(ballNewPos, collision, topRight.add(new Vector2(ball.diameter/2, 0)), 
											bottomRight.add(new Vector2(ball.diameter/2, 0)),
				   new Vector2(1, 0), brick);
		
		collideLine(ballNewPos, collision, bottomLeft.add(new Vector2(0, + ball.diameter/2)), 
				   bottomRight.add(new Vector2(0, + ball.diameter/2)),
				   new Vector2(0, 1), brick);

		collideLine(ballNewPos, collision, bottomLeft.add(new Vector2(-ball.diameter/2, 0)), 
					topLeft.add(new Vector2(-ball.diameter/2, 0)),
					new Vector2(-1, 0), brick);
	}

	private void collideLine(Vector2 ballNewPos, Collision collision,
			Vector2 lineStart, Vector2 lineEnd, Vector2 normal, Brick brick) {
		Vector2 ballStartPos = new Vector2(ball.centerX, ball.centerY);
		Vector2 ballEndPos = ballNewPos;
		
		try {
			Vector2 collideAt = Vector2.lineIntersectionPoint(ballStartPos, ballEndPos, lineStart, lineEnd);
			
			Vector2 lineVector = lineEnd.sub(lineStart);
			float lineLength = lineVector.length();
			
			Vector2 toCollisionPoint = collideAt.sub(lineStart);
			
			float projection = Vector2.dot(lineVector, toCollisionPoint);
			
			if (projection <= lineLength && projection >= 0) {
			
				float ballMovedDistance = collideAt.sub(ballStartPos).length();
				float ballSpeed = ball.getSpeed().length();
				float timeForCollision = ballMovedDistance / ballSpeed;
				
				collision.setCollision(timeForCollision, normal, ball.getSpeed(), brick);
			}
			
		} catch (Exception e) {
			return;
		}
		
		
		
	}

	private void collideCorner(Vector2 ballNewPos, Collision collision, Vector2 corner, Brick brick) {
		Vector2 collideAt = new Vector2(0,0);
		
		
		Vector2 ballStartPos = new Vector2(ball.centerX, ball.centerY);
		Vector2 ballEndPos = ballNewPos;
		float distanceToTopLeft = corner.distanceToLine(ballStartPos, ballEndPos, collideAt);
		if (distanceToTopLeft < ball.diameter / 2.0f ) {
			float ballMovedDistance = collideAt.sub(ballStartPos).length();
			float ballSpeed = ball.getSpeed().length();
			float timeForCollision = ballMovedDistance / ballSpeed;
			Vector2 normal = collideAt.sub(corner);
			normal.normalize();
			
			collision.setCollision(timeForCollision, normal, ball.getSpeed(), brick);
		}
	}

	private void collideWalls(Vector2 ballNewPos, Collision collision) {
		//RIGHT WALL
		if (ballNewPos.x + ball.diameter/2 > level.width) {
			float rightWallBallCenterPosition = level.width - ball.diameter/2;
			float collisionTime = (rightWallBallCenterPosition - ball.centerX) / ball.speedX;
			float yNormal = 0.0f;
			if (Math.sqrt(ball.speedY * ball.speedY) < 0.5f) {
				yNormal = 0.4f;
			}
				
			Vector2 collisionNormal = new Vector2(-1.0f, yNormal);
			collisionNormal.normalize();
			collision.setCollision(collisionTime, collisionNormal, ball.getSpeed(), null);
		}
		
		//LEFT WALL
		if (ballNewPos.x - ball.diameter/2 < 0) {
			float leftWallBallCenterPosition = 0 + ball.diameter/2;
			float collisionTime = (leftWallBallCenterPosition - ball.centerX) / ball.speedX;
			Vector2 collisionNormal = new Vector2(1.0f, 0.0f);
			collision.setCollision(collisionTime, collisionNormal, ball.getSpeed(), null);
		}
		
		//TOP WALL
		if (ballNewPos.y - ball.diameter/2 < 0) {
			float topWallBallCenterPosition = 0 + ball.diameter/2;
			float collisionTime = (topWallBallCenterPosition - ball.centerY) / ball.speedY;
			Vector2 collisionNormal = new Vector2(0.0f, 1.0f);
			collision.setCollision(collisionTime, collisionNormal, ball.getSpeed(), null);
		}
	}

	private void collidePad(Vector2 ballNewPos, Collision collision) {
		if (ballNewPos.x > pad.centerX - pad.width/2 && 
			ballNewPos.x < pad.centerX + pad.width / 2 &&  
			ballNewPos.y + ball.diameter / 2 > pad.topY ) {
			
			float bottomPadBallCenterPosition = pad.topY - ball.diameter/2;
			float collisionTime = (bottomPadBallCenterPosition - ball.centerY) / ball.speedY;
			
			
			float collidePos = ballNewPos.x - pad.centerX;
			
			Vector2 collisionNormal = new Vector2(collidePos, -1.0f);
			collisionNormal.normalize();
			
			
			collision.setCollision(collisionTime, collisionNormal, ball.getSpeed(), null);
			
			
		}
	}

	public boolean isGameOver() {
		return level.hasNoBricks();
	}

	

}
