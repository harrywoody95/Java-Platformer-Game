package Main.Engine;

import javax.swing.JFrame;

public class Window extends JFrame {

JFrame Window;

public void init()
{
	Window = new JFrame();
	Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	Window.setResizable(false);
	Window.setTitle("Platraction");
	
	Game Game = new Game();
	Window.add(Game);
	Window.pack();
	
	Window.setLocationRelativeTo(Window);
	Window.setVisible(true);
	
	Game.StartGameThread();
}

}
