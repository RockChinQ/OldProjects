package tp0;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class mouse0 implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(main.dr.mode==0&&arg0.getX()<=450&&arg0.getY()<=470){
			main.dr.g.setTitle("Thinker-����Ƹ�ͼ-��("+arg0.getX()+","+arg0.getY()+")��λ�û�����һ����");
			main.dr.isb[arg0.getX()][arg0.getY()]=true;
			main.dr.xay[main.dr.xayc][0]=arg0.getX();
			main.dr.xay[main.dr.xayc][1]=arg0.getY();
			main.dr.xay[main.dr.xayc][2]=(char)main.dr.a_ele.getSelectedIndex()+'A';
			//main.dr.note++;
			//System.out.println(main.dr.a_num.getSelectedIndex());
			if(main.dr.a_num.getSelectedItem().toString().equals(" "))
				main.dr.xay[main.dr.xayc][4]=4097;
			else
				main.dr.xay[main.dr.xayc][4]=Integer.parseInt(main.dr.a_num.getSelectedItem().toString());
			if(main.dr.a_ele.getSelectedIndex()<25)
				main.dr.a_ele.setSelectedIndex(main.dr.a_ele.getSelectedIndex()+1);
			else{
				
			}
			main.dr.xayc++;
			main.dr.page=(main.dr.xayc-1)/18;
			main.dr.refresh();
			main.dr.p.repaint();
			main.dr.setnum();
			main.dr.g.setVisible(true);
		}else if(main.dr.mode!=0){
			main.dr.g.setTitle("Thinker-����Ƹ�ͼ-�����߰�ť��ʼ����...");
		}
	}

	private int getnote() {
		int i=0;
		block:for(;i==0;){
			try{
			i=(javax.swing.JOptionPane.showInputDialog("�����ǣ�������д��ĸ����")).toCharArray()[0];
			}catch(Exception e){
				javax.swing.JOptionPane.showMessageDialog(null, "��ʽ����ȷ�������뵥����д��ĸ");
				continue;
			}
			if(i>=65&&i<91){
				for(int a=0;a<main.dr.chac;a++){
					if(main.dr.cha[a]==i){
						javax.swing.JOptionPane.showMessageDialog(null, "���Ϊ"+(char)i+"�ĵ��Ѵ��ڣ�");
						i=0;
						continue block;
					}
				}
				break;
			}else{
				javax.swing.JOptionPane.showMessageDialog(null, "��ʽ����ȷ�������뵥����д��ĸ");
			}
			i=0;
		}
		main.dr.cha[main.dr.chac]=(char) i;
		main.dr.chac++;
		return i;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
