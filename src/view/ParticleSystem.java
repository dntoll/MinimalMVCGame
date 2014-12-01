package view;

import java.util.Random;

import javax.media.opengl.GLAutoDrawable;

public class ParticleSystem {
	
	
	private Particle particles[];
	private int NUM_PARTICLES = 100;
	
	
	ParticleSystem(float x, float y) {
		particles = new Particle[NUM_PARTICLES];
		float startPosX = x;
		float startPosY = y;
		
		spawnNewSystem(startPosX, startPosY);
	}

	private void spawnNewSystem(float startPosX, float startPosY) {
		Random rand = new Random();
		for(int i = 0; i< NUM_PARTICLES; i++) {
			float vx = (rand.nextFloat() - 0.5f) * 1000;
			float vy = (rand.nextFloat() - 0.5f) * 1000;
			particles[i] = new Particle(startPosX, startPosY, vx, vy);
		}
	}

	public void updateAndDraw(GLAutoDrawable drawable, Core core, float timeElapsed) {
		update(timeElapsed);
		draw(drawable, core);
	}

	private void draw(GLAutoDrawable drawable, Core core) {
		for(int i = 0; i< NUM_PARTICLES; i++) {
			particles[i].draw(drawable, core);
		}
	}

	private void update(float timeElapsed) {
		for(int i = 0; i< NUM_PARTICLES; i++) {
			particles[i].update(timeElapsed);
		}
		
	}

}
