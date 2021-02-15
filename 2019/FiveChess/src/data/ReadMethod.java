package data;

import java.awt.Point;

public class ReadMethod {
	public class Method{
		String file;    //文件索引
		Point size;    //长宽
		short[][] chessCode;   //每个格子的棋子代码
		Point put;        //下子点
		char[] shape;  //变换自动生成代码:e.g. 1y:顺时针九十度旋转y轴对称翻转  1s:顺时针九十度旋转无翻转  xy:x轴对称翻转y轴对称翻转
		public Method() {
			
		}
		/*
		public void load(String file) {
			this.file=new String(file);
			String fileStr[]=FileRW.read(file).split("\r\n");//文件读取分割
			//首行长宽
			String sizestr[]=fileStr[0].split(",");
			//次行代码
			String codestr[]=fileStr[1].split(",");
			//三行下子点
			String putstr[]=fileStr[2].split(",");
			//四行可旋转性
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
