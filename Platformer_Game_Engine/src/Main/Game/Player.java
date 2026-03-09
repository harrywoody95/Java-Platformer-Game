package Main.Game;

import Main.Engine.*;
import Math.Vec2;
import Math.Vec2f;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

public class Player extends Entity {
	int Health = 100;
	Game game;
	UserInput userInput;
	Sprite Sprite;
	Vec2f tmp = new Vec2f(0.0f,0.0f);


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

	}

	public void HandleUserInput() {

		Velocity.x = 0;

		if (userInput.Key_D_Pressed) {
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

		if (userInput.Key_A_Pressed) {
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

		if (!userInput.Key_A_Pressed && !userInput.Key_D_Pressed) {
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
			
			Vector<BufferedImage> v6 = new Vector<BufferedImage>();
			BufferedImage RightFall1 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Fall_1.png"));
			BufferedImage RightFall2 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Fall_2.png"));
			
			//THESE ARE MORE SUITED TO FALLING FROM HIGH HEIGHT
			
			//BufferedImage RightFall3 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Fall_3.png"));
			//BufferedImage RightFall4 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Fall_4.png"));
			//BufferedImage RightFall5 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Fall_5.png"));
			//BufferedImage RightFall6 = ImageIO.read(getClass().getResourceAsStream("/player/Right_Fall_6.png"));

			v6.add(RightFall1);
			v6.add(RightFall2);
			//v6.add(RightFall3);
			//v6.add(RightFall4);
			//v6.add(RightFall5);
			//v6.add(RightFall6);

			Vector<BufferedImage> v7 = new Vector<BufferedImage>();
			BufferedImage LeftFall1 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Fall_1.png"));
			BufferedImage LeftFall2 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Fall_2.png"));
			
			//THESE ARE MORE SUITED TO FALLING FROM HIGH HEIGHT
			
			// BufferedImage LeftFall3 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Fall_3.png"));
			// BufferedImage LeftFall4 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Fall_4.png"));
			// BufferedImage LeftFall5 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Fall_5.png"));
			// BufferedImage LeftFall6 = ImageIO.read(getClass().getResourceAsStream("/player/Left_Fall_6.png"));

			v7.add(LeftFall1);
			v7.add(LeftFall2);
			//v7.add(LeftFall3);
			//v7.add(LeftFall4);
			//v7.add(LeftFall5);
			//v7.add(LeftFall6);

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


		lastState = currentState;
	}


	public void Update(Game g) {
	    UpdateCollisionBox();
	    HandleUserInput();
	    //will i collide if so stop me from doing so and update velocity
	    //then apply new velocity
	    if(!onGround)
	    {
		    Velocity.y += gravity;
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


	    SetPlayerAnimation(g);
	    Sprite.Update();
	}
	public void Draw(Graphics2D g2, Game g) {

		// NEED TO MAKE AN ANIMATION CLASS

		g2.drawImage(Sprite.Texture, (int)(Position.x - g.Camera.cameraX), (int)(Position.y - g.Camera.cameraY), 150, 100, null);

		if(g.DrawDebugBoxes)
		{
		  g2.drawRect((int)(Collision.box.Left - g.Camera.cameraX), (int)(Collision.box.Top -
		  g.Camera.cameraY), (int)(Collision.box.Right - Collision.box.Left),
		  (int)(Collision.box.Bottom - Collision.box.Top) );
		}
		 
	}
}
