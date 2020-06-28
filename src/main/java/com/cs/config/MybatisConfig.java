package com.cs.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.cs.repositories"}, sqlSessionTemplateRef = "sqlSessionTemplate")
public class MybatisConfig {

  @Bean
  SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    sqlSessionFactoryBean.setTypeAliasesPackage("api.dto");
    sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
        .getResources("classpath*:mapper/*.xml"));
    return sqlSessionFactoryBean.getObject();
  }

  @Bean
  SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
    return new SqlSessionTemplate(sqlSessionFactory);
  }

  @Bean
  PlatformTransactionManager PlatformTransactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }


}
