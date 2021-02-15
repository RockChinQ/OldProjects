package PictureViewer;

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
	//����
	int gridwidth;
	int gridHeight;
	int panelwidth;
	int panelHeight;
	Color bgcolor;
	boolean showGridLine;
	Color gridLine;
	boolean picFloorOnTop=false;
	Color trans=Color.white;
	//ͼ������
	Color[][] picture;//����ؼ� �ײ�ͼ��
	Color[][] component;
	//ͼ�γ���
	StringBuffer cha=new StringBuffer(read("chars-7.txt"));
	//�ؼ�
	Component[] coms=new Component[50];
	int comsc=0;
	//��ͼ
	BufferedImage bi;
	Graphics2D g;
	StringBuffer str=new StringBuffer("");
	//������
	MouseListener mouseListener;
	Pixel2D(int gridwidth,int gridHeight,int panelwidth,int panelHeight,
			Color bgcolor,boolean showGridLine,Color gridLine,boolean picFloorOnTop,MouseListener mouseListener){
		bi=new BufferedImage(gridwidth*panelwidth,gridHeight*panelHeight,BufferedImage.TYPE_INT_RGB);
		g=bi.createGraphics();
		this.gridwidth=gridwidth;
		this.gridHeight=gridHeight;
		this.panelwidth=panelwidth;
		this.panelHeight=panelHeight;
		this.bgcolor=bgcolor;
		this.showGridLine=showGridLine;
		this.gridLine=gridLine;
		this.picture=new Color[panelwidth][panelHeight];
		this.component=new Color[panelwidth][panelHeight];
		this.picFloorOnTop=picFloorOnTop;
		this.setSize(gridwidth*panelwidth,gridHeight*panelHeight);
		this.mouseListener=mouseListener;
		this.setSize(gridwidth*panelwidth+1, gridHeight*panelHeight+1);
		//��ʼ��ͼ������
		for(int a=0;a<panelwidth;a++){
			for(int b=0;b<panelHeight;b++){
				picture[a][b]=bgcolor;
			}
		}
		for(int a=0;a<panelwidth;a++){
			for(int b=0;b<panelHeight;b++){
				component[a][b]=bgcolor;
			}
		}
		//ע�������
		this.addMouseListener(this.mouseListener);
	}
	public void paint(Graphics g){
		for(int a=0;a<panelwidth;a++){
			for(int b=0;b<panelHeight;b++){
				if(picture[a][b]!=null){
					g.setColor(picture[a][b]);
					g.fillRect(a*gridwidth, b*gridHeight,gridwidth, gridHeight);
				}else{
					g.setColor(this.trans);
					g.drawString("T",a*gridwidth+gridwidth/2 , (b+1)*gridHeight);
				}
			}
		}
		for(int a=0;a<panelwidth;a++){
			for(int b=0;b<panelHeight;b++){
				if(component[a][b]!=bgcolor){
					g.setColor(component[a][b]);
					g.fillRect(a*gridwidth, b*gridHeight,gridwidth, gridHeight);
					//System.out.println(component[a][b].toString());
				}
			}
		}
		//����
		g.setColor(gridLine);
		for(int a=0;a<=panelHeight&&showGridLine;a++){
			g.drawLine(0, a*gridHeight, panelwidth*gridwidth, a*gridHeight);
		}
		for(int a=0;a<=panelwidth&&showGridLine;a++){
			g.drawLine(a*gridwidth, 0, a*gridwidth,panelHeight*gridHeight);
		}
	}
	void setGridSize(int width,int height){
		gridwidth=width;
		gridHeight=height;
	}
	void resetPicture(){

		for(int a=0;a<panelwidth;a++){
			for(int b=0;b<panelHeight;b++){
				picture[a][b]=bgcolor;
			}
		}
		this.repaint();
	}
	void setPictureOnTop(){
		
	}
	void setMouseListener(MouseListener ml){
		this.mouseListener=ml;
	}
	int getXonPanel(int x){
		return x/this.gridwidth;
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
	String getCode(){

		StringBuffer general=new StringBuffer(panelwidth+"&"+panelHeight+"&");
		for(int a=0;a<panelwidth;a++){
			for(int b=0;b<panelHeight;b++){
				if(picture[a][b]==null){
					general.append("n&");
				}else{
					general.append(picture[a][b].getRed()+","+picture[a][b].getGreen()+","+picture[a][b].getBlue()+"&");
				}
			}
		}
		return general.toString();
	}
	void drawLine(int x,int y,int endx,int endy,Color cl){
		if(x<picture.length&&y<picture[0].length){
			if(x==endx)
				endx++;
			if(y==endy)
				endy++;
			if(x<endx&&y<endy){         //���ϵ�����
				int ty=0;
				for(int a=0;((double)endx-(double)x)>=((double)endy-(double)y)&&a+x<endx;a++){                //x��
					if(ty+y<endy){
						if(((double)endx-(double)x)/((double)endy-(double)y)>0&&a%Math.round(((double)endx-(double)x)/((double)endy-(double)y))==0){//x��
							ty++;
						}
					}
					if(a+x<panelwidth&&ty+y<panelHeight){
						picture[a+x][ty+y]=cl;
					}
				}
				int tx=0;
				for(int b=0;((double)endy-(double)y)>((double)endx-(double)x)&&b+y<endy;b++){          //y��
					if(tx+x<endx){
						if(((double)endy-(double)y)/((double)endx-(double)x)>0&&b%Math.round(((double)endy-(double)y)/((double)endx-(double)x))==0){
							tx++;
						}
					}
					if(tx+x<panelwidth&&b+y<panelHeight){
						picture[tx+x][b+y]=cl;
					}
				}
			}else if(x<endx&&y>endy){     //���µ�����
				int ty=0;
				for(int a=0;((double)endx-(double)x)>((double)y-(double)endy)&&a+x<endx;a++){            //x�᳤
					if(y-ty>endy){
						if(((double)x-(double)endx)/((double)y-(double)endy)>0&&a%Math.round(((double)x-(double)endx)/((double)y-(double)endy))==0){
							ty++;
						}
					}
					if(a+x<panelwidth&&y-ty>0){
						picture[a+x][y-ty]=cl;
					}
				}
				int tx=0;
				for(int b=0;((double)y-(double)endy)>=((double)endx-(double)x)&&y-b>endy;b++){            //y�᳤
					if(x+tx<endx){
						if(((double)y-(double)endy)/((double)endx-(double)x)>0&&b%Math.round(((double)y-(double)endy)/((double)endx-(double)x))==0){
							tx++;
						}
					}
					if(y-b>0&&x+tx<panelwidth){
						picture[x+tx][y-b]=cl;
					}
				}
			}
		}
		this.repaint();
	}
	void drawRect(int x,int y,int width,int height,Color cl,boolean fill){
		if(x<picture.length&&y<picture[0].length){
			int sx=x,sy=y;
			int ty=y;
			for(;x<=sx+width;x++){
				for(y=ty;y<=sy+height;y++){
					if(fill&&x<panelwidth&&y<panelHeight)
						picture[x][y]=cl;
					else if(!fill&&x<panelwidth&&y<panelHeight&&((x==sx||x==sx+width)||(y==sy||y==sy+height))){
						picture[x][y]=cl;
					}
				}
			}
		}
		this.repaint();
	}
	void drawByCode(int x,int y,Color cl,String code){
		try{
		if(x>=panelwidth||y>=panelHeight)
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
		}catch(Exception e){
			javax.swing.JOptionPane.showMessageDialog(null, "code���Ϸ�");
		}
	}
	int drawChar(int x,int y,char ch,Color cl,int size,String charset){
		boolean found=false;
		if(x>=panelwidth||y>=panelHeight)
			return 0;
		String[] chars=read(charset).split(";");
		int lin=0;
		int len=chars.length;
		for(int a=0;a<len;a++){
			String[] ach=chars[a].split(":");
			if(ch==ach[0].toCharArray()[0]){
				found=true;
				char[] chcode=ach[1].toCharArray();
				int now=0;
				lin=(int)Math.sqrt(chcode.length);
				//�ֺ�
				char[][] lgrc=new char[lin*size][lin*size];
				int snow=0;                  //��Դ���
				for(int b=0;b<lin;b++){               //��Դ
					for(int c=0;c<lin;c++){             //��Դ
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
				//�ֺ�end
				for(int b=0;b<lin*size;b++){
					for(int c=0;c<lin*size;c++){
						//System.out.println(chcode[now]);
						if(code2[now]=='1'){
							//System.out.println((c+x)+" "+(gridHeight*panelHeight));
							if(c+x>=panelwidth){
								now++;
								continue;
							}
							if(c+x<panelwidth&&b+y<panelHeight)
								picture[c+x][b+y]=cl;
						}
						now++;
					}
				}
				break;
			}
		}
		if(!found)
			return 6;
		this.repaint();
		return lin*size;
	}
	void drawString(int x,int y,String str,Color cl,int size,String charset){
		char[] avchar=str.toCharArray();
		int loa=0;
		for(int a=0;a<avchar.length;a++){
			loa+=drawChar(x+loa,y,avchar[a],cl,size,charset)+1;
		}
		this.repaint();
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
				if(x+a<panelwidth&&y+b<panelHeight){
					if((getDistance(a, b,radius, radius)==radius))
						picture[a+x][b+y]=cl;
					if(fill&&(getDistance(a, b,radius, radius)<radius))
						picture[a+x][b+y]=cl;
				}
			}
		}
		this.repaint();
	}
	void drawComponentString(int x,int y,String str,Color cl,int size,Component com,String charset){
		char[] avchar=str.toCharArray();
		for(int a=0;a<avchar.length;a++){
			drawComponentChar(x+8*a*size,y,avchar[a],cl,size,com,charset);
		}
		this.repaint();
	}
	void drawComponentChar(int x,int y,char ch,Color cl,int size,Component com,String charset){
		if(x>=panelwidth||y>=panelHeight)
			return;
		String[] chars=read(charset).split(";");
		int len=chars.length;
		for(int a=0;a<len;a++){
			String[] ach=chars[a].split(":");
			if(ch==ach[0].toCharArray()[0]){
				char[] chcode=ach[1].toCharArray();
				int now=0;
				int lin=(int)Math.sqrt(chcode.length);
				//�ֺ�
				char[][] lgrc=new char[lin*size][lin*size];
				int snow=0;                  //��Դ���
				for(int b=0;b<lin;b++){               //��Դ
					for(int c=0;c<lin;c++){             //��Դ
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
				//�ֺ�end
				for(int b=0;b<lin*size;b++){
					for(int c=0;c<lin*size;c++){
						//System.out.println(chcode[now]);
						if(code2[now]=='1'){
							//System.out.println((c+x)+" "+(gridHeight*panelHeight));
							if(c+x>=panelwidth){
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
		this.repaint();
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
					if(a+y<panelwidth&&b+x<panelHeight){
						component[a+y][b+x]=bt.getBackground();
						//System.out.println(bt.getBackground().toString());
					}
				}
			}
		}
		drawComponentString(x,y+bt.getHeight()-7*bt.getFont().getSize()-2,bt.getText(),bt.getBackground(),bt.getFont().getSize(),bt,cha.toString());
		this.repaint();
	}
	void updateComponent(){
		for(int a=0;a<panelwidth;a++){
			for(int b=0;b<panelHeight;b++){
				
			}
		}
		this.repaint();
	}
}








