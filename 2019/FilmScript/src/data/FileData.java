package data;

import java.io.File;
import java.util.ArrayList;

import com.alibaba.fastjson.JSONObject;

import boot.Main;
import data.GroupManager.group;
import guiMgr.panels.EditingPnl;
import guiMgr.panels.EditingPnl.Clip;
import guiMgr.panels.EditingPnl.Label;
import guiMgr.panels.LinkManage;
import guiMgr.script.SceneTable.scene;
import guiMgr.script.ShotTable.shot;
import log.Log;

public class FileData {
	public static final String projBase="projects\\";
	public double fps=30;//֡�� Ĭ��30
	public String fileName="Untitled";
	public ArrayList<JSONObject> clipBoard=new ArrayList<JSONObject>();
	public ArrayList<JSONObject> shotcb=new ArrayList<JSONObject>();
	public int minx=2147483647;//��¼�˼��а�����С��x
	/**
	 * �������
	 * @param file
	 */
	public void pack() {
		Log.record("Packing file data...");
		Main.showTip("���ڱ���...", Main.MESSAGE);
		JSONObject whole=new JSONObject();
		
		JSONObject profile=new JSONObject();//group��index emls��uid link��uid label��index
		JSONObject projLbs=new JSONObject();
		JSONObject group=new JSONObject();
		JSONObject clip=new JSONObject();
		JSONObject scene=new JSONObject();
		JSONObject shot=new JSONObject();
		
		
		profile.put("group_index", Main.gm.index);
		profile.put("emls_index", Main.gui.bgp.ep.idIndex);
		profile.put("projLbs_index",Main.gui.bgp.ep.labelIndex);
		profile.put("link_index", LinkManage.linkIndex);
		
		profile.put("maxX", Main.gui.bgp.ep.getMaxX());
		profile.put("maxATrack", Main.gui.bgp.ep.maxATrack);
		profile.put("maxVTrack", Main.gui.bgp.ep.maxVTrack);
		
		profile.put("scene_index", Main.gui.sbp.st.index);
		profile.put("shot_uidIndex", Main.gui.sbp.sb.uid);
		profile.put("shot_sortidIndex", Main.gui.sbp.sb.sortid);
		
		//ʱ������ͼ��Ԫ�ر���
		int i=0;
		for(Label lbs:Main.gui.bgp.ep.projLbs) {
			projLbs.put(i+++"", lbs.getJSON().toJSONString());
		}
		projLbs.put("length", Main.gui.bgp.ep.projLbs.size());
		
		i=0;
		
		for(group gp:Main.gm.groups) {
//			if(gp.uid==-1)//�����޷���
//				continue;
			group.put(i+++"", gp.getGroupJSON().toJSONString());
		}
		group.put("length", Main.gm.groups.size());
		//System.out.println(Main.gm.groups.size());
		
		i=0;
		for(Clip clp:Main.gui.bgp.ep.elms) {
			clip.put(i+++"", clp.getClipJSON().toJSONString());
		}
		clip.put("length", Main.gui.bgp.ep.elms.size());
		
		//�־�ͷ��ͼ��Ԫ�ر���
		//����
		i=0;
		for(scene scn:Main.gui.sbp.st.scn) {
			scene.put(i+++"", scn.getJSONObject().toJSONString());
		}
		scene.put("length", Main.gui.sbp.st.scn.size());
		
		//��ͷ
		i=0;
		for(shot sh:Main.gui.sbp.sb.shot) {
			shot.put(i+++"", sh.getJSONObject().toJSONString());
		}
		shot.put("length", Main.gui.sbp.sb.shot.size());
		
		
		whole.put("profile", profile.toJSONString());
		whole.put("projLbs", projLbs.toJSONString());
		whole.put("group", group.toJSONString());
		whole.put("clip", clip.toJSONString());
		whole.put("scene", scene.toJSONString());
		whole.put("shot", shot.toJSONString());
		
		
		new File(projBase).mkdir();
		FileRW.write(projBase+fileName+".json", whole.toJSONString(), false);
		Main.showTip(fileName+" �������", Main.MESSAGE);
		Main.gui.mwd.setTitle("FilmScript "+Main.VER+"-"+Main.cfd.fileName);
		
		//JSONObject test=JSONObject.parseObject(FileRW.read(file));
		//JSONObject gp=JSONObject.parseObject(test.getString("group"));
		//System.out.println(gp.getIntValue("length"));
	}
	/**
	 * �����ļ�����Ŀ����
	 */
	public boolean load(String fileFullPath) {
//		if(!canClear())
//			return false;
		if(!new File(fileFullPath).exists()) {
			Main.showTip("�޷����ļ�:"+fileFullPath+"������", Main.ERROR);
			return false;
		}
		this.reset();
		try {
			//this.fileName=fileFullPath;
			Main.gui.mwd.setTitle("FilmScript "+Main.VER+"-"+Main.cfd.fileName);
			
			JSONObject whole=JSONObject.parseObject(FileRW.read(fileFullPath));
			
			
			JSONObject profile=JSONObject.parseObject(whole.getString("profile"));
			JSONObject projLbs=JSONObject.parseObject(whole.getString("projLbs"));
			JSONObject group=JSONObject.parseObject(whole.getString("group"));
			JSONObject clip=JSONObject.parseObject(whole.getString("clip"));
			JSONObject scene=JSONObject.parseObject(whole.getString("scene"));
			JSONObject shot=JSONObject.parseObject(whole.getString("shot"));
			//profile
			Main.gm.index=profile.getIntValue("group_index");
			Main.gui.bgp.ep.idIndex=profile.getIntValue("emls_index");
			Main.gui.bgp.ep.labelIndex=profile.getIntValue("projLbs_index");
			LinkManage.linkIndex=profile.getIntValue("link_index");
			Main.gui.bgp.ep.maxATrack=profile.getIntValue("maxATrack");
			Main.gui.bgp.ep.maxVTrack=profile.getIntValue("maxVTrack");
			Main.gui.bgp.ep.updateMaxX();
			
			
			
			int length=0,i=0;
			//projLbs
			length=projLbs.getIntValue("length");
			for(i=0;i<length;i++) {
				JSONObject temp=JSONObject.parseObject(projLbs.getString(i+""));
				Label tl=new Label(temp);
				Main.gui.bgp.ep.projLbs.add(tl);
			}
			//group
			length=group.getIntValue("length");
			for(i=0;i<length;i++) {
				JSONObject temp=JSONObject.parseObject(group.getString(i+""));
				group gp=new group(temp);
				Main.gm.groups.add(gp);
			}
			//clip
			length=clip.getIntValue("length");
			for(i=0;i<length;i++) {
				JSONObject temp=JSONObject.parseObject(clip.getString(i+""));
				Clip clp=new Clip(temp);
				clp.addMouseListener(Main.gui.bgp.ep.em);
				clp.addMouseMotionListener(Main.gui.bgp.ep.em);
				Main.gui.bgp.ep.elms.add(clp);
			}
			//scene
			length=scene.getIntValue("length");
			for(i=0;i<length;i++) {
				JSONObject temp=JSONObject.parseObject(scene.getString(i+""));
//				int index=Main.gui.sbp.st.addScene("", "");
//				System.out.println("**********fd open scn:"+index+" len:"+length);
//				Main.gui.sbp.st.getScene(index).loadJSONData(temp);
				Main.gui.sbp.st.addScene(temp);
			}
			//shot
			length=shot.getIntValue("length");
			for(i=0;i<length;i++) {
				JSONObject temp=JSONObject.parseObject(shot.getString(i+""));
				//Main.gui.sbp.sb.addShot(temp.getString("name"), temp.getIntValue("length"), temp.getIntValue("sc"));
				Main.gui.sbp.sb.addShot(Main.gui.bgp.ep.getClipByUID(temp.getIntValue("vdoid")),temp.getIntValue("scene"),true).loadJSONData(temp);
			}
			
			Main.gui.sbp.st.index=profile.getIntValue("scene_index");
			Main.gui.sbp.sb.uid=profile.getIntValue("shot_uidIndex");
			Main.gui.sbp.sb.sortid=profile.getIntValue("shot_sortidIndex");
		}catch(Exception e) {
			e.printStackTrace();
			Log.record("Open file failed.");
			Main.showTip("�ļ���ʧ�ܣ���ʽ����", Main.ERROR);
			Main.gui.mwd.setTitle("FilmScript "+Main.VER+"-"+Main.cfd.fileName+" (�༭������ԭ�ļ���");
		}
		Main.gui.bgp.setAllVisible(true);
		Main.gui.updateViewMode();
		Main.gui.bgp.ep.updateElement();
		
		return true;
	}
	/**
	 * ѯ����ͼ�򿪵��ļ�
	 * @return
	 */
	public boolean askForFileAndOpen() {
		String inputName=""+javax.swing.JOptionPane.showInputDialog("�������Ŀ����:");
		if(!"".equals(inputName)&&canClear()) {
			this.reset();
			this.fileName=inputName;
			load(projBase+fileName+".json");
			return true;
		}else {
			Main.gui.bgp.ib.setTip("�򿪲�����ȡ��");
			Main.showTip("�����ѱ�ȡ��������������", Main.ERROR);
			return false;
		}
	}
	/**
	 * ѯ���½�����
	 * @return
	 */
	public boolean askForProjName() {
		String inputName=javax.swing.JOptionPane.showInputDialog("�����½���Ŀ����:");
		if(!"".equals(inputName)&&inputName!=null) {
			this.fileName=inputName;
			Main.gui.mwd.setTitle("FilmScript "+Main.VER+"-"+Main.cfd.fileName);
			return true;
		}else {
			Main.gui.bgp.ib.setTip("�½�������ȡ��");
			Main.showTip("�����ѱ�ȡ��������������", Main.ERROR);
			return false;
		}
	}
	public void reset() {
		this.fps=30;
		
		Main.gm.groups.clear();
		Main.gm.index=-1;
		Main.gui.sbp.st.scn.clear();
		Main.gui.sbp.st.index=-1;
		//Main.gui.sbp.st.addScene("δ���峡��", "δ���峡���ľ�ͷ");
		Main.gui.sbp.sb.shot.clear();
		Main.gui.sbp.sb.uid=0;
		Main.gui.sbp.sb.sortid=0;
		
		Main.gui.bgp.ep.elms.clear();
		Main.gui.bgp.ep.projLbs.clear();
		LinkManage.linkIndex=0;
		
		Main.gui.bgp.ep.updateElement();
		Main.gui.bgp.gp.updateGroupEntry();
		Main.gui.bgp.lc.updateLabel();
		Main.gui.bgp.ae.updateCom();
		Main.gui.updateViewMode();
		
		Main.gui.sbp.st.updateScene();
		
		
		
		Main.gui.mwd.repaint();
		
		System.gc();
	}
	/**
	 * ��֤�Ƿ���δ����Ĳ�����ȷ���ܷ�ȫ�رյ�ǰ��Ŀ
	 * @return
	 */
	public boolean canClear() {
		return true;
	}
	public void newProj() {
		// TODO Auto-generated method stub
		Main.gui.sbp.st.addScene("δ���峡��", "δ���峡���ľ�ͷ");
	}
}
