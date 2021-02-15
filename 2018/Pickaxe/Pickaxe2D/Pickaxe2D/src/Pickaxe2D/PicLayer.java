package Pickaxe2D;

import java.awt.Color;
import java.util.ArrayList;

public class PicLayer {
	public Block[][] blocks;
	public DataOfPanel data;
	public int layerNum=0;
	public ArrayList<Shape> shape=new ArrayList<Shape>();
	public boolean shapable=true;
	public StringBuffer name;
	class Shape{
		String type;
		ArrayList<Object> param=new ArrayList<Object>();
		Shape(String command){
			String[] cmdsp=command.split(" ");
			type=new String(cmdsp[0]);
			for(int i=1,len=cmdsp.length;i<len;i++) {
				param.add(cmdsp[i]);
			}
		}
		Shape(String type,ArrayList param){
			this.type=type;
			this.param=param;
		}
	}
	PicLayer(int layerNum,DataOfPanel data){
		this.layerNum=layerNum;
		this.data=data;
		reset();
	}
	public void reset(){
		blocks=new Block[data.panelSize.height][data.panelSize.width];
		for(int i=0;i<data.panelSize.height;i++) {
			for(int j=0;j<data.panelSize.width;j++) {
				blocks[i][j]=new Block(data.preset);
			}
		}
		
	}
	//初步处理
	/*已支持的指令
	 * point <int x> <int y> <int R> <int G> <int B>
	 * line <int startX> <int startY> <int endX> <int endY> <int R> <int G> <int B>
	 * rect <int x> <int y> <int width> <int height> <int R> <int G> <int B> <boolean fill>
	 * circle <int x> <int y> <int radius> <int R> <int G> <int B> <boolean fill>
	 * */
	public void setName(String name) {
		this.name=new StringBuffer(name);
	}
	public String getName() {
		return this.name.toString();
	}
	public void addShape(Shape s) {
		this.shape.add(s);
	}
	public void addShape(String str) {
		this.shape.add(new Shape(str));
	}
	public void addShapes(String[] str) {
		for(int i=0;i<str.length;i++) {
			this.addShape(str[i]);
		}
	}
	public void drawPoint(int x,int y,Color c) {
		if(this.shapable) {
			shape.add(new Shape("point "+x+" "+y+" "+c.getRed()+" "
		+c.getGreen()+" "+c.getBlue()));
			return;
		}
		drawPointFin(x,y,c);
	}
	public void drawLine(int startX,int startY,int endX,int endY,Color c) {
		if(this.shapable) {
			shape.add(new Shape("line "+startX+" "+startY+" "+endX+" "
		+endY+" "+c.getRed()+" "+c.getGreen()+" "+c.getBlue()));
			return;
		}
		drawLineFin(startX,startY,endX,endY,c);
	}
	public void fillRect(int x,int y,Size s,Color c,boolean fill) {
		s=new Size(s.width-1,s.height-1);
		if(this.shapable) {
			shape.add(new Shape("rect "+x+" "+y+" "+s.getWidth()+" "
		+s.getHeight()+" "+c.getRed()+" "+c.getGreen()+" "+c.getBlue()+" "+fill));
			return;
		}
		fillRectFin(x,y,s,c,fill);
	}
	public void fillCircle(int x,int y,int radius,Color c,boolean fill) {
		if(this.shapable) {
			shape.add(new Shape("circle "+x+" "+y+" "+radius+" "
		+c.getRed()+" "+c.getGreen()+" "+c.getBlue()+" "+fill));
			return;
		}
		fillCircleFin(x,y,radius,c,fill);
	}
	//控制shapable
	public void setShapableFalse() {
		updatePanel();
		this.shapable=false;
	}
	public void setShapableTrue() {
		
	}
	public boolean isShapable() {
		return this.shapable;
	}
	//更新面板 按照shape数据修改blocks以便其他地方使用
	public void updatePanel() {
		if(!this.shapable)              //如果已被栅格化则直接返回
			return;
		for(int i=0;i<this.shape.size();i++) {       //遍历所有形状并绘制
			ArrayList<Object> params=shape.get(i).param;
			switch(shape.get(i).type){
			case "point":{
				this.drawPointFin(Integer.parseInt((String) params.get(0)),
						Integer.parseInt((String) params.get(1)),
						new Color(Integer.parseInt((String) params.get(2)),
								Integer.parseInt((String) params.get(3)),
								Integer.parseInt((String) params.get(4))));
				break;
			}
			case "line":{
				this.drawLineFin(Integer.parseInt((String)params.get(0)),
						Integer.parseInt((String)params.get(1)),
						Integer.parseInt((String)params.get(2)),
						Integer.parseInt((String)params.get(3)),
						new Color(Integer.parseInt((String) params.get(4)),
								Integer.parseInt((String) params.get(5)),
								Integer.parseInt((String) params.get(6))));
				break;
			}
			case "rect":{
				this.fillRectFin(Integer.parseInt((String)params.get(0)),
						Integer.parseInt((String)params.get(1)),
						new Size(Integer.parseInt((String)params.get(2)),
								Integer.parseInt((String)params.get(3))),
						new Color(Integer.parseInt((String) params.get(4)),
								Integer.parseInt((String) params.get(5)),
								Integer.parseInt((String) params.get(6))),
						Boolean.parseBoolean((String)params.get(7)));
				break;
			}
			case "circle":{
				this.fillCircleFin(Integer.parseInt((String)params.get(0)),
						Integer.parseInt((String)params.get(1)),
						Integer.parseInt((String)params.get(2)),
						new Color(Integer.parseInt((String) params.get(3)),
								Integer.parseInt((String) params.get(4)),
								Integer.parseInt((String) params.get(5))),
						Boolean.parseBoolean((String)params.get(6)));
				break;
			}
			}
		}
	}
	//栅格化处理
	public void drawPointFin(int x,int y,Color c) {
		if(c!=null&&x>=0&&x<this.data.panelSize.width&&y>=0&&y<this.data.panelSize.height) {
			this.blocks[y][x].color=c;
			int n=Pickaxe2DManager.panel.get(this.data.PANEL_LABEL).map[y][x];
			Pickaxe2DManager.panel.get(this.data.PANEL_LABEL).map[y][x]=(n<=this.layerNum?this.layerNum:n);
		}
	}
	public void drawLineFin(int startX,int startY,int endX,int endY,Color c) {
		if(startX<endX&&startY<endY){         //   \
			if(endX-startX!=endY-startY){         //矩形
				if(endX-startX>endY-startY){        //startX长
					int nowy=0;
					float dt=(float)(endX-startX)/(float)(endY-startY);
					for(int a=0;a<=endX-startX;a++){
						drawPointFin(a+startX,nowy+startY,c);
						if((float)a%dt<1){
							nowy++;
						}
					}
				}else{         //y长
					int nowx=0;
					float dt=(float)(endY-startY)/(float)(endX-startX);
					for(int a=0;a<=endY-startY;a++){
						drawPointFin(nowx+startX,a+startY,c);
						if((float)a%dt<1){
							nowx++;
						}
					}
				}
			}else{                 //方形
				for(int a=0;a<=endX-startX;a++){
					drawPointFin(startX+a,startY+a,c);
				}
			}
		}else if(endX>startX&&endY<startY){     //  /
			if(endX-startX!=startY-endY){      //矩形
				if(endX-startX>startY-endY){       //x长
					int nowy=0;
					float dt=(float)(endX-startX)/(float)(startY-endY);
					for(int a=0;a<=endX-startX;a++){
						drawPointFin(a+startX,startY-nowy,c);
						if((float)a%dt<1){
							nowy++;
						}
					}
				}else{            //y长
					int nowx=0;
					float dt=(float)(startY-endY)/(float)(endX-startX);
					for(int a=0;a<=startY-endY;a++){
						drawPointFin(nowx+startX,startY-a,c);
						if((float)a%dt<1){
							nowx++;
						}
					}
				}
			}else{
				for(int a=0;a<endX-startX;a++){
					drawPointFin(startX+a,startY-a,c);
				}
			}
		}else if(endX>startX&&endY==startY) {
			for(int i=startX;i<=endX;i++) {
				drawPointFin(i,endY,c);
			}
		}else if(endX==startX&&endY>startY) {
			for(int i=startY;i<=endY;i++) {
				drawPointFin(endX,i,c);
			}
		}
		//this.repaint();!!
	}
	public void fillRectFin(int x,int y,Size s,Color c,boolean fill) {
		int sx=x,sy=y;
		int ty=y;
		for(;x<sx+s.width;x++){
			for(y=ty;y<sy+s.height;y++){
				if(fill)
					drawPointFin(x,y,c);
				else if(!fill&&((x==sx||x==sx+s.width)||(y==sy||y==sy+s.height))){
					drawPointFin(x,y,c);
				}
			}
		}
		//this.repaint();
	}
	public void fillCircleFin(int x,int y,int radius,Color c,boolean fill) {
		for(int a=0;a<x+2*radius+1;a++){
			for(int b=0;b<y+2*radius+1;b++){
				if((getDistance(a, b,radius, radius)==radius))
					drawPointFin(a+x,b+y,c);
				if(fill&&(getDistance(a, b,radius, radius)<radius))
					drawPointFin(a+x,b+y,c);
			}
		}
		//this.repaint();
	}
	public int getDistance(int x0,int y0,int x1,int y1){
		int tx=x0,ty=y0;
		x0=Math.min(x0, x1);
		y0=Math.min(y0, y1);
		x1=Math.max(tx, x1);
		y1=Math.max(ty, y1);
		return (int) Math.round(Math.sqrt(Math.pow((double)x1-(double)x0, 2)+Math.pow((double)y1-(double)y0, 2)));
	}
	
}
