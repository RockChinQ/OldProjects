package fstj;

public class main {
	static int co=0;
	public static void main(String[] args) throws Exception{
		run();
		javax.swing.JOptionPane.showMessageDialog(null, "��ӭʹ��!   �����ʾ�հ״�������С�����ڲ����仹ԭ��");
	}
	static void run() {
		co=Integer.parseInt(javax.swing.JOptionPane.showInputDialog("������Ҫͳ�Ƶ�����:"));
		if(co<91&&co>0){
			try {
				new win();
				return;
			} catch (Exception e) {
				javax.swing.JOptionPane.showMessageDialog(null, "����:"+e);
			}
		}else{
			javax.swing.JOptionPane.showMessageDialog(null, "ͳ�Ƶ��������ܴ���90��С��1  ����������");
			run();
		}
	}
}
