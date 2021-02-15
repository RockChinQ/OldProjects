package PickViewer;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

import Pickaxe2D.PickPanel.Grid;

public class PickButton extends JButton {

	/*panel data*/
	public int gridOfWidth=1,gridOfHeight=1;
	public int zoom=1;//每个格子几像素长宽
	public class Grid{
		Color gridColor=null;
		public Grid() {
			
		}
	}
	public Grid[][] grid;
	public boolean gridLine=false;
	public PickButton(){//call jpanel no param method.
		super();
	}
	public PickButton(int zoom,int gridOfWidth,int gridOfHeight) {
		this.zoom=zoom;
		this.gridOfWidth=gridOfWidth;
		this.gridOfHeight=gridOfHeight;
		
		reset();
	}
	
	public void paint(Graphics g) {
		g.setColor(this.getBackground());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		//System.out.println(this.getWidth()+" "+this.getHeight()+" zoom "+zoom+" gow"+this.gridOfWidth+" goh"+this.gridOfHeight);
		for(int i=0;i<this.gridOfHeight;i++) {
			for(int j=0;j<this.gridOfWidth;j++) {
				g.setColor(this.grid[i][j].gridColor==null?this.getBackground():this.grid[i][j].gridColor);
				g.fillRect(j*zoom, i*zoom, zoom, zoom);
			}
		}
		if(this.gridLine) {
			g.setColor(Color.DARK_GRAY);
			for(int i=0;i<this.gridOfWidth;i++) {
				g.drawLine(i*zoom, 0, i*zoom, this.gridOfHeight*zoom);
			}
			for(int i=0;i<this.gridOfHeight;i++) {
				g.drawLine(0, i*zoom, zoom*this.gridOfWidth, i*zoom);
			}
		}
	}
	
	public void setGridSize(int zoom) {
		this.zoom=zoom;
	}
	public void setPanelSizeOnGrid(int width,int height) {
		this.gridOfWidth=width;
		this.gridOfHeight=height;
		reset();
	}
	
	public void reset() {
		this.setSize(zoom*this.gridOfWidth,zoom*this.gridOfHeight);//向系统设置 本面板大小
		this.grid=new Grid[this.gridOfHeight][this.gridOfWidth];
		for(int i=0;i<this.gridOfHeight;i++) {
			for(int j=0;j<this.gridOfWidth;j++) {
				this.grid[i][j]=new Grid();
			}
		}
	}
	public void setGrid(int x,int y,Color c) {
		this.grid[y][x].gridColor=c;
	}

	public void loadPickImage(int x,int y,PickImage pi) {
		int index=0;
		for(int i=0;i<pi.h;i++) {
			for(int j=0;j<pi.w;j++) {
				if(i+y>=this.gridOfHeight||j+x>=this.gridOfWidth) {
					index++;
					continue;
				}
				//String[] grids=pi.data[index++].split(",");
				if(pi.data[index][0]==-1) {//透明
					index++;
					continue;
				}
				this.grid[i+y][j+x].gridColor=new Color(pi.data[index][0],pi.data[index][1],pi.data[index][2]);
				//System.out.println("set:"+(j+x)+","+(i+y));
				index++;
			}
		}
	}
	/*
	public void loadFrontPickImage(int x,int y,PickImage pi) {
		int index=0;
		for(int i=0;i<pi.h;i++) {
			for(int j=0;j<pi.w;j++) {
				if(i+y>=this.gridOfHeight||j+x>=this.gridOfWidth) {
					index++;
					continue;
				}
				if(pi.data[index][0]==-1) {//透明
					index++;
					continue;
				}
				this.front[i+y][j+x].gridColor=new Color(pi.data[index][0],pi.data[index][1],pi.data[index][2]);
				//System.out.println("set:"+(j+x)+","+(i+y));
				index++;
			}
		}
	}*/
}
