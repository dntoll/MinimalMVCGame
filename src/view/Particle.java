package view;

import javax.media.opengl.GLAutoDrawable;

public class Particle {
	float x = 100;
	float y= 100;
	float vx = 0;
	float vy = 0;
	float ax = 0;
	float ay = 1000;
	float size = 5;
	float lifeTime = 0;
	
	
	public Particle(float x, float y, float vx, float vy) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
	}
	
	public void draw(GLAutoDrawable drawable, Core core) {
		float color[] = new float[4];
		color[0] = color[1] = color[2] = 1;
		
			
		color[3]  = 0.5f - lifeTime;
		core.drawBall(drawable, x, y, size, size, color);
	}
	
	
	public void update(float timeElapsed) {
		//Integrate speed with acceleration
		vx = vx + timeElapsed * ax;
		vy = vy + timeElapsed * ay;
		
		//integrate position by speed
		x = x + timeElapsed * vx;
		y = y + timeElapsed * vy;
		
		//
		size += timeElapsed * 5.0f;
		lifeTime += timeElapsed;
		if (lifeTime > 0.5f)
			lifeTime = 0.5f;
	}
}
