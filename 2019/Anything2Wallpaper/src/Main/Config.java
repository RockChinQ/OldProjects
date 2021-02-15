package Main;

import java.util.ArrayList;

public class Config {
	String path;
	class Field{
		String name;
		String value;
		Field(String name,String value){
			this.name=new String(name);
			this.value=new String(value);
		}
	}
	ArrayList<Field> fields=new ArrayList<Field>();
	Config(String path){
		this.path=path;
		this.loadAllField();
	}
	void loadAllField() {
		fields=new ArrayList<Field>();
		String[] fileStr=new FileRW().read(path).split("\r\n");
		int i=0,len=fileStr.length;
		for(i=0;i<len;i++) {
			String[] lineSpt=fileStr[i].split("=");
			Field tempf=new Field(lineSpt[0],null);
			StringBuffer valueStr=new StringBuffer();
			if(lineSpt.length==2) {
				tempf.value=new String(lineSpt[1]);
			}else {
				for(int j=1;j<lineSpt.length;j++) {
					valueStr.append(lineSpt[j]);
					if(j!=lineSpt.length-1) {
						valueStr.append("=");
					}
				}
				tempf.value=new String(valueStr.toString());
			}
			fields.add(tempf);
		}
	}
	String get(String name) {
		int size=this.fields.size();
		for(int i=0;i<size;i++) {
			if(fields.get(i).name.equals(name)) {
				return fields.get(i).value;
			}
		}
		return "#FieldNotFound#";
	}
	int set(String name,String value) {
		int size=this.fields.size();
		for(int i=0;i<size;i++) {
			if(fields.get(i).name.equals(name)) {  //已有：设置值
				fields.get(i).value=new String(value);
				return 0;
			}
		}
		//添加
		this.fields.add(new Field(name,value));
		return 1;
	}
	int saveToFile() {
		int size=this.fields.size();
		StringBuffer str=new StringBuffer();
		for(int i=0;i<size;i++) {
			str.append(fields.get(i).name+"="+fields.get(i).value+"\r\n");
		}
		new FileRW().write(path, str.toString(), false);
		return 0;
	}
}
