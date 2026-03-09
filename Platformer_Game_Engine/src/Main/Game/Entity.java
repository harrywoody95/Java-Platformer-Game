package Main.Game;

import Main.Engine.CollisionBox;
import Math.Vec2f;

public class Entity {
	public CollisionBox Collision = new CollisionBox(0,0,0,0);
	public Vec2f Position = new Vec2f(0,0);
	public boolean onGround = true;
	
	public void UpdateCollisionBox() {
		Collision.box.Top = Position.y + 20;
		Collision.box.Left = Position.x + 55;
		Collision.box.Bottom = Position.y + 100;
		Collision.box.Right = Position.x + 90;
		
	}
}
