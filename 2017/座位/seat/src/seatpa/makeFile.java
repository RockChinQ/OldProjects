package seatpa;

import java.io.File;

public class makeFile {
	class nnn{}
	String make(File f,boolean[][] ba,StringBuffer[][] name){
		int xx=0,yx=0;
		StringBuffer sb0=new StringBuffer();
		StringBuffer sb2=new StringBuffer();
		for(int a=0;a<ba[0].length;a++){
			for(int b=0;b<ba.length;b++){
				if(ba[b][a]){
					xx=xx>b?xx:b;
					yx=yx>a?yx:a;
				}
			}
		}
		for(int a=0;a<yx+1;a++){
			for(int b=0;b<xx+1;b++){
				sb0.append("&"+ba[b][a]);
			}
		}
		for(int a=0;a<yx+1;a++){
			for(int b=0;b<xx+1;b++){
				sb2.append("&"+name[b][a]);
			}
		}
		StringBuffer sbb=new StringBuffer(f.getAbsolutePath());
		String[] fp=sbb.toString().split(".stb");
		//if(!fp[fp.length-1].equals("stb")){
		//	sbb.append(".stb");
		//}
		StringBuffer sb1=new StringBuffer((xx+1)+"&"+(yx+1));
		String s=new String(sb1);
		//new file0().write(sbb.toString(), s,false);
		new file0().write(fp[0]+".stb", s+sb0+sb2,false);
		return fp[0]+".stb";
	}
}
