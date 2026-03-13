package Main.Engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;
import javax.sound.sampled.Clip;


public class SoundManager {
	
	Vector <SoundClip> MusicList = new Vector <SoundClip> ();
	
	
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
					m = new SoundClip(Name, Path, true, false);
				}
				else if(s[2].strip().equals("true"))
				{
					m = new SoundClip(Name, Path, false, true);
				}
				else
				{
					m = new SoundClip(Name, Path, false, false);
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
		System.out.println(MusicList.size());
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
		
	    for (SoundClip c : MusicList)
	    {
	        if (c.Name.equals(Name))
	        {
	        	Stop(c.Clip);
	            Play(c.Clip);
	            Loop(c.Clip);
	        }
	    }
	}
	
	public void StopMusic(String Name)
	{
		for(SoundClip c : MusicList)
		{
			if(c.Name.equals(Name))
			{
				Stop(c.Clip);
			}
		}
	}
	
	public void StopPlayerSoundEffects() {
	    for (SoundClip c : MusicList) {
	        if (c.isPlayerSoundEffect && c.Clip != null) {
	            c.Clip.loop(0);            // cancel looping
	            Stop(c.Clip);           // stop playback
	        }
	    }
	}
	
	public void PlaySoundEffect(String Name)
	{
	    for (SoundClip c : MusicList)
	    {
	        if (c.Name.equals(Name))
	        {
	            c.Clip.stop();
	            c.Clip.setFramePosition(0);
	            c.Clip.start();
	        }
	    }
	}


}
