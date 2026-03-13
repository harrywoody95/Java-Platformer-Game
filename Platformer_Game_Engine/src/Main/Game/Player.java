package Main.Game;

import Main.Engine.*;
import Math.Vec2;
import Math.Vec2f;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

public class Player extends Character  {
	int Health = 100;
	Game game;
	UserInput userInput;
	Vec2f tmp = new Vec2f(0.0f,0.0f);
	Vector <Item> Inventory = new Vector <Item> ();


	public void SetDefaultValues(Game g) {
		Position.x = 400;
		Position.y = 700;
		Speed = 3;
		currentDirection = Direction.Right;
		lastDirection = Direction.Left;
		currentState = State.Idle;
		lastState = State.Walking;
		BufferedImage RightIdle1;
		Vec2 Bitsize = new Vec2(150, 100);

		AnimationState a = new AnimationState();
		try {

			RightIdle1 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Idle_1.png"));
			Sprite = new Sprite(RightIdle1, "Player", Bitsize, Position, 1, a);
			Main.Engine.Sprite.StartSpriteAnimation(g, Sprite, "RightIdle", true);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Player(Game g, UserInput u) {
		LoadPlayerImages(g);
		SetDefaultValues(g);
		this.game = g;
		this.userInput = u;
		Type = EntityType.Player;
		g.CollisionBoxList.add(Collision);
		g.EntityList.add(this);
	}

	public void HandleUserInput(Game g) {

		Velocity.x = 0;

		//stops the player being able to moonwalk
		if(userInput.Key_D_Pressed & userInput.Key_A_Pressed)
		{currentState = State.Idle;}
		
		
		
		//looting handling
		if(userInput.Key_E_Pressed && onGround)
		{
			if(IsLootableInRange(g, 100))
			currentState = State.Looting;
			return;
		}
		if(currentState == State.Looting && Sprite.Animation.Finished)
		{
			currentState = State.Idle;
		}
		
		
		
		
		//going right
		else if (userInput.Key_D_Pressed) {
			lastDirection = currentDirection;
			currentDirection = Direction.Right;

			if (userInput.Key_Shift_Pressed) {
				if(currentState == State.Jumping && !onGround)
				{
					Velocity.x = Speed * 1.5f;
				}
				else
				{
					currentState = State.Running;
					Velocity.x = Speed * 1.5f;
				}
			} else {
				if(currentState == State.Jumping && !onGround)
				{
					Velocity.x = Speed;
				}
				else
				{
					currentState = State.Walking;
					Velocity.x = Speed;
				}
			}
		}

		//going left
		else if (userInput.Key_A_Pressed) {
			lastDirection = currentDirection;
			currentDirection = Direction.Left;

			if (userInput.Key_Shift_Pressed) {
				if(currentState == State.Jumping && !onGround)
				{
					Velocity.x = -(Speed * 1.5f);
				}
				else
				{
					currentState = State.Running;
					Velocity.x = -(Speed * 1.5f);
				}
			} else {
				if(currentState == State.Jumping && !onGround)
				{
					Velocity.x = -Speed;
				}
				else
				{
					currentState = State.Walking;
					Velocity.x = -Speed;
				}
			}
		}
		else
		{}
		
		//no input
		if (!userInput.Key_A_Pressed && !userInput.Key_D_Pressed && currentState != State.Looting) {
			currentState = State.Idle;
		}

		// Jump
		if (userInput.Key_Space_Pressed && onGround) {
			Velocity.y = -10;
			onGround = false;
			currentState = State.Jumping;
			
		}
		
		//falling
		if(!onGround && Velocity.y > 5)
		{
			currentState = State.Falling;
		}
	}

	public void LoadPlayerImages(Game g) {
		try {
			// IDLE PLAYER SPRITES
			Vector<BufferedImage> v = new Vector<BufferedImage>();
			BufferedImage RightIdle1 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Idle_1.png"));
			BufferedImage RightIdle2 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Idle_2.png"));
			v.add(RightIdle1);
			v.add(RightIdle2);

			Vector<BufferedImage> v1 = new Vector<BufferedImage>();
			BufferedImage LeftIdle1 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Idle_1.png"));
			BufferedImage LeftIdle2 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Idle_2.png"));
			v1.add(LeftIdle1);
			v1.add(LeftIdle2);

			// RUNNING PLAYER SPRITES
			Vector<BufferedImage> v2 = new Vector<BufferedImage>();
			BufferedImage RightMove1 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Run_1.png"));
			BufferedImage RightMove2 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Run_2.png"));
			BufferedImage RightMove3 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Run_3.png"));
			BufferedImage RightMove4 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Run_4.png"));
			BufferedImage RightMove5 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Run_5.png"));
			BufferedImage RightMove6 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Run_6.png"));
			BufferedImage RightMove7 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Run_7.png"));
			BufferedImage RightMove8 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Run_8.png"));
			v2.add(RightMove1);
			v2.add(RightMove2);
			v2.add(RightMove3);
			v2.add(RightMove4);
			v2.add(RightMove5);
			v2.add(RightMove6);
			v2.add(RightMove7);
			v2.add(RightMove8);

			Vector<BufferedImage> v3 = new Vector<BufferedImage>();
			BufferedImage LeftMove1 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Run_1.png"));
			BufferedImage LeftMove2 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Run_2.png"));
			BufferedImage LeftMove3 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Run_3.png"));
			BufferedImage LeftMove4 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Run_4.png"));
			BufferedImage LeftMove5 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Run_5.png"));
			BufferedImage LeftMove6 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Run_6.png"));
			BufferedImage LeftMove7 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Run_7.png"));
			BufferedImage LeftMove8 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Run_8.png"));
			v3.add(LeftMove1);
			v3.add(LeftMove2);
			v3.add(LeftMove3);
			v3.add(LeftMove4);
			v3.add(LeftMove5);
			v3.add(LeftMove6);
			v3.add(LeftMove7);
			v3.add(LeftMove8);
		//Jumping
			Vector<BufferedImage> v4 = new Vector<BufferedImage>();
			BufferedImage LeftJump1 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Jump_1.png"));
			BufferedImage LeftJump2 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Jump_2.png"));
			BufferedImage LeftJump3 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Jump_3.png"));
			BufferedImage LeftJump4 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Jump_4.png"));
			BufferedImage LeftJump5 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Jump_5.png"));
			BufferedImage LeftJump6 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Jump_6.png"));
			BufferedImage LeftJump7 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Jump_7.png"));
			BufferedImage LeftJump8 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Jump_8.png"));
			v4.add(LeftJump1);
			v4.add(LeftJump2);
			v4.add(LeftJump3);
			v4.add(LeftJump4);
			v4.add(LeftJump5);
			v4.add(LeftJump6);
			v4.add(LeftJump7);
			v4.add(LeftJump8);
			
			
			Vector<BufferedImage> v5 = new Vector<BufferedImage>();
			BufferedImage RightJump1 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Jump_1.png"));
			BufferedImage RightJump2 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Jump_2.png"));
			BufferedImage RightJump3 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Jump_3.png"));
			BufferedImage RightJump4 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Jump_4.png"));
			BufferedImage RightJump5 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Jump_5.png"));
			BufferedImage RightJump6 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Jump_6.png"));
			BufferedImage RightJump7 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Jump_7.png"));
			BufferedImage RightJump8 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Jump_8.png"));
			v5.add(RightJump1);
			v5.add(RightJump2);
			v5.add(RightJump3);
			v5.add(RightJump4);
			v5.add(RightJump5);
			v5.add(RightJump6);
			v5.add(RightJump7);
			v5.add(RightJump8);
			
			//falling
			Vector<BufferedImage> v6 = new Vector<BufferedImage>();
			BufferedImage RightFall1 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Fall_1.png"));
			BufferedImage RightFall2 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Fall_2.png"));

			v6.add(RightFall1);
			v6.add(RightFall2);

			Vector<BufferedImage> v7 = new Vector<BufferedImage>();
			BufferedImage LeftFall1 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Fall_1.png"));
			BufferedImage LeftFall2 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Fall_2.png"));
			

			v7.add(LeftFall1);
			v7.add(LeftFall2);
			
			//looting
			Vector<BufferedImage> v8 = new Vector<BufferedImage>();
			BufferedImage RightLoot1 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Loot_1.png"));
			BufferedImage RightLoot2 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Loot_2.png"));
			BufferedImage RightLoot3 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Loot_3.png"));
			BufferedImage RightLoot4 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Loot_4.png"));
			BufferedImage RightLoot5 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Loot_5.png"));

			v8.add(RightLoot1);
			v8.add(RightLoot2);
			v8.add(RightLoot3);
			v8.add(RightLoot4);
			v8.add(RightLoot5);
			
			Vector<BufferedImage> v9 = new Vector<BufferedImage>();
			BufferedImage LeftLoot1 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Loot_1.png"));
			BufferedImage LeftLoot2 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Loot_2.png"));
			BufferedImage LeftLoot3 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Loot_3.png"));
			BufferedImage LeftLoot4 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Loot_4.png"));
			BufferedImage LeftLoot5 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Loot_5.png"));

			v9.add(LeftLoot1);
			v9.add(LeftLoot2);
			v9.add(LeftLoot3);
			v9.add(LeftLoot4);
			v9.add(LeftLoot5);
			
			Vector<BufferedImage> v10 = new Vector<BufferedImage>();
			BufferedImage CommonChest1 = ImageIO.read(getClass().getResourceAsStream("/lootable/Chest_Common_1.png"));
			BufferedImage CommonChest2 = ImageIO.read(getClass().getResourceAsStream("/lootable/Chest_Common_2.png"));
			BufferedImage CommonChest3 = ImageIO.read(getClass().getResourceAsStream("/lootable/Chest_Common_3.png"));
			BufferedImage CommonChest4 = ImageIO.read(getClass().getResourceAsStream("/lootable/Chest_Common_4.png"));
			BufferedImage CommonChest5 = ImageIO.read(getClass().getResourceAsStream("/lootable/Chest_Common_5.png"));
			BufferedImage CommonChest6 = ImageIO.read(getClass().getResourceAsStream("/lootable/Chest_Common_6.png"));
			BufferedImage CommonChest7 = ImageIO.read(getClass().getResourceAsStream("/lootable/Chest_Common_7.png"));

			v10.add(CommonChest1);
			v10.add(CommonChest2);
			v10.add(CommonChest3);
			v10.add(CommonChest4);
			v10.add(CommonChest5);
			v10.add(CommonChest6);
			v10.add(CommonChest7);

			Vector<BufferedImage> v11 = new Vector<BufferedImage>();
			BufferedImage RareChest1 = ImageIO.read(getClass().getResourceAsStream("/lootable/Chest_Rare_1.png"));
			BufferedImage RareChest2 = ImageIO.read(getClass().getResourceAsStream("/lootable/Chest_Rare_2.png"));
			BufferedImage RareChest3 = ImageIO.read(getClass().getResourceAsStream("/lootable/Chest_Rare_3.png"));
			BufferedImage RareChest4 = ImageIO.read(getClass().getResourceAsStream("/lootable/Chest_Rare_4.png"));
			BufferedImage RareChest5 = ImageIO.read(getClass().getResourceAsStream("/lootable/Chest_Rare_5.png"));
			BufferedImage RareChest6 = ImageIO.read(getClass().getResourceAsStream("/lootable/Chest_Rare_6.png"));
			BufferedImage RareChest7 = ImageIO.read(getClass().getResourceAsStream("/lootable/Chest_Rare_7.png"));

			v11.add(RareChest1);
			v11.add(RareChest2);
			v11.add(RareChest3);
			v11.add(RareChest4);
			v11.add(RareChest5);
			v11.add(RareChest6);
			v11.add(RareChest7);

			Animation a = Main.Engine.Sprite.CreateSpriteAnimation("RightIdle", v, 15);
			Animation a1 = Main.Engine.Sprite.CreateSpriteAnimation("LeftIdle", v1, 15);
			Animation a2 = Main.Engine.Sprite.CreateSpriteAnimation("RightWalk", v2, 7);
			Animation a3 = Main.Engine.Sprite.CreateSpriteAnimation("LeftWalk", v3, 7);
			Animation a4 = Main.Engine.Sprite.CreateSpriteAnimation("RightRun", v2, 5);
			Animation a5 = Main.Engine.Sprite.CreateSpriteAnimation("LeftRun", v3, 5);
			Animation a6 = Main.Engine.Sprite.CreateSpriteAnimation("LeftJump", v4, 5);
			Animation a7 = Main.Engine.Sprite.CreateSpriteAnimation("RightJump", v5, 5);
			Animation a8 = Main.Engine.Sprite.CreateSpriteAnimation("RightFall", v6, 5);
			Animation a9 = Main.Engine.Sprite.CreateSpriteAnimation("LeftFall", v7, 5);
			Animation a10 = Main.Engine.Sprite.CreateSpriteAnimation("RightLoot", v8, 5);
			Animation a11 = Main.Engine.Sprite.CreateSpriteAnimation("LeftLoot", v9, 5);
			Animation a12 = Main.Engine.Sprite.CreateSpriteAnimation("CommonChestOpen", v10, 2);
			Animation a13 = Main.Engine.Sprite.CreateSpriteAnimation("RareChestOpen", v11, 2);

			g.AnimationList.add(a);
			g.AnimationList.add(a1);
			g.AnimationList.add(a2);
			g.AnimationList.add(a3);
			g.AnimationList.add(a4);
			g.AnimationList.add(a5);
			g.AnimationList.add(a6);
			g.AnimationList.add(a7);
			g.AnimationList.add(a8);
			g.AnimationList.add(a9);
			g.AnimationList.add(a10);
			g.AnimationList.add(a11);
			g.AnimationList.add(a12);
			g.AnimationList.add(a13);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void SetPlayerAnimation(Game g) {

		if (lastState == currentState) {
			return;
		}

		if (currentState == State.Idle) {
			if (currentDirection == Direction.Left) {
				Main.Engine.Sprite.StartSpriteAnimation(g, Sprite, "LeftIdle", true);
			} else {
				Main.Engine.Sprite.StartSpriteAnimation(g, Sprite, "RightIdle", true);
			}

		} else if (currentState == State.Walking) {
			if (currentDirection == Direction.Left) {
				Main.Engine.Sprite.StartSpriteAnimation(g, Sprite, "LeftWalk", true);
			} else {
				Main.Engine.Sprite.StartSpriteAnimation(g, Sprite, "RightWalk", true);
			}

		} else if (currentState == State.Running) {
			if (currentDirection == Direction.Left) {
				Main.Engine.Sprite.StartSpriteAnimation(g, Sprite, "LeftRun", true);
			} else {
				Main.Engine.Sprite.StartSpriteAnimation(g, Sprite, "RightRun", true);
			}
		} else if (currentState == State.Jumping) {
			if (currentDirection == Direction.Left) {
				Main.Engine.Sprite.StartSpriteAnimation(g, Sprite, "LeftJump", false);
			} else {
				Main.Engine.Sprite.StartSpriteAnimation(g, Sprite, "RightJump", false);
			}
		}
		else if (currentState == State.Falling) {
			if (currentDirection == Direction.Left) {
				Main.Engine.Sprite.StartSpriteAnimation(g, Sprite, "LeftFall", false);
			} else {
				Main.Engine.Sprite.StartSpriteAnimation(g, Sprite, "RightFall", false);
			}
		}
		else if (currentState == State.Looting) {
			if (currentDirection == Direction.Left) {
				Main.Engine.Sprite.StartSpriteAnimation(g, Sprite, "LeftLoot", false);
			} else {
				Main.Engine.Sprite.StartSpriteAnimation(g, Sprite, "RightLoot", false);
			}
		}


		lastState = currentState;
	}


	public void Update(Game g) {
	    UpdateCollisionBox();
	    HandleUserInput(g);
	    
	    if(!onGround)
	    {
		    Velocity.y += Game.gravity;
	    }

	    tmp.y = 0.0f;
	    tmp.x = 0.0f;
	    if(g.CollisionManager.BoxCollision(this, this.Collision.box, g.CollisionBoxList, Speed, currentDirection, Velocity, tmp))
	    {
	    	//what happens
	    }
	    Velocity.x = tmp.x;
	    Velocity.y = tmp.y;
	    Position.x += (int)Velocity.x;
	    Position.y += (int)Velocity.y;

	    if(currentState == State.Looting)
	    {
	    	OpenLootables(GetLootablesInRange(g, 100), g.Player);
	    }
	    
	    SetPlayerAnimation(g);
	    Sprite.Update();
	}

	public void UpdateCollisionBox() {
		Collision.box.Top = Position.y + 20;
		Collision.box.Left = Position.x + 55;
		Collision.box.Bottom = Position.y + 100;
		Collision.box.Right = Position.x + 90;
		
	}
	
	public void Draw(Graphics2D g2, Game g) {

		g2.drawImage(Sprite.Texture, (int)(Position.x - g.Camera.cameraX), (int)(Position.y - g.Camera.cameraY), 150, 100, null);
	}
	
	
	 public Vector<Chest> GetLootablesInRange(Game g, int range) { 
		 Vector <Chest> Lootables  = new Vector <Chest>();
		 
		 for(Entity e : g.EntityList)
		 {
			 Vec2f entityPos = e.PositionCenter();
			 Vec2f playerPos = PositionCenter();
			 if(e.Type != EntityType.Chest)
			 {
				 continue;
			 }
			 
			 if(entityPos.x > playerPos.x)
			 {
				 if(currentDirection == Direction.Right && entityPos.x - playerPos.x <= range)
				 {
					 Lootables.add((Chest) e);
				 }
			 }
			 else
			 {
				 if(currentDirection == Direction.Left && playerPos.x - entityPos.x <= range)
				 {
					 Lootables.add((Chest) e);
				 }
			 }
		 }
		 return Lootables;
	  
	  }
	 
	 public void OpenLootables(Vector<Chest> c, Player p)
	 {
		 for(Chest chest : c)
		 {
			 chest.LootChest(p);
		 }
	 }
	 
	 public boolean IsLootableInRange(Game g, int range)
	 {
		 for(Entity e : g.EntityList)
		 {
			 boolean LootableInRangeX = false;
			 boolean LootableInRangeY = false;
			 Vec2f entityPos = e.PositionCenter();
			 Vec2f playerPos = PositionCenter();

			 if(entityPos.x > playerPos.x)
			 {
				 if(currentDirection == Direction.Right)
				 {
					 if(entityPos.x - playerPos.x <= range)
					 {
						 LootableInRangeX = true;
					 }
				 }  
			 }
			 else
			 {
				 if(currentDirection == Direction.Left) 
				 {
					 if(playerPos.x - entityPos.x <= range)
					 {
						 LootableInRangeX = true;
					 }
				 }
			 }
			 
			 if(entityPos.y > playerPos.y)
			 {
				 if(entityPos.y - playerPos.y <= range / 2)
				 {
					 LootableInRangeY = true;
				 }

			 }
			 else
			 {
				 if(playerPos.y - entityPos.y <= range / 2)
				 {
					 LootableInRangeY = true;
				 }
			 }
			 
			 if(LootableInRangeX && LootableInRangeY)
			 {
				 return true;
			 }
		 }
		 return false;
	 }
	 
}
