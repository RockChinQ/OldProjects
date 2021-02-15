package PickEditor;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String image=javax.swing.JOptionPane.showInputDialog("jpg文件路径  28,28,28自动替换为透明");
		String image;
		try {
			image=args[0];
		}catch(Exception e) {
			image=javax.swing.JOptionPane.showInputDialog("jpg文件路径  28,28,28自动替换为透明");
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
		System.out.println("正在转换....");
		StringBuffer fs=new StringBuffer(width+","+height+":");
		int 透明=new Color(28,28,28).getRGB();
		System.out.println(透明);
		for (int i = minx; i < height; i++) {

			for (int j = miny; j < width; j++) {

				int pixel = bi.getRGB(j, i); // 下面三行代码将一个数字转换为RGB数字

				
				System.out.println("i=" + i + ",j=" + j + ":(" + pixel+ ")");
				if(pixel==透明) {
					fs.append("n;");
					continue;
				}
				fs.append(pixel+";");
			}
		}
		FileRW.write(image+".pick", fs.toString(),false);
		System.out.println(image+" 转换完成.");
		System.exit(0);
	}

}
