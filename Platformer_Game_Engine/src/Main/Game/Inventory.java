package Main.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import Main.Engine.Game;
import Main.Engine.UI.UI;
import Main.Engine.UI.UIElement;
import Main.Game.Item.Ammo;
import Main.Game.Item.CraftingItem;
import Main.Game.Item.Healing;
import Main.Game.Item.Item;
import Main.Game.Item.ItemType;
import Main.Game.Item.Valuable;
import Math.Vec2;

public class Inventory {
	public int StackSize = 5;
	public int AmmoStackSize = 50;
	public int UsableInventorySlots = 15;
	
	public Item SheildSlot = null;
	public Item PrimaryGunSlot = null;
	public Item SecondaryGunSlot = null;
	public Item PrimaryHealSlot = null;
	public Item SecondaryHealSlot = null;
	
	public Vector<Item> Inventory = new Vector<Item>();
	
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
		
		if(Inventory.size() < UsableInventorySlots)
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
		
		System.out.println("Inventory Limit : " + UsableInventorySlots);
		System.out.println("Inventory Slots Used : " + Inventory.size());
		
		 int count = 1;
		for(Item i : Inventory)
		{
			System.out.println("Inventory Slot " + count + " : " + i.Count + " X " + i.Name);
			count++;
		}
	}
	
	public void SyncInventoryUI(Game g)
	{
		Inventory Inventory = g.Player.Inventory;

		//set usable and unusable slots
		try {
			BufferedImage CommonSlot = ImageIO.read(getClass().getResourceAsStream("/ui/Common_Slot_Brown.png"));
			BufferedImage UnusableSlot = ImageIO.read(getClass().getResourceAsStream("/ui/Unusable_Slot_Brown.png"));
			BufferedImage HealSlot = ImageIO.read(getClass().getResourceAsStream("/ui/Healing_Icon_Brown.png"));
			BufferedImage SheildSlot = ImageIO.read(getClass().getResourceAsStream("/ui/Sheild_Icon_Brown.png"));
			BufferedImage WeaponSlot = ImageIO.read(getClass().getResourceAsStream("/ui/Weapon_Icon_Brown.png"));
			BufferedImage RareSlot = ImageIO.read(getClass().getResourceAsStream("/ui/Rare_Slot.png"));
			BufferedImage LegendarySlot = ImageIO.read(getClass().getResourceAsStream("/ui/Legendary_Slot.png"));
			
			for(UI ui : g.UIList)
			{
				if(ui.Name.equals("Inventory"))
				{
					for(int i = 0; i < UsableInventorySlots; i++)
					{	
						UIElement slot = ui.GetUIElement("Slot" + (i + 1));
						if(slot.Texture != CommonSlot )
						{
							slot.Texture = ImageIO.read(getClass().getResourceAsStream("/ui/Common_Slot_Brown.png"));
						}

					}
					
					for(int i = UsableInventorySlots; i < 25; i++)
					{
						UIElement slot = ui.GetUIElement("Slot" + (i + 1));
						if(slot.Texture != UnusableSlot)
						{
							slot.Texture = ImageIO.read(getClass().getResourceAsStream("/ui/Unusable_Slot_Brown.png"));
						}

					}
					
					//heal slot 1
					UIElement hslot1 = ui.GetUIElement("HealSlot1");
					if(Inventory.PrimaryHealSlot == null)
					{
						if(hslot1.Texture != HealSlot)
						{
							hslot1.Texture = ImageIO.read(getClass().getResourceAsStream("/ui/Healing_Icon_Brown.png"));
						}
					}
					else
					{
						SetSlotRarity(Inventory.PrimaryHealSlot, hslot1);
					}
					
					//heal slot 2
					UIElement hslot2 = ui.GetUIElement("HealSlot2");
					if(Inventory.SecondaryHealSlot == null)
					{
						if(hslot2.Texture != HealSlot)
						{
							hslot2.Texture = ImageIO.read(getClass().getResourceAsStream("/ui/Healing_Icon_Brown.png"));
						}
					}
					else
					{
						SetSlotRarity(Inventory.SecondaryHealSlot, hslot2);
					}
					
					//Weapon slot 1
					UIElement wslot1 = ui.GetUIElement("WeaponSlot1");
					if(Inventory.PrimaryGunSlot == null)
					{
						if(wslot1.Texture != WeaponSlot)
						{
							wslot1.Texture = ImageIO.read(getClass().getResourceAsStream("/ui/Weapon_Icon_Brown.png"));
						}
					}
					else
					{
						SetSlotRarity(Inventory.PrimaryGunSlot, wslot1);
					}
					
					//Weapoon slot 2
					UIElement wslot2 = ui.GetUIElement("WeaponSlot2");
					if(Inventory.SecondaryGunSlot == null)
					{
						if(wslot2.Texture != WeaponSlot)
						{
							wslot2.Texture = ImageIO.read(getClass().getResourceAsStream("/ui/Weapon_Icon_Brown.png"));
						}
					}
					else
					{
						SetSlotRarity(Inventory.SecondaryGunSlot, wslot2);
					}
					
					//Sheild slot
					UIElement sslot = ui.GetUIElement("SheildSlot");
					if(Inventory.SheildSlot == null)
					{
						if(sslot.Texture != SheildSlot)
						{
							sslot.Texture = ImageIO.read(getClass().getResourceAsStream("/ui/Sheild_Icon_Brown.png"));
						}
					}
					else
					{
						SetSlotRarity(Inventory.SheildSlot, sslot);
					}
					
					
					// now all inventory Items
					for(int i = 0; i < Inventory.Inventory.size(); i++)
					{	
						UIElement slot = ui.GetUIElement("Slot" + (i + 1));
						if(Inventory.Inventory.get(i) == null)
						{
							continue;
						}
						else
						{
							SetSlotRarity(Inventory.Inventory.get(i), slot);
						}

					}
					
					
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void SetSlotRarity(Item i, UIElement Slot)
	{
		try {
			BufferedImage CommonSlot = ImageIO.read(getClass().getResourceAsStream("/ui/Common_Slot_Brown.png"));
			BufferedImage RareSlot = ImageIO.read(getClass().getResourceAsStream("/ui/Rare_Slot.png"));
			BufferedImage LegendarySlot = ImageIO.read(getClass().getResourceAsStream("/ui/Legendary_Slot.png"));
			
			if(i == null)
			{
				return;
			}
			
			if(i.Rarity == Rarity.Common)
			{
				if(Slot.Texture != CommonSlot)
				{
					Slot.Texture = ImageIO.read(getClass().getResourceAsStream("/ui/Common_Slot_Brown.png"));;
				}
			}
			else if(i.Rarity == Rarity.Rare)
			{
				if(Slot.Texture != RareSlot)
				{
					Slot.Texture = ImageIO.read(getClass().getResourceAsStream("/ui/Rare_Slot.png"));
				}
			}
			else {
				if(Slot.Texture != LegendarySlot)
				{
					Slot.Texture = ImageIO.read(getClass().getResourceAsStream("/ui/Legendary_Slot.png"));
				}
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void DrawInventoryUI(UI ui, Game g, Graphics2D g2)
	{
		ui.Draw(g2, g);
		
		Vec2 bitSize = new Vec2((int)(64 / 1.1),(int)(64 / 1.1));
		int gap = (64 - bitSize.x) / 2; 
		int TextSize = 14;
		g2.setFont(new Font("SansSerif", Font.BOLD, TextSize));
		g2.setColor(new Color(210, 210, 210));
		
		for(int i = 0; i < Inventory.size(); i++)
		{	
			UIElement slot = ui.GetUIElement("Slot" + (i + 1));
			Item item = Inventory.get(i);
			if(Inventory.get(i) == null) 
			{
				continue;
			}
			else
			{
				g2.drawImage(item.Sprite.Texture, (int)(slot.Position.x + gap), (int)(slot.Position.y), bitSize.x, bitSize.y, null);
				if(item.ItemType != ItemType.Sheild && item.ItemType != ItemType.Weapon)
				{
					int x = 0;
					if(item.Count < 10) {
						 x = (int) slot.Position.x + (slot.Bitsize.x * slot.Scale) - 22;
					}
					else
					{
						x = (int) slot.Position.x + (slot.Bitsize.x * slot.Scale) - 30;
					}
					int y = (int) slot.Position.y + bitSize.y;
					g2.drawString("X" + item.Count, x,y);
				}
			}

		}
		
		//heal slot 1
		UIElement hslot1 = ui.GetUIElement("HealSlot1");
		Item hitem1 = PrimaryHealSlot;
		if(hitem1 == null)
		{
		}
		else
		{
			g2.drawImage(hitem1.Sprite.Texture, (int)(hslot1.Position.x + gap), (int)(hslot1.Position.y), bitSize.x, bitSize.y, null);
			
			int x = 0;
			if(hitem1.Count < 10) {
				 x = (int) hslot1.Position.x + (hslot1.Bitsize.x * hslot1.Scale) - 22;
			}
			else
			{
				x = (int) hslot1.Position.x + (hslot1.Bitsize.x * hslot1.Scale) - 30;
			}
			int y = (int) hslot1.Position.y + bitSize.y;
			g2.drawString("X" + hitem1.Count, x,y);
		}
		
		//heal slot 2
		UIElement hslot2 = ui.GetUIElement("HealSlot2");
		Item hitem2 = SecondaryHealSlot;
		if(hitem2 == null)
		{
		}
		else
		{
			g2.drawImage(hitem2.Sprite.Texture, (int)(hslot2.Position.x + gap), (int)(hslot2.Position.y), bitSize.x, bitSize.y, null);
			
			int x = 0;
			if(hitem2.Count < 10) {
				 x = (int) hslot2.Position.x + (hslot2.Bitsize.x * hslot2.Scale) - 22;
			}
			else
			{
				x = (int) hslot2.Position.x + (hslot2.Bitsize.x * hslot2.Scale) - 30;
			}
			int y = (int) hslot2.Position.y + bitSize.y;
			g2.drawString("X" + hitem2.Count, x,y);
		}
		
		//gun slot 1
		UIElement wslot1 = ui.GetUIElement("WeaponSlot1");
		Item witem1 = PrimaryGunSlot;
		if(witem1 == null)
		{
		}
		else
		{
			g2.drawImage(witem1.Sprite.Texture, (int)(wslot1.Position.x + gap), (int)(wslot1.Position.y), bitSize.x, bitSize.y, null);
		}
		
		//gun slot 2
		UIElement wslot2 = ui.GetUIElement("WeaponSlot2");
		Item witem2 = SecondaryGunSlot;
		if(witem2 == null)
		{
		}
		else
		{
			g2.drawImage(witem2.Sprite.Texture, (int)(wslot2.Position.x + gap), (int)(wslot2.Position.y), bitSize.x, bitSize.y, null);
		}
		
		//gSheild slot
		UIElement sslot = ui.GetUIElement("SheildSlot");
		Item sitem = SheildSlot;
		if(sitem == null)
		{
		}
		else
		{
			g2.drawImage(sitem.Sprite.Texture, (int)(sslot.Position.x + gap), (int)(sslot.Position.y), bitSize.x, bitSize.y, null);
		}
		
	}
	
	public boolean IsSlotUsable(UIElement e)
	{
		String Name = e.Name;
		
		if(Name.startsWith("Weapon") || Name.startsWith("Heal") || Name.startsWith("Sheild"))
		{
			return true;
		}
		
		String[] data = Name.split("t");
		
		int SlotIndex = Integer.parseInt(data[1]);
		
		if (SlotIndex <= UsableInventorySlots)
		{
			return true;
		}
		return false;
	}
	
	public boolean IsSlotEmpty(UIElement e)
	{
		String Name = e.Name;
		
		if(Name.equals("WeaponSlot1"))
		{
			return PrimaryGunSlot == null;
		}
		if(Name.equals("WeaponSlot2"))
		{
			return SecondaryGunSlot == null;
		}
		if(Name.equals("HealSlot1"))
		{
			return PrimaryHealSlot == null;
		}
		if(Name.equals("HealSlot2"))
		{
			return SecondaryHealSlot == null;
		}
		if(Name.equals("SheildSlot"))
		{
			return SheildSlot == null;
		}
		
		String[] data = Name.split("t");
		
		int SlotIndex = Integer.parseInt(data[1]);
		
		
		return SlotIndex > Inventory.size();

	}
	
	public Item GetItemInSlot(UIElement e)
	{
		if(e.Name.equals("WeaponSlot1"))
		{
			return PrimaryGunSlot;
		}
		if(e.Name.equals("WeaponSlot2"))
		{
			return SecondaryGunSlot;
		}
		if(e.Name.equals("HealSlot1"))
		{
			return PrimaryHealSlot;
		}
		if(e.Name.equals("HealSlot2"))
		{
			return SecondaryHealSlot;
		}
		if(e.Name.equals("SheildSlot"))
		{
			return SheildSlot;
		}
		
		String[] data = e.Name.split("t");
		
		int SlotIndex = Integer.parseInt(data[1]);
		Item i = Inventory.get(SlotIndex - 1);
		return i;
	}
}
