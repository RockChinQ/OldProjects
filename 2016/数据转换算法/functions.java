package Rocket;
class functions{
	boolean inputfile(String dat){
		StringBuffer[] pgm=nem StringBuffer[]{dat.split(";")};
		label:for(int a=0;a<pgm.length;a++){
			StringBuffer[] b=new StringBuffer[]{pgm[a].split(" ")};
			switch(b[a]){
			case "sav":
			case "set":
			case "if":
			case ":endif":
			case "fun":
			case ":endfun":
			case "runto":
			case "return":
			case ":break":
			case "while":
			case ":endwhile":
			case "outprint":
			case "showfun":
			case "notshowfun":
			case "runstop":
				int c=0;
				break;
			default:
				int c=1;
				break label;
			}
		}
		if(c=0)
			return true;
		return false;
	}
    void running(String[] pgm){
    	try{
    		boolean show=false;
    		double[] num=new double[pgm.length];
    		StringBuffer[] name=new StringBuffer[pgm.length];
    		StringBuffer[] da;
    		for(int d=0;d<pgm.length;d++){
    			da=new StringBuffer[]{pgm[d].split(" ")};
    			if(da[0].equals("sav")){
    				for(int e=0;e<name.length;e++){
    					if(name[e]==null){
    						b=da[1].split("=");
    						name[e]=b[0];
    						if(b[1]!=null){
    							num[e]=this.getnum(b[1]);
    						}
    						break;
    					}
    				}
    			}
    			if(da[0].equals("set")){
    				b=da[1].split("=");
    				for(e=0;e<name.length;e++){
    					if(name[e].equals(b[0]))
    						num[e]=this.getnum(b[1]);
    					break;
    				}
    			}
    			if(da[0].equals("if")){
    				if(!this.getswitch(da[1])){
    					for(int f=d;f<pgm.length;f++){
    						if(pgm[f].equals(":endif")){
    							d=f;
    							break;
    						}
    					}
    				}
    			}
    			if(da[0].equals("runto")){
    				for(int f=0;f<pgm.length;f++){
    					b=pgm[f].split(" ")
    				}
    			}
    		}
    	}
    }
}
	
	
	
	
	
	
	
	
	
	
	
	


	
	