package Main.Game;

import java.awt.Graphics2D;

import Main.Engine.CollisionBox;
import Main.Engine.Game;
import Main.Engine.Sprite;
import Math.Vec2;
import Math.Vec2f;

public class Entity {
	public CollisionBox Collision = new CollisionBox(0,0,0,0);
	public Vec2f Position = new Vec2f(0,0);
	public boolean onGround = true;
	public Sprite Sprite;
	public EntityType EntityType;
	
	public void Draw(Graphics2D g2, Game g) {
		g2.drawImage(Sprite.Texture, (int)(Position.x - g.Camera.cameraX), (int)(Position.y - g.Camera.cameraY), Sprite.Bitsize.x * Sprite.Scale, Sprite.Bitsize.y * Sprite.Scale, null);
	}
	
	public Vec2f PositionCenter()
	{
		Vec2f pos = new Vec2f(0,0);
		
		pos.x = Collision.box.Left + (Collision.box.Right - Collision.box.Left) / 2;
		pos.y = Collision.box.Top + (Collision.box.Bottom - Collision.box.Top) / 2;
		
		return pos;
	}
	
	public void Update(Game g)
	{
		// all subclass of entity have there own update function
	}
}
