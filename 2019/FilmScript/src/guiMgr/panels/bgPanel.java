package guiMgr.panels;

import java.awt.Color;

import javax.swing.JPanel;

import boot.Main;
import guiMgr.PnlColor;
import guiMgr.colorBoard;
import log.Log;

public class bgPanel extends JPanel{
	public EditingPnl ep; //编辑区
	public TrackOper to;//轨道操作
	public ToolPnl tp;
	public InfoBar ib;//底部信息框
	public LabelCheck lc;//标签管理
	public colorBoard cb=new colorBoard();//颜色
	public GroupPnl gp;
	public AttributeEdit ae;//属性
	public WinBar wb;
	public PnlColor pc=new PnlColor();
	public bgPanel(PnlColor pc) {
		Log.record("Loading timeline panels...");
		this.setSize(1000, 700);
		this.setLayout(null);
		this.setBackground(new Color(30,30,30));
		this.pc=pc;
		
		cb.setBackground(pc.getColor(pc.editingPnl*1.2));
		cb.setLocation(-200, -200);
		this.add(cb);
		
		Log.record("Loading attributeEdit...");
		ae=new AttributeEdit();
		ae.setSize(200, 240);
		ae.setLocation(10, 270);
		ae.loadCfg();
		ae.resize();
		this.add(ae);
		
		Log.record("Loading groupPnl...");
		gp=new GroupPnl();
		gp.setSize(180, 220);
		gp.setLocation(10, 10);
		gp.loadCfg();
		gp.resize();
		this.add(gp);
		
		Log.record("Loading trackOperPanel...");
		to=new TrackOper();
		to.setSize(360, 40);
		to.setLocation(10, 10);
		to.loadCfg();
		to.resize();
		this.add(to);
		
		Log.record("Loading toolPanel...");
		tp=new ToolPnl();
		tp.setSize(180, 40);
		tp.setLocation(10, 60);
		tp.loadCfg();
		tp.resize();
		this.add(tp);
		
		Log.record("Loading label manager...");
		lc=new LabelCheck();
		lc.setLocation(10, 110);
		lc.loadCfg();
		lc.resize();
		this.add(lc);
		
		Log.record("Loading winBar...");
		wb=new WinBar();
		wb.setSize(this.getWidth(), 20);
		wb.setLocation(0, this.getHeight()-36);
		wb.resize();
		this.add(wb);
		
		Log.record("Loading infoBar...");
		ib=new InfoBar();
		ib.setSize(this.getWidth(), 20);
		ib.setLocation(0, this.getHeight()-16);
		ib.resize();
		this.add(ib);
		
		Log.record("Loading editing panel...");
		ep=new EditingPnl();
		ep.setSize(this.getSize());
		ep.setLocation(0, 0);
		this.add(ep);
		
		
		wb.addLabel(ae);
		wb.addLabel(gp);
		wb.addLabel(lc);
		wb.addLabel(to);
		wb.addLabel(tp);
		
		
		this.setPanelColor();
	}
	public void setPanelColor() {
		ep.setBackground(pc.getColor(pc.editingPnl));
		to.setBackground(pc.getColor(pc.trackOper));
		to.setBackgroundx(pc.getColor(pc.trackOper));
		tp.setBackground(pc.getColor(pc.toolPnl));
		tp.setBackgroundx(pc.getColor(pc.toolPnl));
		lc.setBackground(pc.getColor(pc.labelCheck));
		lc.setBackgroundx(pc.getColor(pc.labelCheck));
		ib.setBackground(pc.getColor(pc.infoBar));
		gp.setBackgroundx(pc.getColor(pc.groupPnl));
		ae.setBackgroundx(pc.getColor(pc.attributeEdit));
		wb.setBackground(ib.getBackground());
	}
	public void resize() {
		/*
		StackTraceElement[] ste=Thread.currentThread().getStackTrace();
		StackTraceElement a=(StackTraceElement)ste[2];
		System.out.println("resize from["+a.getClassName()+"."+
					a.getMethodName()+"()."+a.getLineNumber()+"]");
		*/
		ep.setSize(this.getSize());
		//ep.updateElement();
		ib.setLocation(0, this.getHeight()-118);
		//ib.setLocation(0, 0);
		ib.setSize(this.getWidth(), 20);
		ib.resize();
		wb.setSize(this.getWidth(), 20);
		wb.setLocation(0, this.getHeight()-138);
		wb.resize();
		ep.updateElement();
		//System.out.println(this.getHeight()+" "+ib.getLocation()+" "+ib.getSize());
	}
	public void setAllVisible(boolean vsb) {
		
		ep.setVisible(vsb);
		ae.setVisible(vsb);
		gp.setVisible(vsb);
		to.setVisible(vsb);
		tp.setVisible(vsb);
		lc.setVisible(vsb);
		wb.setVisible(vsb);
		ib.setVisible(vsb);
	}
}
