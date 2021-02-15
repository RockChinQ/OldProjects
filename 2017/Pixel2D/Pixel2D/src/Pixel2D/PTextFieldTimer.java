package Pixel2D;

import java.awt.Color;
import java.util.TimerTask;

public class PTextFieldTimer extends TimerTask{
	public Pixel2D p2d;
	public PTextFieldTimer(Pixel2D p2d){
		this.p2d=p2d;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int size=p2d.component.size();
		if(!p2d.component.isEmpty()&&p2d.component.get(p2d.TextFieldFocus).getClass().toString().equals("class Pixel2D.PTextField")){
			PixelComponent tpc=p2d.component.get(p2d.TextFieldFocus);
			((PTextField)tpc).showCursor(((PTextField)tpc).showing,p2d);
			((PTextField)tpc).showing=!((PTextField)tpc).showing;
		}
	}

}
