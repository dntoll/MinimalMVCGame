package model;

public class Level {
	public int width = 8;
	public int height = 8;
	
	public Brick bricks[][];
	
	Level() {
		
		bricks = new Brick[width][];
		for (int x = 0; x < width; x++) {
			bricks[x] = new Brick[height];
			for (int y = 0; y < height; y++) {
				
				if (y < 4)
					bricks[x][y] = new FillBrick();
				else
					bricks[x][y] = new EmptyBrick();
			}
		}
	}
}
