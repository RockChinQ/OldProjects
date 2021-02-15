package data;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import boot.Main;
import guiMgr.PnlColor;
import guiMgr.panels.EditingPnl.Clip;
import log.Log;

public class GroupManager {
	//public Map<String,Integer> groups=new HashMap<String,Integer>();
	public static class group{
		public String name="";
		public int uid;
		public int startX=0;
		public int maxWidth=70,maxHeight=0;
		public Color cl=new Color(120,200,5);
		public group(String na,int uid){
			this.name=na;
			this.uid=uid;
		}
		public group(JSONObject json) {
			//System.out.println("********new group from json,uid is"+json.getIntValue("uid"));
			this.name=json.getString("name");
			this.uid=json.getIntValue("uid");
			this.startX=json.getIntValue("startX");
			this.cl=new Color(json.getIntValue("cl"));
		}
		public JSONObject getGroupJSON() {
			JSONObject json=new JSONObject();
			json.put("name", name+"");
			//System.out.println(name);
			json.put("uid", uid);
			json.put("startX", startX);
			json.put("cl",cl.getRGB());
			return json;
		}
	}
	public ArrayList<group> groups=new ArrayList<group>();
	int index=-1;
	public GroupManager() {
		//this.newGroup("No Group");
	}
	public int newGroup(String name) {
		int i=index++;
		group gp=new group(name, i);
		//gp.cl=PnlColor.randHighContrastColor(80,60,180);
		groups.add(gp);
		Main.gui.bgp.gp.updateGroupEntry();
		Log.record("Add new group uid:"+i+" name:"+name);
		return i;
	}
	public void resetMXWidthHeight() {
		for(group gp:groups) {
			gp.maxWidth=70;
			gp.maxHeight=0;
		}
	}
	public void setMXWidth(int groupUID,int MXWidth) {
		for(group gp:groups) {
			if(gp.uid==groupUID) {
				gp.maxWidth=gp.maxWidth<MXWidth?MXWidth:gp.maxWidth;
			}
		}
	}
	public void addMXHeight(int groupUID,int addHeight) {
		for(group gp:groups) {
			if(gp.uid==groupUID) {
				gp.maxHeight+=addHeight;
			}
		}
	}
	public void setAllGroupStartX() {
		Main.gui.bgp.ep.updateMaxX();
		int minx=Main.gui.bgp.ep.getMaxX();
		for(group gp:groups) {
			minx=Main.gui.bgp.ep.getMaxX();
			for(Clip clp:Main.gui.bgp.ep.elms) {
				if(clp.groupId==gp.uid) {
					minx=minx>clp.x?clp.x:minx;
				}
			}
			gp.startX=minx;
		}
	}
	public group getGroup(int uid) {
		for(group gp:this.groups) {
			if(gp.uid==uid) {
				//System.out.println("return group obj:"+uid);
				return gp;
			}
		}
		//System.out.println("no such group "+uid);
		return null;
	}
	public int countGroupEleAmount(int uid) {
		int r=0;
		for(Clip cp:Main.gui.bgp.ep.elms) {
			if(cp.groupId==uid) {
				r++;
			}
		}
		return r;
	}
}
