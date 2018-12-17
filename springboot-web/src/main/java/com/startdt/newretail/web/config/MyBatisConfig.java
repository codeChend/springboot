package com.startdt.newretail.web.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by weilong
 */
@Slf4j
@Configuration
@MapperScan("com.startdt.sgminiprogram.dal.mapper")
public class MyBatisConfig {
    @Value("${spring.datasource.url}")
    String dbUrl;
    @Value("${spring.datasource.username}")
    String userName;
    @Value("${spring.datasource.password}")
    String password;

    @Value("${spring.datasource.driverClassName}")
    String driverClassName;
    private static String MYBATIS_CONFIG = "mybatis_config.xml";
    private static String MAPPER_PATH = "/mapper/*.xml";


    @Bean
    public SqlSessionFactoryBean createSqlSessionFactoryBean(
        @Qualifier("dataSourceMybatis") DataSource dataSource) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //设置mybatis configuration 扫描路径
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));
        //添加mapper 扫描路径
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + MAPPER_PATH;
        sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageSearchPath));
        //设置datasource
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    @Bean
    public DataSource dataSourceMybatis() {
        Map<String,Object> properties=new HashMap<>();
        properties.put(DruidDataSourceFactory.PROP_DRIVERCLASSNAME,driverClassName);
        properties.put(DruidDataSourceFactory.PROP_URL,dbUrl);
        properties.put(DruidDataSourceFactory.PROP_USERNAME,userName);
        properties.put(DruidDataSourceFactory.PROP_PASSWORD,password);
        //添加统计、SQL注入、日志过滤器
        properties.put(DruidDataSourceFactory.PROP_FILTERS,"stat,wall,logback");
        //sql合并，慢查询定义为5s
        properties.put(DruidDataSourceFactory.PROP_CONNECTIONPROPERTIES,"druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000");
        try {
            return DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            log.error("数据库连接失败:",e);
        }
        return null;
    }

}
