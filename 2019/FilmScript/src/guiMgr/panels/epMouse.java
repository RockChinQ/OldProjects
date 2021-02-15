package guiMgr.panels;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import boot.Main;
import guiMgr.panels.EditingPnl.Clip;

public class epMouse implements MouseWheelListener, MouseListener, MouseMotionListener{
	EditingPnl ep;
	Boolean addingVdo=false,addingAdo=false,addingVaa=false;
	public epMouse(EditingPnl ep){
		this.ep=ep;
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		if(Main.gui.bgp.ep.viewMode==Main.gui.bgp.ep.TIMELINE_VIEW) {
			if(arg0.isAltDown()) {
				if(arg0.getWheelRotation()==-1&&ep.zoom<32) {
					ep.zoom*=2;
					//System.out.println("1111");
				}else if(arg0.getWheelRotation()==1&&ep.zoom>=0.0625) {
					ep.zoom=ep.zoom/2>0.0625?ep.zoom/2:0.0625;
				}
				//System.out.println(ep.zoom);
				//Main.gui.bgp.ep.requestFocus();
			}else if(arg0.isControlDown()){
				if(arg0.getWheelRotation()==-1) {
					ep.add+=ep.eleHeight;
				}else if(arg0.getWheelRotation()==1) {
					ep.add-=ep.eleHeight;
				}
			}else {
				int add=(int) ((160/Main.gui.bgp.ep.zoom));
				if(arg0.getWheelRotation()==1&&(double)ep.tmlnStart+(double)this.ep.getWidth()/ep.zoom<(double)ep.maxX+1000) {
					this.ep.tmlnStart+=add;
				}else if(arg0.getWheelRotation()==-1) {
					//this.ep.tmlnStart-=add;
					this.ep.tmlnStart=(this.ep.tmlnStart-add>=0?this.ep.tmlnStart-add:0);
				}
			}
		}else if(Main.gui.bgp.ep.viewMode==Main.gui.bgp.ep.GROUP_VIEW) {
			if(arg0.isControlDown()){
				if(arg0.getWheelRotation()==-1) {
					ep.addY+=ep.GROUP_VIEW_ELE_HEIGHT;
					//ep.gt.addY+=ep.GROUP_VIEW_ELE_HEIGHT;
					ep.gt.setLocation(ep.gt.getX(), ep.gt.getY()+ep.GROUP_VIEW_ELE_HEIGHT);
				}else if(arg0.getWheelRotation()==1) {
					ep.addY-=ep.GROUP_VIEW_ELE_HEIGHT;
					//ep.gt.addY-=ep.GROUP_VIEW_ELE_HEIGHT;
					ep.gt.setLocation(ep.gt.getX(), ep.gt.getY()-ep.GROUP_VIEW_ELE_HEIGHT);
				}
			}else {
				//int add=(int) ((160/Main.gui.bgp.ep.zoom));
				if(arg0.getWheelRotation()==-1) {
					//this.ep.tmlnStart+=add;
					this.ep.addX+=50;
					//this.ep.gt.addX+=50;
					//System.out.println(ep.gt.getX());
					ep.gt.setLocation(ep.gt.getX()+50, ep.gt.getY());
					//System.out.println(ep.gt.getLocation());
				}else if(arg0.getWheelRotation()==1) {
					//this.ep.tmlnStart-=add;
					//this.ep.tmlnStart=(this.ep.tmlnStart-add>=0?this.ep.tmlnStart-add:0);
					ep.addX-=50;
					//ep.gt.addX-=50;
					//System.out.println(ep.gt.getX());
					ep.gt.setLocation(ep.gt.getX()-50, ep.gt.getY());
					//System.out.println(ep.gt.getLocation());
				}
			}
		}
		this.ep.updateElement();
		//Main.gui.bgp.repaint();
	}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {
		//System.out.println("?????");
		Main.gui.bgp.ep.cl.setVisible(false);
	}
	public void mousePressed(MouseEvent e) {
		Main.gui.bgp.ep.setFocusable(true);
		Main.gui.bgp.ep.requestFocus();
		ep.pressPoint.setLocation(e.getPoint());
		this.ep.cr.setLocation(e.getPoint());
		if(Main.gui.bgp.tp.toolSelected==Main.gui.bgp.tp.sl) {//选择工具
			for(int i=0;i<this.ep.elms.size();i++) {
				this.ep.elms.get(i).chosen=false;
			}
		}else if(Main.gui.bgp.tp.toolSelected==Main.gui.bgp.tp.adv) {//添加影像
			//System.out.println(e.getX()+" "+Main.gui.bgp.ep.whichTrack(e.getYOnScreen()));
			this.addingVdo=false;
			int track=Main.gui.bgp.ep.whichTrack(e.getYOnScreen());
			if(track==-1||Main.gui.bgp.ep.trackType(e.getYOnScreen())!=EditingPnl.Clip.VDO_TRACK) {
				return;
			}
			this.addingVdo=true;
			Main.gui.bgp.ep.addElement("VideoClip",EditingPnl.Clip.VIDEO
					,0,track,EditingPnl.getFrameByPixel(e.getX()),null);
			Main.gui.bgp.ep.updateElement();
		}else if(Main.gui.bgp.tp.toolSelected==Main.gui.bgp.tp.ada) {//添加音频
			this.addingAdo=false;
			int track=Main.gui.bgp.ep.whichTrack(e.getYOnScreen());
			if(track==-1||Main.gui.bgp.ep.trackType(e.getYOnScreen())!=EditingPnl.Clip.ADO_TRACK) {
				return;
			}
			this.addingAdo=true;
			Main.gui.bgp.ep.addElement("AudioClip", EditingPnl.Clip.AUDIO, 
					0, track, EditingPnl.getFrameByPixel(e.getX()),null);
			Main.gui.bgp.ep.updateElement();
		}else if(Main.gui.bgp.tp.toolSelected==Main.gui.bgp.tp.ava) {//添加影音
			
		}
	}
	public void mouseReleased(MouseEvent e) {
		ep.cr.setVisible(false);
		if(Main.gui.bgp.tp.toolSelected==Main.gui.bgp.tp.adv&&addingVdo) {//添加影像
			this.addingVdo=false;
			if(Main.gui.bgp.ep.elms.get(Main.gui.bgp.ep.elms.size()-1).length<=0) {
				Main.gui.sbp.sb.removeShot(Main.gui.bgp.ep.elms.get(Main.gui.bgp.ep.elms.size()-1));
				Main.gui.bgp.ep.elms.remove(Main.gui.bgp.ep.elms.size()-1);

			}else {
				Main.gui.bgp.ep.unselectAllClip();
				Main.gui.bgp.ep.elms.get(Main.gui.bgp.ep.elms.size()-1).chosen=true;
				Main.gui.bgp.ep.solveAllConflict(Main.gui.bgp.ep.elms.get(Main.gui.bgp.ep.elms.size()-1));
				Main.gui.bgp.ep.updateElement();
			}
		}else if(Main.gui.bgp.tp.toolSelected==Main.gui.bgp.tp.ada&&addingAdo) {//添加音频
			this.addingAdo=false;
			if(Main.gui.bgp.ep.elms.get(Main.gui.bgp.ep.elms.size()-1).length<=0) {
				Main.gui.bgp.ep.elms.remove(Main.gui.bgp.ep.elms.size()-1);
			}else {
				Main.gui.bgp.ep.unselectAllClip();
				Main.gui.bgp.ep.elms.get(Main.gui.bgp.ep.elms.size()-1).chosen=true;
				Main.gui.bgp.ep.solveAllConflict(Main.gui.bgp.ep.elms.get(Main.gui.bgp.ep.elms.size()-1));
				Main.gui.bgp.ep.updateElement();
			}
		}else if(Main.gui.bgp.tp.toolSelected==Main.gui.bgp.tp.ava) {//添加影音
			
		}
		Main.gui.bgp.ep.updateMaxX();
		Main.gui.bgp.ae.updateCom();
		Main.gui.mwd.repaint();
	}
	
	public void mouseDragged(MouseEvent e) {
		ToolPnl tp=Main.gui.bgp.tp;
		if(tp.toolSelected==tp.sl) {//选择
			Point crlc=ep.cr.getLocation();
			//System.out.println((e.getX()-pressPoint.getX())+"  "+(e.getY()-pressPoint.getY()));
			this.ep.cr.setSize((int)Math.abs(e.getX()-ep.pressPoint.getX())
					, (int)Math.abs(e.getY()-ep.pressPoint.getY()));
			if(e.getX()-ep.pressPoint.getX()<0) {
				crlc.setLocation(e.getX(), crlc.getY());
			}
			if(e.getY()-ep.pressPoint.getY()<0) {
				crlc.setLocation(crlc.getX(), e.getY());
			}
			ep.cr.setLocation(crlc);
			this.ep.cr.setVisible(true);
			ep.cr.repaint();
			//System.out.println();
			Set<Integer> linkId=new HashSet<Integer>();
			for(int i=0;i<this.ep.elms.size();i++) {
				Clip tc=this.ep.elms.get(i);
				//System.out.print(tc.title+":");
				if((ep.cr.getX()<tc.getX()+tc.getWidth()&&ep.cr.getY()<tc.getY()+tc.getHeight()
						&&ep.cr.getX()>tc.getX()&&ep.cr.getY()>tc.getY())) {  //clip在左上 右上 左下 右下
					selectEle(tc);
					linkId.add(tc.linkId);
					//System.out.println("0");
				}else if((ep.cr.getX()+ep.cr.getWidth()>tc.getX()&&ep.cr.getY()<tc.getY()+tc.getHeight()
						&&ep.cr.getX()<tc.getX()&&ep.cr.getY()>tc.getY())){
					selectEle(tc);
					linkId.add(tc.linkId);
					//System.out.println("1");
				}else if((ep.cr.getX()<tc.getX()+tc.getWidth()&&ep.cr.getY()+ep.cr.getHeight()>tc.getY()
						&&ep.cr.getX()>tc.getX()&&ep.cr.getY()<tc.getY())){
					selectEle(tc);
					linkId.add(tc.linkId);
					//System.out.println("2");
				}else if((ep.cr.getX()+ep.cr.getWidth()>tc.getX()&&ep.cr.getY()+ep.cr.getHeight()>tc.getY()
						&&ep.cr.getX()<tc.getX()&&ep.cr.getY()<tc.getY())){
					selectEle(tc);
					linkId.add(tc.linkId);
					//System.out.println("3");
				}else {
					if(linkId.contains(tc.linkId)&&tc.linkId!=-1) {
						continue;
					}else {
						tc.chosen=false;
					}
				}
				/*
				 * if(tc.chosen) {//添加到窗口上层
				 * 
				 * Main.gui.mwd.getLayeredPane().add(tc,new Integer(Integer.MAX_VALUE)); }
				 */
				
			}
			//System.out.println(new Date().getTime());
			Main.gui.mwd.repaint();
			//System.out.println(new Date().getTime());
		}else if(Main.gui.bgp.tp.toolSelected==Main.gui.bgp.tp.adv) {//添加影像
			if(!this.addingVdo)
				return;
			Main.gui.bgp.ep.elms.get(Main.gui.bgp.ep.elms.size()-1)
			.length=EditingPnl.getFrameByPixel(e.getX())-
			EditingPnl.getFrameByPixel(Main.gui.bgp.ep.pressPoint.x);
			Main.gui.bgp.ep.updateElement();
		}else if(Main.gui.bgp.tp.toolSelected==Main.gui.bgp.tp.ada) {//添加音频
			if(!this.addingAdo)
				return;
			Main.gui.bgp.ep.elms.get(Main.gui.bgp.ep.elms.size()-1)
			.length=EditingPnl.getFrameByPixel(e.getX())-
			EditingPnl.getFrameByPixel(Main.gui.bgp.ep.pressPoint.x);
			Main.gui.bgp.ep.updateElement();
		}else if(Main.gui.bgp.tp.toolSelected==Main.gui.bgp.tp.ava) {//添加影音
			
		}
	}
	public void mouseMoved(MouseEvent e) {
		if(Main.gui.bgp.tp.toolSelected==Main.gui.bgp.tp.adv||
				Main.gui.bgp.tp.toolSelected==Main.gui.bgp.tp.ada||
				Main.gui.bgp.tp.toolSelected==Main.gui.bgp.tp.ava) {
			int eleHeight=Main.gui.bgp.ep.eleHeight;
			int type=Main.gui.bgp.ep.trackType(e.getYOnScreen())
					,track=Main.gui.bgp.ep.whichTrack(e.getYOnScreen());
			
			int x=EditingPnl.getPixelByFrame(EditingPnl.getFrameByPixel(e.getX())),y=0;
			if(type==Clip.AUDIO)    //音频置于时间线下方
				y=track*(eleHeight+5)+Main.gui.bgp.ep.getHeight()/2+30;
			else
				y=Main.gui.bgp.ep.getHeight()/2-30-track*(eleHeight+5)-eleHeight;
			Main.gui.bgp.ep.cl.setLocation(x, y+Main.gui.bgp.ep.centreLineDeltaY);
			Main.gui.bgp.ep.cl.setVisible(true);
			Main.gui.bgp.ep.cl.resize();
			Main.gui.bgp.repaint();
			//Main.gui.bgp.repaint();
			//System.out.println(x+" "+(y+Main.gui.bgp.ep.centreLineDeltaY)+"  "+e.getX()+","+e.getY());
		}else if(Main.gui.bgp.tp.toolSelected!=Main.gui.bgp.tp.sc){
			Main.gui.bgp.ep.cl.setVisible(false);
		}
	}
	public void selectEle(Clip clp) {
		clp.chosen=true;
		if(clp.linkId==-1)
			return;
		for(Clip cp:Main.gui.bgp.ep.elms) {
			if(cp.linkId==clp.linkId)
				cp.chosen=true;
		}
	}
}
