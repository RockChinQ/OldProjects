package guiMgr.script;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import boot.Main;
import data.IconDataBase;
import guiMgr.FixedPanel;
import guiMgr.PnlColor;
import guiMgr.fields.ComboBoxField;
import guiMgr.fields.Icon;
import guiMgr.fields.InputAreaField;
import guiMgr.fields.InputField;
import guiMgr.script.SceneTable.scene;
import guiMgr.script.ShotTable.shot;
import log.Log;

public class ShotAttri extends FixedPanel implements KeyListener, ChangeListener, ActionListener {
	static final Font font=new Font("",Font.PLAIN,13),italic=new Font("",Font.ITALIC,15),ttiny=new Font("",Font.PLAIN,12);
	static final Color fcl=new Color(240,240,240),blue=new Color(20,160,255);
	/**
	 * 1����		���ֲ����޸�
	 * 2����		����double(s)
	 * 3����		����
	 //* 4����		�ı���������˵�
	 * 5��λ		�ı���������˵�
	 * 6�˶�		�ı���
	 * 7��Ч		�ı���
	 * 8����		�ı���
	 * 9��ע
	 */
	JLabel shot_id=new JLabel("#1");//����
	//InputField length=new InputField();
	InputAreaField descr=new InputAreaField("����",360,80,30);
	InputAreaField note=new InputAreaField("��ע",360,50,30);
	InputField len=new InputField("ʱ��",100,30,30);
	JLabel unit_of_len=new JLabel("֡(0��)");
	JLabel scel=new JLabel("��������");
	JComboBox<String> sce=new JComboBox<String>();
	InputField seat=new InputField("��λ",140,30,30);
	seatSelector sec=new seatSelector();
	JLabel secl=new JLabel("��λ"),secl1=new JLabel("(��ѡ)");
	InputField moti=new InputField("�˾�",190,30,30);
	InputField ado=new InputField("��Ч",160,30,30);
	JLabel tyl=new JLabel("����");
	JLabel tyl2=new JLabel("�о�");
	JSlider type=new JSlider(0,4,2);
	
	Icon exc=new Icon(IconDataBase.shot_attri_extreme_close),clo=new Icon(IconDataBase.shot_attri_close),
			med=new Icon(IconDataBase.shot_attri_medium),pan=new Icon(IconDataBase.shot_attri_panorama),
			pro=new Icon(IconDataBase.shot_attri_prospect);
	JPanel bgp=new JPanel();
	public ShotAttri() {
		super();
		this.setLayout(null);
		this.setSize(300, 400);
		//this.setLocation(2, 300);
		this.setTitlex("  ��ͷ��Ϣ");
		
		bgp.setLayout(null);
		bgp.setSize(getSize());
		bgp.setLocation(0, 0);
		this.add(bgp);
		
		bgp.add(exc);
		bgp.add(clo);
		bgp.add(med);
		bgp.add(pan);
		bgp.add(pro);
		
		bgp.add(shot_id);
		bgp.add(descr);
		bgp.add(len);
		unit_of_len.setForeground(fcl);
		bgp.add(unit_of_len);
		scel.setForeground(fcl);
		bgp.add(scel);
		bgp.add(sce);
		
		bgp.add(type);
		bgp.add(tyl);
		bgp.add(tyl2);
		seat.input.setEditable(true);
		//bgp.add(seat);
		bgp.add(sec);
		bgp.add(secl);
		secl1.setFont(ttiny);
		bgp.add(secl1);
		bgp.add(moti);
		bgp.add(ado);
		bgp.add(note);
		
		descr.input.addKeyListener(this);
		len.input.addKeyListener(this);
		type.addChangeListener(this);
		seat.input.addKeyListener(this);
		moti.input.addKeyListener(this);
		ado.addKeyListener(this);
		note.input.addKeyListener(this);
		sce.addActionListener(this);
		
		len.input.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				unit_of_len.setText("֡("+Main.getNTTimeCodeSmartly(Long.parseLong(len.getValue()))+")");
				if(e.getKeyCode()==KeyEvent.VK_DELETE||e.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
					if(len.getValue().length()==1) {
						e.consume();
						return;
					}
				}
			}
			public void keyReleased(KeyEvent e) {
			}
			public void keyTyped(KeyEvent e) {
				int keyChar=e.getKeyChar();
				if ((keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9)) {
					try {
					unit_of_len.setText("֡("+Main.getNTTimeCodeSmartly(Long.parseLong(len.getValue()+e.getKeyChar()))+")");
					}catch(Exception e1) {
						e1.printStackTrace();
						Main.showTip("�������ֵ", Main.ERROR);
						e.consume();
					}
				} else {
					e.consume(); 
				}
			}
		});
		
		this.updateCom();
	}
	/**
	 * ���¿ؼ�
	 */
	public void updateCom() {
		bgp.setBackground(getBackground());
		bgp.setSize(500,900);
		
		shot_id.setSize(30, 15);
		shot_id.setLocation(5, 5);
		shot_id.setFont(font);
		shot_id.setForeground(blue);
		
		descr.setLocation(10,30);
		descr.setBackground(getBackground());
		descr.updateCom();
		
		len.setLocation(10, 120);
		len.setBackground(getBackground());
		len.updateCom();
		
		unit_of_len.setSize(55, 30);
		unit_of_len.setLocation(len.getX()+len.getWidth()+5, len.getY());
		
		scel.setSize(50, 30);
		scel.setLocation(unit_of_len.getWidth()+unit_of_len.getX(), len.getY());
		sce.setSize(120, 25);
		sce.setLocation(scel.getWidth()+scel.getX(), scel.getY()+2);
		
		exc.setSize(13, 13);
		exc.setLocation(40, len.getY()+len.getHeight()+15);
		exc.setBackground(getBackground());
		clo.setSize(13, 13);
		clo.setLocation(97, len.getY()+len.getHeight()+15);
		clo.setBackground(getBackground());
		med.setSize(13, 13);
		med.setLocation(158, len.getY()+len.getHeight()+16);
		med.setBackground(getBackground());
		pan.setSize(13, 13);
		pan.setLocation(217, len.getY()+len.getHeight()+16);
		pan.setBackground(getBackground());
		pro.setSize(13, 13);
		pro.setLocation(275, len.getY()+len.getHeight()+16);
		pro.setBackground(getBackground());
		
		type.setSize(250, 30);
		type.setLocation(40,  len.getY()+len.getHeight()+30);
		type.setBackground(getBackground());
		tyl.setSize(40, 30);
		tyl.setLocation(10, type.getY());
		tyl.setForeground(fcl);
		tyl2.setSize(40, 30);
		tyl2.setLocation(type.getX()+type.getWidth()+10, type.getY());
		tyl2.setFont(italic);
		tyl2.setForeground(fcl);
		
		seat.setLocation(10,type.getY()+type.getHeight()+10);
		seat.setBackground(getBackground());
		//seat.updateCom();
		secl.setLocation(10, type.getY()+type.getHeight()+10);
		secl.setSize(50, 25);
		secl.setForeground(fcl);
		secl1.setLocation(secl.getX()-3, secl.getY()+secl.getHeight()+3);
		secl1.setSize(50, 20);
		secl1.setForeground(fcl);
		sec.setLocation(40, type.getY()+type.getHeight()+10);
		sec.setBackground(PnlColor.getColor(getBackground(), 0.8));
		
		moti.setLocation(seat.getX()+seat.getWidth()+30,seat.getY());
		moti.setBackground(getBackground());
		moti.updateCom();
		
		ado.setLocation(moti.getX(), seat.getY()+seat.getHeight()+10);
		ado.setBackground(getBackground());
		ado.updateCom();
		
		note.setLocation(10,sec.getY()+sec.getHeight()+10);
		note.setBackground(getBackground());
		note.updateCom();
	}
	/**
	 * ��ѡ�еľ�ͷȡ��Ϣ
	 */
	public void updateInfo() {
		shot sh=Main.gui.sbp.sb.getSelectedShot();
		if(sh==null) {
			bgp.setVisible(false);
			return;
		}
		bgp.setVisible(true);
		shot_id.setText("#"+(sh.sortid+1));
		
		descr.setValue(sh.vdo.description);
		
		len.setValue(sh.vdo.length+"");
		unit_of_len.setText("֡("+Main.getNTTimeCodeSmartly(sh.vdo.length)+")");
		
		sce.removeActionListener(this);
		sce.removeAllItems();
		int sindex=0,i=0;
		for(scene sc:Main.gui.sbp.st.scn) {
			if(sh.sceneid==sc.uid) {
				sindex=i;
			}
			sce.addItem(sc.gp.name);
			i++;
		}
		sce.setSelectedIndex(sindex);
		sce.addActionListener(this);
		
		sec.updateInfo();
		type.setValue(sh.shotType);
		tyl2.setText(Main.gui.sbp.sb.getTypeText(sh.shotType));
		moti.setValue(sh.motion);
		ado.setValue(sh.audio);
		note.setValue(sh.note);
		
	}
	public void saveData() {
		shot sh=Main.gui.sbp.sb.getSelectedShot();
		int previousLength=sh.vdo.length;
		Log.record("update shot "+sh.sortid+" profile.");
		sh.vdo.description=descr.getValue();
		sh.vdo.length=Integer.parseInt(len.getValue())<=0?1:Integer.parseInt(len.getValue());
		sh.shotType=type.getValue();
		sh.motion=moti.getValue();
		sh.audio=ado.getValue();
		sh.note=note.getValue();
		tyl2.setText(Main.gui.sbp.sb.getTypeText(sh.shotType));
		scene sc=Main.gui.sbp.st.getSceneByName(sce.getSelectedItem().toString());
		sh.sceneid=sc.uid;
		sh.vdo.groupId=sc.gp.uid;
		//�����ƶ�֮���ز�
		int deltax=sh.vdo.length-previousLength;
		Main.gui.bgp.ep.waveMove(deltax, sh.vdo.x+1, -1);
		
		sec.updateInfo();
		
		Main.gui.sbp.sb.updateShotTable();
		Main.gui.sbp.sb.repaint();
	}
	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		saveData();
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode()==KeyEvent.VK_ENTER) {
			saveData();
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		saveData();
		Main.gui.sbp.st.updateScene();
		Main.gui.sbp.st.repaint();
	}
}
