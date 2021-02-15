package Pixel2D;

import java.awt.Color;
import java.util.TimerTask;

public class PTextField extends PixelComponent{
	public int gridx=0;
	public boolean showing=false;
	public PTextField(){
		
	}
	public PTextField(String text){
		this.text=text;
	}
	public void showCursor(boolean show,Pixel2D p2d){

		if(show){
			p2d.drawLine(this.gridx+this.location.x+2, this.location.y+1,this.gridx+this.location.x+2, this.location.y+this.height-1,this.fontcl);
			
		}else{
			p2d.drawLine(this.gridx+this.location.x+2, this.location.y+1,this.gridx+this.location.x+2, this.location.y+this.height-1,Color.GRAY);
		}
	}
	
}
