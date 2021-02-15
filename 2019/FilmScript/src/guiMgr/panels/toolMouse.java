package guiMgr.panels;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import boot.Main;

public class toolMouse implements MouseListener{
	ToolPnl.tool lastTool;
	public void mouseClicked(MouseEvent e) {
		Main.gui.bgp.tp.repaint();
	}
	public void mouseEntered(MouseEvent e) {
		ToolPnl.tool tt=(ToolPnl.tool)e.getSource();
		Main.gui.bgp.ib.setTip(tt.name+": "+tt.descri);
	}
	public void mouseExited(MouseEvent e) {
		Main.gui.bgp.ib.setTip("����");
	}
	public void mousePressed(MouseEvent e) {
		ToolPnl tp=Main.gui.bgp.tp;
		tp.toolSelected.chosen=false;
		lastTool=tp.toolSelected;
		if(e.getSource()==tp.sl) {
			tp.toolSelected=tp.sl;
			tp.sl.setChosen(true);
		}else if(e.getSource()==tp.sc) {
			tp.toolSelected=tp.sc;
			Main.gui.bgp.ep.cl.mode=1;
			tp.sc.setChosen(true);
		}else if(e.getSource()==tp.uw) {//����
			EditingPnl tep=Main.gui.bgp.ep;
			/*����Ƿ�����زĸı�������
			 *  ��ͬʱ����audio��video/title
			 * */
			tp.toolSelected=tp.uw;
			tp.uw.setChosen(true);
			int lastOneType=0,chosenNum=0;
			boolean canChangeTrack=true;
			int lagestNum=0,smallestNum=65536;
			for(int i=0;i<tep.elms.size();i++) {
				if(tep.elms.get(i).chosen) {
					if(chosenNum!=0&&((lastOneType==EditingPnl.Clip.VIDEO
							||lastOneType==EditingPnl.Clip.TITLE)
							&&tep.elms.get(i).type==EditingPnl.Clip.AUDIO)
							||(lastOneType==EditingPnl.Clip.AUDIO
							&&(tep.elms.get(i).type==EditingPnl.Clip.VIDEO
							||tep.elms.get(i).type==EditingPnl.Clip.TITLE))) {
						canChangeTrack=false;
						break;
					}
					lastOneType=tep.elms.get(i).type;
					chosenNum++;
					lagestNum=lagestNum<tep.elms.get(i).track?tep.elms.get(i).track:lagestNum;
					smallestNum=smallestNum>tep.elms.get(i).track?tep.elms.get(i).track:smallestNum;
				}
			}
			//�������
			if(canChangeTrack&&chosenNum>0) {
				if(lastOneType==EditingPnl.Clip.AUDIO) {
					if(smallestNum>0) {
						for(int i=0;i<tep.elms.size();i++) {
							if(tep.elms.get(i).chosen) {
								tep.elms.get(i).track--;
							}
						}
					}
				}else {
					for(int i=0;i<tep.elms.size();i++) {
						if(tep.elms.get(i).chosen) {
							tep.elms.get(i).track++;
						}
					}
				}
				tep.updateElement();
				this.solveAllConflictByGoingThroughChosenElms();
			}else if(!canChangeTrack){
				Main.gui.bgp.ib.setTip("����ͬʱ�ƶ�ͬ���ز�");
				Main.showTip("����ͬʱ�ƶ�ͬ���ز�", Main.ERROR);
			}else if(chosenNum<=0) {
				Main.gui.bgp.ib.setTip("δѡ���κ��ز�");
				Main.showTip("δѡ���κ��ز�", Main.ERROR);
			}
			Main.gui.bgp.tp.repaint();
		}else if(e.getSource()==tp.dw) {//����
			EditingPnl tep=Main.gui.bgp.ep;
			/*����Ƿ�����زĸı�������
			 *  ��ͬʱ����audio��video/title
			 * */
			tp.toolSelected=tp.dw;
			tp.dw.setChosen(true);
			int lastOneType=0,chosenNum=0;
			boolean canChangeTrack=true;
			int lagestNum=0,smallestNum=65536;
			for(int i=0;i<tep.elms.size();i++) {
				if(tep.elms.get(i).chosen) {
					if(chosenNum!=0&&((lastOneType==EditingPnl.Clip.VIDEO
							||lastOneType==EditingPnl.Clip.TITLE)
							&&tep.elms.get(i).type==EditingPnl.Clip.AUDIO)
							||(lastOneType==EditingPnl.Clip.AUDIO
							&&(tep.elms.get(i).type==EditingPnl.Clip.VIDEO
							||tep.elms.get(i).type==EditingPnl.Clip.TITLE))) {
						canChangeTrack=false;
						break;
					}
					lastOneType=tep.elms.get(i).type;
					chosenNum++;
					lagestNum=lagestNum<tep.elms.get(i).track?tep.elms.get(i).track:lagestNum;
					smallestNum=smallestNum>tep.elms.get(i).track?tep.elms.get(i).track:smallestNum;
				}
			}
			//�������
			if(canChangeTrack&&chosenNum>0) {
				if(lastOneType==EditingPnl.Clip.AUDIO) {
					for(int i=0;i<tep.elms.size();i++) {
						if(tep.elms.get(i).chosen) {
							tep.elms.get(i).track++;
						}
					}
				}else {
					//System.out.println(smallestNum);
					if(smallestNum>0) {
						for(int i=0;i<tep.elms.size();i++) {
							if(tep.elms.get(i).chosen) {
								tep.elms.get(i).track--;
							}
						}
					}
				}
				tep.updateElement();
				this.solveAllConflictByGoingThroughChosenElms();
			}else if(!canChangeTrack){
				Main.gui.bgp.ib.setTip("����ͬʱ�ƶ�ͬ���ز�");
				Main.showTip("����ͬʱ�ƶ�ͬ���ز�", Main.ERROR);
			}else if(chosenNum<=0) {
				Main.gui.bgp.ib.setTip("δѡ���κ��ز�");
				Main.showTip("δѡ���κ��ز�", Main.ERROR);
			}
		}else if(e.getSource()==tp.adv) {
			tp.toolSelected=tp.adv;
			Main.gui.bgp.ep.cl.mode=0;
			tp.adv.setChosen(true);
		}else if(e.getSource()==tp.ada) {
			tp.toolSelected=tp.ada;
			Main.gui.bgp.ep.cl.mode=0;
			tp.ada.setChosen(true);
		}else if(e.getSource()==tp.ava) {
			tp.toolSelected=tp.ava;
			Main.gui.bgp.ep.cl.mode=0;
			tp.ava.setChosen(true);
		}else {
			return;
		}
		
	}
	public void mouseReleased(MouseEvent e) {
		ToolPnl tp=Main.gui.bgp.tp;
		Main.gui.bgp.ib.setTip("����");
		if(e.getSource()==tp.uw||e.getSource()==tp.dw) {
			tp.toolSelected=this.lastTool;
			tp.uw.setChosen(false);
			tp.dw.setChosen(false);
			this.lastTool.setChosen(true);
		}
	}
	/**
	 * �������е���ѡ�ز��Խ�����и��ǳ�ͻ
	 */
	public void solveAllConflictByGoingThroughChosenElms() {
		for(int i=0;i<Main.gui.bgp.ep.elms.size();i++) {
			if(Main.gui.bgp.ep.elms.get(i).chosen) {
				Main.gui.bgp.ep.solveAllConflict(Main.gui.bgp.ep.elms.get(i));
			}
		}
		Main.gui.bgp.ep.updateElement();
	}
}
