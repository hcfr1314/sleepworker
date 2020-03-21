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
@MapperScan(basePackages = "com.huanghe.alarm.api.mapper.nari", sqlSessionTemplateRef = "nariSqlSessionTemplate")
public class DataSoruceNariConfig {

    @Bean(name = "dataSourceNari")
    @ConfigurationProperties(prefix = "spring.datasource.nari")
    public DataSource getDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "nariSqlSessionFactory")
    public SqlSessionFactory getSqlFactory(@Qualifier("dataSourceNari") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/nari/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "nariTransactionManager")
    public DataSourceTransactionManager getTransactionManager(@Qualifier("dataSourceNari") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("nariSqlSessionTemplate")
    public SqlSessionTemplate getTemplate(@Qualifier("nariSqlSessionFactory") SqlSessionFactory factory) {
        return new SqlSessionTemplate(factory);
    }


}
