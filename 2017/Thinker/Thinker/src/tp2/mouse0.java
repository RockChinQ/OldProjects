package tp2;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class mouse0 implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent arg0) {
		main.setStyle2();
		javax.swing.JOptionPane.showMessageDialog(null, "条件帮助：\n1)请在此文本框输入定理中的条件\n2)如要表示<条件1>或<条件2>成立，请输入\"<条件1>|<条件2>\"\n3)如要表示<条件1>和<条件2>成立，请输入\"<条件1>&<条件2>\"\n4)注意:当\"和\"与\"或\"同时存在时,\"|\"的优先级大于\"&\"\n4)提高优先级请为表达式加括号(全半角均可)\n    如:\n ┌──────────────────────────────┬───────────────────┐\n │自然语言                                                                                                                 │表示方法                                                                 │\n │<条件1>或<条件2>成立时条件成立                                                                │<条件1>|<条件2>                                                  │\n │<条件1>和<条件2>均成立时条件成立                                                            │<条件1>&<条件2>                                                │\n  │<条件1>和<条件2>成立 或者 <条件3>和<条件4>成立                              │(<条件1>&<条件2>)|(<条件3>&<条件4>)       │\n└──────────────────────────────┴───────────────────┘");
		main.setStyle();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		main.w.g.setTitle("点击查看帮助");
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		main.w.g.setTitle("输入定理");
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
