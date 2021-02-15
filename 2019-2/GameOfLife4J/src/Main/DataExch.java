package Main;

public class DataExch {
	//vars
	public boolean last[][]=new boolean[45][60];
	//configs
	public int cWidth=10,cHeight=10;
	public int pWidth=60,pHeight=45;  //ϸ������
	public double iterCycle=1; //��������/��
	public char boundCptType='r'; //�߽绷�� s  ����1 1  ����0 0  ��ʼ���ֵ r
	
	public double drawCycle=2; //��������/��
	public boolean wallpaper=false;
	public double wpCycle=60; //���ñ�ֽ����/��
	public double zoomToWp=2;  //��ֽϸ���ߴ�:����ϸ���ߴ�
	
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
