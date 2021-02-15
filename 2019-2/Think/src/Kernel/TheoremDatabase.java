package Kernel;

import java.io.File;
import java.util.ArrayList;

public class TheoremDatabase {
	class Theorem{
		String grade,name;
		ArrayList<String> dition=new ArrayList<String>(),clusion=new ArrayList<String>();
		Theorem(String dition,String clusion){
			this.init(null,null,dition,clusion);
		}
		Theorem(String name,String grade,String dition,String clusion){
			this.init(name, grade, dition, clusion);
		}
		void init(String name,String grade,String dition,String clusion) {
			this.grade=new String(grade);
			this.name=new String(name);
			String st0[]=dition.split(";");
			for(int i=0;i<st0.length;i++) {
				this.dition.add(st0[i]);
			}
			String st1[]=clusion.split(";");
			for(int i=0;i<st1.length;i++) {
				this.clusion.add(st1[i]);
			}
		}
		void printself() {
			System.out.println("name:"+name+" grade:"+grade+" condition:"+dition+" conclusion:"+clusion);
		}
	}
	class Defination{
		
	}
	ArrayList<Theorem> theorem=new ArrayList<Theorem>();
	
	void importTheorem(String index) {
		if(index.endsWith(".tr")) {     //仅一个定理
			this.importOneTheorem(index);
		}else if(index.endsWith("*")) {  //一个文件夹下的所有定理
			File folder=new File(index.replace("*", ""));
			File[] files=folder.listFiles();
			for(int i=0;i<files.length;i++) {
				if(files[i].isFile()) {
					this.importOneTheorem(files[i].getAbsolutePath());
				}
			}
		}else{                        //一个文件夹及其子文件夹中的所有定理
			boolean sym=index.endsWith("\\");
			File folder=new File(index.replace("*", ""));
			File[] files=folder.listFiles();
			for(int i=0;i<files.length;i++) {
				if(files[i].isFile()) {
					this.importOneTheorem(files[i].getAbsolutePath());
				}else if(files[i].isDirectory()) {
					importTheorem(index+(sym?"":"\\")+files[i].getName()+"\\");
				}
			}
		}
	}
	void printAllTheorem() {
		for(int i=0;i<theorem.size();i++) {
			System.out.print(i+">");
			theorem.get(i).printself();
		}
	}
	private void importOneTheorem(String index) {
		if(!index.endsWith(".tr"))
			return;
		Config c=new Config(index);
		theorem.add(new Theorem(c.read("name"),c.read("grade")
				,c.read("condition"),c.read("conclusion")));
	}
}
