package CAE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

public class check extends Thread{
	static int c=0;
	dothing d=new dothing();
	StringBuffer[] s0=new StringBuffer[10];
	boolean[] b0=new boolean[10];
	StringBuffer[] s1=new StringBuffer[10];
	StringBuffer[] s2=new StringBuffer[10];
	StringBuffer[] s3=new StringBuffer[10];
	StringBuffer[] s4=new StringBuffer[10];
	StringBuffer[] s5=new StringBuffer[10];
	StringBuffer[] s6=new StringBuffer[10];
	StringBuffer[] s7=new StringBuffer[10];
	boolean[] b1=new boolean[10];
	boolean bo=true;
	check(){}
	void add(String s0,boolean b,
			String s1,String s2,
			String s3,String s4,
			String s5,String s6,String ss) {
		//条件 等于 数据 参数1 参数2 事件 参数1 参数2 描述
		bo=false;
		this.s0[c]=new StringBuffer(s0);
		this.b0[c]=b;
		this.s1[c]=new StringBuffer(s1);
		this.s2[c]=new StringBuffer(s2);
		this.s3[c]=new StringBuffer(s3);
		this.s4[c]=new StringBuffer(s4);
		this.s5[c]=new StringBuffer(s5);
		this.s6[c]=new StringBuffer(s6);
		this.s7[c]=new StringBuffer(ss);
		spawnwindows.jl[c].setText("任务"+(c+1)+ss);
		this.b1[c]=true;
		c++;
		bo=true;
		javax.swing.JOptionPane.showMessageDialog(null,"添加成功!\n描述:"+ss);
		javax.swing.JOptionPane.showMessageDialog(null,"任务列表已更新!");
	}
	void del(int i) {
		bo=false;
		int in;
		spawnwindows.jl[i].setText("<无任务 请点击\"添加任务\"以添加>");
		for(in=i;in<10;){
			if(in<9){
				s0[in]=s0[in+1];
				b0[in]=b0[in+1];
				s1[in]=s1[in+1];
				s2[in]=s2[in+1];
				s3[in]=s3[in+1];
				s4[in]=s4[in+1];
				s5[in]=s5[in+1];
				s6[in]=s6[in+1];
				s7[in]=s7[in+1];
				b1[in]=b1[in+1];
				if(in>=0&&!spawnwindows.jl[in+1].getText().equals("<无任务 请点击\"添加任务\"以添加>")){
					//spawnwindows.jl[in].setText(r[1]);
					spawnwindows.jl[in].setText("任务"+(in+1)+s7[in+1]);
					spawnwindows.jl[in+1].setText("<无任务 请点击\"添加任务\"以添加>");
				}
			if(spawnwindows.jl[in].getText().equals("<无任务 请点击\"添加任务\"以添加>")){
				break;
			}
			in++;
			}else if(in==10){
				s0[in]=null;
				b0[in]=false;
				s1[in]=null;
				s2[in]=null;
				s3[in]=null;
				s4[in]=null;
				s5[in]=null;
				s6[in]=null;
				b1[in]=false;
			}
		}
		c--;
		bo=true;
		//javax.swing.JOptionPane.showMessageDialog(null,"任务列表已更新!");
		spawnwindows.l.setText("任务列表已更新!");
	}
	public void run(){
		for(;true;){
			//System.out.println(i<c&&i<10&&c!=0&&bo);
			for(int i=0;i<c&&i<10&&c!=0&&bo;){
				//System.out.println();
				if(b1[i]){
					try{
						//System.out.println(s0[i].toString().equals("时间"));
						if(s0[i].toString().equals("时间")){
							String s=s1[i].toString();
							String[] t=s.split(" ");
							Calendar ca=Calendar.getInstance();
							int h=Integer.parseInt(t[0].toString());
							int m=Integer.parseInt(t[1].toString());
							int e=Integer.parseInt(t[2].toString());
							//System.out.println(h+" "+m+" "+e+" "+ca.get(Calendar.HOUR_OF_DAY)+" "+ca.get(Calendar.MINUTE)+" "+ca.get(Calendar.SECOND));
							if(b1[i]&&ca.get(Calendar.HOUR_OF_DAY)==h&&ca.get(Calendar.MINUTE)==m&&ca.get(Calendar.SECOND)==e&&b0[i]){
								d.run(s4[i].toString(),s5[i].toString(),s6[i].toString());
								this.del(i);
								break;
							}else if(b1[i]&&ca.get(Calendar.HOUR_OF_DAY)!=h&&ca.get(Calendar.MINUTE)!=m&&ca.get(Calendar.SECOND)!=e&&!b0[i]){
								d.run(s4[i].toString(),s5[i].toString(),s6[i].toString());
								this.del(i);
								i--;
								break;
							}
						}else if(s0[i].toString().equals("随机数")){
							int r=(int) (Math.random()*100);
							int ra=Integer.parseInt(s1[i].toString());
							if(b1[i]&&ra==r&&b0[i]){
								d.run(s4[i].toString(),s5[i].toString(),s6[i].toString());
								this.del(i);
								break;
							}else if(b1[i]&&ra!=r&&!b0[i]){
								d.run(s4[i].toString(),s5[i].toString(),s6[i].toString());
								this.del(i);
								break;
							}
						}else if(s0[i].toString().equals("读取文件内容")){
							File file=new File(s2[i].toString());
							FileReader fr=new FileReader(file);
							BufferedReader br=new BufferedReader(fr);
							try {
								StringBuffer sb=new StringBuffer("");
								while(true){
									String str=br.readLine();
									if(str==null){
										break;
									}
									sb=new StringBuffer(sb+str);
								}
								if(b1[i]&&s1[i].toString().equals(sb.toString())&&b0[i]){
									d.run(s4[i].toString(),s5[i].toString(),s6[i].toString());
									this.del(i);
									i++;
									break;
								}else if(b1[i]&&!s1[i].toString().equals(sb.toString())&&!b0[i]){
									d.run(s4[i].toString(),s5[i].toString(),s6[i].toString());
									this.del(i);
									break;
								}
							} catch(Exception e){
								javax.swing.JOptionPane.showMessageDialog(null,"出错:"+e+" 程序退出!");
								System.exit(0);
							} finally{
								br.close();
							}
						}else if(s0[i].toString().equals("弹出输入框输内容")){
							String strin=javax.swing.JOptionPane.showInputDialog(null, "输入内容:");
							if(strin.equals(s1[i].toString())&&b0[i]){
								d.run(s4[i].toString(),s5[i].toString(),s6[i].toString());
								this.del(i);
								break;
							}else if(!strin.equals(s1[i].toString())&&!b0[i]){
								d.run(s4[i].toString(),s5[i].toString(),s6[i].toString());
								this.del(i);
								break;
							}
						}
					}catch(Exception e){
						javax.swing.JOptionPane.showMessageDialog(null,"出错:"+e+" 程序退出!");
						System.exit(0);
					}
				}
				i++;
				if(i==10){
					i=0;
				}
			}
		}
	}
	void keycheck(char s) throws Exception{
		for(int i=0;c!=0&&i<c;){
			if(s0[i].toString().equals("键盘按下字符")&&b1[i]){
				char[] cha=s1[i].toString().toCharArray();
				StringBuffer n0=new StringBuffer(s);
				StringBuffer n=new StringBuffer(cha[0]);
				String ng=n.toString();
				if(b0[i]&&ng.equals(n0.toString())){
					d.run(s4[i].toString(),s5[i].toString(),s6[i].toString());
					this.del(i);
				}else if(!b0[i]&&!ng.equals(n0.toString())){
					d.run(s4[i].toString(),s5[i].toString(),s6[i].toString());
					this.del(i);
				}
			}
			i++;
		}
	}
}
