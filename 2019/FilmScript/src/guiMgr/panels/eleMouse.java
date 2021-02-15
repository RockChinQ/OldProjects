package guiMgr.panels;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import boot.Main;
import guiMgr.panels.EditingPnl.Clip;
import log.Log;

public class eleMouse implements MouseListener, MouseMotionListener {
	static Point dragStart=new Point(0,0),clpLocation=new Point(0,0);
	static int length=0;
	static int pressedEleTrackStart=0;//记录了被点击的素材最开始的轨道标号
	EditingPnl ep;
	Clip eClp;
	int pressButton=0;
	boolean resizing=false;
	int resizeMode=-1;
	public void mouseClicked(MouseEvent e) {
		EditingPnl.Clip clp=(EditingPnl.Clip)e.getSource();
		if(Main.gui.bgp.tp.toolSelected==Main.gui.bgp.tp.sl) {
			clp.chosen=true;
			clp.repaint();
		}
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
		EditingPnl.Clip clp=(EditingPnl.Clip)e.getSource();
		//剪刀
		if(Main.gui.bgp.tp.toolSelected==Main.gui.bgp.tp.sc) {
			Main.gui.bgp.ep.cl.setLocation(-5, 0);
			//Log.record("x,y:"+(clp.getX()+arg0.getX())+",1"+" height:"+Main.gui.bgp.ep.cl.getHeight());
		}else if(Main.gui.bgp.tp.toolSelected==Main.gui.bgp.tp.sl) {
			
			Main.gui.mwd.setCursor(Cursor.DEFAULT_CURSOR);
		}
		Main.gui.bgp.repaint();
	}
	public void mousePressed(MouseEvent e) {
		pressButton=e.getButton();
		EditingPnl.Clip clp=(EditingPnl.Clip)e.getSource();
		eClp=clp;
		//右键菜单
		if(e.getButton()==3) {
			//System.out.println("set");
			this.selectEle(clp.uid,e);
			if(Main.gui.bgp.ep.em.eClp.linkId!=-1) {//将所有已选的素材的链接组删除（设为-1）
				Main.gui.bgp.ep.emn.link.setLabel("取消链接");
			}else {
				boolean isAllChosenUnlinked=true;
				int chosenCount=0;
				for(Clip cp:Main.gui.bgp.ep.elms) {
					if(cp.chosen)
						chosenCount++;
					if(cp.chosen&&cp.linkId!=-1) {//有已选的为有链接组的
						isAllChosenUnlinked=false;
						break;
					}
				}
				if(isAllChosenUnlinked) {
					//System.out.println("set link");
					Main.gui.bgp.ep.emn.link.setLabel("链接");
				}else {
					//System.out.println("set unlink");
					Main.gui.bgp.ep.emn.link.setLabel("取消链接");
				}
			}
			
			Main.gui.bgp.ep.updateElement();
			Main.gui.bgp.repaint();
			Main.gui.bgp.ep.emn.addGroupsMenuItem();
			Main.gui.bgp.ep.emn.show(Main.gui.bgp.ep, clp.getX()+e.getX(), e.getY()+clp.getY());
		}
		//选择素材
		//在此将已选的素材添加到未选的素材之上
		if(e.getButton()==1) {
			if(Main.gui.bgp.tp.toolSelected==Main.gui.bgp.tp.sl) {
				this.selectEle(clp.uid,e);
			}else if(Main.gui.bgp.tp.toolSelected==Main.gui.bgp.tp.sc) {
				if(Main.gui.bgp.ep.cutClipApart(clp, (int)(e.getX()/Main.gui.bgp.ep.zoom))!=-1)
					Main.gui.bgp.ep.updateElement();
			}
		}
	}
	public void mouseReleased(MouseEvent e) {
		if(Main.gui.bgp.ep.viewMode==Main.gui.bgp.ep.TIMELINE_VIEW) {
			//更新帧数
			EditingPnl.Clip clp=(EditingPnl.Clip)e.getSource();
			if(!resizing&&Main.gui.bgp.tp.toolSelected==Main.gui.bgp.tp.sl) {
				int dx=clp.getX()-clpLocation.x;
				int dp=(int) (dx/Main.gui.bgp.ep.zoom);
				for(int i=0;dp!=0&&i<Main.gui.bgp.ep.elms.size();i++) {
					EditingPnl.Clip clp1=Main.gui.bgp.ep.elms.get(i);
					if(clp1.chosen) {
						clp1.x=clp1.x+dp>=0?clp1.x+dp:0;
						Main.gui.bgp.ep.solveAllConflict(clp1);
					}
				}
				if(dp!=0)
					Main.gui.bgp.ep.updateElement();
				Main.gui.bgp.ib.setTip("就绪");
			}else if(resizing) {
				for(Clip cp:Main.gui.bgp.ep.elms) {
					if(cp.chosen)
						Main.gui.bgp.ep.solveAllConflict(cp);
				}
				this.resizeMode=-1;
				Main.gui.bgp.ib.setTip("就绪");
				Main.gui.bgp.ep.updateElement();
			}
			resizing=false;
		}
	}
	public void mouseDragged(MouseEvent arg0) {
		if(Main.gui.bgp.ep.viewMode==Main.gui.bgp.ep.TIMELINE_VIEW) {
			if(pressButton==1) {
				if(Main.gui.bgp.tp.toolSelected==Main.gui.bgp.tp.sl) {//移动素材的监听器
					//System.out.println("dragged");
					int dx=arg0.getXOnScreen()-dragStart.x;
					//System.out.println("   "+arg0.getXOnScreen()+" "+dragStart.x);
					EditingPnl tep=Main.gui.bgp.ep;
					
					if(resizing) {//修剪
						//验证是否有素材在本次操作之后会变成1
						//或者有素材x小于0
						boolean willDo=true;
						for(Clip clp:Main.gui.bgp.ep.elms) {
							if(clp.chosen) {
								if((resizeMode==0&&clp.temp_length-EditingPnl.getFrameByPixel(dx)+Main.gui.bgp.ep.tmlnStart<1)
										||(resizeMode==1&&clp.temp_length+EditingPnl.getFrameByPixel(dx)-Main.gui.bgp.ep.tmlnStart<1)) {
									//System.out.println("clp "+clp.title+" l now "+clp.length);
									Main.gui.bgp.ib.setTip("到达修剪极限:修剪最小长度为1帧 ["+clp.title+"("+clp.uid+")]");
									Main.showTip("到达修剪极限:修剪最小长度为1帧 ["+clp.title+"("+clp.uid+")]", Main.WARNING);
									willDo=false;
								}else if(resizeMode==0) {
									if(EditingPnl.getFrameByPixel(arg0.getXOnScreen()-dragStart.x+clp.temp_location.x)<0) {
										willDo=false;
									}
								}
							}
						}
						if(willDo) {
							Main.gui.bgp.ib.setTip("修剪"+(dx>=0?"+":"")+(EditingPnl.getFrameByPixel(dx)-Main.gui.bgp.ep.tmlnStart));
							Main.showTip("修剪"+(dx>=0?"+":"")+(EditingPnl.getFrameByPixel(dx)-Main.gui.bgp.ep.tmlnStart), Main.MESSAGE);
							for(Clip clp:Main.gui.bgp.ep.elms) {
								if(resizeMode==0&&clp.chosen) {
									clp.length=clp.temp_length-EditingPnl.getFrameByPixel(dx)+Main.gui.bgp.ep.tmlnStart;
									clp.x=EditingPnl.getFrameByPixel(arg0.getXOnScreen()-dragStart.x+clp.temp_location.x);
									
								}else if(resizeMode==1&&clp.chosen) {
									clp.length=clp.temp_length+EditingPnl.getFrameByPixel(dx)-Main.gui.bgp.ep.tmlnStart;
								
								}
								Main.gui.bgp.ep.solveAllConflict(clp);
							}
						}
						//System.out.println(eClp.x+" "+eClp.length);
						Main.gui.bgp.ep.updateElement();
					}else if(!resizing) {
						/*
						挨个设置已选择的素材的位置
					 	*/
						for(int i=0;i<Main.gui.bgp.ep.elms.size();i++) {
							EditingPnl.Clip clp=Main.gui.bgp.ep.elms.get(i);
							if(clp.chosen) {
								int x=(int) ((clp.x-tep.tmlnStart)*tep.zoom),y=0;
								if(clp.type==Clip.AUDIO)    //音频置于时间线下方
									y=clp.track*(tep.eleHeight+5)+tep.getHeight()/2+30;
								else
									y=tep.getHeight()/2-30-clp.track*(tep.eleHeight+5)-tep.eleHeight;
								clp.setLocation(x+dx, y+tep.centreLineDeltaY);
								//System.out.println(x+" "+dx);
								//注意此处未更新帧数
								//在下方更新了
								//可能被删了
							}
						}
						Main.gui.bgp.ib.setTip("移动素材:"+(int)(dx/tep.zoom)+" 帧");
					}
				}
			}
		}
	}
	public void mouseMoved(MouseEvent arg0) {
		EditingPnl.Clip clp=(EditingPnl.Clip)arg0.getSource();
		if(Main.gui.bgp.ep.viewMode==Main.gui.bgp.ep.TIMELINE_VIEW) {
			//鼠标进入某一个素材
			//剪刀
			if(Main.gui.bgp.tp.toolSelected==Main.gui.bgp.tp.sc) {
				Main.gui.bgp.ep.cl.setVisible(true);
				Main.gui.bgp.ep.cl.resize();
				Main.gui.bgp.ep.cl.repaint();
				Main.gui.bgp.ep.cl.setLocation(EditingPnl.getPixelByFrame
						(EditingPnl.getFrameByPixel(clp.getX()+arg0.getX())), clp.getY());
				Main.gui.bgp.repaint();
				//Log.record("x,y:"+(clp.getX()+arg0.getX())+",1"+" height:"+Main.gui.bgp.ep.cl.getHeight());
			}else if(Main.gui.bgp.tp.toolSelected==Main.gui.bgp.tp.sl) {
				if(arg0.getX()<=5||arg0.getX()>=clp.getWidth()-5) {
					resizing=true;
					if(arg0.getX()<=5)
						this.resizeMode=0;
					else if(arg0.getX()>=clp.getWidth()-5)
						this.resizeMode=1;
					Main.gui.mwd.setCursor(Cursor.E_RESIZE_CURSOR);
				}else {
					this.resizeMode=-1;
					resizing=false;
					Main.gui.mwd.setCursor(Cursor.DEFAULT_CURSOR);
				}
				Main.gui.bgp.repaint();
			}
		}
	}
	
	
	public void selectEle(int uid,MouseEvent e) {
		//System.out.println("Selecting "+Main.gui.bgp.ep.elms.get(Main.gui.bgp.ep.indexClipByUID(uid)).title+" "+Main.gui.bgp.ep.elms.get(Main.gui.bgp.ep.indexClipByUID(uid)).uid);
		//System.out.print("lnk:"+Main.gui.bgp.ep.elms.get(Main.gui.bgp.ep.indexClipByUID(uid)).lnk.size()+" ");
		Clip clp=Main.gui.bgp.ep.elms.get(Main.gui.bgp.ep.indexClipByUID(uid));
		
		if(!clp.chosen) {
			for(int i=0;(!e.isShiftDown())&&i<Main.gui.bgp.ep.elms.size();i++) {
				Main.gui.bgp.ep.elms.get(i).chosen=false;
			}
			clp.chosen=true;
		}
		//选中所有已链接的
		//System.out.print("lnk:"+Main.gui.bgp.ep.elms.get(Main.gui.bgp.ep.indexClipByUID(uid)).lnk.size()+" ");
		//System.out.println(clp.linkId);
		for(int i=0;clp.linkId!=-1
				&&i<Main.gui.bgp.ep.elms.size();i++) {
			//System.out.println(i);
			if(Main.gui.bgp.ep.elms.get(i).linkId==clp.linkId)
				Main.gui.bgp.ep.elms.get(i).chosen=true;
		}
		//System.out.println();
		for(Clip cp:Main.gui.bgp.ep.elms) {
			if(cp.chosen) {
				cp.temp_length=cp.length;
				cp.temp_location=cp.getLocation();
			}
		}
		
		length=clp.length;
		dragStart.setLocation(e.getXOnScreen(),e.getYOnScreen());
		clpLocation.setLocation(clp.getLocation());
		this.pressedEleTrackStart=clp.track;
		//真的是拿算力来解决bug
		Main.gui.bgp.ep.updateEleLayer();
		Main.gui.bgp.ae.updateCom();
		//System.out.println(dragStart);
	}
}
