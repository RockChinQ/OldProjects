package guiMgr;

import java.awt.Color;
import java.util.ArrayList;

import boot.Main;
import log.Log;

public class PnlColor {
	public Color basic=new Color(255,255,255);
	public double editingPnl,toolPnl,trackOper,infoBar,labelCheck,groupPnl
					,attributeEdit;
	public double sceneTable,sceneAttri,shotAttri,shotTable;
	public PnlColor() {
		editingPnl=0.20;
		toolPnl=0.25;
		trackOper=0.25;
		labelCheck=0.25;
		infoBar=0.35;
		groupPnl=0.25;
		attributeEdit=0.25;
		
		sceneTable=0.20;
		sceneAttri=0.20;
		shotAttri=0.20;
		shotTable=0.20;
		loadCfg();
	}
	/**
	 * 加载面板颜色配置
	 */
	public void loadCfg() {
		try {
			editingPnl=Double.parseDouble(Main.cfg.get("editingPnl.color").toString());
			toolPnl=Double.parseDouble(Main.cfg.get("toolPnl.color").toString());
			trackOper=Double.parseDouble(Main.cfg.get("trackOper.color").toString());
			infoBar=Double.parseDouble(Main.cfg.get("infoBar.color").toString());
			groupPnl=Double.parseDouble(Main.cfg.get("groupPnl.color").toString());
			labelCheck=Double.parseDouble(Main.cfg.get("labelCheck.color").toString());
			attributeEdit=Double.parseDouble(Main.cfg.get("attributeEdit.color").toString());
			sceneTable=Double.parseDouble(Main.cfg.get("sceneTable.color").toString());
			sceneAttri=Double.parseDouble(Main.cfg.get("sceneAttri.color").toString());
			shotAttri=Double.parseDouble(Main.cfg.get("shotAttri.color").toString());
			shotTable=Double.parseDouble(Main.cfg.get("shotTable.color").toString());
		}catch(Exception e) {
			Log.record("Load color cfg failed.");
			e.printStackTrace();
		}
	}
	
	public static final Color psc[]=new Color[] {new Color(40,140,240),new Color(187,127,127)
			,new Color(136,0,21),new Color(237,28,36),new Color(255,127,39)
			,new Color(255,242,0),new Color(74,177,116),new Color(0,162,232)
			,new Color(83,92,204),new Color(136,73,164),new Color(0,128,64)
			,new Color(195,195,0),new Color(185,122,87),new Color(255,201,14)
			,new Color(181,230,29)};
	
	public static Color getRandomFromPreset() {
		return psc[rand(0,psc.length)];
	}
	/**
	 * 
	 * 根据设定的basicColor计算指定程度的颜色
	 * @param rate
	 * @return
	 */
	public Color getColor(double rate) {
		int r=(int)((double)basic.getRed()*rate)
				,g=(int)((double)basic.getGreen()*rate)
				,b=(int)((double)basic.getBlue()*rate);
		return new Color(r,g,b);
	}
	public static Color getColor(Color c,double rate) {
		try {
			return new Color((int)(c.getRed()*rate),(int)(c.getGreen()*rate),(int)(c.getBlue()*rate));
		}catch(Exception e) {
			return Color.WHITE;
		}
	}
	public static Color randHighContrastColor(int delta,int rmin,int rmax) {
		int rgb[]=new int[3];
		rgb[0]=rand(0,255);
		rgb[1]=(rgb[0]+delta+rand(rmin,rmax))%255;
		rgb[2]=(rgb[1]+delta+rand(rmin,rmax))%255;
		//int orger[]=new int[3];
		ArrayList<Integer> order=new ArrayList<Integer>();
		order.add(0);
		order.add(1);
		order.add(2);
		order=randNoSameNum(order);
		return new Color(rgb[order.get(0)],rgb[order.get(1)],rgb[order.get(2)]);
	}
	public static Color randColor(int min,int max) {
		return new Color(rand(min,max),rand(min,max),rand(min,max));
	}
	public static int rand(int min,int max) {
		return (int) ((Math.random()*1000)%(max-min)+min);
	}
	public static ArrayList randNoSameNum(ArrayList arg) {
		ArrayList re=new ArrayList();
		while(arg.size()!=0) {
			int i=rand(0,arg.size());
			re.add(arg.get(i));
			arg.remove(i);
		}
		return re;
	}
}
