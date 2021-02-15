package Kernel;

import java.util.ArrayList;

public class Kernel {
	ArrayList<Obj> objs=new ArrayList<Obj>();
	class Obj{
		String field;
		int paramAmount;
		Obj(String field,int pa){
			this.field=field;
			this.paramAmount=pa;
		}
	}
	Kernel(){
		String objs[]=new file0().read("objectList.ls").split(";");
		for(int i=0;i<objs.length;i++) {
			this.objs.add(new Obj(objs[i].split(" ")[0]
					,Integer.parseInt(objs[i].split(" ")[1])));
		}
	}
	void solve(ArrayList<String> condition,String result) {
		int len=Main.tdb.theorem.size();
		for(int i=0;i<len;i++) {
			ArrayList<String> cdtt=Main.tdb.theorem.get(i).dition;
			for()
		}
	}
	String replaceActualParam(String ap) {
		for(int i=0;i<this.objs.size();i++) {
			
		}
	}
}
