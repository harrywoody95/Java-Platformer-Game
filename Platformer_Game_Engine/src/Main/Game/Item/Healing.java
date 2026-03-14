package Main.Game.Item;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.Engine.AnimationState;
import Main.Engine.Game;
import Main.Engine.Sprite;
import Math.Vec2;
import Math.Vec2f;

public class Healing extends Item {
	HealingType Type;
	int Buff;
	
	public Healing (HealingType h)
	{
		if(h == HealingType.Sheild)
		{
			Buff = 100;
			Value = 89;
			Rarity = Main.Game.Rarity.Rare;
			Name = "Sheild Charge";
			
			BufferedImage Sheild;
			Vec2 Bitsize = new Vec2(32, 32);

			AnimationState a = new AnimationState();
			try {

				Sheild = ImageIO.read(getClass().getResourceAsStream("/items/Sheild.png"));
				Sprite = new Sprite(Sheild, "Sheild", Bitsize, Position, 1, a);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(h == HealingType.Stamina)
		{
			Buff = 100;
			Value = 75;
			Rarity = Main.Game.Rarity.Common;
			Name = "Stamina Shot";
			
			BufferedImage Stamina;
			Vec2 Bitsize = new Vec2(32, 32);

			AnimationState a = new AnimationState();
			try {

				Stamina = ImageIO.read(getClass().getResourceAsStream("/items/Stamina_Shot.png"));
				Sprite = new Sprite(Stamina, "Stamina", Bitsize, Position, 1, a);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(h == HealingType.HealthShot)
		{
			Buff = 50;
			Value = 65;
			Rarity = Main.Game.Rarity.Common;
			Name = "Health Shot";
			
			BufferedImage HealthShot;
			Vec2 Bitsize = new Vec2(32, 32);

			AnimationState a = new AnimationState();
			try {

				HealthShot = ImageIO.read(getClass().getResourceAsStream("/items/Health_Shot.png"));
				Sprite = new Sprite(HealthShot, "HealthShot", Bitsize, Position, 1, a);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(h == HealingType.HealthKit)
		{
			Buff = 100;
			Value = 99;
			Rarity = Main.Game.Rarity.Rare;
			Name = "Health Kit";
			
			BufferedImage HealthKit;
			Vec2 Bitsize = new Vec2(32, 32);

			AnimationState a = new AnimationState();
			try {

				HealthKit = ImageIO.read(getClass().getResourceAsStream("/items/Health_Kit.png"));
				Sprite = new Sprite(HealthKit, "HealthKit", Bitsize, Position, 1, a);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Type = h;
		ItemType = Main.Game.Item.ItemType.Healing;
		Count = 1;
		EntityType = Main.Game.EntityType.Item;
	}
	
}
