package core;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Date;

public class SpaceScan {
	public static ArrayList<ArrayList<Point>> area=new ArrayList<ArrayList<Point>>();

	public static ArrayList<Point> atemp=new ArrayList<Point>();
	public static int typenow=0;
	public static int blocks[][]=new int [Main.mapHeight][Main.mapWidth];
	public static int index=0;
	/*
	 * ��Ǳ���
	 * �μ�al.get(index)��ӱ���
	 * ѭ��������һ��
	 * ѭ����ɷ���
	 * 
	 * */
	public static void reset() {
		area=new ArrayList<ArrayList<Point>>();
		for(int i=0;i<Main.mapHeight;i++) {
			for(int j=0;j<Main.mapWidth;j++) {
				SpaceScan.blocks[i][j]=-1;
			}
		}
		index=0;
	}
	public static void scan(int x,int y) {
		//blocks[y][x]=Level.getAbsType(Level.blocks[y][x]);
		blocks[y][x]=index;
		//System.out.println(" "+index);
		atemp.add(new Point(x,y));
		for(int i=Math.max(0, y-1);i<=Math.min(Main.mapHeight-1, y+1);i++) {
			for(int j=Math.max(0, x-1);j<=Math.min(Main.mapWidth-1, x+1);j++) {
				if(i==y&&j==x)//�����Լ���Ҫ��Ȼ�����Ȥ
					continue;
				if(blocks[i][j]!=-1) {//������scan�ģ�Ҫ��Ȼ�����Ȥ
					continue;
				}
				if(Level.getAbsType(Level.blocks[i][j])!=typenow) {//�߽�
					continue;
				}
				//��һ��
				scan(j,i);
			}
		}
	}
	public static void manager() {
		reset();
		Long st=new Date().getTime();
		//�������Ҹ��ӣ�����δ��scan�ľͷ��͵�scan�ݹ麯��
		for(int i=0;i<Main.mapHeight;i++) {
			for(int j=0;j<Main.mapWidth;j++) {
				if(blocks[i][j]==-1) {//����һ������
					atemp=new ArrayList<Point>();//��ӽ���ʱal
					typenow=Level.getAbsType(Level.blocks[i][j]);
					scan(j,i);
					area.add(atemp);//ɨ�������ӽ��ܼ�al
					index++;
				}
			}
		}
		System.out.println("area amount:"+area.size()+" time:"+(new Date().getTime()-st));
	}
	public static ArrayList<Integer> findAreaSurroundedByBlock(ArrayList<Integer> blockType) {
		/*
		 * ȷ����������յ�����
		 * ��֤ÿ���ڲ�block�Ƿ��ڱ�Ե
		 *   ��¼x��y���ϱ���ǵ�Χ��block
		 * */
		/*����bug
		 * ����һ����aΧ���γɵ�������
		 * �кܶ�b��ĳ����״����
		 * �������п��ܱ�ʶ��Ϊ��bΧ��
		 * 
		 * ���������벻�����е���״
		 * �д���֤
		 * */
		ArrayList<Integer> result=new ArrayList<Integer>();
		int size=area.size();
		for(int i=0;i<size;i++) {//����ÿ��area
			Point start=new Point(),end=new Point();
			int minx=65536,miny=65536,maxx=0,maxy=0;
			int areasize=area.get(i).size();
			int box=0,boy=0;//��xy���ϵ�Χ��block������
			for(int j=0;j<areasize;j++) {//������ǰarea���а�������
				//����Χ����Χ��block
				Point tp=area.get(i).get(j);
				//System.out.println(" blockType:"+Level.getAbsType(Level.blocks[tp.y][tp.x])+" areaIndex:"+i);
				for(int k=Math.max(tp.y-1,0);k<=Math.min(tp.y+1, Main.mapHeight-1);k++) {
					for(int l=Math.max(tp.x-1, 0);l<=Math.min(tp.x+1, Main.mapWidth-1);l++) {
						minx=minx>tp.x?tp.x:minx;//minx����tpx������minx=tpx
						miny=miny>tp.y?tp.y:miny;
						maxx=maxx<tp.x?tp.x:maxx;
						maxy=maxy<tp.y?tp.y:maxy;
						if(k!=tp.y&&l!=tp.x)
							continue;
						if(k==tp.y&&l==tp.x)
							continue;
						if(blocks[k][l]==blocks[tp.y][tp.x])//����ͬ������򲻼���
							continue;
						//System.out.println("find area index:"+l+" "+k+" "+tp.x+" "+(Main.mapWidth-1));
						if(blockType.indexOf(Level.getAbsType(Level.blocks[k][l]))!=-1) {//������б�����������ڲ��ҵ��ı߽緽��
							if(k==tp.y)//����
								boy++;
							else if(l==tp.x)
								box++;//����
						}
					}
				}
			}
			//��֤Χ��block�����Ƿ���ϱ�׼
			//System.out.println(maxx+"-"+minx+" "+box);
			if(  ((maxx-minx+1)*2<=box)&&((maxy-miny+1)*2)<=boy  ) {
				result.add(i);
				//System.out.println("result                                           :"+i);
			}
		}
		return result;
	}
}







