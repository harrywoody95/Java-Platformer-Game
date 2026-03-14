package Main.Game.Item;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.Engine.AnimationState;
import Main.Engine.Sprite;
import Main.Game.Rarity;
import Math.Vec2;

public class Sheild extends Item {
	int Durability;
	int Capacity;
	
	public Sheild(Rarity r){
		if(r == Main.Game.Rarity.Common)
		{
			Durability = 100;
			Capacity = 50;
			Value = 65;
			Name = "Common Shelid";
			
			BufferedImage CommonSheild;
			Vec2 Bitsize = new Vec2(32, 32);

			AnimationState a = new AnimationState();
			try {

				CommonSheild = ImageIO.read(getClass().getResourceAsStream("/items/Common_Sheild.png"));
				Sprite = new Sprite(CommonSheild, "CommonSheild", Bitsize, Position, 1, a);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(r == Main.Game.Rarity.Rare)
		{
			Durability = 120;
			Capacity = 100;
			Value = 99;
			Name = "Rare Shelid";
			
			BufferedImage RareSheild;
			Vec2 Bitsize = new Vec2(32, 32);

			AnimationState a = new AnimationState();
			try {

				RareSheild = ImageIO.read(getClass().getResourceAsStream("/items/Rare_Sheild.png"));
				Sprite = new Sprite(RareSheild, "RareSheild", Bitsize, Position, 1, a);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(r == Main.Game.Rarity.Legendary)
		{
			Durability = 150;
			Capacity = 150;
			Value = 189;
			Name = "Legendary Shelid";
			
			BufferedImage LegendarySheild;
			Vec2 Bitsize = new Vec2(32, 32);

			AnimationState a = new AnimationState();
			try {

				LegendarySheild = ImageIO.read(getClass().getResourceAsStream("/items/Legendary_Sheild.png"));
				Sprite = new Sprite(LegendarySheild, "LegendarySheild", Bitsize, Position, 1, a);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Rarity = r;
		ItemType = Main.Game.Item.ItemType.Sheild;
		Count = 1;
		EntityType = Main.Game.EntityType.Item;
	}
}
