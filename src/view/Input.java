package view;


import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Input implements MouseListener, MouseMotionListener, KeyListener {

	private boolean mouseWasReleased = false;
	private Point mousePos = new Point(0,0);
	private char keyReleased = 0;

	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.mousePos = arg0.getPoint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.mousePos = arg0.getPoint();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		this.mousePos = arg0.getPoint();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		this.mousePos = arg0.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		this.mousePos = arg0.getPoint();
		mouseWasReleased = true;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		keyReleased  = arg0.getKeyChar();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		this.mousePos = arg0.getPoint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		this.mousePos = arg0.getPoint();
		
	}

	public boolean userReleasesButton() {
		if (mouseWasReleased) {
			mouseWasReleased = false;
			return true;
		}
		return false;
	}

	public boolean userPressKey(char c) {
		if (keyReleased == c) {
			keyReleased = 0;
			return true;
		}
		return false;
	}

	public float getMousePosX() {
		return mousePos.x;
	}

	public float getMousePosY() {
		return mousePos.y;
	}

	public void clear() {
		mouseWasReleased = false;
	}

}
