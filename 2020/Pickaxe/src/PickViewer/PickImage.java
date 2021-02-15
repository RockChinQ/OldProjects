package PickViewer;

import java.awt.Color;

public class PickImage {
	public int w=0,h=0;
	public int data[][];
	public PickImage(String path) {
		String fileStr=FileRW.read(path);
		String[] fss=fileStr.split(":");
		String[] dss=fss[1].split(";");
		int w=Integer.parseInt(fss[0].split(",")[0]),h=Integer.parseInt(fss[0].split(",")[1]);
		//this.data=dss;
		this.w=w;
		this.h=h;
		data=new int[w*h][3];
		//把文件中的颜色数据搞成三个数字
		int[] rgb = new int[3];
		for(int i=0;i<w*h;i++) {
			data[i]=new int[3];
			if(dss[i].equals("n")) {//透明
				data[i][0]=-1;
				continue;
			}
			int pixel=Integer.parseInt(dss[i]);
			data[i][0] = (pixel & 0xff0000) >> 16;
			data[i][1] = (pixel & 0xff00) >> 8;
			data[i][2] = (pixel & 0xff);
		}
	}
}
