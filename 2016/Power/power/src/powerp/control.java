package powerp;

public class control extends Thread{
	int h,l; //行,列
	int type;   //类型
	int inc;  //接口数量
	int[] interhan;
	int[] interlie; //接口[行][列]
	int[] inph;
	int[] inpl;
	boolean lig=false;
	boolean r=false;
	public void run(){
		for(;r;){
			if(islight()){
				
			}
		}
	}
	boolean islight(){
		for(int f=0;f<inc;){
			for(int s=0;s<inc;){
				for(int t=0;window.con[interhan[f]][interlie[s]].lig&&t<window.con[interhan[f]][interlie[s]].inc;){
					for(int d=0;d<window.con[interhan[f]][interlie[s]].inc;){
						if(window.con[interhan[f]][interlie[s]].interhan[t]==h&&
							window.con[interhan[f]][interlie[s]].interlie[d]==l){
							return true;
						}
						d++;
					}
					t++;
				}
				s++;
			}
			f++;
		}
		return false;
	}
}
