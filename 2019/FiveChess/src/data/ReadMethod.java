package data;

import java.awt.Point;

public class ReadMethod {
	public class Method{
		String file;    //�ļ�����
		Point size;    //����
		short[][] chessCode;   //ÿ�����ӵ����Ӵ���
		Point put;        //���ӵ�
		char[] shape;  //�任�Զ����ɴ���:e.g. 1y:˳ʱ���ʮ����תy��ԳƷ�ת  1s:˳ʱ���ʮ����ת�޷�ת  xy:x��ԳƷ�תy��ԳƷ�ת
		public Method() {
			
		}
		/*
		public void load(String file) {
			this.file=new String(file);
			String fileStr[]=FileRW.read(file).split("\r\n");//�ļ���ȡ�ָ�
			//���г���
			String sizestr[]=fileStr[0].split(",");
			//���д���
			String codestr[]=fileStr[1].split(",");
			//�������ӵ�
			String putstr[]=fileStr[2].split(",");
			//���п���ת��
			String roundstr[]=fileStr[3].split(",");
			
			size.setLocation(DataBase.str2int(sizestr[0]),DataBase.str2int(sizestr[1]));
			int codeIndex=0;
			for(int y=0;y<size.y;y++) {
				for(int x=0;x<size.x;x++) {
					chessCode[y][x]=(short) DataBase.str2int(codestr[codeIndex++]);
				}
			}
			put.setLocation(DataBase.str2int(putstr[0]),DataBase.str2int(putstr[1]));
		}*/
	}
	public static void init() {
		
	}
}
