package test;

import java.awt.Color;
import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a=0x1f,b=0x4;
		int c=(a^b);
		//System.out.println(toBinary(a)+"\n"+toBinary(b)+"\n"+toBinary(c));
		//System.out.println(new Color(45));
		ArrayList<Object> temp=new ArrayList<Object>();
		temp.add("123");
		System.out.println(Integer.parseInt((String) temp.get(0)));
	}
	static String toBinary(int n) {
		return Integer.toBinaryString(n);
	}

}
