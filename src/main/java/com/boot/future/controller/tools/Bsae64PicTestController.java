package com.boot.future.controller.tools;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boot.future.swagger.result.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@Controller
@RequestMapping("/pic")
public class Bsae64PicTestController {

    final static Logger logger = LoggerFactory.getLogger(Bsae64PicTestController.class);

    @GetMapping(value = "/get")
    @ResponseBody
    public Map<String, Object> updateLoginUser() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        boolean flag = false;
        try {
            // Base64解码
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = decoder.decodeBuffer(getImageStr());
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成jpeg图片
            // String imgFilePath = "D://" + name + ".jpg";//新生成的图片
            OutputStream out = new FileOutputStream("D://a.jpg");
            out.write(b);
            out.flush();
            out.close();
            flag = true;
        } catch (Exception e) {
            map.put("msg", e.getMessage());
        }

        map.put("flag", flag);
        return map;
    }

    @GetMapping(value = "/getp")
    @ResponseBody
    public Map<String, Object> getBasePic() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        boolean flag = false;
        try {
            InputStream inputStream = null;
            byte[] data = null;
            inputStream = new FileInputStream("D://ludashi.jpg");
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
            // 加密
            BASE64Encoder encoder = new BASE64Encoder();
            String pic = encoder.encode(data);
            map.put("pic", pic);
            flag = true;
        } catch (Exception e) {
            map.put("msg", e.getMessage());
        }
        map.put("flag", flag);
        return map;
    }

    /**
     * @return
     * @Description: 根据图片地址转换为base64编码字符串
     * @Author:
     * @CreateTime:
     */
    public static String getImageStr() {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream("D://ludashi.jpg");
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
}
