
package OSCAPI;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class PicUtils {
    private String destFile;
    private int width;
    private int height;
    private Image img;
    private String ext;
    //缩放图片工具的构造函数
    public PicUtils(Image img) throws IOException {
        this.img =img;
        //获取图片的长和宽
        this.width = this.img.getWidth(null);
        this.height = this.img.getHeight(null);
    }
    /**
     * 按比例对图片进行缩放.
     * @param scale 缩放比例
     * @throws IOException
     */
    public Image zoomByScale(double scale) throws IOException {
        //获取缩放后的长和宽
        int _width = (int) (scale * width);
        int _height = (int) (scale * height);
        //获取缩放后的Image对象
        return img.getScaledInstance(_width, _height, Image.SCALE_DEFAULT);
    }
    /**
     * 指定长和宽对图片进行缩放
     * @param width 长
     * @param height 宽
     * @throws IOException
     */
    public Image zoomBySize(int width, int height) throws IOException {
        //与按比例缩放的不同只在于,不需要获取新的长和宽,其余相同.
        return img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
    }
}
