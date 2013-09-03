package com.greatdevs.Sound;

import javax.sound.sampled.*;

import com.greatdevs.StaticGameOptions;

import java.io.File;

public class Sound {	
	
	public static Clip MusicClip;
	
	private Sound() {
		
	}

	public static void play(String path) {
		try{
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
			    new File(Sound.class.getResource(path).toURI()));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			if (StaticGameOptions.PLAY_SOUNDS) clip.start();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void PlayMusic(String path, float Volume){
		try{
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
			    new File(Sound.class.getResource(path).toURI()));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			MusicClip = clip;
			FloatControl gainControl = 
			    (FloatControl) MusicClip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(Volume); // Reduce volume by 10 decibels.
			MusicClip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void StopMusic(){
		MusicClip.stop();
	}
}
