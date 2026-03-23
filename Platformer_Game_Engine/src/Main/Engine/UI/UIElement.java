package Main.Engine.UI;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Vector;

import Main.Engine.Game;
import Math.Vec2;
import Math.Vec2f;

public class UIElement {
	public BufferedImage Texture = null;
	String Text;
	int TextSize;
	public String Name;
	public Vec2f Position;
	public Vec2 Bitsize;
	public int Scale;
	UIType Type;
	
	public UIElement(UIType Type, BufferedImage image, String Name,  String Text, int textSize, Vec2 bitsize, Vec2f position, int scale)
	{
		this.Type = Type;
		Texture = image;
		this.Name = Name;
		this.Text = Text;
		this.TextSize = textSize;
		Bitsize = bitsize;
		Position = position;
		Scale = scale;
	}
	
	public void Update()
	{
		
	}
	
	public void Draw(Graphics2D g2, Game g)
	{
		if(Type == UIType.Sprite)
		{
			g2.drawImage(Texture, (int)(Position.x), (int)(Position.y), Bitsize.x * Scale, Bitsize.y * Scale, null);
		}
		if(Type == UIType.Text)
		{
			g2.setFont(new Font("SansSerif", Font.PLAIN, TextSize));
			g2.drawString(Text, (Position.x), (Position.y));
		}
	}
	
	
}