package com.group3.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author wl_sun
 * @description TODO
 * @create Date
 */
@Configuration
@MapperScan(value = "com.group3.mapper")
@PropertySource("classpath:db.properties")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement
public class MapperConfig {
    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    /**
     * 数据连接地址池配置
     *
     * @return druidDataSource
     */
    @Bean
    public DruidDataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        //配置数据库基本的连接属性
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setUrl(jdbcUrl);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        // 配置初始化大小、最小、最大连接数
        druidDataSource.setInitialSize(1);
        druidDataSource.setMinIdle(1);
        druidDataSource.setMaxActive(20);
        // 配置获取连接等待超时的时间
        druidDataSource.setMaxWait(60000);
        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        // 配置一个连接在池中最小生存的时间，单位是毫秒
        druidDataSource.setMinEvictableIdleTimeMillis(300000);
        // 禁止自动提交，实现事务管理
        druidDataSource.setDefaultAutoCommit(false);
        //设置连接池启用预处理事务集
        druidDataSource.setPoolPreparedStatements(true);
        return druidDataSource;
    }

    /**
     * 装配MyBatis的SqlSessionFactory
     *
     * @return SqlSessionFactory
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(druidDataSource());
        //指定xml文件路径
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*.xml"));
        // 开启开启驼峰命名转换
        sessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sessionFactory.getObject();
    }

//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory());
//    }

    /**
     * 装配事务管理器
     *
     * @return DataSourceTransactionManager
     */
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() {
        DataSourceTransactionManager dtc = new DataSourceTransactionManager();
        dtc.setDataSource(druidDataSource());
        return dtc;
    }
}