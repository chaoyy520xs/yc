package com.jjkj.main.config.druid;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;

@WebServlet(urlPatterns = "/druid/*", 
initParams={
        @WebInitParam(name="allow",value="192.168.16.110,127.0.0.1"),// IP白名单 (没有配置或者为空，则允许所有访问)
        @WebInitParam(name="deny",value=""),// IP黑名单 (存在共同时，deny优先于allow)
        @WebInitParam(name="loginUsername",value="root"),// 用户名
        @WebInitParam(name="loginPassword",value="root"),// 密码
        @WebInitParam(name="resetEnable",value="false")// 禁用HTML页面上的“Reset All”功能
})
@Configuration
public class DruidConfig extends StatViewServlet {
	private static final long serialVersionUID = 1L;
	@ConfigurationProperties(prefix="spring.datasource.druid")
	@Bean
	public DataSource druid() {
		return new DruidDataSource();
	}
	
/*
 * ！！！无法启用以下文件，目前原因暂未查明。用同包以下两个类@WebFilter拦截，实现相同功能
 * @author yc
 */
//	配置druid监控
//	1.配置一个管理后台的Servlet
//	@Bean
//	public ServletRegistrationBean statViewServlet() {
//		ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
//		Map<String,String> initParameters = new HashMap<>();
//		
//		initParameters.put("loginUsername", "admin");
//		initParameters.put("loginPassword", "123456");
//		initParameters.put("allow", "");//默认允许所有访问
//		initParameters.put("deny", "192.168.1.1");	
//		bean.setInitParameters(initParameters);
//		return bean;
//	}
//	
//	@Bean
//	public FilterRegistrationBean<WebStatFilter> webStatFilter() {
//		FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean();
//		bean.setFilter(new WebStatFilter());
//		Map<String,String> initParameters = new HashMap<>();
//		initParameters.put("exclusions", "*.js,*.css,/druid/*");
//		bean.setInitParameters(initParameters);
//		return bean;
//		
//	}

}
