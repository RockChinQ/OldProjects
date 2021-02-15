package tp0;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class editac implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==main.dr.e_del){
			boolean del=false;
			main.setStyle();
			for(int a=0;a<main.dr.xayc;a++){
				if(main.dr.xay[a][3]==1){
					int t=javax.swing.JOptionPane.showConfirmDialog(null, "删除第"+(a+1)+"个点("+(char)main.dr.xay[a][2]+"点)？", "删除第"+(a+1)+"个点？", 2,3, null);
					if(del||t==0){
						main.dr.poa[a].setBackground(Color.WHITE);
						main.dr.xay[main.dr.page*18+a][3]=0;
						main.dr.chose--;
						main.dr.refresh();
						main.dr.delPoint(a);
						break;
					}
				}
			}
			main.setStyle2();
		}else if(arg0.getSource()==main.dr.e_save){
			if(main.dr.chose==1){
				for(int a=0;a<main.dr.xayc;a++){
					try{
					if(main.dr.xay[a][3]==1){
						main.dr.xay[a][0]=Integer.parseInt(main.dr.jtf0.getText());
						main.dr.xay[a][1]=Integer.parseInt(main.dr.jtf1.getText());
						break;
					}
					}catch(NumberFormatException e){main.setStyle(); javax.swing.JOptionPane.showMessageDialog(null, "数值不能为空","Error!",0); main.setStyle2();}
				}
				main.dr.refresh();
				main.dr.p.repaint();
			}
		}
	}

}