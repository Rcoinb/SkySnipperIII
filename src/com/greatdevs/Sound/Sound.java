package com.greatdevs.Sound;

import javax.sound.sampled.*;
import com.greatdevs.StaticGameOptions;

public class Sound {	
	
	public static Clip MusicClip;
	
	private Sound() {
		
	}

	public static void play(String path) {
		try{
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
			    Sound.class.getResource(path));
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
				 Sound.class.getResource(path));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			MusicClip = clip;
			FloatControl gainControl = 
			    (FloatControl) MusicClip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(Volume); // Reduce volume by 10 decibels.
			MusicClip.loop(Clip.LOOP_CONTINUOUSLY);
			System.out.println("Music: loaded");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void StopMusic(){
		MusicClip.stop();
	}
}
