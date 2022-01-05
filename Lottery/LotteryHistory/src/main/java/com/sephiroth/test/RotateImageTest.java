package com.sephiroth.test;

import net.sourceforge.tess4j.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class RotateImageTest {
    public static void main(String[] args) throws IOException {
        // 创建实例
        ITesseract instance = new Tesseract();

        // 设置识别语言

        instance.setLanguage("chi_sim");

        // 设置识别引擎

        instance.setOcrEngineMode(ITessAPI.TessOcrEngineMode.OEM_TESSERACT_LSTM_COMBINED);

        // 读取文件

        BufferedImage image = ImageIO.read(RotateImageTest.class.getResourceAsStream("/2.png"));
        try {
            BufferedImage image1 = rotateImage(image,45);
            // 识别

            String result = instance.doOCR(image1);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }

    public static String getInfo(InputStream is) throws IOException {
        // 创建实例
        ITesseract instance = new Tesseract();

        // 设置识别语言

        instance.setLanguage("chi_sim");

        // 设置识别引擎

        instance.setOcrEngineMode(ITessAPI.TessOcrEngineMode.OEM_TESSERACT_LSTM_COMBINED);

        // 读取文件

        BufferedImage image = ImageIO.read(is);
        try {
            //BufferedImage image1 = rotateImage(image,45);
            // 识别

            String result = instance.doOCR(image);
            System.out.println(result);
            return result;
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static BufferedImage rotateImage(final BufferedImage bufferedimage,
                                            final int degree) {
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(w, h, type))
                .createGraphics()).setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
        return img;
    }

}
