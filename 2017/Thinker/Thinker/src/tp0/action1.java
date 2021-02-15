package tp0;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class action1 implements ActionListener {
//»»Ò³
	@Override
	public void actionPerformed(ActionEvent ar) {
		if(ar.getSource().equals(main.dr.next)){
			if(main.dr.xayc>(main.dr.page+1)*18){
				main.dr.page++;
				//System.out.println("************"+main.dr.page);
				main.dr.refresh();
			}
		}else if(ar.getSource().equals(main.dr.last)){
			if(main.dr.page!=0){
				main.dr.page--;
				main.dr.refresh();
			}
		}else if(ar.getSource().equals(main.dr.next2)){
			if(main.dr.papbc>(main.dr.page2+1)*18){
				main.dr.page2++;
				//System.out.println("************"+main.dr.page);
				main.dr.refresh2();
			}
		}else if(ar.getSource().equals(main.dr.last2)){
			if(main.dr.page2!=0){
				main.dr.page2--;
				main.dr.refresh2();
			}
		}
	}

}
