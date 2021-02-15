package Pixel2D;

public class PLabel extends PixelComponent{
	public PLabel(){}
	public PLabel(String text){
		this.text=text;
	}
	public PLabel(PixelIcon icon){
		this.icon=icon;
	}
	public PLabel(String text,PixelIcon icon){
		this.text=text;
		this.icon=icon;
	}
}
