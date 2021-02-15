package core;

import Pickaxe2D.PickImage;

public class MapMaker {
	int w=Main.mapWidth,h=Main.mapHeight;
	int mapData[][];

	public String res="resources\\";
	PickImage grass=new PickImage(res+"grass.png.pick");
	PickImage line_road=new PickImage(res+"line_road.png.pick"),row_road=new PickImage(res+"row_road.png.pick");
	PickImage agrass=new PickImage(res+"agrass.png.pick"),flower=new PickImage(res+"flower.png.pick");
	//random spawn  
	public void randomMap() {
		reset();
		Level.reset();
		int lineRoad=((int)(Math.random()*100)%5)+(Main.mapWidth-6),rowRoad=((int)(Math.random()*100)%4)+2;//两条路生成
		System.out.println("Road:"+lineRoad+" ， "+rowRoad+" mapSize:"+w+","+h);
		Level.road_y=rowRoad;
		Level.road_x=lineRoad;
		for(int i=0;i<w;i++) {//布置横的路
			mapData[rowRoad][i]=1;
			Level.blocks[rowRoad][i]=1;
		}
		for(int i=0;i<h;i++) {//竖的
			mapData[i][lineRoad]=2;
			Level.blocks[i][lineRoad]=2;
		}

		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				if(mapData[i][j]==0)
					Main.gg.mapp.map.loadPickImage(j*grass.w, i*grass.h, grass);
			}
		}
		//加花
		int x=0,y=0;
		for(int i=0;i<45;i++) {
			x=(int)(Math.random()*1000)%(Main.mapWidth*10-20)+10;
			y=(int)(Math.random()*1000)%(Main.mapHeight*10-20)+10;
			//System.out.println("Plants:"+x+" "+y);
			Main.gg.mapp.map.loadPickImage(x, y, (int)(Math.random()*100)%2==0?agrass:flower);
		}
		for(int i=0;i<h;i++) {//加路
			for(int j=0;j<w;j++) {
				if(mapData[i][j]==1)
					Main.gg.mapp.map.loadPickImage(j*grass.w, i*grass.h, line_road);
				else if(mapData[i][j]==2)
						Main.gg.mapp.map.loadPickImage(j*grass.w, i*grass.h, row_road);
			}
		}
		Main.gg.mapp.map.loadPickImage(lineRoad*grass.w, rowRoad*grass.h, new PickImage(res+"center_road.png.pick"));
		
	}
	public void reset() {
		mapData=new int[h][w];
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				mapData[i][j]=0;
			}
		}
	}
}
