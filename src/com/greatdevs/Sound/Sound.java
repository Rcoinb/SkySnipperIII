package com.greatdevs.Sound;

import java.applet.Applet;

import javax.sound.sampled.*;

import java.applet.AudioClip;
import java.io.File;

public class Sound {	
	private AudioClip clip;

	private Sound(String name) {
		try {
			clip = Applet.newAudioClip(Sound.class.getResource(name));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void play(String path) {
		try{
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
			    new File(Sound.class.getResource(path).toURI()));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void loop() {
		try {
			new Thread() {
				public void run() {
					clip.loop();
				}
			}.start();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public static void PlayMusic(String path, float Valmue){
		try{
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
			    new File(Sound.class.getResource(path).toURI()));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			FloatControl gainControl = 
			    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(Valmue); // Reduce volume by 10 decibels.
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void stop(){
		clip.stop();
	}
}
