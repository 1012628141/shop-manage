package cn.pyj520.shop.api.config;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * @Description:mybatis相关配置
 * @Author: zjy
 * @Date: 2020-03-19 10:12
 */

@Configuration
public class MybatisConfig {

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    /**
     * @Author: zjy on 2020-03-19 10:15
     * @Description:配置分页插件
     */
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("dialect", "Mysql");
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("supportMethodsArguments", "false");
        pageHelper.setProperties(p);
        return pageHelper;
    }

    /**
     * @Author: zjy on 2020-06-29 14:03
     * @Description:在原有的sqlsession上增加配置
     */
    @PostConstruct
    public void mybatisConfig() {
        Iterator var1 = this.sqlSessionFactoryList.iterator();
        while (var1.hasNext()) {
            SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) var1.next();
            //设置驼峰自动下划线互相转化
            sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
        }

    }

}
