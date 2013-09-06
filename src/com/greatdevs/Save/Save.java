package com.greatdevs.Save;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

import com.greatdevs.Game;
import com.greatdevs.StaticGameOptions;
import com.greatdevs.Entity.ShipTypes;
import com.greatdevs.GameWorld.MultiPlayer;
import com.greatdevs.GameWorld.SinglePlayer;

public class Save {

	public int bestscore, coins, coopbestscore;
	public File file = new File(StaticGameOptions.PATH + "SkySnipperIII//Save.save");

	public void savegame() throws Exception {
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter saveFile = new FileWriter(file);

		// Write the data to the file.
		saveFile.write("\n");
		saveFile.write(bestscore + "\n");
		saveFile.write(coopbestscore + "\n");
		saveFile.write(coins + "\n");
		saveFile.write(ShipTypes.type1[3] + "\n");
		saveFile.write(ShipTypes.type2[3] + "\n");
		saveFile.write(ShipTypes.type3[3] + "\n");
		saveFile.write(ShipTypes.type4[3] + "\n");
		saveFile.write(ShipTypes.type5[3] + "\n");
		saveFile.write(ShipTypes.type6[3] + "\n");
		if (!ShipTypes.ownType) {
			saveFile.write(ShipTypes.getIntType() + "\n");
		} else if (ShipTypes.ownType) {
			saveFile.write("7" + "\n");
			saveFile.write(ShipTypes.name + "\n");
		}
		saveFile.write("\n");

		// All done, close the FileWriter.
		saveFile.close();
	}

	public void loadgame() {
		BufferedReader saveFile;

		// Throw away the blank line at the top.
		// Get the integer value from the String.
		if (file.exists()) {
			try{
			saveFile = new BufferedReader(new FileReader(file));
			saveFile.readLine();
			bestscore = Integer.parseInt(saveFile.readLine());
			coopbestscore = Integer.parseInt(saveFile.readLine());
			coins = Integer.parseInt(saveFile.readLine());
			ShipTypes.type1[3] = Integer.parseInt(saveFile.readLine());
			ShipTypes.type2[3] = Integer.parseInt(saveFile.readLine());
			ShipTypes.type3[3] = Integer.parseInt(saveFile.readLine());
			ShipTypes.type4[3] = Integer.parseInt(saveFile.readLine());
			ShipTypes.type5[3] = Integer.parseInt(saveFile.readLine());
			ShipTypes.type6[3] = Integer.parseInt(saveFile.readLine());
			int t = Integer.parseInt(saveFile.readLine());
			if (t != 7)
				ShipTypes.setType(t);
			else if (t == 7) {
				loadtype(saveFile.readLine());
			}
			else if (t == 0){
				ShipTypes.setType(1);
			}
			saveFile.readLine();
			saveFile.close();
			}
			catch(Exception e){
				e.printStackTrace();
				ShipTypes.setType(1);
			}
		} else if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			ShipTypes.setType(1);
		}
		// Not needed, but read blank line at the bottom.
	}

	public void loadtype(String name) throws Exception {
		BufferedReader saveFile = new BufferedReader(new FileReader(new File(
				StaticGameOptions.PATH + "SkySnipperIII//Ships//" + name + ".dat")));
		saveFile.readLine();
		int[] type = new int[4];

		type[0] = Integer.parseInt(saveFile.readLine());
		type[1] = Integer.parseInt(saveFile.readLine());
		type[2] = Integer.parseInt(saveFile.readLine());
		type[3] = Integer.parseInt(saveFile.readLine());

		saveFile.close();

		BufferedImage image = ImageIO.read(new File(
				StaticGameOptions.PATH + "SkySnipperIII//Ships//" + name + ".png"));

		ShipTypes.setOwnType(type, image, name);

	}

	public void createdirectory() {
		File ff = new File(StaticGameOptions.PATH + "SkySnipperIII");
		try {
			if (ff.mkdir())
				System.out.println("Directory SkySnipperIII Created");
			else
				System.out.println("Directory SkySnipperIII is not created");
		} catch (Exception e) {
			e.fillInStackTrace();
		}

		File fff = new File(StaticGameOptions.PATH + "SkySnipperIII//Ships");
		try {
			if (fff.mkdir())
				System.out.println("Directory Ships Created");
			else
				System.out.println("Directory Ships is not created");
		} catch (Exception e) {
			e.fillInStackTrace();
		}
	}

	public void loadallgame(Game game) {
		SinglePlayer.BESTSCORE = game.save.bestscore;
		MultiPlayer.BESTSCORE = game.save.coopbestscore;
		SinglePlayer.COINS = game.save.coins;
	}

	public void saveallgame(Game game) {
		game.save.coins = SinglePlayer.COINS;
		if (SinglePlayer.SCORE > SinglePlayer.BESTSCORE) {
			SinglePlayer.BESTSCORE = SinglePlayer.SCORE;
			game.save.bestscore = SinglePlayer.BESTSCORE;
		}
		if (MultiPlayer.SCORE > MultiPlayer.BESTSCORE) {
			MultiPlayer.BESTSCORE = MultiPlayer.SCORE;
			game.save.coopbestscore = MultiPlayer.BESTSCORE;
		}
		try {
			game.save.savegame();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
