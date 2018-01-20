package com.boot.future.controller;

import com.alibaba.fastjson.JSONObject;
import com.boot.future.tools.HttpUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/url")
public class HttpController {

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getFansInsertData2() {
        Map<String, Object> map = new HashMap<String, Object>();
        boolean flag = false;
        final String url = "http://172.24.18.206:8800/api/VioOrder?order_id=101132";
        try {
            JSONObject json = HttpUtils.httpRequest(url, "GET", null);
            map.put("json", json);
            flag = true;
            map.put("flag", flag);
        } catch (Exception e) {
            map.put("flag", flag);
            map.put("msg", e.getMessage());
        }
        return map;
    }
}
