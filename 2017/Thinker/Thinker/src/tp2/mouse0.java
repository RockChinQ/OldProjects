package tp2;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class mouse0 implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent arg0) {
		main.setStyle2();
		javax.swing.JOptionPane.showMessageDialog(null, "����������\n1)���ڴ��ı������붨���е�����\n2)��Ҫ��ʾ<����1>��<����2>������������\"<����1>|<����2>\"\n3)��Ҫ��ʾ<����1>��<����2>������������\"<����1>&<����2>\"\n4)ע��:��\"��\"��\"��\"ͬʱ����ʱ,\"|\"�����ȼ�����\"&\"\n4)������ȼ���Ϊ���ʽ������(ȫ��Ǿ���)\n    ��:\n ���������������������������������������������������������������Щ���������������������������������������\n ����Ȼ����                                                                                                                 ����ʾ����                                                                 ��\n ��<����1>��<����2>����ʱ��������                                                                ��<����1>|<����2>                                                  ��\n ��<����1>��<����2>������ʱ��������                                                            ��<����1>&<����2>                                                ��\n  ��<����1>��<����2>���� ���� <����3>��<����4>����                              ��(<����1>&<����2>)|(<����3>&<����4>)       ��\n���������������������������������������������������������������ة���������������������������������������");
		main.setStyle();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		main.w.g.setTitle("����鿴����");
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		main.w.g.setTitle("���붨��");
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
