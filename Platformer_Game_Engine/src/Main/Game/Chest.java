package Main.Game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import Main.Engine.AnimationState;
import Main.Engine.Game;
import Main.Engine.Sprite;
import Main.Game.Item.Item;
import Math.Vec2;

public class Chest extends Entity{
	Vector <Item> Loot = new Vector <Item>();
	Rarity Rarity;
	boolean Opened = false;
	private boolean hasOpenAnimation = false;
	
	public Chest(int x, int y, Rarity r, Game g)
	{
		//make new chest. 
		// todo - set loot randomly

		
		Position.x = x;
		Position.y = y;
		Rarity = r;
		BufferedImage Chest;
		Vec2 Bitsize = new Vec2(64, 64);

		AnimationState a = new AnimationState();
		try {
			if(r == Main.Game.Rarity.Common)
			{
				Chest = ImageIO.read(getClass().getResourceAsStream("/lootable/Chest_Common_1.png"));
				Sprite = new Sprite(Chest, "Chest", Bitsize, Position, 1, a);

			}
			if(r == Main.Game.Rarity.Rare)
			{
				Chest = ImageIO.read(getClass().getResourceAsStream("/lootable/Chest_Rare_1.png"));
				Sprite = new Sprite(Chest, "Chest", Bitsize, Position, 1, a);

			}
			
			Collision.box.Top = Position.y + 20;
			Collision.box.Left = Position.x + 12 ;
			Collision.box.Bottom = Position.y + 50;
			Collision.box.Right = Position.x + 47;
			
			EntityType = EntityType.Chest;
			
			g.EntityList.add(this);
			g.CollisionBoxList.add(Collision);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void LootChest(Game g)
	{
		if(!Opened)
		{
			for(Item i : Loot)
			{
				g.Player.Inventory.Inventory.add(i);
			}
		}
		Opened = true;
		g.SoundManager.PlaySoundEffect("VeryRareItem");
	}
	
	public void Update(Game g)
	{
		Sprite.Update();
		if(Opened && !hasOpenAnimation)
		{
			if(Rarity == Main.Game.Rarity.Common)
			{
				Main.Engine.Sprite.StartSpriteAnimation(g, Sprite, "CommonChestOpen", false);
				hasOpenAnimation = true;
			}
			if(Rarity == Main.Game.Rarity.Rare)
			{
				Main.Engine.Sprite.StartSpriteAnimation(g, Sprite, "RareChestOpen", false);
				hasOpenAnimation = true;
			}
		}
	}
	
}
