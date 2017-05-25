package com.rogchen.common.utils;

import com.google.common.collect.Maps;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * @Author Rogchen{cbs@xmyr.cn}
 * Created by chbs on 2017/4/11.
 * {tags}
 */
public class MapUtils {

    public static Map paramMap(HttpServletRequest request){
        Map<String, Object> maps = Maps.newHashMap();
        Enumeration<String> requestParamEnum = request.getParameterNames();
        while(requestParamEnum.hasMoreElements()){
            String paramKey = requestParamEnum.nextElement();
            try {
                maps.put(paramKey, request.getParameter(paramKey));
                //maps.put(paramKey, new String(request.getParameter(paramKey).getBytes("ISO-8859-1"),"UTF-8"));
            }
            catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return maps;
    }
}
