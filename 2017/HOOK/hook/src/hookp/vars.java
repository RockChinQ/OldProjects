package hookp;

public class vars {
	static int len=(new file0().read(main.file.toString()).split("\"").length)/2+1;
	static StringBuffer[] type=new StringBuffer[65536];
	static int tc=0;
	static StringBuffer[] name=new StringBuffer[65536];
	static int nc=0;
	static StringBuffer[] value=new StringBuffer[65536];
	static int vc=0;
	static StringBuffer[] strs=new StringBuffer[len];   //输出语句
	vars(){
		for(int a=0;a<65536;a++){
			value[a]=new StringBuffer("rock");
		}
	}
	void newvars(String line){
		String[] t=line.split(" ");
		StringBuffer ets=new StringBuffer("");
		int tl=t.length;
		for(int a=1;a<tl;a++)
			ets.append(t[a]);
		String[] t0=ets.toString().split(",");
		int t0l=t0.length;
		if(t[0].equals("int")){
			for(int a=0;a<t0l;a++){
				String[] t1=t0[a].split("=");
				if(include(t1[0],"\\[")&&include(t1[0],"\\]")){
					String[] fg0=t1[0].split("\\[");
					String[] fg1=fg0[1].split("\\]");
					int al=Integer.parseInt(getValue(fg1[0]).toString());
					String[] arv=t1[1].split(" ");
					for(int b=0;b<al;b++){
						name[a]=new StringBuffer(fg0[0]+"["+a+"]");
						nc++;
						if(isIntNum(getValue(arv[a]))){
							value[vc]=new StringBuffer(getValue(arv[a]).toString());
							vc++;
						}
						type[tc]=new StringBuffer("int");
						tc++;
					}
				}else{
					name[nc]=new StringBuffer(t1[0]);
					nc++;
					if(isIntNum(getValue(t1[1]))){
						value[vc]=new StringBuffer(getValue(t1[1]).toString());
						vc++;
					}
					type[tc]=new StringBuffer("int");
					tc++;
				}
			}
		}else if(t[0].equals("float")){
			for(int a=0;a<t0l;a++){
				String[] t1=t0[a].split("=");
				if(include(t1[0],"\\[")&&include(t1[0],"\\]")){
					String[] fg0=t1[0].split("\\[");
					String[] fg1=fg0[1].split("\\]");
					int al=Integer.parseInt(getValue(fg1[0]).toString());
					String[] arv=t1[1].split(" ");
					for(int b=0;b<al;b++){
						name[a]=new StringBuffer(fg0[0]+"["+a+"]");
						nc++;
						if(isFloatNum(getValue(arv[a]))){
							value[vc]=new StringBuffer(getValue(arv[a]).toString());
							vc++;
						}
						type[tc]=new StringBuffer("float");
						tc++;
					}
				}else{
					name[nc]=new StringBuffer(t1[0]);
					nc++;
					if(isFloatNum(getValue(t1[1]))){
						value[vc]=new StringBuffer(getValue(t1[1]).toString());
						vc++;
					}
					type[tc]=new StringBuffer("float");
					tc++;
				}
			}
		}else if(t[0].equals("char")){
			for(int a=0;a<t0l;a++){
				String[] t1=t0[a].split("=");
				try{
					if(include(t1[0],"\\[")&&include(t1[0],"\\]")){//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
						String[] fg0=t1[0].split("\\[");
						String[] fg1=fg0[1].split("\\]");
						int al=Integer.parseInt(getValue(fg1[0]).toString());
						String[] arv=t1[1].split(" ");
						for(int b=0;b<al;b++){
							name[a]=new StringBuffer(fg0[0]+"["+a+"]");
							nc++;
							value[vc]=new StringBuffer(getValue(arv[a]).toString());
							vc++;
							type[tc]=new StringBuffer("char");
							tc++;
						}
					}
					name[nc]=new StringBuffer(t1[0]);
					nc++;
					value[vc]=new StringBuffer(getValue(t1[1]).toString());//getValue(t1[1]).toString()
					vc++;
					type[tc]=new StringBuffer("char");
					tc++;
				}catch(Exception e){
					//error("ERROR:未知错误:"+e);
					e.printStackTrace();
				}
			}
		}else{
			System.out.println("未找到"+t[0]);
		}
	}
	boolean isIntNum(Object obj){
		try{
			String[] s=obj.toString().split("\\.");
			int tr=Integer.parseInt(s[0]);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	boolean isFloatNum(Object obj){
		try{
			float tr=Float.parseFloat(obj.toString());
		}catch(Exception e){
			return false;
		}
		return true;
	}
	void error(Object o){
		new err().throwerr("ERROR:"+o.toString());
	}
	void correct(String na,String v){
		String va=getValue(v).toString();
		boolean b=false;
		for(int a=0;a<65536;a++){
			if(name[a].equals(na)){
				if(type[a].equals("int")){
					if(isIntNum(va))
						value[a]=new StringBuffer(va);
					else
						error(va+"不是正确的类型");
				}else if(type[a].equals("float")){
					if(isFloatNum(va))
						value[a]=new StringBuffer(va);
					else
						error(va+"不是正确的类型");
				}else if(type[a].equals("char")){
					value[a]=new StringBuffer(va);
				}
				b=true;
				break;
			}
		}
		if(!b){
			error("无法找到变量"+na);
		}
	}
	Object getValue(String s){
		if(isFloatNum(s)||isFloatNum(s))  //常数
			return s;
		if(include(s,"\\+"))
			return oper(s,0);
		else if(include(s,"\\-"))
			return oper(s,1);
		else if(include(s,"\\*"))
			return oper(s,2);
		else if(include(s,"\\/"))
			return oper(s,3);
		
		for(int a=0;a<nc-1;a++){
			if(name[a].toString().equals(s)){
				return value[a];
			}
		}
		String[] lt=s.split(" ");
		int ltl=lt.length;
		StringBuffer[] num=new StringBuffer[ltl-1];
		for(int b=1;b<ltl;b++){
			num[b-1]=new StringBuffer(lt[b]);
		}
		if(start.far.isFunction(lt[0]))
			return start.far.fr(lt[0],num);
		else{
			return s.split("\'")[1];
		}
	}
	
	boolean include(String s,String sy){
		String[] t=s.split(sy);
		if(t[0].equals(s)){
			return false;
		}
		return true;
	}
	
	Object oper(String s,int sym){
		Float on0=(float) 0,on1=(float) 0;
		if(sym==0&&!isFloatNum(Float.parseFloat(s.split("\\+")[0])))
			on0=(Float) getValue(""+Float.parseFloat(s.split("\\+")[0]));
		else if(sym==1&&!isFloatNum(Float.parseFloat(s.split("\\-")[0])))
			on0=(Float) getValue(""+Float.parseFloat(s.split("\\-")[0]));
		else if(sym==2&&!isFloatNum(Float.parseFloat(s.split("\\*")[0])))
			on0=(Float) getValue(""+Float.parseFloat(s.split("\\*")[0]));
		else if(sym==3&&!isFloatNum(Float.parseFloat(s.split("\\/")[0])))
			on0=(Float) getValue(""+Float.parseFloat(s.split("\\/")[0]));

		if(sym==0&&!isFloatNum(Float.parseFloat(s.split("\\+")[1])))
			on0=(Float) getValue(""+Float.parseFloat(s.split("\\+")[1]));
		else if(sym==1&&!isFloatNum(Float.parseFloat(s.split("\\-")[1])))
			on0=(Float) getValue(""+Float.parseFloat(s.split("\\-")[1]));
		else if(sym==2&&!isFloatNum(Float.parseFloat(s.split("\\*")[1])))
			on0=(Float) getValue(""+Float.parseFloat(s.split("\\*")[1]));
		else if(sym==3&&!isFloatNum(Float.parseFloat(s.split("\\/")[1])))
			on0=(Float) getValue(""+Float.parseFloat(s.split("\\/")[1]));
		
		if(sym==0)
			return Float.parseFloat(s.split("\\+")[0])+Float.parseFloat(s.split("\\+")[1]);
		else if(sym==1)
			return Float.parseFloat(s.split("\\-")[0])+Float.parseFloat(s.split("\\-")[1]);
		else if(sym==2)
			return Float.parseFloat(s.split("\\*")[0])+Float.parseFloat(s.split("\\*")[1]);
		else if(sym==3)
			return Float.parseFloat(s.split("\\/")[0])+Float.parseFloat(s.split("\\/")[1]);
		else
			return "NaN";
	}
}
