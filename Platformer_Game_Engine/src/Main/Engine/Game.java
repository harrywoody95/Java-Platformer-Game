package Main.Engine;

import java.awt.Color;

import Main.Engine.Map.GameMap;
import Main.Game.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Game extends JPanel implements Runnable{
	
	final int FPS = 60;
	final int ASSETTILESIZE = 64;
	final int SCALE = 1;
	final int TILESIZE = ASSETTILESIZE * SCALE;
	public final static int TILECOLUMNS = 20;
	public final static int TILEROWS = 15;
	public final static int SCREENWIDTH = TILECOLUMNS * 64; // 20 COLOUMS OF TILES
	public final static int SCREENHEIGHT = TILEROWS * 64; //15 ROWS OF TILES
	public final static float gravity = 0.3f;
	
	public Vector <Animation> AnimationList = new Vector<Animation>();
	public Vector <CollisionBox> CollisionBoxList = new Vector<CollisionBox>();
	public Vector <Entity> EntityList = new Vector<Entity>();
	public CollisionManager CollisionManager = new CollisionManager();
	public GameMap map = new GameMap(this);
	Thread GameThread;
	UserInput UserInput = new UserInput();
	public Player Player = new Player(this, UserInput);
	Chest c = new Chest(700, 782, Rarity.Rare, this);
	public Camera Camera = new Camera();
	public SoundManager SoundManager = new SoundManager();
	public boolean DrawDebugBoxes = true;
	
	public Game() {
		this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(UserInput);
		this.setFocusable(true);
		LoadAnimations();
		SoundManager.PlayMusic("Music");
	}

	public void StartGameThread()
	{
		GameThread = new Thread(this);
		GameThread.start();
	}
	
	@Override
	public void run() {
		
		double DrawInterval = 1000000000/FPS;
		double Delta = 0;
		long LastTime = System.nanoTime();
		long CurrentTime;
		long Timer = 0;
		int DrawCount = 0;
		
 		
		while (GameThread != null)
		{
			CurrentTime = System.nanoTime();
			
			Delta += (CurrentTime - LastTime) / DrawInterval;
			Timer += (CurrentTime - LastTime);
			
			LastTime = CurrentTime;
			
			if(Delta >= 1)
			{
				
				//Actual game loop
				Update();
				repaint();
				
				
				
				
				
				
				
				Delta--;
				DrawCount++;
			}
			
			if(Timer >= 1000000000)
			{
				System.out.println("FPS: " + DrawCount);

				DrawCount = 0;
				Timer = 0;
			}
		}	
	}
	
	public void Update()
	{
		UserInput.Update(this);
		Camera.Update(Player);
		for(Entity e : EntityList)
		{
			e.Update(this);
		}
	}
	
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		map.DrawMap(g2, this);
		for(Entity e : EntityList )
		{
			e.Draw(g2, this);
		}
		if(DrawDebugBoxes)
		{
			 for(CollisionBox b : CollisionBoxList) 
			 { 
				 g2.drawRect((int)(b.box.Left - Camera.cameraX), (int)(b.box.Top - Camera.cameraY), (int)(b.box.Right - b.box.Left), (int)(b.box.Bottom - b.box.Top) ); 
			 }
		}
		
		
		 
		g2.dispose();
	}
	
	public void LoadAnimations()
	{
			try {
			InputStream is = getClass().getResourceAsStream("/animation/animations.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String Line = br.readLine();
			while (Line != null)
			{
				if (Line.charAt(0) == '#')
				{
					Line = br.readLine();
					continue;
				}
				
				else
				{
					String[] s = Line.split(" ");
					
					String Name = s[0].strip();
					String Path = s[1].strip();
					int Frames = Integer.parseInt(s[2].strip());
					int Speed = Integer.parseInt(s[3].strip());
					
					Vector <BufferedImage> v = new Vector<BufferedImage>();
					
					for(int x = 0; x < Frames; x++)
					{
						BufferedImage i = ImageIO.read(getClass().getResourceAsStream(Path + "_" + Integer.toString(x + 1) + ".png"));
						v.add(i);
					}
					
					Animation a = Main.Engine.Sprite.CreateSpriteAnimation(Name, v, Speed);
					this.AnimationList.add(a);
					Line = br.readLine();
				}
		
			}
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
}
