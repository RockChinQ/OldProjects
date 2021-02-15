package seatpa;

public class save {
	class nnn{}
	boolean save0(){
		StringBuffer str=new StringBuffer(editgui.l0+"&"+editgui.l1);
		for(int b=0;b<editgui.l1*editgui.l0;b++){
			str.append("&"+editgui.s[b+2]);
		}
		for(int a=0;a<editgui.l0;a++){
			for(int b=0;b<editgui.l1;b++){
				if(editgui.isseat[a][b])
					str.append("&"+editgui.jbt[a][b].getText());
			}
		}
		new file0().write(editgui.filen,str.toString(),false);
		return true;
	}
}
