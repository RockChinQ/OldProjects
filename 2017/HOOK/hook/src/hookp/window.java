package hookp;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class window {
	static gui g=new gui(750,560,0,0,true,"������־",true,false,true);
	static JTextArea jta= new JTextArea();
	JPanel jp=new JPanel();
	window(){
		jp.setLayout(null);
		jta.setSize(120, 60);
		jp.add(jta);
	    
		JScrollPane jsp = new JScrollPane(jta); //���ı�������ӹ�����
		jsp.setLocation(5, 5);//���þ��δ�С.��������Ϊ(�������ϽǺ�����x,�������Ͻ�������y�����γ��ȣ����ο��)
		jsp.setSize(735,520);
		jsp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//Ĭ�ϵ������ǳ����ı���Ż���ʾ�����������������ù�����һֱ��ʾ
		jp.add(jsp);
		jta.setText("��λ��༭���ű�(.ses) ������\nV1.0������� ����:Rock ��������Ȩ��\n=======================================================================");
		jta.setEditable(false);
		jta.setFont(new Font("",Font.BOLD,17));
		g.add(jp);
		g.setVisible(true);
	}
}
