package com.example.practice;

import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class QueryVersion {

    @Resource(name = "restTemplate")
    private RestTemplate restTemplate;

    private static String FIX_URL = "http://172.25.220.22:8082";

    @Test
    void contextLoads() {

        String url = FIX_URL + "/pcp/queryPCPVersion?brandCode={brandCode}&tenantId={tenantId}&queryDate={queryDate}&ifLikeId={ifLikeId}";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("brandCode", "1");
        paramMap.put("tenantId", "01001");
        paramMap.put("queryDate", new SimpleDateFormat("yyyyMMdd").format(new Date()));
        paramMap.put("ifLikeId", "1");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("appid", "10000");
        headers.add("sign", sign("10000", "12345", paramMap));

        System.out.println("get请求头：" + JSON.toJSONString(headers));
        System.out.println("get请求体：" + JSON.toJSONString(paramMap));
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class, paramMap);
        System.out.println(response.getBody());
    }

    /**
     * 加密
     *
     * @param appid
     * @param appKey
     * @param paramMap 参数
     * @return
     */
    public static String sign(String appid, String appKey, Map<String, Object> paramMap) {

        // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
        List<Map.Entry<String, Object>> infoIds = new ArrayList<Map.Entry<String, Object>>(paramMap.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, Object>>() {
            public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
                return (o1.getKey()).toString().compareTo(o2.getKey());
            }
        });

        // 构造签名键值对的格式
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> item : infoIds) {
            if (item.getKey() != null || item.getKey() != "") {
                String key = item.getKey();
                Object val = item.getValue();
                if (!(val == "" || val == null)) {
                    sb.append(val + "&");
                }
            }
        }

        //去除最后一个&符号
        String valueStr = sb.toString();
        if (StringUtils.isNotBlank(valueStr)) {
            valueStr = valueStr.substring(0, valueStr.length() - 1);
        }

        //拼接appid + APP_KEY
        valueStr = appid.concat(appKey).concat(valueStr);
        System.out.println("需加密字符串：" + valueStr);
        //加密（MD5加密成32位大写）
        String sign = DigestUtil.md5Hex(valueStr).toUpperCase();
        System.out.println("加密后字符串：" + sign);
        return sign;
    }

}
