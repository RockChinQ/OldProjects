package PictureEditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class action1 implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==main.w.point){
			main.p.mode=painter.MODE_POINT;
		}else if(arg0.getSource()==main.w.line){
			main.p.mode=painter.MODE_LINE;
		}else if(arg0.getSource()==main.w.circle){
			main.p.mode=painter.MODE_CIRCLE;
		}else if(arg0.getSource()==main.w.rect){
			main.p.mode=painter.MODE_RECT;
		}else if(arg0.getSource()==main.w.text){
			main.p.mode=painter.MODE_TEXT;
		}else if(arg0.getSource()==main.w.get){
			main.p.mode=painter.MODE_GET;
		}else if(arg0.getSource()==main.w.del){
			main.p.mode=painter.MODE_DEL;
		}else if(arg0.getSource()==main.w.trans){
			main.p.mode=painter.MODE_TRANS;
		}
	}

}
