package MainPkg;

import java.util.TimerTask;


public class Update extends TimerTask{
	public static final double G=10;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.updateAllDust();
	}
	public static void updateAllDust() {
		for(int i=0;i<Main.w.dusts.size();i++) {
			Vector force=new Vector(0,0);
			for(int j=0;j<Main.w.dusts.size();j++) {
				if(j!=i) {
					Vector tve=new Vector(Main.w.dusts.get(i).focus);
					tve.mult(Main.w.dusts.get(i)
							.focus.getDistanceTo(Main.w.dusts
							.get(j).focus));
					tve.normal();
					tve.mult(G*Main.w.dusts.get(i).points.size()
							*Main.w.dusts.get(j).points.size()
							/Math.pow(Main.w.dusts.get(i)
							.focus.getDistanceTo(Main.w.dusts
							.get(j).focus),2));
					System.out.println("       "+i+"."+j+":"+tve.x+","+tve.y);
					force.add(tve);
				}
			}
			force.mult(1/Main.w.dusts.get(i).points.size());
			Main.w.dusts.get(i).acc=force;
			System.out.println("acc="+force.x+" "+force.y);
			Main.w.dusts.get(i).update();
			Main.w.p.repaint();
		}
	}

}
