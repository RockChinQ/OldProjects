
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
    //����ͼƬ���ߵĹ��캯��
    public PicUtils(Image img) throws IOException {
        this.img =img;
        //��ȡͼƬ�ĳ��Ϳ�
        this.width = this.img.getWidth(null);
        this.height = this.img.getHeight(null);
    }
    /**
     * ��������ͼƬ��������.
     * @param scale ���ű���
     * @throws IOException
     */
    public Image zoomByScale(double scale) throws IOException {
        //��ȡ���ź�ĳ��Ϳ�
        int _width = (int) (scale * width);
        int _height = (int) (scale * height);
        //��ȡ���ź��Image����
        return img.getScaledInstance(_width, _height, Image.SCALE_DEFAULT);
    }
    /**
     * ָ�����Ϳ��ͼƬ��������
     * @param width ��
     * @param height ��
     * @throws IOException
     */
    public Image zoomBySize(int width, int height) throws IOException {
        //�밴�������ŵĲ�ֻͬ����,����Ҫ��ȡ�µĳ��Ϳ�,������ͬ.
        return img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
    }
}
