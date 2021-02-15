package data;

import java.awt.Point;
import java.util.ArrayList;

public class LoadFile {
	String fileName;
	class Portal{
		public Point p;
		public Portal(int x,int y) {
			p=new Point(x,y);
		}
		public Portal(Point p) {
			this.p=p;
		}
		public void setPosition(Point p) {
			this.p.setLocation(p);
		}
		public void setPosition(int x,int y) {
			this.p.setLocation(x, y);
		}
	}
	ArrayList<Portal> portals;
	
	public LoadFile(String fileName) {
		this.fileName=fileName;
		this.reloadData();
	}
	public void reloadData() {
		portals=new ArrayList<Portal>();
		String data=FileRW.read(fileName);
		String[] dataSpted=data.split(";");
		int pointAmount=dataSpted.length;
		for(int i=0;i<pointAmount;i++) {
			String[] pointSpted=dataSpted[i].split(",");
			portals.add(new Portal(Integer.parseInt(pointSpted[0]),Integer.parseInt(pointSpted[1])));
		}
	}
}
