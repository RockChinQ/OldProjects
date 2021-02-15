package boot;

import java.awt.Point;
import java.awt.Toolkit;
import java.util.Date;

import javax.swing.UIManager;

import data.Config;
import data.FileData;
import data.FloatTipTimer;
import data.GroupManager;
import data.MakeRes;
import guiMgr.MainWd;
import guiMgr.PnlColor;
import guiMgr.Workflow;
import guiMgr.panels.EditingPnl;
import guiMgr.panels.FloatTip;
import log.Log;

public class Main {
	public static  Config cfg;
	public static MainWd gui;
	public static MakeRes mr;
	public static FileData cfd;//Current filedata
	public static GroupManager gm;//组管理
	public static final String VER="Alpha 1.3";
	public static FloatTipTimer ftt;
	public static int MESSAGE,WARNING,ERROR;
	/**
	 * 主方法
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Log.record("Launch.ID:"+new Date().getTime());
		cfd=new FileData();
		try {
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");//Nimbus风格，jdk6 update10版本以后的才会出现
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//当前系统风格
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");//Motif风格，是蓝黑
			//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());//跨平台的Java风格
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//windows风格
			//UIManager.setLookAndFeel("javax.swing.plaf.windows.WindowsLookAndFeel");//windows风格
			//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");//java风格
			//UIManager.setLookAndFeel("com.apple.mrj.swing.MacLookAndFeel");//待考察，
			} catch (Exception e) {
			e.printStackTrace();
			}
		mr=new MakeRes();
		mr.make();
		
		cfg=new Config("FilmScript.cfg");
		gui=new MainWd();
		gm=new GroupManager();
		ftt=new FloatTipTimer();
		MESSAGE=gui.ft.MESSAGE;
		WARNING=gui.ft.WARNING;
		ERROR=gui.ft.ERROR;
		gui.sbp.st.addScene("未定义场景", "未定义场景的镜头");
		

		//gui.mwd.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(mr.cut_left, new Point(15, 15), "cut_left"));
		
//		gui.bgp.ep.addElement("test0", EditingPnl.Clip.VIDEO, 100,0, 1);
//		gui.bgp.ep.addElement("test1", EditingPnl.Clip.VIDEO, 100,2, 121);
//		gui.bgp.ep.addElement("test2", EditingPnl.Clip.VIDEO, 100,1, 1);
//		gui.bgp.ep.addElement("test3", EditingPnl.Clip.VIDEO, 500,0, 95);
//		gui.bgp.ep.addElement("test4", EditingPnl.Clip.AUDIO, 100,0, 0);
//		gui.bgp.ep.addElement("test5", EditingPnl.Clip.AUDIO, 100,1, 98);
//		gui.bgp.ep.addLabel(10,"Label-"
//		+(Main.gui.bgp.ep.labelIndex+1),gui.bgp.cb.cb.getColor(8));
		
		//gui.bgp.ep.viewMode=gui.bgp.ep.GROUP_VIEW;
//		int in1=gm.newGroup("Group 1");
//		gui.bgp.ep.elms.get(2).groupId=in1;

//		sleep(20);
//		gm.newGroup("1");
//		sleep(20);
//		gm.newGroup("2");
//		sleep(20);
//		gm.newGroup("3");
//		sleep(20);
//		gm.newGroup("4");
//		sleep(20);
//		gm.newGroup("5");
//		sleep(20);
//		gm.newGroup("6");
//		sleep(20);
//		gm.newGroup("7");
//		sleep(20);
		//gui.bgp.ep.elms.get(3).chosen=true;
		
		gui.mwd.setVisible(true);
		gui.bgp.gp.updateGroupEntry();
		gui.bgp.lc.updateLabel();
		gui.bgp.ae.updateCom();
		gui.updateViewMode();
		gui.bgp.ep.playNow=0;
		gui.bgp.ep.updateElement();
		
		gui.sbp.st.addScene("第一个场景场景场景场景场景场景", "第一个场景，在市区内的室内");
		gui.sbp.st.choose(0, true);
		gui.sbp.st.addScene("Second", "second scene outside room.");
		gui.sbp.sb.addShot("Shot 1",120,gui.sbp.st.getSelectedSceneId());
		gui.sbp.sb.addShot("Shot 2",120, -1);
		gui.sbp.sb.addShot("Shot 3",120, -1);
		gui.sbp.sb.addShot("Shot 4",120, -1);
		gui.sbp.sb.insert(2, "shot 2.5", 120, 1);
		
		
		gui.wf.changeWorkflow(Workflow.SCRIPT);
//		gui.wf.changeWorkflow(Workflow.DETAIL);
		gui.mwd.repaint();
		//gui.bgp.ft.displayTip("启动成功", FloatTip.MESSAGE);
		//cfd.pack("test.json");
//		sleep(1000);
//		cfd.reset();
	}
	/**
	 * 显示顶部提示
	 * 
	 */
	public static void showTip(String tip,int type) {
		gui.ft.displayTip(tip, type);
	}
	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String getNatureTimeCode(long frame) {
		double fps=Main.cfd.fps;
		int fram=(int) (frame%fps);//设置帧数
		int seco=(int) (((frame-fram)/fps)%60);
		int minu=(int) ((((frame-fram)/fps-seco)/60)%60);
		int hour=(int) (((((frame-fram)/fps-seco)/60-minu)/60)%24);
		return hour+":"+minu+":"+seco+":"+fram;
	}
	public static int getNTHour(long frame) {

		double fps=Main.cfd.fps;
		int fram=(int) (frame%fps);//设置帧数
		int seco=(int) (((frame-fram)/fps)%60);
		int minu=(int) ((((frame-fram)/fps-seco)/60)%60);
		int hour=(int) (((((frame-fram)/fps-seco)/60-minu)/60));
		return hour;
	}
	public static int getNTMinu(long frame) {

		double fps=Main.cfd.fps;
		int fram=(int) (frame%fps);//设置帧数
		int seco=(int) (((frame-fram)/fps)%60);
		int minu=(int) ((((frame-fram)/fps-seco)/60)%60);
		
		return minu;
	}
	public static int getNTSeco(long frame) {

		double fps=Main.cfd.fps;
		int fram=(int) (frame%fps);//设置帧数
		int seco=(int) (((frame-fram)/fps)%60);
		
		return seco;
	}
	public static int getNTFram(long frame) {

		double fps=Main.cfd.fps;
		int fram=(int) (frame%fps);//设置帧数
		
		return fram;
	}
	public static String getNTTimeCodeSmartly(long fram) {
		int h=getNTHour(fram),m=getNTMinu(fram),s=getNTSeco(fram),f=getNTFram(fram);
		return (h==0?"":(h+":"))+(m==0&&h==0?"":(m+":"))+(s==0&&m==0&&h==0?"":(s+":"))+f+"";
	}
}
