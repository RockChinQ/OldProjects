package MainPkg;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel{
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.WHITE);
		for(int i=0;i<Main.w.dusts.size();i++) {
			Dust td=Main.w.dusts.get(i);
			for(int j=0;j<td.points.size();j++) {
				System.out.println(td.points.get(j).x+" "+td.points.get(j).y);
				g.drawRect((int)td.points.get(j).x, (int)td.points.get(j).y,1,1);
				g.drawString(""+i+"."+j+" a="+(float)td.acc.getLength(),(int)td.points.get(j).x+2, (int)td.points.get(j).y);
			}
		}
	}
}
