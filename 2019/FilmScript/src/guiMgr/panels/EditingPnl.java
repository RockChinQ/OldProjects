package guiMgr.panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.alibaba.fastjson.JSONObject;

import boot.Main;
import data.GroupManager.group;
import guiMgr.PnlColor;
import guiMgr.panels.EditingPnl.Clip;
import guiMgr.script.ShotTable.shot;
import log.Log;

public class EditingPnl extends JPanel{
	/**
	 * 选择框
	 * @author Administrator
	 *
	 */
	public class ChooseRect extends JPanel{
		public void paint(Graphics g) {
			g.setColor(new Color(255,255,255,70));
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Color.white);
			g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
		}
	}

	public static class Clip extends JPanel{
		public String title;
		int  type;
		public int length;  //长度/帧
		public int track;
		public int x;//在多少帧
		ArrayList<Label> lbs;
		public String description="无描述";
		Image thumb;
		public boolean chosen=false;
		public int uid=0;
		public int linkId=-1;//链接uid
		public int groupId=-1;//分组uid
		
		
		int temp_length=0;
		Point temp_location=new Point();
		
		public final static int VDO_TRACK=0,ADO_TRACK=1;
		public final static int VIDEO=0,AUDIO=1,TITLE=2;
		Color VIDEO_Cl=new Color(114,154,204)
				,AUDIO_CL=new Color(45,226,123)
				,TITLE_CL=new Color(82,41,95)
				,MAIN_TRACK_CL=new Color(80,120,180);
		public void paint(Graphics g) {
			
			Color bgc;
			if(this.type==VIDEO) {
				bgc=track==0?this.MAIN_TRACK_CL:this.VIDEO_Cl;
				
			}else if(this.type==AUDIO)
				bgc=this.AUDIO_CL;
			else
				bgc=this.TITLE_CL;
			g.setColor(bgc);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			//选择标记层
			if(chosen) {
				g.setColor(new Color(235,235,235,140));
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
			}
			g.setColor(Color.BLACK);
			g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
			g.setFont(text);
			int deltaX=0;
			//计算该元素是否完全在界面内以确定标题绘制位置
			//验证此元素的开头是否小于tmlnStart且现在是时间线视图
			if(this.x<tmlnStart&&Main.gui.bgp.ep.viewMode==Main.gui.bgp.ep.TIMELINE_VIEW) {
				//System.out.println(title+" "+this.x+" "+tmlnStart);
				deltaX=(int) ((tmlnStart-this.x)*zoom);
			}
			g.drawString(title, linkId==-1?10+deltaX:28+deltaX,Main.gui.bgp.ep.eleHeight/2+4);
			if(linkId!=-1) {
				g.setColor(new Color(170,170,170));
				((Graphics2D)g).setStroke(new BasicStroke(2));
				int spx=5+deltaX,spy=Main.gui.bgp.ep.eleHeight/2-5;
				g.drawLine(spx, spy, spx+8, spy+8);
				g.drawLine(spx, spy, spx+5, spy-5);
				g.drawLine(spx+5, spy-5, spx+13, spy+3);
				
				g.drawLine(spx+1, spy+6, spx+1+8, spy+6+8);
				g.drawLine(spx+1+8, spy+6+8, spx+1+13, spy+6+3);
				g.drawLine(spx+14, spy+9, spx+6, spy+1);
				
				g.setColor(new Color(19,182,251));
				g.setFont(tiny_italic);
				g.drawString(linkId+"", spx+8, spy+2);
			}
			if(this.getWidth()>=120&&((this.x+this.length)*zoom-tmlnStart*zoom)>=120) {
				g.setColor(Color.white);
				g.setFont(tiny_plain);
				Main.gui.bgp.ep.tmln.td.set(this.length);
				g.drawString("["+Main.gui.bgp.ep.tmln.td.hour+":"
				+Main.gui.bgp.ep.tmln.td.minu+":"+Main.gui.bgp.ep.tmln.td.seco
				+":"+Main.gui.bgp.ep.tmln.td.fram+"]",this.getWidth()-60, 20);
			}
		}
		public int trackType() {
			if(this.type==Clip.AUDIO)
				return Clip.ADO_TRACK;
			return Clip.VDO_TRACK;
		}
		public Clip() {
		}
		

		/**
		 * 将一个Clip对象打包为json
		 * @param clp
		 * @return
		 */
		public JSONObject getClipJSON() {
			JSONObject clpj=new JSONObject();
			/**
			 * title str
			 * type int
			 * length int
			 * track int
			 * x int
			 * descr str
			 * chosen bool
			 * uid int
			 * linkId int
			 * groupId int
			 * lbs Arr
			 */
			clpj.put("title", title);
			clpj.put("type", type);
			clpj.put("length", length);
			clpj.put("track", track);
			clpj.put("x", x);
			clpj.put("description", this.description);
			clpj.put("chosen", chosen);
			clpj.put("uid",uid);
			clpj.put("linkId", linkId);
			clpj.put("groupId", groupId);
			clpj.put("labels", null);
			
			return clpj;
		}
		/**
		 * 从json生成clip对象
		 */
		public Clip(JSONObject json) {
			this.title=json.getString("title");
			this.type=json.getIntValue("type");
			this.length=json.getIntValue("length");
			this.track=json.getIntValue("track");
			this.x=json.getIntValue("x");
			this.description=json.getString("description");
			this.chosen=json.getBooleanValue("chosen");
			this.uid=json.getIntValue("uid");
			this.linkId=json.getIntValue("linkId");
			this.groupId=json.getIntValue("groupId");
			
		}
		
	}
	public static class Label{
		int x;
		int clptr=-1;
		Color cl=new Color(255,104,4);
		String text;
		int uid=0;
		public JSONObject getJSON() {
			JSONObject json=new JSONObject();
			json.put("x", x);
			json.put("clptr", clptr);
			json.put("cl", cl.getRGB());
			json.put("text", text);
			json.put("uid", uid);
			return json;
		}
		public Label() {
			
		}
		public Label(JSONObject json) {
			this.x=json.getIntValue("x");
			this.clptr=json.getIntValue("clptr");
			this.cl=new Color(json.getIntValue("cl"));
			this.text=json.getString("text");
			this.uid=json.getIntValue("uid");
		}
	}
	//关于各种视图的
	public static final int TIMELINE_VIEW=0,GROUP_VIEW=1;
	public int viewMode=TIMELINE_VIEW;//0时间线视图 1分组视图
	
	public static final Color default_labelCl=new Color(242,97,0);
	
	//组视图
	public static final int addXReset=80,addYReset=110;
	public int addX=addXReset,addY=addYReset;
	public static final int GROUP_VIEW_ELE_HEIGHT=35;
	public static final int ELE_SPACE = 7;
	
	static Font text=new Font("",Font.BOLD,15);
	
	static Font tiny_italic=new Font("",Font.BOLD,12);
	static Font tiny_plain=new Font("",Font.PLAIN,10);
	
	public static double zoom=2;//像素/帧
	static int tmlnStart=0;
	public int eleHeight=20;
	public int centreLineDeltaY=0,add=0;   //向下偏移量
	int maxX=0;//项目长度
	public int maxVTrack=0;//最高轨道
	public int maxATrack=0;
	int maxLabelX=0;//最远标记
	boolean changing=false;
	public int playNow=150;
	public ArrayList<Clip> elms=new ArrayList<Clip>();
	public ArrayList<Label> projLbs=new ArrayList<Label>();
	TimeLine tmln;  //timeline
	public int idIndex=0;
	public int labelIndex=0;
	
	//最先显示的素材标签，用于移动时显示标记
	//Clip disClp=new Clip();
	
	
	
	
	/**
	 * 时间指示柱
	 * @author Administrator
	 *
	 */
	public class PlayNow extends JPanel{
		public PlayNow() {
			//this.setBackground(new Color(0,0,0,255));
		}
		public void resize() {
			//this.setSize(7,(maxVTrack+2)*(eleHeight+5)-(5)+tmln.getHeight()+(maxATrack+2)*(eleHeight+5)-(5));
			this.setSize(7,(maxVTrack+1)*(eleHeight+5)+tmln.getHeight()+(maxATrack+1)*(eleHeight+5)+40);
			//this.setLocation((int)((playNow-tmlnStart)*zoom)-3,tmln.getY()-(maxVTrack+2)*(eleHeight+5)+(5));
			this.setLocation((int)((playNow-tmlnStart)*zoom)-3,tmln.getY()-((maxVTrack+1)*(eleHeight+5))-20);
		}
		public void paint(Graphics g) {
			//飞棱柱
			if(playNow>=tmlnStart&&playNow<=(int)(tmln.getWidth()/zoom)+tmlnStart){
				g.setColor(new Color(39,102,248));
				g.drawLine(3, 0, 3, this.getHeight());
				g.fillRect(0, 0, 7,6);
				g.drawLine(0, 6, 3, 10);
				g.drawLine(6, 6, 3, 10);
				g.drawLine(0,6,6,6);
				g.drawLine(1, 7, 5, 7);
				g.drawLine(2, 8, 4, 8);
				
				g.fillRect(0, this.getHeight()-6, 7, 6);
				g.drawLine(0, this.getHeight()-6, 3, this.getHeight()-11);
				g.drawLine(6, this.getHeight()-6, 3, this.getHeight()-11);
				g.drawLine(1, this.getHeight()-7, 6, this.getHeight()-7);
				g.drawLine(2, this.getHeight()-8, 5, this.getHeight()-8);
				
				//g.fillRect(0, this.getHeight()-(maxVTrack+2)*(eleHeight+5)+(15), this.getWidth(), 10);
				//Main.gui.bgp.repaint();
			}
		}
	}
	/**
	 * 标记线
	 * @author Administrator
	 *
	 */
	public class CutLine extends JPanel{
		public int mode=0;
		public static final int ADDMODE=0,CUTMODE=1;
		public CutLine() {
		}
		public void resize() {
			this.setSize(1,eleHeight);
		}
		public void paint(Graphics g) {
			
			g.setColor(mode==CUTMODE?Color.black:Color.white);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
		
	}
	public class SceneLabel extends JPanel{
		
		public void paint(Graphics g) {
			g.setColor(getBackground());
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			int maxf=getMaxFrameInWindow();
			for(Clip clp:elms) {
				if(clp.trackType()==Clip.ADO_TRACK||clp.track!=0)
					continue;
				if( (clp.x<tmlnStart&&clp.x+clp.length<tmlnStart) || (clp.x>maxf&&clp.x+clp.length>maxf) )
					continue;
				g.setColor(Main.gm.getGroup(clp.groupId).cl);
				g.fillRect(getPixelByFrame(clp.x), 0, (int) (clp.length*zoom), 5);
				
			}
		}
	}
	PlayNow pn=new PlayNow();
	ChooseRect cr=new ChooseRect();
	CutLine cl=new CutLine();
	SceneLabel sl=new SceneLabel();
	public GroupTmln gt=new GroupTmln();
	
	Point pressPoint=new Point(0,0);
	epMouse epm;
	epKey epk;
	
	eleMenu emn;
	/**
	 * 构造函数
	 */
	public EditingPnl() {
		centreLineDeltaY=this.getHeight()-150-this.getHeight()/2+add;
		this.setLayout(null);
		this.add(pn);
		/*
		to.setSize(100, 140);
		to.setLocation(10, 10);
		this.add(to);
		*/
		epm=new epMouse(this);
		epk=new epKey(this);
		emn=new eleMenu();
		this.add(emn);
		
		
		cl.setLocation(-5, 0);
		this.add(cl);
		
		cr.setSize(0, 0);
		cr.setLocation(0, 0);
		cr.setVisible(true);
		this.add(cr);
		
		tmln=new TimeLine();
		tmln.setSize(this.getWidth(),50);
		tmln.setLocation(0,this.getHeight()/2-tmln.getHeight()/2+centreLineDeltaY);
		tmln.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				playNow=(int) ((e.getX()/zoom)+tmlnStart);
				playNow=playNow<0?0:playNow;
				pn.resize();
				//pn.repaint();
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				Main.gui.bgp.ep.setFocusable(true);
				Main.gui.bgp.ep.requestFocus();
			}
			public void mouseReleased(MouseEvent e) {
				Main.gui.mwd.repaint();
			}
			
		});
		tmln.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent arg0) {
				playNow=(int) Math.round((arg0.getX()/zoom)+tmlnStart);
				playNow=playNow<0?0:playNow;
				pn.resize();
				//pn.repaint();
				Main.gui.mwd.repaint();
			}
			public void mouseMoved(MouseEvent arg0) {}
		});
		this.add(tmln);
		
		sl.setLocation(0, tmln.getY()-5);
		sl.setSize(this.getWidth(), 5);
		this.add(sl);
		
		this.addMouseWheelListener(epm);
		this.addMouseListener(epm);
		this.addMouseMotionListener(epm);
		
		this.addKeyListener(epk);
		try {
			add=Integer.parseInt(Main.cfg.get("trackLocationAdd").toString());
			eleHeight=Integer.parseInt(Main.cfg.get("eleHeight").toString());
			addX+=Integer.parseInt(Main.cfg.get("groupview_addX").toString());
			addY+=Integer.parseInt(Main.cfg.get("groupview_addY").toString());
			//gt.addX+=Integer.parseInt(Main.cfg.get("groupview_addX").toString());
			//gt.addY+=Integer.parseInt(Main.cfg.get("groupview_addY").toString());
		}catch(Exception e) {
			Log.record("Read cfg failed.");
		}
		this.setVisible(true);
		this.setFocusable(true);
		
	}
	/**
	 * 设置缩放值
	 * @param zoom
	 */
	public void setZoom(int zoom) {
		this.zoom=zoom;
	}
	/**
	 * 设置起始帧
	 * @param start
	 */
	public void setStart(int start) {
		this.tmlnStart=start;
	}
	public Clip getClipByUID(int uid) {
		for(Clip cp:elms) {
			if(cp.uid==uid) {
				return cp;
			}
		}
		return null;
	}
	/**
	 * 计算所选片段中最小的x
	 */
	public void calcMinXOfSelectedClip() {
		Main.cfd.minx=2147483647;
		for(Clip cp:elms) {
			if(cp.chosen&&cp.trackType()==Clip.VDO_TRACK) {
				Main.cfd.minx=Main.cfd.minx<cp.x?Main.cfd.minx:cp.x;
			}
		}
	}
	/**
	 * 返回是否有可复制片段
	 * @return
	 */
	public boolean canCopy() {
		boolean can=false;
		for(Clip cp:elms) {
			if(cp.chosen&&cp.trackType()==Clip.VDO_TRACK) {
				can=true;
				break;
			}
		}
		return can;
	}
	/**
	 * copy
	 */
	public void copy() {
		if(!canCopy())
			return;
		Log.record("copying...");
		this.calcMinXOfSelectedClip();
		//清空并向其添加复制的clip
		Main.cfd.clipBoard=new ArrayList<JSONObject>();
		for(Clip cp:Main.gui.bgp.ep.elms) {
			if(cp.chosen&&cp.trackType()==Clip.VDO_TRACK) {
				Main.cfd.clipBoard.add(cp.getClipJSON());
				Main.cfd.shotcb.add(Main.gui.sbp.sb.getShotByClipUID(cp.uid).getJSONObject());
			}
		}
	}
	/**
	 * 剪切
	 */
	public void cut() {
		if(!canCopy())
			return;
		Log.record("cutting...");
		this.calcMinXOfSelectedClip();
		//清空并向其添加复制的clip
		Main.cfd.clipBoard=new ArrayList<JSONObject>();
		Main.cfd.shotcb=new ArrayList<JSONObject>();
		ArrayList<Clip> rem=new ArrayList<Clip>();
		for(Clip cp:Main.gui.bgp.ep.elms) {
			if(cp.chosen&&cp.trackType()==Clip.VDO_TRACK) {
				Main.cfd.clipBoard.add(cp.getClipJSON());
				Main.cfd.shotcb.add(Main.gui.sbp.sb.getShotByClipUID(cp.uid).getJSONObject());
				rem.add(cp);
			}
		}
		for(Clip cp:rem) {
			//删除原选中的
			Main.gui.sbp.sb.removeShot(Main.gui.sbp.sb.getShotByClipUID(cp.uid).uid, cp);
		}
		Main.gui.bgp.ep.updateElement();
	}
	/**
	 * 粘贴
	 * @param x
	 */
	public void paste(int x) {
		Log.record("Paste to x:"+x);
		for(int i=0;i<Main.cfd.clipBoard.size();i++) {
			Clip tc=new Clip(Main.cfd.clipBoard.get(i));
			tc.x=tc.x-Main.cfd.minx+x;
			//tc.track=0;
			tc.uid=++idIndex;//设置唯一id，0作为保留id不予设置 
			tc.addMouseListener(em);
			tc.addMouseMotionListener(em);
			maxX=maxX<tc.x+tc.length?tc.x+tc.length:maxX;
			shot ts=Main.gui.sbp.sb.addShot(tc, this.getLastClipScene(tc.x), true);
			ts.loadJSONData(Main.cfd.shotcb.get(i));
			ts.vdo=tc;
			this.elms.add(tc);
			this.solveAllConflict(tc);
			//System.out.println(tc.x);
		}
		this.updateElement();
		Main.gui.sbp.sb.recalcSortID();
	}
	/**
	 * 更新界面，将会删除所有控件并重新添加
	 */
	public void updateElement() {
//		StackTraceElement[] ste=Thread.currentThread().getStackTrace();
//		StackTraceElement a=(StackTraceElement)ste[2];
//		
//		System.out.println("来自 "+a.getClassName()+"."+a.getMethodName()+"."+a.getLineNumber());
		
		Log.record("Update Elements.");
		/*
		StackTraceElement[] ste=Thread.currentThread().getStackTrace();
		StackTraceElement a=(StackTraceElement)ste[2];
		System.out.println("update elements from["+a.getClassName()+"."+
					a.getMethodName()+"()."+a.getLineNumber()+"]");
		*/
		
		centreLineDeltaY=this.getHeight()-150-this.getHeight()/2+add;
		this.removeAll();
		System.gc();//清理
		if(this.viewMode==this.TIMELINE_VIEW) {
			Main.gui.sbp.sb.updateAvailable();
			Main.gui.sbp.sb.recalcSortID();
			Main.gui.sbp.sb.repaint();
			//zoom=((int)(zoom*10000))/10000;
			//tmlnStart=((int)(tmlnStart*10000))/10000;
			//添加trackoper panel
			
			
			
			cl.setLocation(-5, 0);
			this.add(cl);
			
			this.add(emn);
			
			cr.setSize(0, 0);
			cr.setLocation(0, 0);
			cr.setVisible(true);
			//cr.setBackground(Color.white);
			
			tmln.setSize(this.getWidth(),50);
			tmln.setLocation(0,this.getHeight()/2-tmln.getHeight()/2+centreLineDeltaY);
			tmln.repaint();
			/*	
			this.setSize(4,tmln.getY()+tmln.getHeight()+(maxATrack+1)*(eleHeight+5)-(maxVTrack+1)*(eleHeight+5));
			this.setLocation((int)((playNow-tmlnStart)*zoom)-2,tmln.getY()+tmln.getHeight()/2-(maxVTrack+1)*(eleHeight+5));
			 */
			pn.resize();
			pn.repaint();
			this.add(pn);
			this.add(cr);
			
			sl.setLocation(0, tmln.getY()-5);
			sl.setSize(this.getWidth(), 5);
			sl.setBackground(getBackground());
			this.add(sl);
			//重绘所有clip
			//所有clip之前添加displayclip
			//this.add(disClp);
			for(int i=this.elms.size()-1;i>=0;i--) {
				/*
			 	* 添加clip的部分
			 	* 注意，现在的这种写法，当clip多时必然会导致卡顿
			 	* 因为现在是挨个重新添加元素
			 	* 
			 	* */
				Clip tc=elms.get(i);
				tc.setSize((int) (tc.length*zoom),eleHeight);
				tc.repaint();
				int x=(int) ((tc.x-tmlnStart)*zoom),y=0;
				if(tc.type==Clip.AUDIO)    //音频置于时间线下方
					y=tc.track*(eleHeight+5)+this.getHeight()/2+30;
				else
					y=this.getHeight()/2-30-tc.track*(eleHeight+5)-eleHeight;
				tc.setLocation(x, y+centreLineDeltaY);
				//tc.uid=i;
				//tc.addMouseListener(new eleMouse());
				this.add(tc);
			}
			//绘制时间线
			this.add(tmln);
			//this.repaint();
			Main.gui.bgp.repaint();
			this.setVisible(true);
		}else if(this.viewMode==this.GROUP_VIEW) {
			
			cr.setSize(0, 0);
			cr.setLocation(0, 0);
			cr.setVisible(true);
			//cr.setBackground(Color.white);
			this.add(cr);
			Main.gui.bgp.ep.gt.reset();
			Main.gm.resetMXWidthHeight();
//			//计算逻辑时间轴长度（pixel）
//				//向gt添加每个组
//			for(group gp:Main.gm.groups) {
//				gt.addGroupLabel(gp, gp.startX);
//			}
				//现在设置每个素材控件长度并计算每个组maxWidth
			for(Clip clp:elms) {
				clp.setSize(getLogicalWidth(clp.length),this.GROUP_VIEW_ELE_HEIGHT);//暂定组视图的素材为定高
				
				Main.gm.setMXWidth(clp.groupId, clp.getWidth());
				Main.gm.addMXHeight(clp.groupId, this.GROUP_VIEW_ELE_HEIGHT+this.ELE_SPACE);
				//clp.setLocation(gt.addX, y);
				
			}
				//向GroupTmln设置面板长度
			int w=0,h=0;//记录了gt的面板大小（不含边框
			int gpaddx=0;//记录了每个组的起始帧数(不含边框
			for(group gp:Main.gm.groups) {
				gt.addGroupLabel(gp, (int) (gpaddx/zoom));
				
				int y=0;
				for(Clip clp:elms) {
					//System.out.println(clp.title);
					try {
					if(clp.groupId==gp.uid) {
						//System.out.println(clp.title+" "+((int) (gpaddx*zoom)+this.addX)+" "+( y*this.GROUP_VIEW_ELE_HEIGHT+5+this.addY));
						clp.setLocation((int) (gpaddx)+this.addX
								, y*(this.GROUP_VIEW_ELE_HEIGHT+this.ELE_SPACE)+this.addY);
						y++;
						this.add(clp);
					}
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
				gpaddx+=gp.maxWidth+gt.groupSpace;
				w+=gp.maxWidth+gt.groupSpace;
				h=h<gp.maxHeight?gp.maxHeight:h;
				
			}
			//System.out.println(w+" "+h);
			gt.setSizex(w, h);
			gt.setLocation(this.addX-this.addXReset,this.addY-this.addXReset-this.GROUP_VIEW_ELE_HEIGHT);
			//System.out.println(gt.addX+" "+gt.addY);
			//System.out.println();
			//System.out.println(w+" "+h);
			this.add(gt);
			
			//Main.gui.bgp.ae.updateCom();
			Main.gui.bgp.repaint();
			this.setVisible(true);
		}

		this.setVisible(true);
	}
	/*
	 * 计算素材的width
	 * @param fram
	 * 还需根据zoom
	 */
	public int getLogicalWidth(int fram) {//计算素材的width
		double y=Math.pow(fram, 7.0/15.0);//计算函数值
		return (int) (zoom*y);
	}
	/**
	 * 设置各素材的层
	 * 素材多了可能会卡
	 */
	public void updateEleLayer() {
		if(this.viewMode==this.TIMELINE_VIEW) {
			Log.record("Update Elements Layer.");
			//移除所有未选择的ele
			for(int i=0;i<this.elms.size();i++) {
				if(!this.elms.get(i).chosen) {
					//System.out.println(i);
					this.remove(this.elms.get(i));
				}
			}
			//把未选择的添加到已选择之下
			for(int i=0;i<this.elms.size();i++) {
				if(!this.elms.get(i).chosen) {
					this.add(this.elms.get(i));
				}
			}
		}
		//repaint
		Main.gui.bgp.repaint();
	}
	public eleMouse em=new eleMouse();
	/**
	 * 添加元素
	 * @param title
	 * @param type
	 * @param length
	 * @param track
	 * @param x
	 * @param shl 已创建的shot对象
	 */
	public Clip addElement(String title,int type,int length,int track,int x,shot shl) {
//		Log.record("outdated method please call addScene");
//		Main.showTip("程序调用了过时方法", Main.ERROR);
		
		Clip tc=new Clip();
		tc.title=title;
		tc.type=type;
		tc.length=length;
		tc.track=track;
		tc.x=x;
		tc.uid=++idIndex;//设置唯一id，0作为保留id不予设置 
		tc.addMouseListener(em);
		tc.addMouseMotionListener(em);
		maxX=maxX<tc.x+tc.length?tc.x+tc.length:maxX;
		Log.record("Add elements.");
		if(type==Clip.VIDEO||type==Clip.TITLE) {  //检查视频轨
			this.maxVTrack=maxVTrack>track?maxVTrack:track;
		}else if(type==Clip.AUDIO) {
			this.maxATrack=maxATrack>track?maxATrack:track;
		}
		
		//添加镜头关系
		if(type==Clip.VIDEO) {
			if(shl==null) {
				Main.gui.sbp.sb.addShot(tc, this.getLastClipScene(x), track==0?true:false);
				
			}else {
				;//这里什么都不做，因为只会在shottable新增时有shl对象地调用此方法，而clip对象是被此方法返回后被shottable设为新增的片段的。
				
				//System.out.println(shl.sceneid);
				tc.groupId=Main.gui.sbp.st.getScene(shl.sceneid).gp.uid;
			}
		}
		this.elms.add(tc);
		this.solveAllConflict(tc);
		return tc;
	}
	/**
	 * 查找位于x前一段片段所属的场景
	 * @param x
	 * @return
	 */
	public int getLastClipScene(int x) {
		Main.gui.sbp.sb.sort();
		int lsx=0;
		for(shot sh:Main.gui.sbp.sb.shot) {
			if(sh.available) {
				if(sh.vdo.x>x&&lsx<=x) {
					return sh.sceneid;
				}
				lsx=sh.vdo.x;
			}
		}
		return -1;
	}
	public void addLabel(int x,String text,Color cl) {
		boolean removed=false;
		for(int i=0;i<this.projLbs.size();i++) {
			if(Math.abs(this.projLbs.get(i).x-x)<1) {
				this.projLbs.remove(i);
				if(this.projLbs.size()>=4) {
					if(Main.gui.bgp.lc.labelStart>this.projLbs.size()-4) {
						Main.gui.bgp.lc.labelStart=this.projLbs.size()-4;
					}
				}
//				Main.gui.bgp.lc.labelStart=
//						(Main.gui.bgp.lc.labelStart+4>
//						this.projLbs.size()?
//						(this.projLbs.size()-4)
//						:Main.gui.bgp.lc.labelStart);
				removed=true;
				break;
			}
		}
		if(!removed) {
			Label tl=new Label();
			tl.x=x;
			tl.text=text;
			tl.cl=cl==null?EditingPnl.default_labelCl:cl;
			tl.uid=(++this.labelIndex);
			this.projLbs.add(tl);
		}
		Main.gui.bgp.lc.updateLabel();
	}
	/**
	 * 获取页面内最大帧率
	 * @return 
	 */
	public int getMaxFrameInWindow() {
		return this.tmlnStart+EditingPnl.getFrameByPixel(this.getWidth());
	}
	/**
	 * 若playnow不在窗口内则设置tmlnStart的位置适合
	 */
	public void isPlayNowInPanel() {
		if(!(playNow>tmlnStart&&playNow<(int)(tmln.getWidth()/zoom)+tmlnStart)) {
			//System.out.println(1111);
			int halfScrFrame=(int) (this.getWidth()/zoom/2);
			//System.out.println(tmlnStart-halfScrFrame);
			this.tmlnStart=playNow-halfScrFrame>0?playNow-halfScrFrame:0;
		}
	}
	//2020.3.19需要重写
	public int whichTrack(int yOnScreen) {
		/*
		centreLineDeltaY=this.getHeight()-150-this.getHeight()/2+add;
		int y=yOnScreen-tmln.getY()-tmln.getHeight()/2-this.getY()-Main.gui.bgp.getY()-Main.gui.mwd.getY();
		if(yOnScreen>tmln.getY()+tmln.getHeight()/2+this.getY()+Main.gui.bgp.getY()+Main.gui.mwd.getY()) {//音频轨
			//y=tc.track*(eleHeight+5)+this.getHeight()/2+30
			return (int)((y-30-this.getHeight()/2-centreLineDeltaY)/(eleHeight+5));//公式应该没问题
		}
		//y=this.getHeight()/2-30-tc.track*(eleHeight+5)-eleHeight;
		return (int)((y+eleHeight-this.getHeight()/2+30+5)/(-1*(eleHeight+5)))-5;//绝对是本项目最复杂的算法
		*/
		//轨道间隔 5
		centreLineDeltaY=this.getHeight()-150-this.getHeight()/2+add;//似乎是更新设置   3 19重写时已忘记半年前的定义,但还是加上，因为此语句看似与此方法的运算无关
		int yOnEtp=yOnScreen-Main.gui.mwd.getY()-Main.gui.bgp.getY()-this.getY();//editingpnl上的y
		//System.out.println(this.getHeight()/2-15+centreLineDeltaY+"------"+yOnEtp+"------"+yOnScreen+"-----");
		if(yOnEtp<(this.getHeight()/2+5+centreLineDeltaY)) {//video
			//System.out.println("video");
			int delta=(this.getHeight()/2+5+centreLineDeltaY)-yOnEtp+20;//高度
			return (delta/(eleHeight+5));
		}else if(yOnEtp>(this.getHeight()/2+centreLineDeltaY+tmln.getHeight())) {//audio
			//System.out.println("audio");
			int delta=yOnEtp-(this.getHeight()/2+centreLineDeltaY+tmln.getHeight())+20;
			return (delta/(eleHeight+5)-1);
		}
		return -1;
	}
	public int trackType(int yOnScreen) {
		int yOnEtp=yOnScreen-Main.gui.mwd.getY()-Main.gui.bgp.getY()-this.getY();
		if(yOnEtp<(this.getHeight()/2+5+centreLineDeltaY)) {//video
			return Clip.VDO_TRACK;
		}else if(yOnEtp>(this.getHeight()/2+centreLineDeltaY+tmln.getHeight())) {//audio
			return Clip.ADO_TRACK;
		}
		return -1;
	}
	/**
	 * 解决素材冲突
	 * @param obj
	 */
	public void solveAllConflict(Clip obj) {   //解决所有同轨道上素材覆盖冲突
		Log.record("solvingConflict obj:"+obj.title);
		ArrayList<Clip> removeEle=new ArrayList<Clip>();
		for(int i=0;i<elms.size();i++) {
			//如果此素材与当前遍历素材在同一轨道
			if(!obj.equals(elms.get(i))&&!elms.get(i).chosen&&elms.get(i).trackType()==obj.trackType()
					&&elms.get(i).track==obj.track) {
				//如果重合
				if(obj.x>elms.get(i).x&&obj.x+obj.length<elms.get(i).x+elms.get(i).length) {//放在了中间
					int lengthBefore=elms.get(i).length;
					Log.record("Insert "+obj.title+" to "+elms.get(i).title+" at "+obj.x+"("+(elms.get(i).x)+")");
					int dx=cutClipApart(elms.get(i),obj.x-elms.get(i).x);
					elms.get(dx).x=obj.x+obj.length;
					elms.get(dx).length=lengthBefore-elms.get(i).length-obj.length;
					//System.out.println(elms.get(i).title);
				}else if(obj.x<=elms.get(i).x&&obj.x+obj.length>=elms.get(i).x+elms.get(i).length){//全覆盖和完全重合
					removeEle.add(elms.get(i));
				}else if(obj.x+obj.length>elms.get(i).x&&elms.get(i).x>obj.x) {//后端重叠
					elms.get(i).length-=obj.x+obj.length-elms.get(i).x;
					elms.get(i).x=obj.x+obj.length;
					//System.out.println(elms.get(i).title);
					//System.out.println("1 solved bcsOf:"+elms.get(i).title);
				}else if(obj.x<elms.get(i).x+elms.get(i).length&&obj.x>elms.get(i).x) {//前端重叠
					//System.out.println(obj.x+" "+obj.length+" "+elms.get(i).x+" "+elms.get(i).length);
					
					elms.get(i).length=obj.x-elms.get(i).x;
					//System.out.println("2 solved bcsOf:"+elms.get(i).title);
				}else if(obj.x==elms.get(i).x&&obj.x+obj.length<elms.get(i).x+elms.get(i).length) {//起点重合且前段覆盖

					//System.out.println(elms.get(i).title);
					elms.get(i).length-=obj.length;
					elms.get(i).x=obj.x+obj.length;
				}else if(obj.x>elms.get(i).x&&obj.x+obj.length==elms.get(i).x+elms.get(i).length) {//终点重合且后段覆盖
					elms.get(i).length-=obj.length;
				}
			}
		}
		for(int i=0;i<removeEle.size();i++) {
			Main.gui.sbp.sb.removeShot( removeEle.get(i));
			elms.remove(removeEle.get(i));
			Log.record("Remove Ele:"+removeEle.get(i).title+" elms.size()="+elms.size());
		}
	}
	/**
	 * 剪开素材，这是与镜头同步的
	 * @param obj
	 * @param dFromObjStart 相对于obj开始的x
	 *//*Love you,Ashley.*/
	public int cutClipApart(Clip obj,int dFromObjStart) {
		Clip temp=new Clip();
		temp.title=obj.title+"-";
		temp.type=obj.type;
		temp.track=obj.track;
		temp.description=obj.description;
		//temp.thumb=;
		temp.x=obj.x+dFromObjStart;
		temp.length=obj.length-dFromObjStart;
		if(dFromObjStart<=0||temp.length<=0) {
			return -1;
		}
		obj.length=dFromObjStart;
		Clip cp=this.addElement(temp.title, temp.type, temp.length, temp.track, temp.x,null);
//		//现在新增镜头对象
//		Main.gui.sbp.sb.addShot(cp,Main.gui.sbp.sb.getShotByClipUID(obj.uid).sceneid
//				, obj.track==0&&obj.type==Clip.VIDEO?true:false);
		Log.record("Cut "+obj.title+" at "+dFromObjStart);
		
		return this.elms.size()-1;

	}
	
	
	 public static int getPixelByFrame(int frame) { 
		 return (int) (zoom*(frame-tmlnStart));
	}
	public static int getFrameByPixel(int pixel) {
		return (int) Math.round( (pixel/zoom)+tmlnStart);
	}
	public void unselectAllClip() {
		for(Clip clp:elms) {
			if(clp.chosen)
				clp.chosen=false;
		}
	}
	/**
	 * 波纹修剪，对音频和视频同时生效
	 * @param addx
	 * @param startClipX
	 * @param track
	 */
	public void waveMove(int addx,int startClipX,int track) {
		for(Clip clp:elms) {
			if(clp.x>=startClipX&&(clp.track==track||track==-1)) {
				clp.x+=addx;
			}
		}
		this.updateElement();
	}
	/**
	 * 根据uid返回片段
	 * @param uid
	 * @return
	 */
	public int indexClipByUID(int uid) {
		for(int i=0;i<Main.gui.bgp.ep.elms.size();i++) {
			if(elms.get(i).uid==uid) {
				//System.out.println("index:"+i);
				return i;
			}
		}
		return -1;
	}
	/**
	 * 就算maxx
	 */
	public void updateMaxX() {
		for(Clip clp:elms) {
			maxX=maxX<clp.x+clp.length?clp.x+clp.length:maxX;
		}
	}
	public int getMaxX() {
		return this.maxX;
	}
}




