package Main;

public class DataExch {
	//vars
	public boolean last[][]=new boolean[45][60];
	//configs
	public int cWidth=10,cHeight=10;
	public int pWidth=60,pHeight=45;  //细胞数量
	public double iterCycle=1; //迭代周期/秒
	public char boundCptType='r'; //边界环绕 s  常量1 1  常量0 0  初始随机值 r
	
	public double drawCycle=2; //绘制周期/秒
	public boolean wallpaper=false;
	public double wpCycle=60; //设置壁纸周期/秒
	public double zoomToWp=2;  //壁纸细胞尺寸:窗口细胞尺寸
	
	public double surRate=0.4;
	DataExch(){
		reset();
	}
	public void reset() {
		for(int i=0;i<pHeight;i++) {
			for(int j=0;j<pWidth;j++) {
				last[i][j]=false;
			}
		}
	}
	public void loadConfigs() {
		
	}
	public void randomSpawn() {
		for(int i=0;i<pHeight;i++) {
			for(int j=0;j<pWidth;j++) {
				if(Math.random()<=surRate)
					last[i][j]=true;
				else
					last[i][j]=false;
			}
		}
		Main.cpti.generation=0;
	}
}
