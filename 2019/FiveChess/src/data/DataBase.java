package data;

import java.awt.Point;
import java.util.ArrayList;

import gameMgr.GameMgr;
import launch.Main;

public class DataBase {
	public static short pnl[][]=new short[13][13];
	public static short aiCode=1;
	public static int gameMd=-1;//-1:break  0:2p  1:ai
	public static final int AIMode=1,PPMode=0;
	public static int now=0;
	public static boolean ailock=false;
	public static boolean playing=false;
	public static ArrayList<Point> step=new ArrayList<Point>();
	public DataBase() {
	}
	public static void init() {
		for(int i=0;i<13;i++) {
			for(int j=0;j<13;j++) {
				pnl[i][j]=-1;
			}
		}
	}
	public static short getPlayerCode() {
		return (short) (aiCode==1?0:1);
	}
	public static void changeNow() {
		now=(now==0?1:0);
		Main.w.notice.setText("Now:"+(now==0?"black":"white"));
	}
	public static void put(Point p,short now) {
		put(p.x,p.y,now);
		return;
	}
	public static void put(int x,int y,short now) {
		DataBase.pnl[y][x]=(short)now;
		step.add(new Point(x, y));
		return;
	}
	public static void back() {
		if(!DataBase.playing||step.size()==0)
			return;
		Point p=step.get(step.size()-1);
		DataBase.pnl[p.y][p.x]=-1;
		step.remove(step.size()-1);
		if(DataBase.gameMd==0) {
			DataBase.changeNow();
		}else if(DataBase.gameMd==1) {
			p=step.get(step.size()-1);
			DataBase.pnl[p.y][p.x]=-1;
			step.remove(step.size()-1);
		}
		GameMgr.updateDisplay();
	}
	public static int str2int(String str) {
		return Integer.parseInt(str);
	}
	public static boolean str2bool(String str) {
		return Boolean.parseBoolean(str);
	}
	public static boolean intStr2bool(String str) {
		return str2int(str)==0?false:true;
	}
}
