package MainPkg;

import java.awt.Point;

public class Vector {
	public double x=0,y=0;
	public Vector(){
		
	}
	public Vector(double x,double y){
		this.x=x;
		this.y=y;
	}
	public Vector(Point p) {
		this.x=p.x;
		this.y=p.y;
	}
	public Vector(Vector v) {
		this.x=v.x;
		this.y=v.y;
	}
	public void add(Vector v) {
		this.x+=v.x;
		this.y+=v.y;
	}
	public void add(double num) {
		this.x+=num;
		this.y+=num;
	}
	public void mult(double num) {
		this.x*=num;
		this.y*=num;
	}
	public void normal() {
		double length=getLength();
		x/=length;
		y/=length;
	}
	public double getLength() {
		return Math.sqrt(this.x*this.x+this.y*this.y);
	}
	public Vector oppo() {
		return new Vector(0.0-this.x,0.0-this.y);
	}
	public double getDistanceTo(Vector v) {
		return v.getLength()-this.getLength();
	}
}
