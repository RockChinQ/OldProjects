package CAE;

public class f extends Thread{
	boolean b=false;
	public void run(){
		while(true){
			if(spawnwindows.b1&&!b){
				spawnwindows.wi.requestFocus();
				b=true;
			}else if(!spawnwindows.b1){
				b=false;
			}
		}
	}
}
