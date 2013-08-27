package com.greatdevs.Menu;

import com.greatdevs.Game;

public class ConsoleCheckCommand {
	public static void checkcommand(String command, Game game, Console console){
		if (command.equals("go")){
			System.out.println(command);
		}
		
		console.command = "";
	}
}
