package guiMgr;

import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import boot.Main;
import guiMgr.panels.EditingPnl;
import guiMgr.panels.FloatTip;
import guiMgr.panels.MenuBar;
import guiMgr.panels.bgPanel;
import guiMgr.script.scriptBgp;
import log.Log;

public class MainWd implements WindowListener, ComponentListener, KeyListener {
	public JFrame mwd;
	public AllPnlBg apb;
	public bgPanel bgp;
	public scriptBgp sbp;
	
	public JPanel focus;
	public MenuBar mb;
	public Workflow wf;
	public PnlColor pc=new PnlColor();
	public int mw,mh;

	public FloatTip ft;
	public MainWd() {
		Log.record("Loading window...");
		mwd=new JFrame("Scenarist "+Main.VER+"-"+Main.cfd.fileName);
		if(Main.cfg.get("mwd.isMax").toString().equals("true")) {
			mwd.setSize(1000, 700);
			mwd.setLocation(10, 10);
			mwd.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}else if(!Main.cfg.get("mwd.width").toString().equals("#undefine#")){
			mwd.setSize(Integer.parseInt(Main.cfg.get("mwd.width")
					.toString()),Integer.parseInt(Main.cfg
							.get("mwd.height").toString()));
			mwd.setLocation(Integer.parseInt(Main.cfg.get("mwd.x").toString())
					,Integer.parseInt(Main.cfg.get("mwd.y").toString()));
		}else {
			mwd.setSize(1000, 700);
			mwd.setLocation(10, 10);
		}
		mwd.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		mwd.setLayout(null);
		

		
		ft=new FloatTip();
		ft.setLocation(-100, 100);
		ft.setSize(100, 20);
		mwd.add(ft);
		//工作流控制
		
		apb=new AllPnlBg();
		apb.setBackground(new Color(40,40,40));
		apb.setSize(mwd.getSize());
		apb.setLocation(0, 0);
		apb.setLayout(null);
		mwd.add(apb);
		
		wf=new Workflow();
		wf.setSize(1000, 30);
		wf.setLocation(0, 0);
		wf.setBackground(pc.getColor(pc.infoBar*0.9));
		apb.add(wf);
		
		
		//添加面板
		bgp=new bgPanel(pc);
		bgp.setLocation(0, 35);
		apb.add(bgp);
		//分镜面板
		sbp=new scriptBgp(pc);
		sbp.setLocation(0, 35);
		apb.add(sbp);
		
		
		//窗口菜单
		mb=new MenuBar();
		mwd.setJMenuBar(mb);

		mw=mwd.getWidth();
		mh=mwd.getHeight();
		//mwd.getLayeredPane().setLayout(null);
		mwd.addWindowListener(this);
		mwd.addComponentListener(this);
		mwd.addKeyListener(this);
		//mwd.setVisible(true);
		mwd.setFocusable(true);
		Log.record("Finish.");
		
	}
	public void updateViewMode() {
		bgPanel bgp=Main.gui.bgp;
		if(bgp.ep.viewMode==bgp.ep.GROUP_VIEW) {
			bgp.ep.zoom=16;
			bgp.tp.select(bgp.tp.sl);
			bgp.gp.setVisible(true);
			bgp.cb.setVisible(false);
			bgp.lc.setVisible(false);
			bgp.to.setVisible(false);
			bgp.tp.setVisible(false);
			
			bgp.gp.setEnabled(true);
			
			bgp.to.setEnabled(false);
			bgp.tp.setEnabled(false);
			bgp.lc.setEnabled(false);
		}else if(bgp.ep.viewMode==bgp.ep.TIMELINE_VIEW) {//时间线
			bgp.gp.setVisible(false);
			bgp.cb.setVisible(false);
			bgp.lc.setVisible(true);
			bgp.to.setVisible(true);
			bgp.tp.setVisible(true);
			
			bgp.to.setEnabled(true);
			bgp.tp.setEnabled(true);
			bgp.lc.setEnabled(true);
			
			bgp.gp.setEnabled(false);
		}
		bgp.ep.updateElement();
		bgp.repaint();
	}
	/**
	 * 窗口化一个浮动面板
	 * @param jp
	 */
	public void enwindowPanel(FloatPanel jp) {
		this.bgp.remove(jp);
		JDialog jd=new JDialog(this.mwd);
		jd.setSize(jp.getWidth()+14,jp.getHeight()+37);
		//System.out.println(jp.getSize());
		jd.setLocation(10, 10);
		jd.setLayout(null);
		jd.setTitle(jp.tl.title);
		jd.addWindowListener(new DialogListner(jp,jp.getLocation()));
		jp.setLocation(0, 0);
		jd.add(jp);
		jd.setVisible(true);
		this.mwd.repaint();
		jp.wded=true;
	}
	
	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowClosing(WindowEvent arg0) {
		mwd.setVisible(false);
		Main.gui.bgp.ib.setProgressText("正在储存设置...");
		Main.cfg.set("mwd.isMax",mwd.getExtendedState()==JFrame
				.MAXIMIZED_BOTH?true:false);
		Main.cfg.set("mwd.width", mwd.getWidth());
		Main.cfg.set("mwd.height", mwd.getHeight());
		Main.cfg.set("mwd.x", mwd.getX());
		Main.cfg.set("mwd.y", mwd.getY());
		Main.gui.bgp.ib.setProgressRate(0.3);
		
		Main.cfg.set("editingPnl.color",pc.editingPnl);
		Main.cfg.set("toolPnl.color", pc.toolPnl);
		Main.cfg.set("trackOper.color", pc.trackOper);
		Main.cfg.set("labelCheck.color", pc.labelCheck);
		Main.cfg.set("infoBar.color", pc.infoBar);
		Main.cfg.set("labelCheck.color", pc.labelCheck);
		Main.cfg.set("groupPnl.color", pc.groupPnl);
		Main.cfg.set("attributeEdit.color", pc.attributeEdit);
		Main.cfg.set("sceneTable.color", pc.sceneTable);
		Main.cfg.set("sceneAttri.color", pc.sceneAttri);
		Main.cfg.set("shotAttri.color", pc.shotAttri);
		Main.cfg.set("shotTable.color", pc.shotTable);
		Main.cfg.set("trackOper.x", bgp.to.getX());
		Main.cfg.set("trackOper.y", bgp.to.getY());
		Main.cfg.set("toolPnl.x", bgp.tp.getX());
		Main.cfg.set("toolPnl.y", bgp.tp.getY());
		Main.cfg.set("labelCheck.x", bgp.lc.getX());
		Main.cfg.set("labelCheck.y", bgp.lc.getY());
		Main.cfg.set("groupPnl.x", bgp.gp.getX());
		Main.cfg.set("groupPnl.y", bgp.gp.getY());
		Main.cfg.set("attributeEdit.x", bgp.ae.getX());
		Main.cfg.set("attributeEdit.y", bgp.ae.getY());
		Main.gui.bgp.ib.setProgressRate(0.8);
		
		Main.cfg.set("trackLocationAdd", bgp.ep.add);
		Main.cfg.set("eleHeight", bgp.ep.eleHeight);
		Main.cfg.set("groupview_addX", bgp.ep.addX-bgp.ep.addXReset);
		Main.cfg.set("groupview_addY", bgp.ep.addY-bgp.ep.addYReset);
		Main.gui.bgp.ib.setProgressRate(1);
		Log.record("Closing...");
		Log.saveLog();
		System.exit(0);
	}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {
//        int realH =mwd.getContentPane().getSize().height,realW=mwd.getContentPane().getSize().width;
//        int titleH = mh - realH,titleW=mw-realW;
//        mwd.setSize(mw+titleW, mh+titleH);
	}
	public void componentHidden(ComponentEvent arg0) {}
	public void componentMoved(ComponentEvent arg0) {}
	public void componentResized(ComponentEvent arg0) {
		bgp.setSize(mwd.getWidth()-13,mwd.getHeight());
		sbp.setSize(mwd.getWidth()-13, mwd.getHeight());
		apb.setSize(mwd.getSize());
		wf.resize();
		//bgp.resize();
		if(wf.getWorkflow()==wf.SCRIPT) {
			sbp.resize();
			bgp.setVisible(false);
			sbp.setVisible(true);
		}else if(wf.getWorkflow()==wf.DETAIL) {
			bgp.resize();
			sbp.setVisible(false);
			bgp.setVisible(true);
		}
	}
	public void componentShown(ComponentEvent arg0) {}
	public void keyPressed(KeyEvent arg0) {
		//System.out.println(arg0.getKeyCode());
		
		
	}
	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {}
	

}
