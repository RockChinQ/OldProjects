package SvvMain;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

public class Operate extends Thread{
	public char disk;
	file0 file0=new file0();
	//һЩ���
	int depth=0;
	int fileOperedAmount=0,fileOperedSize=0,folderOperedAmount=0;
	long timeStart=0;
	//������һЩ����
	
	//end
	Operate(char disk){
		this.disk=disk;
	}
	void startOperate(char disk,String pathNow,String[][] commands,String label) {        //���������̷������ڵ����·����������β����ı�־����������Ŀ�����ļ��У�
		/* !!! ÿ���´���·��������ĩβ��'\'*/
		/*�µ�һ�εݹ�дΪstartOperate(disk,pathNow+"\\"+<�µ��ļ�����>,commands,label)*/
		System.out.println("disk:"+disk+" pathNow:"+pathNow);
		try {
			File pathFileObj=new File(disk+":\\"+pathNow);
			File[] files=pathFileObj.listFiles();         //�ļ����У��б�
			int filesLen=files.length;
			int commandsLen=commands.length;
			for(int i=0;i<filesLen;i++) {
				try {
					if(files[i].isFile()) {                //�����ļ�
						for(int j=0;j<commandsLen;j++) {
							switch(commands[j][0]) {
							case "TREE":{
								try {
									if(Launcher.d.param[Integer.parseInt(commands[j][1])].get(1).equals("DIR_STRUCTURE")) {
										new File(new File((String) Launcher.d.param[Integer.parseInt(commands[j][1])].get(0)).getParent()).mkdirs();
										StringBuffer tabAddup=new StringBuffer("");
										for(int k=0;k<depth;k++) tabAddup.append("  |");
										file0.write((String) Launcher.d.param[Integer.parseInt(commands[j][1])].get(0)+label+".txt"
												,tabAddup.toString()+files[i].getName()+"\r\n", true);
									}else if(Launcher.d.param[Integer.parseInt(commands[j][1])].get(1).equals("FULL_PATH")) {
										file0.write((String) Launcher.d.param[Integer.parseInt(commands[j][1])].get(0)+label+".txt"
												,"[FIL]"+files[i].getAbsolutePath()+"\r\n", true);
									}
								}catch(Exception e) {
									System.out.println("TREE");
									e.printStackTrace();
								}
								break;
							}
							case "COPY":{              //����
								try {
									//new file0().write(target+"\\"+fileList[i].getName(), "", false);
									Files.copy(files[i].getAbsoluteFile().toPath()
											,new File(Launcher.d.param[Integer.parseInt(commands[j][1])].get(0)+"\\"+label+"\\"+pathNow+"\\"+files[i].getName()).getAbsoluteFile().toPath());
								} catch (IOException e) {
									// TODO Auto-generated catch block
									System.out.println("third");
									e.printStackTrace();
								}
								this.fileOperedAmount++;
								this.fileOperedSize+=files[i].length()/1000;
								break;
							}
							case "EXIT":{
								System.out.println("Exit");
								System.exit(0);
								break;
							}
							}
						}
					}else if(files[i].isDirectory()) {
						for(int j=0;j<commandsLen;j++) {
							switch(commands[j][0]) {
							case "TREE":{
								if(Launcher.d.param[Integer.parseInt(commands[j][1])].get(1).equals("DIR_STRUCTURE")) {
									new File(new File((String) Launcher.d.param[Integer.parseInt(commands[j][1])].get(0)).getParent()).mkdirs();
									StringBuffer tabAddup=new StringBuffer("");
									for(int k=0;k<depth;k++) tabAddup.append("  |");
									file0.write((String) Launcher.d.param[Integer.parseInt(commands[j][1])].get(0)+label+".txt"
											,tabAddup.toString()+"[Dir]"+files[i].getName()+"\r\n", true);
								}else if(Launcher.d.param[Integer.parseInt(commands[j][1])].get(1).equals("FULL_PATH")) {
									file0.write((String) Launcher.d.param[Integer.parseInt(commands[j][1])].get(0)+label+".txt"
											,"[Dir]"+files[i].getAbsolutePath()+"\r\n", true);
								}
								break;
							}
							case "COPY":{
								File targetDirWithLabel=new File(Launcher.d.param[Integer.parseInt(commands[j][1])].get(0)+"\\"+label+"\\"+pathNow+"\\"+files[i].getName());
								if(!targetDirWithLabel.isDirectory()) {
									targetDirWithLabel.mkdirs();
								}
								break;
							}
							case "EXIT":{
								System.out.println("Exit");
								System.exit(0);
								break;
							}
							}
						}
						this.folderOperedAmount++;
						depth++;
						startOperate(disk,pathNow+(depth-1==0?"":"\\")+files[i].getName(),commands,label);
					}
				}catch(Exception e) {
					System.out.println("second");
					e.printStackTrace();
				}
			}
		}catch(Exception e) {
			System.out.println("root");
			e.printStackTrace();
		}
		depth--;
		return;
	}
	public void run(){
		try {
		for(int i=0;i<Launcher.d.task.oper.length;i++) {               //������&�ָ����������
			String[] commandSet=Launcher.d.task.oper[i].split("[|]");   //��|�ָ�Ĳ�������
			String[][] commandSetSplited=new String[commandSet.length][2];
			for(int j=0;j<commandSet.length;j++) {                     //�ֱ𴢴�ָ��Ͳ���
				commandSetSplited[j]=commandSet[j].split(" ");
			}
			depth=0;
			this.fileOperedAmount=0;
			this.fileOperedSize=0;
			this.folderOperedAmount=0;
			this.timeStart=new Date().getTime();
			if(!Launcher.d.devLists.read(new file0().read(disk+":\\"+Launcher.d.lastOperLabel)).equals("#undefined#")) {
				Launcher.d.devLists.write(new file0().read(disk+":\\"+Launcher.d.lastOperLabel), timeStart+"");
			}else if(!new File(disk+":\\"+Launcher.d.lastOperLabel).exists()
					||Launcher.d.devLists.read(new file0().read(disk+":\\"+Launcher.d.lastOperLabel)).equals("#undefined#")) {
				new file0().write(disk+":\\"+Launcher.d.lastOperLabel,"S"+timeStart,false);
				Launcher.d.devLists.write("S"+timeStart, timeStart+"");
			}
			startOperate(this.disk,"",commandSetSplited,new file0().read(disk+":\\"+Launcher.d.lastOperLabel));     //��ʼ����
			System.out.println("Oper done.");
			this.disk=(char)-1;//����
			this.stop();
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
