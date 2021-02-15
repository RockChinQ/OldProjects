package guiMgr.panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

import boot.Main;
import data.GroupManager;
import data.GroupManager.group;
import guiMgr.FloatPanel;
import guiMgr.PnlColor;
import guiMgr.panels.EditingPnl.Clip;
import log.Log;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class GroupPnl extends FloatPanel{
	public static final Color txColor=new Color(240,240,240);
	public static final Font txf=new Font("",Font.PLAIN,12);
	public static final Font addf=new Font("",Font.PLAIN,19);
	public class GroupEntry extends JPanel{
		public String name;
		public group gp;
		public text tx=new text();
		public del dl=new del();
		public GroupEntry(String name,group gp) {
			this.name=name;
			this.gp=gp;
			this.setLayout(null);
			this.setSize(170, 30);
			//this.setBackground(gp.cl);
			//this.setBackground(PnlColor.getColor(Main.gui.bgp.ep.getBackground(), 1.2));
			
			dl.setSize(16, 16);
			dl.setBackground(null);
			dl.setLocation(150, 7);
			this.add(dl);
			
			tx.setSize(180, 30);
			tx.setBackground(null);
			tx.setLocation(0, 0);
			this.add(tx);
			
		}
		class text extends JPanel{
			public void paint(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				g.setColor(gp.cl);
				g.fillRect(0, 0, 180, 3);
				g.setColor(txColor);
				g.setFont(txf);
				g.drawString(name, 10, 20);
			}
		}
		class del extends JButton{
			public void paint(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				//g.setColor(new Color(80,200,255));
				//((Graphics2D)g).setStroke(new BasicStroke(3));
				//g.drawLine(1, 1, 14, 14);
				//g.drawLine(14, 1, 1, 14);
//				g.drawLine(6, 0, 8, 0);
//				g.drawLine(0, 1, 14, 1);
//				g.drawLine(2, 3, 2, 13);
//				g.drawLine(2, 13, 4, 15);
//				g.drawLine(4, 15, 10, 15);
//				g.drawLine(10, 15, 12, 13);
//				g.drawLine(12, 13, 12, 3);
//				g.drawLine(5, 4, 5, 12);
//				g.drawLine(7, 4, 7, 12);
//				g.drawLine(9, 4, 9, 12);
				g.setColor(txColor);
				g.drawLine(6, 2, 8, 2);
				g.drawLine(2, 3, 12, 3);
				g.drawLine(3, 5, 3, 13);
				g.drawLine(4, 14, 10, 14);
				g.drawLine(11, 5, 11, 13);
				g.drawLine(5, 6, 5, 12);
				g.drawLine(7, 6, 7, 12);
				g.drawLine(9, 6, 9, 12);
			}
		}
	}
	public class oper extends JPanel{
		
		add add=new add();
		public oper() {
			this.setLayout(null);
			
			add.setSize(15, 15);
			add.setLocation(0, 0);
			//add.setBackground(getBackground());
			this.add(add);
			
			add.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent arg0) {

					String name=javax.swing.JOptionPane.showInputDialog("命名组：");
					for(GroupManager.group gp:Main.gm.groups) {
						if(gp.name.equals(name)) {
							Main.gui.bgp.ib.setTip("已存在名为:"+name+"的组.");
							Main.showTip("已存在名为:"+name+"的组", Main.ERROR);
							return;
						}
					}
					if(name==null||name.equals("")) {
						Main.gui.bgp.ib.setTip("新建组操作被用户取消或输入为空");
						Main.showTip("操作已被取消或请输入名称", Main.ERROR);
						return;
					}
//					Main.gm.newGroup(name);
					Main.gui.sbp.st.addScene(name, "无描述");
					//如果是分组视图，则update
					if(Main.gui.bgp.ep.viewMode==Main.gui.bgp.ep.GROUP_VIEW) {
						Main.gui.bgp.ep.updateElement();
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
			});
		}
		class add extends JPanel{
			public void paint(Graphics g) {
				//g.setColor(this.getBackground());
				//g.fillRect(0, 0, this.getWidth(), this.getHeight());
				g.setColor(txColor);
				g.setFont(addf);
				g.drawString("+", 5, 14);
			}
		}
	}
	//public GroupEntry ge[];
	public int groupStart=0;
	public JPanel gbp=new JPanel();
	public oper op=new oper();
	public GroupPnl() {
		this.setLayout(null);
//		this.setSize(80, 200);
		this.setTitlex("分组关系");
		
		op.setSize(180, 14);
		op.setLocation(1, 20);
		this.add(op);
		
		gbp.setLayout(null);
		gbp.setSize(180,180);
		gbp.setLocation(0, 20);
		this.add(gbp);
		
		gbp.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent arg0) {
				if(arg0.getWheelRotation()==1) {//向下
					groupStart+=groupStart<Main.gm.groups.size()-6?1:0;
					//System.out.println(groupStart);
					updateGroupEntry();
				}else if(arg0.getWheelRotation()==-1) {
					groupStart-=groupStart==0?0:1;
					//System.out.println(groupStart);
					updateGroupEntry();
				}
				
			}
		});
		//this.updateGroupEntry();
	}
	public void updateGroupEntry() {
		
		gbp.removeAll();
		System.gc();
		gbp.setBackground(this.getBackground());
		GroupManager gm=Main.gm;
		for(int i=groupStart;i<gm.groups.size();i++) {
			//System.out.println("i="+1);
			GroupEntry ge=new GroupEntry(gm.groups.get(i).name
					,gm.groups.get(i));
			ge.setLocation(5,(i-groupStart-1)*(30+5));
			ge.setBackground(PnlColor.getColor(gbp.getBackground(), 1.4));
			ge.dl.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					for(Clip clp:Main.gui.bgp.ep.elms) {
						if(clp.groupId==ge.gp.uid) {
							clp.groupId=-1;
							Main.gui.sbp.sb.getShotByClipUID(clp.uid).sceneid=-1;
							Main.gui.sbp.sb.getShotByClipUID(clp.uid).seat=-1;
						}
					}
					Main.gm.groups.remove(gm.getGroup(ge.gp.uid));
					Main.gui.sbp.st.removeScene(Main.gui.sbp.st.getSceneByGroupUID(ge.gp.uid).uid);
					if(gm.groups.size()-groupStart<6&&groupStart>0) {
						groupStart--;
					}
					Main.gui.bgp.gp.updateGroupEntry();
					Main.gui.bgp.ep.updateElement();
					Main.gui.sbp.st.updateScene();
				}
			});
			op.setBackground(PnlColor.getColor(getBackground(), 1.6));
			op.setLocation(1, this.getHeight()-15);
			gbp.add(ge);
		}
		//Main.gui.bgp.ep.updateElement();
		Main.gui.bgp.repaint();
	}
	public void loadCfg() {
		try {
			this.setLocation(Integer.parseInt(Main.cfg.get("groupPnl.x").toString())
					,Integer.parseInt(Main.cfg.get("groupPnl.y").toString()));
		}catch(Exception e) {
			Log.record("Read cfg failed.");
			e.printStackTrace();
		}
	}
}
