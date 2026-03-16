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
			CommonDropChance = 0.9f;
			RareDropChance = 0.1f;
			LegendaryDropChance = 0.0f;
		}
		if(r == Rarity.Rare)
		{
			MaxLoot = 4;
			CommonDropChance = 0.7f;
			RareDropChance = 0.2f;
			LegendaryDropChance = 0.1f;
		}
	}
}
