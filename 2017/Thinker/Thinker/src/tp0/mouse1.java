package tp0;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class mouse1 implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		for(int a=0;a<18;a++){
			if(arg0.getSource().equals(main.dr.poa[a])){
				if(arg0.getButton()==MouseEvent.BUTTON1){
					if((!main.dr.poa[a].getText().equals("<ÔÝÎÞ>"))&&main.dr.poa[a].getBackground().equals(Color.WHITE)){
						main.dr.poa[a].setBackground(Color.YELLOW);
						main.dr.xay[main.dr.page*18+a][3]=1;
						main.dr.chose++;
						main.dr.refresh();
					}else if(main.dr.poa[a].getBackground().equals(Color.YELLOW)){
						main.dr.poa[a].setBackground(Color.WHITE);
						main.dr.xay[main.dr.page*18+a][3]=0;
						main.dr.chose--;
						main.dr.refresh();
					}
				}
			}
		}
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