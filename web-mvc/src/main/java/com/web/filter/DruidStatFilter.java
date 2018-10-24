package com.web.filter;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(urlPatterns = "/*", initParams = {
        /** 忽略资源 */
        @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*"),
        /** 会话统计最大数,默认1000 */
        @WebInitParam(name = "sessionStatMaxCount", value = "2000"),
        /** 监控当前COOKIE的用户,value为cookieName */
        @WebInitParam(name = "principalCookieName", value = "USER_COOKIE"),
        /** 监控当前SESSION的用户,value为sessionName */
        @WebInitParam(name = "principalSessionName", value = "USER_SESSION"),
        /** 监控单个url访问数据库情况 */
        @WebInitParam(name = "profileEnable", value = "true")
})
public class DruidStatFilter extends WebStatFilter {
}
