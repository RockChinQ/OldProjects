package fstj;

public class main {
	static int co=0;
	public static void main(String[] args) throws Exception{
		run();
		javax.swing.JOptionPane.showMessageDialog(null, "欢迎使用!   如果显示空白窗口请最小化窗口并将其还原。");
	}
	static void run() {
		co=Integer.parseInt(javax.swing.JOptionPane.showInputDialog("请输入要统计的人数:"));
		if(co<91&&co>0){
			try {
				new win();
				return;
			} catch (Exception e) {
				javax.swing.JOptionPane.showMessageDialog(null, "出错:"+e);
			}
		}else{
			javax.swing.JOptionPane.showMessageDialog(null, "统计的人数不能大于90或小于1  请重新输入");
			run();
		}
	}
}
