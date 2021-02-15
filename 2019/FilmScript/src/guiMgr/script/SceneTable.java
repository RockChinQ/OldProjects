package guiMgr.script;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.alibaba.fastjson.JSONObject;

import boot.Main;
import data.IconDataBase;
import data.GroupManager.group;
import guiMgr.FixedPanel;
import guiMgr.PnlColor;
import guiMgr.fields.Icon;
import guiMgr.script.ShotTable.shot;
import log.Log;

public class SceneTable extends FixedPanel implements MouseListener{
	static final Color cho=new Color(0,140,250)
			,border=new Color(180,180,180),btnt=new Color(30,160,255);
	static final Font scnn=new Font("",Font.PLAIN,13),tiny=new Font("",Font.PLAIN,9);
	static final Color namecl=new Color(240,240,240);
	public static class scene extends JPanel{
		public static class seat extends JPanel{
			Point lo;
			String name;
			int uid=0;
			public Color cl=new Color(235,108,0),selec=new Color(255,190,4);
			seat(Point lo,String name,scene sc){
				this.lo=new Point(lo);
				this.name=new String(name);
				this.uid=sc.seatuidindex++;
				this.addMouseListener(new MouseListener() {
					public void mouseClicked(MouseEvent e) {
						Main.gui.sbp.sb.getSelectedShot().seat=uid;
						Main.gui.sbp.sh.sec.repaint();
					}
					public void mouseEntered(MouseEvent e) {}
					public void mouseExited(MouseEvent e) {}
					public void mousePressed(MouseEvent e) {}
					public void mouseReleased(MouseEvent e) {}
				});
			}
			seat(JSONObject json){
				this.addMouseListener(new MouseListener() {
					public void mouseClicked(MouseEvent e) {
						Main.gui.sbp.sb.getSelectedShot().seat=uid;
						Main.gui.sbp.sh.sec.repaint();
					}
					public void mouseEntered(MouseEvent e) {}
					public void mouseExited(MouseEvent e) {}
					public void mousePressed(MouseEvent e) {}
					public void mouseReleased(MouseEvent e) {}
				});
				this.loadJSONData(json);
			}
			public JSONObject getJSONObject() {
				JSONObject json=new JSONObject();
				json.put("lo.x", lo.x);
				json.put("lo.y", lo.y);
				json.put("name", name);
				json.put("uid", uid);
				return json;
			}
			public void loadJSONData(JSONObject json) {
				this.lo.setLocation(json.getDoubleValue("lo.x"), json.getDoubleValue("lo.y"));
				name=json.getString("name");
				uid=json.getIntValue("uid");
			}
			public void paint(Graphics g) {
				boolean select=false;
				if(Main.gui.sbp.sb.getSelectedShot().seat==uid) {
					select=true;
				}
				g.setColor(select?selec:cl);
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				g.setColor(Color.white);
				g.drawString(""+uid, 2, 7);
			}
		}
		//String name;
		String descri;
		String location;
		String note;
		public int uid;
		public group gp;
		Color thumb[][]=new Color[75][115];
		public ArrayList<seat> seats=new ArrayList<seat>();
		int seatuidindex=0;
		boolean chosen=false;
		//temp
		int shotamount=0;
		
		
		scene(){
			for(int i=0;i<75;i++) {
				for(int j=0;j<115;j++) {
					thumb[i][j]=new Color(thumbCanvas.bg.getRGB());
					//System.out.print(1111);
				}
			}
		}
		//scene的jsonpacker
		public JSONObject getJSONObject() {
			JSONObject json=new JSONObject();
			json.put("descri", descri);
			json.put("location", location);
			json.put("note", note);
			json.put("uid", uid);
			json.put("groupuid", gp.uid);
			
			//seats
			JSONObject seat=new JSONObject();
			int slen=seats.size();
			seat.put("length", slen);
			for(int i=0;i<slen;i++) {
				seat.put(i+"", seats.get(i).getJSONObject().toJSONString());
			}
			json.put("seats", seat.toJSONString());
			//缩略图
			StringBuffer thumbs=new StringBuffer();
			for(int i=0;i<thumb.length;i++) {
				for(int j=0;j<thumb[0].length;j++) {
					thumbs.append(thumb[i][j].getRGB()+";");
				}
			}
			json.put("thumbData", thumbs.toString());
			
			json.put("seatuidindex", seatuidindex);
			json.put("chosen", chosen);
			return json;
		}
		//scene的jsonloader
		public void loadJSONData(JSONObject json) {
			descri=json.getString("descri");
			location=json.getString("location");
			note=json.getString("note");
			uid=json.getIntValue("uid");
			//gp.uid=json.getIntValue("groupuid");
			gp=Main.gm.getGroup(json.getIntValue("groupuid"));
			
			//清空seat装入json的seats
			seats=new ArrayList<seat>();
			JSONObject seat=json.getJSONObject("seats");
			int length=seat.getIntValue("length");
			for(int i=0;i<length;i++) {
				seat ts=new seat(seat.getJSONObject(i+""));
				seats.add(ts);
			}
			//缩略图
			String thud[]=json.getString("thumbData").split(";");
			int index=0;
			for(int i=0;i<thumb.length;i++) {
				for(int j=0;j<thumb[0].length;j++) {
					thumb[i][j]=new Color(Integer.parseInt(thud[index++]));
				}
			}
			
			seatuidindex=json.getIntValue("seatuidindex");
			chosen=json.getBooleanValue("chosen");
			
		}
		
		public void paint(Graphics g) {
			FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(scnn);
			int w=SwingUtilities.computeStringWidth(fm, gp.name);
			//bg
			g.setColor(getBackground());
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			//文字
			g.setColor(namecl);
			g.drawString(gp.name, 5, this.getHeight()-7);
			g.setColor(getBackground());
			g.fillRect(this.getWidth()-3, this.getHeight()-21, 2, 20);
			//边框
			g.setColor(getBackground());
			g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
			//镜头数量
			g.setColor(PnlColor.getColor(getBackground(), 1.5));
			g.fillRect(1, 4, 20, 20);
			g.setColor(namecl);
			g.drawString(shotamount+"",3, 19);
			//标记
			g.setColor(chosen?this.gp.cl:border);
			g.fillRect(0,0, this.getWidth(), 4);
			//del
			if(uid!=-1) {
				int dx=93,dyssb=5;
				g.setColor(PnlColor.getColor(getBackground(), 1.5));
				g.fillRect(dx-3, dyssb-1, 20, 20);
				g.setColor(namecl);
				g.drawLine(6+dx, 2+dyssb, 8+dx, 2+dyssb);
				g.drawLine(2+dx, 3+dyssb, 12+dx, 3+dyssb);
				g.drawLine(3+dx, 5+dyssb, 3+dx, 13+dyssb);
				g.drawLine(4+dx, 14+dyssb, 10+dx, 14+dyssb);
				g.drawLine(11+dx, 5+dyssb, 11+dx, 13+dyssb);
				g.drawLine(5+dx, 6+dyssb, 5+dx, 12+dyssb);
				g.drawLine(7+dx, 6+dyssb, 7+dx, 12+dyssb);
				g.drawLine(9+dx, 6+dyssb, 9+dx, 12+dyssb);
			}
			//color
			g.setColor(PnlColor.getColor(getBackground(), 1.5));
			g.fillRect(this.getWidth()-40, 4, 20, 20);
			//System.out.println(this.getWidth()-40);
			g.setColor(gp.cl);
			g.fillRect(this.getWidth()-36, 8,12, 12);
		}
		public void removeSeat(int uid) {
			seat s=null;
			for(seat se:seats) {
				if(se.uid==uid) {
					s=se;
					break;
				}
			}
			//修改seat为此的镜头为无镜头
			for(shot sh:Main.gui.sbp.sb.shot) {
				if(sh.sceneid==uid&&sh.seat==s.uid)
					sh.seat=-1;
			}
			seats.remove(s);
		}
	}
	public static class SceneBtn extends Icon{
		public static Color border=new Color(240,240,240);
		public SceneBtn(String ico,int w,int h) {
			super(ico);
			this.setSize(w, h);
		}
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(border);
			g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
		}
		public void setBorderCL(Color bd) {
			this.border=bd;
		}
	}
	public static final Color btnbg=new Color(75,75,75),btnch=new Color(30,30,30);
	public int index=-1;
	public ArrayList<scene> scn=new ArrayList<scene>();
	public JPanel bgp=new JPanel();
	
	public boolean lin=false;
	
	public SceneBtn addsc=new SceneBtn(IconDataBase.scene_table_add,24,24);
	public SceneBtn link=new SceneBtn(IconDataBase.scene_table_link,24,24);
	public scene tsc=null;//调色板临时scene
	public SceneTable() {
		this.setLayout(null);
		this.setSize(1000, 150);
		this.setTitlex("场景表");
		this.setIcon(IconDataBase.scene_table_win);

		addsc.setLocation(2, 27);
		addsc.setBackground(btnbg);
		addsc.setColor(btnt);
		this.add(addsc);
		addsc.addMouseListener(this);
		link.setLocation(30, 27);
		link.setBackground(btnbg);
		link.setColor(btnt);
		this.add(link);
		link.addMouseListener(this);
		
		bgp.setLayout(null);
		bgp.setSize(this.getSize());
		bgp.setLocation(0, 55);
		this.add(bgp);
	}
	/**
	 * 根据传入的name计算出是否需要更改为其他名字
	 * @param name
	 * @return
	 */
	public String getLegalName(String name,scene s) {
		for(scene sc:scn) {
			if(sc.equals(s))
				continue;
			//发现重名
			if(name.equals(sc.gp.name)) {
				String sp[]=sc.gp.name.split("-");
				int num=0;
				try {
					num=Integer.parseInt(sp[sp.length-1]);
					StringBuffer nr=new StringBuffer();
					for(int i=0;i<sp.length;i++) {
						if(i!=sp.length-1)
							nr.append(sp[i]+"-");
						else {
							nr.append(num+1);
						}
					}
					return getLegalName(nr.toString(),s);
				}catch(Exception e) {
					//最后一个不是数字，则调用此方法
					return getLegalName(name+"-2",s);
				}
			}
		}
		return name;
	}
	/**
	 * 新建场景
	 */
	public int addScene(String name,String descri) {
		Log.record("Add scene "+name);
		scene sc=new scene();
		name=new String(this.getLegalName(name,sc));
		sc.gp=Main.gm.getGroup(Main.gm.newGroup(name));
		sc.gp.name=name;
		sc.descri=descri;
		sc.uid=index++;
		this.setAllUnselected();
		sc.chosen=true;
		sc.setSize(110, 95);
		this.scn.add(sc);
		sc.addMouseListener(new sceneMouse());
		updateScene();
		Main.gui.sbp.sa.updateInfo();
		return sc.gp.uid;
	}
	/*
	 * 通过json新建，不新建组而是链接
	 * */
	public int addScene(JSONObject json) {
		Log.record("Add scene from json");
		scene sc=new scene();
		sc.loadJSONData(json);
		this.setAllUnselected();
		sc.chosen=true;
		sc.setSize(110, 95);
		this.scn.add(sc);
		sc.addMouseListener(new sceneMouse());
		updateScene();
		Main.gui.sbp.sa.updateInfo();
		return sc.gp.uid;
	}
	/**
	 * 取消所有选中的项
	 */
	public void setAllUnselected() {
		for(scene sc:scn) {
			sc.chosen=false;
		}
	}
	/**
	 * 根据uid删除场景
	 * @param uid
	 */
	public void removeScene(int uid) {
		for(int i=0;i<scn.size();i++) {
			scene sc=scn.get(i);
			if(sc.uid==uid) {
				scn.remove(sc);
				return;
			}
		}
	}
	public scene getSceneByGroupUID(int gpuid) {
		for(scene sc:scn) {
			if(sc.gp.uid==gpuid) {
				return sc;
			}
		}
		return null;
	}
	public scene getScene(int uid) {
		for(scene sc:scn) {
			//System.out.println("     "+sc.uid);
			if(sc.uid==uid) {
				//System.out.println("index scn return:"+uid);
				return sc;
			}
		}
		return null;
	}
	/**
	 * 根据uid选中
	 * @param uid
	 */
	public void choose(int uid,boolean chosen) {

		for(scene sc:scn) {
			if(sc.uid==uid)
				sc.chosen=chosen;
		}
		Main.gui.sbp.sa.updateInfo();
	}
	public void resize() {
		super.resize();
		bgp.setSize(this.getWidth()-15, this.getHeight()-25);
	}
	/**
	 * 刷新所有场景
	 */
	public void updateScene() {
		SceneBtn.border=this.getBackground();
		bgp.setBackground(PnlColor.getColor(getBackground(), 1));
		bgp.removeAll();
		
		link.setBackground(lin?btnch:btnbg);
		
		for(int i=0;i<scn.size();i++) {
			//System.out.println("update scene "+scn.get(i).uid);
			scn.get(i).setSize(110, 95);
			scn.get(i).setLocation(i*120+10, 0);
			scn.get(i).setBackground(PnlColor.getColor(getBackground(), 1.3));
			scn.get(i).shotamount=0;
			for(shot sh:Main.gui.sbp.sb.shot) {
				//必须是可用的镜头才写进去
				if(sh.sceneid==scn.get(i).uid&&sh.available) {
					scn.get(i).shotamount++;
					//System.out.println("shot name "+sh.vdo.title);
				}
			}
			scn.get(i).repaint();
			bgp.add(scn.get(i));
		}
	}
	/**
	 * 获取已选中的场景id
	 */
	public int getSelectedSceneId() {
		for(scene scn:scn) {
			if(scn.chosen) {
				return scn.uid;
			}
		}
		return -1;
	}
	public scene getSceneByName(String name) {
		for(scene sc:scn) {
			if(name.equals(sc.gp.name)) {
				return sc;
			}
		}
		return null;
	}
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource()==addsc) {
			this.addScene("键入名称", "输入布景细节");
			this.setAllUnselected();
			this.choose(index-1, true);
			this.updateScene();
			this.repaint();
			Main.showTip("新建场景", Main.MESSAGE);
		}else if(arg0.getSource()==link) {
			lin=!lin;
			link.setBackground(lin?btnch:btnbg);
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
