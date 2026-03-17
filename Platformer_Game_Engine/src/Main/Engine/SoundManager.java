package Main.Engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;
import javax.sound.sampled.Clip;


public class SoundManager {
	
	
	// i think what i want is to create new sound clips every time and add and remove them from a vector.
	//i can then have multiple of the same sound.
	//i can also force play sound effects or place them into a queue so that if i place 5 songs they will 
	// only after the other has finished.
	
	// I can have have all sounds pre-loaded into another vector so I'm not opening and closing files all the time. 
	
	// keep the music list vector as my 'all possible sounds list'
	
	//then make a list of what I'm playing and somehow include Queue into that..
	
	
	Vector <SoundClip> MusicList = new Vector <SoundClip> ();
	Vector<SoundClip> Playing = new Vector <SoundClip> ();
	Vector <SoundClip> Queue = new Vector<SoundClip>();
	
	
	public SoundManager()
	{
		LoadSounds();
	}
	
	public void LoadSounds()
	{
		try {
		InputStream is = getClass().getResourceAsStream("/audio/master.txt");
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
				SoundClip m; 
				if (s[2].strip().equals("ptrue"))
				{
					m = new SoundClip(Name, Path, true, false, false);
				}
				else if(s[2].strip().equals("true"))
				{
					m = new SoundClip(Name, Path, false, true, false);
				}
				else
				{
					m = new SoundClip(Name, Path, false, false, false);
				}
				
				MusicList.add(m);
				Line = br.readLine();
			}
	
		}
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//System.out.println(MusicList.size());
	}
	
	public void Play(Clip c)
	{
		c.start();
	}
	
	public void Stop(Clip c)
	{
		c.stop();
		c.setFramePosition(0);
	}
	
	public void Loop(Clip c)
	{
		c.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void PlayMusic (String Name)
	{
		SoundClip data = null; 
	    for (SoundClip c : MusicList)
	    {
	        if (c.Name.equals(Name))
	        {
	        	data = c;
	        }
	    }
		
		if(data != null)
		{
			SoundClip Play = new SoundClip(data.Name, data.Path, data.isPlayerSoundEffect, data.isSoundEffect, true);
			Playing.add(Play);
	    	Stop(Play.Clip);
	        Play(Play.Clip);
	        Loop(Play.Clip);
		}
		else
		{
			System.out.println("ERROR : SoundManager - " + Name + " Not Found in music list");
		}

	}
	
	public void StopMusic(String Name)
	{
		for(SoundClip c : Playing)
		{
			if(c.Name.equals(Name))
			{
				Stop(c.Clip);
			}
		}
	}
	
	public void StopPlayerSoundEffects() {
	    for (int x = 0; x < Playing.size(); x++) {
	        SoundClip c = Playing.get(x);

	        if (c.isPlayerSoundEffect && c.Clip != null) {
	            Stop(c.Clip);
	            Playing.remove(x);
	            x--;
	        }
	    }

	}
	
	public void PlaySoundEffect(String Name)
	{
		SoundClip data = null;
	    for (SoundClip c : MusicList)
	    {
	        if (c.Name.equals(Name))
	        {
	        	data = c;
	        }
	    }
	    if(data != null)
	    {
			SoundClip Play = new SoundClip(data.Name, data.Path, data.isPlayerSoundEffect, data.isSoundEffect, false);
			Playing.add(Play);
	        Play(Play.Clip);
	    }
	    else
	    {
	    	System.out.println("ERROR : SoundManager - " + Name + " Not Found in music list");
	    }
	}

	
	public void Update()
	{
		for(int x = 0; x < Playing.size(); x++)
		{
			SoundClip s = Playing.get(x);
			
			if(s.Clip == null)
			{
				Playing.remove(x);
				x--;
				continue;
			}
			
			if(s.loop == false && s.Clip.getFramePosition() >= s.Clip.getFrameLength())
			{
				Playing.remove(x);
				x--;
			}
		}
	}
}
