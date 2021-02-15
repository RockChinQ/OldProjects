package seatpa;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class actionli0 implements ActionListener {
	class nnn{}
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==editgui.saveb){
			for(int a=0;a<editgui.l0;a++){
				for(int b=0;b<editgui.l1;b++){
					if(editgui.isseat[a][b]){
						editgui.name[a][b]=new StringBuffer(editgui.jbt[a][b].getText());
					}
				}
			}
			if(new save().save0()){
				JOptionPane.showMessageDialog(null, "保存成功！");
				editgui.corr=false;
			}
		}
		if(arg0.getSource()==editgui.crpi){
			new pic();
		}
		if(arg0.getSource()==pic.jbt){
			try {
				String ss=javax.swing.JOptionPane.showInputDialog("输入文件名:");
				ImageIO.write(pic.d.bi, "jpg",new File(System.getProperty("user.home")+"\\Desktop\\"+ss+".jpg"));
				JOptionPane.showMessageDialog(null, "已保存 "+ss+".jpg 到桌面,文件完整路径:"+(System.getProperty("user.home")+"\\Desktop\\"+ss+".jpg"));
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(arg0.getSource()==editgui.sp){
			if(main.b){
				setting.g.setVisible(true);
			}else{
				new setting();
				main.b=true;
			}
		}
		if(arg0.getSource()==setting.jbt0){
			try {
				editgui.jp.setBackground(new Color(setting.re[setting.col],setting.gr[setting.col],setting.bl[setting.col]));
				new file0().write(main.sou+"setting",setting.jcb.isSelected()+" "+setting.jcb1.getSelectedItem().toString()+" "+setting.col+" "+setting.re[setting.col]+" "+setting.gr[setting.col]+" "+setting.bl[setting.col],false);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setting.g.setVisible(false);
		}
	}

}
