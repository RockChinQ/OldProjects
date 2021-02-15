package hookp;

public class runblock {
	static StringBuffer ing=new StringBuffer("");
	static StringBuffer[] il;
	void run(String fun,int line){
		int bl=start.block.length;
		for(int a=0;a<bl;a++){
			if(start.block[a].equals(fun)){
				ing=new StringBuffer(start.block[a+1]);
				break;
			}
		}
		String[] temp=ing.toString().split(";");
		int tl=temp.length;
		for(int a=line;a<tl;a++){  //运行
			//变量
			if(include(temp[a],"=")){ //YES
				String[] t=temp[a].split("=");
				if(include(t[0]," ")){
					start.v.newvars(temp[a]);
				}else{
					start.v.correct(t[0], t[1]);
				}
			}
			
			//函数
			else{
				String[] lt=temp[a].split(" ");
				int ltl=lt.length;
				StringBuffer[] num=new StringBuffer[ltl-1];
				for(int b=1;b<ltl;b++){
					num[b-1]=new StringBuffer(lt[b]);
				}
				start.far.fr(lt[0],num);
			}
		}
	}
	boolean include(String s,String sy){
		String[] t=s.split(sy);
		if(t[0].equals(s)){
			return false;
		}
		return true;
	}
}
