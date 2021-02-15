package guiMgr.panels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import boot.Main;
import guiMgr.FloatPanel;
import guiMgr.PnlColor;
import guiMgr.fields.InputField;
import guiMgr.panels.EditingPnl.Clip;
import log.Log;

public class AttributeEdit extends FloatPanel {
	public class page extends JPanel{
		public page() {
			this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			this.setSize(90, 20);
			
			this.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent e) {
					if(page==CLIP_PAGE) {
						page=PROJECT_PAGE;
						updateCom();
					}else {
						page=CLIP_PAGE;
						updateCom();
					}
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
		}
		public void paint(Graphics g) {
			g.setColor(PnlColor.getColor(getBackground(), 1.2));
			if(page==CLIP_PAGE) {
				g.fillRect(3, 0, 45, 20);
			}else {
				g.fillRect(45, 0, 45, 20);
			}
			g.setColor(new Color(245,245,245));
			g.drawString("  素材   项目", 0, 13);
		}
	}
	public class ClipPg extends JPanel implements KeyListener{
		InputField name=new InputField("名称",150,26,0.195);
		InputField descri=new InputField("描述",175,26,0.16);
		ClipPg(){
			this.setLayout(null);

			name.setLocation(10, 10);
			this.add(name);
			descri.setLocation(10, 40);
			this.add(descri);
			
			name.input.addKeyListener(this);
			descri.input.addKeyListener(this);
			//name.requestFocus();
		}
		
		public void updateCom() {
			name.setBackground(getBackground());
			descri.setBackground(getBackground());
		}

		public void keyPressed(KeyEvent arg0) {
			//System.out.println(arg0.getKeyChar());
			if(arg0.getKeyCode()==KeyEvent.VK_ENTER) {
				now.title=name.getValue();
				now.description=descri.getValue();
				now.repaint();
			}
		}
		public void keyReleased(KeyEvent arg0) {
		}
		public void keyTyped(KeyEvent arg0) {
		}
	}
	public class ProjPg extends JPanel implements KeyListener{
		InputField fps=new InputField("项目帧率",90,26,0.62);
		String strbeforetyp="";
		ProjPg(){
			this.setLayout(null);
			
			fps.setLocation(10, 10);
			this.add(fps);
			fps.input.addKeyListener(this);
		}
		public void updateCom() {
			fps.setBackground(getBackground());
		}
		public void keyPressed(KeyEvent e) {
			this.strbeforetyp=new String(fps.getValue());
			if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				try {
					Main.cfd.fps=Double.parseDouble(fps.getValue());
					Log.record("Set fps:"+Main.cfd.fps);
				}catch(Exception e1) {
					Main.cfd.fps=30;
					
				}
				Main.gui.bgp.ep.updateElement();
			}
		}
		public void keyReleased(KeyEvent e) {
		}
		public void keyTyped(KeyEvent e) {
			int keyChar=e.getKeyChar();
			if ((keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9)) {
				
			} else {
				e.consume(); 
			}
		}
	}
	public class NothingToShow extends JPanel{
		public void paint(Graphics g) {
			g.setColor(getBackground());
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(new Color(200,200,200));
			g.drawString("<没有可显示的属性>", 20, 20);
			
		}
	}
	
	public int page=0;
	public static final int CLIP_PAGE=0,PROJECT_PAGE=1;
	
	public page pg=new page();
	public JPanel abp=new JPanel();
	
	ClipPg cp=new ClipPg();
	ProjPg pp=new ProjPg();
	NothingToShow nts=new NothingToShow();
	
	Clip now=null;
	public AttributeEdit() {
		this.setLayout(null);
		this.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				Log.record("DONOT CLICK HERE!!!");
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
		//this.setSize(200, 20);
		this.setTitlex("属性");
		
		pg.setLocation(0, 20);
		this.add(pg);
		
		abp.setLayout(null);
		abp.setLocation(3, 40);
		this.add(abp);

		cp.setSize(abp.getSize());
		cp.setLocation(0, 0);
		abp.add(cp);
		pp.setSize(abp.getSize());
		pp.setLocation(0, 0);
		abp.add(pp);
		nts.setSize(abp.getSize());
		nts.setLocation(0, 0);
		abp.add(nts);
	}
	
	public void updateCom() {
		pg.setBackground(this.getBackground());
		abp.setSize(this.getWidth()-6, this.getHeight()-43);
		abp.setBackground(PnlColor.getColor(getBackground(), 1.2));
		cp.setSize(abp.getSize());
		cp.setBackground(abp.getBackground());
		cp.updateCom();
		
		pp.setSize(abp.getSize());
		pp.setBackground(abp.getBackground());
		pp.updateCom();
		nts.setSize(abp.getSize());
		nts.setBackground(abp.getBackground());
		//cp.updateCom();
		int count=0;
		for(Clip clp:Main.gui.bgp.ep.elms) {
			if(clp.chosen) {
				now=clp;
				count++;
			}
		}
		//System.out.println(count);
		if(this.page==this.CLIP_PAGE) {
			if(count==1) {
				cp.setVisible(true);
				pp.setVisible(false);
				nts.setVisible(false);
				
				cp.name.setValue(now.title);
				cp.descri.setValue(now.description);
			}else {
				cp.setVisible(false);
				pp.setVisible(false);
				nts.setVisible(true);
				
			}
		}else {
			cp.setVisible(false);
			pp.setVisible(true);
			nts.setVisible(false);
			pp.fps.setValue((int)Main.cfd.fps+"");
		}
		
		this.repaint();
	}
	
	public void loadCfg() {
		try {
			this.setLocation(Integer.parseInt(Main.cfg.get("attributeEdit.x").toString())
					,Integer.parseInt(Main.cfg.get("attributeEdit.y").toString()));
		}catch(Exception e) {
			Log.record("Read cfg failed.");
			e.printStackTrace();
		}
	}
}
