package Pixel2D;

public class PButton extends PixelComponent{
	public PButton(){}
	public PButton(String text){
		this.text=text;
	}
	public PButton(PixelIcon icon){
		this.icon=icon;
	}
	public PButton(String text,PixelIcon icon){
		this.text=text;
		this.icon=icon;
	}
}
