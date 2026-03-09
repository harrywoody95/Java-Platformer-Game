package Main.Engine.Map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;


import javax.imageio.ImageIO;

import Main.Engine.Game;

public class Background {

	BufferedImage layer1;
	BufferedImage layer2;
	BufferedImage layer3;
	BufferedImage layer4;
	BufferedImage layer5;
	
	public Background()
	{
		try {
			layer1 = ImageIO.read(getClass().getResourceAsStream("/map/bg_layer_1.png"));
			layer2 = ImageIO.read(getClass().getResourceAsStream("/map/bg_layer_2.png"));
			layer3 = ImageIO.read(getClass().getResourceAsStream("/map/bg_layer_3.png"));
			layer4 = ImageIO.read(getClass().getResourceAsStream("/map/bg_layer_4.png"));
			layer5 = ImageIO.read(getClass().getResourceAsStream("/map/bg_layer_5.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void DrawLayer(Graphics2D g2, BufferedImage layer, Game g, double speed)
	{
	    int width = Game.SCREENWIDTH * 2;
	    int height = Game.SCREENHEIGHT; 

	    int camX = g.Camera.cameraX;
	    int x = (int)(-camX * speed) % width;


	    g2.drawImage(layer, x, 0, width, height, null);
	    g2.drawImage(layer, x + width, 0, width, height, null);
	    g2.drawImage(layer, x - width, 0, width, height, null);
	}
	
	public void DrawBackground(Graphics2D g2, Game g)
	{
	    DrawLayer(g2, layer1, g, 0.1);
	    DrawLayer(g2, layer2, g, 0.2);
	    DrawLayer(g2, layer3, g, 0.3);
	    DrawLayer(g2, layer4, g, 0.4);
	    DrawLayer(g2, layer5, g, 0.5);
	}
}
