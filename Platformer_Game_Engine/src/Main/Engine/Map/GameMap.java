package Main.Engine.Map;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import Main.Engine.CollisionBox;
import Main.Engine.Game;

public class GameMap {

	public Background background;
	public TileManager tileManager;
	int MapData[][];
	
	public GameMap(Game g) {
		background = new Background();
		tileManager = new TileManager();
		MapData = new int[63][15]; // my map size
		LoadMap(g);
	}
	
	public void LoadMap(Game g) {
		try {
		InputStream is = getClass().getResourceAsStream("/map1/map1_layer1.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		int col = 0;
		int row = 0;
		
		while (col < 63 && row < 15) // my map size
		{

				String line = br.readLine();
				
				while(col < 63) // my map size
				{
					String Numbers[] = line.split(" ");
					int num = Integer.parseInt(Numbers[col]);
					MapData[col][row] = num;
					col++;
				}
				if (col == 63) // my map size
				{
					col = 0;
					row++;
				}
		}
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		int x = 0;
		int y = 0;
		int col = 0;
		int row = 0;
		
		
		while(col < 63 && row < 15 ) // i set these based on the map size have have made.
		{
			int tileNum = MapData[col][row];
			if(tileNum == 0)
			{
			}
			else
			{
				tileNum = tileNum - 1;
				if(tileManager.tiles[tileNum].collision)
				{
					CollisionBox b = new CollisionBox(y, x, y + 64, x + 64);
					g.CollisionBoxList.add(b);
				}
			}
			col++;
			x += 64;
			if(col == 63) // again my map size
			{
				col = 0;
				x = 0;
				row++;
				y += 64;
			}
		}
		
	}
		
		
	
	
	public void DrawMap(Graphics2D g2, Game g)
	{
		int x = 0;
		int y = 0;
		int col = 0;
		int row = 0;
		
		background.DrawBackground(g2, g);
		
		while(col < 63 && row < 15 ) // i set these based on the map size have have made.
		{
			int tileNum = MapData[col][row];
			if(tileNum == 0)
			{
			}
			else
			{
				tileNum = tileNum - 1;
				g2.drawImage(tileManager.tiles[tileNum].Tile, x - g.Camera.cameraX, y - g.Camera.cameraY, 64, 64, null);
			}
			col++;
			x += 64;
			if(col == 63) // again my map size
			{
				col = 0;
				x = 0;
				row++;
				y += 64;
			}
		}
		
	}
}
