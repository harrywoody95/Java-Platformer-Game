package Main.Game;
import Math.Vec2f;

public class Character extends Entity{
	int Speed;
	public Direction currentDirection;
	public State currentState;
	public Direction lastDirection;
	public State lastState;
	public Vec2f Velocity = new Vec2f(0, 0);
}
