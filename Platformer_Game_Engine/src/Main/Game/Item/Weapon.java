package Main.Game.Item;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.Engine.AnimationState;
import Main.Engine.Sprite;
import Main.Game.Rarity;
import Math.Vec2;

public class Weapon extends Item {
	WeaponType Type;
	int Damage;
	int Durability;
	
	public Weapon(WeaponType wt, Rarity r)
	{
		if(wt == WeaponType.Pistol)
		{
			if(r == Main.Game.Rarity.Common)
			{
				Name = "Common Pistol";
				Value = 85;
				Damage = 7;
				Durability = 100;
			}
			if(r == Main.Game.Rarity.Rare)
			{
				Name = "Rare Pistol";
				Value = 145;
				Damage = 10;
				Durability = 120;
			}
			if(r == Main.Game.Rarity.Legendary)
			{
				Name = "Legendary Pistol";
				Value = 199;
				Damage = 12;
				Durability = 150;
			}
			
			BufferedImage Pistol;
			Vec2 Bitsize = new Vec2(70, 70);

			AnimationState a = new AnimationState();
			try {

				Pistol = ImageIO.read(getClass().getResourceAsStream("/items/Pistol.png"));
				Sprite = new Sprite(Pistol, "Pistol", Bitsize, Position, 1, a);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(wt == WeaponType.Shotgun)
		{
			if(r == Main.Game.Rarity.Common)
			{
				Name = "Common Shotgun";
				Value = 130;
				Damage = 10;
				Durability = 100;
			}
			if(r == Main.Game.Rarity.Rare)
			{
				Name = "Rare Shotgun";
				Value = 210;
				Damage = 15;
				Durability = 120;
			}
			if(r == Main.Game.Rarity.Legendary)
			{
				Name = "Legendary Shotgun";
				Value = 280;
				Damage = 18;
				Durability = 150;
			}
			
			BufferedImage Shotgun;
			Vec2 Bitsize = new Vec2(78, 78);

			AnimationState a = new AnimationState();
			try {

				Shotgun = ImageIO.read(getClass().getResourceAsStream("/items/Shotgun.png"));
				Sprite = new Sprite(Shotgun, "Shotgun", Bitsize, Position, 1, a);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(wt == WeaponType.MachineGun)
		{
			if(r == Main.Game.Rarity.Common)
			{
				Name = "Common Machine Gun";
				Value = 190;
				Damage = 6;
				Durability = 100;
				
				BufferedImage MG;
				Vec2 Bitsize = new Vec2(78, 78);

				AnimationState a = new AnimationState();
				try {

					MG = ImageIO.read(getClass().getResourceAsStream("/items/Machine_Gun.png"));
					Sprite = new Sprite(MG, "MG1", Bitsize, Position, 1, a);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(r == Main.Game.Rarity.Rare)
			{
				Name = "Rare Machine Gun";
				Value = 280;
				Damage = 8;
				Durability = 120;
				
				BufferedImage MG;
				Vec2 Bitsize = new Vec2(78, 78);

				AnimationState a = new AnimationState();
				try {

					MG = ImageIO.read(getClass().getResourceAsStream("/items/Machine_Gun.png"));
					Sprite = new Sprite(MG, "MG1", Bitsize, Position, 1, a);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(r == Main.Game.Rarity.Legendary)
			{
				Name = "Legendary Machine Gun";
				Value = 399;
				Damage = 10;
				Durability = 150;
				
				BufferedImage MG;
				Vec2 Bitsize = new Vec2(78, 78);

				AnimationState a = new AnimationState();
				try {

					MG = ImageIO.read(getClass().getResourceAsStream("/items/Machine_Gun_2.png"));
					Sprite = new Sprite(MG, "MG2", Bitsize, Position, 1, a);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		Count = 1;
		ItemType = Main.Game.Item.ItemType.Weapon;
		Rarity = r;
		Type = wt;
		EntityType = Main.Game.EntityType.Item;
	}
}
