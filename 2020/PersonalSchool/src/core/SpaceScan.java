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
	 * 标记本格
	 * 次级al.get(index)添加本格
	 * 循环搜索下一格
	 * 循环完成返回
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
				if(i==y&&j==x)//跳过自己，要不然会很有趣
					continue;
				if(blocks[i][j]!=-1) {//跳过已scan的，要不然会很有趣
					continue;
				}
				if(Level.getAbsType(Level.blocks[i][j])!=typenow) {//边界
					continue;
				}
				//下一级
				scan(j,i);
			}
		}
	}
	public static void manager() {
		reset();
		Long st=new Date().getTime();
		//挨个查找格子，发现未被scan的就发送到scan递归函数
		for(int i=0;i<Main.mapHeight;i++) {
			for(int j=0;j<Main.mapWidth;j++) {
				if(blocks[i][j]==-1) {//发现一个区域
					atemp=new ArrayList<Point>();//添加进临时al
					typenow=Level.getAbsType(Level.blocks[i][j]);
					scan(j,i);
					area.add(atemp);//扫描完毕添加进总集al
					index++;
				}
			}
		}
		System.out.println("area amount:"+area.size()+" time:"+(new Date().getTime()-st));
	}
	public static ArrayList<Integer> findAreaSurroundedByBlock(ArrayList<Integer> blockType) {
		/*
		 * 确定抽象起点终点坐标
		 * 验证每个内部block是否在边缘
		 *   记录x轴y轴上被标记的围绕block
		 * */
		/*疑似bug
		 * 若在一个由a围绕形成的区域内
		 * 有很多b以某种形状排列
		 * 该区域有可能被识别为由b围绕
		 * 
		 * 但是我设想不出排列的形状
		 * 有待验证
		 * */
		ArrayList<Integer> result=new ArrayList<Integer>();
		int size=area.size();
		for(int i=0;i<size;i++) {//遍历每个area
			Point start=new Point(),end=new Point();
			int minx=65536,miny=65536,maxx=0,maxy=0;
			int areasize=area.get(i).size();
			int box=0,boy=0;//在xy轴上的围绕block的数量
			for(int j=0;j<areasize;j++) {//遍历当前area所有包含格子
				//在周围查找围绕block
				Point tp=area.get(i).get(j);
				//System.out.println(" blockType:"+Level.getAbsType(Level.blocks[tp.y][tp.x])+" areaIndex:"+i);
				for(int k=Math.max(tp.y-1,0);k<=Math.min(tp.y+1, Main.mapHeight-1);k++) {
					for(int l=Math.max(tp.x-1, 0);l<=Math.min(tp.x+1, Main.mapWidth-1);l++) {
						minx=minx>tp.x?tp.x:minx;//minx大于tpx则设置minx=tpx
						miny=miny>tp.y?tp.y:miny;
						maxx=maxx<tp.x?tp.x:maxx;
						maxy=maxy<tp.y?tp.y:maxy;
						if(k!=tp.y&&l!=tp.x)
							continue;
						if(k==tp.y&&l==tp.x)
							continue;
						if(blocks[k][l]==blocks[tp.y][tp.x])//若是同区域的则不计算
							continue;
						//System.out.println("find area index:"+l+" "+k+" "+tp.x+" "+(Main.mapWidth-1));
						if(blockType.indexOf(Level.getAbsType(Level.blocks[k][l]))!=-1) {//传入的列表里面包括现在查找到的边界方块
							if(k==tp.y)//左右
								boy++;
							else if(l==tp.x)
								box++;//上下
						}
					}
				}
			}
			//验证围绕block数量是否符合标准
			//System.out.println(maxx+"-"+minx+" "+box);
			if(  ((maxx-minx+1)*2<=box)&&((maxy-miny+1)*2)<=boy  ) {
				result.add(i);
				//System.out.println("result                                           :"+i);
			}
		}
		return result;
	}
}







