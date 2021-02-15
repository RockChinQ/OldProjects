package guiMgr.script;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import boot.Main;
import data.IconDataBase;
import guiMgr.FixedPanel;
import guiMgr.PnlColor;
import guiMgr.colorBoardTarget;
import guiMgr.fields.Icon;
import guiMgr.fields.InputAreaField;
import guiMgr.fields.InputField;
import guiMgr.script.SceneTable.scene;
import guiMgr.script.SceneTable.scene.seat;

public class SceneAttri extends FixedPanel implements KeyListener, MouseListener{
	/**
	 * name
	 * 布景细节
	 * 位置
	 * 备注
	 */
	InputField name,locat,note;
	InputAreaField descri;
	public thumbCanvas thu;
	
	scene scnow=null;

	Icon win=new Icon(IconDataBase.scene_attri_win);
	Icon na=new Icon(IconDataBase.scene_attri_name);
	Icon lo=new Icon(IconDataBase.scene_attri_location);
	Icon no=new Icon(IconDataBase.scene_attri_note);
	Icon de=new Icon(IconDataBase.scene_attri_description);
	Icon th=new Icon(IconDataBase.scene_attri_thumb);
	JLabel thl=new JLabel("简图");
	class clbl extends JPanel {
		public void paint(Graphics g) {
			g.setColor(getBackground());
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(thu.cl);
			g.fillRect(5, 5, this.getWidth()-10, this.getHeight()-10);
		}
	}
	clbl clb=new clbl();
	colorBoardTarget cbt=c->{thu.cl=c;Main.gui.sbp.repaint();};
	Icon pencil=new Icon(IconDataBase.scene_attri_pencil);
	Icon reset=new Icon(IconDataBase.shot_table_del_tiny);
	Icon eraser=new Icon(IconDataBase.scene_attri_eraser);
	Icon confir=new Icon(IconDataBase.scene_attri_confirm);
	Icon seats=new Icon(IconDataBase.scene_attri_seat);
	
	JLabel sel=new JLabel("机位(0)");
	public SceneAttri() {
		this.setLayout(null);
		this.setSize(400, 500);
		this.setTitlex("场景信息");

		na.setSize(10, 10);
		na.setLocation(3, 41);
		this.add(na);
		lo.setSize(10, 10);
		lo.setLocation(3, 70);
		this.add(lo);
		no.setSize(10, 10);
		no.setLocation(3, 100);
		this.add(no);
		de.setSize(10, 10);
		de.setLocation(162, 42);
		this.add(de);
		th.setSize(10, 10);
		th.setLocation(3, 130);
		this.add(th);
		thl.setSize(70, 15);
		thl.setLocation(15, 128);
		thl.setForeground(name.fcl);
		this.add(thl);

		win.setSize(12, 12);
		win.setLocation(12, 7);
		this.add(win);
		
		name=new InputField("名称",120,26,30);
		name.setLocation(15, 30);
		name.input.addKeyListener(this);
		this.add(name);
		locat=new InputField("地点",140,26,30);
		locat.setLocation(15, 60);
		locat.input.addKeyListener(this);
		this.add(locat);
		note=new InputField("备注",140,26,30);
		note.setLocation(15, 90);
		note.input.addKeyListener(this);
		this.add(note);
		
		descri=new InputAreaField("细节",195,85,30);
		descri.setLocation(175, 30);
		descri.input.addKeyListener(this);
		this.add(descri);
		
		thu=new thumbCanvas(230,150);
		thu.setLocation(45, 125);
		thu.setVisible(true);
		//thu.repaint();
		this.add(thu);
		clb.setSize(25, 25);
		clb.setLocation(thu.getWidth()+thu.getX()+2, thu.getY());
		clb.setBackground(PnlColor.getColor(getBackground(), 1.2));
		clb.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				if(Main.gui.sbp.cb.cbt!=cbt) {
					Main.gui.sbp.cb.setLocation(clb.getX(), clb.getY()+30);
					Main.gui.sbp.cb.setVisible(cbt);
				}else {
					Main.gui.sbp.cb.setVisible(null);
				}
			}
			public void mouseReleased(MouseEvent e) {
			}
		});
		this.add(clb);
		pencil.setSize(25, 25);
		pencil.setLocation(clb.getX(), clb.getY()+clb.getHeight()+1);
		pencil.addMouseListener(this);
		this.add(pencil);
		eraser.setSize(25, 25);
		eraser.setLocation(clb.getX(), pencil.getY()+pencil.getHeight()+1);
		eraser.addMouseListener(this);
		this.add(eraser);
		reset.setSize(20, 20);
		reset.setLocation(thu.getX()-20, clb.getY()+clb.getHeight()+90);
		reset.addMouseListener(this);
		this.add(reset);
		confir.setSize(25, 25);
		confir.setLocation(clb.getX(), thu.getY()+thu.getHeight()-25);
		confir.addMouseListener(this);
		//this.add(confir);
		
		seats.setSize(25, 25);
		seats.setLocation(clb.getX(), thu.getY()+thu.getHeight()-26);
		seats.addMouseListener(this);
		this.add(seats);
		//thu.reset();
		
		this.updateCom();
	}
	public void updateCom() {
		name.setBackground(getBackground());
		name.updateCom();
		locat.setBackground(getBackground());
		locat.updateCom();
		note.setBackground(getBackground());
		note.updateCom();
		descri.setBackground(getBackground());
		descri.updateCom();
		win.setBackground(getBackground());
		clb.setBackground(PnlColor.getColor(getBackground(), 1.6));
		pencil.setBackground(PnlColor.getColor(getBackground(), 0.8));
		eraser.setBackground(PnlColor.getColor(getBackground(), 1.6));
		reset.setBackground(PnlColor.getColor(getBackground(), 1.6));
		confir.setBackground(PnlColor.getColor(getBackground(), 1.6));
		confir.setColor(new Color(20,220,30));
		seats.setBackground(PnlColor.getColor(getBackground(), 1.6));
		
	}
	/**
	 * 从已选中的场景取信息
	 */
	public void updateInfo() {
		//System.out.println(2222222);
		for(scene sc:Main.gui.sbp.st.scn) {
			if(sc.chosen) {
				this.name.setValue(sc.gp.name);
				this.descri.setValue(sc.descri);
				this.locat.setValue(sc.location);
				this.note.setValue(sc.note);
				thu.pix=sc.thumb;
				//seats
				
				scnow=sc;
				break;
			}
		}
		thu.repaint();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		scnow.gp.name=Main.gui.sbp.st.getLegalName(this.name.getValue(),scnow);
		name.setValue(scnow.gp.name);
		scnow.descri=this.descri.getValue();
		scnow.location=this.locat.getValue();
		scnow.note=this.note.getValue();
		Main.gui.sbp.st.repaint();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==reset) {
			thu.reset(thu.getWidth(), thu.getHeight());
		}else if(e.getSource()==eraser) {
			eraser.setBackground(PnlColor.getColor(getBackground(), 0.8));
			pencil.setBackground(PnlColor.getColor(getBackground(), 1.6));
			seats.setBackground(PnlColor.getColor(getBackground(), 1.6));
			thu.tool=thu.ERASER;
		}else if(e.getSource()==pencil) {
			eraser.setBackground(PnlColor.getColor(getBackground(), 1.6));
			pencil.setBackground(PnlColor.getColor(getBackground(), 0.8));
			seats.setBackground(PnlColor.getColor(getBackground(), 1.6));
			thu.tool=thu.PENCIL;
		}else if(e.getSource()==confir) {
			int w=thu.pix[0].length,h=thu.pix.length;
			scnow.thumb=new Color[h][w];
			for(int i=0;i<h;i++) {
				for(int j=0;j<w;j++) {
					scnow.thumb[i][j]=thu.pix[i][j];
				}
			}
		}else if(e.getSource()==seats) {
			eraser.setBackground(PnlColor.getColor(getBackground(), 1.6));
			pencil.setBackground(PnlColor.getColor(getBackground(), 1.6));
			seats.setBackground(PnlColor.getColor(getBackground(), 0.8));
			thu.tool=thu.CAMERA;
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
