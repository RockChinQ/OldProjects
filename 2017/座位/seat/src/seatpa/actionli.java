package seatpa;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class actionli implements MouseListener,ActionListener {

	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {
		for(int a=0;a<editgui.l0;a++){
			for(int b=0;b<editgui.l1;b++){
				if(arg0.getSource()==editgui.jbt[a][b]&&arg0.getButton()==MouseEvent.BUTTON1){
					record.ms.push("corr "+a+" "+b+" "+editgui.jbt[a][b].getText());
					new record().delete();
					try{
						String s=javax.swing.JOptionPane.showInputDialog("�����ڴ���λ��ѧ������:");
						editgui.jbt[a][b].setText(s);
						if(editgui.jbt[a][b].getText().equals("")){
							editgui.jbt[a][b].setText("��������");
						}
					}catch(Exception e){
						editgui.jbt[a][b].setText("��������");
					}finally{
						new log().addlog((b+1)+"��"+(a+1)+"�б��޸�");
						editgui.corr=true;
					}
				}if(arg0.getSource()==editgui.jbt[a][b]&&arg0.getButton()==MouseEvent.BUTTON3){
					if(editgui.ax==-1){
						editgui.jbt[a][b].setBackground(Color.yellow);
						editgui.ax=a;
						editgui.bx=b;
					}else if(editgui.ax!=-1){
						record.ms.push("repl "+editgui.ax+" "+editgui.bx+" "+a+" "+b);
						new record().delete();
						String s=editgui.jbt[editgui.ax][editgui.bx].getText();       //ԭ
						editgui.jbt[editgui.ax][editgui.bx].setText(editgui.jbt[a][b].getText());
						editgui.jbt[a][b].setText(s);                                //��
						editgui.jbt[editgui.ax][editgui.bx].setBackground(Color.WHITE);
						editgui.ax=editgui.bx=-1;
						new log().addlog("�滻��λ����");
						editgui.corr=true;
					}
				}
			}
		}}
	public void mouseReleased(MouseEvent arg0) {}
	public void actionPerformed(ActionEvent e) {
		for(int a=0;a<editgui.l0;a++){
			for(int b=0;b<editgui.l1;b++){
				if(e.getSource()==editgui.jbt[a][b]){
					record.ms.push("corr "+a+" "+b+" "+editgui.jbt[a][b].getText());
					new record().delete();
					try{
						String s=javax.swing.JOptionPane.showInputDialog("�����ڴ���λ��ѧ������:");
						editgui.jbt[a][b].setText(s);
						if(editgui.jbt[a][b].getText().equals("")){
							editgui.jbt[a][b].setText("��������");
						}
					}catch(Exception e0){
						editgui.jbt[a][b].setText("��������");
					}finally{
						new log().addlog((b+1)+"��"+(a+1)+"�б��޸�");
						editgui.corr=true;
					}
				}
			}
		}
	}
	class nnn{}
}
