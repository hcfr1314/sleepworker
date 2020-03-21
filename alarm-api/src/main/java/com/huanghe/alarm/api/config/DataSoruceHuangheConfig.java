package com.huanghe.alarm.api.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.huanghe.alarm.api.mapper.huanghe", sqlSessionTemplateRef = "huangheSqlSessionTemplate")
public class DataSoruceHuangheConfig {

    /**
     * 创建数据源
     * @return
     */
    @Bean(name = "dataSourceHuanghe")
    @ConfigurationProperties(prefix = "spring.datasource.huanghe")
    @Primary
    public DataSource getDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 创建sessionfactory
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "huangheSqlSessionFactory")
    @Primary
    public SqlSessionFactory getSqlFactory(@Qualifier("dataSourceHuanghe") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/huanghe/*.xml"));
        return bean.getObject();
    }

    /**
     * 创建事务管理
     * @param dataSource
     * @return
     */
    @Bean(name = "huangheTransactionManager")
    @Primary
    public DataSourceTransactionManager getTransactionManager(@Qualifier("dataSourceHuanghe") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 创建模板
     * @param factory
     * @return
     */
    @Bean("huangheSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate getTemplate(@Qualifier("huangheSqlSessionFactory") SqlSessionFactory factory) {
        return new SqlSessionTemplate(factory);
    }

}
