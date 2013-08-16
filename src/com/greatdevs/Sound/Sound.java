package com.greatdevs.Sound;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	public static final Sound button = new Sound("button.wav");
	
	private AudioClip clip;

	private Sound(String name) {
		try {
			clip = Applet.newAudioClip(Sound.class.getResource(name));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void play() {
		try {
			new Thread() {
				public void run() {
					clip.play();
				}
			}.start();
		} catch (Throwable e) {
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
	
	public void stop(){
		clip.stop();
	}
}
