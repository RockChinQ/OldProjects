package Pickaxe;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LayerManagerPanel extends JPanel{
	Color bg=new Color(25,25,25);
	Font bFont=new Font("",Font.PLAIN,15);
	
	ArrayList<LayerPanel> layerPanel=new ArrayList<LayerPanel>();
	class LayerPanel extends JPanel{
		JLabel thumb,name,type;
		LayerPanel(String name,ImageIcon thumb,String type){
			this.thumb=new JLabel(thumb);
			this.name=new JLabel(name);
			this.type=new JLabel(type);
			this.setLayout(null);
			
			this.add(this.type);
			this.add(this.thumb);
			this.add(this.name);
		}
		public void setLayerPanelSize(int w,int h) {
			this.setBackground(new Color(40,40,40));
			this.setSize(w, h);
			this.thumb.setLocation(1, 2);
			this.type.setLocation(h-3,0);
			type.setSize(14,14);
			type.setFont(new Font("",Font.ITALIC,12));
			type.setForeground(new Color(5,204,226));
			this.thumb.setSize(h-4, h-4);
			this.name.setLocation(h+5, 0);
			this.name.setSize(w-(h+2), h);
			name.setForeground(new Color(5,204,226));
		}
	}
	LayerManagerPanel(){
		this.setBackground(bg);
		this.setLayout(null);
		this.setSize(280, 280);
		
	}
	void update(int begin) {
		layerPanel=new ArrayList<LayerPanel>();
		int size=EditingManager.main.layer.size();
		System.out.println("size£º"+size);
		for(int i=begin;i<size;i++) {
			if(this.getHeight()-(36*i)-45<20) {
				break;
			}
			layerPanel.add(new LayerPanel(EditingManager.main.layer.get(i).getName()+" "+i
					,new ImageIcon(Boot.filePath+"thumb.png")
					,EditingManager.main.layer.get(i).isShapable()?"v":""));
			layerPanel.get(i).setLayerPanelSize(this.getWidth()-20, 34);
			layerPanel.get(i).setLocation(10,this.getHeight()-(36*i)-55);
			this.add(layerPanel.get(i));
		}
	}
}
