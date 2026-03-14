package Main.Game.Item;

import Main.Game.Entity;
import Main.Game.Rarity;

public class Item extends Entity{
	public String Name;
	public Rarity Rarity;
	public int Value;
	ItemType ItemType;
	int Count;
	
	public Item () {
		// make cool items. have it make it pick it from a pool. txt files
	}
}
