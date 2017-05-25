package com.rogchen.common.utils.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Maps;
import com.rogchen.common.utils.json.JsonMapper;
import com.rogchen.common.xml.UtilValidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *  ajax 响应输出工具
 */
public class AjaxResponseUtils {
    private static final Logger logger = LoggerFactory.getLogger(AjaxResponseUtils.class);

    // 响应状态
    public static enum ResultType{
        // 成功    失败    nosession
        success, error, nosession
    }

    private static final JsonMapper mapper = JsonMapper.nonEmptyMapper();


    /**
     *  操作成功 响应输出
     *
     * @param response
     * @param headers
     */
    public static void renderSuccess(HttpServletResponse response, final String... headers) {
        render(response, null, null, null, ResultType.success, headers);
    }

    /**
     *  操作成功 响应输出, 附带 操作记录的 id
     *
     * @param response
     * @param id
     * @param headers
     */
    public static void renderSuccessWithId(HttpServletResponse response, Long id, final String... headers) {
        Map<String,Long> map = Maps.newHashMap();
        map.put("id",id);
        render(response, map, null, null, ResultType.success, headers);
    }

    /**
     *  操作成功 响应输出
     *
     * @param response
     * @param o
     * @param headers
     */
    public static void renderSuccess(HttpServletResponse response, final Object o, final String... headers) {
        render(response, o, null, null, ResultType.success, headers);
    }

    /**
     *  操作失败 响应输出
     *
     * @param response
     * @param message
     * @param headers
     */
    public static void renderError(HttpServletResponse response, final String message, final String... headers) {
        renderError(response, null, message, null, headers);
    }

    /**
     *  操作失败 响应输出,附带 出错码
     *
     * @param response
     * @param message
     * @param code
     */
    //TODO若重载，在调用的时候报模棱两可
    public static void renderError(HttpServletResponse response, final String message, final String code) {
        String[] empty = {};
        renderError(response, null, message, code, empty);
    }

    /**
     *  操作失败 响应输出
     */
    public static void renderError(HttpServletResponse response, final Object o, final String message, final String code, final String... headers) {
        render(response, o, message, code, ResultType.error, headers);
    }

    /**
     *  输出响应 nosession
     */
    public static void renderNoSession(HttpServletResponse response, final String... headers) {
        render(response, null, null, null, ResultType.nosession, headers);
    }

    /**
     *  输出响应
     */
    public static void render(HttpServletResponse response, final Object o, final String message, final String code, ResultType resultType, final String... headers) {
        ObjectNode objectNode = mapper.getMapper().createObjectNode();
        objectNode.put("result", resultType.toString());
        if(UtilValidate.isNotEmpty(o)) objectNode.put("data", mapper.getMapper().valueToTree(o));
        if(UtilValidate.isNotEmpty(message)) objectNode.put("message", message);
        if(UtilValidate.isNotEmpty(code)) objectNode.put("code", code);
        
        render(response, objectNode, headers);
    }

    /**
     *  输出响应
     *
     * @param response
     * @param objectNode
     * @param headers
     */
    public static void render(HttpServletResponse response, Object objectNode, final String... headers) {
        try {
            RenderUtils.renderJson(response, mapper.getMapper().writeValueAsString(objectNode), headers);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
