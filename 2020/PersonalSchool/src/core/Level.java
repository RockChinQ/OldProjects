package core;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Map;

import Pickaxe2D.PickImage;
import render.tool.BuildingPanel;

public class Level {
	public static int road_x=0,road_y=0;
	public final static int GRASS=0,LINE_ROAD=1,ROW_ROAD=2,ROW_FENCE=3,LINE_FENCE=4,WESTNORTH_FENCE=5
			,EASTNORTH_FENCE=6,WESTSOUTH_FENCE=7,EASTSOUTH_FENCE=8,SINGLE_FENCE=9,FOURDIREC_FENCE=10
			,CENTER_ROAD=11,ROW_GATE=12,LINE_GATE=13,ROW_TEACHWALL=14,LINE_TEACHWALL=15,BOTTOM_TEACH=16
			,WESTNORTH_TEACHWALL=17,EASTNORTH_TEACHWALL=18,WESTSOUTH_TEACHWALL=19,EASTSOUTH_TEACHWALL=20;
	public final static int YES=1,NO=2,AGAIN=3;
	public final static int ABS_GRASS=0,ABS_ROAD=1,ABS_FENCE=2,ABS_GATE=3,ABS_TEACH=4;
	/*
	class Building{
		ArrayList<Point> range=new ArrayList<Point>();
	}
	ArrayList<Building> builds=new ArrayList<Building>();
	*/
	public static int blocks[][]=new int[Main.mapHeight][Main.mapWidth];
	public static int puttable[][]=new int[Main.mapHeight][Main.mapWidth];
	
	public static PickImage grass=new PickImage(Main.res+"grass.png.pick")
			,line_road=new PickImage(Main.res+"line_road.png.pick")
			,row_road=new PickImage(Main.res+"row_road.png.pick")
			,center_road=new PickImage(Main.res+"center_road.png.pick");
	public static PickImage yes=new PickImage(Main.res+"bldg_yes.png.pick"),no=new PickImage(Main.res+"bldg_no.png.pick")
			,again=new PickImage(Main.res+"bldg_yesagain.png.pick");
	public static PickImage line_fence=new PickImage(Main.res+"fence_line.png.pick"),row_fence=new PickImage(Main.res+"fence_row.png.pick");
	public static PickImage westnorth_fence=new PickImage(Main.res+"westnorth_fence.png.pick")
			,eastnorth_fence=new PickImage(Main.res+"eastnorth_fence.png.pick")
			,westsouth_fence=new PickImage(Main.res+"westsouth_fence.png.pick")
			,eastsouth_fence=new PickImage(Main.res+"eastsouth_fence.png.pick")
			,single_fence=new PickImage(Main.res+"single_fence.png.pick")
			,fourdirec_fence=new PickImage(Main.res+"fourdirec_fence.png.pick");
	public static PickImage row_gate=new PickImage(Main.res+"row_gate.png.pick"),line_gate=new PickImage(Main.res+"line_gate.png.pick");
	public static PickImage row_teachwall=new PickImage(Main.res+"row_teachwall.png.pick")
			,line_teachwall=new PickImage(Main.res+"line_teachwall.png.pick")
			,bottom_teach=new PickImage(Main.res+"bottom_teach.png.pick")
			,westnorth_teachwall=new PickImage(Main.res+"westnorth_teachwall.png.pick")
			,eastnorth_teachwall=new PickImage(Main.res+"eastnorth_teachwall.png.pick")
			,westsouth_teachwall=new PickImage(Main.res+"westsouth_teachwall.png.pick")
			,eastsouth_teachwall=new PickImage(Main.res+"eastsouth_teachwall.png.pick");
	public static void reset() {
		for(int i=0;i<Main.mapHeight;i++) {
			for(int j=0;j<Main.mapWidth;j++) {
				blocks[i][j]=0;
				puttable[i][j]=0;
			}
		}
	}
	public static void resetFront() {

		for(int i=0;i<Main.mapHeight;i++) {
			for(int j=0;j<Main.mapWidth;j++) {
				//blocks[i][j]=0;
				puttable[i][j]=0;
			}
		}
	}
	public static void updateFront() {
		for(int i=0;i<Main.mapHeight;i++) {
			for(int j=0;j<Main.mapWidth;j++) {
				if(puttable[i][j]==YES) {
					Main.gg.mapp.map.loadFrontPickImage(j*10, i*10, yes);
				}else if(puttable[i][j]==NO){
					Main.gg.mapp.map.loadFrontPickImage(j*10, i*10, no);
				}else {
					Main.gg.mapp.map.loadFrontPickImage(j*10, i*10, again);
				}
			}
		}
		Main.gg.mapp.repaintMap();
	}
	public static int putBuilding(Point start,Point end) {//修改blocks储存的数据值以放置建筑
		System.out.println(start+" "+end);
		if(BuildingPanel.bldg==BuildingPanel.FENCE) {
			if(start.getLocation().equals(end.getLocation())) {//单个
				Level.blocks[start.y][start.x]=Level.SINGLE_FENCE;
			}else if(start.x==end.x) {//竖的
				for(int i=Math.min(start.y, end.y);i<=Math.max(start.y, end.y);i++) {
					Level.blocks[i][start.x]=Level.ROW_FENCE;
				}
			}else if(start.y==end.y) {//横的
				for(int i=Math.min(start.x, end.x);i<=Math.max(start.x, end.x);i++) {
					Level.blocks[start.y][i]=Level.LINE_FENCE;
				}
			}
			return 0;
		}else if(BuildingPanel.bldg==BuildingPanel.TEACH){//放置教学楼
			//上下两条横的
			//System.out.println(1111111);
			for(int i=Math.min(start.x, end.x);i<=Math.max(start.x, end.x);i++) {
				Level.blocks[Math.min(start.y, end.y)][i]=Level.LINE_TEACHWALL;
				Level.blocks[Math.max(start.y, end.y)][i]=Level.LINE_TEACHWALL;
			}
			//左右两条竖的
			for(int i=Math.min(start.y, end.y);i<=Math.max(start.y, end.y);i++) {
				Level.blocks[i][Math.min(start.x, end.x)]=Level.ROW_TEACHWALL;
				Level.blocks[i][Math.max(start.x, end.x)]=Level.ROW_TEACHWALL;
			}
			//地板
			for(int i=Math.min(start.y, end.y)+1;i<Math.max(start.y, end.y);i++) {
				for(int j=Math.min(start.x, end.x)+1;j<Math.max(start.x, end.x);j++) {
					Level.blocks[i][j]=Level.BOTTOM_TEACH;
				}
			}
			return 0;
		}else if(BuildingPanel.bldg==BuildingPanel.GATE) {
			//System.out.println(1111);
			if(start.x==end.x) {//竖的
				for(int i=Math.min(start.y, end.y);i<=Math.max(start.y, end.y);i++) {
					Level.blocks[i][start.x]=Level.ROW_GATE;
				}
			}else if(start.y==end.y) {//横的
				for(int i=Math.min(start.x, end.x);i<=Math.max(start.x, end.x);i++) {
					Level.blocks[start.y][i]=Level.LINE_GATE;
				}
			}
			return 0;
		}
		return 1;
	}
	public static void solveMistake() {
		int bt=0;
		for(int i=0;i<Main.mapHeight;i++) {
			for(int j=0;j<Main.mapWidth;j++) {
				bt=Level.blocks[i][j];
				if(Level.getAbsType(bt)==Level.ABS_FENCE) {//修正栅栏错误
					if(i-1>=0&&i+1<Main.mapHeight&&j-1>=0&&j+1<Main.mapWidth&&Level.getAbsType(Level.blocks[i-1][j])==Level.ABS_FENCE
							&&Level.getAbsType(Level.blocks[i+1][j])==Level.ABS_FENCE
							&&Level.getAbsType(Level.blocks[i][j-1])==Level.ABS_FENCE
							&&Level.getAbsType(Level.blocks[i][j+1])==Level.ABS_FENCE) {//四个
						Level.blocks[i][j]=Level.FOURDIREC_FENCE;
						Level.solveCornerFenceMistake(j, i);
					}else if(i+1<Main.mapHeight&&j+1<Main.mapWidth&&Level.getAbsType(Level.blocks[i+1][j])==Level.ABS_FENCE
							&&Level.getAbsType(Level.blocks[i][j+1])==Level.ABS_FENCE) {//西北
						Level.blocks[i][j]=Level.WESTNORTH_FENCE;
						Level.solveCornerFenceMistake(j, i);
					}else if(i+1<Main.mapHeight&&j-1>=0&&Level.getAbsType(Level.blocks[i+1][j])==Level.ABS_FENCE
							&&Level.getAbsType(Level.blocks[i][j-1])==Level.ABS_FENCE) {//东北
						Level.blocks[i][j]=Level.EASTNORTH_FENCE;
						Level.solveCornerFenceMistake(j, i);
					}else if(i-1>=0&&j+1<Main.mapWidth&&Level.getAbsType(Level.blocks[i-1][j])==Level.ABS_FENCE
							&&Level.getAbsType(Level.blocks[i][j+1])==Level.ABS_FENCE) {//西南
						Level.blocks[i][j]=Level.WESTSOUTH_FENCE;
						Level.solveCornerFenceMistake(j, i);
					}else if(i-1>=0&&j-1>=0&&Level.getAbsType(Level.blocks[i-1][j])==Level.ABS_FENCE
							&&Level.getAbsType(Level.blocks[i][j-1])==Level.ABS_FENCE) {//东南
						Level.blocks[i][j]=Level.EASTSOUTH_FENCE;
						Level.solveCornerFenceMistake(j, i);
					}else {//无连接,单面和重合连接，三面连接
						int count=-1;
						for(int k=(i-1>=0?i-1:0);k<=(i+1<Main.mapHeight-1?i+1:Main.mapHeight-1);k++) {
							for(int l=(j-1>=0?j-1:0);l<=(j+1<Main.mapWidth-1?j+1:Main.mapWidth-1);l++) {
								if(k!=i&&l!=j)
									continue;
								if(Level.getAbsType(Level.blocks[k][l])==Level.ABS_FENCE) {
									count++;
								}
							}
						}
						if(count==0) {
							Level.blocks[i][j]=Level.SINGLE_FENCE;
						}else if(count==1||count==2) {//单面和重合
							if((i-1>=0&&Level.getAbsType(Level.blocks[i-1][j])==Level.ABS_FENCE)
									||(i+1<Main.mapHeight&&Level.getAbsType(Level.blocks[i+1][j])==Level.ABS_FENCE)) {//竖的
								Level.blocks[i][j]=Level.ROW_FENCE;
							}else {
								Level.blocks[i][j]=Level.LINE_FENCE;
							}
						}
					}
				}else if(bt>=1&&bt<=2) {
					if(i==Level.road_y&&j==Level.road_x) {
						Level.blocks[i][j]=Level.CENTER_ROAD;
					}
				}
			}
		}
	}
	public static void solveCornerFenceMistake(int x,int y) {
		int count=-1;
		for(int k=(y-1>=0?y-1:0);k<=(y+1<Main.mapHeight-1?y+1:Main.mapHeight-1);k++) {
			for(int l=(x-1>=0?x-1:0);l<=(x+1<Main.mapWidth-1?x+1:Main.mapWidth-1);l++) {
				if(k!=y&&l!=x)
					continue;
				if(Level.getAbsType(Level.blocks[k][l])==Level.ABS_FENCE) {
					count++;
				}
			}
		}
		if(count==3) {//这里偷个懒啊，我懒得做四个三面连接的block贴图了，直接上四面连接的，以后写个旋转代码吧
			Level.blocks[y][x]=Level.FOURDIREC_FENCE;
		}
		return;
	}
	
	public static void updateMap() {  //渲染map
		Level.solveMistake();
		Main.gg.mapp.map.reset();
		PickImage tpi=new PickImage(Main.res+"grass.png.pick");
		for(int i=0;i<Main.mapHeight;i++) {
			for(int j=0;j<Main.mapWidth;j++) {
				/*GRASS=0,LINE_ROAD=1,ROW_ROAD=2,ROW_FENCE=3,LINE_FENCE=4,WESTNORTH_FENCE=5
			,EASTNORTH_FENCE=6,WESTSOUTH_FENCE=7,EASTSOUTH_FENCE=8,SINGLE_FENCE=9;*/
				switch(Level.blocks[i][j]) {
					case 0:
						tpi=Level.grass;
						break;
					case 1:
						tpi=Level.line_road;
						break;
					case 2:
						tpi=Level.row_road;
						break;
					case 3:
						tpi=Level.row_fence;
						break;
					case 4:
						tpi=Level.line_fence;
						break;
					case 5:
						tpi=Level.westnorth_fence;
						break;
					case 6:
						tpi=Level.eastnorth_fence;
						break;
					case 7:
						tpi=Level.westsouth_fence;
						break;
					case 8:
						tpi=Level.eastsouth_fence;
						break;
					case 9:
						tpi=Level.single_fence;
						break;
					case 10:
						tpi=Level.fourdirec_fence;
						break;
					case 11:
						tpi=Level.center_road;
						break;
					case 12:
						tpi=Level.row_gate;
						break;
					case 13:
						tpi=Level.line_gate;
						break;
					case Level.ROW_TEACHWALL:
						tpi=Level.row_teachwall;
						break;
					case Level.LINE_TEACHWALL:
						tpi=Level.line_teachwall;
						break;
					case Level.BOTTOM_TEACH:
						tpi=Level.bottom_teach;
						break;
					case Level.WESTNORTH_TEACHWALL:
						tpi=Level.westnorth_teachwall;
						break;
					case Level.EASTNORTH_TEACHWALL:
						tpi=Level.eastnorth_teachwall;
						break;
					case Level.WESTSOUTH_TEACHWALL:
						tpi=Level.westsouth_teachwall;
						break;
					case Level.EASTSOUTH_TEACHWALL:
						tpi=Level.eastsouth_teachwall;
						break;
				}
				Main.gg.mapp.map.loadPickImage(j*10,i*10,tpi);
			}
		}
		Main.gg.mapp.map.resetFront();
		Main.gg.mapp.repaintMap();
	}
	public static int getAbsType(int concreteType) {
		if(concreteType==0) {//艹
			return Level.ABS_GRASS;
		}else if((concreteType>=1&&concreteType<=2)||concreteType==11) {
			return Level.ABS_ROAD;
		}else if(concreteType>=3&&concreteType<=10) {
			return Level.ABS_FENCE;
		}else if(concreteType==12||concreteType==13) {
			return Level.ABS_GATE;
		}else if(concreteType>=14&&concreteType<=20) {
			return Level.ABS_TEACH;
		}
		return -1;
	}
	
}
