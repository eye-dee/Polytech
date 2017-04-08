package skillself.spring.sqlite;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import skillself.spring.sqlite.dao.ContactDao;
import skillself.spring.sqlite.dao.ContactDaoImpl;

import javax.sql.DataSource;

/**
 * Polytech
 * Created by igor on 06.04.17.
 */

@Configuration
//@PropertySource(value = "classpath:spring/sqlite/jdbc.properties")
@ComponentScan(basePackages = "skillself.spring.sqlite")
public class SqliteJavaConfig {
    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:/home/igor/projects/SQLite/SkillSelf/skillself.db");

        return dataSource;
//        return null;
    }

    @Bean
    public ContactDao contactDao() {
        return new ContactDaoImpl(dataSource(),jdbcTemplate(),namedParameterJdbcTemplate());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());

        return jdbcTemplate;
//        return null;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
//        return null;
    }
}
