package com.boot.future.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64coderTest {
	 private final static Log log = LogFactory.getLog(Base64coderTest.class);
    //base64字符串转化成图片
    public static boolean GenerateImage(String imgStr, String name) {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            //生成jpeg图片
           // String imgFilePath = "D://" + name + ".jpg";//新生成的图片
            OutputStream out = new FileOutputStream(name);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
          log.info(e.getMessage());
            return false;
        }
    }
    
    /**
     * @Description: 根据图片地址转换为base64编码字符串
     * @Author: 
     * @CreateTime: 
     * @return
     */
    public static String getImageStr(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

	public static void main(String[] args) {
	    String strImg = getImageStr("D://ludashi.jpg");
	    System.out.println(strImg);
	    GenerateImage(strImg, "D://a.jpg");
	}
}
