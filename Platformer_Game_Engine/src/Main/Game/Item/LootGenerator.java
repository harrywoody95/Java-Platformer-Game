package Main.Game.Item;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

import Main.Main;
import Main.Game.Rarity;

public class LootGenerator {
	
	DropChance CommonChestDropRates = new DropChance(Rarity.Common);
	DropChance RareChestDropRates = new DropChance(Rarity.Rare);
	Vector <String> CommonPool = new Vector<String>();
	Vector <String> RarePool = new Vector<String>();
	Vector <String> LegendaryPool = new Vector<String>();
	
	
	public LootGenerator() {
		
		try {
				InputStream is = getClass().getResourceAsStream("/items/Common_Loot_Pool.txt");
				BufferedReader br = new BufferedReader(new InputStreamReader(is));

				String line = br.readLine();
				
					while(line != null)
					{
						if(line.charAt(0) == '#')
						{
							line = br.readLine();
							continue;
						}
						CommonPool.add(line);
						line = br.readLine();
					}
			
					br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		try {
			InputStream is = getClass().getResourceAsStream("/items/Rare_Loot_Pool.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			String line = br.readLine();
			
				while(line != null)
				{
					if(line.charAt(0) == '#')
					{
						line = br.readLine();
						continue;
					}
					RarePool.add(line);
					line = br.readLine();
				}
		
				br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			InputStream is = getClass().getResourceAsStream("/items/Legendary_Loot_Pool.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			String line = br.readLine();
			
				while(line != null)
				{
					if(line.charAt(0) == '#')
					{
						line = br.readLine();
						continue;
					}
					LegendaryPool.add(line);
					line = br.readLine();
				}
		
				br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(CommonPool.size());
		//System.out.println(RarePool.size());
		//System.out.println(LegendaryPool.size());
	}
	
	public Vector <Item> GenerateLoot (Rarity r){
		Item item = new Item();
		Vector <Item> v = new Vector <Item>();
		int NumberOfItems = 0;
		
		if(r == Rarity.Common)
		{
			NumberOfItems = 1 + (int)(Math.random() * (CommonChestDropRates.MaxLoot + 1));
		}
		if(r == Rarity.Rare)
		{
			NumberOfItems = 1 + (int)(Math.random() * (RareChestDropRates.MaxLoot + 1));
		}
		System.out.println("Number of item to gen" + NumberOfItems);
		
		for(int x = 0; x < NumberOfItems; x++)
		{
			String s = GetRandomItem(r);
			//System.out.println(s);
			
			String[] Data = s.split(" ");
			//System.out.println(Data[0]);
			
			ItemType Type = Item.StringToItemType(Data[0].strip());
			//System.out.println(Data[0].strip());
			//System.out.println(Type);
			
			 if (Type == ItemType.Ammo)
			 {
				  Ammo a = new Ammo(Item.StringToAmmoType(Data[1].strip()));
				  v.add(a); 
				  //System.out.println("Added " + a.Name);
				  continue; 
			 }
			 if (Type == ItemType.Crafting)
			 {
				  CraftingItem i = new CraftingItem(Item.StringToCraftingMaterialRarity(Data[1].strip()), Item.StringToCraftingMaretial(Data[2].strip()));
				  v.add(i); 
				 // System.out.println("Added " + i.Name);
				  continue;
			 }
			 if (Type == ItemType.Weapon)
			 {
				  Weapon w = new Weapon(Item.StringToRarity(Data[1]), Item.StringToWeaponType(Data[2].strip()));
				  v.add(w); 
				 // System.out.println("Added " + w.Name);
				  continue;
			 }
			 if (Type == ItemType.Healing)
			 {
				  Healing h = new Healing(Item.StringToHealingType(Data[1].strip()));
				  v.add(h); 
				  //System.out.println("Added " + h.Name);
				  continue;
			 }
			 if (Type == ItemType.Valuable)
			 {
				  Valuable i = new Valuable(Item.StringToValuableType(Data[1].strip()));
				  v.add(i); 
				 // System.out.println("Added " + i.Name);
				  continue;
			 }
		}
		
		
		return v;
	}
	
	private String GetRandomItem(Rarity r) {
	    float RandomNumber = (float)Math.random();
	    DropChance dc = CommonChestDropRates;
	    if(r == Rarity.Rare)
	    {
	    	dc = RareChestDropRates;
	    }

	    if (RandomNumber < dc.LegendaryDropChance) {
	        return LegendaryPool.get((int)(Math.random() * LegendaryPool.size()));
	    }
	    else if (RandomNumber < dc.LegendaryDropChance + dc.RareDropChance) {
	        return RarePool.get((int)(Math.random() * RarePool.size()));
	    }
	    else {
	        return CommonPool.get((int)(Math.random() * CommonPool.size()));
	    }
	}

	
	

}
