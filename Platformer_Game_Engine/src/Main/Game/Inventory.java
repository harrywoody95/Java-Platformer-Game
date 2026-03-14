package Main.Game;

import java.util.Vector;

import Main.Game.Item.Item;

public class Inventory {
	public int StackSize = 5;
	public int InventorySlots = 12;
	
	Item SheildSlot;
	Item PrimaryGunSlot;
	Item SecondaryGunSlot;
	Item PrimaryHealSlot;
	Item SecondaryHealSlot;
	
	Vector<Item> Inventory = new Vector<Item>();
	
}
