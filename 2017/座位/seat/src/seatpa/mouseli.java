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
						addf.jl.setText("ͳ�ƣ�"+addf.tj+"(������)");*/
					}else if(addf.seat[a][b].getIcon().equals(addf.ii2)){
						addf.seat[a][b].setIcon(addf.ii);
						addf.g.setVisible(true);
						addf.ba[a][b]=false;
						addf.tj--;
						addf.jl.setText("ͳ�ƣ�"+addf.tj+"(������)");
					}
				}
			}
		}
	}
	public void mouseEntered(MouseEvent arg0) {
		if(arg0.getSource()==window.jl0){
			window.jl.setText("�½�һ����λ���ļ�(.stb�ļ�).");
		}else if(arg0.getSource()==window.jl1){
			window.jl.setText("�����е���λ���ļ�(.stb�ļ�).");
		}
		window.jl.setLocation(50, 15);
	}
	public void mouseExited(MouseEvent arg0) {
		window.jl.setText("��ӭʹ����λ��༭������ѡ��һ��ѡ��:");
		window.jl.setLocation(10, 15);
	}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==addf.jbt2){
			if(addf.tj!=0&&javax.swing.JOptionPane.showConfirmDialog(null, "�������ļ���")==0){
				StringBuffer[][] name=new StringBuffer[15][15];
				for(int a=0;a<15;a++){
					for(int b=0;b<15;b++){
						name[a][b]=new StringBuffer("��������");
					}
				}
				
				JFileChooser jfc=new JFileChooser();
				int r = jfc.showSaveDialog(null);
				File f=jfc.getSelectedFile();   //�����ļ�
				makeFile mf=new makeFile();
				String sss=mf.make(f,addf.ba,name);
				if(javax.swing.JOptionPane.showConfirmDialog(null, "�Ƿ�ʼ�༭�ոմ�������λ��(.stb)�ļ�","�򿪣�", 2)==0){
					main.ssb=new StringBuffer(sss);
					addf.g.dispose();
					new editgui();
				}
			}else if(addf.tj==0){
				javax.swing.JOptionPane.showMessageDialog(null, "���ܴ�������λ�ı��");
			}
		}
		if(arg0.getSource()==addf.jbt){
			if(addf.jbt.getText().equals("�����Χѡȡ:��")){
				addf.jbt.setText("�����Χѡȡ:��");
				addf.lab=true;
			}else{
				addf.jbt.setText("�����Χѡȡ:��");
				addf.lab=false;
				addf.loa[0]=addf.loa[1]=-1;
			}
		}
		label:for(int a=0;a<15;a++){
			addf.jl.setText("ͳ�ƣ�"+addf.tj+"����ɨ��...");
			for(int b=0;b<15;b++){
				if(arg0.getSource()==addf.seat[a][b]){
					if(!addf.lab){
						if(addf.seat[a][b].getIcon().equals(addf.ii)){
							addf.seat[a][b].setIcon(addf.ii2);
							addf.g.setVisible(true);
							addf.ba[a][b]=true;
							addf.tj++;
							addf.jl.setText("ͳ�ƣ�"+addf.tj+"(������)");
							break label;
						}else if(addf.seat[a][b].getIcon().equals(addf.ii2)){
							addf.seat[a][b].setIcon(addf.ii);
							addf.g.setVisible(true);
							addf.ba[a][b]=false;
							addf.tj--;
							addf.jl.setText("ͳ�ƣ�"+addf.tj+"(������)");
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
								addf.jl.setText("ͳ�ƣ�"+addf.tj+"(������)");
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
		addf.jl.setText("ͳ�ƣ�"+addf.tj+"(������)");
	}

}
