package main;

import main.entities.filters.ISpamFilter;
import main.entities.filters.SpamFilter;
import main.entities.models.EmailHandler;
import main.entities.models.IEmailHandler;
import main.entities.repositories.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    @Scope("singleton")
    public IReceivedEmailsRepository receivedEmailsRepository() {
        return new EmailsRepository() {
        };
    }

    @Bean
    @Scope("singleton")
    public ISentEmailsRepository sentEmailsRepository() {
        return new EmailsRepository() {
        };
    }

    @Bean
    @Scope("singleton")
    public ISpamFilterRepository spamFilterRepository() {
        return new SpamFilterRepository() {
        };
    }

    @Bean
    @Scope("singleton")
    public ISpamFilter spamFilter() {
        return new SpamFilter(spamFilterRepository(), receivedEmailsRepository()) {
        };
    }

    @Bean
    @Scope("singleton")
    public IEmailHandler emailHandler() {
        return new EmailHandler(receivedEmailsRepository(), sentEmailsRepository(), spamFilter()) {
        };
    }
}
