package tp0;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class action3 implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if((arg0.getSource()==main.dr.e_setax&&main.dr.jtf0.getText().equals(""))||(arg0.getSource()==main.dr.e_setay&&main.dr.jtf1.getText().equals(""))){
			main.setStyle();
			javax.swing.JOptionPane.showMessageDialog(null,"数值不能为空！","数值不能为空！",0);
			main.setStyle2();
			return;
		}
		int x=0,y=0;
		if(arg0.getSource()==main.dr.e_setax)
			x=Integer.parseInt(main.dr.jtf0.getText());
		if(arg0.getSource()==main.dr.e_setay)
			y=Integer.parseInt(main.dr.jtf1.getText());
		for(int a=0;a<main.dr.xayc;a++){
			if(main.dr.xay[a][3]==1){
				if(arg0.getSource()==main.dr.e_setax)
					main.dr.xay[a][0]=x;
				if(arg0.getSource()==main.dr.e_setay)
					main.dr.xay[a][1]=y;
			}
		}
		main.dr.refresh();
		main.dr.p.repaint();
	}

}