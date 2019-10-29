package com.trunk.core.validate;

import com.trunk.core.listeners.LoadPropertiesListener;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;

/**
 * @author fanhaoming
 * @ClassName Captcha
 * @Description TODO
 * @Date 2019/10/29 16:37
 * @Version
 **/
@Data
public abstract class Captcha {
    protected int width = 200; // 验证码显示长度
    protected int height = 80; // 验证码显示高度
    protected Font font = new Font("宋体", Font.BOLD, 24); // 字体
    protected int codeLength = 5; // 验证码长度
    protected String word = ""; // 当前的字符串

    {
        String fontSizeStr = (String) LoadPropertiesListener.get("validateCode.fontSize");
        String codeLengthStr = (String) LoadPropertiesListener.get("validateCode.codeLength");
        if(StringUtils.isNotBlank(fontSizeStr)) {
            this.font = new Font("宋体", Font.BOLD, Integer.parseInt(fontSizeStr));
        }
        if(StringUtils.isNotBlank(codeLengthStr)) {
            this.codeLength = Integer.parseInt(codeLengthStr);
        }
    }

    public BufferedImage getBufferedImage()throws Exception {
        return null;
    }

    public abstract void out (OutputStream os) throws Exception;
}
