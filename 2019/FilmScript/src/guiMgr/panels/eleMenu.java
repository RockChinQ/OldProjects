package guiMgr.panels;

import java.awt.CheckboxMenuItem;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.alibaba.fastjson.JSONObject;

import boot.Main;
import data.GroupManager;
import guiMgr.panels.EditingPnl.Clip;

public class eleMenu extends JPopupMenu implements ActionListener {
	JMenuItem link;
	JMenu groupMn;
	JMenuItem newg=new JMenuItem("新建分组"),del=new JMenuItem("删除"),wave_del=new JMenuItem("波纹删除");
	JCheckBoxMenuItem none=new JCheckBoxMenuItem("无分组");
	JCheckBoxMenuItem gr[];
	JMenuItem copy=new JMenuItem("复制"),cut=new JMenuItem("剪切");
	public eleMenu() {
		link=new JMenuItem("链接/取消链接");
		link.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				foundAndBreak:for(int i=0;i<Main.gui.bgp.ep.elms.size();i++) {
//					if(Main.gui.bgp.ep.elms.get(i).chosen) {
//						if(Main.gui.bgp.ep.elms.get(i).lnk.size()!=0) {//取消链接
//							//System.out.println("del");
//							for(int j=0;j<Main.gui.bgp.ep.elms.get(i).lnk.size();j++) {
//								if(j==i)//跳过自己
//									continue;
//								Main.gui.bgp.ep.elms.get(j).chosen=false;
//								Main.gui.bgp.ep.elms.get(j).lnk=new ArrayList<Integer>();
//							}
//							Main.gui.bgp.ep.elms.get(i).lnk=new ArrayList<Integer>();
//						}else if(Main.gui.bgp.ep.elms.get(i).lnk.size()==0) {//添加
//							for(int j=0;j<Main.gui.bgp.ep.elms.size();j++) {//容器
//								if(Main.gui.bgp.ep.elms.get(j).chosen) {
//									for(int k=0;k<Main.gui.bgp.ep.elms.size();k++) {//被加
//										if(Main.gui.bgp.ep.elms.get(k).chosen) {
//											Main.gui.bgp.ep.elms.get(j).lnk.add(Main.gui.bgp.ep.elms.get(k).uid);
//											
//										}
//									}
//								}
//							}
//							
//							break foundAndBreak;
//							
//						}
//					}
//				}
				
				if(Main.gui.bgp.ep.em.eClp.linkId!=-1) {//将所有已选的素材的链接组删除（设为-1）
					for(Clip clp:Main.gui.bgp.ep.elms) {
						if(clp.chosen)
							clp.linkId=-1;
					}
				}else {
					boolean isAllChosenUnlinked=true;
					int chosenCount=0;
					for(Clip clp:Main.gui.bgp.ep.elms) {
						if(clp.chosen)
							chosenCount++;
						if(clp.chosen&&clp.linkId!=-1) {//有已选的为有链接组的
							isAllChosenUnlinked=false;
							break;
						}
					}
					if(isAllChosenUnlinked) {
						if(chosenCount==1)//如果只选了一个
							return;
						int linkIdTemp=LinkManage.linkIndex++;
						for(Clip clp:Main.gui.bgp.ep.elms) {
							if(clp.chosen)
								clp.linkId=linkIdTemp;
						}
					}else {
						for(Clip clp:Main.gui.bgp.ep.elms) {
							if(clp.chosen)
								clp.linkId=-1;
						}
					}
				}
				Main.gui.mwd.repaint();
			}
		});
		
		groupMn=new JMenu("设为分组");
		groupMn.add(none);
		none.setState(true);
		groupMn.add(newg);
		
		groupMn.addSeparator();
		
		this.add(link);
		this.add(groupMn);
		
		newg.addActionListener(this);
		//none.addActionListener(this);
		none.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				//System.out.println(1111111);
				for(Clip clp:Main.gui.bgp.ep.elms) {
					if(clp.chosen) {
						//System.out.println(clp.title);
						clp.groupId=-1;
						Main.gui.sbp.sb.getShotByClipUID(clp.uid).sceneid=-1;
						Main.gui.sbp.sb.getShotByClipUID(clp.uid).seat=-1;
					}
				}
				//如果是分组视图，则update
				if(Main.gui.bgp.ep.viewMode==Main.gui.bgp.ep.GROUP_VIEW) {
					Main.gui.bgp.ep.updateElement();
				}
				Main.gui.sbp.st.updateScene();
			}
		});
		//接下来是素材操作
		this.addSeparator();
		this.add(copy);
		copy.addActionListener(e->{
			Main.gui.bgp.ep.copy();
		});
		this.add(cut);
		cut.addActionListener(e->{
			Main.gui.bgp.ep.cut();
		});
		
		this.add(del);
		del.addActionListener(e-> {
			ArrayList<Clip> rem=new ArrayList<Clip>();
			for(Clip clp:Main.gui.bgp.ep.elms) {
				if(clp.chosen) {
					rem.add(clp);
				}
			}
			for(Clip vdo:rem) {
				Main.gui.sbp.sb.removeShot(vdo);
			}
			Main.gui.bgp.ep.elms.removeAll(rem);
			Main.gui.bgp.ae.updateCom();
			Main.gui.bgp.ep.updateElement();
		});
		this.add(wave_del);
		wave_del.addActionListener(e-> {
			ArrayList<Clip> rem=new ArrayList<Clip>();
			for(Clip clp:Main.gui.bgp.ep.elms) {
				if(clp.chosen) {
					rem.add(clp);
				}
			}
			for(Clip vdo:rem) {
//				Main.gui.sbp.sb.removeShot(vdo);
				Main.gui.sbp.sb.removeShot(Main.gui.sbp.sb.getShotByClipUID(vdo.uid).uid, null);
			}
			Main.gui.bgp.ep.elms.removeAll(rem);
			Main.gui.bgp.ae.updateCom();
			Main.gui.bgp.ep.updateElement();
		});
	}
	public void addGroupsMenuItem() {
		groupMn.removeAll();
		groupMn.add(none);
		none.setState(true);
		groupMn.add(newg);
		groupMn.addSeparator();
		boolean isAllChosenNoGroup=true;
		gr=new JCheckBoxMenuItem[Main.gm.groups.size()-1];
		for(int i=1;i<Main.gm.groups.size();i++) {//从1开始，0是无分组
			gr[i-1]=new JCheckBoxMenuItem(Main.gm.groups.get(i).name);
			int uid=Main.gm.groups.get(i).uid;
			boolean isAllChosenClipSameGroup=true;
			//验证所有已选素材是否都属于这个组
			for(Clip cp:Main.gui.bgp.ep.elms) {
				if(cp.chosen&&cp.groupId!=-1) {
					isAllChosenNoGroup=false;
				}
				if(cp.chosen&&cp.groupId!=uid) {
					isAllChosenClipSameGroup=false;
				}
			}
			if(isAllChosenClipSameGroup) {
				none.setState(false);
				gr[i-1].setState(true);
			}
			gr[i-1].addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					int uid=-1;
					//取得group对象
					for(GroupManager.group gp:Main.gm.groups) {
						if(gp.name==((JCheckBoxMenuItem)arg0.getSource()).getLabel()) {
							uid=gp.uid;
						}
					}
					//设置
					int scuid=Main.gui.sbp.st.getSceneByGroupUID(uid).uid;
					for(Clip clp:Main.gui.bgp.ep.elms) {
						if(clp.chosen) {
							clp.groupId=uid;
							Main.gui.sbp.sb.getShotByClipUID(clp.uid).sceneid=scuid;
							Main.gui.sbp.sb.getShotByClipUID(clp.uid).seat=-1;
						}
					}
					//如果是分组视图，则update
					if(Main.gui.bgp.ep.viewMode==Main.gui.bgp.ep.GROUP_VIEW) {
						Main.gui.bgp.ep.updateElement();
					}
					//刷新scenetable
					Main.gui.sbp.st.updateScene();
				}
			});
			groupMn.add(gr[i-1]);
		}
		if(!isAllChosenNoGroup) {
			none.setState(false);
		}
		
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==newg) {//新建
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
			int uid=Main.gui.sbp.st.addScene(name, "无描述");
			int scnid=Main.gui.sbp.st.getSceneByGroupUID(uid).uid;
			for(Clip clp:Main.gui.bgp.ep.elms) {
				if(clp.chosen) {
					clp.groupId=uid;
					Main.gui.sbp.sb.getShotByClipUID(clp.uid).sceneid=scnid;
					Main.gui.sbp.sb.getShotByClipUID(clp.uid).seat=-1;
				}
			}
			Main.gui.sbp.st.updateScene();
			//如果是分组视图，则update
			if(Main.gui.bgp.ep.viewMode==Main.gui.bgp.ep.GROUP_VIEW) {
				Main.gui.bgp.ep.updateElement();
			}
		}
	}
}
