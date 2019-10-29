package com.trunk.core.validate;

import com.trunk.core.utils.Randoms;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * @author fanhaoming
 * @ClassName JpgCaptcha
 * @Description TODO
 * @Date 2019/10/29 16:19
 * @Version
 **/
public class JpgCaptcha extends Captcha {
    public JpgCaptcha(int width,int height) {
        this.width = width;
        this.height = height;
    }
    public JpgCaptcha(int width,int height,int codeLength) {
        this.width = width;
        this.height = height;
        this.codeLength = codeLength;
    }
    public BufferedImage getBufferedImage() throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics g = image.getGraphics();
        //生成随机类
        Random random = new Random();
        // 设定背景色
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        //设定字体
        g.setFont(font);
        // 随机产生168条干扰线，使图象中的认证码不易被其它程序探测到
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 168; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        //取随机产生的码
        word = Randoms.createRandomCharData(codeLength);
        //4代表4位验证码,如果要生成更多位的认证码,则加大数值
        // 将认证码显示到图象中
        for(int i=0,j=word.length(),k=width/j;i<j;i++) {
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(word.substring(i, i + 1), k*i+5 , height/2);
        }
        // 释放图形上下文
        g.dispose();
        return image;
    }
    @Override
    public void out(OutputStream os) throws IOException {
        ImageIO.write(getBufferedImage(),"jpg",os);
    }
    //给定范围获得随机颜色
    static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
