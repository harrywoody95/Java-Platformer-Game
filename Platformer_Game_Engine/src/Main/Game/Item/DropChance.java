package Main.Game.Item;

import Main.Game.Rarity;

public class DropChance {
	public int MaxLoot;
	public float CommonDropChance;
	public float RareDropChance;
	public float LegendaryDropChance;
	
	public DropChance(Rarity r)
	{
		if(r == Rarity.Common)
		{
			MaxLoot = 3;
			CommonDropChance = 0.95f;
			RareDropChance = 0.04f;
			LegendaryDropChance = 0.01f;
		}
		if(r == Rarity.Rare)
		{
			MaxLoot = 4;
			CommonDropChance = 0.83f;
			RareDropChance = 0.12f;
			LegendaryDropChance = 0.05f;
		}
	}
}
