package Pickaxe2Dr1;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Pick2DPanel extends JPanel {
	public DataOfPanel data;
	public ArrayList<PicLayer> layer=new ArrayList<PicLayer>();
	public PicLayer rdgLayer;
	int[][] map;
	boolean line=false;
	Color lineColor=Color.BLACK;
	PaintOnPanel pop=new PaintOnPanel() {
		public void paint(Graphics g) {
		}
	};
	public Pick2DPanel(Size gridSize,Size panelSize,Color preset,int PANEL_LABEL) {
		data=new DataOfPanel(panelSize,gridSize,preset,PANEL_LABEL);
		map=new int[panelSize.height][panelSize.width];
		for(int i=0;i<panelSize.height;i++) {
			for(int j=0;j<panelSize.width;j++) {
				map[i][j]=0;
			}
		}
		this.setSize(gridSize.width*panelSize.width, gridSize.height*panelSize.height);
		rdgLayer=new PicLayer(-1,this.data);
	}
	public void setGridSize(int width,int height) {
		this.data.gridSize=new Size(width,height);
	}
	public Size getGridSize() {
		return this.data.gridSize;
	}
	public void setIfDrawLine(boolean b) {
		this.line=b;
		this.repaint();
	}
	public void setLineColor(Color c) {
		this.lineColor=c;
	}
	public void paint(Graphics g) {
		int transparentGridWidth=7;
		Color dark=new Color(204,204,204),light=Color.WHITE;
		/*下午就认为此处可能得去掉
		 *因为如果多个panel叠加会导致下方panel不可见
		 *现在想想如若重叠应该只生成一个panel并叠加layer
		 *这个问题想留在以后再考虑
		*/
		for(int i=0;i<=data.gridSize.height*data.panelSize.height/transparentGridWidth;i++) {      //底部显示为"透明"的样子
			for(int j=0;j<=data.gridSize.width*data.panelSize.width/transparentGridWidth;j++) {
				g.setColor((j+i%2)%2==0?dark:light);
				g.fillRect(j*transparentGridWidth, i*transparentGridWidth, transparentGridWidth, transparentGridWidth);
			}
		}
		this.updateAllLayer();
		for(int i=0;layer.size()>0&&i<data.panelSize.height;i++) {
			for(int j=0;j<data.panelSize.width;j++) {
				//常规
				//System.out.println(g.getColor().toString());
				this.rdgLayer.blocks[i][j]=this.layer.get(map[i][j]).blocks[i][j];
				Block tempb=this.layer.get(map[i][j]).blocks[i][j];
				//System.out.println(tempb.color.toString());
				if(tempb.color!=null) {
					g.setColor(tempb.color);
					g.fillRect(j*this.data.gridSize.width,i*this.data.gridSize.height,
						this.data.gridSize.width,this.data.gridSize.height);
				}else {         //透明
					continue;
				}
				
			}
		}
		if(line) {
			g.setColor(lineColor);
			for(int i=0;i<this.data.panelSize.height;i++) {  //横线
				g.drawLine(0, this.data.gridSize.height*i, 
						this.data.gridSize.width*this.data.panelSize.width,
						this.data.gridSize.height*i);
			}
			for(int j=0;j<this.data.panelSize.width;j++) {   //竖线
				g.drawLine(this.data.gridSize.width*j, 0,
						this.data.gridSize.width*j,
						this.data.gridSize.height*this.data.panelSize.height);
			}
		}
		this.pop.paint(g);
	}
	public void setPaintOnPanelClass(PaintOnPanel pop) {
		this.pop=pop;
	}
	public int getLayerSize() {
		return layer.size();
	}
	public PicLayer addLayer() {
		int size=layer.size();
		this.layer.add(new PicLayer(size,this.data));
		return this.layer.get(this.layer.size()-1);
	}
	public void insertLayer(int location) {
		this.layer.add(location, new PicLayer(location,this.data));
		for(int i=location+1;i<layer.size();i++) {
			this.layer.get(i).layerNum++;
		}
	}
	public void removeLayer(int n) {
		this.layer.remove(n);
		for(int i=n;i<layer.size();i++) {
			this.layer.get(i).layerNum--;
		}
	}
	/*已删除Pickaxe2DManager.removePanel();方法，则此方法已弃用
	void changeLabel(int newLabel) {
		this.data.PANEL_LABEL=newLabel;
		for(int i=0;i<layer.size();i++) {
			layer.get(i).data.PANEL_LABEL=newLabel;
		}
	}*/
	public void rendering() {
		this.updateAllLayer();
		for(int i=0;layer.size()>0&&i<this.data.panelSize.height;i++) {
			for(int j=0;j<this.data.panelSize.width;j++) {
				this.rdgLayer.blocks[i][j]=this.layer.get(map[i][j]).blocks[i][j];
			}
		}
	}
	public void updateAllLayer() {
		for(int i=0;i<layer.size();i++) {
			layer.get(i).updatePanel();
		}
	}
	public void pressPxdFile(String path) {
		StringBuffer fstr=new StringBuffer();
		/*
		System.out.println(this.data.panelSize.getWidth());
		System.out.println("out:"+(byte)(this.data.panelSize.getWidth()>>>8)+" "+(byte)this.data.panelSize.getWidth());
		fstr.append((byte)(this.data.panelSize.getWidth()>>>8));
		fstr.append((byte)this.data.panelSize.getWidth());
		*/
		fstr.append(this.data.panelSize.getWidth()+","+this.data.panelSize.getHeight()
					+","+this.layer.size()+","+this.data.preset.getRGB()+";");
		for(int i=0;i<layer.size();i++) {
			PicLayer pl=layer.get(i);
			if(pl.shapable) {
				fstr.append("t"+"&");
				for(int j=0;j<pl.shape.size();j++) {
					PicLayer.Shape ps=pl.shape.get(j);
					fstr.append(ps.type);
					for(int k=0;k<ps.param.size();k++) {
						fstr.append(" "+ps.param.get(k));
					}
					fstr.append("&");
				}
			}else{
				fstr.append("f"+"&");
				for(int j=0;j<pl.data.panelSize.getHeight();j++) {
					for(int k=0;k<pl.data.panelSize.getWidth();k++) {
						Color c=pl.blocks[j][k].color;
						fstr.append(c.getRGB()+"&");
					}
				}
			}
			fstr.append(";");
		}
		
		new FileRW().write(path, fstr.toString(), false);
	}
	public static Pick2DPanel unpressPxdFile(String path) {
		String fstr=new FileRW().read(path);
		/*
		byte all[]=fstr.getBytes();
		int temp=all[0];
		temp<<=8;
		temp|=all[1];
		System.out.println(all[0]+" "+all[1]);
		*/
		String is[]=fstr.split(";");
		String basic[]=is[0].split(",");
		Pick2DPanel p2dp=new Pick2DPanel(new Size(1,1),new Size(Integer.parseInt(basic[0])
				,Integer.parseInt(basic[1])),new Color(Integer.parseInt(basic[3])),0);
		int layerAmount=Integer.parseInt(basic[2]);
		for(int i=0;i<layerAmount;i++) {
			p2dp.addLayer();
			String lstr[]=is[i+1].split("&");
			if(lstr[0].equals("t")) {           //shapable
				p2dp.layer.get(p2dp.layer.size()-1).shapable=true;
				for(int j=0;j<lstr.length-1;j++) {
					p2dp.layer.get(p2dp.layer.size()-1).addShape(lstr[j+1]);
				}
			}else if(lstr[0].equals("f")) {
				p2dp.layer.get(p2dp.layer.size()-1).shapable=false;
				int bi=1,w=Integer.parseInt(basic[0]),h=Integer.parseInt(basic[1]);
				for(int j=0;j<h;j++) {
					for(int k=0;k<w;k++) {
						p2dp.layer.get(p2dp.layer.size()-1).blocks[j][i]
								.color=new Color(Integer.parseInt(lstr[bi++]));
					}
				}
			}
		}
		return p2dp;
	}
}
