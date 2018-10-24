package com.web.filter;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/druid/*", initParams = {
        /** 白名单，如果不配置或value为空，则允许所有 */
        @WebInitParam(name = "allow", value = ""),
        /** 黑名单，与白名单存在相同IP时，优先于白名单 */
        @WebInitParam(name = "deny", value = ""),
        /** false为不允许清空统计数据 */
        @WebInitParam(name = "resetEnable", value = "false"),
        /** 用户名 */
        @WebInitParam(name = "loginUsername", value = ""),
        /** 密码 */
        @WebInitParam(name = "loginPassword", value = "")
})
public class DruidStatViewServlet extends StatViewServlet {
}
