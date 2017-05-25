package com.rogchen.common;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Cookie 辅助类
 *
 */
public class CookieUtil {

	/**默认Cookie保存时间为7天*/
	public static final Integer MaxAge=60*60*24*7;
	public static final Integer Hour=60*60;
	public static final Integer Half_Hour=60*30;
	public static final Integer Day=60*60*24;
    public static final Integer Closed=-1;

    public static final String USER_LOGIN_NAME="c_userName";

    /** 店中店商品 推荐url标识 */
    public static final String REBATE_URL="c_rebateUrl";

	/** 浏览店中店时,把店主(userId) 存入cookie
	 *  推荐相关的逻辑，现在不开放属性，只开放方法，具体请调用
	 *  getCid  -- 获取cookie中的cid
	 *  setCid  -- 设置cid到cookie
	 *  setCidWithoutMyself  -- 设置除了登录会员的cId之外的cId到cookie
	 *  @modified by yucx on 15/7/1 下午2:29 modify 重构设置和获取cid的方法
	 **/
    private static final String SHOPINSHOP_USERID ="c_shopInShop_userId";

    /** 当前位置的lbs */
    public static final String CURRENT_LBS="c_currentLBS";


    /** 浏览门店时,把 门店(storeId) 存入cookie */
    public static final String STORE_ID="c_store_id";
	// 浏览微店时,把 微店(userId) 存入cookie
    public static final String M_STORE_USER_ID="c_m_store_user_id";

    /**  当前访问者(ip) 所在的地区 */
    public static final String AREA_INFO="c_area_info";

    /**  当前访问者 所在的省份、市(area.id) */
    public static final String PROVINCE_ID ="c_province_id";
    public static final String CITY_ID ="c_city_id";

	// 区/县 所属areaId
	public static final String COUNTY_AREA_ID ="c_county_area_id";
	// 街道/镇 所属areaId
	public static final String TOWN_AREA_ID ="c_town_area_id";
	// 村庄 所属areaId
    public static final String VILLAGE_AREA_ID ="c_village_area_id";
	// 详细地址
    public static final String ADDRESS_DETAIL ="c_address_detail";// addressDetail
	// 收货人联系电话
    public static final String ADDRESS_PHONE ="c_address_phone";
	// 收货人姓名
    public static final String ADDRESS_USER_NAME ="c_address_userName";

	// 跳转到选择收货地址的 来源类型
    public static final String FROM_TYPE ="c_from_type";
    
    // 订单收货地址Id
    public static final String ORDER_ADDRESS_ID ="order_address_id";
    // 订单发票Id
    public static final String ORDER_INVOICE_ID ="order_invoice_id";
    // 搭配套餐Id
    public static final String ORDER_COMBINATION_ID ="order_combination_id";
    // 搭配套餐数量
    public static final String ORDER_COMBINATION_NUM ="order_combination_num";
    //订单派送类型
    public static final String ORDER_DISTRBUTE_TYPE = "order_distrbute_type";

    /** 微信用户的 openId */
    public static final String WX_OPEN_ID_KEY="c_open_id";


    public static final String THIRD_PARTY_USER_ID ="c_third_party_user_name";
    public static final String REGISTER_PLATFORM="c_register_platform";
    public static final String USER_ID="c_uid";




	/**
	 * 获得cookie
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param name
	 *            cookie name
	 * @return if exist return cookie, else return null.
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Assert.notNull(request);
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0)
		{
			try {
				name = URLEncoder.encode(name, "UTF-8");
				for (Cookie cookie : cookies)
				{
					if (name.equals(cookie.getName()))
					{
						return cookie;
					}
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	/**
	 * 获得指定Cookie的值
	 * @param request 请求对象
	 * @param name 名字
	 * @return 值
	 */
	public static String getCookieValue(HttpServletRequest request, String name) {
		String value = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) 
		{
			for (Cookie cookie : cookies)
			{
				if (cookie.getName().equals(name)) 
				{
					try {
						value = URLDecoder.decode(cookie.getValue(), "utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return value;
	}

    /**
     * 获得指定Cookie的值
     * @param request 请求对象
     * @param name 名字
     * @return 值
     */
    public static Long getCookieValueParseLong(HttpServletRequest request, String name) {
        String value = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
        {
            for (Cookie cookie : cookies)
            {
                if (cookie.getName().equals(name))
                {
                    try {
                        value = URLDecoder.decode(cookie.getValue(), "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        Long value_ = -2L;
        try {
            value_ = Long.parseLong(value);
        }catch (NumberFormatException e){}

        return value_;
    }

	/**
	 * 获得指定Cookie的值
	 * @param request 请求对象
	 * @param name 名字
	 * @return 值
	 */
	public static Integer getCookieValueParseInteger(HttpServletRequest request, String name) {
		String value = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null)
		{
			for (Cookie cookie : cookies)
			{
				if (cookie.getName().equals(name))
				{
					try {
						value = URLDecoder.decode(cookie.getValue(), "utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			}
		}

		Integer value_ = -2;
		try {
			value_ = Integer.parseInt(value);
		}catch (NumberFormatException e){}

		return value_;
	}

	/**
	 * 添加cookie
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param name
	 *            cookie名称
	 * @param value
	 *            cookie值
	 * @param maxAge
	 *            有效期(单位: 秒)
	 */
	public static void addCookie(HttpServletResponse response,
			String name, String value, Integer maxAge) {
		addCookie(response, name, value, maxAge, "/", "", null, true);
	}

    /**
     * 添加cookie
     *
     * @param response
     *            HttpServletResponse
     * @param name
     *            cookie名称
     * @param value
     *            cookie值
     * @param useDefaultTime
     *            是否使用cookie默认的 有效时间值
     */
    public static void addCookie(HttpServletResponse response, String name, String value,Boolean useDefaultTime) {
        addCookie( response, name,  value,  null, "/", "", null, useDefaultTime);
    }
	
	/**
	 * 添加cookie
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param name
	 *            cookie名称
	 * @param value
	 *            cookie值
	 */
	public static void addCookie(HttpServletResponse response, String name, String value) {
		addCookie(response, name, value, null, "/", "", null, true);
	}
	
	/**
	 * 添加cookie
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param name
	 *            cookie名称
	 * @param value
	 *            cookie值
	 * @param maxAge
	 *            有效期(单位: 秒)
	 * @param path
	 *            路径
	 * @param domain
	 *            域
	 * @param secure
	 *            是否启用加密
	 */
	public static void addCookie(HttpServletResponse response,
			String name, String value, Integer maxAge, String path, 
			String domain, Boolean secure, Boolean useDefaultTime) {
		Assert.notNull(response);
		Assert.hasText(name);
		
		if (maxAge == null && useDefaultTime) {
			maxAge=MaxAge;
		}
		
		try {
			name = URLEncoder.encode(name, "UTF-8");
			value = URLEncoder.encode(value, "UTF-8");
			Cookie cookie = new Cookie(name, value);
			if (maxAge != null) {
				cookie.setMaxAge(maxAge);
			}
			if (StringUtils.isNotEmpty(path)) {
				cookie.setPath(path);
			}
			if (StringUtils.isNotEmpty(domain)) {
				cookie.setDomain(domain);
			}
			if (secure != null) {
				cookie.setSecure(secure);
			}
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 移除cookie
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param name
	 *            cookie名称
	 * @param path
	 *            路径
	 * @param domain
	 *            域
	 */
	public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name, String path, String domain) {
		Assert.notNull(request);
		Assert.notNull(response);
		Assert.hasText(name);
		try {
			name = URLEncoder.encode(name, "UTF-8");
			Cookie cookie = new Cookie(name, null);
			cookie.setMaxAge(0);
			
			if (StringUtils.isNotEmpty(path))
			{
				cookie.setPath(path);
			}
			
			if (StringUtils.isNotEmpty(domain))
			{
				cookie.setDomain(domain);
			}
			
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 移除cookie
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param name
	 *            cookie名称
	 */
	public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		removeCookie(request, response, name, "/", "");
	}

	/**
	 * 获取推荐注册、店中店推荐等逻辑的推荐者id
	 * @param request
	 * @return
	 */
	public static String getCid(HttpServletRequest request) {
		return getCookieValue(request, SHOPINSHOP_USERID);
	}

	/**
	 * 设置推荐注册、店中店推荐等逻辑的推荐者id到cookie
	 * @param response
	 * @param cId
	 */
	public static void setCid(HttpServletResponse response, String cId) {
		setCid(response, cId, null);
	}

	/**
	 * 	设置 当前定位的 位置到cookie
	 * @param response
	 * @param name
	 * @param value
	 */
	public static void setGetLocation(HttpServletResponse response, String name, String value){
		addCookie(response, name,  value,  Closed, "/", "", null, false);
	}

	/**
	 * 设置推荐注册、店中店推荐等逻辑的推荐者id到cookie
	 * @param response
	 * @param cId
	 * @param maxAge 最长的存活时间
	 */
	public static void setCid(HttpServletResponse response, String cId, Integer maxAge) {
		if (maxAge == null) {
			addCookie(response, SHOPINSHOP_USERID, cId);
		} else {
			addCookie(response, SHOPINSHOP_USERID, cId, maxAge);
		}
	}

	/**
	 * 除了当前登录会员的id之外，设置推荐注册、店中店推荐等逻辑的推荐者id到cookie
	 * @param request
	 * @param response
	 * @param cId
	 */
	public static void setCidWithoutMyself(HttpServletRequest request, HttpServletResponse response, String cId) {
		setCidWithoutMyself(request, response, cId, null);
	}

	/**
	 * 除了当前登录会员的id之外，设置推荐注册、店中店推荐等逻辑的推荐者id到cookie
	 * @param request
	 * @param response
	 * @param cId
	 * @param maxAge 最长存活期
	 */
	public static void setCidWithoutMyself(HttpServletRequest request, HttpServletResponse response, String cId, Integer maxAge) {
		if (StringUtils.isNotBlank(getCid(request))) {
		}
		setCid(response, cId, maxAge);
	}
	/**
	 * cookie设置门店id
	 * @param request
	 * @param response
	 * @param sId
	 */
	public static void setSid( HttpServletRequest request,HttpServletResponse response, String sId){
		addCookie(response,STORE_ID,sId,Closed);
		removeCookie(request, response, M_STORE_USER_ID);
	}
}
