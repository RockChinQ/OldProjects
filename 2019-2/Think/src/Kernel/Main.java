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
		al.add("��lƽ������m");
		al.add("��l����n�ཻ�ڵ�P");
		kn.solve(al,"��n��ƽ������m");
	}

}
