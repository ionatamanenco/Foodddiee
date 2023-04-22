package ia.ffy.foodforyou.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan("ia.ffy.foodforyou")
@EnableJpaRepositories("ia.ffy.foodforyou")
@EnableTransactionManagement
public class DomainConfig {
}
