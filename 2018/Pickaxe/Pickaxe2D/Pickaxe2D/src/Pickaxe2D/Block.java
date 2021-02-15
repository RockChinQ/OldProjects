package Pickaxe2D;

import java.awt.Color;

public class Block {
	Color color=Color.white;
	StringBuffer label;
	short border=0x0;
	final static int top=0x8,bottom=0x2,right=0x4,left=0x1;
	Block(){
		;
	}
	Block(Color c){
		this.color=c;
	}
	void setLabel(String label) {
		this.label=new StringBuffer(label);
	}
	void setBorder(short border) {
		this.border=border;
	}
}
