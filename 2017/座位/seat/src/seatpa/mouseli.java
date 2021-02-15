package seatpa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class mouseli implements MouseListener, ActionListener {
	class nnn{}
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource()==window.jl0&&main.label==0){
			window.g.dispose();
			//try {new Thread().sleep(200);} catch (InterruptedException e) {}
			new addf();
			main.label=1;
		}
		if(arg0.getSource()==window.jl1){
			main.bb=false;
			window.g.dispose();
			new open();
			main.bb=true;
			//addf.g.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			//addf.g.dispose();
		}
		
		if(arg0.getSource()==addf.jl){
			label:for(int a=0;a<15;a++){
				for(int b=0;b<15;b++){
					if(addf.seat[a][b].getIcon().equals(addf.ii)){
						continue;
						/*addf.seat[a][b].setIcon(addf.ii2);
						addf.g.setVisible(true);
						addf.ba[a][b]=true;
						addf.tj++;
						addf.jl.setText("统计："+addf.tj+"(点此清空)");*/
					}else if(addf.seat[a][b].getIcon().equals(addf.ii2)){
						addf.seat[a][b].setIcon(addf.ii);
						addf.g.setVisible(true);
						addf.ba[a][b]=false;
						addf.tj--;
						addf.jl.setText("统计："+addf.tj+"(点此清空)");
					}
				}
			}
		}
	}
	public void mouseEntered(MouseEvent arg0) {
		if(arg0.getSource()==window.jl0){
			window.jl.setText("新建一个座位表文件(.stb文件).");
		}else if(arg0.getSource()==window.jl1){
			window.jl.setText("打开已有的座位表文件(.stb文件).");
		}
		window.jl.setLocation(50, 15);
	}
	public void mouseExited(MouseEvent arg0) {
		window.jl.setText("欢迎使用座位表编辑器，请选择一个选项:");
		window.jl.setLocation(10, 15);
	}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==addf.jbt2){
			if(addf.tj!=0&&javax.swing.JOptionPane.showConfirmDialog(null, "创建新文件？")==0){
				StringBuffer[][] name=new StringBuffer[15][15];
				for(int a=0;a<15;a++){
					for(int b=0;b<15;b++){
						name[a][b]=new StringBuffer("键入姓名");
					}
				}
				
				JFileChooser jfc=new JFileChooser();
				int r = jfc.showSaveDialog(null);
				File f=jfc.getSelectedFile();   //传入文件
				makeFile mf=new makeFile();
				String sss=mf.make(f,addf.ba,name);
				if(javax.swing.JOptionPane.showConfirmDialog(null, "是否开始编辑刚刚创建的座位表(.stb)文件","打开？", 2)==0){
					main.ssb=new StringBuffer(sss);
					addf.g.dispose();
					new editgui();
				}
			}else if(addf.tj==0){
				javax.swing.JOptionPane.showMessageDialog(null, "不能创建无座位的表格！");
			}
		}
		if(arg0.getSource()==addf.jbt){
			if(addf.jbt.getText().equals("两点大范围选取:关")){
				addf.jbt.setText("两点大范围选取:开");
				addf.lab=true;
			}else{
				addf.jbt.setText("两点大范围选取:关");
				addf.lab=false;
				addf.loa[0]=addf.loa[1]=-1;
			}
		}
		label:for(int a=0;a<15;a++){
			addf.jl.setText("统计："+addf.tj+"正在扫描...");
			for(int b=0;b<15;b++){
				if(arg0.getSource()==addf.seat[a][b]){
					if(!addf.lab){
						if(addf.seat[a][b].getIcon().equals(addf.ii)){
							addf.seat[a][b].setIcon(addf.ii2);
							addf.g.setVisible(true);
							addf.ba[a][b]=true;
							addf.tj++;
							addf.jl.setText("统计："+addf.tj+"(点此清空)");
							break label;
						}else if(addf.seat[a][b].getIcon().equals(addf.ii2)){
							addf.seat[a][b].setIcon(addf.ii);
							addf.g.setVisible(true);
							addf.ba[a][b]=false;
							addf.tj--;
							addf.jl.setText("统计："+addf.tj+"(点此清空)");
							break label;
						}
					}else if(addf.lab){
						if(addf.seat[a][b].getIcon().equals(addf.ii3)){
							addf.loa[0]=addf.loa[1]=-1;
							addf.seat[a][b].setIcon(addf.ii);
						}else if(addf.loa[0]==-1){
							if(addf.seat[a][b].getIcon().equals(addf.ii2)){
								addf.seat[a][b].setIcon(addf.ii);
								addf.g.setVisible(true);
								addf.ba[a][b]=false;
								addf.tj--;
								addf.jl.setText("统计："+addf.tj+"(点此清空)");
								break label;
							}
							addf.loa[0]=a;
							addf.loa[1]=b;
							addf.seat[a][b].setIcon(addf.ii3);
						}else if(addf.loa[0]!=-1){
							int x=Math.min(a, addf.loa[0]),y=Math.min(b, addf.loa[1]),yt=y;
							int mx=Math.max(a, addf.loa[0]),my=Math.max(b, addf.loa[1]);
							for(;x<mx+1;x++){
								for(y=yt;y<my+1;y++){
									if(addf.seat[x][y].getIcon().equals(addf.ii2)){
										continue;
									}
									addf.seat[x][y].setIcon(addf.ii2);
									addf.ba[x][y]=true;
									addf.loa[0]=addf.loa[1]=-1;
									addf.tj++;
								}
							}
						}
					}
				}
			}
		}
		addf.jl.setText("统计："+addf.tj+"(点此清空)");
	}

}
