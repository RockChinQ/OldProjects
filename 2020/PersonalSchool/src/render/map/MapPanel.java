package render.map;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

import Pickaxe2D.PickImage;
import Pickaxe2D.PickPanel;
import core.Level;
import core.Main;
import core.SpaceScan;
import render.tool.BuildingPanel;

public class MapPanel extends JPanel{
	public PickPanel map;
	public PickImage yes=new PickImage(Main.res+"bldg_yes.png.pick"),no=new PickImage(Main.res+"bldg_no.png.pick")
			,again=new PickImage(Main.res+"bldg_yesagain.png.pick");
	public MapPanel() {
		this.setLayout(null);
		//map spawn
		map=new PickPanel(2,Main.mapWidth*10,Main.mapHeight*10);
		//map.gridLine=true;
		map.gridLineStep=10;
		map.setLocation(0, 0);
		map.setBackground(new Color(83,143,65));
		map.addMouseListener(new MapMouse());
		this.setSize(map.getWidth(), map.getHeight());
		System.out.println("map.size:"+map.getSize());
		this.add(map);
	}
	public void readyToBuild() {
		if(BuildingPanel.bldg==BuildingPanel.FENCE) {
			//System.out.println("road_x,y:"+Level.road_x+" "+Level.road_y);
			for(int i=0;i<Main.mapHeight;i++) {
				for(int j=0;j<Main.mapWidth;j++) {
					if(i>Level.road_y&&j<Level.road_x&&Level.blocks[i][j]==Level.GRASS) {//#################################################
						map.loadFrontPickImage(j*10, i*10,yes);
						Level.puttable[i][j]=Level.YES;
					}else {
						map.loadFrontPickImage(j*10, i*10, no);
						Level.puttable[i][j]=Level.NO;
					}
				}
			}
		}else if(BuildingPanel.bldg==BuildingPanel.TEACH) {//##############################################################################
			SpaceScan.manager();
			ArrayList<Integer> aroundBlock=new ArrayList<Integer>();
			aroundBlock.add(Level.ABS_FENCE);
			aroundBlock.add(Level.ABS_TEACH);
			aroundBlock.add(Level.ABS_GATE);
			ArrayList<Integer> areasIndex=SpaceScan.findAreaSurroundedByBlock(aroundBlock);//寻找被栅栏围绕的封闭区域
			
			//全部设置为no
			for(int i=0;i<Main.mapHeight;i++) {
				for(int j=0;j<Main.mapWidth;j++) {
					//map.loadFrontPickImage(j*10, i*10, no);
					Level.puttable[i][j]=Level.NO;
				}
			}
			//挨个设置标记
			int size=areasIndex.size();
			nextArea:for(int i=0;i<size;i++) {
				int index=areasIndex.get(i);//从areasindex中取出的区域索引
				int asize=SpaceScan.area.get(index).size();
				//在指定区域标记
				for(int j=0;j<asize;j++) {
					Point tp=SpaceScan.area.get(index).get(j);
					if(Level.getAbsType(Level.blocks[tp.y][tp.x])!=Level.ABS_GRASS) {  //如果此区域不是艹则下一区域
						//continue nextArea;
					}
					//map.loadFrontPickImage(tp.x*10, tp.y*10,yes);
					Level.puttable[tp.y][tp.x]=Level.YES;
				}
			}
		}else if(BuildingPanel.bldg==BuildingPanel.GATE) {//##############################################################################
			for(int i=0;i<Main.mapHeight;i++) {
				for(int j=0;j<Main.mapWidth;j++) {
					if(Level.getAbsType(Level.blocks[i][j])==Level.ABS_FENCE) {
						map.loadFrontPickImage(j*10, i*10,yes);
						Level.puttable[i][j]=Level.YES;
					}else {
						map.loadFrontPickImage(j*10, i*10, no);
						Level.puttable[i][j]=Level.NO;
					}
				}
			}
		}
		MapMouse.time=1;
		Level.updateFront();
		map.repaint();
	}
	public void secondClickArea(Point firstClick) {
		MapMouse.time=2;
		if(BuildingPanel.bldg==BuildingPanel.FENCE) {
			
			for(int i=0;i<Main.mapHeight;i++) {
				nextBlock:for(int j=0;j<Main.mapWidth;j++) {
					if(Level.puttable[i][j]==Level.YES&&(i==firstClick.y||j==firstClick.x)) {
						//System.out.println("nextblock");
						//竖
						if(j==firstClick.x) {
							for(int k=Math.min(i, firstClick.y);k<Math.max(i, firstClick.y);k++) {
								//System.out.println(j+","+k+" 0  abstype:"+Level.getAbsType(Level.blocks[k][j]));
								if(Level.getAbsType(Level.blocks[k][j])!=Level.ABS_FENCE&&Level.getAbsType(Level.blocks[k][j])!=Level.ABS_GRASS) {//本格不行
									Level.puttable[i][j]=Level.NO;
									//System.out.println("   no");
									continue nextBlock;
								}
							}
						}else {//横的
							for(int k=Math.min(j, firstClick.x);k<Math.max(j, firstClick.x);k++) {
								//System.out.println(k+","+i+" 1  abstype:"+Level.getAbsType(Level.blocks[i][j]));
								if(Level.getAbsType(Level.blocks[i][k])!=Level.ABS_FENCE&&Level.getAbsType(Level.blocks[i][k])!=Level.ABS_GRASS) {//本格不行
									Level.puttable[i][j]=Level.NO;
									//System.out.println("   no");
									continue nextBlock;
								}
							}
						}
						Level.puttable[i][j]=Level.YES;
					}else {
						Level.puttable[i][j]=Level.NO;
					}
				}
			}
			
		}else if(BuildingPanel.bldg==BuildingPanel.TEACH) {
			//Main.gg.mapp.map.resetFront();
			//System.out.println("teach second click.");
			for(int i=0;i<Main.mapHeight;i++) {
				nextBlock:for(int j=0;j<Main.mapWidth;j++) {
					if(Level.puttable[i][j]==Level.YES) {//遍历每一个可点击的格子
						Level.puttable[i][j]=Level.NO;//先设置为no，看情况再设置yes
						//接下来验证格子到点击点形成的矩形是否有非艹的存在
							//设置抽象起终点
						Point sp=new Point(Math.min(firstClick.x,j),Math.min(firstClick.y, i))
								,ep=new Point(Math.max(firstClick.x, j),Math.max(firstClick.y, i));
						for(int k=sp.y;k<=ep.y;k++) {//在矩形区域内遍历
							for(int l=sp.x;l<=ep.x;l++) {
								if(Level.getAbsType(Level.blocks[k][l])!=Level.ABS_GRASS) {
									continue nextBlock;
								}
							}
						}
						Level.puttable[i][j]=Level.YES;
					}
				}
			}
		}else if(BuildingPanel.bldg==BuildingPanel.GATE) {
			for(int i=0;i<Main.mapHeight;i++) {
				for(int j=0;j<Main.mapWidth;j++) {
					Level.puttable[i][j]=Level.NO;
				}
			}
			for(int i=(firstClick.y-1>=0?firstClick.y-1:0);i<=(firstClick.y+1<Main.mapHeight?firstClick.y+1:Main.mapHeight);i++) {
				for(int j=(firstClick.x-1>=0?firstClick.x-1:0);j<=(firstClick.x+1<Main.mapWidth?firstClick.x+1:Main.mapWidth);j++) {
					if(i!=firstClick.y&&j!=firstClick.x)
						continue;
					if(Level.getAbsType(Level.blocks[i][j])==Level.ABS_FENCE) {
						Level.puttable[i][j]=Level.YES;
					}else {
						Level.puttable[i][j]=Level.NO;
					}
				}
			}
		}
		Level.puttable[firstClick.y][firstClick.x]=Level.AGAIN;
		Level.updateFront();
	}
	public void repaintMap() {
		Main.gg.mainf.repaint();
	}
}
