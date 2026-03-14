package Main.Game.Item;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.Engine.AnimationState;
import Main.Engine.Sprite;
import Main.Game.EntityType;
import Math.Vec2;

public class Ammo extends Item{
	AmmoType AmmoType;
	public static int StackSize = 50;
	public Ammo(AmmoType a) {
		AmmoType = a;
		EntityType = Main.Game.EntityType.Item;
		ItemType = Main.Game.Item.ItemType.Ammo;
		
		
		
		if(a == Main.Game.Item.AmmoType.Pistol)
		{
			Name = "Pistol Ammo";
			Rarity = Main.Game.Rarity.Common;
			Value = 10;
			Count = 10;
			
			BufferedImage PA;
			Vec2 Bitsize = new Vec2(32, 32);

			AnimationState am = new AnimationState();
			try {

				PA = ImageIO.read(getClass().getResourceAsStream("/items/Pistol_Ammo.png"));
				Sprite = new Sprite(PA, "PistolAmmo", Bitsize, Position, 1, am);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(a == Main.Game.Item.AmmoType.Rifle)
		{
			Name = "Rifle Ammo";
			Rarity = Main.Game.Rarity.Common;
			Value = 15;
			Count = 20;
			
			BufferedImage RA;
			Vec2 Bitsize = new Vec2(32, 32);

			AnimationState am = new AnimationState();
			try {

				RA = ImageIO.read(getClass().getResourceAsStream("/items/Rifle_Ammo.png"));
				Sprite = new Sprite(RA, "RifleAmmo", Bitsize, Position, 1, am);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(a == Main.Game.Item.AmmoType.Shotgun)
		{
			Name = "Shotgun Ammo";
			Rarity = Main.Game.Rarity.Common;
			Value = 15;
			Count = 10;
			
			BufferedImage SA;
			Vec2 Bitsize = new Vec2(32, 32);

			AnimationState am = new AnimationState();
			try {

				SA = ImageIO.read(getClass().getResourceAsStream("/items/Shotgun_Ammo.png"));
				Sprite = new Sprite(SA, "ShotgunAmmo", Bitsize, Position, 1, am);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

