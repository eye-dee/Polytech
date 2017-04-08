package prospring.chapter4.classforconfig;

import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Polytech
 * Created by igor on 17.03.17.
 */

@Configuration
@ImportResource(value="classpath:META-INF/spring/app-context-xml.xml")
@PropertySource(value="classpath:message.properties")
@ComponentScan(basePackages = {"prospring.chapter4"})
@EnableTransactionManagement
public class AppConfig {
    /*@Autowired
    Environment env;
*/
    @Bean
    @Lazy(value = true)
    public MessageProvider messageProvider() {
        return new ConfigurableMessageProvider("Hello World"/*env.getProperty("message")*/);
    }

    @Bean(name = "messageRenderer")
    @Scope(value = "prototype")
    @DependsOn(value = "messageProvider")
    public MessageRenderer messageRenderer() {
        final MessageRenderer renderer = new StandartOutMessageRenderer();
        renderer.setMessageProvider(messageProvider());

        return renderer;
    }
}
