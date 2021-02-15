package tp0;

import java.util.LinkedList;

import javax.swing.ImageIcon;

public class mystack {
	 private LinkedList ll=new LinkedList();
	 String name;
	 mystack(String name){
		 this.name=name;
	 }
	 public void push(Object o){
		 ll.addFirst(o);
	 }
	 public Object pop(){
		 Object obj=ll.removeFirst();
		 return obj;
	 }
	 public Object peek(){
		 return ll.getFirst();
	 }
	 public boolean empty(){
		 return ll.isEmpty();
	 }
}