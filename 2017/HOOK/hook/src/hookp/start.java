package hookp;

public class start {
	static runblock rb=new runblock();
	static vars v=new vars();
	static String[] block;
	static FandR far=new FandR();
	start(String pro){
		StringBuffer pr=new StringBuffer(new file0().read(pro));
		//É¾³ý\n\r
		StringBuffer t0=new StringBuffer("");
		
		String[] te=pr.toString().split("\n");
		int l0=te.length;
		for(int a=0;a<l0;a++)
			t0.append(te[a]);
		
		String[] te0=t0.toString().split("\t");
		
		StringBuffer t1=new StringBuffer("");
		int l1=te0.length;
		for(int a=0;a<l1;a++)
			t1.append(te0[a]);

		String[] te1=t1.toString().split("\r");
		
		StringBuffer t2=new StringBuffer("");
		int l2=te1.length;
		for(int a=0;a<l2;a++)
			t2.append(te1[a]);
		pr=t2;
		//´¦Àí³ÌÐòÖÐ×Ö·û´®
		String[] strs=pr.toString().split("\"");
		int len=strs.length;
		StringBuffer[] result=new StringBuffer[len];
		for(int a=0;a<len;a++)
			result[a]=new StringBuffer(strs[a]);
		int i=0;
		for(int a=1;a<len;a+=2){
			vars.strs[i]=new StringBuffer(strs[a]);
			result[a]=new StringBuffer("_"+i);
		}
		for(int a=0;a<len;a++){
			pr.append(result[a]);
		}
		//·Ö¸îÓï¾ä¿é
		block=pr.toString().split(":");
		
		//Ö´ÐÐ
		if(!block[0].equals("entry")){
			String[] s=block[0].split(";");
			int sl=s.length;
			for(int a=0;a<sl;a++){
				v.newvars(s[a]);
			}
			for(int a=0;a<v.nc-1;a++)
				System.out.println(v.name[a]+" "+v.value[a]);
		}
		rb.run("entry", 0);
	}
}
