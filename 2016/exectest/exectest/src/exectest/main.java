package exectest;

import java.awt.HeadlessException;
import java.io.IOException;

public class main {
	public static void main(String[] args){
		try {
			Runtime.getRuntime().exec(javax.swing.JOptionPane.showInputDialog("���룺"));
		} catch (Exception e) {
		}
		System.exit(0);
	}
}
