package com.greatdevs.Entity;

import java.awt.image.BufferedImage;

public class ShipTypes{
	//HP, maxspeed, reloadtime, price
	public static int t1p = 0, t2p = 250, t3p = 500, t4p = 1000, t5p = 2500, t6p = 5000;
	
	public static boolean ownType = false;
	public static BufferedImage playerimage;
	public static String name;
	
	public static int[] showtype = new int[5];
	public static int[] type = new int[5];
	
	public static final int[] type1 = {5, 5, 25, 0, 0};
	public static final int[] type2 = {5, 10, 30, 250, 1};
	public static final int[] type3 = {7, 7, 25, 500, 2};
	public static final int[] type4 = {10, 7, 15, 1000, 3};
	public static final int[] type5 = {8, 8, 20, 2500, 4};
	public static final int[] type6 = {15, 8, 10, 5000, 5};
	
	
	public static void setShowType(int t){
		if (t == 1) showtype = type1;
		if (t == 2) showtype = type2;
		if (t == 3) showtype = type3;
		if (t == 4) showtype = type4;
		if (t == 5) showtype = type5;
		if (t == 6) showtype = type6;
	}
	
	public static int[] getShowType(){
		return showtype;
	}
	
	public static void setType(int t){
		if (t == 1) type = type1;
		if (t == 2) type = type2;
		if (t == 3) type = type3;
		if (t == 4) type = type4;
		if (t == 5) type = type5;
		if (t == 6) type = type6;
		ownType = false;
	}
	
	public static int getIntType(){
		if (type.equals(type1)) return 1;
		if (type.equals(type2)) return 2;
		if (type.equals(type3)) return 3;
		if (type.equals(type4)) return 4;
		if (type.equals(type5)) return 5;
		if (type.equals(type6)) return 6;
		return 1;
	}
	
	public static void setOwnType(int[] type, BufferedImage image, String name){
		ownType = true;
		ShipTypes.type = type;
		ShipTypes.playerimage = image;
		ShipTypes.name = name;
	}
	
	public static String getShowPowerString(){
		if (getShowType().equals(type1)) return "Kill all stars";
		if (getShowType().equals(type2)) return "Small ship, HP from stars";
		if (getShowType().equals(type3)) return "Something &s";
		if (getShowType().equals(type4)) return "Something &s";
		if (getShowType().equals(type5)) return "Stars to coins";
		if (getShowType().equals(type6)) return "Drakula laser";
		return "Something &s";
	}
	
	public static int[] getType(){
		return type;
	}
}
