package MainPkg;

import java.awt.Point;
import java.util.ArrayList;

public class Dust {
	Vector focus=new Vector();
	ArrayList<Vector> points=new ArrayList<Vector>();
	Vector vel=new Vector(),acc=new Vector();
	Dust(Vector focus){
		this.focus=focus;
	}
	public void update() {
		vel.add(acc);  //�ٶ�
		Vector sl=new Vector(points.get(0)),el=new Vector(points.get(0));  //����Ͻǵ�point������½ǵ�point
												//Ϊ�˷������Լ�д��vector��
		for(int i=0;i<points.size();i++) {
			Vector tp=points.get(i);
			tp.add(vel);
			if(tp.x<sl.x)
				sl.x=tp.x;
			else if(tp.x>el.x)
				el.x=tp.x;
			if(tp.y<sl.y)
				sl.y=tp.y;
			else if(tp.y>el.y)
				el.y=tp.y;
		}
		sl.add(el);
		sl.mult(0.5);
		focus=new Vector(sl);
	}
}
