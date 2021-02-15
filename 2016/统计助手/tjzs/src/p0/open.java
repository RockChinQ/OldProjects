package p0;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public class open {
	tablep tp=new tablep();
	int[][] ti;
	void openfile(String s){
		String[] ss=new file0().read(s).split(";");       //ÁÐ
		String[] s0=ss[0].split(" ");                     //ÐÐ
		ti=new int[s0.length][ss.length];
		createt(s0.length, ss.length,s0,ss);
	}
	void createt(int x,int y,String[] s0,String[] s1){
		for(int i=0;i<x;){
			String[] ii=s1[i].split(" ");
			for(int i0=0;i0<y;){
				ti[i][i0]=Integer.parseInt(ii[i0]);
				i0++;
			}
			i++;
		}
		JTable jt=new JTable(x,y);
	}
}
