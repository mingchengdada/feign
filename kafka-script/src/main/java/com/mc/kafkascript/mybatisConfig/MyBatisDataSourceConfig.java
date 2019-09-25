/*
package com.mc.kafkascript.mybatisConfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurationSelector;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.StringTokenizer;

*/
/**
 * 项目名称:   pinkstone
 * 包:        com.mc.kafkascript.mybatisConfig
 * 类名称:     MyBatisDataSourceConfig
 * 类描述:     类功能描述
 * 创建人:     mc
 * 创建时间:   2019/9/25 10:29
 *//*

@Configuration
public class MyBatisDataSourceConfig {

    private static final String MAPPER_LOCATION = "classpath:sqlmap/*Mapper.xml";


    public DataSource businessDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("");
        dataSource.setUsername("");
        dataSource.setPassword("");
        dataSource.setInitialSize(5);
        dataSource.setMinIdle(5);
        dataSource.setMaxWait(60000);
        dataSource.setMaxActive(20);
        dataSource.setValidationQuery("select 1");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnReturn(false);
        dataSource.setTestOnBorrow(false);
        dataSource.setRemoveAbandoned(true);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        dataSource.setPoolPreparedStatements(true);
        String connectionInitSqls = "SET NAMES utf8mb4";
        StringTokenizer tokenizer = new StringTokenizer(connectionInitSqls, ";");
        dataSource.setConnectionInitSqls(Collections.list(tokenizer));//重点设置该参数
//        dataSource.setFilters("stat,wall,log4j");
        return dataSource;
    }

    @Bean
    @Primary
    public DataSourceTransactionManager businessTransactionManager() throws SQLException {
        return new DataSourceTransactionManager(businessDataSource());
    }

    @Bean
    @Primary
    public SqlSessionFactory businessSqlSessionFactory() throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(businessDataSource());
        sessionFactory.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(MyBatisDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
*/
