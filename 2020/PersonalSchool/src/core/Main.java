package core;

import Pickaxe2D.FileRW;
import render.GuiGather;

public class Main {
	public static String dataPath="data";
	public static String res="resources\\";
	public static String version="alpha0.2";
	public static GuiGather gg;
	public static MapMaker mm;
	public static int mapWidth=70,mapHeight=40;
	public static int mode=0;//1=developer
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(FileRW.read("mode").equals("developer")) {
			mode=1;
		}
		//data process
		
		//gui spawn
		gg=new GuiGather();
		mm=new MapMaker();
		mm.randomMap();
		
		gg.mainf.setVisible(true);
	}

}
