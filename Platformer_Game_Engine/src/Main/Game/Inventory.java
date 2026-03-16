package Main.Game;

import java.util.Vector;

import Main.Game.Item.Ammo;
import Main.Game.Item.CraftingItem;
import Main.Game.Item.Healing;
import Main.Game.Item.Item;
import Main.Game.Item.ItemType;
import Main.Game.Item.Valuable;

public class Inventory {
	public int StackSize = 5;
	public int AmmoStackSize = 50;
	public int InventorySlots = 12;
	
	Item SheildSlot = null;
	Item PrimaryGunSlot = null;
	Item SecondaryGunSlot = null;
	Item PrimaryHealSlot = null;
	Item SecondaryHealSlot = null;
	
	Vector<Item> Inventory = new Vector<Item>();
	
	public boolean AddItemToInventory(Item i)
	{
		//CHECK HEALING
		if(i.ItemType == ItemType.Healing)
		{
			//IF PrimarySlot is empty
			if(PrimaryHealSlot == null)
			{
				if(SecondaryHealSlot != null)
				{
					Healing healSlot = (Healing)(SecondaryHealSlot);
					Healing healItem = (Healing)(i);
					if(healSlot.Type == healItem.Type && healSlot.Count < StackSize)
					{
						healSlot.Count++;
						return true;
					}
				}

				PrimaryHealSlot = i;
				return true;

			}
			//if Primary Slot has something
			else
			{
				Healing healSlot = (Healing)(PrimaryHealSlot);
				Healing healItem = (Healing)(i);
				if(healSlot.Type == healItem.Type && healSlot.Count < StackSize)
				{
					healSlot.Count++;
					return true;
				}
			}
			
			//if secondary slot is empty
			if(SecondaryHealSlot == null)
			{
				if(PrimaryHealSlot != null)
				{
					Healing healSlot = (Healing)(PrimaryHealSlot);
					Healing healItem = (Healing)(i);
					if(healSlot.Type == healItem.Type && healSlot.Count < StackSize)
					{
						healSlot.Count++;
						return true;
					}
				}
				
				SecondaryHealSlot = i;
				return true;
			}
			
			// if seconardy slot has something
			else
			{
				Healing healSlot = (Healing)(SecondaryHealSlot);
				Healing healItem = (Healing)(i);
				if(healSlot.Type == healItem.Type && healSlot.Count < StackSize)
				{
					healSlot.Count++;
					return true;
				}
			}
			
			//do we have a stack in the inventory
			for(Item item : Inventory)
			{
				if(item.ItemType == ItemType.Healing)
				{
					Healing InventoryHeal = (Healing)(item);
					Healing HealItem = (Healing)(i);
					
					if(HealItem.Type == InventoryHeal.Type && InventoryHeal.Count < StackSize)
					{
						InventoryHeal.Count++;
						return true;
					}
				}
			}
			
		}
		
		//IF ITEM IS A WEAPON
		if(i.ItemType == ItemType.Weapon)
		{
			if(PrimaryGunSlot == null)
			{
				PrimaryGunSlot = i;
				return true;
			}
			if(SecondaryGunSlot == null)
			{
				SecondaryGunSlot = i;
				return true;
			}
		}
		
		//If Item is a sheild
		if(i.ItemType == ItemType.Sheild)
		{
			if(SheildSlot == null)
			{
				SheildSlot = i;
				return true;
			}
		}

		//if item is a valuable
		if(i.ItemType == ItemType.Valuable)
		{
			//try stack it
			
			for(Item item : Inventory)
			{
				if(item.ItemType == ItemType.Valuable)
				{
					Valuable InventoryValuable = (Valuable)(item);
					Valuable ValuableItem = (Valuable)(i);
					
					if(ValuableItem.Type == InventoryValuable.Type && InventoryValuable.Count < StackSize)
					{
						InventoryValuable.Count++;
						return true;
					}
				}
			}
		}
		
		
		// if item is a craft item
		if(i.ItemType == ItemType.Crafting)
		{
			for(Item item : Inventory)
			{
				if(item.ItemType == ItemType.Crafting)
				{
					CraftingItem InventoryCrafting = (CraftingItem)(item);
					CraftingItem CraftingItem = (CraftingItem)(i);
					
					if(CraftingItem.CraftingMaterial == InventoryCrafting.CraftingMaterial && 
							CraftingItem.CraftingRarity == InventoryCrafting.CraftingRarity &&
							InventoryCrafting.Count < StackSize)
					{
						InventoryCrafting.Count++;
						return true;
					}
				}
			}
		}
		
		// if item is ammo
		if(i.ItemType == ItemType.Ammo)
		{
			for(Item item : Inventory)
			{
				if(item.ItemType == ItemType.Ammo)
				{
					Ammo InventoryAmmo = (Ammo)(item);
					Ammo AmmoItem = (Ammo)(i);
					
					if(AmmoItem.AmmoType == InventoryAmmo.AmmoType && InventoryAmmo.Count < AmmoStackSize)
					{
						InventoryAmmo.Count += AmmoItem.Count;
						if(InventoryAmmo.Count > AmmoStackSize)
						{
							int c = InventoryAmmo.Count - AmmoStackSize;
							InventoryAmmo.Count = AmmoStackSize;
							i.Count = c;
						}
						return true;
					}
				}
			}
		}
		
		
		
		
		
		//add to inventory if theres room
		
		if(Inventory.size() < InventorySlots)
		{
			Inventory.add(i);
			return true;
		}
		else
		{
			//drop Item - player full
			return false;
		}
	}
	
	public void PrintInventory()
	{
		String Pgs;
		String Sgs;
		String Phs;
		String Shs;
		String Ss;
		
		if(PrimaryGunSlot == null)
		{
			Pgs = "Empty";
		}
		else
		{
			Pgs = PrimaryGunSlot.Name;
		}
		if(SecondaryGunSlot == null)
		{
			Sgs = "Empty";
		}
		else
		{
			Sgs = SecondaryGunSlot.Name;
		}
		if(PrimaryHealSlot == null)
		{
			Phs = "Empty";
		}
		else
		{
			Phs = PrimaryHealSlot.Count + " X " + PrimaryHealSlot.Name;
		}
		if(SecondaryHealSlot == null)
		{
			Shs = "Empty";
		}
		else
		{
			Shs = SecondaryHealSlot.Count + " X " + SecondaryHealSlot.Name;
		}
		if(SheildSlot == null)
		{
			Ss = "Empty";
		}
		else
		{
			Ss = SheildSlot.Name;
		}


		
		System.out.println("Primary Gun Slot : " + Pgs);
		System.out.println("Secondary Gun Slot : " + Sgs);
		System.out.println("Primary Heal Slot : " + Phs);
		System.out.println("Secondary Heal Slot : " + Shs);
		System.out.println("Sheild Slot : " + Ss);
		
		System.out.println("Inventory Limit : " + InventorySlots);
		System.out.println("Inventory Slots Used : " + Inventory.size());
		
		 int count = 1;
		for(Item i : Inventory)
		{
			System.out.println("Inventory Slot " + count + " : " + i.Count + " X " + i.Name);
			count++;
		}
	}
	
}
