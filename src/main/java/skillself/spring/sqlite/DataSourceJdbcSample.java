package skillself.spring.sqlite;

import org.springframework.context.support.GenericXmlApplicationContext;
import skillself.spring.sqlite.dao.ContactDao;

/**
 * Polytech
 * Created by igor on 06.04.17.
 */
public class DataSourceJdbcSample {
    public static void main(final String[] args) {
        final GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/sqlite/datasource-drivermanager-basic.xml");
        //ctx.load("classpath:spring/sqlite/embeded-spring-base.xml");

        ctx.refresh();
        final ContactDao contactDao = ctx.getBean("contactDaoBasic", ContactDao.class);

        System.out.println("First name for contact id 1 is: " +
                contactDao.findFirstNameByid(2L));

        contactDao.findAll().forEach(System.out::println);
    }
}
