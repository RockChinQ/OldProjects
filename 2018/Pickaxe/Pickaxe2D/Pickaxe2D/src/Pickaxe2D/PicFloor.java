package Pickaxe2D;

public class PicFloor {
	Block[][] blocks;
	PicFloor(DataOfPanel data){
		blocks=new Block[data.panelSize.height][data.panelSize.width];
		for(int i=0;i<data.panelSize.height;i++) {
			for(int j=0;j<data.panelSize.width;j++) {
				blocks[i][j]=new Block(data.background);
			}
		}
		
	}
}
