package PickViewer;

import java.util.ArrayList;


public class Config {
	public String file;
	ArrayList<Field> fields=new ArrayList<Field>();
	boolean loading=false;
	class Field{
		String name;
		Object value;
	}
	public Config(String file) {
		this.file=new String(file);
		reload();
	}
	public void reload() {
		try {
			loading=true;
			fields=new ArrayList<Field>();
			String fileStr=new FileRW().read(file);
			String flSpt[]=fileStr.split("\r\n");  //检查是否是rn
			int len=flSpt.length;
			for(int i=0;i<len;i++) {
				Field f=new Field();
				f.name=flSpt[i].split("=")[0];
				f.value=flSpt[i].split("=")[1];
				this.fields.add(f);
			}
			Log.record("Reload over.");
		}catch(Exception e) {
			Log.record("Reload failed.");
			e.printStackTrace();
		}finally {
			loading=false;
		}
	}
	public Object get(String name) {
		if(loading) {
			Log.record("get f:"+name+" while loading.");
			return "#loading#";
		}
		for(int i=0;i<fields.size();i++) {
			if(fields.get(i).name.equals(name)) {
				return fields.get(i).value;
			}
		}
		Log.record("Field:"+name+" undefine.");
		return "#undefine#";
	}
	public boolean set(String name,Object value) {
		Log.record("Set "+name+"="+value);
		if(loading) {
			Log.record("set f:"+name+" while loading.");
			return false;
		}
		int i;
		for(i=0;i<fields.size();i++) {
			if(fields.get(i).name.equals(name)) { //已存在，则修改值
				fields.get(i).value=value;
				break;
			}
		}
		if(i==fields.size()) {   //无此项，添加
			Field f=new Field();
			f.name=name;
			f.value=value;
			fields.add(f);
		}
		save();
		return true;
	}
	public void save() {
		StringBuffer fl=new StringBuffer();
		for(int i=0;i<fields.size();i++) {
			fl.append(fields.get(i).name+"="+fields.get(i).value.toString()+"\r\n");
		}
		new FileRW().write(file, fl.toString(), false);
		Log.record("Save finished.");
		return;
	}
	public boolean remove(String name) {
		if(loading) {
			Log.record("Remove f:"+name+" while loading.");
			return false;
		}
		for(int i=0;i<fields.size();i++) {
			if(fields.get(i).name.equals(name)) {
				fields.remove(i);
			}
		}
		save();
		return true;
	}
	public void removeAll() {
		Log.record("Remove all fields.");
		fields=new ArrayList<Field>();
		save();
		return;
	}
}
