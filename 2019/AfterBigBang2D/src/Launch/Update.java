package Launch;

import java.util.TimerTask;

public class Update extends TimerTask{
	public static final float G=10;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(Main.w.run)
			updateAllDust();
	}
	public void updateAllDust() {
		System.out.println("====================");
		for(int i=0;i<Main.dusts.size();i++) {
			System.out.println(i+":");
			Main.dusts.get(i).force.x=0;
			Main.dusts.get(i).force.y=0;
			for(int j=0;j<Main.dusts.size();j++) {
				if(i==j)
					continue;
				Vector tf=new Vector(Main.dusts.get(i).position
						.getDistanceTo(Main.dusts.get(j).position));
				tf.normal();
				tf.mult(G*Main.dusts.get(i).mass*Main.dusts.get(j)
						.mass/Math.pow(Main.dusts.get(i).position
								.getDistanceTo(Main.dusts.get(j).position).getLength(),2));
				Main.dusts.get(i).addForce(tf);
				System.out.println("  addForceFrom"+j+":"+tf.x+","+tf.y);
			}
			Main.dusts.get(i).update();
		}
		Main.w.p.repaint();
	}
}
