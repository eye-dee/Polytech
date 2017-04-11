package skillself.spring.sqlite.dao;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import skillself.spring.sqlite.object.Contact;
import skillself.spring.sqlite.object.ContactPhoneDetail;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Polytech
 * Created by igor on 04.04.17.
 */

@AllArgsConstructor
public class ContactDaoImpl implements ContactDao, InitializingBean {
    private static final RowMapper<Contact> CONTACT_MAPPER = (rs, rowNum) -> Contact.builder()
            .id(rs.getLong("id"))
            .firstName(rs.getString("firstName"))
            .lastName(rs.getString("lastName"))
            .birthDate(rs.getDate("birthDate"))
            .build();

    private static final ResultSetExtractor<List<Contact>> CONTACT_EXTRACTOR = resultSet -> {
        Map<Long, Contact> map = new HashMap<>();
            while (resultSet.next()) {
                //Long id = resultSet.getLong("id");
                //Long id = resultSet.getLong("Contact.id");
                Long id = resultSet.getLong(1);
            /*if (map.get(id) == null) {
                map.put(id,
                        Contact.builder().build())
            }*/
                Contact contact = map.computeIfAbsent(id, k -> {
                    try {
                        return Contact.builder()
                                .id(id)
                                .firstName(resultSet.getString("firstName"))
                                .lastName(resultSet.getString("lastName"))
                                .birthDate(resultSet.getDate("birthDate"))
                                .build();
                    } catch (final SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    return Contact.builder().build();
                });

                //Long contactPhoneId = resultSet.getLong("ContactPhoneDetail.id");
                Long contactPhoneId = resultSet.getLong(5);
                if (contactPhoneId > 0) {
                    contact.addPhoneDetail(ContactPhoneDetail.builder()
                            .id(contactPhoneId)
                            .contactId(contact.getId())
                            .phoneNumber(resultSet.getString("phoneNumber"))
                            .phoneType(resultSet.getString("phoneType"))
                            .build());
                }
            }
        return map.values().stream().collect(Collectors.toList());
    };

    /*private static final String SQL_UPDATE_CONTACT = "Update Contact set " +
            "firstName = :firstName, lastName = :lastName, birthDate = :birthDate where id = :id";
    private static final String SQL_INSERT_CONTACT = "Insert Into Contact (firstName, lastName, birthDate) " +
            "Values (:firstName, :lastName, :birthDate)";*/

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SqlUpdate sqlUpdate;
    private SqlUpdate sqlInsert;

    /*public ContactDaoImpl(final DataSource dataSource,
                          final JdbcTemplate jdbcTemplate,
                          final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }*/

   /* public void setDataSource(final DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate();
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        jdbcTemplate.setDataSource(dataSource);

        // TODO: 10.04.17 to Java config
        sqlUpdate = new SqlUpdate(dataSource,SQL_UPDATE_CONTACT);
        sqlUpdate.declareParameter(new SqlParameter("firstName", Types.VARCHAR));
        sqlUpdate.declareParameter(new SqlParameter("lastName", Types.VARCHAR));
        sqlUpdate.declareParameter(new SqlParameter("birthDate", Types.DATE));
        sqlUpdate.declareParameter(new SqlParameter("id", Types.INTEGER));

        sqlInsert = new SqlUpdate(dataSource,SQL_INSERT_CONTACT);
        sqlInsert.declareParameter(new SqlParameter("firstName", Types.VARCHAR));
        sqlInsert.declareParameter(new SqlParameter("lastName", Types.VARCHAR));
        sqlInsert.declareParameter(new SqlParameter("birthDate", Types.DATE));
        sqlInsert.setGeneratedKeysColumnNames(new String[]{"id"});
        sqlInsert.setReturnGeneratedKeys(true);

        System.out.println("dataSource = " + dataSource);
    }*/

    @Override
    public List<Contact> findAll() {
        return jdbcTemplate.query(
                "select * from Contact",
                CONTACT_MAPPER
        );
    }

    @Override
    public List<Contact> findByFirstName(final String firstName) {
        return jdbcTemplate.query(
                "select * from contact where firstName = ?",
                new Object[]{firstName},
                CONTACT_MAPPER
        );
    }

    @Override
    public String findLastNameById(final Long id) {
        final String sql = "select lastName from contact where id = :contactld";

        final Map<String, Object> namedParameters = new HashMap<>();

        namedParameters.put("contactld", id);

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, String.class);

        /*return jdbcTemplate.queryForObject(
                "select lastName from contact where id = ?",
                new Object[]{id},
                String.class
        );*/
    }

    @Override
    public String findFirstNameByid(final Long id) {
        return jdbcTemplate.queryForObject(
                "select firstName from contact where id = ?",
                new Object[]{id}, String.class);
    }

    @Override
    public long insert(final Contact contact) {
        final Map<String,Object> parameters = new HashMap<>();
        parameters.put("firstName",contact.getFirstName());
        parameters.put("lastName",contact.getLastName());
        parameters.put("birthDate",contact.getBirthDate());

        final KeyHolder keyHolder = new GeneratedKeyHolder();

        sqlInsert.updateByNamedParam(parameters,keyHolder);

        return keyHolder.getKey().longValue();
        //return Contact.builder()...build();
        // TODO: 10.04.17 log it
    }

    @Override
    public void update(final Contact contact) {
        final Map<String,Object> parameters = new HashMap<>();
        parameters.put("firstName",contact.getFirstName());
        parameters.put("lastName",contact.getLastName());
        parameters.put("birthDate",contact.getBirthDate());
        parameters.put("id",contact.getId());

        sqlUpdate.updateByNamedParam(parameters);

        // TODO: 10.04.17 log it
    }

    @Override
    public void delete(final Long contactId) {

    }

    @Override
    public List<Contact> findAllWithDetails() {
//        return jdbcTemplate.query(
//                "select * from Contact " +
//                        "INNER JOIN ContactPhoneDetail on Contact.id = ContactPhoneDetail.contactId",
//                CONTACT_EXTRACTOR
//                );
//        return jdbcTemplate.query(
//                "select * from Contact " +
//                        "INNER JOIN ContactPhoneDetail on Contact.id = ContactPhoneDetail.contactId",
//                CONTACT_EXTRACTOR
//                );

        return jdbcTemplate.query(
                "select * from Contact " +
                        "LEFT JOIN ContactPhoneDetail on Contact.id = ContactPhoneDetail.contactId",
                CONTACT_EXTRACTOR
        );
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSource == null) {
            throw new BeanCreationException("Data source is null");
        }
    }
}
