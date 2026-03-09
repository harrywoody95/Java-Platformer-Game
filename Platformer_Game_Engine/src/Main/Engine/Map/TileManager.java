package Main.Engine.Map;

import java.io.IOException;

import javax.imageio.ImageIO;
//
public class TileManager {
	
	Tile[] tiles;
	
	public TileManager() {
		tiles = new Tile[10];
		LoadTileImages();
	}
	
	public void LoadTileImages() {
		try {
			tiles[0] = new Tile();
			tiles[0].Tile = ImageIO.read(getClass().getResourceAsStream("/map/1.png"));
			
			tiles[1] = new Tile();
			tiles[1].Tile = ImageIO.read(getClass().getResourceAsStream("/map/2.png"));
			tiles[1].collision = true;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
