

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GL2;
import static javax.media.opengl.GL2.*;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import view.Input;
import view.View;

import model.Model;


import com.jogamp.opengl.util.FPSAnimator;

import controller.Controller;


public class MinimalApplication implements GLEventListener  {
	private Model model;
	private View view;
	private Input input;
	private Controller controller;
	private long previousTime;
	
	static int width = 800;
	static int height = 600;

    
    public MinimalApplication(GLCanvas c) throws Exception {
    	model = new Model();
    	input = new Input();
    	view = new View(model, width, height, input);
    	
    	controller = new Controller(model, view);
    	
    	c.addMouseListener(input);
    	c.addMouseMotionListener(input);
    	c.addKeyListener(input);
    	
    	
    }

    @Override
    public void display(GLAutoDrawable drawable) {
    	long currentTime = System.currentTimeMillis();
        
    	controller.update((float)((currentTime - previousTime) / 1000.0f), drawable);
        
        
        previousTime = currentTime;
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void init(GLAutoDrawable drawable) {
    	reshape(drawable, 0, 0 , width, height);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
    	view.reshape(drawable, x, y, w, h);
    	previousTime = System.currentTimeMillis();
    }
    
    public static void main(String[] args) throws Exception {
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        GLCanvas canvas = new GLCanvas(caps);
        
        Frame frame = new Frame("Minimal MVC");
        frame.setSize(MinimalApplication.width, MinimalApplication.height);
        frame.add(canvas);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        canvas.addGLEventListener(new MinimalApplication(canvas));

        FPSAnimator animator = new FPSAnimator(canvas, 60);
        animator.start();
    }
}
