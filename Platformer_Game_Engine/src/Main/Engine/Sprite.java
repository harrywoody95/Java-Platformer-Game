package Main.Engine;

import java.awt.image.BufferedImage;
import java.util.Vector;

import Math.Vec2;
import Math.Vec2f;

public class Sprite {
	public BufferedImage Texture = null;
	AnimationState Animation;
	String Name;
	Vec2f Position;
	Vec2 Bitsize;
	int Scale;
	
	public Sprite(BufferedImage image, String name, Vec2 bitsize, Vec2f position, int scale, AnimationState animation)
	{
		Texture = image;
		Name = name;
		Bitsize = bitsize;
		Position = position;
		Scale = scale;
		Animation = animation;
	}
	
	public void Update()
	{

		if (Animation == null)
		{
			return;
		}
		Animation.CurrentTick++;

		if (Animation.Animation.Speed <= Animation.CurrentTick)
		{

			Animation.CurrentTick = 0;

			if (Animation.Animation.Frames.size() == Animation.CurrentFrameIndex + 1)
			{
				if (Animation.Loop)
				{
					Animation.CurrentFrameIndex = 0;
					Texture = Animation.Animation.Frames.get(0);
					return;
				}
				else
				{
					Animation = null;
					return;
				}
			}
			else
			{
				Animation.CurrentFrameIndex++;
				Texture = Animation.Animation.Frames.get(Animation.CurrentFrameIndex);
			}
		}
		
	}
	
	public static Animation CreateSpriteAnimation(String Name, Vector <BufferedImage> Frames, int TickSpeed)
	{
		Animation a = new Animation();
		a.Name = Name;
		a.Frames = Frames;
		a.Speed = TickSpeed;
		return a;
	}

	public static void StartSpriteAnimation(Game game, Sprite Sprite, String Name, boolean Loop)
	{
		Animation Animation = null;

		if (Sprite.Animation != null)
		{
			Sprite.Animation = null;
		}
		for (Animation a : game.AnimationList)
		{
			if (a.Name == Name)
			{
				Animation = a;
				break;
			}
		}
		if(Animation == null)
		{
			System.out.println("****ERROR**** Animation name : " + Name + "was not found");
			return;
		}

		if(Sprite.Animation == null)
		{
			Sprite.Animation = new AnimationState();
		}
		Sprite.Animation.CurrentTick = 0;
		Sprite.Animation.Loop = Loop;
		Sprite.Animation.Animation = Animation;
		Sprite.Animation.CurrentFrameIndex = 0;

		Sprite.Texture = Sprite.Animation.Animation.Frames.get(0);
	}
}
