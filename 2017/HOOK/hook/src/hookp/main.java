package hookp;

public class main {
	static StringBuffer file=new StringBuffer();
	public static void main(String[] args) {
		new vars();
		if(args.length>=1){
			new window();
			window.g.setTitle("������־:"+args[0]);
		}else if(args.length<1){
			file=new StringBuffer(javax.swing.JOptionPane.showInputDialog("����·����"));
			new window();
			window.g.setTitle("������־:"+file.toString());
		}
		new start(file.toString());
	}
}
