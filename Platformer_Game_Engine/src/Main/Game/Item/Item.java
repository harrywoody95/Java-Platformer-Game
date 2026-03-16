package Main.Game.Item;

import Main.Game.Entity;
import Main.Game.Rarity;

public class Item extends Entity{
	public String Name;
	public Rarity Rarity;
	public int Value;
	public ItemType ItemType;
	public int Count;
	
	
	public Item () {
		// make cool items. have it make it pick it from a pool. txt files
	}
	
	 public static ItemType StringToItemType(String s)
	 {
		 if (s.equals("Ammo"))
		 {
			 return Main.Game.Item.ItemType.Ammo;
		 }
		 if (s.equals("Crafting"))
		 {
			 return Main.Game.Item.ItemType.Crafting;
		 }
		 if (s.equals("Weapon"))
		 {
			 return Main.Game.Item.ItemType.Weapon;
		 }
		 if (s.equals("Healing"))
		 {
			 return Main.Game.Item.ItemType.Healing;
		 }
		 if (s.equals("Valuable"))
		 {
			 return Main.Game.Item.ItemType.Valuable;
		 }
		 if (s.equals("Sheild"))
		 {
			 return Main.Game.Item.ItemType.Sheild;
		 }
		 System.out.println("ERROR : StringToItemType - Type : '" + s + "' doesnt exist");
		 return null;
	 }
	 
	 public static AmmoType StringToAmmoType(String s)
	 {
		 if (s.equals("Pistol"))
		 {
			 return Main.Game.Item.AmmoType.Pistol;
		 }
		 if (s.equals("Shotgun"))
		 {
			 return Main.Game.Item.AmmoType.Shotgun;
		 }
		 if (s.equals("Rifle"))
		 {
			 return Main.Game.Item.AmmoType.Rifle;
		 }
		 System.out.println("ERROR : StringToAmmoType - Type doesnt exist");
		 return null;
	 }
	 
	 public static CraftingMaterialRarity StringToCraftingMaterialRarity(String s)
	 {
		 if (s.equals("Simple"))
		 {
			 return Main.Game.Item.CraftingMaterialRarity.Simple;
		 }
		 if (s.equals("Average"))
		 {
			 return Main.Game.Item.CraftingMaterialRarity.Average;
		 }
		 if (s.equals("Advanced"))
		 {
			 return Main.Game.Item.CraftingMaterialRarity.Advanced;
		 }
		 System.out.println("ERROR : StringToCraftingMaterialRarity - Type doesnt exist");
		 return null;
	 }
	 
	 public static CraftingMaterial StringToCraftingMaretial(String s)
	 {
		 if (s.equals("GunPart"))
		 {
			 return Main.Game.Item.CraftingMaterial.GunPart;
		 }
		 if (s.equals("WeaponModule"))
		 {
			 return Main.Game.Item.CraftingMaterial.WeaponModule;
		 }
		 if (s.equals("SheildPart"))
		 {
			 return Main.Game.Item.CraftingMaterial.SheildPart;
		 }
		 if (s.equals("Metal"))
		 {
			 return Main.Game.Item.CraftingMaterial.Metal;
		 }
		 if (s.equals("GunPowder"))
		 {
			 return Main.Game.Item.CraftingMaterial.GunPowder;
		 }
		 System.out.println("ERROR : StringToCraftingMaterial - Type doesnt exist");
		 return null;
	 }
	 
	 public static Rarity StringToRarity(String s)
	 {
		 if (s.equals("Common"))
		 {
			 return Main.Game.Rarity.Common;
		 }
		 if (s.equals("Rare"))
		 {
			 return Main.Game.Rarity.Rare;
		 }
		 if (s.equals("Legendary"))
		 {
			 return Main.Game.Rarity.Legendary;
		 }
		 System.out.println("ERROR : StringToRarity - Type doesnt exist");
		 return null;
	 }
	 
	 public static WeaponType StringToWeaponType(String s)
	 {
		 if (s.equals("Pistol"))
		 {
			 return WeaponType.Pistol;
		 }
		 if (s.equals("MachineGun"))
		 {
			 return WeaponType.MachineGun;
		 }
		 if (s.equals("Shotgun"))
		 {
			 return WeaponType.Shotgun;
		 }
		 System.out.println("ERROR : StringToWeaponType - Type doesnt exist");
		 return null;
	 }
	 
	 public static HealingType StringToHealingType(String s)
	 {
		 if (s.equals("HealthKit"))
		 {
			 return HealingType.HealthKit;
		 }
		 if (s.equals("HealthShot"))
		 {
			 return HealingType.HealthShot;
		 }
		 if (s.equals("Stamina"))
		 {
			 return HealingType.Stamina;
		 }
		 if (s.equals("Sheild"))
		 {
			 return HealingType.Sheild;
		 }
		 System.out.println("ERROR : StringToHealingType - Type doesnt exist");
		 return null;
	 }
	 
	 public static ValuableType StringToValuableType(String s)
	 {
		 if (s.equals("Book"))
		 {
			 return ValuableType.Book;
		 }
		 if (s.equals("Diamond"))
		 {
			 return ValuableType.Diamond;
		 }
		 if (s.equals("GoldBar"))
		 {
			 return ValuableType.GoldBar;
		 }
		 if (s.equals("GoldRing")) 
		 {
			 return ValuableType.GoldRing;
		 }
		 if (s.equals("Necklace")) 
		 {
			 return ValuableType.Necklace;
		 }
		 if (s.equals("PocketWatch")) 
		 {
			 return ValuableType.PocketWatch;
		 }
		 System.out.println("ERROR : StringToValuableType - Type doesnt exist");
		 return null;
	 }
}
