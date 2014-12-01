package model;

public class Vector2 {
	public float x;
	public float y;
	
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public static float dot(Vector2 a, Vector2 b) {
		return a.x * b.x + a.y * b.y;
	}

	public Vector2 mul(float f) {
		return new Vector2(x * f , y * f);
	}

	public Vector2 add(Vector2 b) {
		return new Vector2(x + b.x , y + b.y);
	}

	public void normalize() {
		float len = (float) Math.sqrt(x *x + y * y);
		x = x / len;
		y = y / len;
	}

}
