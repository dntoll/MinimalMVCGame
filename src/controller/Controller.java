package controller;
import javax.media.opengl.GLAutoDrawable;

import view.View;

import model.Model;


public class Controller {

	private Model model;
	private View view;

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
	}

	public void update(float timeElapsed, GLAutoDrawable drawable) {
		
		//Handle input
		
		
		//Change model state
		model.update(timeElapsed);
		
		//Generate output
		view.render(drawable);
	}

}
