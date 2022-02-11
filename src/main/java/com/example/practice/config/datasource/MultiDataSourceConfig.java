package com.example.practice.config.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author gaopengtao
 * @version 配置类
 */
@Configuration
public class MultiDataSourceConfig {
    /**
     * 配置数据源1
     *
     * @return
     */
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "server.datasource.ds1")
    public DataSource ds1DataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置数据源2
     *
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "server.datasource.ds2")
    public DataSource ds2DataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置SqlSessionFactory
     *
     * @param ds1DataSource
     * @return
     * @throws Exception
     */
    @Bean
    @Primary
    public SqlSessionFactory ds1SqlSessionFactory(@Qualifier("ds1DataSource") DataSource ds1DataSource) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds1DataSource);
        fb.setTypeAliasesPackage("com.example.practice");
        fb.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/db1/*.xml")
        );
        return fb.getObject();
    }

    /**
     * 配置SqlSessionFactory
     *
     * @param ds2DataSource
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory ds2SqlSessionFactory(@Qualifier("ds2DataSource") DataSource ds2DataSource) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds2DataSource);
        fb.setTypeAliasesPackage("com.example.practice");
        fb.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/db2/*.xml")
        );
        return fb.getObject();
    }

    /**
     * 配置MapperScannerConfigurer
     *
     * @return
     */
    @Bean(name = "ds1MapperScannerConfigurer")
    public MapperScannerConfigurer ds1MapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setSqlSessionFactoryBeanName("ds1SqlSessionFactory");
        configurer.setBasePackage("com.example.practice");
        // 用注解的方式，为mapper接口绑定数据源
        configurer.setAnnotationClass(Ds1Dao.class);
        return configurer;
    }

    /**
     * 配置MapperScannerConfigurer
     *
     * @return
     */
    @Bean(name = "ds2MapperScannerConfigurer")
    public MapperScannerConfigurer ds2MapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setSqlSessionFactoryBeanName("ds2SqlSessionFactory");
        configurer.setBasePackage("com.example.practice");
        // 用注解的方式，为mapper接口绑定数据源
        configurer.setAnnotationClass(Ds2Dao.class);
        return configurer;
    }

    /**
     * 配置事务管理
     *
     * @param ds1DataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "ds1TransactionManager")
    @Primary
    public DataSourceTransactionManager ds1TransactionManager(@Qualifier("ds1DataSource") DataSource ds1DataSource) throws Exception {
        return new DataSourceTransactionManager(ds1DataSource);
    }

    /**
     * 配置事务管理
     *
     * @param ds2DataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "ds2TransactionManager")
    public DataSourceTransactionManager ds2TransactionManager(@Qualifier("ds2DataSource") DataSource ds2DataSource) throws Exception {
        return new DataSourceTransactionManager(ds2DataSource);
    }
}
