package com.csye6220.shareonline.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    /**
     * sessionFactory方法中，通过形参 DataSource dataSource 即可自动注入
     * Spring Boot 根据 spring.datasource.* 配置生成的 DataSource Bean。
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();

        // 注入自动配置的 DataSource
        sessionFactoryBean.setDataSource(dataSource);

        // 指定扫描实体类的包，这里要与实体类所在包相一致
        sessionFactoryBean.setPackagesToScan("com.csye6220.shareonline.model");
        // 如果实体类在别的包，如 com.example.shareonline.model，就改成对应包名

        // 配置 Hibernate 属性
        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.format_sql", "true");
        // 开发环境下，如果需要自动建表，可加:
         props.put("hibernate.hbm2ddl.auto", "update");

        sessionFactoryBean.setHibernateProperties(props);

        return sessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        // 基于 Hibernate SessionFactory 来创建事务管理器
        return new HibernateTransactionManager(sessionFactory);
    }
}
