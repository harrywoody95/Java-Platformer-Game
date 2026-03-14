package Main.Game.Item;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.Engine.AnimationState;
import Main.Engine.Sprite;
import Math.Vec2;

public class Valuable extends Item {
	
	public Valuable(ValuableType t)
	{
		EntityType = Main.Game.EntityType.Item;
		ItemType = Main.Game.Item.ItemType.Money;
		Count = 1;
		
		if(t == ValuableType.Book)
		{
			Name = "Old Book";
			Value = 40;
			Rarity = Main.Game.Rarity.Common;
			BufferedImage B;
			Vec2 Bitsize = new Vec2(32, 32);

			AnimationState a = new AnimationState();
			try {

				B = ImageIO.read(getClass().getResourceAsStream("/items/Book.png"));
				Sprite = new Sprite(B, "OldBook", Bitsize, Position, 1, a);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(t == ValuableType.Diamond)
		{
			Name = "Diamond";
			Value = 150;
			Rarity = Main.Game.Rarity.Legendary;
			BufferedImage B;
			Vec2 Bitsize = new Vec2(32, 32);

			AnimationState a = new AnimationState();
			try {

				B = ImageIO.read(getClass().getResourceAsStream("/items/Diamond.png"));
				Sprite = new Sprite(B, "Diamond", Bitsize, Position, 1, a);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(t == ValuableType.GoldBar)
		{
			Name = "Gold Bar";
			Value = 170;
			Rarity = Main.Game.Rarity.Legendary;
			BufferedImage B;
			Vec2 Bitsize = new Vec2(32, 32);

			AnimationState a = new AnimationState();
			try {

				B = ImageIO.read(getClass().getResourceAsStream("/items/Gold_Bar.png"));
				Sprite = new Sprite(B, "GoldBar", Bitsize, Position, 1, a);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(t == ValuableType.GoldRing)
		{
			Name = "Gold Ring";
			Value = 90;
			Rarity = Main.Game.Rarity.Rare;
			BufferedImage B;
			Vec2 Bitsize = new Vec2(32, 32);

			AnimationState a = new AnimationState();
			try {

				B = ImageIO.read(getClass().getResourceAsStream("/items/Gold_Ring.png"));
				Sprite = new Sprite(B, "GoldRing", Bitsize, Position, 1, a);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(t == ValuableType.Necklace)
		{
			Name = "Gem Necklace";
			Value = 80;
			Rarity = Main.Game.Rarity.Rare;
			BufferedImage B;
			Vec2 Bitsize = new Vec2(32, 32);

			AnimationState a = new AnimationState();
			try {

				B = ImageIO.read(getClass().getResourceAsStream("/items/Necklace_1.png"));
				Sprite = new Sprite(B, "GemNecklace", Bitsize, Position, 1, a);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(t == ValuableType.PocketWatch)
		{
			Name = "Pocket Watch";
			Value = 50;
			Rarity = Main.Game.Rarity.Common;
			BufferedImage B;
			Vec2 Bitsize = new Vec2(32, 32);

			AnimationState a = new AnimationState();
			try {

				B = ImageIO.read(getClass().getResourceAsStream("/items/Pocket_Watch.png"));
				Sprite = new Sprite(B, "PocketWatch", Bitsize, Position, 1, a);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
