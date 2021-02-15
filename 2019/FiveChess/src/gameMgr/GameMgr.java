package gameMgr;

import ai.AiMgr;
import data.DataBase;
import launch.Main;
import ts.opt;
import ui.DisplayPanel;
import ui.Window;

public class GameMgr {
	
	public static boolean put(int x,int y) {
		System.out.println("x,y,v="+x+","+y+","+DataBase.pnl[y][x]);
		if(DataBase.pnl[y][x]!=-1) {     //有棋子则返回
			return false;
		}
		short now=(short) DataBase.now;
		//先修改当前棋子代码再下棋
		//玩家的子
		DataBase.changeNow();
		DataBase.put(x,y,now);
		
		checkGameOver(x,y,now);
		
		opt.printTime();
		updateDisplay();
		//验证完玩家之后ai下子
		if(DataBase.gameMd==1) {  //ai
			DataBase.changeNow();
			DataBase.put(AiMgr.aiPut(), now);
		}
		//ai下子结束验证ai
		checkGameOver(x,y,now);
		//回去吧！
		return true;//Succeeded in putting or not;
	}
	public static void checkGameOver(int x,int y,short now) {
		//验证是否结束
		opt.printTime();
		int gameOver=gameOver(x,y,now);
		//System.out.println("          "+gameOver);
		if(gameOver==-1){
			DataBase.playing=false;
			Main.w.notice.setText("Nobody won the game!");
		}else  if(gameOver!=-2) {
			DataBase.playing=false;
			DataBase.gameMd=-1;
			Main.w.notice.setText((now==0?"black":"white")+" won the game!");
		}
	}
	public static void resetPnl() {
		DataBase.init();
		DataBase.now=1;
		DataBase.changeNow();
		updateDisplay();
	}
	public static void updateDisplay() {
		Main.w.dp.updatePnlData(DataBase.pnl);
	}
	public static short gameOver(int cx,int cy,short last) {
		boolean win=false;
		//check 4directions:(-1,0)(-1,-1)(0,-1)(+1,-1)
		//if(cx-1>=0&&DataBase.pnl[cy][cx-1]==last) {
		int cc=countChess(-1,0,cx,cy,last);   //-
		int te=countChess(1,0,cx-cc,cy,last);
		System.out.println(last+"count:"+te);
		if(te>=4) {
			System.out.println("***Win(0):"+last);
			return last;
		}
		cc=countChess(-1,-1,cx,cy,last);     //\
		te=countChess(1,1,cx-cc,cy-cc,last);
		if(te>=4) {
			System.out.println("***Win(1):"+last);
			return last;
		}
		cc=countChess(0,-1,cx,cy,last);     //|
		te=countChess(0,1,cx,cy-cc,last);
		if(te>=4) {
			System.out.println("***Win(2):"+last);
			return last;
		}
		cc=countChess(1,-1,cx,cy,last);     // /
		te=countChess(-1,1,cx+cc,cy-cc,last);
		if(te>=4) {
			System.out.println("***Win(3):"+last);
			return last;
		}
		//}
		//draw or not
		boolean full=true;
		for(int i=0;i<13;i++) {
			for(int j=0;j<13;j++) {
				if(DataBase.pnl[i][j]==-1) {
					full=false;
				}
			}
		}
		if(full) {
			return -1;
		}
		
		return -2;
	}
	public static int countChess(int vx,int vy,int sx,int sy,short chessCode) {
		if((sx+vx<0||sx+vx>=13||sy+vy<0||sy+vy>=13)||DataBase.pnl[sy+vy][sx+vx]!=chessCode)
			return 0;
		return 1+countChess(vx,vy,sx+vx,sy+vy,chessCode);
	}
}
