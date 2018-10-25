package com.web.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@PropertySource("classpath:application.properties")
@MapperScan(value = "com.web.mapper2", sqlSessionFactoryRef = "druidSqlSessionFactory")
public class DataSourceConfig2 {

    @Value("${spring.datasource.druid.url}")
    private String url;

    @Value("${spring.datasource.druid.username}")
    private String username;

    @Value("${spring.datasource.druid.password}")
    private String password;

    @Value("${mybatis.type-aliases-package:com.web.entity}")
    private String typeAliasesPackage;

    @Value("${mybatis.mapperLocations2:classpath:com/web/mapper2/*.xml}")
    private String mapperLocations;

    @Bean(name = "druidDataSource", initMethod = "init", destroyMethod = "close")
    public DruidDataSource druidDataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        // stat是统计SQL，wall是SQL防火墙，防SQL注入的，log4j是用来输出统计数据的
        dataSource.setFilters("stat,wall");
        return dataSource;
    }

    @Bean("druidSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("druidDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage(typeAliasesPackage);
        factoryBean.setMapperLocations(resolver.getResources(mapperLocations));
        return factoryBean.getObject();
    }

    /**
     * 配置事务管理器
     */
    @Bean("tx2")
    public DataSourceTransactionManager transactionManager(@Qualifier("druidDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public DruidStatInterceptor DruidStatInterceptor() {
        return new DruidStatInterceptor();
    }

    @Bean
    @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public JdkRegexpMethodPointcut jdkRegexpMethodPointcut() {
        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPatterns("com.web.controller.*", "com.web.service.*");
        return pointcut;
    }

    @Bean
    public DefaultPointcutAdvisor druidStatAdvisor(DruidStatInterceptor druidStatInterceptor, JdkRegexpMethodPointcut druidStatPointcut) {
        DefaultPointcutAdvisor defaultPointAdvisor = new DefaultPointcutAdvisor();
        defaultPointAdvisor.setPointcut(druidStatPointcut);
        defaultPointAdvisor.setAdvice(druidStatInterceptor);
        return defaultPointAdvisor;
    }

}
