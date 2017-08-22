package ru.cboss.config;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import ru.cboss.config.mappers.ProblemTaskMapper;
import ru.cboss.core.db.DBManager;

import javax.sql.DataSource;

/**
 * Created by ishafigullin on 13.06.2017.
 */
public class MyBatisConfig {
    private MyBatisConfig(){}

    public static SqlSessionFactory getInstance(){
        return MyBatisConfigHolder.INSTANCE;
    }

    private static class MyBatisConfigHolder {
        private final static SqlSessionFactory INSTANCE;
        // config myBatis SqlSessionFactory:
        static {
            DataSource dataSource = DBManager.getDataSource();
            TransactionFactory transactionFactory = new JdbcTransactionFactory();
            Environment environment = new Environment("development", transactionFactory, dataSource);
            Configuration configuration = new Configuration(environment);
            String packageName = ProblemTaskMapper.class.getPackage().getName();
            // System.out.println("+++++++++++++ packageName = " + packageName); // TODO temp out
            configuration.addMappers(packageName);
            // configuration.addMapper(UserMapper.class);
            /* String resource = "org/mybatis/.../mybatis-config.xml"; TODO configure in XML better
            InputStream inputStream = Resources.getResourceAsStream(resource);
            INSTANCE = new SqlSessionFactoryBuilder().build(inputStream); */
            INSTANCE = new SqlSessionFactoryBuilder().build(configuration);

            // System.out.println("++++++++++++++ init MyBatisConfigHolder.INSTANCE = " + INSTANCE); // TODO temp out
        }
    }
}
