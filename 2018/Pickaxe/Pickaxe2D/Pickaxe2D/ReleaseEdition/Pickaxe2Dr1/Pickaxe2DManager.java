package Pickaxe2Dr1;

import java.awt.Color;
import java.util.ArrayList;

public class Pickaxe2DManager {
	public static ArrayList<Pick2DPanel> panel=new ArrayList<Pick2DPanel>();
	static int panelAmount=0;
	public Pickaxe2DManager(){
		
	}
	public static int addPanel(int gridWidth,int gridHeight,int width,int height,Color preset) {
		panel.add(new Pick2DPanel(new Size(gridWidth,gridHeight)
				,new Size(width,height),preset,Pickaxe2DManager.panelAmount));
		return panelAmount++;
	}
	public static int addPanel(Pick2DPanel p2dp) {
		panel.add(p2dp);
		return panelAmount++;
	}
	/*��������ƺ���û��ʲô����
	public int insertPanel(int gridWidth,int gridHeight,int width,int height,Color preset,int location) {
		panel.add(location,new Pick2DPanel(new Size(gridWidth,gridHeight)
				,new Size(width,height),preset,Pickaxe2DManager.panelAmount));
		return location+1;  //ע�� �˴������ArrayList����ʱ��λ��
	}*/
	public static Pick2DPanel getPanel(int panelLabel) {
		return panel.get(panelLabel);
	}
}
