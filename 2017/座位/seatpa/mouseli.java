package seatpa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class mouseli implements MouseListener, ActionListener {
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource()==window.jl0&&main.label==0){
			window.g.dispose();
			try {new Thread().sleep(200);} catch (InterruptedException e) {}
			new addf();
			main.label=1;
		}
		if(arg0.getSource()==addf.jl){
			label:for(int a=0;a<15;a++){
				for(int b=0;b<15;b++){
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
				}
			}
		}
	}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	
	
	public void actionPerformed(ActionEvent arg0) {
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
