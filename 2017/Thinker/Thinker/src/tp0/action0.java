package tp0;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class action0 implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(main.dr.mode==-1){
			main.dr.mode=0;
			main.dr.jbt0.setIcon(main.dr.ii1);
		}else if(main.dr.mode==0){
			main.dr.mode=-1;
			main.dr.jbt0.setIcon(main.dr.ii0);
		}
		main.dr.g.setTitle("Thinker-请绘制附图-无消息");
	}

}
