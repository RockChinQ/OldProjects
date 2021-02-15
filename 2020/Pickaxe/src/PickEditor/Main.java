package PickEditor;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String image=javax.swing.JOptionPane.showInputDialog("jpg�ļ�·��  28,28,28�Զ��滻Ϊ͸��");
		String image;
		try {
			image=args[0];
		}catch(Exception e) {
			image=javax.swing.JOptionPane.showInputDialog("jpg�ļ�·��  28,28,28�Զ��滻Ϊ͸��");
		}
		//int[] rgb = new int[3];

		File file = new File(image);

		BufferedImage bi = null;

		try {

			bi = ImageIO.read(file);

		} catch (Exception e) {

			e.printStackTrace();

		}

		int width = bi.getWidth();

		int height = bi.getHeight();

		int minx = bi.getMinX();

		int miny = bi.getMinY();

		System.out.println("width=" + width + ",height=" + height + ".");

		System.out.println("minx=" + minx + ",miniy=" + miny + ".");
		System.out.println("����ת��....");
		StringBuffer fs=new StringBuffer(width+","+height+":");
		int ͸��=new Color(28,28,28).getRGB();
		System.out.println(͸��);
		for (int i = minx; i < height; i++) {

			for (int j = miny; j < width; j++) {

				int pixel = bi.getRGB(j, i); // �������д��뽫һ������ת��ΪRGB����

				
				System.out.println("i=" + i + ",j=" + j + ":(" + pixel+ ")");
				if(pixel==͸��) {
					fs.append("n;");
					continue;
				}
				fs.append(pixel+";");
			}
		}
		FileRW.write(image+".pick", fs.toString(),false);
		System.out.println(image+" ת�����.");
		System.exit(0);
	}

}
