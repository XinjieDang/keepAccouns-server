package com.dxj.security.common.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  响应工具类
 */
public class WebUtils {
    /**
     * 将字符串渲染到客户端
     * @param response
     * @param string
     * @return
     */
    public static String renderString(HttpServletResponse response,String string){
        try {
            response.setStatus(200);
            response.setContentType("applicaiton/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
