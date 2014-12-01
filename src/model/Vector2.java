package model;

public class Vector2 {
	public float x;
	public float y;
	
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	private void set(Vector2 pb) {
		this.x = pb.x;
		this.y = pb.y;
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
	
	public Vector2 sub(Vector2 b) {
		return new Vector2(x - b.x , y - b.y);
	}

	public void normalize() {
		float len = length();
		x = x / len;
		y = y / len;
	}
	
	//http://geomalgorithms.com/a02-_lines.html
	public float distanceToLine(Vector2 LineStart, Vector2 LineEnd, Vector2 collidePosition ) {
		Vector2 v = LineEnd.sub(LineStart);
		Vector2 w = sub(LineStart);
		
		float c1 = dot(w, v);
		if ( c1 <= 0 ) { // before P0 
			collidePosition.set(LineStart);
			return distanceTo(LineStart);
		}
		float c2 = dot(v,v);
		if ( c2 <= c1 ) {// after P1
			collidePosition.set(LineEnd);
			return distanceTo(LineEnd);
		}
		
		float b = c1 / c2;
		Vector2 Pb = LineStart.add(v.mul(b));
		collidePosition.set(Pb);
		return distanceTo(Pb);
	}

	

	private float distanceTo(Vector2 b) {
		return sub(b).length();
	}

	public float length() {
		return (float) Math.sqrt(x *x + y * y);
	}

	public static Vector2 lineIntersectionPoint(Vector2 ps1, Vector2 pe1, Vector2 ps2,  Vector2 pe2) throws Exception
	{
		  // Get A,B,C of first line - points : ps1 to pe1
		  float A1 = pe1.y-ps1.y;
		  float B1 = ps1.x-pe1.x;
		  float C1 = A1*ps1.x+B1*ps1.y;
		 
		  // Get A,B,C of second line - points : ps2 to pe2
		  float A2 = pe2.y-ps2.y;
		  float B2 = ps2.x-pe2.x;
		  float C2 = A2*ps2.x+B2*ps2.y;
		 
		  // Get delta and check if the lines are parallel
		  float delta = A1*B2 - A2*B1;
		  if(delta == 0)
		     throw new Exception("Parallell");
		 
		  // now return the Vector2 intersection point
		  return new Vector2(
		      (B2*C1 - B1*C2)/delta,
		      (A1*C2 - A2*C1)/delta
		  );
	}

}
