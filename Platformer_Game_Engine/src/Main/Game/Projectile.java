package Main.Game;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.Engine.AnimationState;
import Main.Engine.Game;
import Main.Engine.Sprite;
import Main.Game.Item.WeaponType;
import Math.Vec2;

public class Projectile extends Entity{
	//Type
	int Damage = 0;
	int Speed = 0;
	Direction Direction;

	public Projectile (Game g, int Damage, int speed, int x, int y, Direction dir, WeaponType type)
	{
		this.Damage = Damage;
		this.Speed = speed;
		Position.x = x;
		Position.y = y;
		Direction = dir;
		Vec2 Bitsize = new Vec2(32,32);
		AnimationState a = new AnimationState();
		if (type == WeaponType.Pistol)
		{
			BufferedImage image;
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/projectile/Pistol_Bullet.png"));
				Sprite = new Sprite(image, "Bullet", Bitsize, Position, 1, a);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (type == WeaponType.Shotgun)
		{
			BufferedImage image;
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/projectile/Shotgun_Bullet.png"));
				Sprite = new Sprite(image, "Bullet", Bitsize, Position, 1, a);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (type == WeaponType.MachineGun)
		{
			BufferedImage image;
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/projectile/Rifle_Bullet.png"));
				Sprite = new Sprite(image, "Bullet", Bitsize, Position, 1, a);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		g.EntityList.add(this);
	}
	
	public void Update(Game g)
	{
		if(Direction == Main.Game.Direction.Left)
		{
			Position.x -= Speed;
		}
		else
		{
			Position.x += Speed;
		}
		
		//projectile fall off
		Position.y += 0.4f;
		
		
		//handle any hits with collision
	}
}
