package guiMgr.panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import boot.Main;
import data.GroupManager;

/**
 * ������ͼ����ʾ���߼�ʱ����
 * @author Administrator
 *
 */
public class GroupTmln extends JPanel{
	Color frontCl=new Color(200,200,200);
	Font font=new Font("����",Font.BOLD,16);
	//static final Color border=new Color(250,200,15);
	static final Color branch=Color.white;
	public int addX=30,addY=80;
	static final int lwidth=120,lheight=38;
	static final int groupSpace=70;
	class glabel{int fram;GroupManager.group gp;
		public glabel(int fram,GroupManager.group gp)
		{this.fram=fram;this.gp=gp;}
	}
	ArrayList<glabel> gls=new ArrayList<glabel>();
	public void paint(Graphics g) {
		g.setColor(Main.gui.bgp.ep.getBackground());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		//��������
		((Graphics2D)g).setStroke(new BasicStroke(3));
		g.setColor(branch);
		g.drawLine(addX+3, addY, this.getWidth()-addX
				-Main.gm.groups.get(Main.gm.groups.size()-1).maxWidth, addY);
		//�˵�
		//g.drawOval(addX, addY-5, 10, 10);
		//g.drawOval(this.getWidth()-addX-Main.gm.groups.get(Main.gm.groups.size()-1).maxWidth, addY-5, 10, 10);
		
		//����ÿ�����ǩ
		//g.setColor(border);
		int aax=0;
		for(int i=0;i<gls.size();i++) {
			g.setColor(Main.gui.bgp.ep.getBackground());
			g.fillRect(addX+aax, addY-this.lheight/2, lwidth, lheight);
			//System.out.println(gls.get(i).gp.name);
			g.setColor(gls.get(i).gp.cl);
			g.fillRect(addX+aax, addY-this.lheight/2, lwidth, lheight);
			//���Ʒ�֧��
			int count=gls.get(i).gp.maxHeight
					/(Main.gui.bgp.ep.GROUP_VIEW_ELE_HEIGHT+Main.gui.bgp.ep.ELE_SPACE);
			
			int addxx=25;//��֧��λ��
			//һ����֧
			g.setColor(branch);
			if(Main.gm.countGroupEleAmount(gls.get(i).gp.uid)!=0)
				g.drawLine(addX+aax+addxx+3,addY+this.lheight/2+3 , addX+aax+addxx+3, gls.get(i).gp.maxHeight+addY+6);
			//������֧
			for(int j=0;j<count;j++) {
				g.drawLine(addX+aax+addxx+3
						, addY+this.lheight+j*(Main.gui.bgp.ep.GROUP_VIEW_ELE_HEIGHT+Main.gui.bgp.ep.ELE_SPACE)+12
						, addX+aax+addxx+23
						,  addY+this.lheight+j*(Main.gui.bgp.ep.GROUP_VIEW_ELE_HEIGHT+Main.gui.bgp.ep.ELE_SPACE)+12);
			}
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString(gls.get(i).gp.name,addX+aax+5, addY-this.lheight/2+25);
			aax+=gls.get(i).gp.maxWidth+this.groupSpace;
		}
	}
	public void addGroupLabel(GroupManager.group gp,int fram) {
		this.gls.add(new glabel(fram,gp));
	}
	public void reset() {
		this.gls=new ArrayList<glabel>();
		System.gc();
	}
	public void setSizex(int width,int height) {
		this.setSize(width+2*addX, height+2*addY);
	}
}
