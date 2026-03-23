package Main.Engine.UI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

import javax.imageio.ImageIO;

import Main.Engine.Game;
import Main.Game.Direction;
import Main.Game.Inventory;
import Main.Game.State;
import Main.Game.Item.Ammo;
import Main.Game.Item.CraftingItem;
import Main.Game.Item.Healing;
import Main.Game.Item.Item;
import Main.Game.Item.ItemType;
import Main.Game.Item.Sheild;
import Main.Game.Item.Valuable;
import Math.Vec2;
import Math.Vec2f;

public class UI {
	public String Name = "";
	public Vector <UIElement> UIElements = new Vector <UIElement>();
	boolean Active = false;
	public DraggedItem Dragging = new DraggedItem();
	
	public UI (String Name, Vector <UIElement> UIElements)
	{
		this.Name = Name;
		this.UIElements = UIElements;
	}
	
	public void Draw(Graphics2D g2, Game g)
	{
		for(UIElement e : UIElements)
		{
			e.Draw(g2, g);
		}
		
		if(Dragging.Item != null)
		{
			Vec2 CenterPos = new Vec2 (0,0);
			CenterPos.x = (int)g.UserInput.MouseX - ((Dragging.Item.Sprite.Bitsize.x * 2) / 2);
			CenterPos.y = (int)g.UserInput.MouseY - ((Dragging.Item.Sprite.Bitsize.y * 2) / 2);
			g2.drawImage(Dragging.Item.Sprite.Texture, CenterPos.x, CenterPos.y, Dragging.Item.Sprite.Bitsize.x * 2, Dragging.Item.Sprite.Bitsize.y * 2, null);
		}
	}
	
	public void LoadInventoryUI(String Path)
	{
		try {
			InputStream is = getClass().getResourceAsStream(Path);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String Line = br.readLine();
			while (Line != null)
			{
				if (Line.charAt(0) == '#')
				{
					Line = br.readLine();
					continue;
				}
				
				else
				{
					String[] s = Line.split(" ");
					String Name = "";
					BufferedImage i = null;
					UIType Type;
					String Text = "";
					int TextSize = 0;
					Vec2 Bitsize = new Vec2(0, 0);
					Vec2f Position = new Vec2f(0.0f, 0.0f);
					int Scale = 0;
					
					//order is name, type, image path, text, textsize, bitsize x then y, position x then y, Scale
					
					//name
					Name = s[0].strip();
					
					// type
					
					if(s[1].strip().equals("Sprite"))
					{
						Type = UIType.Sprite;
					}
					else {
						Type = UIType.Text;
					}
					
					//Image path
					if(!s[2].strip().equals("null"))
					{
						i = ImageIO.read(getClass().getResourceAsStream(s[2].strip()));
					}
					
					//text
					String tmp = s[3].strip().replace("'", "");
					Text = tmp.replace("~", " ");
					
					
					//textsize
					TextSize = Integer.parseInt(s[4].strip());
					
					//Bitsize
					int x = Integer.parseInt(s[5].strip());
					int y = Integer.parseInt(s[6].strip());
					
					Bitsize.x = x;
					Bitsize.y = y;
					
					//position
					
					int px = Integer.parseInt(s[7].strip());
					int py = Integer.parseInt(s[8].strip());
					
					Position.x = px;
					Position.y = py;
					
					Scale = Integer.parseInt(s[9].strip());
					
					UIElement e = new UIElement(Type, i, Name, Text, TextSize, Bitsize, Position, Scale);
					UIElements.add(e);
					
					
					
					Line = br.readLine();
				}
		
			}

				//make grid.
				//25 max
				int index = 0;
				int posx = 545;
				int posy = 220;
				Vec2 bitsize = new Vec2(32,32);
				Vec2f Position = new Vec2f(posx,posy);
			for(int x = 0; x < 25; x++)
			{
				BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/ui/Common_Slot_Brown.png"));
				Vec2f Pos = new Vec2f(Position.x,Position.y);
				UIElement ui = new UIElement(UIType.Sprite, image, "Slot"+(index + 1), "", 0, bitsize, Pos, 2);
				UIElements.add(ui);
				Position.x += 90;
				index++;
				
				if(index % 5 == 0)
				{
					Position.x = posx;
					Position.y += 90;
				}
			}

					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	public UIElement GetUIElement(String Name)
	{
		for(UIElement e : UIElements)
		{
			if(e.Name.equals(Name))
			{
				return e;
			}
		}
		return null;
	}
	
	public void HandleUserInput(Game g)
	{
		//inventory input
		if(Name.equals("Inventory"))
		{
			if(g.UserInput.Key_Tab_Released)
			{
				g.InventoryUIActive = !g.InventoryUIActive;
			}
			
			if(g.UserInput.Mouse_Left_Pressed && Dragging.Item == null)
			{
				for(UIElement e : UIElements)
				{
					//only get slots that are good
					if(!e.Name.startsWith("Weapon") && !e.Name.startsWith("Slot") && !e.Name.startsWith("Heal") && !e.Name.startsWith("Sheild"))
					{
						continue;
					}
					
					if(e.Position.x <= g.UserInput.MouseX && (int)(e.Position.x + (e.Bitsize.x * e.Scale)) >= g.UserInput.MouseX &&
							e.Position.y <= g.UserInput.MouseY && (int)(e.Position.y + (e.Bitsize.y * e.Scale)) >= g.UserInput.MouseY)
					{
						if(g.Player.Inventory.IsSlotUsable(e) && !g.Player.Inventory.IsSlotEmpty(e))
						{
							Dragging.Item = g.Player.Inventory.GetItemInSlot(e);
							Dragging.OriginMousePosX = g.UserInput.MouseX;
							Dragging.OriginMousePosY = g.UserInput.MouseY;
							System.out.println("dragging : " + Dragging.Item.Name);
						}
					}
				}
			}
			
			if(g.UserInput.Mouse_Left_Released)
			{
				if(Dragging.Item != null)
				{
				
				
				for(UIElement e : UIElements)
				{
					//only get slots that are good
					if(!e.Name.startsWith("Weapon") && !e.Name.startsWith("Slot") && !e.Name.startsWith("Heal") && !e.Name.startsWith("Sheild"))
					{
						continue;
					}
					
					if(e.Position.x <= g.UserInput.MouseX && (int)(e.Position.x + (e.Bitsize.x * e.Scale)) >= g.UserInput.MouseX &&
							e.Position.y <= g.UserInput.MouseY && (int)(e.Position.y + (e.Bitsize.y * e.Scale)) >= g.UserInput.MouseY)
					{
						//if not usable just exit loop
						if(!g.Player.Inventory.IsSlotUsable(e))
						{
							break;
						}
						DropDraggedItem(g, e, Dragging, this);
						g.Player.Inventory.SyncInventoryUI(g);
					}
				}
				
				Dragging.Item = null;
				}
			}
		}
	}
	
	// THIS NEEDS REFACTORING ITS FAR TO BIG
	public void DropDraggedItem(Game g, UIElement e, DraggedItem DraggedItem, UI ui)
	{
		//if the slot is empty
		Inventory Inventory = g.Player.Inventory;
		
		UIElement DraggedFrom = GetSlotAt(ui, DraggedItem.OriginMousePosX, DraggedItem.OriginMousePosY);
		UIElement DraggedTo = e;
		
		if(DraggedTo == null || DraggedFrom == null)
		{
			return;
		}
		
		int DraggedFromSlotIndex = 0;
		int DraggedToSlotIndex = 0;

		if(g.Player.Inventory.IsSlotEmpty(e))
		{
			//find the slot im trying to drop in.
			
			if(DraggedTo.Name.equals("WeaponSlot1"))
			{
				//take it away from inventory, place it in this slot
				if(DraggedItem.Item.ItemType == ItemType.Weapon)
				{
					Inventory.PrimaryGunSlot = DraggedItem.Item;
					
					if(DraggedFrom.Name.equals("WeaponSlot2"))
					{
						Inventory.SecondaryGunSlot = null;
						return;
					}
					else
					{
						DraggedFromSlotIndex = GetSlotIndex(DraggedFrom);
						Inventory.Inventory.remove(DraggedFromSlotIndex - 1);
						return;
					}		
				}
			}
			
			if(e.Name.equals("WeaponSlot2"))
			{
				//take it away from inventory, place it in this slot
				if(DraggedItem.Item.ItemType == ItemType.Weapon)
				{
					Inventory.SecondaryGunSlot = DraggedItem.Item;

					if(DraggedFrom.Name.equals("WeaponSlot1"))
					{
						Inventory.PrimaryGunSlot = null;
						return;
					}
					else
					{
						DraggedFromSlotIndex = GetSlotIndex(DraggedFrom);
						Inventory.Inventory.remove(DraggedFromSlotIndex - 1);
						return;
					}		
				}
			}
			if(e.Name.equals("HealSlot1"))
			{
				//take it away from inventory, place it in this slot
				if(DraggedItem.Item.ItemType == ItemType.Healing)
				{
					Inventory.PrimaryHealSlot = DraggedItem.Item;

					if(DraggedFrom.Name.equals("HealSlot2"))
					{
						Inventory.SecondaryHealSlot = null;
						return;
					}
					else
					{
						DraggedFromSlotIndex = GetSlotIndex(DraggedFrom);
						Inventory.Inventory.remove(DraggedFromSlotIndex - 1);
						return;
					}
							

				}
			}
			if(e.Name.equals("HealSlot2"))
			{
				//take it away from inventory, place it in this slot
				if(DraggedItem.Item.ItemType == ItemType.Healing)
				{
					Inventory.SecondaryHealSlot = DraggedItem.Item;
					if(DraggedFrom.Name.equals("HealSlot1"))
					{
						Inventory.PrimaryHealSlot = null;
						return;
					}
					else
					{
						DraggedFromSlotIndex = GetSlotIndex(DraggedFrom);
						Inventory.Inventory.remove(DraggedFromSlotIndex - 1);
						return;
					}
				}
			}
			if(e.Name.equals("SheildSlot"))
			{
				//take it away from inventory, place it in this slot
				if(DraggedItem.Item.ItemType == ItemType.Sheild)
				{
					Inventory.SheildSlot = DraggedItem.Item;
					

					DraggedFromSlotIndex = GetSlotIndex(DraggedFrom);
					Inventory.Inventory.remove(DraggedFromSlotIndex - 1);
					return;
				}
			}
			
			
	//add to the empty slot in inventory / the next empty slot

			//add the item to inventory
			Inventory.Inventory.add(DraggedItem.Item);
			
			// remove the item from where it came from
			if(DraggedFrom.Name.equals("WeaponSlot1"))
			{
				Inventory.PrimaryGunSlot = null;
				return;
			}
			if(DraggedFrom.Name.equals("WeaponSlot2"))
			{
				Inventory.SecondaryGunSlot = null;
				return;
			}
			if(DraggedFrom.Name.equals("HealSlot1"))
			{
				Inventory.PrimaryHealSlot = null;
				return;
			}
			if(DraggedFrom.Name.equals("HealSlot2"))
			{
				Inventory.SecondaryHealSlot = null;
				return;
			}
			if(DraggedFrom.Name.equals("SheildSlot"))
			{
				Inventory.SheildSlot = null;
				return;
			}
			
			DraggedFromSlotIndex = GetSlotIndex(DraggedFrom);
			Inventory.Inventory.remove(DraggedFromSlotIndex - 1);
			return;
					
		}
		
		//if the slot is not empty
		else
		{
			if(e.Name.equals("WeaponSlot1"))
			{
				if(DraggedItem.Item.ItemType == ItemType.Weapon)
				{
					if(DraggedItem.Item == Inventory.PrimaryGunSlot)
					{
						return;
					}
					Item i = Inventory.PrimaryGunSlot;
					Inventory.PrimaryGunSlot = DraggedItem.Item;
					
					if(DraggedFrom.Name.equals("WeaponSlot2"))
					{
						Inventory.SecondaryGunSlot = i;
						return;
					}
					else
					{
						DraggedFromSlotIndex = GetSlotIndex(DraggedFrom);
						Inventory.Inventory.remove(DraggedFromSlotIndex - 1);
						Inventory.Inventory.add(i);
						return;
					}		
				}
			}
			if(e.Name.equals("WeaponSlot2"))
			{
				if(DraggedItem.Item.ItemType == ItemType.Weapon)
				{
					if(DraggedItem.Item == Inventory.SecondaryGunSlot)
					{
						return;
					}
					Item i = Inventory.SecondaryGunSlot;
					Inventory.SecondaryGunSlot = DraggedItem.Item;
					
					if(DraggedFrom.Name.equals("WeaponSlot1"))
					{
						Inventory.PrimaryGunSlot = i;
						return;
					}
					else
					{
						DraggedFromSlotIndex = GetSlotIndex(DraggedFrom);
						Inventory.Inventory.remove(DraggedFromSlotIndex - 1);
						Inventory.Inventory.add(i);
						return;
					}
				}
			}
			if(e.Name.equals("HealSlot1"))
			{
				if(DraggedItem.Item.ItemType == ItemType.Healing)
				{
					Healing Primary = (Healing)Inventory.PrimaryHealSlot;
					Healing Drag = (Healing)DraggedItem.Item; 
					if(Drag.Type != Primary.Type)
					{
						if(DraggedItem.Item == Inventory.PrimaryHealSlot)
						{
							return;
						}
						Item i = Inventory.PrimaryHealSlot;
						Inventory.PrimaryHealSlot = DraggedItem.Item;

						if(DraggedFrom.Name.equals("HealSlot2"))
						{
							Inventory.SecondaryHealSlot = i;
							return;
						}
						else
						{
							DraggedFromSlotIndex = GetSlotIndex(DraggedFrom);
							Inventory.Inventory.remove(DraggedFromSlotIndex - 1);
							Inventory.Inventory.add(i);
							return;
						}
					}
					else
					{
						if(DraggedItem.Item == Inventory.PrimaryHealSlot)
						{
							return;
						}
						//they are the same type
						if(DraggedItem.Item.Count + Inventory.PrimaryHealSlot.Count <= Inventory.StackSize)
						{
							Inventory.PrimaryHealSlot.Count += DraggedItem.Item.Count;
							//Inventory.SyncInventoryUI(g);
							return;
						}
						else
						{
							while(Inventory.PrimaryHealSlot.Count < Inventory.StackSize && DraggedItem.Item.Count > 0)
							{
								Inventory.PrimaryHealSlot.Count++;
								DraggedItem.Item.Count--;
							}
							if(DraggedItem.Item.Count > 0)
							{
								Inventory.Inventory.add(DraggedItem.Item);
							}
							
							return;
						}
					}
				}
			}
			if(e.Name.equals("HealSlot2"))
			{
				if(DraggedItem.Item.ItemType == ItemType.Healing)
				{
					Healing Secondary = (Healing)Inventory.SecondaryHealSlot;
					Healing Drag = (Healing)DraggedItem.Item; 
					if(Drag.Type != Secondary.Type)
					{
						if(DraggedItem.Item == Inventory.SecondaryHealSlot)
						{
							return;
						}
						Item i = Inventory.SecondaryHealSlot;
						Inventory.SecondaryHealSlot = DraggedItem.Item;
						if(DraggedFrom.Name.equals("HealSlot1"))
						{
							Inventory.PrimaryHealSlot = i;
						}
						else
						{
							DraggedFromSlotIndex = GetSlotIndex(DraggedFrom);
							Inventory.Inventory.remove(DraggedFromSlotIndex - 1);
							Inventory.Inventory.add(i);
							return;
						}
					}
					else
					{
						if(DraggedItem.Item == Inventory.SecondaryHealSlot)
						{
							return;
						}
						//they are the same type
						if(DraggedItem.Item.Count + Inventory.SecondaryHealSlot.Count <= Inventory.StackSize)
						{
							Inventory.SecondaryHealSlot.Count += DraggedItem.Item.Count;
							return;
						}
						else
						{
							if(Inventory.SecondaryHealSlot == DraggedItem.Item)
							{
								return;
							}
							while(Inventory.SecondaryHealSlot.Count < Inventory.StackSize && DraggedItem.Item.Count > 0)
							{
								Inventory.SecondaryHealSlot.Count++;
								DraggedItem.Item.Count--;
							}
							if(DraggedItem.Item.Count > 0)
							{
								Inventory.Inventory.add(DraggedItem.Item);
							}
							
							return;
						}
					}
				}
			}
			if(e.Name.equals("SheildSlot"))
			{
				if(DraggedItem.Item.ItemType == ItemType.Sheild)
				{
					if(DraggedItem.Item == Inventory.SheildSlot)
					{
						return;
					}
					Item i = Inventory.SheildSlot;
					Inventory.SheildSlot = DraggedItem.Item;
					DraggedFromSlotIndex = GetSlotIndex(DraggedFrom);
					Inventory.Inventory.remove(DraggedFromSlotIndex - 1);
					Inventory.Inventory.add(i);
					return;
				}
			}
			
			DraggedToSlotIndex = GetSlotIndex(DraggedTo);
			Item i = Inventory.Inventory.get(DraggedToSlotIndex - 1);

			if(i.ItemType == DraggedItem.Item.ItemType)
			{
				if(DraggedItem.Item.ItemType == ItemType.Ammo)
				{
					Ammo exisitingA = (Ammo)(i);
					Ammo DraggingA = (Ammo)(DraggedItem.Item);
					if(DraggingA == exisitingA)
					{
						return;
					}
					if(exisitingA.AmmoType == DraggingA.AmmoType)
					{
						if(exisitingA.Count < Inventory.AmmoStackSize)
						{
							while(exisitingA.Count < Inventory.AmmoStackSize && DraggingA.Count > 0)
							{
								exisitingA.Count++;
								DraggingA.Count--;
							}
							if(DraggingA.Count != 0)
							{
								Inventory.Inventory.add(DraggingA);
								return;
							}
						}
						else
						{
							Inventory.Inventory.add(DraggingA);
							return;
						}
					}
					
				}
				else if(DraggedItem.Item.ItemType == ItemType.Crafting)
				{
					CraftingItem exisitingA = (CraftingItem)(i);
					CraftingItem DraggingA = (CraftingItem)(DraggedItem.Item);
					if(DraggingA == exisitingA)
					{
						return;
					}
					if(exisitingA.CraftingMaterial == DraggingA.CraftingMaterial && exisitingA.CraftingRarity == DraggingA.CraftingRarity)
					{
						if(exisitingA.Count < Inventory.StackSize)
						{
							while(exisitingA.Count < Inventory.StackSize && DraggingA.Count > 0)
							{
								exisitingA.Count++;
								DraggingA.Count--;
							}
							if(DraggingA.Count != 0)
							{
								Inventory.Inventory.add(DraggingA);
								return;
							}
						}
						else
						{
							Inventory.Inventory.add(DraggingA);
							return;
						}
					}
				}
				else if(DraggedItem.Item.ItemType == ItemType.Healing)
				{
					Healing exisitingA = (Healing)(i);
					Healing DraggingA = (Healing)(DraggedItem.Item);
					if(DraggingA == exisitingA)
					{
						return;
					}
					if(exisitingA.Type == DraggingA.Type)
					{
						if(exisitingA.Count < Inventory.StackSize)
						{
							while(exisitingA.Count < Inventory.StackSize && DraggingA.Count > 0)
							{
								exisitingA.Count++;
								DraggingA.Count--;
							}
							if(DraggingA.Count != 0)
							{
								Inventory.Inventory.add(DraggingA);
								return;
							}
						}
						else
						{
							Inventory.Inventory.add(DraggingA);
							return;
						}
					}
				}
				else if(DraggedItem.Item.ItemType == ItemType.Sheild)
				{
					if(DraggedItem.Item == i)
					{
						return;
					}
					Inventory.Inventory.add(DraggedItem.Item);
					return;

				}
				if(DraggedItem.Item.ItemType == ItemType.Valuable)
				{
					Valuable exisitingA = (Valuable)(i);
					Valuable DraggingA = (Valuable)(DraggedItem.Item);
					if(DraggingA == exisitingA)
					{
						return;
					}
					if(exisitingA.Type == DraggingA.Type)
					{
						if(exisitingA.Count < Inventory.StackSize)
						{
							while(exisitingA.Count < Inventory.StackSize && DraggingA.Count > 0)
							{
								exisitingA.Count++;
								DraggingA.Count--;
							}
							if(DraggingA.Count != 0)
							{
								Inventory.Inventory.add(DraggingA);
								return;
							}
						}
						else
						{
							Inventory.Inventory.add(DraggingA);
							return;
						}
					}
				}
				else if(DraggedItem.Item.ItemType == ItemType.Weapon)
				{
					
					if(DraggedItem.Item == i)
					{
						return;
					}
					Inventory.Inventory.add(DraggedItem.Item);
					return;
				}

			
		
			}
			
			//not the same so swap the items
			Item item1 = null;
			Item item2 = null;
			String[] data1 = null;
			int SlotIndex1 = 0;
			String[] data2 = null;
			int SlotIndex2 = 0;
			
			for(UIElement element : ui.UIElements)
			{
				//only get slots that are good
				if(!element.Name.startsWith("Weapon") && !element.Name.startsWith("Slot") && !element.Name.startsWith("Heal") && !element.Name.startsWith("Sheild"))
				{
					continue;
				}
				
				if(element.Position.x <= DraggedItem.OriginMousePosX && (int)(element.Position.x + (element.Bitsize.x * element.Scale)) >= DraggedItem.OriginMousePosX &&
						element.Position.y <= DraggedItem.OriginMousePosY && (int)(element.Position.y + (element.Bitsize.y * element.Scale)) >= DraggedItem.OriginMousePosY)
				{
					//element is the thing to swap with
					data1 = element.Name.split("t");
					SlotIndex1 = Integer.parseInt(data1[1]);
				    item1 = Inventory.Inventory.get(SlotIndex1 - 1);
					
				}
				
				if(element.Position.x <= g.UserInput.MouseX && (int)(element.Position.x + (element.Bitsize.x * element.Scale)) >= g.UserInput.MouseX &&
						element.Position.y <= g.UserInput.MouseY && (int)(element.Position.y + (element.Bitsize.y * element.Scale)) >= g.UserInput.MouseY)
				{
					//element is the thing to swap with
					data2 = element.Name.split("t");
					SlotIndex2 = Integer.parseInt(data2[1]);
					
					item2 = Inventory.Inventory.get(SlotIndex2 - 1);

					
				}
				
			}
			
			if(item1 == null || item2 == null)
			{
				return;
			}
			
			Inventory.Inventory.set(SlotIndex1 - 1, item2);
			Inventory.Inventory.set(SlotIndex2 - 1, item1);
			return;
		}
	}
	
	private UIElement GetSlotAt( UI ui, int mouseX, int mouseY)
	{
	    for (UIElement element : ui.UIElements)
	    {
	        if (!element.Name.startsWith("Weapon") &&
	            !element.Name.startsWith("Slot") &&
	            !element.Name.startsWith("Heal") &&
	            !element.Name.startsWith("Sheild"))
	            continue;

	        if (mouseX >= element.Position.x &&
	            mouseX <= element.Position.x + (element.Bitsize.x * element.Scale) &&
	            mouseY >= element.Position.y &&
	            mouseY <= element.Position.y + (element.Bitsize.y * element.Scale))
	        {
	            return element;
	        }
	    }
	    return null;
	}
	
	private int GetSlotIndex(UIElement element)
	{
	    String[] data = element.Name.split("t");
	    return Integer.parseInt(data[1]); 
	}
}

