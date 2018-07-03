package com.zdran.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Create by ranzd on 2018/7/3
 *
 * @author cm.zdran@gmail.com
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.zdran.springboot.mapper", sqlSessionFactoryRef = "learningSqlSessionFactory")
public class MyBatisConfig {
    private Logger logger = LoggerFactory.getLogger(MyBatisConfig.class);

    @Autowired
    DruidDataSourceProperties druidDataSourceProperties;

    @Bean(name = "learningSqlSessionFactory")
    @Primary
    public SqlSessionFactory learningSqlSessionFactory() throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(getDruidDataSource());
        sessionFactory.setTypeAliasesPackage("com.zdran.springboot.dao");
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:/mybatis/mapping/*.xml"));
        return sessionFactory.getObject();
    }

    @Bean(name = "learningDataSource")
    public DataSource getDruidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(druidDataSourceProperties.getDriverClassName());
        druidDataSource.setUrl(druidDataSourceProperties.getUrl());
        druidDataSource.setUsername(druidDataSourceProperties.getUsername());
        druidDataSource.setPassword(druidDataSourceProperties.getPassword());
        druidDataSource.setInitialSize(druidDataSourceProperties.getInitialSize());
        druidDataSource.setMinIdle(druidDataSourceProperties.getMinIdle());
        druidDataSource.setMaxActive(druidDataSourceProperties.getMaxActive());
        druidDataSource.setMaxWait(druidDataSourceProperties.getMaxWait());
        druidDataSource.setTimeBetweenEvictionRunsMillis(druidDataSourceProperties.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(druidDataSourceProperties.getMinEvictableIdleTimeMillis());
        druidDataSource.setTestOnBorrow(druidDataSourceProperties.isTestOnBorrow());
        druidDataSource.setTestOnReturn(druidDataSourceProperties.isTestOnReturn());
        druidDataSource.setTestWhileIdle(druidDataSourceProperties.isTestWhileIdle());
        druidDataSource.setKeepAlive(druidDataSourceProperties.isKeepAlive());
        druidDataSource.setRemoveAbandoned(druidDataSourceProperties.isRemoveAbandoned());
        druidDataSource.setRemoveAbandonedTimeout(druidDataSourceProperties.getRemoveAbandonedTimeout());
        druidDataSource.setLogAbandoned(druidDataSourceProperties.isLogAbandoned());

        druidDataSource.setPoolPreparedStatements(druidDataSourceProperties.isPoolPreparedStatements());
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(druidDataSourceProperties.getMaxPoolPreparedStatementPerConnectionSize());
        try {
            druidDataSource.setFilters(druidDataSourceProperties.getFilters());
            druidDataSource.init();
        } catch (SQLException e) {
            logger.error("数据源初始化失败", e);
        }
        return druidDataSource;
    }
}
