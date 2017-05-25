package com.rogchen.common.utils.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.rogchen.common.Page;
import com.rogchen.common.utils.json.JsonMapper;
import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Rogchen{cbs@xmyr.cn}
 * Created by chbs on 2017/4/11.
 * {tags}
 */
public class RenderUtils {

    private static final Logger logger = LoggerFactory.getLogger(RenderUtils.class);

    //定义content type
    public static final String TEXT_TYPE = "text/plain";
    public static final String JSON_TYPE = "application/json";
    public static final String XML_TYPE = "text/xml";
    public static final String HTML_TYPE = "text/html";
    public static final String JS_TYPE = "text/javascript";
    public static final String EXCEL_TYPE = "application/vnd.ms-excel";

    //定义header
    public static final String AUTHENTICATION_HEADER = "Authorization";

    //-- header 常量定义
    private static final String HEADER_ENCODING ="encoding";
    private static final String HEADER_NOCACHE = "no-cache";
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final boolean DEFAULT_NOCACHE = true;

    private static final String EXPIRES = "Expires";

    private static final String PRAGMA = "Pragma";

    private static final String CACHE_CONTROL = "Cache-Control";


    public static HttpServletResponse initResponseHeader(HttpServletResponse response,final String contentType,final String...headers){
        //分析headers参数
        String encoding = DEFAULT_ENCODING;
        boolean noCache = DEFAULT_NOCACHE;
        for(String header:headers){
            String headerName = StringUtils.substringBefore(header, ":");
            String headerValue = StringUtils.substringAfter(header, ":");

            if (StringUtils.equalsIgnoreCase(headerName, HEADER_ENCODING)) {
                encoding = headerValue;
            } else if (StringUtils.equalsIgnoreCase(headerName, HEADER_NOCACHE)) {
                noCache = Boolean.parseBoolean(headerValue);
            } else {
                throw new IllegalArgumentException(headerName + "不是一个合法的header类型");
            }
        }

        //设置headers参数
        String fullContentType = contentType +";charset="+encoding;
        response.setContentType(fullContentType);
        if(noCache){
            setNoCacheHeader(response);
        }
        return response;
    }

    /**
     * 设置禁止客户端缓存的Header.
     * @param response
     */
    public static void setNoCacheHeader(HttpServletResponse response) {
        // Http 1.0 header
        response.setDateHeader(EXPIRES, 1L);
        response.addHeader(PRAGMA, "no-cache");
        // Http 1.1 header
        response.setHeader(CACHE_CONTROL, "no-cache, no-store, max-age=0");
    }


    //绕过jsp/freemarker直接输出文本的函数

    /**
     * 直接输出内容的简便函数.
     * render("text/plain", "hello", "encoding:GBK");
     * render("text/plain", "hello", "no-cache:false");
     * render("text/plain", "hello", "encoding:GBK", "no-cache:false");
     * @param headers 可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
     * @param response
     * @param contentType
     * @param content
     * @param headers
     */
    public static void render(HttpServletResponse response,final String contentType,final String content,final String... headers){
        initResponseHeader(response, contentType, headers);
        try {
            response.getWriter().write(content);
            response.getWriter().flush();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 直接输出文本.
     *
     * @see (String, String, String...)
     */
    public static void renderText(HttpServletResponse response, final String text, final String... headers) {
        render(response, RenderUtils.TEXT_TYPE, text, headers);
    }

    /**
     * 直接输出HTML.
     *
     * @see (String, String, String...)
     */
    public static void renderHtml(HttpServletResponse response, final String html, final String... headers) {
        render(response, RenderUtils.HTML_TYPE, html, headers);
    }

    /**
     * 直接输出XML.
     *
     * @see (String, String, String...)
     */
    public static void renderXml(HttpServletResponse response, final String xml, final String... headers) {
        render(response, RenderUtils.XML_TYPE, xml, headers);
    }


    /**
     * 直接输出JSON.
     *
     * @param jsonString json字符串.
     * @see (String, String, String...)
     */
    public static void renderJson(HttpServletResponse response, final String jsonString, final String... headers) {
        render(response, RenderUtils.JSON_TYPE, jsonString, headers);
    }

    /**
     * 直接输出JSON,使用Jackson转换Java对象.
     */
    private static ObjectMapper mapper = JsonMapper.nonEmptyMapper().getMapper();

    public static void renderJson(HttpServletResponse response, final Page<? extends Object> page,
                                  final String... headers) {
//        initResponseHeader(response, RenderUtils.JSON_TYPE, headers);
//        Map<String, Object> result = Maps.newHashMap();
//        result.put("result", "success");
//        result.put("rows", page.getContent());
//        result.put("results", page.getTotalSize());
//        try {
//            mapper.writeValue(response.getWriter(), result);
//        } catch (IOException e) {
//            throw new IllegalArgumentException(e);
//        }
        renderJson(response, page.getCount(), page.getContent(), headers);
    }

    /**
     * IE底下头设置为json,如果是通过ajaxFileUpload的方式（内部是用iframe），会变成下载,故设置为html
     *
     * @param response
     * @param data
     * @param headers
     */
    public static void renderJsonWithHtmlHeader(HttpServletResponse response, final Object data, final String... headers) {
        initResponseHeader(response, RenderUtils.HTML_TYPE, headers);
        try {
            mapper.writeValue(response.getWriter(), data);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     *  渲染 数据 到 response
     *
     * @param response
     * @param totalSize
     * @param content
     * @param headers
     */
    public static void renderJson(HttpServletResponse response, long totalSize, List<? extends Object> content,
                                  final String... headers) {
        initResponseHeader(response, RenderUtils.JSON_TYPE, headers);
        Map<String, Object> result = Maps.newHashMap();
        result.put("result", "success");
        result.put("rows", content);
        result.put("results", totalSize);
        try {
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapper.writeValue(response.getWriter(), result);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     *  渲染 数据 到 response
     *
     * @param response
     * @param content
     * @param headers
     */
    public static void renderListJson(HttpServletResponse response, List<? extends Object> content, final String... headers) {
        initResponseHeader(response, RenderUtils.JSON_TYPE, headers);
        Map<String, Object> result = Maps.newHashMap();
        result.put("result", "success");
        result.put("rows", content);
        try {
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapper.writeValue(response.getWriter(), result);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     *  渲染 object 到 response
     *
     * @param response
     * @param object
     * @param headers
     */
    public static void renderObject(HttpServletResponse response, Object object, final String... headers) {
        initResponseHeader(response, RenderUtils.JSON_TYPE, headers);
        try {
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapper.writeValue(response.getWriter(), object);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     *  渲染分页数据 到 response
     *
     * @param response
     * @param page
     * @param props
     */
    public static void renderPageJson(HttpServletResponse response, Page<? extends Object> page,
                                      String props) {
        if (StringUtils.isNotBlank(props)) {
            List<Map<String, Object>> list = transListProp(page.getContent(), props);
            renderJson(response, page.getCount(), list);
        } else {
            renderJson(response, page.getCount(), page.getContent());
        }
    }

    /**
     * 通过指定转成的属性,props：多个以,分隔
     *
     * @param objList
     * @param props
     * @return
     */
    public static List<Map<String, Object>> transListProp(List<? extends Object> objList, String props) {
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        if (objList != null) {
            for (Object obj : objList) {
                Map<String, Object> map = objectChangeToMap(obj, props);
                mapList.add(map);
            }
        }
        return mapList;
    }

    /**
     * 把 obj 按照 属性-->值 放到 map里
     *
     * @param obj
     * @param props
     * @return
     */
    public static Map<String, Object> objectChangeToMap(Object obj, String props) {
        Map<String, Object> map = Maps.newHashMap();
        String[] propArr = props.split(",");
        for (String prop : propArr) {
            if (StringUtils.isNotBlank(prop)) {
                try {
                    prop = prop.trim();
                    map.put(prop, PropertyUtils.getProperty(obj, prop));
                } catch (NestedNullException exception) {
                    map.put(prop, null);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        }
        return map;
    }

    /**
     * 数据渲染到 response
     *
     * @param response
     * @param object
     * @param props
     */
    public static void renderJson(HttpServletResponse response, Object object, String props) {
        if (StringUtils.isNotBlank(props)) {
            Map<String, Object> map = objectChangeToMap(object, props);
            renderJson(response, map);
        }
    }

    /**
     * 数据渲染到 response
     *
     * @param response
     * @param object
     */
    public static void renderJson(HttpServletResponse response, Object object) {
        try {
            initResponseHeader(response, RenderUtils.JSON_TYPE);
            mapper.writeValue(response.getWriter(), object);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static List filterPageData(Page<? extends Object> page, String props) {
        List list = page.getContent();
        if (StringUtils.isNotBlank(props)) {
            list = transListProp(page.getContent(), props);
        }
        return list;
    }

    /**
     * 返回包含 分页信息 的 map
     *
     * @param page
     * @param props
     * @return
     */
    public static Map<String, Object> filterPageDataResult(Page<? extends Object> page, String props) {
        Map<String, Object> message = new HashMap<String, Object>();
        message.put("result", "success");
        return filterPageDataResult(page, props, message);
    }

    /**
     * 返回分页信息 的 map
     *
     * @param page
     * @param props
     * @param message
     * @return
     */
    private static Map<String, Object> filterPageDataResult(Page<? extends Object> page, String props, Map<String, Object> message) {
        Map<String, Object> result = new HashMap<String, Object>();
        List gridData = filterPageData(page, props);
        result.put("rows", gridData);
        message.put("results", page.getCount());
        if (message != null && !message.isEmpty()) result.putAll(message);
        return result;
    }

    /**
     * 返回 成功的 map
     *
     * @return
     */
    public static Map<String, Object> getSuccessMap() {
        Map<String, Object> result = Maps.newHashMap();
        result.put("result", "success");
        return result;
    }

    // 返回 成功的 map
    public static final Map<String, Object> SUCCESS_RESULT = getSuccessMap();

    /**
     * 返回 失败的 结果 map
     *
     * @param message
     * @return
     */
    public static Map<String, Object> getFailMap(String message) {
        Map<String, Object> result = Maps.newHashMap();
        result.put("result", "error");
        result.put("message", message);
        return result;
    }

    /**
     *  重置 表单token 的 visitCount 标志
     *
     * @param request
     */
    public static void resetFormTokenVisitCount(HttpServletRequest request){
//        request.setAttribute(BizConstants.DEFAULT_RESET_FORM_TOKEN_VISIT_COUNT, "true");
    }

    /**
     * 缥 Model 中填充 参数
     *
     * @param modelAttrs
     * @param model
     * @return
     */
    public static Model fillModel(Map<String, Object> modelAttrs, Model model) {
        if (modelAttrs == null || model == null) {
            return model;
        }
        for (String key : modelAttrs.keySet()) {
            model.addAttribute(key, modelAttrs.get(key));
        }
        return model;
    }

    /**
     * 给 ModelAndView 中 填充参数
     *
     * @param modelAttrs
     * @param model
     * @return
     */
    public static ModelAndView fillModel(Map<String, Object> modelAttrs, ModelAndView model) {
        if (modelAttrs == null || model == null) {
            return model;
        }
        for (String key : modelAttrs.keySet()) {
            model.addObject(key, modelAttrs.get(key));
        }
        return model;
    }

    /**
     * 用户API列表数据获取
     * @param page
     * @param props
     * @return
     */
    public static Map<String, Object> filterApiPageData(Page<? extends Object> page, String props){
        Map<String, Object> message = new HashMap<String, Object>();
        Map<String, Object> pageInfo = new HashMap<String, Object>();
        message.put("res_data", pageInfo);
        pageInfo.put("total", page.getCount());
        pageInfo.put("page_no", page.getPageNo());
        pageInfo.put("page_size", page.getPageSize());
        pageInfo.put("data_list", transListProp(page.getContent(), props));
        return pageInfo;
    }

}
