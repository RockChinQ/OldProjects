package Pixel2D;

import java.awt.Color;
import java.awt.Component;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Graphics2D;
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

public class Pixel2D extends JPanel implements MouseMotionListener,MouseListener, KeyListener{
	//常规
	int gridWeight;
	int gridHeight;
	int panelWeight;
	int panelHeight;
	boolean showGridLine;
	Color gridLine;
	Color trans=Color.white;
	//图层数据
	Color[][] picture;
	//图形常量
	StringBuffer cha=new StringBuffer(read("chars-7.txt"));
	//绘图
	BufferedImage bi;
	Graphics2D g;
	StringBuffer str=new StringBuffer("");
	//控件component
	ArrayList<PixelComponent> component=new ArrayList<PixelComponent>();
	//前景背景色
	Color basiccolor;
	Color bgcolor;
	//预置颜色
	public final static Color[] presetColor=new Color[]{new Color(0,0,0),new Color(127,127,127),new Color(136,0,21),new Color(237,28,36),new Color(255,127,39),new Color(255,242,0),new Color(34,177,76),new Color(0,162,232),new Color(63,72,204),new Color(163,73,164)};      
	//PTextField的光标
	Timer t=new Timer();
	//PTextFieldTimer光标计时器
	PTextFieldTimer ptft=new PTextFieldTimer(this);
	//Labels
	int outputc=0;
	int theComponentMouseIn=-1;
	//本面板的一些焦点
	int TextFieldFocus=0;
	//Test需要，务必在发布前删除
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
	public Pixel2D(int gridWeight,int gridHeight,int panelWeight,int panelHeight,Color basiccolor,
			Color bgcolor,boolean showGridLine,Color gridLine){
		bi=new BufferedImage(gridWeight*panelWeight,gridHeight*panelHeight,BufferedImage.TYPE_3BYTE_BGR);
		g=bi.createGraphics();
		this.gridWeight=gridWeight;
		this.gridHeight=gridHeight;
		this.panelWeight=panelWeight;
		this.panelHeight=panelHeight;
		this.bgcolor=bgcolor;
		this.basiccolor=basiccolor;
		this.showGridLine=showGridLine;
		this.gridLine=gridLine;
		this.picture=new Color[panelWeight][panelHeight];
		this.setSize(gridWeight*panelWeight,gridHeight*panelHeight);
		this.setSize(gridWeight*panelWeight+1, gridHeight*panelHeight+1);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addKeyListener(this);
		//初始化图层数据
		this.resetPixelPanel();
		//PTextField的光标
		t.schedule(ptft, new Date(),550);
		//焦点
		this.requestFocus();
	}
	public void paint(Graphics g){
		int n=0;
		for(int a=0;a<panelWeight;a++){         //透明背景
			n=a%2;
			for(int b=0;b<panelHeight;b++){
				g.setColor(n%2==0?new Color(204,204,204):Color.WHITE);
				g.fillRect(b*7, a*7,7,7);
				n+=1;
			}
		}
		for(int a=0;a<panelWeight;a++){
			for(int b=0;b<panelHeight;b++){
				if(picture[a][b]!=null){
					g.setColor(picture[a][b]);
					g.fillRect(a*gridWeight, b*gridHeight,gridWeight, gridHeight);
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
	}
	private void println(Object s){
		System.out.println(s);
	}

	private void print(Object s){
		System.out.print(s);
	}
	public void resetPixelPanel(){

		for(int a=0;a<panelWeight;a++){
			for(int b=0;b<panelHeight;b++){
				picture[a][b]=bgcolor;
			}
		}
		component=new ArrayList();
		this.repaint();
	}
	public void addPixelComponent(PixelComponent pxlc){
		component.add(pxlc);
	}
	public void removePixelComponent(PixelComponent pxlc){
		component.remove(pxlc);
	}
	public void updatePixelComponent(){    //更新控件
		int csize=component.size();
		for(int a=0;a<csize;a++){
			updateOneComponent(a);
		}
	}
	public void updateOneComponent(int a){
		switch(component.get(a).getClass().toString()){
		case "class Pixel2D.PButton":{                 
			PButton tpb=(PButton)component.get(a);
			int x=0;
			this.drawRect(tpb.location.x+1, tpb.location.y+1, tpb.width, tpb.height, this.basiccolor,false);
			this.drawRect(tpb.location.x, tpb.location.y, tpb.width, tpb.height,tpb.background,true);
			if(tpb.icon!=null){
				this.drawPixelImage(tpb.location.x+x+1, tpb.location.y+1, null, tpb.icon.getIconCode(tpb.height-1));
				x+=tpb.height+1;
			}
			char[] tc=tpb.text.toCharArray();
			int tclen=tc.length;
			this.drawString(tpb.location.x+1+x, tpb.location.y+1, tpb.getDisplayableText(), tpb.fontcl, tpb.font.size, tpb.font.getCharset());
			break;
		}
		case "class Pixel2D.PLabel":{
			PLabel tpl=(PLabel)component.get(a);
			char[] tc=tpl.text.toCharArray();
			int tclen=tc.length;
			int x=1;
			if(tpl.showBorderLine){
				this.drawRect(tpl.location.x, tpl.location.y, tpl.width, tpl.height,tpl.background, false);
			}
			if(tpl.icon!=null){
				this.drawPixelImage(tpl.location.x+x, tpl.location.y+1, null, tpl.icon.getIconCode(tpl.height-1));
				x+=tpl.height+1;
			}

			this.drawString(tpl.location.x+1+x, tpl.location.y+1, tpl.getDisplayableText(), tpl.fontcl, tpl.font.size, tpl.font.getCharset());
			break;
		}
		case "class Pixel2D.PTextField":{
			PTextField tptf=(PTextField)component.get(a);
			int x=0;
			this.drawRect(tptf.location.x, tptf.location.y, tptf.width, tptf.height, Color.gray, true);
			this.drawRect(tptf.location.x, tptf.location.y, tptf.width, tptf.height, tptf.background, false);
			tptf.gridx=this.drawString(tptf.location.x+1, tptf.location.y+1, tptf.getDisplayableText(), tptf.fontcl, tptf.font.size, tptf.font.getCharset());
			//println(""+tptf.gridx);
			this.TextFieldFocus=a;
			break;
		}
		}
	}
	public void componentPressed(int a){
		//点击动画
		switch(component.get(a).getClass().toString()){
		case "class Pixel2D.PButton":{
			PButton tpb=(PButton)component.get(a);
			int x0=0;
			this.drawRect(tpb.location.x, tpb.location.y, tpb.width, tpb.height,this.basiccolor,false);
			this.drawRect(tpb.location.x+1, tpb.location.y+1, tpb.width, tpb.height, tpb.background,true);
			if(tpb.icon!=null){
				this.drawPixelImage(tpb.location.x+x0+2, tpb.location.y+2, null, tpb.icon.getIconCode(tpb.height-1));
				x0+=tpb.height+1;
			}
			char[] tc=tpb.text.toCharArray();
			int tclen=tc.length;
			this.drawString(tpb.location.x+2+x0, tpb.location.y+2, tpb.getDisplayableText(), tpb.fontcl, tpb.font.size, tpb.font.getCharset());
			break;
		}
		case "class Pixel2D.PTextField":{
			((PTextField)component.get(this.TextFieldFocus)).showing=false;
			((PTextField)component.get(this.TextFieldFocus)).showCursor(false, this);
			this.TextFieldFocus=a;
		}
		}
		//动画结束
	}
	public int getXonPanel(int x){
		return x/this.gridWeight;
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

		StringBuffer general=new StringBuffer(panelWeight+"&"+panelHeight+"&");
		for(int a=0;a<panelWeight;a++){
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
			if(endx-x!=endy-y){         //矩形
				if(endx-x>endy-y){        //x长
					int nowy=0;
					float dt=(float)(endx-x)/(float)(endy-y);
					for(int a=0;a<=endx-x;a++){
						setGrid(a+x,nowy+y,cl);
						if((float)a%dt<1){
							nowy++;
						}
					}
				}else{         //y长
					int nowx=0;
					float dt=(float)(endy-y)/(float)(endx-x);
					for(int a=0;a<=endy-y;a++){
						setGrid(nowx+x,a+y,cl);
						if((float)a%dt<1){
							nowx++;
						}
					}
				}
			}else{                 //方形
				for(int a=0;a<=endx-x;a++){
					setGrid(x+a,y+a,cl);
				}
			}
		}else if(endx>x&&endy<y){     //  /
			if(endx-x!=y-endy){      //矩形
				if(endx-x>y-endy){       //x长
					int nowy=0;
					float dt=(float)(endx-x)/(float)(y-endy);
					for(int a=0;a<=endx-x;a++){
						setGrid(a+x,y-nowy,cl);
						if((float)a%dt<1){
							nowy++;
						}
					}
				}else{            //y长
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
		}else if(x==endx){          //竖直向下（G  :-P)
			for(;y<=endy;y++){
				setGrid(x, y, cl);
			}
		}else if(y==endy){         //平直
			for(;x<=endx;x++){
				setGrid(x, y, cl);
			}
		}
		this.repaint();
	}
	public void drawRect(int x,int y,int weight,int height,Color cl,boolean fill){
		if(x<picture.length&&y<picture[0].length){
			int sx=x,sy=y;
			int ty=y;
			for(;x<=sx+weight;x++){
				for(y=ty;y<=sy+height;y++){
					if(fill&&x<panelWeight&&y<panelHeight)
						setGrid(x,y,cl);
					else if(!fill&&x<panelWeight&&y<panelHeight&&((x==sx||x==sx+weight)||(y==sy||y==sy+height))){
						setGrid(x,y,cl);
					}
				}
			}
		}
		this.repaint();
	}
	public void drawByCode(int x,int y,Color cl,String code){
		if(x>=panelWeight||y>=panelHeight)
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
		drawByCode(x,y,cl,pxlimg.weight+"&"+pxlimg.height+"&"+pxlimg.code);
	}
	public int drawChar(int x,int y,char ch,Color cl,int size,String charset){
		boolean found=false;
		if(x>=panelWeight||y>=panelHeight)
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
				if(x+a<panelWeight&&y+b<panelHeight){
					if((getDistance(a, b,radius, radius)==radius))
						setGrid(a+x,b+y,cl);
					if(fill&&(getDistance(a, b,radius, radius)<radius))
						setGrid(a+x,b+y,cl);
				}
			}
		}
		this.repaint();
	}
	
	/*鼠标监听器 describe:原计划独立一个类，但因为监听器中需要使用当前Pixel2D对象实例化的某些对象，
	 *则在此处写监听器*/
	@Override
	public void mouseDragged(MouseEvent ae) {
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		int x=this.getXonPanel(e.getX()),y=this.getYonPanel(e.getY());
		int size=component.size();
		for(int a=0;a<size;a++){
			PixelComponent tpc=component.get(a);
			if(a!=theComponentMouseIn&&x>tpc.location.x&&y>tpc.location.y&&x<tpc.width+tpc.location.x&&y<tpc.height+tpc.location.y){
					try{
						component.get(a).mouseListener.mouseEntered(new p2dMouseEvent(component.get(a)));
					}catch(Exception arg0){}
					if(theComponentMouseIn!=-1){
					try{
						component.get(theComponentMouseIn).mouseListener.mouseExited(new p2dMouseEvent(component.get(theComponentMouseIn)));
					}catch(Exception arg0){}
				}
				theComponentMouseIn=a;
			}else if(a==theComponentMouseIn){
				PixelComponent tpc2=component.get(theComponentMouseIn);
				if(x>tpc2.location.x&&y>tpc2.location.y&&x<tpc2.width+tpc2.location.x&&y<tpc2.height+tpc2.location.y){
					break;
				}else {
					try{
						component.get(theComponentMouseIn).mouseListener.mouseExited(new p2dMouseEvent(component.get(theComponentMouseIn)));
					}catch(Exception arg0){}
					theComponentMouseIn=-1;
				}
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int x=this.getXonPanel(e.getX()),y=this.getYonPanel(e.getY());
		int size=component.size();
		for(int a=0;a<size;a++){
			PixelComponent tpc=component.get(a);
			if(x>tpc.location.x&&y>tpc.location.y&&x<tpc.width+tpc.location.x&&y<tpc.height+tpc.location.y){
				tpc.mouseListener.mouseClicked(new p2dMouseEvent(tpc));
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {

		int x=this.getXonPanel(e.getX()),y=this.getYonPanel(e.getY());
		int size=component.size();
		for(int a=0;a<size;a++){
			PixelComponent tpc=component.get(a);
			if(x>tpc.location.x&&y>tpc.location.y&&x<tpc.width+tpc.location.x&&y<tpc.height+tpc.location.y){
				componentPressed(a);
				tpc.mouseListener.mousePressed(new p2dMouseEvent(tpc));
			}
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {

		int x=this.getXonPanel(e.getX()),y=this.getYonPanel(e.getY());
		int size=component.size();
		for(int a=0;a<size;a++){
			PixelComponent tpc=component.get(a);
			if(x>tpc.location.x&&y>tpc.location.y&&x<tpc.width+tpc.location.x&&y<tpc.height+tpc.location.y){
				updateOneComponent(a);
				tpc.mouseListener.mouseReleased(new p2dMouseEvent(tpc));
			}
		}
	}
	//鼠标监听器end
	//键盘监听器
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyCode()!=0){
			this.component.get(this.TextFieldFocus).text+=e.getKeyChar();
			this.updateOneComponent(TextFieldFocus);
			println(""+e.getKeyChar());
		}
	}
	
}









