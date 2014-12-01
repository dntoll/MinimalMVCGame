package controller;
import javax.media.opengl.GLAutoDrawable;

import view.IMGUI;
import view.View;

import model.Model;


public class Controller {

	private Model model;
	private View view;
	private boolean hasStartedGame = false;
	
	private IMGUI imgui;

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		imgui = new IMGUI(view.getCore(), view.getInput());
	}

	public void update(float timeElapsed, GLAutoDrawable drawable) {
		view.loadResources();
		
		
		if (hasStartedGame) {
			playGame(timeElapsed, drawable);
			
			if (model.isGameOver())
				hasStartedGame = false;
		} else {
			doMenu(drawable);
		}
		
		view.getInput().clear();
	}

	private void doMenu(GLAutoDrawable drawable) {
		view.getCore().clearScreen(drawable);
		if (imgui.doButton("Start Game", 100, 100, drawable)) {
			hasStartedGame = true;
		}
		
	}

	private void playGame(float timeElapsed, GLAutoDrawable drawable) {
		//Handle input
		float x = view.getMouseXPos();
		model.pad.centerX = x;
		
		//Change model state
		model.update(timeElapsed, view);
		
		//Generate output
		view.render(drawable, timeElapsed);
	}

}
