package com.greatdevs.Sound;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	public static final Sound button = new Sound("button.wav");
	public static final Sound coin = new Sound("coin.wav");
	public static final Sound explosion = new Sound("explosion.wav");
	public static final Sound select = new Sound("select.wav");
	public static final Sound shoot = new Sound("shoot.wav");
	public static final Sound shootpressed = new Sound("shootpressed.wav");
	public static final Sound superpower = new Sound("superpower.wav");
	public static final Sound bosssuperpower = new Sound("bosssuperpower.wav");
	
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
