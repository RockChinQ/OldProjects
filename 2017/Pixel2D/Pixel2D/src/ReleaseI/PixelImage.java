package ReleaseI;

public class PixelImage {
	public int width,height;
	public String code;
	public PixelImage(String path){
		try{
			String[] fcode=new file0().read(path).split("&");
			width=Integer.parseInt(fcode[0]);
			height=Integer.parseInt(fcode[1]);
			StringBuffer codes=new StringBuffer();
			int len=fcode.length-2;
			for(int a=0;a<len;a++){
				codes.append(fcode[a+2]+"&");
			}
			code=new String(codes.toString());
		}catch(Exception e){
			new PixelSystemException("PixelSystemException-->ControlException-->PictureCodeError\nat PixelImage-->PixelImage(String path);");
		}
		//System.out.println("123456");
	}
	public PixelImage(int width,int height,String imgcode){
		code=new String(imgcode);
		this.width=width;
		this.height=height;
	}
}
