package skillself.spring.sqlite;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.object.SqlUpdate;
import skillself.spring.sqlite.dao.ContactDao;
import skillself.spring.sqlite.dao.ContactDaoImpl;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Polytech
 * Created by igor on 06.04.17.
 */

@Configuration
//@PropertySource(value = "classpath:spring/sqlite/jdbc.properties")
@ComponentScan(basePackages = "skillself.spring.sqlite")
public class SqliteJavaConfig {
    private static final String SQL_UPDATE_CONTACT = "Update Contact set " +
            "firstName = :firstName, lastName = :lastName, birthDate = :birthDate where id = :id";
    private static final String SQL_INSERT_CONTACT = "Insert Into Contact (firstName, lastName, birthDate) " +
            "Values (:firstName, :lastName, :birthDate)";

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
        return new ContactDaoImpl(dataSource(),jdbcTemplate(),namedParameterJdbcTemplate(),sqlUpdate(),sqlInsert());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());

        return jdbcTemplate;
//        return null;
    }

    @Bean
    public SqlUpdate sqlUpdate() {
        final SqlUpdate sqlUpdate = new SqlUpdate(dataSource(),SQL_UPDATE_CONTACT);
        sqlUpdate.declareParameter(new SqlParameter("firstName", Types.VARCHAR));
        sqlUpdate.declareParameter(new SqlParameter("lastName", Types.VARCHAR));
        sqlUpdate.declareParameter(new SqlParameter("birthDate", Types.DATE));
        sqlUpdate.declareParameter(new SqlParameter("id", Types.INTEGER));

        return sqlUpdate;
    }

    @Bean
    public SqlUpdate sqlInsert() {
        final SqlUpdate sqlInsert = new SqlUpdate(dataSource(),SQL_INSERT_CONTACT);
        sqlInsert.declareParameter(new SqlParameter("firstName", Types.VARCHAR));
        sqlInsert.declareParameter(new SqlParameter("lastName", Types.VARCHAR));
        sqlInsert.declareParameter(new SqlParameter("birthDate", Types.DATE));
        sqlInsert.setGeneratedKeysColumnNames(new String[]{"id"});
        sqlInsert.setReturnGeneratedKeys(true);

        return sqlInsert;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
//        return null;
    }
}
