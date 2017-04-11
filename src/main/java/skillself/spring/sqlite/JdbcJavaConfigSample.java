package skillself.spring.sqlite;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import skillself.spring.sqlite.dao.ContactDao;
import skillself.spring.sqlite.object.Contact;
import skillself.spring.sqlite.object.ContactPhoneDetail;

/**
 * Polytech
 * Created by igor on 06.04.17.
 */

public class JdbcJavaConfigSample {
    public static void main(final String[] args) {
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SqliteJavaConfig.class);

        final ContactDao contactDao = applicationContext.getBean("contactDao", ContactDao.class);

        contactDao.findAll().forEach(System.out::println);
        contactDao.findAllWithDetails().forEach(System.out::println);

        final Contact contact = contactDao.findAll().get(0);
        final Contact newContact = Contact.builder()
                .id(contact.getId())
                .firstName("firstName2")
                .lastName("lastName2")
                .birthDate(contact.getBirthDate())
                .build();

        newContact.addPhoneDetail(ContactPhoneDetail.builder().build());
        newContact.addPhoneDetail(ContactPhoneDetail.builder().build());
        newContact.addPhoneDetail(ContactPhoneDetail.builder().build());

        contactDao.update(newContact);
        System.out.println("After Update \n-----------------------------------------");
        contactDao.findAll().forEach(System.out::println);

        contactDao.update(contact);
        System.out.println("After update \n-----------------------------------------");
        contactDao.findAll().forEach(System.out::println);

        final long id = contactDao.insert(newContact);
        System.out.println("After insert \n-----------------------------------------");
        contactDao.findAll().forEach(System.out::println);
        System.out.println("Identifier inserted = " + id);

    }
}
