package test;

import java.awt.Color;

import javax.swing.JFrame;

import Pickaxe2D.PickButton;
import Pickaxe2D.PickImage;
import Pickaxe2D.PickPanel;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame mjf=new JFrame();
		mjf.setSize(500, 500);
		mjf.setLocation(200, 200);
		mjf.setLayout(null);
		mjf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PickPanel pp=new PickPanel(20,20,20);
		pp.setBackground(Color.green);
		pp.setLocation(20, 20);
		pp.gridLine=true;
		
		pp.setGrid(0, 0, Color.black);
		pp.setGrid(1, 1, Color.blue);
		pp.loadPickImage(0, 0, new PickImage("center_road.png.pick"));
		pp.loadFrontPickImage(0, 0, new PickImage("btn_build_prd.png.pick"));
		mjf.add(pp);
		mjf.setVisible(true);
	}
	
	public static void loop() {
		
		
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				for(int k=0;k<100;k++) {
					for(int l=0;l<100;l++) {
						for(int m=0;m<100;m++) {
							for(int n=0;n<100;n++) {
								;
							}
						}
					}
				}
			}
		}
		
		
		
	}

}
