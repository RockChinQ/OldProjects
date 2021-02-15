package Kernel;

import java.util.ArrayList;

public class Main {
	static TheoremDatabase tdb=new TheoremDatabase();
	static Kernel kn=new Kernel();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		tdb.importTheorem("theorem");
		//tdb.printAllTheorem();
		System.out.println("finished.");
		
		//test
		ArrayList<String> al=new ArrayList<String>();
		al.add("线l平行于线m");
		al.add("线l与线n相交于点P");
		kn.solve(al,"线n不平行于线m");
	}

}
