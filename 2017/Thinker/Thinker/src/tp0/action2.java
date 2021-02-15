package tp0;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPopupMenu;

public class action2 implements ActionListener {

	draw2 d=new draw2(450,470);
	int len=0;
	JCheckBox sall;
	public void actionPerformed(ActionEvent arg0){
		//int papbc=main.dr.papbc;
		main.dr.g.setVisible(false);
		int papbc=main.dr.papbc;
		int x=main.dr.chose-1;
		//System.out.println(((int)Math.sqrt(x+1)+2)*100);
		gui g=new gui(Math.max(((int)Math.sqrt(x+1)+2)*100,400),((int)Math.sqrt(x+1)*3)*30+575,0,0,true,"选择需要的线段",false,true,true);
		sall=new JCheckBox("全选");
		sall.setSize(300, 30);
		sall.setFont(new Font("Serif",Font.PLAIN,15));
		g.add(sall);
		d.setSize(450, 470);
		d.setLocation(0, g.getHeight()-550);
		g.add(d);
		g.setLayout(null);
		g.addWindowListener(new WindowListener(){
			public void windowActivated(WindowEvent e) {
			}
			public void windowClosed(WindowEvent e) {
			}
			public void windowClosing(WindowEvent e) {
				main.dr.g.setVisible(true);
			}
			public void windowDeactivated(WindowEvent e) {
			}
			public void windowDeiconified(WindowEvent e) {
			}
			public void windowIconified(WindowEvent e) {
			}
			public void windowOpened(WindowEvent e) {
			}
			
		});
		//JPopupMenu jpm=new JPopupMenu("选择");
		//System.out.println(x);
		JCheckBox[] jcb=new JCheckBox[(1+x)*x/2];
		len=jcb.length;
		JButton con=new JButton("好,就是这些");
		con.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				/*
				char[] tc=new char[2];
				for(int a=0;a<len;a++){
					if(jcb[a].isSelected()&&jcb[a].isEnabled()){
						tc=jcb[a].getText().toCharArray();
						main.dr.addline(tc[0], tc[1]);
					}
				}*/
				main.dr.g.setVisible(true);
				main.dr.refresh2();
				g.dispose();
			}
			
		});
		con.setSize(100, 30);
		con.setLocation(0, g.getHeight()-80);
		g.add(con);
		StringBuffer[] note=new StringBuffer[x+1];
		int notec=0;
		for(int a=0;a<main.dr.xayc;a++){            //生成选项
			if(main.dr.xay[a][3]==1){
				note[notec]=new StringBuffer((char)main.dr.xay[a][2]+"~"+(main.dr.xay[a][4]==4097?"NULL":main.dr.xay[a][4]));
				notec++;
				if(notec==main.dr.chose)
					break;
			}
		}
		int q=0,w=0;
		for(int a=0;a<len;a++){
			jcb[a]=new JCheckBox();
			jcb[a].setSize(120, 30);
			jcb[a].setLocation(q*110, w*30);
			jcb[a].setFont(new Font("Serif",Font.PLAIN,12));
			jcb[a].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					for(int b=0;b<len;b++){
						if(arg0.getSource()==jcb[b]){
							addl(jcb[b]);
							if(main.dr.papbc==papbc||main.dr.papbc==papbc+len){
								sall.setEnabled(true);
								if(main.dr.papbc==(papbc+len)){
									sall.setSelected(true);
								}else{
									sall.setSelected(false);
								}
							}else{
								sall.setEnabled(false);
							}
						}
					}
				}
			});
			g.add(jcb[a]);
			w++;
			if(w>=((int)Math.sqrt(x+1)*3)){
				w=0;
				q++;
			}
		}
		g.setSize(Math.max((q+1)*110+30,465),g.getHeight());
		int i=0;
		for(int a=0;a<main.dr.chose;a++){                             //选项设置
			for(int b=a+1;b<main.dr.chose;b++){
				jcb[i].setText(note[a].toString()+" "+note[b].toString());
				for(int c=0;c<main.dr.papbc;c++){
					if(((char)main.dr.papb[c][0]+"~"+(main.dr.papb[c][3]==4097?"NULL":main.dr.papb[c][3])+" "+(char)main.dr.papb[c][1]+"~"+(main.dr.papb[c][4]==4097?"NULL":main.dr.papb[c][4])).equals(jcb[i].getText())){
						jcb[i].setSelected(true);
						jcb[i].setEnabled(false);
						break;
					}
				}
				i++;
			}
		}
		sall.setLocation(100,con.getY());
		sall.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
					for(int a=0;a<len;a++){
						if(jcb[a].isEnabled()){
							jcb[a].setSelected(sall.isSelected());
							addl(jcb[a]);
						}
					}
			}
			
		});
		sall.setText("全选:"+len+"个  已选:"+main.dr.chose+"个点  已制作:"+0+"条线");
	}
	void addl(JCheckBox jcb){
		boolean iss=jcb.isSelected();
		String[] tc=jcb.getText().split(" ");
		//System.out.println(tc[0].split("-").length);
		if(iss){
			main.dr.papb[main.dr.papbc][0]=tc[0].toCharArray()[0];        //D:D:D:D:D:D:D:*_*
			main.dr.papb[main.dr.papbc][1]=tc[1].toCharArray()[0];
			try{
			main.dr.papb[main.dr.papbc][3]=Integer.parseInt(tc[0].split("~")[1]);
			}catch(Exception e){main.dr.papb[main.dr.papbc][3]=4097;}
			try{
			main.dr.papb[main.dr.papbc][4]=Integer.parseInt(tc[1].split("~")[1]);
			}catch(Exception e){main.dr.papb[main.dr.papbc][4]=4097;}
			main.dr.papbc++;
		}else{
			boolean update=false;
			for(int a=0;a<main.dr.papbc;a++){
				if(main.dr.papb[a][0]==tc[0].toCharArray()[0]&&main.dr.papb[a][1]==tc[1].toCharArray()[0]){
					update=true;
				}
				if(update){
					main.dr.papb[a][0]=main.dr.papb[a+1][0];
					main.dr.papb[a][1]=main.dr.papb[a+1][1];
				}
			}
			main.dr.papbc--;
		}
		sall.setText("全选:"+len+"个  已选:"+main.dr.chose+"个点  已制作:"+main.dr.papbc+"条线");
		d.repaint();
	}

}
