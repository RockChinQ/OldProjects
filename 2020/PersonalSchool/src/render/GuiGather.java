package render;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Pickaxe2D.PickButton;
import Pickaxe2D.PickImage;
import Pickaxe2D.PickPanel;
import core.Main;
import render.map.MapPanel;
import render.tool.BuildingPanel;
import render.tool.ToolPanel;

public class GuiGather {
	public String res="resources\\";
	
	public JFrame mainf=new JFrame("Scl "+Main.version+" "+(Main.mode==1?"开发者模式":""));
	public MapPanel mapp;
	public ToolPanel tp;
	public BuildingPanel bp;
	
	public int mapWidth=Main.mapWidth,mapHeight=Main.mapHeight;
	public PickImage grass=new PickImage(res+"grass.png.pick");
	
	
	public JPanel center=new JPanel(),east=new JPanel(),west=new JPanel(),south=new JPanel(),north=new JPanel();
	public GuiGather() {
		
		//mainf.setLocation(300, 300);
		mainf.setLayout(null);
		mainf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//工具面板
		tp=new ToolPanel();
		tp.setLocation(0, 140);
		mainf.add(tp);
		bp=new BuildingPanel();
		bp.setLocation(60, 140);
		bp.setVisible(false);
		mainf.add(bp);
		
		
		//地图面板
		mapp=new MapPanel();
		mapp.setLocation(0, 0);
		mainf.add(mapp);
		
		mainf.setSize(mapp.getWidth(),mapp.getHeight());
		int mw=mainf.getWidth(),mh=mainf.getHeight();
		System.out.println("mainf.size:"+mainf.getSize());
		mainf.addWindowListener(new WindowAdapter() {
		    public void windowOpened(WindowEvent ev) {
		        int realH = mainf.getContentPane().getSize().height,realW=mainf.getContentPane().getSize().width;
		        int titleH = mh - realH,titleW=mw-realW;
		        mainf.setSize(mw+titleW, mh+titleH);
		        //setBounds((screenSize.width - dw) / 2, (screenSize.height - dh) / 2, dw, dh+titleH);
		    }
		});
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = screenSize.width/2;
		int centerY = screenSize.height/2;
		mainf.setLocation(centerX-mainf.getWidth()/2,centerY-mainf.getHeight()/2);
	}
	public void addBuildLine() {
		for(int i=0;i<mapHeight;i++) {
			for(int j=0;j<mapp.map.gridOfWidth;j++) {
				mapp.map.setGrid(j, i*grass.h,new Color(30,100,30,30));
			}
		}
		for(int i=0;i<mapWidth;i++) {
			for(int j=0;j<mapp.map.gridOfHeight;j++) {
				mapp.map.setGrid(i*grass.w, j,new Color(30,100,30,30));
			}
		}
	}
}
