package guiMgr.script;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.alibaba.fastjson.JSONObject;

import boot.Main;
import data.IconDataBase;
import guiMgr.FixedPanel;
import guiMgr.PnlColor;
import guiMgr.fields.Icon;
import guiMgr.panels.EditingPnl;
import guiMgr.panels.EditingPnl.Clip;
import log.Log;

public class ShotTable extends FixedPanel implements MouseListener {

	/**
	 * 镜号		数字不可修改
	 * 长度		数字double(s)
	 * 景别		下拉菜单
	 * 属于场景	下拉菜单
	 * 人物		文本框加下拉菜单
	 * 机位		文本框加下拉菜单
	 * 运动		文本框加下拉菜单
	 * 音效		文本框加下拉菜单
	 * 内容		文本区
	 */
	static final Color nor=new Color(140,140,140),cho=new Color(0,140,240);
	public class shot extends JPanel implements MouseListener{
		class border extends JPanel{
			public void paint(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				g.setColor(chosen?cho:nor);
				g.fillRect(0, 0, 4, this.getHeight()-1);
			}
		}
		public JSONObject getJSONObject() {
			JSONObject json=new JSONObject();
			
			json.put("type", shotType);
			json.put("scene", sceneid);
			json.put("seat", seat);
			json.put("motion", motion);
			json.put("audio", audio);
			json.put("note", note);
			json.put("uid", uid);
			json.put("sortid",sortid);
			json.put("chosen", chosen);
			json.put("available", available);
			json.put("vdoid", vdo.uid);
			
			return json;
		}
		public void loadJSONData(JSONObject json) {
			this.shotType=json.getIntValue("type");
			this.sceneid=json.getIntValue("scene");
			this.seat=json.getIntValue("seat");
			this.motion=json.getString("motion");
			this.audio=json.getString("audio");
			this.note=json.getString("note");
			this.uid=json.getIntValue("uid");
			this.sortid=json.getIntValue("sortid");
			this.chosen=json.getBooleanValue("chosen");
			this.available=json.getBooleanValue("available");
			this.vdo=Main.gui.bgp.ep.getClipByUID(json.getIntValue("vdoid"));
		}
		public static final int EXTRE_CLOSE=0,CLOSE=1,MEDIUM=2,PANORAMA=3,PROSPECT=4;//景别
		//int shotid=0;
		int shotType=MEDIUM;
		public int sceneid=-1;//默认的无场景的
		//String person="AshleyChin";
		public int seat=-1;
		String motion="-";
		String audio="-";
		String note="-";
		
		public int uid;//唯一id
		int sortid;//用于重排序时确定位置     这也是镜号
		boolean chosen=false;
		
		public boolean available=true;
		
		public Clip vdo;//请设置此视频与音效片段的链接
		//控件
		//镜号  内容  景别  长度  运镜  音效  备注
		JLabel sidl=new JLabel("#");
		JTextArea desa=new JTextArea("");
		JLabel sht=new JLabel("中景");
		JLabel len=new JLabel("4\"");
		JTextArea mot=new JTextArea();
		JTextField ado=new JTextField();
		JTextArea not=new JTextArea();
		
		Icon del=new Icon(IconDataBase.shot_table_del_tiny);
		border bd=new border();
		public shot() {
			this.setLayout(null);
			this.setSize(600, 60);
			del.setSize(30, 30);
			this.add(del);
			del.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent e) {
					if(chosen)
						Main.gui.sbp.sb.unselectAll();
					Main.gui.sbp.sb.removeShot(uid,null);
					Main.gui.sbp.sb.updateShotTable();
					Main.gui.sbp.sb.repaint();
					Main.gui.sbp.st.updateScene();
					Main.gui.sbp.st.repaint();
					Main.gui.sbp.sh.updateInfo();
				}
				public void mouseEntered(MouseEvent e) {
				}
				public void mouseExited(MouseEvent e) {
				}
				public void mousePressed(MouseEvent e) {
				}
				public void mouseReleased(MouseEvent e) {
				}
			});
			
			this.add(sidl);
			sidl.setFont(large);
			sidl.addMouseListener(this);
			sidl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			this.add(desa);
			this.add(sht);
			this.add(len);
			this.add(mot);
			this.add(ado);
			this.add(not);
			this.add(bd);
		}
		public void resize() {
			if(chosen) {
				this.setBackground(PnlColor.getColor(getBackground(), 1.2));
			}
			int w=this.getWidth(),h=this.getHeight();

			bd.setSize(getSize());
			bd.setLocation(0, 0);
			bd.setBackground(PnlColor.getColor(getBackground(), 1));
			//镜号
			sidl.setSize(30, h);
			sidl.setLocation(4, 0);
			sidl.setForeground(fcl);
			sidl.setText(" "+(sortid+1));
			//内容
			desa.setSize((int) (this.getWidth()*0.3), this.getHeight()-4);
			desa.setLocation(sidl.getX()+sidl.getWidth()+6, 2);
			desa.setForeground(fcl);
			desa.setCaretColor(fcl);
			desa.setEditable(false);
			desa.setText(vdo.description);
			desa.setBackground(getBackground());
			desa.setLineWrap(true);
			
			//景别
			sht.setSize(50, this.getHeight());
			sht.setLocation(desa.getX()+desa.getWidth()+3, 0);
			String shtt="";
			if(shotType==this.EXTRE_CLOSE) {
				shtt="特写";
			}else if(shotType==this.CLOSE) {
				shtt="近景";
			}else if(shotType==this.MEDIUM) {
				shtt="中景";
			}else if(shotType==this.PANORAMA) {
				shtt="全景";
			}else if(shotType==this.PROSPECT) {
				shtt="远景";
			}
			sht.setText(shtt);
			sht.setForeground(fcl);
			//时长
			len.setSize(40, this.getHeight());
			len.setLocation(sht.getX()+sht.getWidth()+2, 0);
			len.setText(Main.getNTTimeCodeSmartly(vdo.length));
			len.setForeground(fcl);
			len.setHorizontalAlignment(SwingConstants.RIGHT);
			//运镜
			mot.setSize((int) (this.getWidth()*0.17), this.getHeight()-4);
			mot.setLocation(len.getX()+len.getWidth()+7, 2);
			mot.setText(motion);
			mot.setForeground(fcl);
			mot.setEditable(false);
			mot.setBackground(getBackground());
			mot.setLineWrap(true);
			//音效
			ado.setSize((int) (this.getWidth()*0.15),this.getHeight()-4);
			ado.setLocation(mot.getX()+mot.getWidth()+5, 2);
			ado.setText(this.audio);
			ado.setForeground(fcl);
			ado.setEditable(false);
			ado.setBackground(getBackground());
			ado.setBorder(null);
			//备注
			not.setSize(this.getWidth()-ado.getX()-ado.getWidth()-41, this.getHeight()-4);
			not.setLocation(ado.getX()+ado.getWidth()+5, 2);
			not.setText(this.note);
			not.setForeground(fcl);
			not.setEditable(false);
			not.setBackground(getBackground());
			not.setLineWrap(true);
			//删除
			del.setBackground(getBackground());
			del.setSize(30, 30);
			del.setLocation(not.getWidth()+not.getX()+5, 10);
			
			this.setBackground(Color.black);
			
			this.repaint();
		}
		public void mouseClicked(MouseEvent e) {
			//System.out.println(1111);
			select(((shot)((JLabel)e.getSource()).getParent()));
		}
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e) {
		}
		public void mousePressed(MouseEvent e) {
		}
		public void mouseReleased(MouseEvent e) {
		}
	}
	public class filter extends JPanel{
		private int scnid=-2;//-2 为显示所有
		public void paint(Graphics g) {
			g.setColor(getBackground());
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(fcl);
			String name="所有镜头";
			g.setFont(italic);
			if(scnid!=-2) {
				name=Main.gui.sbp.st.getScene(Main.gui.sbp.st.getSelectedSceneId()).gp.name;
				g.setFont(normal);
			}
			g.drawString(name, 5,20);
		}
		public void updateFilter(int scuid) {
			this.scnid=scuid;
			updateShotTable();
		}
		public int getFilterID() {
			return scnid;
		}
	}
	public class fields extends JPanel implements MouseListener{
		public int sortBy=0;
		public static final int SORTID=0,DESCRI=1,TYPE=2,LENGTH=3,MOTION=4,AUDIO=5,NOTE=6;
		public int direc=1;//1升序 -1降序
		public fields() {
			this.addMouseListener(this);
		}
		public void paint(Graphics g) {
			g.setColor(PnlColor.getColor(getBackground(), 1.5));
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			g.setColor(fcl);
			int hs=5,he=this.getHeight()-5,dx=10,w=this.getWidth()+10;
			//分割线
			g.drawLine(31+dx, hs, 31+dx,he);
			g.drawLine((int)(w*0.3)+28+dx, hs,(int) (w*0.3)+28+dx,he);
			g.drawLine((int)(w*0.3)+28+54+dx, hs,(int) (w*0.3)+28+54+dx,he);
			g.drawLine((int)(w*0.3)+28+54+46+dx, hs,(int) (w*0.3)+28+54+46+dx,he);
			g.drawLine((int)(w*0.3)+28+54+46+(int) (this.getWidth()*0.17)+3+dx, hs
					,(int) (w*0.3)+28+54+46+(int) (this.getWidth()*0.17)+3+dx,he);
			g.drawLine((int)(w*0.3)+28+54+46+(int) (this.getWidth()*0.17)+3+(int) (this.getWidth()*0.15)+5+dx, hs
					,(int) (w*0.3)+28+54+46+(int) (this.getWidth()*0.17)+3+(int) (this.getWidth()*0.15)+5+dx,he);
			g.drawLine(w-52, hs, w-52, he);
			//字段
			hs=19;
			int dx1=15;
			g.drawString("镜号", dx1, hs);
			g.drawString("内容", 33+dx+dx1, hs);
			g.drawString("景别",(int)(w*0.3)+28+dx+dx1, hs);
			g.drawString("时长", (int)(w*0.3)+28+54+dx+dx1, hs);
			g.drawString("运镜", (int)(w*0.3)+28+54+46+dx+dx1, hs);
			g.drawString("音效",(int)(w*0.3)+28+54+46+(int) (this.getWidth()*0.17)+3+dx+dx1, hs);
			g.drawString("备注", (int)(w*0.3)+28+54+46+(int) (this.getWidth()*0.17)+3+(int) (this.getWidth()*0.15)+5+dx+dx1, hs);
			//排序标志
			int x=0;
			if(this.sortBy==this.SORTID)
				x=2;
			else if(sortBy==this.DESCRI)
				x= 33+dx;
			else if(sortBy==this.TYPE)
				x=(int)(w*0.3)+28+dx+3;
			else if(sortBy==this.LENGTH)
				x=(int)(w*0.3)+28+54+dx+3;
			else if(sortBy==this.MOTION)
				x=(int)(w*0.3)+28+54+46+dx+3;
			else if(sortBy==this.AUDIO)
				x=(int)(w*0.3)+28+54+46+(int) (this.getWidth()*0.17)+3+dx+3;
			else if(sortBy==this.NOTE)
				x=(int)(w*0.3)+28+54+46+(int) (this.getWidth()*0.17)+3+(int) (this.getWidth()*0.15)+5+dx+3;
			
			g.setFont(tiny);
			if(this.direc==-1) {
				g.drawString("▼", x, hs);
				
			}else {
				g.drawString("▲", x, hs);
			}
//			g.setColor(Color.DARK_GRAY);
//			g.drawRect(-1, -1, this.getWidth()-1, this.getHeight());
		}
		public void mouseClicked(MouseEvent arg0) {
			int x=arg0.getX(),y=arg0.getY(),dx=10;
			int w=this.getWidth()+10;
			if(x<33+dx) {//sortid
				direc=sortBy==SORTID?direc*-1:1;
				sortBy=SORTID;
			}else if(x<(int)(w*0.3)+28+dx){//descri
				direc=sortBy==DESCRI?direc*-1:1;
				sortBy=DESCRI;
			}else if(x<(int)(w*0.3)+28+54+dx) {
				direc=sortBy==TYPE?direc*-1:1;
				sortBy=TYPE;
			}else if(x<(int)(w*0.3)+28+54+46+dx) {//length
				direc=sortBy==LENGTH?direc*-1:1;
				sortBy=LENGTH;
			}else if(x<(int)(w*0.3)+28+54+46+(int) (this.getWidth()*0.17)+3+dx) {//motion
				direc=sortBy==MOTION?direc*-1:1;
				sortBy=MOTION;
			}else if(x<(int)(w*0.3)+28+54+46+(int) (this.getWidth()*0.17)+3+(int) (this.getWidth()*0.15)+5+dx) {//AUDIO
				direc=sortBy==AUDIO?direc*-1:1;
				sortBy=AUDIO;
			}else {//note
				direc=sortBy==NOTE?direc*-1:1;
				sortBy=NOTE;
			}
			updateShotTable();
		}
		public void mouseEntered(MouseEvent arg0) {
		}
		public void mouseExited(MouseEvent arg0) {
		}
		public void mousePressed(MouseEvent arg0) {
		}
		public void mouseReleased(MouseEvent arg0) {
		}
	}
	
	public static final Color fcl=new Color(240,240,240);
	public static final int DEFAULT_LENGTH=120;//默认镜头长度（帧）
	public static final int EXTRE_CLOSE=0,CLOSE=1,MEDIUM=2,PANORAMA=3,PROSPECT=4;//景别
	public static final Font italic=new Font("",Font.ITALIC,13),normal=new Font("",Font.PLAIN,13)
			,tiny=new Font("",Font.PLAIN,9),large=new Font("",Font.PLAIN,15);
	
	public ArrayList<shot> shot=new ArrayList<shot>();
	public int uid=0;//为uid赋值
	public int sortid=0;//基础sortid赋值，在每次重排序之后会被设置
	
	JPanel bgp=new JPanel();
	
	Icon win=new Icon(IconDataBase.shot_table_window);
	Icon add=new Icon(IconDataBase.shot_table_add);
	Icon filt=new Icon(IconDataBase.shot_table_filter);
	Icon eras=new Icon(IconDataBase.shot_table_eraser);
	
	JLabel noshtip=new JLabel("此场景无可显示镜头");
	
	filter fil=new filter();
	fields fie=new fields();
	public ShotTable() {
		this.setLayout(null);
		this.setSize(400, 400);
		this.setTitlex("分镜表");
		
		noshtip.setFont(italic);
		noshtip.setSize(120, 30);
		noshtip.setForeground(fcl);
		noshtip.setLocation(20, 50);
		noshtip.setVisible(false);
		this.add(noshtip);
		
		fie.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.add(fie);
		
		bgp.setSize(this.getWidth(), this.getHeight()-60);
		bgp.setLocation(0, 90);
		bgp.setLayout(null);
		this.add(bgp);

		fil.setSize(80, 30);
		fil.setLocation(200, 30);
		this.add(fil);
		eras.setSize(18, 18);
		eras.setLocation(200, 30);
		eras.addMouseListener(this);
		this.add(eras);
		filt.setSize(20, 20);
		this.add(filt);
		
		win.setSize(12, 12);
		win.setLocation(12, 7);
		this.add(win);
		add.setSize(24, 24);
		add.setLocation(10, 30);
		add.addMouseListener(this);
		this.add(add);
		
		//this.updateShotTable();
	}
	/**
	 * 为shot添加鼠标事件
	 * @param sh
	 */
	public void addML(shot sh) {
		sh.addMouseListener(this);
	}
	/**
	 * 单独选中一个素材
	 * @param ts
	 */
	public void select(shot ts) {
		this.unselectAll();
		ts.chosen=true;
		this.updateShotTable();
		this.repaint();
		Main.gui.sbp.sh.updateInfo();
	}
	/**
	 * 重算所有镜号
	 */
	public void recalcSortID() {
		this.sortid=0;
		this.sort();
		for(shot sh:shot) {
			if(sh.available)
				sh.sortid=sortid++;
			else
				sh.sortid=-1;
		}
		this.updateShotTable();
	}
	public void updateAvailable() {
		for(shot sh:shot) {
			sh.available=sh.vdo.track==0?true:false;
		}
	}
	/**
	 * 现在改整个架构有些复杂，计划之后禁止在轨道0添加片段
	 * 以后将会更改为两处同步
	 */
	public shot addShot(String name,int length,int scene) {
		Log.record("Add new shot "+name);
		shot ts=new shot();
		ts.vdo=Main.gui.bgp.ep.addElement(name, EditingPnl.Clip.VIDEO, length, 0, getEndX(),ts);
		ts.sceneid=scene;
		ts.uid=uid++;
		ts.sortid=sortid++;
		ts.vdo.groupId=Main.gui.sbp.st.getScene(scene).gp.uid;
		ts.addMouseListener(this);
		this.shot.add(ts);
		select(ts);
		//System.out.println("scene "+scene);
		this.updateShotTable();
		Main.gui.sbp.st.updateScene();
		return ts;
	}
	/**
	 * 根据已有的clip创建镜头
	 * @param vdo
	 * @param scene
	 * @param available
	 */
	public shot addShot(Clip vdo,int scene,boolean available) {
		Log.record("Add new shot by vdo "+vdo.title);
		shot ts=new shot();
		ts.vdo=vdo;
		ts.sceneid=scene;
		ts.uid=uid++;
		ts.sortid=-1;//之后重算所有镜号
		ts.vdo.groupId=vdo.groupId;
		ts.addMouseListener(this);
		ts.available=available;
		this.shot.add(ts);
		if(available)
			select(ts);
		this.updateShotTable();
		Main.gui.sbp.st.updateScene();
		this.recalcSortID();
		return ts;
	}
	public void insert(int index,String name,int length,int scene) {
		//TODO 此方法须测试
		Log.record("insert shot at "+index);
		shot ts=new shot();
		ts.sceneid=scene;
		ts.uid=uid++;
		shot lsShot=getBySortId(index);
		int lsx= lsShot.vdo.x;
		//修改之后素材时间轴位置
		for(shot sh:shot) {
			//System.out.println(sh.sortid+" "+sh.vdo.title);
			if(sh.sortid>=index) {
				//System.out.println(sh.vdo.title+" "+index);
				sh.vdo.x+=length;
			}
		}
		//计算位置
		ts.vdo=Main.gui.bgp.ep.addElement(name, EditingPnl.Clip.VIDEO, length
				, 0,lsx,ts);
		//将此位置之后的所有素材sortid加一
		for(shot sh:shot) {
			if(sh.sortid>=index) {
				sh.sortid++;
			}
		}
		ts.sortid=index;
		sortid++;
		ts.addMouseListener(this);
		this.shot.add(ts);
		select(ts);
		this.addML(ts);
		//解决顺序问题
		this.sort();
		this.updateShotTable();
		Main.gui.sbp.st.updateScene();
	}
	/**
	 * 根据uid删除shot,这个方法是与时间轴同步的
	 * @param uid
	 * @param vdo 如果vdo为null则是波纹删除
	 */
	public void removeShot(int uid,Clip vdo) {
		this.sort();
		//TODO 需要测试
		for(int i=0;i<shot.size();i++) {
			//found
			if(shot.get(i).uid==uid) {
				//遍历之后所有，以设置x和镜号
				for(int j=i+1;j<shot.size();j++) {
					//镜号减一
					shot.get(j).sortid--;
					//波纹设置x,将忽略轨道号
					//TODO 验证是否有必要验证轨道号
					if(vdo==null)
						shot.get(j).vdo.x-=shot.get(i).vdo.length;
				}
				//if(vdo==null)
				Main.gui.bgp.ep.elms.remove(shot.get(i).vdo);
				shot.remove(i);
				//设置镜号终点
				sortid--;
				Main.gui.sbp.st.updateScene();
				return;
			}
		}
	}
	public void removeShot(Clip vdo) {
		if(vdo.trackType()==Clip.ADO_TRACK)
			return;
		removeShot(Main.gui.sbp.sb.getShotByClipUID(vdo.uid).uid, vdo);
	}
	/**
	 * 根据ep的记录的clip计算track0的最尾端
	 * @return
	 */
	public int getEndX() {
		int result=0;
		for(Clip clp:Main.gui.bgp.ep.elms) {
			if(clp.track==0&&clp.trackType()==Clip.VDO_TRACK) {
				result=clp.x+clp.length>result?clp.x+clp.length:result;
			}
		}
		return result;
	}
	/**
	 * 根据镜号取得镜头对象
	 * @param sortId
	 * @return
	 */
	public shot getBySortId(int sortId) {
		for(shot sh:shot) {
			if(sh.sortid==sortId) {
				return sh;
			}
		}
		return null;
	}
	/**
	 * 根据已设置的排序方式进行排序
	 */
	public void sortBeforeUpdate() {
		Comparator<shot> cpr=null;
		int d=fie.direc;
		switch(this.fie.sortBy) {
		case fields.SORTID:{
			cpr=(arg0,arg1)->arg0.sortid>arg1.sortid?d:d*-1;
			break;
		}
		case fields.DESCRI:{
			cpr=(arg0,arg1)->arg0.vdo.description.compareTo(arg1.vdo.description)>=0?d:d*-1;
			break;
		}
		case fields.TYPE:{
			cpr=(arg0,arg1)->arg0.shotType>arg1.shotType?d:d*-1;
			break;
		}
		case fields.LENGTH:{
			cpr=(arg0,arg1)->arg0.vdo.length>arg1.vdo.length?d:d*-1;
			break;
		}
		case fields.MOTION:{
			cpr=(arg0,arg1)->arg0.motion.compareTo(arg1.motion)>=0?d:d*-1;
			break;
		}
		case fields.AUDIO:{
			cpr=(arg0,arg1)->arg0.audio.compareTo(arg1.audio)>=0?d:d*-1;
			break;
		}
		case fields.NOTE:{
			cpr=(arg0,arg1)->arg0.note.compareTo(arg1.note)>=0?d:d*-1;
			break;
		}
		}

		this.shot.sort(cpr);
	}
	/**
	 * 排序
	 */
	public void sort() {
//		Comparator cpr=new Comparator<shot>() {
//
//			@Override
//			public int compare(guiMgr.script.ShotTable.shot arg0, guiMgr.script.ShotTable.shot arg1) {
//				// TODO Auto-generated method stub
//				if(arg0.sortid>arg1.sortid)
//					return 1;
//				return -1;
//			}
//			
//		};
//		this.shot.sort(cpr);
//		this.updateShotTable();
		sortByX();
	}
	/**
	 * 根据x排序
	 */
	public void sortByX() {
		Comparator cpr=new Comparator<shot>() {

			@Override
			public int compare(guiMgr.script.ShotTable.shot arg0, guiMgr.script.ShotTable.shot arg1) {
				// TODO Auto-generated method stub
				if(arg0.vdo.x>arg1.vdo.x)
					return 1;
				return -1;
			}
			
		};
		this.shot.sort(cpr);
		//this.updateShotTable();
	}
	public void resize() {
		super.resize();
		
		fie.setLocation(10, 55);
		fie.setSize(this.getWidth()-19, 31);
		fie.setBackground(getBackground());
		
		
		bgp.setSize(this.getWidth(), this.getHeight()-60);
		bgp.setBackground(getBackground());
		
		filt.setLocation(this.getWidth()-144, 30);
		filt.setBackground(getBackground());
		fil.setLocation(filt.getWidth()+filt.getX()+8, 25);
		fil.setBackground(PnlColor.getColor(getBackground(), 0.8));
		eras.setLocation(fil.getX()+fil.getWidth()+2, 30);
		eras.setBackground(getBackground());
		this.updateShotTable();
	}
	/**
	 * 刷新界面
	 */
	public void updateShotTable() {
		win.setBackground(getBackground());
		add.setBackground(PnlColor.getColor(getBackground(), 1.15));
		noshtip.setVisible(false);
		this.fie.setVisible(true);
		
		bgp.removeAll();
		this.sortBeforeUpdate();
		int ya=0;
		for(shot sh:shot) {
			//System.out.println("adding "+sh.vdo.title);
			//sh.addMouseListener(this);
			if(this.fil.getFilterID()!=-2&&sh.sceneid!=fil.getFilterID())
				continue;
			if(!sh.available)//不可用
				continue;
			sh.setSize(this.getWidth()-30, 40);
			sh.setLocation(15, ya*(sh.getHeight()+3));
			sh.setBackground(PnlColor.getColor(getBackground(), sh.chosen?1.45:1.2));
			sh.resize();
			bgp.add(sh);
			ya++;
		}
		if(ya==0) {
			noshtip.setVisible(true);
			this.fie.setVisible(false);
			this.unselectAll();
			Main.gui.sbp.sh.updateInfo();
		}else if(!this.isSelectedShotCanBeSeen()) {
			this.selectLastOne();
		}
		this.repaint();
	}
	/**
	 * 取消所有选中
	 */
	public void unselectAll() {
		for(shot sh:shot) {
			sh.chosen=false;
		}
	}
	public shot getSelectedShot() {
		for(shot sh:shot) {
			if(sh.chosen)
				return sh;
		}
		return null;
	}
	/**
	 * 改变scuid和gpuid，这个是景组同步的
	 * @param sc_from 原场景id
	 * @param sc_to
	 * @param gp_from 原组id
	 * @param gp_to
	 */
	public void changeScGpUID(int sc_from,int sc_to,int gp_from,int gp_to) {
		for(shot sh:shot) {
			if(sh.sceneid==sc_from) {
				sh.sceneid=sc_to;
				sh.seat=-1;
			}	
			if(sh.vdo.groupId==gp_from)
				sh.vdo.groupId=gp_to;
		}
	}
	public boolean isSelectedShotCanBeSeen() {
		if(this.getSelectedShot()==null)
			return false;
		return this.getSelectedShot().sceneid==fil.getFilterID()||fil.getFilterID()==-2;
	}
	/**
	 * 选中最后一个镜头
	 */
	public void selectLastOne() {
		
		shot tosel=shot.get(shot.size()-1);
		if(fil.getFilterID()!=-2) {
			for(shot sh:shot) {
				if(sh.sceneid==fil.getFilterID()&&sh.available) {
					tosel=sh;
				}
			}
		}
		select(tosel);
	}
	/**
	 * 根据数值返回景别中文
	 * @param shotType
	 * @return
	 */
	public String getTypeText(int shotType) {

		String shtt="";
		if(shotType==this.EXTRE_CLOSE) {
			shtt="特写";
		}else if(shotType==this.CLOSE) {
			shtt="近景";
		}else if(shotType==this.MEDIUM) {
			shtt="中景";
		}else if(shotType==this.PANORAMA) {
			shtt="全景";
		}else if(shotType==this.PROSPECT) {
			shtt="远景";
		}
		return shtt;
	}
	
	public shot getShotByClipUID(int vdo) {
		for(shot sh:shot) {
			if(sh.vdo.uid==vdo) {
				return sh;
			}
		}
		return null;
	}
	
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource()==add) {
			this.addShot("Shot "+this.uid, 120, Main.gui.sbp.st.getSelectedSceneId());
			Main.gui.sbp.st.updateScene();
			Main.gui.sbp.st.repaint();
		}else if(arg0.getSource()==eras) {
			this.fil.updateFilter(-2);
		}
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
}
