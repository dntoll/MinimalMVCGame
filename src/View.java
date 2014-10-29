import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;


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

	public void setDimensions(int width, int height) {
		this.width = width;
		this.height = height;
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

}
