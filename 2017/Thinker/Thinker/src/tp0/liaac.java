package tp0;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class liaac implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		for(int a=0;a<main.dr.papbc;a++){
			if(main.dr.lia[a].equals(arg0.getSource())){
				main.setStyle();
				int i=javax.swing.JOptionPane.showConfirmDialog(null, "你确定要删除线"+(char)main.dr.papb[main.dr.page2*18+a][0]+"~"+(main.dr.papb[main.dr.page*18+a][3]==4097?"NULL":main.dr.papb[main.dr.page*18+a][3])+" "+(char)main.dr.papb[main.dr.page2*18+a][1]+"~"+(main.dr.papb[main.dr.page*18+a][4]==4097?"NULL":main.dr.papb[main.dr.page*18+a][4])+"吗？","删除", 1);
				main.setStyle2();
				if(i==0){
					main.dr.delLine(main.dr.page2*18+a);
					main.dr.refresh2();
					main.dr.p.repaint();
					main.dr.g.setVisible(true);
					break;
				}
			}
		}
	}

}
