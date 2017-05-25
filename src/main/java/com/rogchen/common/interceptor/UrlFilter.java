package com.rogchen.common.interceptor;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Rogchen{cbs@xmyr.cn}
 * Created by chbs on 2017/4/7.
 * {tags}
 */
public class UrlFilter implements Filter {

    private List<String> list = new ArrayList();

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String requestURI = request.getRequestURI();
        for(String str :list){
            if(requestURI.contains(str)){
//                System.out.println("url的过滤："+str);
//                response.sendRedirect("/blog/front/blogIndex.html");
                chain.doFilter(request, response);
                return;
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {
       String urlfilter = config.getInitParameter("urlfilter");
        if(urlfilter !=null){
            for(String ig : StringUtils.split(urlfilter,',')){
//                System.out.println("输出Ig:"+ig);
                list.add(ig);
            }
        }

    }

}
