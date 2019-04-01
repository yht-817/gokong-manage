package cn.gokong.www.base.util.imgcodetools;


import cn.hutool.core.util.ImageUtil;
import cn.ikeek.www.ftp.FTPUtil;
import cn.ikeek.www.ftp.enums.DirectoryEnums;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.Hashtable;

/**
 * 对图片添加二维码
 */
public class CodeImgTools {
    /**
     * 二维码图片的生成
     */
    public static BufferedImage createImage(String content, int qrcode_width, int qrcode_height) throws WriterException {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, qrcode_width, qrcode_height, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }

    // 把图片和二维码合并
    public static String imgCode(String contUrl, int type) {
        String path_url = null;
        try {
            String filec = "/data/code/";
            //String filec = "C:\\Users\\swk\\Desktop\\";
            String filepath = null;
            if (type == 1) {
                // 市长
                //filepath = "C:\\Users\\swk\\Desktop\\code.png";
                filepath = "/data/deploy/codeback/code.png";
            } else {
                // 普通用户
                //filepath = "C:\\Users\\swk\\Desktop\\codep.png";
                filepath = "/data/deploy/codeback/codep.png";
            }
            File file = new File(filepath);
            File fc = new File(filec);
            if (!fc.exists()) {
                fc.mkdir();
            }
            String path = filec + System.currentTimeMillis() + ".png";
            int qrcode_width = 200;
            int qrcode_height = 200;
            int width = -ImageUtil.read(file).getWidth() / 2 + 222;
            int height = ImageUtil.read(file).getHeight() / 2 - 225;
            System.out.println("二维码生成宽度：" + width + "高度：" + height);
            BufferedImage image = createImage(contUrl, qrcode_width, qrcode_height);
            ImageUtil.pressImage(ImageUtil.read(file), new File(path), image, width, height, 1.0f);
            // 将图片上传到FTP服务器
            File filein = new File(path);
            path_url = FTPUtil.uploadStreamFile(DirectoryEnums.CODE_100010006, new FileInputStream(filein));
            System.out.println("+++" + path_url);
            filein.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path_url;
    }
}
