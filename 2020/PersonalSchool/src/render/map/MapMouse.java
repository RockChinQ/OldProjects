package render.map;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import core.Level;
import core.Main;
import render.tool.BuildingPanel;

public class MapMouse implements MouseListener {
	public static int time=0;
	public static Point first=new Point();
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println(arg0.getButton());
		if(arg0.getButton()==3) {
			Main.gg.mapp.map.resetFront();
			Level.resetFront();
			time=0;
			Main.gg.mapp.repaintMap();
			return;
		}
		int x=arg0.getX(),y=arg0.getY();
		int xOnMap=x/10/Main.gg.mapp.map.zoom,yOnMap=y/10/Main.gg.mapp.map.zoom;
		System.out.println("mapmouse.xy:"+xOnMap+","+yOnMap+" "+arg0.getX()+" "+arg0.getY());
		if(time==1&&Level.puttable[yOnMap][xOnMap]==Level.YES) {
			first=new Point(xOnMap,yOnMap);
			Main.gg.mapp.secondClickArea(first);
		}else if(time==2&&Level.puttable[yOnMap][xOnMap]==Level.YES) {
			int label=Level.putBuilding(first, new Point(xOnMap,yOnMap));
			if(label==0) {//∑≈÷√≥…π¶
				time=0;
				Level.updateMap();
				BuildingPanel.bldg=0;
			}
			//Main.gg.mapp.readyToBuild();
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
