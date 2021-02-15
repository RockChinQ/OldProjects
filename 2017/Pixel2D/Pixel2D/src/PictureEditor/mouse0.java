package PictureEditor;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class mouse0 implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int x=main.w.p2d.getXonPanel(arg0.getX()),y=main.w.p2d.getYonPanel(arg0.getY());
		if(main.p.mode==painter.MODE_POINT){
			main.w.p2d.setGrid(x, y, main.p.cl);
		}else if(main.p.mode==painter.MODE_LINE){
			if(main.p.point1.getX()==-1){
				main.p.point1.x=x;
				main.p.point1.y=y;
			}else if(main.p.point1.getX()!=-1){
				main.w.p2d.drawLine(main.p.point1.x, main.p.point1.y, x, y, main.p.cl);
				main.p.point1.x=-1;
			}
		}else if(main.p.mode==painter.MODE_CIRCLE){
			/*if(main.p.point1.getX()==-1){
				main.p.point1.x=x;
				main.p.point1.y=y;
			}else if(main.p.point1.getX()!=-1){
				main.w.p2d.drawCircle(main.p.point1.x, main.p.point1.y, main.w.p2d.getDistance(main.p.point1.x, main.p.point1.y, x, y),main.p.cl, false);
				main.p.point1.x=-1;
			}*/
			main.w.p2d.drawCircle(x,y,Integer.parseInt(javax.swing.JOptionPane.showInputDialog(null, "输入半径：")),main.p.cl, false);
			
		}else if(main.p.mode==painter.MODE_RECT){
			if(main.p.point1.getX()==-1){
				main.p.point1.x=x;
				main.p.point1.y=y;
			}else if(main.p.point1.getX()!=-1){
				main.w.p2d.drawRect(main.p.point1.x, main.p.point1.y, x, y, main.p.cl,false);
				main.p.point1.x=-1;
			}
		}else if(main.p.mode==painter.MODE_TEXT){
			
		}else if(main.p.mode==painter.MODE_GET){
			main.p.cl=main.w.p2d.getGird(x, y);
			main.w.color.setForeground(main.p.cl);
		}else if(main.p.mode==painter.MODE_DEL){
			main.w.p2d.setGrid(x, y, main.p.bg);
		}else if(main.p.mode==painter.MODE_TRANS){
			main.w.p2d.setGrid(x, y, null);
		}
		main.w.message.setText("选定坐标"+main.p.point1);
		main.w.g.setVisible(true);
		main.w.p2d.repaint();
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
