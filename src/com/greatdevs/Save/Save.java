package com.greatdevs.Save;

import java.io.*;
import com.greatdevs.Game;
import com.greatdevs.Entity.ShipTypes;

public class Save {

public int bestscore, coins;
public File file = new File("C://Users//Public//SkySnipperIII//save.txt");

public void savegame() throws Exception{
	if (!file.exists()){
		file.createNewFile();
	}
	    FileWriter saveFile = new FileWriter(file);

	    // Write the data to the file.
	    saveFile.write("\n");
	    saveFile.write(bestscore + "\n");
	    saveFile.write(coins + "\n");
	    saveFile.write(ShipTypes.type1[3] + "\n");
	    saveFile.write(ShipTypes.type2[3] + "\n");
	    saveFile.write(ShipTypes.type3[3] + "\n");
	    saveFile.write(ShipTypes.type4[3] + "\n");
	    saveFile.write(ShipTypes.type5[3] + "\n");
	    saveFile.write(ShipTypes.type6[3] + "\n");
	    saveFile.write(ShipTypes.getIntType() + "\n");
	    saveFile.write("\n");
	    
	    // All done, close the FileWriter.
	    saveFile.close();
}

public void loadgame() throws Exception{
	if (!file.exists()){
		file.createNewFile();
		ShipTypes.setType(1);
	}
	    BufferedReader saveFile=
	        new BufferedReader(new FileReader(file));

	    // Throw away the blank line at the top.
	    // Get the integer value from the String.
	    if (file.exists()){
		saveFile.readLine(); 
	    bestscore = Integer.parseInt(saveFile.readLine());
	    coins = Integer.parseInt(saveFile.readLine());
	    ShipTypes.type1[3] = Integer.parseInt(saveFile.readLine());
	    ShipTypes.type2[3] = Integer.parseInt(saveFile.readLine());
	    ShipTypes.type3[3] = Integer.parseInt(saveFile.readLine());
	    ShipTypes.type4[3] = Integer.parseInt(saveFile.readLine());
	    ShipTypes.type5[3] = Integer.parseInt(saveFile.readLine());
	    ShipTypes.type6[3] = Integer.parseInt(saveFile.readLine());
	    ShipTypes.setType(Integer.parseInt(saveFile.readLine()));
	    } 
	    // Not needed, but read blank line at the bottom.
	    saveFile.readLine(); 

	    saveFile.close();
}

public void createdirectory(){
	File ff = new File("C://Users//Public//SkySnipperIII");
	try{
	if(ff.mkdir())
	System.out.println("Directory Created");
	else
	System.out.println("Directory is not created");
	}catch(Exception e){
	e.fillInStackTrace();
	} 
}

public void loadallgame(Game game){
	game.update.gameworld.BESTSCORE = game.save.bestscore;
	game.update.gameworld.COINS = game.save.coins;
}

public void saveallgame(Game game){
	game.save.coins = game.update.gameworld.COINS;
	if (game.update.gameworld.SCORE > game.update.gameworld.BESTSCORE){
		game.update.gameworld.BESTSCORE = game.update.gameworld.SCORE;
		game.save.bestscore = game.update.gameworld.BESTSCORE;
	}
	try {
		game.save.savegame();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}
