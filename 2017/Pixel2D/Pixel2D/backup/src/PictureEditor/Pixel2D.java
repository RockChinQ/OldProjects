package PictureEditor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Pixel2D extends JPanel{
	//常规
	int gridWeight;
	int gridHeight;
	int panelWeight;
	int panelHeight;
	Color bgcolor;
	boolean showGridLine;
	Color gridLine;
	boolean picFloorOnTop=false;
	Color trans=Color.white;
	//图层数据
	Color[][] picture;//顶层控件 底层图画
	Color[][] component;
	//图形常量
	String cha=read("chars.txt");
	//控件
	Component[] coms=new Component[50];
	int comsc=0;
	//绘图
	BufferedImage bi;
	Graphics2D g;
	StringBuffer str=new StringBuffer("");
	//监听器
	MouseListener mouseListener;
	Pixel2D(int gridWeight,int gridHeight,int panelWeight,int panelHeight,
			Color bgcolor,boolean showGridLine,Color gridLine,boolean picFloorOnTop,MouseListener mouseListener){
		bi=new BufferedImage(gridWeight*panelWeight,gridHeight*panelHeight,BufferedImage.TYPE_INT_RGB);
		g=bi.createGraphics();
		this.gridWeight=gridWeight;
		this.gridHeight=gridHeight;
		this.panelWeight=panelWeight;
		this.panelHeight=panelHeight;
		this.bgcolor=bgcolor;
		this.showGridLine=showGridLine;
		this.gridLine=gridLine;
		this.picture=new Color[panelWeight][panelHeight];
		this.component=new Color[panelWeight][panelHeight];
		this.picFloorOnTop=picFloorOnTop;
		this.setSize(gridWeight*panelWeight,gridHeight*panelHeight);
		this.mouseListener=mouseListener;
		this.setSize(gridWeight*panelWeight+1, gridHeight*panelHeight+1);
		//初始化图层数据
		for(int a=0;a<panelWeight;a++){
			for(int b=0;b<panelHeight;b++){
				picture[a][b]=bgcolor;
			}
		}
		for(int a=0;a<panelWeight;a++){
			for(int b=0;b<panelHeight;b++){
				component[a][b]=bgcolor;
			}
		}
		//注册监听器
		this.addMouseListener(this.mouseListener);
	}
	public void paint(Graphics g){
		main.w.message.setText("正在绘制...");
		for(int a=0;a<panelWeight;a++){
			for(int b=0;b<panelHeight;b++){
				if(picture[a][b]!=null){
					g.setColor(picture[a][b]);
					g.fillRect(a*gridWeight, b*gridHeight,gridWeight, gridHeight);
				}else{
					g.setColor(this.trans);
					g.drawString("T",a*gridWeight+gridWeight/2 , (b+1)*gridHeight);
				}
			}
		}
		for(int a=0;a<panelWeight;a++){
			for(int b=0;b<panelHeight;b++){
				if(component[a][b]!=bgcolor){
					g.setColor(component[a][b]);
					g.fillRect(a*gridWeight, b*gridHeight,gridWeight, gridHeight);
					//System.out.println(component[a][b].toString());
				}
			}
		}
		//框线
		g.setColor(gridLine);
		for(int a=0;a<=panelHeight&&showGridLine;a++){
			g.drawLine(0, a*gridHeight, panelWeight*gridWeight, a*gridHeight);
		}
		for(int a=0;a<=panelWeight&&showGridLine;a++){
			g.drawLine(a*gridWeight, 0, a*gridWeight,panelHeight*gridHeight);
		}
		main.w.message.setText("选定坐标"+main.p.point1);
	}
	void resetPicture(){

		for(int a=0;a<panelWeight;a++){
			for(int b=0;b<panelHeight;b++){
				picture[a][b]=bgcolor;
			}
		}
	}
	void setPictureOnTop(){
		
	}
	void setMouseListener(MouseListener ml){
		this.mouseListener=ml;
	}
	int getXonPanel(int x){
		return x/this.gridWeight;
	}
	int getYonPanel(int y){
		return y/this.gridHeight;
	}
	String read(String s){
		File f=new File(s);
		try {
			FileInputStream fis=new FileInputStream(f);
			byte[] data=new byte[(int)f.length()];
			fis.read(data);
			fis.close();
			return new String(new String(data));
		} catch (Exception e) {
		}
		return "";
	}
	void setGrid(int x,int y,Color gcolor){
		if(x<picture.length&&y<picture[0].length)
			picture[x][y]=gcolor;
		this.repaint();
	}
	void drawLine(int x,int y,int endx,int endy,Color cl){
		if(x<picture.length&&y<picture[0].length){
			if(x==endx)
				endx++;
			if(y==endy)
				endy++;
			if(x<endx&&y<endy){         //左上到右下
				int ty=0;
				for(int a=0;((double)endx-(double)x)>=((double)endy-(double)y)&&a+x<endx;a++){                //x长
					if(ty+y<endy){
						if(((double)endx-(double)x)/((double)endy-(double)y)>0&&a%Math.round(((double)endx-(double)x)/((double)endy-(double)y))==0){//x长
							ty++;
						}
					}
					if(a+x<panelWeight&&ty+y<panelHeight){
						picture[a+x][ty+y]=cl;
					}
				}
				int tx=0;
				for(int b=0;((double)endy-(double)y)>((double)endx-(double)x)&&b+y<endy;b++){          //y长
					if(tx+x<endx){
						if(((double)endy-(double)y)/((double)endx-(double)x)>0&&b%Math.round(((double)endy-(double)y)/((double)endx-(double)x))==0){
							tx++;
						}
					}
					if(tx+x<panelWeight&&b+y<panelHeight){
						picture[tx+x][b+y]=cl;
					}
				}
			}else if(x<endx&&y>endy){     //左下到右上
				int ty=0;
				for(int a=0;((double)endx-(double)x)>((double)y-(double)endy)&&a+x<endx;a++){            //x轴长
					if(y-ty>endy){
						if(((double)x-(double)endx)/((double)y-(double)endy)>0&&a%Math.round(((double)x-(double)endx)/((double)y-(double)endy))==0){
							ty++;
						}
					}
					if(a+x<panelWeight&&y-ty>0){
						picture[a+x][y-ty]=cl;
					}
				}
				int tx=0;
				for(int b=0;((double)y-(double)endy)>=((double)endx-(double)x)&&y-b>endy;b++){            //y轴长
					if(x+tx<endx){
						if(((double)y-(double)endy)/((double)endx-(double)x)>0&&b%Math.round(((double)y-(double)endy)/((double)endx-(double)x))==0){
							tx++;
						}
					}
					if(y-b>0&&x+tx<panelWeight){
						picture[x+tx][y-b]=cl;
					}
				}
			}
		}
		this.repaint();
	}
	void drawRect(int x,int y,int weight,int height,Color cl,boolean fill){
		if(x<picture.length&&y<picture[0].length){
			int sx=x,sy=y;
			int ty=y;
			for(;x<=weight;x++){
				for(y=ty;y<=height;y++){
					if(fill&&x<panelWeight&&y<panelHeight)
						picture[x][y]=cl;
					else if(!fill&&x<panelWeight&&y<panelHeight&&((x==sx||x==sx+weight)||(y==sy||y==sy+height))){
						picture[x][y]=cl;
					}
				}
			}
		}
		this.repaint();
	}
	void drawByCode(int x,int y,Color cl,String code){
		if(x>=panelWeight||y>=panelHeight)
			return;
		int temp=x;
		x=y;y=temp;
		String[] code0=code.split("&");
		int w=Integer.parseInt(code0[0]),h=Integer.parseInt(code0[1]);
		//System.out.println(code0.length);
		int now=2;
		for(int a=0;a<w;a++){
			for(int b=0;b<h;b++){
				if(code0[now].equals("n")){
					now++;
					continue;
				}else if(code0[now].equals("cl")&&a+y<picture.length&&b+x<picture[0].length){
					picture[a+y][b+x]=cl;
				}else if(a+y<picture.length&&b+x<picture[0].length)
					picture[a+y][b+x]=new Color(Integer.parseInt(code0[now].split(",")[0]),Integer.parseInt(code0[now].split(",")[1]),Integer.parseInt(code0[now].split(",")[2]));
				now++;
			}
		}
		this.repaint();
	}
	void drawChar(int x,int y,char ch,Color cl,int size){
		if(x>=panelWeight||y>=panelHeight)
			return;
		String[] chars=cha.split(";");
		int len=chars.length;
		for(int a=0;a<len;a++){
			String[] ach=chars[a].split(":");
			if(ch==ach[0].toCharArray()[0]){
				char[] chcode=ach[1].toCharArray();
				int now=0;
				int lin=(int)Math.sqrt(chcode.length);
				//字号
				char[][] lgrc=new char[lin*size][lin*size];
				int snow=0;                  //读源标记
				for(int b=0;b<lin;b++){               //读源
					for(int c=0;c<lin;c++){             //读源
						for(int d=0;d<size;d++){
							for(int e=0;e<size;e++){
								lgrc[b*size+d][c*size+e]=chcode[snow];
							}
						}
						snow++;
					}
				}
				char[] code2=new char[lin*size*lin*size];
				int codec=0;
				for(int b=0;b<lin*size;b++){
					for(int c=0;c<lin*size;c++){
						code2[codec]=lgrc[b][c];
						codec++;
					}
				}
				//字号end
				for(int b=0;b<lin*size;b++){
					for(int c=0;c<lin*size;c++){
						//System.out.println(chcode[now]);
						if(code2[now]=='1'){
							//System.out.println((c+x)+" "+(gridHeight*panelHeight));
							if(c+x>=panelWeight){
								continue;
							}
							if(c+x<panelWeight&&b+y<panelHeight)
								picture[c+x][b+y]=cl;
						}
						now++;
					}
				}
				break;
			}
		}
	}
	void drawString(int x,int y,String str,Color cl,int size){
		char[] avchar=str.toCharArray();
		for(int a=0;a<avchar.length;a++){
			drawChar(x+8*a*size,y,avchar[a],cl,size);
		}
	}
	int getDistance(int x0,int y0,int x1,int y1){
		int tx=x0,ty=y0;
		x0=Math.min(x0, x1);
		y0=Math.min(y0, y1);
		x1=Math.max(tx, x1);
		y1=Math.max(ty, y1);
		//System.out.println(x0+" "+x1+" "+y0+" "+y1);
		return (int) Math.round(Math.sqrt(Math.pow((double)x1-(double)x0, 2)+Math.pow((double)y1-(double)y0, 2)));
	}
	void drawCircle(int x,int y,int radius,Color cl,boolean fill){
		for(int a=0;a<x+2*radius+1;a++){
			for(int b=0;b<y+2*radius+1;b++){
				//System.out.println((getDistance(a, b,x+radius, y+radius)+" "+a+" "+b));
				if(x+a<panelWeight&&y+b<panelHeight){
					if((getDistance(a, b,radius, radius)==radius))
						picture[a+x][b+y]=cl;
					if(fill&&(getDistance(a, b,radius, radius)<radius))
						picture[a+x][b+y]=cl;
				}
			}
		}
		this.repaint();
	}
	Color getGird(int x,int y){
		return picture[x][y];
	}
	void drawComponentString(int x,int y,String str,Color cl,int size,Component com){
		char[] avchar=str.toCharArray();
		for(int a=0;a<avchar.length;a++){
			drawComponentChar(x+8*a*size,y,avchar[a],cl,size,com);
		}
	}
	void drawComponentChar(int x,int y,char ch,Color cl,int size,Component com){
		if(x>=panelWeight||y>=panelHeight)
			return;
		String[] chars=cha.split(";");
		int len=chars.length;
		for(int a=0;a<len;a++){
			String[] ach=chars[a].split(":");
			if(ch==ach[0].toCharArray()[0]){
				char[] chcode=ach[1].toCharArray();
				int now=0;
				int lin=(int)Math.sqrt(chcode.length);
				//字号
				char[][] lgrc=new char[lin*size][lin*size];
				int snow=0;                  //读源标记
				for(int b=0;b<lin;b++){               //读源
					for(int c=0;c<lin;c++){             //读源
						for(int d=0;d<size;d++){
							for(int e=0;e<size;e++){
								lgrc[b*size+d][c*size+e]=chcode[snow];
							}
						}
						snow++;
					}
				}
				char[] code2=new char[lin*size*lin*size];
				int codec=0;
				for(int b=0;b<lin*size;b++){
					for(int c=0;c<lin*size;c++){
						code2[codec]=lgrc[b][c];
						codec++;
					}
				}
				//字号end
				for(int b=0;b<lin*size;b++){
					for(int c=0;c<lin*size;c++){
						//System.out.println(chcode[now]);
						if(code2[now]=='1'){
							//System.out.println((c+x)+" "+(gridHeight*panelHeight));
							if(c+x>=panelWeight){
								continue;
							}
							if(c+x<(x+com.getX()+com.getWidth())&&b+y<(y+com.getY()+com.getHeight()))
								component[c+x][b+y]=cl;
						}
						now++;
					}
				}
				break;
			}
		}
	}
	void addButton(JButton bt){
		coms[comsc]=bt;
		comsc++;
		drawButton(bt);
	}
	void drawButton(JButton bt){
		int w=bt.getWidth(),h=bt.getHeight();
		int x=bt.getX(),y=bt.getY();
		for(int a=0;a<w;a++){
			for(int b=0;b<h;b++){
				if(a==0||a==w-1||b==0||b==h-1){
					if(a+y<panelWeight&&b+x<panelHeight){
						component[a+y][b+x]=bt.getBackground();
						//System.out.println(bt.getBackground().toString());
					}
				}
			}
		}
		drawComponentString(x,y+bt.getHeight()-7*bt.getFont().getSize()-2,bt.getText(),bt.getBackground(),bt.getFont().getSize(),bt);
	}
	void updateComponent(){
		for(int a=0;a<panelWeight;a++){
			for(int b=0;b<panelHeight;b++){
				
			}
		}
	}
}









