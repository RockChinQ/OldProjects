package ReleaseI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Pixel2D extends JPanel implements MouseMotionListener,MouseListener{
	//����
	int gridWidth;
	int gridHeight;
	int panelWidth;
	int panelHeight;
	boolean showGridLine;
	Color gridLine;
	Color trans=Color.white;
	//ͼ������
	Color[][] picture;
	//ͼ�γ���
	StringBuffer cha=new StringBuffer(read("chars-7.txt"));
	//��ͼ
	BufferedImage bi;
	Graphics2D g;
	StringBuffer str=new StringBuffer("");
	//ǰ������ɫ
	Color basiccolor;
	Color bgcolor;
	//Ԥ����ɫ
	public final static Color[] presetColor=new Color[]{new Color(0,0,0),new Color(127,127,127),new Color(136,0,21),new Color(237,28,36),new Color(255,127,39),new Color(255,242,0),new Color(34,177,76),new Color(0,162,232),new Color(63,72,204),new Color(163,73,164)};      
	//PTextField�Ĺ��
	Timer t=new Timer();
	//Labels
	int outputc=0;
	int theComponentMouseIn=-1;
	//������һЩ����
	int TextFieldFocus=0;
	//������
	PixelMouseListener pml;
	boolean pmlH=false;
	PixelMouseMotionListener pmml;
	boolean pmmlH=false;
	//Test��Ҫ������ڷ���ǰɾ��
	class thread2{
		Thread tt=new Thread();
		thread2(){
			
		}
		void sleep(long time){
			try {
				tt.sleep(time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	thread2 t2=new thread2();
	//
	public Pixel2D(int gridWidth,int gridHeight,int panelWidth,int panelHeight,Color basiccolor,
			Color bgcolor,boolean showGridLine,Color gridLine){
		bi=new BufferedImage(gridWidth*panelWidth,gridHeight*panelHeight,BufferedImage.TYPE_3BYTE_BGR);
		g=bi.createGraphics();
		this.gridWidth=gridWidth;
		this.gridHeight=gridHeight;
		this.panelWidth=panelWidth;
		this.panelHeight=panelHeight;
		this.bgcolor=bgcolor;
		this.basiccolor=basiccolor;
		this.showGridLine=showGridLine;
		this.gridLine=gridLine;
		this.picture=new Color[panelWidth][panelHeight];
		this.setSize(gridWidth*panelWidth,gridHeight*panelHeight);
		this.setSize(gridWidth*panelWidth+1, gridHeight*panelHeight+1);
		//��ʼ��ͼ������
		this.resetPixelPanel();
		//������
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		//����
		this.requestFocus();
	}
	public void addPixelMouseListener(PixelMouseListener pml) {
		this.pml=pml;
		this.pmlH=true;
	}
	public void addPixelMouseMotionListener(PixelMouseMotionListener pmml) {
		this.pmml=pmml;
		this.pmmlH=true;
	}
	public void paint(Graphics g){
		int n=0;
		for(int a=0;a<panelWidth;a++){         //͸������
			n=a%2;
			for(int b=0;b<panelHeight;b++){
				g.setColor(n%2==0?new Color(204,204,204):Color.WHITE);
				g.fillRect(b*7, a*7,7,7);
				n+=1;
			}
		}
		for(int a=0;a<panelWidth;a++){
			for(int b=0;b<panelHeight;b++){
				if(picture[a][b]!=null){
					g.setColor(picture[a][b]);
					g.fillRect(a*gridWidth, b*gridHeight,gridWidth, gridHeight);
				}
			}
		}
		//����
		g.setColor(gridLine);
		for(int a=0;a<=panelHeight&&showGridLine;a++){
			g.drawLine(0, a*gridHeight, panelWidth*gridWidth, a*gridHeight);
		}
		for(int a=0;a<=panelWidth&&showGridLine;a++){
			g.drawLine(a*gridWidth, 0, a*gridWidth,panelHeight*gridHeight);
		}
	}
	private void println(Object s){
		System.out.println(s);
	}

	private void print(Object s){
		System.out.print(s);
	}
	public void resetPixelPanel(){

		for(int a=0;a<panelWidth;a++){
			for(int b=0;b<panelHeight;b++){
				picture[a][b]=bgcolor;
			}
		}
		this.repaint();
	}
	public int getXonPanel(int x){
		return x/this.gridWidth;
	}
	public int getYonPanel(int y){
		return y/this.gridHeight;
	}
	public String read(String s){
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
	public void setGrid(int x,int y,Color gcolor){
		if(x<picture.length&&y<picture[0].length)
			picture[x][y]=gcolor;
		this.repaint();
	}
	public String getCode(){

		StringBuffer general=new StringBuffer(panelWidth+"&"+panelHeight+"&");
		for(int a=0;a<panelWidth;a++){
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
	public void drawLine(int x,int y,int endx,int endy,Color cl){
		if(x<endx&&y<endy){         //   \
			if(endx-x!=endy-y){         //����
				if(endx-x>endy-y){        //x��
					int nowy=0;
					float dt=(float)(endx-x)/(float)(endy-y);
					for(int a=0;a<=endx-x;a++){
						setGrid(a+x,nowy+y,cl);
						if((float)a%dt<1){
							nowy++;
						}
					}
				}else{         //y��
					int nowx=0;
					float dt=(float)(endy-y)/(float)(endx-x);
					for(int a=0;a<=endy-y;a++){
						setGrid(nowx+x,a+y,cl);
						if((float)a%dt<1){
							nowx++;
						}
					}
				}
			}else{                 //����
				for(int a=0;a<=endx-x;a++){
					setGrid(x+a,y+a,cl);
				}
			}
		}else if(endx>x&&endy<y){     //  /
			if(endx-x!=y-endy){      //����
				if(endx-x>y-endy){       //x��
					int nowy=0;
					float dt=(float)(endx-x)/(float)(y-endy);
					for(int a=0;a<=endx-x;a++){
						setGrid(a+x,y-nowy,cl);
						if((float)a%dt<1){
							nowy++;
						}
					}
				}else{            //y��
					int nowx=0;
					float dt=(float)(y-endy)/(float)(endx-x);
					for(int a=0;a<=y-endy;a++){
						setGrid(nowx+x,y-a,cl);
						if((float)a%dt<1){
							nowx++;
						}
					}
				}
			}else{
				for(int a=0;a<endx-x;a++){
					setGrid(x+a,y-a,cl);
				}
			}
		}else if(x==endx){          //��ֱ���£�G  :-P)
			for(;y<=endy;y++){
				setGrid(x, y, cl);
			}
		}else if(y==endy){         //ƽֱ
			for(;x<=endx;x++){
				setGrid(x, y, cl);
			}
		}
		this.repaint();
	}
	public void drawRect(int x,int y,int width,int height,Color cl,boolean fill){
		if(x<picture.length&&y<picture[0].length){
			int sx=x,sy=y;
			int ty=y;
			for(;x<=sx+width;x++){
				for(y=ty;y<=sy+height;y++){
					if(fill&&x<panelWidth&&y<panelHeight)
						setGrid(x,y,cl);
					else if(!fill&&x<panelWidth&&y<panelHeight&&((x==sx||x==sx+width)||(y==sy||y==sy+height))){
						setGrid(x,y,cl);
					}
				}
			}
		}
		this.repaint();
	}
	public void drawByCode(int x,int y,Color cl,String code){
		if(x>=panelWidth||y>=panelHeight)
			return;
		int temp=x;
		x=y;y=temp;
		String[] code0=code.split("&");
		int w=Integer.parseInt(code0[0]),h=Integer.parseInt(code0[1]);
		int now=2;
		for(int a=0;a<w;a++){
			for(int b=0;b<h;b++){
				if(code0[now].equals("n")){
					now++;
					continue;
				}else if(code0[now].equals("cl")&&a+y<picture.length&&b+x<picture[0].length){
					setGrid(a+y,b+x,cl);
				}else if(a+y<picture.length&&b+x<picture[0].length)
					setGrid(a+y,b+x,new Color(Integer.parseInt(code0[now].split(",")[0]),Integer.parseInt(code0[now].split(",")[1]),Integer.parseInt(code0[now].split(",")[2])));
				now++;
			}
		}
		this.repaint();
	}
	public void drawPixelImage(int x,int y,Color cl,PixelImage pxlimg){
		drawByCode(x,y,cl,pxlimg.width+"&"+pxlimg.height+"&"+pxlimg.code);
	}
	public int drawChar(int x,int y,char ch,Color cl,int size,String charset){
		boolean found=false;
		if(x>=panelWidth||y>=panelHeight)
			return 0;
		String[] chars=read(charset).split("#;");
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
							if(c+x>=panelWidth){
								now++;
								continue;
							}
							setGrid(c+x,b+y,cl);
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
	public int drawString(int x,int y,String str,Color cl,int size,String charset){
		Color sour=cl;
		char[] avchar=str.toCharArray();
		int loa=0,gridc=7;
		for(int a=0;a<avchar.length;a++){
			if(avchar[a]=='\n'){
				loa=0;
				y+=gridc;
				continue;
			}else if(avchar[a]=='$'){
				switch(avchar[a+1]){
				case '0':{
					cl=presetColor[0];
					a++;
					continue;
				}
				case '1':{
					cl=presetColor[1];
					a++;
					continue;
				}
				case '2':{
					cl=presetColor[2];
					a++;
					continue;
				}
				case '3':{
					cl=presetColor[3];
					a++;
					continue;
				}
				case '4':{
					cl=presetColor[4];
					a++;
					continue;
				}
				case '5':{
					cl=presetColor[5];
					a++;
					continue;
				}
				case '6':{
					cl=presetColor[6];
					a++;
					continue;
				}
				case '7':{
					cl=presetColor[7];
					a++;
					continue;
				}
				case '8':{
					cl=presetColor[8];
					a++;
					continue;
				}
				case '9':{
					cl=presetColor[9];
					a++;
					continue;
				}
				case 'c':
				case 'C':
				{
					gridc=drawChar(x+loa,y,'$',cl,size,charset)+1;
					loa+=gridc;
					a++;
					continue;
				}
				case 'r':
				case 'R':
				{
					cl=sour;
					a++;
					continue;
				}
				default:{
					a++;
					continue;
				}
				}
			}
			gridc=drawChar(x+loa,y,avchar[a],cl,size,charset)+1;
			loa+=gridc;
		}
		this.repaint();
		return loa;
	}
	public int getDistance(int x0,int y0,int x1,int y1){
		int tx=x0,ty=y0;
		x0=Math.min(x0, x1);
		y0=Math.min(y0, y1);
		x1=Math.max(tx, x1);
		y1=Math.max(ty, y1);
		return (int) Math.round(Math.sqrt(Math.pow((double)x1-(double)x0, 2)+Math.pow((double)y1-(double)y0, 2)));
	}
	public void drawCircle(int x,int y,int radius,Color cl,boolean fill){
		for(int a=0;a<x+2*radius+1;a++){
			for(int b=0;b<y+2*radius+1;b++){
				if(x+a<panelWidth&&y+b<panelHeight){
					if((getDistance(a, b,radius, radius)==radius))
						setGrid(a+x,b+y,cl);
					if(fill&&(getDistance(a, b,radius, radius)<radius))
						setGrid(a+x,b+y,cl);
				}
			}
		}
		this.repaint();
	}
	
	//mouse
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(pmlH)
			this.pml.mouseClicked(new p2dMouseEvent(this,new Point(arg0.getX()/this.gridWidth,arg0.getY()/this.gridHeight),arg0.getClickCount(),arg0.getButton()));
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(pmlH)
			this.pml.mouseEntered(new p2dMouseEvent(this,new Point(arg0.getX()/this.gridWidth,arg0.getY()/this.gridHeight),arg0.getClickCount(),arg0.getButton()));
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(pmlH)
			this.pml.mouseExited(new p2dMouseEvent(this,new Point(arg0.getX()/this.gridWidth,arg0.getY()/this.gridHeight),arg0.getClickCount(),arg0.getButton()));
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(pmlH)
			this.pml.mousePressed(new p2dMouseEvent(this,new Point(arg0.getX()/this.gridWidth,arg0.getY()/this.gridHeight),arg0.getClickCount(),arg0.getButton()));
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(pmlH)
			this.pml.mouseReleased(new p2dMouseEvent(this,new Point(arg0.getX()/this.gridWidth,arg0.getY()/this.gridHeight),arg0.getClickCount(),arg0.getButton()));
	}
	
	//mouseMotion
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(this.pmmlH)
			this.pml.mouseDragged(new p2dMouseEvent(this,new Point(arg0.getX()/this.gridWidth,arg0.getY()/this.gridHeight),arg0.getClickCount(),arg0.getButton()));
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(this.pmmlH)
			this.pml.mouseMoved(new p2dMouseEvent(this,new Point(arg0.getX()/this.gridWidth,arg0.getY()/this.gridHeight),arg0.getClickCount(),arg0.getButton()));
	}
	
}









