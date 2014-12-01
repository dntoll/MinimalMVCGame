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
				
				if (y < 1 && x < 4)
					bricks[x][y] = new FillBrick();
				else
					bricks[x][y] = new EmptyBrick();
			}
		}
	}

	public void removeBrick(Brick brickToRemove) {
		
		if (brickToRemove == null)
			return;
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (bricks[x][y] == brickToRemove) {
					bricks[x][y] = new EmptyBrick();
				}
			}
		}
	}

	public boolean hasNoBricks() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (bricks[x][y].collides()) {
					return false;
				}
			}
		}
		
		return true;
	}
}
