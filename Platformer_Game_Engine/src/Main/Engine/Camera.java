package Main.Engine;

import Main.Game.Player;

public class Camera {
	public int cameraX = 0;
	public int cameraY = 0;

	int leftMargin = 400;
	int rightMargin = 400;
	int topMargin = 150;
	int bottomMargin = 150;
	
	public void Update(Player player) {

	    int playerScreenX = (int)player.Position.x - cameraX;
	    int playerScreenY = (int)player.Position.y - cameraY;

	    if(playerScreenX < leftMargin) {
	        cameraX = (int)player.Position.x - leftMargin;
	    }

	    if(playerScreenX > Game.SCREENWIDTH - rightMargin) {
	        cameraX = (int)player.Position.x - (Game.SCREENWIDTH - rightMargin);
	    }

	    if(playerScreenY < topMargin) {
	        cameraY = (int)player.Position.y - topMargin;
	    }

	    if(playerScreenY > Game.SCREENHEIGHT - bottomMargin) {
	        cameraY = (int)player.Position.y - (Game.SCREENHEIGHT - bottomMargin);
	    }
	}
}
