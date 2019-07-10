package cn.zhu4wp.springboot.shopping.shopping.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class ValidateCode {
    //图片宽度
    private int width = 160;
    //图片高度
    private int height = 40;
    //验证码字符个数
    private int codeCount = 5;
    //验证码干扰线数
    private int lineCount = 150;
    //验证码
    private String code = null;
    //验证码图片Buffer
    private BufferedImage buffImg = null;
    //验证码范围
    private char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public ValidateCode() {
        this.createCode();
    }

    public ValidateCode(int width,int height){
        this.width = width;
        this.height = height;
        this.createCode();
    }
    /**
     * @param width 图片宽
     * @param height 图片高
     * @param codeCount 字符个数
     * @param lineCount 干扰线条数
     */
    public ValidateCode(int width, int height, int codeCount, int lineCount) {
        this.width = width;
        this.height = height;
        this.codeCount = codeCount;
        this.lineCount = lineCount;
        this.createCode();
    }
    public void createCode(){
        int x = 0;
        int fontHeight = 0;
        int codeY = 0;
        int red = 0;
        int green = 0;
        int blue = 0;
        //每个字符的宽度
        x = width / (codeCount + 2);
        //字符的高度
        fontHeight = height - 2;
        codeY = height - 4;

        //图像buffer
        buffImg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();
        //生成随机数
        Random random = new Random();
        //图像填充为白色
        g.setColor(Color.white);
        g.fillRect(0,0,width,height);
        //创建字体
        Font font = new Font("Fixedsys",Font.PLAIN,fontHeight);
        g.setFont(font);

        for (int i = 0; i < lineCount; i++) {
// 设置随机开始和结束坐标
            int xs = random.nextInt(width);//x坐标开始
            int ys = random.nextInt(height);//y坐标开始
            int xe = xs + random.nextInt(width / 8);//x坐标结束
            int ye = ys + random.nextInt(height / 8);//y坐标结束
// 产生随机的颜色值，让输出的每个干扰线的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawLine(xs, ys, xe, ye);
        }
        // randomCode记录随机产生的验证码
        StringBuffer randomCode = new StringBuffer();
// 随机产生codeCount个字符的验证码。
        for (int i = 0; i < codeCount; i++) {
            String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
// 产生随机的颜色值，让输出的每个字符的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawString(strRand, (i + 1) * x, codeY);
// 将产生的四个随机数组合在一起。
            randomCode.append(strRand);

        }
// 将四位数字的验证码保存到Session中。
        code = randomCode.toString();
    }

    public void write(String path) throws IOException {
        OutputStream sos = new FileOutputStream(path);
        this.write(sos);
    }
    public void write(OutputStream sos) throws IOException {
        ImageIO.write(buffImg, "png", sos);
        sos.close();
    }
    public BufferedImage getBuffImg() {
        return buffImg;
    }
    public String getCode() {
        return code;
    }
}
