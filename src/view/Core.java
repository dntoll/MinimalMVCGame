package view;

import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLException;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class Core {
	
	private Texture brick;
	private Texture ball;
	
	void loadResources() throws GLException, IOException {
		if (brick == null)
			brick = TextureIO.newTexture(new File("redbox.png"), false);
		if (ball == null)
			ball = TextureIO.newTexture(new File("ball.png"), false);
	}
	
	void drawBall(GLAutoDrawable drawable, float x, float y, float w, float h) {
		drawQuad(ball, drawable, x, y, w, h);
	}
	
	void drawBrick(GLAutoDrawable drawable, float x, float y, float w, float h) {
		drawQuad(brick, drawable, x, y, w, h);
	}
	
	void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
   	 	GL2 gl = drawable.getGL().getGL2();
        GLU glu = new GLU();
        
        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity(); // reset
        glu.gluOrtho2D (0.0, w, h, 0);  // define drawing area
        
        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity(); // reset
        
        
   }
	
	
	
	private void drawQuad(Texture tex, GLAutoDrawable drawable, float x, float y, float w, float h) {
		GL2 gl = drawable.getGL().getGL2();
		
		//gl.glEnable(GL.GL_TEXTURE_2D);
		tex.enable(gl);
		gl.glEnable(GL.GL_BLEND);
		gl.glBlendFunc(gl.GL_SRC_ALPHA, gl.GL_ONE_MINUS_SRC_ALPHA);
		
		tex.bind(gl);
		//gl.glBindTexture(GL.GL_TEXTURE0, texture.getTextureObject());
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor4f(1, 1, 1,1);
		
		gl.glTexCoord2f(0, 1);
		gl.glVertex2f(x,      y);
		gl.glTexCoord2f(1, 1);
		gl.glVertex2f(x + w, y);
		gl.glTexCoord2f(1, 0);
		gl.glVertex2f(x + w, y + h);
		gl.glTexCoord2f(0, 0);
		gl.glVertex2f(x,      y + h);
	
		gl.glEnd();
		//gl.glDisable(GL.GL_TEXTURE_2D);
		tex.disable(gl);
	}
	
	void drawBall(GLAutoDrawable drawable, float x, float y, float w, float h, float color[]) {
		GL2 gl = drawable.getGL().getGL2();
		
		gl.glEnable(GL.GL_TEXTURE_2D);
		ball.enable(gl);
		gl.glBlendFunc(gl.GL_SRC_ALPHA, gl.GL_ONE_MINUS_SRC_ALPHA);
		
		ball.bind(gl);
		gl.glBegin(GL2.GL_QUADS);

		gl.glColor4f(color[0], color[1], color[2],color[3]);
		
		gl.glTexCoord2f(0, 1);
		gl.glVertex2f(x,      y);
		gl.glTexCoord2f(1, 1);
		gl.glVertex2f(x + w, y);
		gl.glTexCoord2f(1, 0);
		gl.glVertex2f(x + w, y + h);
		gl.glTexCoord2f(0, 0);
		gl.glVertex2f(x,      y + h);
	
		gl.glEnd();
		gl.glDisable(GL.GL_TEXTURE_2D);
		ball.disable(gl);
	}

	public void clearScreen(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
	}

	public void drawFrame(GLAutoDrawable drawable, float x, float y, float w,
			float h) {
		GL2 gl = drawable.getGL().getGL2();
		
		
		gl.glBegin(GL2.GL_LINE_STRIP);
		gl.glColor4f(1, 1, 1,1);
		
		
		gl.glVertex2f(x,      y);
		gl.glVertex2f(x + w, y);
		gl.glVertex2f(x + w, y + h);
		
		gl.glVertex2f(x,      y + h);
		gl.glVertex2f(x,      y);
	
		gl.glEnd();
		
	}
}
