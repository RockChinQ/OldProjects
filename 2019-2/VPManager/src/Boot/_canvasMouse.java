package Boot;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class _canvasMouse implements MouseListener, MouseMotionListener {
	static int vnum=0,anum=0,tnum=0;
	static Point pressPointOnPanel,pointAtStart=new Point();  //
	static Element pressOnElement;
	public void mouseClicked(MouseEvent e) {
		if(e.getButton()==1) {   //left
			Main.g.etp.save();
			if(Main.g.tlp.toolse.equals(Main.g.tlp.addv)) {
				Main.g.cvp.eles.add(new Element(Element.VIDEO,vnum
						,"videoClip-"+vnum,"",e.getPoint()));
				vnum++;
				Main.g.etp.show(Main.g.cvp.eles.get(Main.g.cvp.eles.size()-1));
			}else if(Main.g.tlp.toolse.equals(Main.g.tlp.adda)) {
				Main.g.cvp.eles.add(new Element(Element.AUDIO,anum
						,"audio-"+anum,"",e.getPoint()));
				anum++;
				Main.g.etp.show(Main.g.cvp.eles.get(Main.g.cvp.eles.size()-1));
			}else if(Main.g.tlp.toolse.equals(Main.g.tlp.addt)) {
				Main.g.cvp.eles.add(new Element(Element.TRANSITION,tnum
						,"transition-"+tnum,"",e.getPoint()));
				tnum++;
				Main.g.etp.show(Main.g.cvp.eles.get(Main.g.cvp.eles.size()-1));
			}else if(Main.g.tlp.toolse.equals(Main.g.tlp.link)) {
				
			}else if(Main.g.tlp.toolse.equals(Main.g.tlp.pointer)) {
				int x=e.getX(),y=e.getY(),w=Main.g.ccp.ewidth,h=Main.g.ccp.eheight;
				int i=0;
				for(i=0;i<Main.g.cvp.eles.size();i++) {
					Element et=Main.g.cvp.eles.get(i);
					if((x>=et.location.x&&x<=et.location.x+w)
							&&(y>=et.location.y&&y<=et.location.y+h)) {
						Main.g.etp.show(et);
						break;
					}
				}
			}else if(Main.g.tlp.toolse.equals(Main.g.tlp.del)) {
				int x=e.getX(),y=e.getY(),w=Main.g.ccp.ewidth,h=Main.g.ccp.eheight;
				for(int i=Main.g.cvp.eles.size()-1;i>0;i--) {
					Element et=Main.g.cvp.eles.get(i);
					if((x>=et.location.x&&x<=et.location.x+w)
							&&(y>=et.location.y&&y<=et.location.y+h)) {
						Main.g.cvp.eles.remove(i);
						break;
					}
				}
			}
			Main.g.mf.setVisible(true);
			Main.g.mf.repaint();
		}
	}
	public void mouseEntered(MouseEvent e) {
		if(Main.g.tlp.toolse.equals(Main.g.tlp.pointer))
			Main.g.mf.setCursor(Cursor.MOVE_CURSOR);
	}
	public void mouseExited(MouseEvent e) {
		Main.g.mf.setCursor(Cursor.DEFAULT_CURSOR);
	}
	public void mousePressed(MouseEvent e) {
		Main.change=true;
		
		_canvasMouse.pressPointOnPanel=e.getPoint();
		if(Main.g.tlp.toolse.equals(Main.g.tlp.pointer)) {
			int i=0,w=Main.g.ccp.ewidth,h=Main.g.ccp.eheight
					,x=e.getX(),y=e.getY();
			for(i=0;i<Main.g.cvp.eles.size();i++) {
				Element et=Main.g.cvp.eles.get(i);
				if((x>=et.location.x&&x<=et.location.x+w)
						&&(y>=et.location.y&&y<=et.location.y+h)) {
					_canvasMouse.pressOnElement=et;
					_canvasMouse.pointAtStart.setLocation(et.location.x, et.location.y);
				}
			}
		}
	}
	public void mouseReleased(MouseEvent e) {
		if(Main.g.tlp.toolse.equals(Main.g.tlp.pointer)) {
			if(this.pressOnElement==null) {
				int dx=e.getX()-this.pressPointOnPanel.x,dy=e.getY()-this.pressPointOnPanel.y;
				for(int i=0;i<Main.g.cvp.eles.size();i++) {
					Main.g.cvp.eles.get(i).location.setLocation(Main.g.cvp.eles.get(i)
							.location.x+dx
							,Main.g.cvp.eles.get(i).location.y+dy);
				}
				if(Main.g.etp.e!=null) {
					Main.g.etp.save();
					Main.g.etp.e=null;
				}
				Main.g.mf.repaint();
			}
		}
		this.pressOnElement=null;
		this.pressPointOnPanel=null;
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		if(Main.g.tlp.toolse.equals(Main.g.tlp.pointer)) {
			int dx=arg0.getX()-this.pressPointOnPanel.x,dy=arg0.getY()-this.pressPointOnPanel.y;
			if(this.pressOnElement!=null) {
				this.pressOnElement.location.setLocation(this.pointAtStart.x+dx,this.pointAtStart.y+dy);
				Main.g.etp.setVisible(false);
			}
			Main.g.mf.repaint();
		}
	}
	public void mouseMoved(MouseEvent arg0) {}

}
