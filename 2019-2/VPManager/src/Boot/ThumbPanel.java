package Boot;

import java.awt.*;

import javax.swing.JPanel;

public class ThumbPanel extends JPanel{
	int bx=0,by=0,ex=0,ey=0,thumbscale=2;
	ThumbPanel(){
		this.addMouseListener(new _thumbMouse());
		this.addMouseMotionListener(new _thumbMouse());
		this.setSize(200, 150);
	}
	public void paint(Graphics g) {
		g.setColor(new Color(190,190,190));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.white);
		((Graphics2D)g).setStroke(new BasicStroke(3));
		g.drawRect(6, 12,this.getWidth()-12,this.getHeight()-17);
		g.drawImage(Toolkit.getDefaultToolkit().getImage("Files\\Image\\drag.png")
				,0,0,null, this);
		//w=12 h=4
		int zoom=Main.g.ccp.zoom,ewidth=60,eheight=20;
		double sw=(double)ewidth/(double)Main.g.ccp.ewidth
				,sh=(double)eheight/(double)Main.g.ccp.eheight;
		int cx=0,cy=0;
		for(int i=0;i<Main.g.cvp.eles.size();i++) {
			Element e=Main.g.cvp.eles.get(i);
			Point l=e.location,c=new Point(Main.g.cvp.getWidth()/2,Main.g.cvp.getHeight()/2);
			Point newl=new Point();
			newl.setLocation(c.x+((double)l.x-(double)c.x)*sw-bx
					, c.y+((double)l.y-(double)c.y)*sh-by);
			if(e.type==Element.VIDEO) {
				g.setColor(new Color(255,127,39));
			}else if(e.type==Element.AUDIO) {
				g.setColor(new Color(34,177,76));
			}else if(e.type==Element.TRANSITION) {
				g.setColor(new Color(127,127,127));
			}else if(e.type==Element.INFO) {
				g.setColor(new Color(0,162,232));
			}
			g.fillRect(newl.x/thumbscale+9,newl.y/thumbscale+16,60/thumbscale,20/thumbscale);
			g.setColor(Color.white);
			g.setFont(new Font("",Font.PLAIN,10));
			g.drawString(e.num+"",newl.x/thumbscale+60/thumbscale/2+9
					,newl.y/thumbscale+20/thumbscale/2+3+16);
			if(i==0) {
				cx=(newl.x-c.x)/5;
				cy=(newl.y-c.y)/5;
			}
		}
		g.setColor(new Color(240,240,240));
		g.fillRect(cx-2, cy-2, 4, 4);
	}
	public void resize() {
		int ex=0,ey=0,bx=Main.g.cvp.getWidth(),by=Main.g.cvp.getHeight();
		int zoom=Main.g.ccp.zoom,ewidth=60,eheight=20;
		double sw=(double)ewidth/(double)Main.g.ccp.ewidth
				,sh=(double)eheight/(double)Main.g.ccp.eheight;
		for(int i=0;i<Main.g.cvp.eles.size();i++) {
			Element e=Main.g.cvp.eles.get(i);
			Point l=e.location,c=new Point(Main.g.cvp.getWidth()/2,Main.g.cvp.getHeight()/2);
			Point newl=new Point();
			newl.setLocation(c.x+((double)l.x-(double)c.x)*sw
					, c.y+((double)l.y-(double)c.y)*sh);
			bx=newl.x<bx?newl.x:bx;
			by=newl.y<by?newl.y:by;
			ex=newl.x+60>ex?newl.x+60:ex;
			ey=newl.y+20>ey?newl.y+20:ey;
		}
		int w=(ex-bx)/thumbscale+18,h=(ey-by)/thumbscale+24;
		this.setSize(w,h);
		this.bx=bx;
		this.by=by;
		this.ex=ex;
		this.ey=ey;
		
	}
}
