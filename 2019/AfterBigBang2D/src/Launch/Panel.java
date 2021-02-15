package Launch;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel{
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.WHITE);
		for(int i=0;i<Main.dusts.size();i++) {
			Dust td=Main.dusts.get(i);
			//System.out.println(td.points.get(j).x+" "+td.points.get(j).y);
			g.drawString(""+i, (int)td.position.x*3+4, (int)td.position.y*3+4);
			g.fillRect((int)td.position.x*3, (int)td.position.y*3,2,2);
			//g.drawString(""+i+" a="+(float)td.acc.getLength(),(int)td.points.get(j).x+2, (int)td.points.get(j).y);
			g.setColor(Color.green);
			g.drawLine((int)td.position.x*3,(int)td.position.y*3,(int)(td.position.x+td.force.x*2)*3,(int)(td.position.y+td.force.y*2)*3);
			g.setColor(Color.WHITE);
		}
	}
}
