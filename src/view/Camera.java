package view;

public class Camera {
	private int width;
	private int height;
	private float scale;
	private int levelWidth;
	private int levelHeight;
	private static int frame = 100;
	
	Camera(int levelWidth, int levelHeight) {
		this.levelWidth = levelWidth;
		this.levelHeight = levelHeight;
	}
	
	void setDimensions(int width, int height) {
		this.width = width;
		this.height = height;
		
		int scaleX = (width-frame*2) / levelWidth;
		int scaleY = (height-frame*2) / levelHeight;
		
		scale = scaleX;
		if (scaleY < scaleX) {
			scale = scaleY;
		}
	}
	
	//Camera
			
	public float getScale() {
		return scale;
	}
	public float toViewX(float  x) {
		return x * scale + frame;
	}

	public float toViewY(float y) {
		return y * scale + frame;
	}

	public float toModelX(float viewPos) {
		return (viewPos - frame) / scale;
	}
}
