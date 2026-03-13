package Main.Engine;

import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;

public class SoundClip {
	String Name;
	URL SoundURL;
	Clip Clip;
	boolean isPlayerSoundEffect;
	boolean isSoundEffect;
	public SoundClip(String Name, String Path, boolean isPlayerSoundEffect, boolean isSoundEffect)
	{
	    this.Name = Name;
	    this.SoundURL = getClass().getResource(Path);
	    this.isPlayerSoundEffect = isPlayerSoundEffect;
	    this.isSoundEffect = isSoundEffect;

	    try {
	        AudioInputStream ais = AudioSystem.getAudioInputStream(SoundURL);
	        Clip = AudioSystem.getClip();
	        Clip.open(ais);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
