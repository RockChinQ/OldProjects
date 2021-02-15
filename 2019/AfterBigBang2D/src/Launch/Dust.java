package Launch;

import java.util.ArrayList;

public class Dust {
	//ArrayList<Dust> links=new ArrayList<Dust>();
	Vector position=new Vector();
	Vector speed=new Vector();
	Vector force=new Vector();
	float mass=1;
	Dust(int x,int y){
		this.position.x=x;
		this.position.y=y;
	}
	void update() {
		Vector acc=new Vector(force);
		acc.mult(1.0/mass);//计算加速度
		speed.add(acc);//更新速度
		position.add(speed);//更新坐标
		System.out.println("  acc="+acc.x+","+acc.y+"\n  spd="+speed.x+","+speed.y);
	}
	void addForce(Vector force) {
		this.force.add(force);
	}
}
