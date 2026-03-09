package Main.Engine;

import Math.Box;

public class CollisionBox {
	public Box box;
	
	public CollisionBox (int top, int left, int bottom, int right)
	{
		box = new Box(top, left, bottom, right);
	}
}
