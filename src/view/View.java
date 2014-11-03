package view;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

import model.Model;


public class View {
	private Model model;
	private Input input;
	private int width;
	private int height;

	public View(Model model, int width, int height, Input input) {
		this.model = model;
		this.input = input;
		setDimensions(width, height);
	}
	
	 public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
    	 GL2 gl = drawable.getGL().getGL2();
         GLU glu = new GLU();
         
         gl.glMatrixMode(GL_PROJECTION);
         gl.glLoadIdentity(); // reset
         glu.gluOrtho2D (0.0, w, h, 0);  // define drawing area
         
         gl.glMatrixMode(GL_MODELVIEW);
         gl.glLoadIdentity(); // reset
         
         setDimensions(w, h);    
    }

	public void render(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		
		//top left is 0, 0
		int w = 10;
		int h = 10;
		drawQuad(gl, width / 2 - 5, height / 2 - 5, w, h);
	}

	private void drawQuad(GL2 gl, int x, int y, int w, int h) {
		gl.glBegin(GL2.GL_QUADS);
		
		gl.glVertex2f(x,      y);
		gl.glVertex2f(x + w, y);
		gl.glVertex2f(x + w, y + h);
		gl.glVertex2f(x,      y + h);
	
		gl.glEnd();
	}
	
	
	 
	 private void setDimensions(int width, int height) {
		this.width = width;
		this.height = height;
	}

}
