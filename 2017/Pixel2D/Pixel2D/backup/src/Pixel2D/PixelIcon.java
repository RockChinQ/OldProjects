package Pixel2D;

import java.awt.Point;

public class PixelIcon {
	public PixelImage image;
	public PixelIcon(PixelImage image) {
		this.image=image;
	}

	public PixelImage getIconCode(int width){
		String[] codearr=image.code.split("&");
		StringBuffer csb=new StringBuffer();
		for(int a=0;a<width;a++){
			for(int b=0;b<width;b++){
				csb.append(codearr[a*image.height+b]+"&");
			}
		}
		return new PixelImage(width,width,csb.toString());
	}
}
