package seatpa;

import java.io.File;

import javax.swing.JFileChooser;

public class open {
	class nnn{}
	open(){
		try{
		JFileChooser jfc=new JFileChooser();
		int r = jfc.showOpenDialog(null);
		File f=jfc.getSelectedFile();
		String str=f.getPath();
		main.ssb=new StringBuffer(str);
		Thread.sleep(500);
		new editgui();
		}catch(Exception e){
			new file0().write(main.sou+"setting","false �� 17 154 217 234",false);
			javax.swing.JOptionPane.showMessageDialog(null,"��Ǹ���ļ���ʧ�ܣ�\nԭ��"+e+"\n*�����Ǽ��㽨�飺\n(1)ȷ����򿪵��ļ��Ǳ�����֧�ֵı����ʽ(.stb)\n(2)����������\n(3)ѡ����ļ��������𻵣����½�һ��\n("+main.ssb+")","��λ��༭��",0,null);
			System.exit(-1);
		}
	}
}
