package view;

import javax.media.opengl.GLAutoDrawable;

public class IMGUI {
	
	private Core core;
	private Input input;
	
	public IMGUI(Core core, Input input) {
		this.core = core;
		this.input = input;
	}
	
	public boolean doButton(String buttonText, int x, int y, GLAutoDrawable drawable) {
		float width = 128;
		float height = 32;
		float mx = input.getMousePosX();
		float my = input.getMousePosY();
		
		boolean mouseOver = false;
		
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				mouseOver = true;
			}
		}
		
		if (mouseOver == false) {
			core.drawBrick(drawable, x, y, width, height);
		} else {
			core.drawBrick(drawable, x+5, y+5, width-10, height-10);
		}
		
		if (mouseOver && input.userReleasesButton()) {
			return true;
		}
		
		return false;
	}

}
