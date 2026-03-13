package Main.Engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserInput implements KeyListener {

	public boolean Key_W_Pressed = false;
	public boolean Key_S_Pressed = false;
	public boolean Key_A_Pressed = false;
	public boolean Key_D_Pressed = false;
	public boolean Key_E_Pressed = false;
	public boolean Key_Shift_Pressed = false;
	public boolean Key_Space_Pressed = false;
	
	public boolean Key_W_Released = false;
	public boolean Key_S_Released = false;
	public boolean Key_A_Released = false;
	public boolean Key_D_Released = false;
	public boolean Key_E_Released = false;
	public boolean Key_Shift_Released = false;
	public boolean Key_Space_Released = false;
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//reset release values
		Key_W_Released = false;
		Key_S_Released = false;
		Key_D_Released = false;
		Key_A_Released = false;
		Key_Shift_Released = false;
		Key_Space_Released = false;
		
		int key = e.getKeyCode();
		
		switch(key)
		{
		case KeyEvent.VK_W:
			Key_W_Pressed = true;
			break;
		case KeyEvent.VK_S:
			Key_S_Pressed = true;
			break;
		case KeyEvent.VK_D:
			Key_D_Pressed = true;
			break;
		case KeyEvent.VK_A:
			Key_A_Pressed = true;
			break;
		case KeyEvent.VK_E:
			Key_E_Pressed = true;
			break;
		case KeyEvent.VK_SHIFT:
			Key_Shift_Pressed = true;
			break;
		case KeyEvent.VK_SPACE:
			Key_Space_Pressed = true;
			break;
		default:
			break;
		
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		switch(key)
		{
		case KeyEvent.VK_W:
			Key_W_Pressed = false;
			Key_W_Released = true;
			break;
		case KeyEvent.VK_S:
			Key_S_Pressed = false;
			Key_S_Released = true;
			break;
		case KeyEvent.VK_D:
			Key_D_Pressed = false;
			Key_D_Released = true;
			break;
		case KeyEvent.VK_A:
			Key_A_Pressed = false;
			Key_A_Released = true;
			break;
		case KeyEvent.VK_E:
			Key_E_Pressed = false;
			Key_E_Released = true;
			break;
		case KeyEvent.VK_SHIFT:
			Key_Shift_Pressed = false;
			Key_Shift_Released = true;
			break;
		case KeyEvent.VK_SPACE:
			Key_Space_Pressed = false;
			Key_Space_Released = true;
			break;
		default:
			break;
		
		}
		
	}
	
	public void Update(Game game)
	{

	}

}
