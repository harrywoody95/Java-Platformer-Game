package Main.Game.Item;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.Engine.AnimationState;
import Main.Engine.Sprite;
import Math.Vec2;

public class CraftingItem extends Item {
	CraftingMaterialRarity CraftingRarity;
	CraftingMaterial CraftingMaterial;
	
	public CraftingItem(CraftingMaterialRarity r, CraftingMaterial m)
	{
		CraftingRarity = r;
		CraftingMaterial = m;
		Count = 1;
		EntityType = Main.Game.EntityType.Item;
		ItemType = Main.Game.Item.ItemType.Crafting;
		
		
		if(m == Main.Game.Item.CraftingMaterial.WeaponModule)
		{
			if(r == CraftingMaterialRarity.Simple)
			{
				Value = 60;
				Name = "Simple Weapon Module";
				Rarity = Main.Game.Rarity.Common;
				BufferedImage GM;
				Vec2 Bitsize = new Vec2(32, 32);

				AnimationState a = new AnimationState();
				try {

					GM = ImageIO.read(getClass().getResourceAsStream("/items/Simple_Weapon_Module.png"));
					Sprite = new Sprite(GM, "GunModule", Bitsize, Position, 1, a);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(r == CraftingMaterialRarity.Average)
			{
				
				Value = 85;
				Name = "Average Weapon Module";
				Rarity = Main.Game.Rarity.Rare;
				BufferedImage GM;
				Vec2 Bitsize = new Vec2(32, 32);

				AnimationState a = new AnimationState();
				try {

					GM = ImageIO.read(getClass().getResourceAsStream("/items/Average_Weapon_Module.png"));
					Sprite = new Sprite(GM, "GunModule", Bitsize, Position, 1, a);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(r == CraftingMaterialRarity.Advanced)
			{
				Value = 120;
				Name = "Advanced Weapon Module";
				Rarity = Main.Game.Rarity.Rare;
				BufferedImage GM;
				Vec2 Bitsize = new Vec2(32, 32);

				AnimationState a = new AnimationState();
				try {

					GM = ImageIO.read(getClass().getResourceAsStream("/items/Advanced_Weapon_Module.png"));
					Sprite = new Sprite(GM, "GunModule", Bitsize, Position, 1, a);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if(m == Main.Game.Item.CraftingMaterial.GunPart)
		{
			if(r == CraftingMaterialRarity.Simple)
			{
				Value = 60;
				Name = "Simple Gun Part";
				Rarity = Main.Game.Rarity.Common;
				BufferedImage GP;
				Vec2 Bitsize = new Vec2(32, 32);

				AnimationState a = new AnimationState();
				try {

					GP = ImageIO.read(getClass().getResourceAsStream("/items/Simple_Gun_Part.png"));
					Sprite = new Sprite(GP, "GunModule", Bitsize, Position, 1, a);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(r == CraftingMaterialRarity.Average)
			{
				Value = 85;
				Name = "Average Gun Part";
				Rarity = Main.Game.Rarity.Rare;
				BufferedImage GP;
				Vec2 Bitsize = new Vec2(32, 32);

				AnimationState a = new AnimationState();
				try {

					GP = ImageIO.read(getClass().getResourceAsStream("/items/Average_Gun_Part.png"));
					Sprite = new Sprite(GP, "GunModule", Bitsize, Position, 1, a);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(r == CraftingMaterialRarity.Advanced)
			{
				Value = 130;
				Name = "Advanced Gun Part";
				Rarity = Main.Game.Rarity.Rare;
				BufferedImage GP;
				Vec2 Bitsize = new Vec2(32, 32);

				AnimationState a = new AnimationState();
				try {

					GP = ImageIO.read(getClass().getResourceAsStream("/items/Advanced_Gun_Part.png"));
					Sprite = new Sprite(GP, "GunModule", Bitsize, Position, 1, a);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if(m == Main.Game.Item.CraftingMaterial.SheildPart)
		{
			if(r == CraftingMaterialRarity.Simple)
			{
				Value = 50;
				Name = "Simple Sheild Part";
				Rarity = Main.Game.Rarity.Common;
				BufferedImage SP;
				Vec2 Bitsize = new Vec2(32, 32);

				AnimationState a = new AnimationState();
				try {

					SP = ImageIO.read(getClass().getResourceAsStream("/items/Simple_Sheild_Part.png"));
					Sprite = new Sprite(SP, "SimpleSheildPart", Bitsize, Position, 1, a);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(r == CraftingMaterialRarity.Average)
			{
				Value = 80;
				Name = "Average Sheild Part";
				Rarity = Main.Game.Rarity.Rare;
				BufferedImage SP;
				Vec2 Bitsize = new Vec2(32, 32);

				AnimationState a = new AnimationState();
				try {

					SP = ImageIO.read(getClass().getResourceAsStream("/items/Average_Sheild_Part.png"));
					Sprite = new Sprite(SP, "AverageSheildPart", Bitsize, Position, 1, a);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(r == CraftingMaterialRarity.Advanced)
			{
				Value = 100;
				Name = "Advanced Sheild Part";
				Rarity = Main.Game.Rarity.Rare;
				BufferedImage SP;
				Vec2 Bitsize = new Vec2(32, 32);

				AnimationState a = new AnimationState();
				try {

					SP = ImageIO.read(getClass().getResourceAsStream("/items/Advanced_Sheild_Part.png"));
					Sprite = new Sprite(SP, "AdvancedSheildPart", Bitsize, Position, 1, a);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		if(m == Main.Game.Item.CraftingMaterial.Metal)
		{
			Value = 1;
			Name = "Metal";
			Rarity = Main.Game.Rarity.Common;
			CraftingRarity = CraftingMaterialRarity.Simple;
			BufferedImage M;
			Vec2 Bitsize = new Vec2(32, 32);

			AnimationState a = new AnimationState();
			try {

				M = ImageIO.read(getClass().getResourceAsStream("/items/Metal.png"));
				Sprite = new Sprite(M, "Metal", Bitsize, Position, 1, a);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(m == Main.Game.Item.CraftingMaterial.GunPowder)
		{
			Value = 1;
			Name = "GunPowder";
			Rarity = Main.Game.Rarity.Common;
			CraftingRarity = CraftingMaterialRarity.Simple;
			BufferedImage GP;
			Vec2 Bitsize = new Vec2(32, 32);

			AnimationState a = new AnimationState();
			try {

				GP = ImageIO.read(getClass().getResourceAsStream("/items/Gun_Powder.png"));
				Sprite = new Sprite(GP, "GunPowder", Bitsize, Position, 1, a);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
