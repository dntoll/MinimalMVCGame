package view;
import java.io.IOException;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLException;

import model.Ball;
import model.Level;
import model.Model;
import model.Pad;


public class View {
	private Model model;
	private Input input;
	private Camera camera;

	
	private Core core = new Core();

	public View(Model model, int width, int height, Input input) {
		this.model = model;
		this.input = input;
		camera = new Camera(model.level.width, model.level.height);
		camera.setDimensions(width, height);
		
	}

	public void render(GLAutoDrawable drawable) {
		try {
			core.loadResources();
		} catch (GLException | IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		core.clearScreen(drawable);
		
		drawLevel(drawable, model.level);
		drawBall(drawable, model.ball);
		drawPad(drawable, model.pad);
		
	}
	 
	 private void drawPad(GLAutoDrawable drawable, Pad pad) {
		 float vx = camera.toViewX(pad.centerX);
		 float vy = camera.toViewY(pad.topY);
		 float vPadSize = (pad.width * camera.getScale());
		 
		 core.drawQuad(drawable, vx - vPadSize / 2.0f, vy, vPadSize, camera.getScale() * 0.5f);
	}

	private void drawBall(GLAutoDrawable drawable, Ball ball) {
		 float vx = camera.toViewX(ball.centerX);
		 float vy = camera.toViewY(ball.centerY);
		 float vBallSize = (ball.diameter * camera.getScale());
		 
		 core.drawQuad(drawable, vx - vBallSize / 2.0f, vy - vBallSize / 2.0f, vBallSize, vBallSize);
	}

	private void drawLevel(GLAutoDrawable drawable, Level level) {
		
		
		for  (int x = 0; x < level.width; x++) {
			for (int y = 0; y < level.height; y++)  {
				float w = camera.getScale();
				float h = camera.getScale();
				if (level.bricks[x][y] instanceof model.FillBrick) {
					float vx = camera.toViewX(x);
					float vy = camera.toViewY(y);
					
					core.drawQuad(drawable, vx, vy, w, h);
				}
			}
		}

		//top left is 0, 0
			
		
	}

	

	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
		core.reshape(drawable, x, y, w, h);
		camera.setDimensions(w, h);    
	}

	public float getMouseXPos() {
		float viewMousePos = input.getMousePosX();
		return camera.toModelX(viewMousePos);
		
	}

}
