package Main.Game;
import Main.Engine.CollisionBox;
import Math.Vec2;
import Math.Vec2f;

public class Entity {
	public Vec2f Position = new Vec2f(0,0);
	int Speed;
	public Direction currentDirection;
	public State currentState;
	public Direction lastDirection;
	public State lastState;
	public CollisionBox Collision = new CollisionBox(0,0,0,0);
	public Vec2f Velocity = new Vec2f(0, 0);
	public boolean onGround = true;
	public float gravity = 0.3f;
	
	public void UpdateCollisionBox() {
		Collision.box.Top = Position.y + 20;
		Collision.box.Left = Position.x + 55;
		Collision.box.Bottom = Position.y + 100;
		Collision.box.Right = Position.x + 90;
		
	}

}
