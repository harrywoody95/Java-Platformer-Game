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
		{
			//lastState = currentState;
			currentState = State.Idle;
		}
		
		
		
		//looting handling
		if(userInput.Key_E_Pressed && onGround)
		{
			if(IsLootableInRange(g, 100))
			//lastState = currentState;
			currentState = State.Looting;
			return;
		}
		if(currentState == State.Looting && Sprite.Animation.Finished)
		{
			//lastState = currentState;
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
					//lastState = currentState;
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
					//lastState = currentState;
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
					//lastState = currentState;
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
					//lastState = currentState;
					currentState = State.Walking;
					Velocity.x = -Speed;
				}
			}
		}
		else
		{}
		
		//no input
		if (!userInput.Key_A_Pressed && !userInput.Key_D_Pressed && currentState != State.Looting) {
			//lastState = currentState;
			currentState = State.Idle;
		}

		// Jump
		if (userInput.Key_Space_Pressed && onGround) {
			Velocity.y = -10;
			onGround = false;
			//lastState = currentState;
			currentState = State.Jumping;
		}
		
		//falling
		if(!onGround && Velocity.y > 5)
		{
			//lastState = currentState;
			currentState = State.Falling;
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
	    	OpenLootables(GetLootablesInRange(g, 100), g);
	    }
	    
	    SetPlayerAnimation(g);
	    Sprite.Update();
	    UpdatePlayerSounds(g);
		lastState = currentState;
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
	 
	 public void OpenLootables(Vector<Chest> c, Game g)
	 {
		 for(Chest chest : c)
		 {
			 chest.LootChest(g);
		 }
	 }
	 
	 public boolean IsLootableInRange(Game g, int range)
	 {
		 for(Entity entity : g.EntityList)
		 {
			 if(entity.Type != EntityType.Chest)
			 {
				 continue;
			 }
			 
			 Chest e = (Chest)(entity);
			 if(e.Opened)
			 {
				 continue;
			 }
			 
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
	 
	 public void UpdatePlayerSounds(Game g)
	 {
		 System.out.println("State: " + lastState + " -> " + currentState);

		 if(currentState == State.Jumping && lastState != currentState)
		 {

					g.SoundManager.StopPlayerSoundEffects();
			 
			g.SoundManager.PlaySoundEffect("Jump");
		 }
		 if(currentState == State.Walking && lastState != currentState)
		 {

				 g.SoundManager.StopPlayerSoundEffects();
			 
			g.SoundManager.PlayMusic("Walk");
		 }
		 if(currentState == State.Idle && lastState != currentState)
		 {
			 g.SoundManager.StopPlayerSoundEffects();
		 }
		 if(currentState == State.Running && lastState != currentState)
		 {

				 g.SoundManager.StopPlayerSoundEffects();
			 
			g.SoundManager.PlayMusic("Run");
		 }
		 if(currentState == State.Looting && lastState != currentState)
		 {

				 g.SoundManager.StopPlayerSoundEffects();
			 
			g.SoundManager.PlaySoundEffect("Open");
		 }
	 }
	 
}
