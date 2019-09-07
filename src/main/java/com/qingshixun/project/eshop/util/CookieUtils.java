package com.qingshixun.project.eshop.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Component
public class CookieUtils {

    /**
     * 设置cookie
     * @param name  cookie名字
     * @param value cookie值
     * @param request
     * @param response
     */
    public  void addCookie(String name, String value,HttpServletRequest request,HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String pid= value;
        String pids  = pid;
        if(cookies != null){
            for (Cookie cookie : cookies){
                if(name.equals(cookie.getName())){
                    pids = cookie.getValue();
                    //1-3-2 本次访问商品pid是8----->8-1-3-2
                    //1-3-2 本次访问商品pid是3----->3-1-2
                    //1-3-2 本次访问商品pid是2----->2-1-3
                    //将pids拆成一个数组
                    String[] split = pids.split("-"); //{3,1,2}
                    List<String> pidList = Arrays.asList(split);  //[3,1,2]
                    LinkedList<String> list = new LinkedList<String>(pidList); //[3,1,2]
                    //判断集合中是否存在当前pid,如果存在，则删除它，然后再首部再添加pid
                    if(list.contains(value)){
                        list.remove(pid);
                    }
                    list.addFirst(pid);
                    //再将[3,1,2]转成3-1-2的字符串
                    StringBuffer sb = new StringBuffer();
                    for(int i=0; i<list.size() && i<5; i++){
                        sb.append(list.get(i));
                        sb.append("-");
                    }
                    //去掉3-1-2-后的 -
                    pids = sb.substring(0, sb.length() - 1);
                }
            }
        }
        Cookie cookiePids = new Cookie("HistoryProductList", pids);
        cookiePids.setPath("/");
        response.addCookie(cookiePids);
    }

    /**
     * 根据Cookie名称得到Cookie对象，不存在该对象则返回Null
     * @param name
     * @param request
     * @return
     */
    public static Cookie getCookie(String name,HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies==null||cookies.length<1) {
            return null;
        }
        Cookie cookie = null;
        for (Cookie c : cookies) {
            if (name.equals(c.getName())) {
                cookie = c;
                break;
            }
        }
        return cookie;
    }
    /**
     * 检索所有Cookie封装到Map集合
     *
     * @param request
     * @return
     */
    public Map<String, String> getCookieMap(HttpServletRequest request) {
        Map<String, String> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies&&cookies.length>1) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie.getValue());
            }
        }
        return cookieMap;
    }

    /**
     * 通过Key(Cookie名称)获取Value
     * @return Value
     */
    public  String getCookieValueByName(String name,HttpServletRequest request) {
        Cookie cookie = getCookie(name,request);
        if(cookie != null){
            return cookie.getValue();
        }
        return null;
    }
    /**
     * 移除cookie
     * @param name   Cookie key
     */
    public static void removeCookie(String name,HttpServletRequest request,HttpServletResponse response) {
        if (null == name) {
            return;
        }
        Cookie cookie = getCookie(name,request);
        if(null != cookie){
            cookie.setPath("/");
            cookie.setValue("");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }
}
