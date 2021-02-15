package seatpa;

public class record {
	static mystack ms=new mystack("ms");
	static mystack re=new mystack("re");
	void delete(){
		while(!re.empty()){
			re.pop();
		}
	}
}
