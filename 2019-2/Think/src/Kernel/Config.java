package Kernel;

public class Config {
	String conFile;
	String[] ts;
	static StringBuffer[][] item;
	int itemAmount=0;
	public Config(String conFile) {
		this.conFile=conFile;
		updateConfigs();
	}
	public void updateConfigs() {
		ts=new file0().read(conFile).split("\r\n");
		int len=ts.length;
		itemAmount=len;
		item=new StringBuffer[len][2];
		for(int i=0;i<len;i++) {
			String[] ts0=ts[i].split("=");
			item[i][0]=new StringBuffer(ts0[0]);
			item[i][1]=new StringBuffer(ts0[1]);
		}
	}
	public String read(String item) {
		for(int i=0;i<itemAmount;i++) {
			if(this.item[i][0].toString().equals(item)) {
				return this.item[i][1].toString();
			}
		}
		return "#undefined#";
	}
	public void write(String item,String value) {
		StringBuffer fileCode=new StringBuffer();
		boolean find=false;
		for(int i=0;i<itemAmount;i++) {
			if(this.item[i][0].toString().equals(item)) {
				find=true;
				this.item[i][1]=new StringBuffer(value);
			}
			fileCode.append(this.item[i][0]+"="+this.item[i][1]);
		}
		if(find) {
			return;
		}else {
			new file0().write(conFile, "\n\r"+item+"="+value, true);
			updateConfigs();
		}
	}
}
