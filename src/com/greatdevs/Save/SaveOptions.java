package com.greatdevs.Save;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import com.greatdevs.StaticGameOptions;

public class SaveOptions {

	public File file = new File(
			"C://Users//Public//SkySnipperIII//GameOptions.txt");

	public void saveOptions() throws Exception {
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter saveFile = new FileWriter(file);

		// Write the data to the file.
		saveFile.write("\n");
		saveFile.write(StaticGameOptions.PLAY_MUSIC + "\n");
		saveFile.write(StaticGameOptions.PLAY_SOUNDS + "\n");
		saveFile.write(StaticGameOptions.MUSIC_VOLUME + "\n");
		saveFile.write("\n");

		// All done, close the FileWriter.
		saveFile.close();
	}

	public void loadOptions() throws Exception{
			BufferedReader saveFile;

			// Throw away the blank line at the top.
			// Get the integer value from the String.
			if (file.exists()) {
				saveFile = new BufferedReader(new FileReader(file));
				saveFile.readLine();
				StaticGameOptions.PLAY_MUSIC = Boolean.parseBoolean(saveFile.readLine());
				StaticGameOptions.PLAY_SOUNDS = Boolean.parseBoolean(saveFile.readLine());
				StaticGameOptions.MUSIC_VOLUME = Float.parseFloat(saveFile.readLine());
				saveFile.readLine();
				saveFile.close();
				System.out.println("Options: loaded, PLAY_MUSIC: " + StaticGameOptions.PLAY_MUSIC + ", PLAY_SOUNDS: " + StaticGameOptions.PLAY_SOUNDS + ", MUSIC_VOLUME: " + StaticGameOptions.MUSIC_VOLUME);
			} else if (!file.exists()) {
				file.createNewFile();
				saveOptions();
			}
	}
}
