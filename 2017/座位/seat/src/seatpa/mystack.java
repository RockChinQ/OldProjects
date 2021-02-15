package seatpa;

import java.util.LinkedList;

import javax.swing.ImageIcon;

public class mystack {
	 private LinkedList ll=new LinkedList();
	 String name;
	 mystack(String name){
		 this.name=name;
	 }
	 public void push(Object o){
		 if(name.equals("ms"))
			 editgui.undo.setIcon(new ImageIcon(main.sou+"undo.jpg"));
		 else if(name.equals("re")){
			 editgui.redo.setIcon(new ImageIcon(main.sou+"redo.jpg"));
		 }
		 ll.addFirst(o);
	 }
	 public Object pop(){
		 Object obj=ll.removeFirst();
		 if(name.equals("ms")&&ll.isEmpty())
			 editgui.undo.setIcon(new ImageIcon(main.sou+"undo2.jpg"));
		 else if(name.equals("re")&&ll.isEmpty()){
			 editgui.redo.setIcon(new ImageIcon(main.sou+"redo2.jpg"));
		 }
		 return obj;
	 }
	 public Object peek(){
		 return ll.getFirst();
	 }
	 public boolean empty(){
		 return ll.isEmpty();
	 }
}