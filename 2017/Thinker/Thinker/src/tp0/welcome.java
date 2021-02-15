package tp0;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

public class welcome {
	welcome(){
		JWindow w0=new JWindow();
		JWindow w1=new JWindow();
		JWindow w2=new JWindow();
		JWindow w3=new JWindow();
		ImageIcon ii=new ImageIcon("bor.jpg");
		JLabel jl0=new JLabel(ii);
		JLabel jl1=new JLabel(ii);
		JLabel jl2=new JLabel(ii);
		JLabel jl3=new JLabel(ii);
		jl0.setSize(400, 400);
		jl1.setSize(400, 400);
		jl2.setSize(400, 400);
		jl3.setSize(400, 400);
		jl0.setLocation(0, 0);
		jl1.setLocation(0, 0);
		jl2.setLocation(0, 0);
		jl3.setLocation(0, 0);
		w0.setLayout(null);
		w1.setLayout(null);
		w2.setLayout(null);
		w3.setLayout(null);
		w0.add(jl0);
		w1.add(jl1);
		w2.add(jl2);
		w3.add(jl3);
		int x=main.dr.g.getX()+200,y=main.dr.g.getY()+200;
		w0.setLocation(x, y);
		w1.setLocation(x+398, y);
		w2.setLocation(x,y+198);
		w3.setLocation(x, y);
		w0.setVisible(true);
		w1.setVisible(true);
		w2.setVisible(true);
		w3.setVisible(true);
		int sec=12;
		try{
			for(int a=0;a<406;a+=10){
				new Thread().sleep(sec);
				w0.setSize(a, 3);
				w2.setSize(a, 3);
			}
			for(int a=0;a<200;a+=8){
				new Thread().sleep(sec);
				w1.setSize(3,a);
				w3.setSize(3, a);
			}
		}catch(Exception e){}
		JWindow jw0=new JWindow();
		JLabel jlx=new JLabel(new ImageIcon("welcome2.png"));
		jw0.setBackground(Color.WHITE);
		jw0.setSize(400, 200);
		jw0.setLocation(main.dr.g.getX()+200,main.dr.g.getY()+200);
		jw0.setVisible(true);
		jw0.setLayout(null);
		jlx.setSize(400, 200);
		jlx.setLocation(0, 0);
		jw0.add(jlx);
		System.out.println();
		w0.dispose();
		w1.dispose();
		w2.dispose();
		w3.dispose();
		try {
			new Thread().sleep(1000);
			jw0.dispose();
			return;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
